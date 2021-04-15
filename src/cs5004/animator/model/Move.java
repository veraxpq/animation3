package cs5004.animator.model;

import java.awt.geom.Point2D;

/**
 * This class represents a Move class.
 */
public class Move extends AbstractAnimation {

  private final Point2D startPoint;
  private final Point2D endPoint;
  private NameOfShape type;

  /**
   * Construct a move object and initializes it with the given parameters.
   *
   * @param startTime the given start time
   * @param endTime the given end time
   * @param startPoint the given start point
   * @param endPoint the given end point
   * @throws IllegalArgumentException when the start time is negative, or start time is later than
   *                                  end time or the given point is null
   */
  public Move(int startTime, int endTime, Point2D startPoint, Point2D endPoint, NameOfShape type) {
    super(startTime, endTime);
    if (startPoint == null || endPoint == null) {
      throw new IllegalArgumentException("the input point is null");
    }
    this.startPoint = startPoint;
    this.endPoint = endPoint;
    this.type = type;
  }


  /**
   * This method play the animation with the given shape, and return the shape of the given time.
   *
   * @param shape the given shape
   * @param currentTime the given time
   * @return the shape of the given time
   * @throws IllegalArgumentException if the given current time is negative or the shape is null
   */
  @Override
  public Shape play(Shape shape, int currentTime) {
    super.play(shape, currentTime);
    if (currentTime <= startTime) {
      return null;
    } else if (currentTime >= endTime) {
      return shape.createNewShape(endPoint);
    } else {
      double startX = startPoint.getX();
      double startY = startPoint.getY();
      double endX = endPoint.getX();
      double endY = endPoint.getY();
      double intermediateX = getIntermediate(startTime, endTime, currentTime, startX, endX);
      double intermediateY = getIntermediate(startTime, endTime, currentTime, startY, endY);
      Point2D intermediatePoint = new Point2D.Double(intermediateX, intermediateY);
      return shape.createNewShape(intermediatePoint);
    }
  }

  /**
   * Get the string value of the object.
   *
   * @return the string of the object
   */
  @Override
  public String toString() {
    return " moves from (" + startPoint.getX() + ", " + startPoint.getY() + ")"
            + " to (" + endPoint.getX() + ", " + endPoint.getY() + ")" + " from t=" + startTime
            + " to t=" + endTime;
  }

  @Override
  public String getSVGOfAnimation(double speed) {
    String str = "";
    double begin = (this.getStartTime() / speed) * 1000;
    double end = (this.getEndTime() / speed) * 1000;
    double duration = end - begin;

    String str1 = " <animate attributeType=\"xml\" begin=\"" + begin + "ms\" dur=\""
            + duration + "ms\" attributeName=\"";

    String str2 = "\" " + "from=\"" + this.startPoint.getX()  + "\" to=\""
            + this.endPoint.getX() + "\" fill=\"freeze\" />\n";

    String str3 = " <animate attributeType=\"xml\" begin=\"" + begin + "ms\" dur=\""
            + duration+ "ms\" attributeName=\"";

    String str4 = "\" " + "from=\"" + this.startPoint.getY() + "\" to=\""
            + this.endPoint.getY() + "\" fill=\"freeze\" />\n";

    if (type == NameOfShape.R) {
      str += str1 + "x" + str2 + str3 + "y" + str4;
    }
    else if (type == NameOfShape.C) {
      str += str1 + "cx" + str2 + str3 + "cy" + str4;
    }

    return str;

  }


}
