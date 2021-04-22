package cs5004.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D;


/**
 * This class represents the Rectangle object.
 */
public class Rectangle extends AbstractShape {
  private double width;
  private double height;

  /**
   * Construct the Rectangle object with the given position, color, type, name, appear time and
   * disappear time.
   *
   * @param position      the given position
   * @param color         the given color
   * @param width         the given width
   * @param height        the given height
   * @param type          the given type
   * @param name          the given name
   * @param appearTime    the given appear time
   * @param disappearTime the given disappear time
   * @throws IllegalArgumentException when the position is null, the color is null, the width or
   *                                                               height is negative, the type is
   *                                  null, the name is null or
   *                                  length = 0,  the appear time is negative and the disappear
   *                                                               time is earlier than the appear
   *                                  time
   */
  public Rectangle(Point2D position, Color color, double width, double height, NameOfShape type,
                   String name, int appearTime, int disappearTime) {
    super(position, color, type, name, appearTime, disappearTime);
    if (height < 0 || width < 0) {
      throw new IllegalArgumentException("the given height or width cannot be negative");
    }
    this.height = height;
    this.width = width;
    this.type = NameOfShape.R;
  }


  /**
   * Construct the Rectangle object with the given type, name.
   *
   * @param type          the given type
   * @param name          the given name
   * @throws IllegalArgumentException when the type is null, the name is null
   */
  public Rectangle(String name, String type) {
    super(name, type, false);

  }



  /**
   * This method create new shape with the given point.
   *
   * @param point the given point
   * @return the new shape
   */
  @Override
  public Shape createNewShape(Point2D point) {
    return new Rectangle(point, color, width, height, type, name, appearTime, disappearTime);
  }

  /**
   * This method create a new shape with the given color.
   *
   * @param color the given color
   * @return the new rectangle
   */
  @Override
  public Shape createNewShape(Color color) {
    return new Rectangle(position, color, width, height, type, name, appearTime, disappearTime);
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
    return new Rectangle(position, color, newWidth, newHeight, type, name,
            appearTime, disappearTime);
  }

  /**
   * This method copy and returns a new object.
   *
   * @return a copied object
   */
  @Override
  public Shape copy() {
    return new Rectangle(position, color, width, height, type, name, appearTime, disappearTime);
  }

  /**
   * Get the height of the object.
   *
   * @return the height of the object
   */
  @Override
  public double getHeight() {
    return height;
  }

  /**
   * Get the width of the object.
   *
   * @return the width of the object
   */
  @Override
  public double getWidth() {
    return width;
  }

  /**
   * Get the string of the object.
   *
   * @return the string of the object
   */
  @Override
  public String toString() {
    return "Name: " + getName() + "\n"
            + "Type: " + getType() + "\n"
            + "Min Corner: (" + position.getX() + ", " + position.getY() + "), Width: "
            + String.format("%.1f", width) + ", Height: " + String.format("%.1f", height)
            + ", Color: (" + (float) (color.getRed() / 255) + ", "
            + (float) (color.getGreen() / 255) + ", "
            + (float) (color.getBlue() / 255) + ")" + "\nAppears at t=" + appearTime
            + "\nDisappears at t=" + disappearTime;
  }

  @Override
  public String getSVGOfShape(double tempo) {
    return "<rect id=\"" + this.getName() + "\" x=\"" + this.getPosition().getX() + "\" y=\""
            + this.getPosition().getY() + "\" width=\"" + this.getWidth() + "\" height=\""
            + this.getHeight() + "\" fill=\"rgb" + "(" + color.getRed() + ","
            + color.getGreen() + "," + color.getBlue() + ")"
            + "\" visibility=\"hidden\">\n"
            + getVisibleSVG(tempo);
  }

  @Override
  public String getSVGOfEndTag() {
    return "</rect>";
  }

}
