package cs5004.animator.model;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



import cs5004.animator.util.AnimationBuilder;

/** This class represents the implementation of the model class. */
public class ModelImpl implements Model {
  private Map<String, Shape> mapOfShapes;
  private Map<String, List<Animation>> mapOfAnimations;
  private int canvasPositionX;
  private int canvasPositionY;
  private int canvasWidth;
  private int canvasHeight;

  /** Construct the ModelImpl object and initialized it. */
  public ModelImpl() {
    mapOfShapes = new LinkedHashMap<>();
    mapOfAnimations = new LinkedHashMap<>();
  }

  /**
   * This method add shape with the given shape.
   *
   * @param shape the given shape
   * @throws IllegalArgumentException if the shape is null
   */
  @Override
  public void addShape(Shape shape) {
    if (shape == null) {
      throw new IllegalArgumentException("Invalid shape");
    }
    if (mapOfShapes.containsKey(shape.getName())) {
      throw new IllegalArgumentException("the given name exists, please change a name");
    }
    mapOfShapes.put(shape.getName(), shape);
    // update mapOfAnimations at the same time
    mapOfAnimations.put(shape.getName(), new ArrayList<>());
  }

  /**
   * This method add shape with the given parameters.
   *
   * @param position the given position of the shape
   * @param color the given color
   * @param width the given width
   * @param height the given height
   * @param type the given type
   * @param name the given name
   * @param appearTime the given appear time
   * @param disappearTime the given disappear time
   * @throws IllegalArgumentException when the position is null, or the color is null, the width or
   *     height is negative, the type is null, the name is null or length = 0, the appear time is
   *     negative and the disappear time is earlier than the appear time
   */
  @Override
  public void addShape(
      Point2D position,
      Color color,
      double width,
      double height,
      NameOfShape type,
      String name,
      int appearTime,
      int disappearTime) {
    if (mapOfShapes.containsKey(name)) {
      throw new IllegalArgumentException("the given name exists, please change a name");
    }
    if (type == NameOfShape.R) {
      mapOfShapes.put(
          name,
          new Rectangle(position, color, width, height, type, name, appearTime, disappearTime));
    } else if (type == NameOfShape.C) {
      mapOfShapes.put(
          name, new Oval(position, color, width, height, type, name, appearTime, disappearTime));
    } else {
      throw new IllegalArgumentException("Invalid shape");
    }

    mapOfAnimations.put(name, new ArrayList<>());
  }

  /**
   * This method add an animation to the model with the given name of the shape and animation.
   *
   * @param id the name of the shape
   * @param animation the given animation
   * @throws IllegalArgumentException if the given id or animation is null, or the id has not been
   *     saved in the map.
   */
  @Override
  public void addAnimation(String id, Animation animation) {
    if (id == null || animation == null || id.isBlank()) {
      throw new IllegalArgumentException("Invalid ID or animation");
    }

    if (mapOfAnimations.containsKey(id)) {
      if (animation.getEndTime() >= this.mapOfShapes.get(id).getDisappearTime()) {
        this.mapOfShapes.get(id).setDisappearTime(animation.getEndTime());
      }
      // a shape's move animations can't overlap, since a shape
      // can't do different moves at the same time
      if (animation.toString().contains("moves")) {
        List<Animation> listOfMove =
                mapOfAnimations.get(id).stream()
                        .filter(o -> o.toString().contains("moves"))
                        .collect(Collectors.toList());
        if (listOfMove.size() == 0) {
          mapOfAnimations.get(id).add(animation);
        } else {
          boolean a = true;
          for (Animation each : listOfMove) {
            // when their time ranges are not overlapped
            if (!(each.getEndTime() <= animation.getStartTime()
                    || each.getStartTime() >= animation.getEndTime())) {
              a = false;
              break;
            }
          }
          // if we capture one time when their time ranges are overlapped
          if (!a) {
            throw new IllegalArgumentException("each move's time can't be overlapped");
          } else {
            mapOfAnimations.get(id).add(animation);
          }
        }
      } else {
        mapOfAnimations.get(id).add(animation);
      }
    } else {
      // can't add animation before adding shape
      throw new IllegalArgumentException("there is no such shape");
    }

    //sort the animations by start time
    mapOfAnimations.get(id).sort(Comparator.comparing(o -> o.getStartTime()));

  }

