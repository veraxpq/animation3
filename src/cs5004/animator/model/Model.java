package cs5004.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;


/**
 * This class represents the model interface.
 */
public interface Model {

  /**
   * This method add a shape to the model.
   *
   * @param shape the given shape
   * @throws IllegalArgumentException when the shape is null
   */
  void addShape(Shape shape) throws IllegalArgumentException;

  /**
   * This method add shape with the given parameters.
   *
   * @param position      the given position of the shape
   * @param color         the given color
   * @param width         the given width
   * @param height        the given height
   * @param type          the given type
   * @param name          the given name
   * @param appearTime    the given appear time
   * @param disappearTime the given disappear time
   * @throws IllegalArgumentException when the position is null, the color is null, the width
   *                            or height is negative, the type is null, the name is null or
   *                            length = 0,  the appear time is negative and the disappear time
   *                            is earlier than the appear time
   */
  void addShape(Point2D position, Color color, double width,
                double height, NameOfShape type, String name, int appearTime, int disappearTime)
          throws IllegalArgumentException;

  /**
   * This method add an animation to the model with the given name of the shape and animation.
   *
   * @param id the name of the shape
   * @param animation the given animation
   */
  void addAnimation(String id, Animation animation);

  /**
   * Get the list of the shapes at the given time.
   *
   * @param tick the given tick
   * @return the list of the shapes at the given time
   */
  List<Shape> getShapeAtTick(int tick);

  /**
   * Get the string of the model object.
   *
   * @return the string of the model object
   */
  String toString();

  /**
   * This method removes the given shape in the model.
   *
   * @param shape the given shape
   * @throws IllegalArgumentException when the given shape is null or not existent
   */
  void removeShape(Shape shape) throws IllegalArgumentException;

  /**
   * This method remove the the shape of the given id.
   *
   * @param id the given id
   * @throws IllegalArgumentException when the given id is null or not existent
   */
  void removeShape(String id) throws IllegalArgumentException;

  /**
   * Get the shape of the given id.
   *
   * @param id the given id
   * @return the shape of the given id
   * @throws IllegalArgumentException when the given id is null or not existent
   */
  Shape getShape(String id) throws IllegalArgumentException;

  /**
   * Get the hashmap of shapes in model.
   *
   * @return the hashmap of shapes
   */
  Map<String, Shape> getMapOfShapes();

  /**
   * Get the hashmap of animations in model.
   *
   * @return the hashmap of animations
   */
  Map<String, List<Animation>> getMapOfAnimations();

  /**
   * Set the size and position of canvas.
   *
   * @param x the x position
   * @param y the y position
   * @param width the canvas width
   * @param height the canvas height
   */
  void setCanvas(int x, int y, int width, int height);

  /**
   * Method to get the X coordinate of canvas.
   *
   * @return top left X coordinate
   */
  int getCanvasX();

  /**
   * Method to get the y coordinate of canvas.
   *
   * @return the y coordinate of canvas
   */
  int getCanvasY();

  /**
   * Get the canvas width.
   *
   * @return canvas width
   */
  int getCanvasWidth();

  /**
   * Get the canvas height.
   *
   * @return canvas height
   */
  int getCanvasHeight();

  /**
   * Get the list of shapes in model.
   *
   * @return canvas height
   */
  List<Shape> getListOfShapes();


}
