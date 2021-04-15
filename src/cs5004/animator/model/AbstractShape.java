package cs5004.animator.model;


import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * This class represents an abstract class of Shape interface.
 */
public abstract class AbstractShape implements Shape {

  Point2D position;
  Color color;
  NameOfShape type;
  String name;
  int appearTime;
  int disappearTime;
  boolean isInitialized;

  /**
   * Construct the AbstractShape object with the given position, color, type, name, appear time and
   * disappear time.
   *
   * @param position      the given position
   * @param color         the given color
   * @param type          the given type
   * @param name          the given name
   * @param appearTime    the given appear time
   * @param disappearTime the given disappear time
   * @throws IllegalArgumentException when the position is null, the color is null, the width or
   *                            height is negative, the type is null, the name is null or
   *                            length = 0,  the appear time is negative and the disappear
   *                            time is earlier than the appear time
   */
  public AbstractShape(Point2D position, Color color, NameOfShape type, String name, int appearTime,
                int disappearTime) {
    if (position == null || color == null || name == null || name.length() == 0 || appearTime < 0
            || disappearTime < appearTime || type == null) {
      throw new IllegalArgumentException("the given parameter is illegal");
    }
    this.position = position;
    this.color = color;
    this.name = name;
    this.appearTime = appearTime;
    this.disappearTime = disappearTime;
    this.type = type;
    this.isInitialized = true;

  }

  /**
   * Construct the AbstractShape object with the given type, name and initialized status.
   *
   * @param type          the given type
   * @param name          the given name
   * @param isInitialized  whether the shape is initialized
   * @throws IllegalArgumentException when the type is null, the name is null
   */
  public AbstractShape(String name, String type, boolean isInitialized) {
    if (name == null || type == null) {
      throw new IllegalArgumentException("the given parameter is illegal");
    }

    this.name = name;
    this.isInitialized = isInitialized;

    if (type.equalsIgnoreCase("rectangle")) {
      this.type = NameOfShape.R;
    }
    else if (type.equalsIgnoreCase("oval")) {
      this.type = NameOfShape.C;
    }
    else {
      throw new IllegalArgumentException("invalid type");
    }

  }


  @Override
  public boolean isInitialized() {
    return this.isInitialized;
  }


  /**
   * Get the color of the object.
   *
   * @return the color of the object
   */
  @Override
  public Color getColor() {
    Color copy = new Color(this.color.getRGB());
    return copy;
  }

  /**
   * Get the name of the object.
   *
   * @return the name of the object
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Get the type of the object.
   *
   * @return the type of the object
   */
  @Override
  public NameOfShape getType() {
    return type;
  }

  /**
   * Get the position of the object.
   *
   * @return the position of the object
   */
  @Override
  public Point2D getPosition() {
    return this.position;
  }

  /**
   * Get the appear time of the object.
   *
   * @return the appear time of the object
   */
  @Override
  public int getAppearTime() {
    return this.appearTime;
  }

  /**
   * Get the disappear time of the object.
   *
   * @return the disappear time of the object
   */
  @Override
  public int getDisappearTime() {
    return this.disappearTime;
  }

  /**
   * Get the string value of the object.
   *
   * @return the string of the object
   */
  @Override
  public String toString() {
    return "";
  }

  /**
   * Set the end time of a shape.
   *
   * @param endTime the given end time of a shape
   * @return shape's information in SVG format.
   */
  @Override
  public void setDisappearTime(int endTime) {
    this.disappearTime = endTime;
  }

  /**
   * A private helper method to set the visibility of a shape according to its appear/disappear time.
   *
   * @param tempo the given speed
   * @return shape's visibility information in SVG format.
   */
  String getVisibleSVG(double tempo) {
    double begin = (this.appearTime / tempo) * 1000;
    double end = (this.disappearTime / tempo) * 1000;
    double duration = end - begin;
    return " <animate attributeType=\"xml\" attributeName=\"visibility\""
            + " to=\"visible\" begin=\"" + begin
            + "ms\" dur=\""
            + (duration) + "ms" + "\" " + " fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" attributeName=\"visibility\" "
            + "to=\"hidden\" begin=\""
            + end + "ms\" fill=\"freeze\" />";

  }

}