  /**
   * Get the list of the shapes at the given time.
   *
   * @param time the given time
   * @return the list of the shapes at the given time
   * @throws IllegalArgumentException when the given time is negative
   */
  @Override
  public List<Shape> getShapeAtTick(int time) {
    if (time < 0) {
      throw new IllegalArgumentException("the input time is negative");
    }
    List<Shape> res = new ArrayList<>();
    for (Map.Entry each : mapOfAnimations.entrySet()) {
      Shape newShape = mapOfShapes.get(each.getKey());
      Shape resShape = null;
      String name = newShape.getName();
      for (Animation animation : (List<Animation>) each.getValue()) {
        if (animation.getStartTime() <= time && animation.getEndTime() >= time) {
          newShape = animation.play(newShape, time);
          if (newShape != null) {
            resShape = newShape.copy();
          }
        }
      }
      if (resShape != null) {
        res.add(resShape);
        if (resShape.getName().equals(res.get(res.size() - 1).getName())) {
          res.set(res.size() - 1, resShape);
        }
        mapOfShapes.put(name, resShape);
      }
    }
    return res;
  }

  /**
   * Get the string of the model object.
   *
   * @return the string of the model object
   */
  @Override
  public String toString() {
    String str = "Shapes:\n";
    String str1 = "";
    String str2 = "";

    for (Map.Entry each : mapOfAnimations.entrySet()) {
      Shape shape = mapOfShapes.get(each.getKey());
      str1 += shape.toString() + "\n\n";
    }

    for (Map.Entry each : mapOfAnimations.entrySet()) {
      Shape shape = mapOfShapes.get(each.getKey());
      for (Animation a : (ArrayList<Animation>) each.getValue()) {
        str2 += "Shape " + shape.getName();
        str2 += a.toString() + "\n";
      }
    }

    str += str1 + str2;

    return str.substring(0, str.length() - 1);
  }

  /**
   * This method removes the given shape in the model.
   *
   * @param shape the given shape
   * @throws IllegalArgumentException when the given shape is null or not existent
   */
  @Override
  public void removeShape(Shape shape) {
    if (shape == null) {
      throw new IllegalArgumentException("Invalid shape");
    }

    if (mapOfShapes.containsKey(shape.getName())) {
      mapOfShapes.remove(shape.getName());
      // update mapOfAnimations at the same time
      mapOfAnimations.remove(shape.getName());
    } else {
      throw new IllegalArgumentException("Can't remove a non-exist shape");
    }
  }

  /**
   * This method remove the the shape of the given id.
   *
   * @param id the given id
   * @throws IllegalArgumentException when the given id is null or not existent
   */
  @Override
  public void removeShape(String id) {
    if (id == null) {
      throw new IllegalArgumentException("Invalid shape's name");
    }

    if (mapOfShapes.containsKey(id)) {
      mapOfShapes.remove(id);
      // update mapOfAnimations at the same time
      mapOfAnimations.remove(id);
    } else {
      throw new IllegalArgumentException("Can't remove a non-exist shape");
    }
  }

  /**
   * Get the shape of the given id.
   *
   * @param id the given id
   * @return the shape of the given id
   * @throws IllegalArgumentException when the given id is null or not existent
   */
  @Override
  public Shape getShape(String id) {
    if (id == null) {
      throw new IllegalArgumentException("Invalid shape's name");
    }
    if (mapOfShapes.containsKey(id)) {
      return mapOfShapes.get(id);
    } else {
      throw new IllegalArgumentException("there is no such shape");
    }
  }

  @Override
  public Map<String, Shape> getMapOfShapes() {
    return this.mapOfShapes;
  }

