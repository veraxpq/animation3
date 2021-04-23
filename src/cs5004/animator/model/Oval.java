package cs5004.animator.model;


import java.awt.Color;
import java.awt.geom.Point2D;


/**
 * This class represents an Oval object.
 */
public class Oval extends AbstractShape {
  private double xRadius;
  private double yRadius;

  /**
   * Construct a oval object and initializes it with the given parameters.
   *
   * @param position      the given position
   * @param color         the given color
   * @param type          the given type
   * @param name          the given name
   * @param appearTime    the given appear time
   * @param disappearTime the given disappear time
   * @throws IllegalArgumentException when the position is null, the color is null, the width or
   *                                  height is negative, the type is null, the name is null or
   *                                  length = 0,  the appear time is negative and the disappear
   *                                  time is earlier than the appear time
   */
  public Oval(Point2D position, Color color, double xRadius, double yRadius, NameOfShape type,
              String name, int appearTime, int disappearTime) {
    super(position, color, type, name, appearTime, disappearTime);
    if (xRadius < 0 || yRadius < 0) {
      throw new IllegalArgumentException("the x or y radius is illegal");
    }
    this.xRadius = xRadius;
    this.yRadius = yRadius;
    this.type = NameOfShape.C;
  }

  /**
   * Construct the Oval object with the given type, name.
   *
   * @param type          the given type
   * @param name          the given name
   * @throws IllegalArgumentException when the type is null, the name is null
   */
  public Oval(String name, String type) {
    super(name, type, false);

  }


  /**
   * Get the copied oval object.
   *
   * @return the copied oval object
   */
  @Override
  public Shape copy() {
    return new Oval(position, color, xRadius, yRadius, type, name, appearTime, disappearTime);
  }

  /**
   * Get the x radius of the object.
   *
   * @return the x radius of the object
   */
  @Override
  public double getWidth() {
    return xRadius;
  }

  /**
   * Get the y radius of the object.
   *
   * @return the y radius of the object
   */
  @Override
  public double getHeight() {
    return yRadius;
  }

  /**
   * Get the string value of the object.
   *
   * @return the string of the object
   */
  @Override
  public String toString() {
    return "Name: " + getName() + "\n"
            + "Type: " + getType() + "\n"
            + "Min Corner: (" + position.getX() + ", " + position.getY() + "), X Radius: "
            + String.format("%.1f", xRadius) + ", Y Radius: " + String.format("%.1f", yRadius)
            + ", Color: (" + (float) (color.getRed() / 255) + ", "
            + (float) (color.getGreen() / 255)
            + ", " + (float) (color.getBlue() / 255) + ")" + "\nAppears at t=" + appearTime
            + "\nDisappears at t=" + disappearTime;
  }

  /**
   * This method create new shape with the given point.
   *
   * @param newPoint the given point
   * @return the new shape
   */
  @Override
  public Shape createNewShape(Point2D newPoint) {
    return new Oval(newPoint, color, xRadius, yRadius, type, name, appearTime, disappearTime);
  }

  /**
   * This method create a new shape with the given color.
   *
   * @param newColor the given color
   * @return the new shape
   */
  @Override
  public Shape createNewShape(Color newColor) {
    return new Oval(position, newColor, xRadius, yRadius, type, name, appearTime, disappearTime);
  }

  /**
   * This method create a new shape with the given width and height.
   *
   * @param newWidth the given width
   * @param newHeight the given height
   * @return the new shape
   */
  @Override
  public Shape createNewShape(double newWidth, double newHeight) {
    return new Oval(position, color, newWidth, newHeight, type, name, appearTime, disappearTime);
  }

  @Override
  public String getSVGOfShape(double tempo) {
    double newX = this.getPosition().getX() + this.getWidth() / 2 ;
    double newY = this.getPosition().getY() + this.getHeight() / 2 ;
    return "<ellipse id=\"" + this.getName() + "\" cx=\"" + newX + "\" cy=\""
            + newY + "\" rx=\"" + this.getWidth() / 2 + "\" ry=\""
            + this.getHeight() / 2 + "\" fill=\"rgb" + "(" + color.getRed() + ","
            + color.getGreen() + "," + color.getBlue() + ")" + "\" visibility=\"hidden\">\n"
            + getVisibleSVG(tempo);

  }


  @Override
  public String getSVGOfEndTag() {
    return "</ellipse>";
  }

}
