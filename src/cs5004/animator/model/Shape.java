package cs5004.animator.model;


import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * This interface represents a shape.
 */
public interface Shape {

  /**
   * Get the color of the object.
   *
   * @return the color of the object
   */
  Color getColor();

  /**
   * Get the type of the object.
   *
   * @return the type of the object
   */
  NameOfShape getType();

  /**
   * Get the name of the object.
   *
   * @return the name of the object
   */
  String getName();

  /**
   * Get the position of the object.
   *
   * @return the position of the object
   */
  Point2D getPosition();

  /**
   * Get the copied oval object.
   *
   * @return the copied oval object
   */
  Shape copy();

  /**
   * Get the string value of the object.
   *
   * @return the string of the object
   */
  String toString();

  /**
   * This method create new shape with the given point.
   *
   * @param point the given point
   * @return a new shape with the given point
   */
  Shape createNewShape(Point2D point);

  /**
   * This method create a new shape with the given color.
   *
   * @param color the given color
   * @return a new shape with the the given color
   */
  Shape createNewShape(Color color);

  /**
   * This method create a new shape with the given width and height.
   *
   * @param newWidth  the given width
   * @param newHeight the given height
   * @return a new shape with the given width, height or x-radius, y-radius
   */
  Shape createNewShape(double newWidth, double newHeight);

  /**
   * Get the width of the object.
   *
   * @return the width or x- radius of the object
   */
  double getWidth();

  /**
   * Get the height of the object.
   *
   * @return the height or y-radius of the object
   */
  double getHeight();

  /**
   * Get the appear time of the object.
   *
   * @return the appear time of the object
   */
  int getAppearTime();

  /**
   * Get the disappear time of the object.
   *
   * @return the disappear time of the object
   */
  int getDisappearTime();

  /**
   * Check whether the shape is initialized with essential parameters(eg.position, color and so on).
   *
   * @return true if it is initialized otherwise false
   */
  boolean isInitialized();

  /**
   * Get a string information from a shape in SVG format.
   *
   * @param tempo the speed
   * @return shape's information in SVG format.
   */
  String getSVGOfShape(double tempo);

  /**
   * Get a shape's SVG end tag to specify the shape type.
   *
   * @return the end tag of a shape in SVG format.
   */
  String getSVGOfEndTag();

  /**
   * Set the end time of a shape.
   *
   * @param endTime the given end time of a shape
   */
  void setDisappearTime(int endTime);

}