  @Override
  public Map<String, List<Animation>> getMapOfAnimations() {
    return this.mapOfAnimations;
  }

  @Override
  public void setCanvas(int x, int y, int width, int height) {
    this.canvasPositionX = x;
    this.canvasPositionY = y;
    this.canvasWidth = width;
    this.canvasHeight = height;
  }

  @Override
  public int getCanvasX() {
    return this.canvasPositionX;
  }

  @Override
  public int getCanvasY() {
    return this.canvasPositionY;
  }

  @Override
  public int getCanvasWidth() {
    return this.canvasWidth;
  }

  @Override
  public int getCanvasHeight() {
    return this.canvasHeight;
  }

  @Override
  public List<Shape> getListOfShapes() {
    return mapOfShapes.values().stream().collect(Collectors.toList());
  }

  /**
   * This class represents the implementation of the AnimationBuilder interface. This class is used
   * with AnimationReader in order to correctly populate the contents of a text file into an
   * object of Model.
   */
  public static final class Builder implements AnimationBuilder<Model> {

    private Model model;

    /**
     * Method to construct an AnimationBuilder object. The constructor creates a model object.
     */
    public Builder() {
      this.model = new ModelImpl();
    }

    @Override
    public Model build() {
      return model;
    }

    @Override
    public AnimationBuilder<Model> setBounds(int x, int y, int width, int height) {
      model.setCanvas(x, y, width, height);
      return this;
    }

    @Override
    public AnimationBuilder<Model> declareShape(String name, String type) {
      if (type.equalsIgnoreCase("rectangle")) {
        Shape shape = new Rectangle(name, type);
        model.addShape(shape);
      }
      else if (type.equalsIgnoreCase("oval")
              || type.equalsIgnoreCase("ellipse")) {
        type = "oval";
        Shape shape = new Oval(name, type);
        model.addShape(shape);
      }
      else {
        throw new IllegalArgumentException("invalid type");
      }
      return this;


    }

    @Override
    public AnimationBuilder<Model> addMotion(String name, int t1, int x1, int y1, int w1, int h1,
        int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
      if (x1 != x2 || y1 != y2) {
        NameOfShape type = model.getShape(name).getType();
        Animation a = new Move(t1, t2, new Point(x1, y1), new Point(x2, y2), type);
        model.addAnimation(name, a);
      }

      if (w1 != w2 || h1 != h2) {
        NameOfShape type = model.getShape(name).getType();
        model.addAnimation(name, new Scale(t1, t2, w1, h1, w2, h2, type));
      }
      if (r1 != r2 || g1 != g2 || b1 != b2) {
        model.addAnimation(name, new ChangeColor(t1, t2,
                new Color(r1, g1, b1), new Color(r2, g2, b2)));
      }

      if (x1 == x2 && y1 == y2 && r1 == r2 && g1 == g2 && b1 == b2 && w1 == w2 && h1 == h2) {
        NameOfShape type = model.getShape(name).getType();
        model.addAnimation(name, new Move(t1, t2, new Point(x1, y1), new Point(x2, y2), type));
      }

      int appearTime = t1;
      int disappearTime = t2;

      Shape shape = model.getShape(name);
      if (! shape.isInitialized()) {
        if (shape.getType() == NameOfShape.C) {
          Shape initializedShape = new Oval(new Point(x1, y1), new Color(r1, g1, b1), w1,
                  h1, NameOfShape.C, name, appearTime, disappearTime);
          model.removeShape(name);
          model.addShape(initializedShape);

        } else if (shape.getType() == NameOfShape.R) {
          Shape initializedShape = new Rectangle(new Point(x1, y1), new Color(r1, g1, b1),
                  w1, h1, NameOfShape.R, name, appearTime, disappearTime);
          model.removeShape(name);
          model.addShape(initializedShape);
        } else {
          throw new IllegalArgumentException("invalid type");
        }
      }
      return this;
    }
  }
}
