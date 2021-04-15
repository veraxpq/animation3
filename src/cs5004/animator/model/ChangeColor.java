package cs5004.animator.model;

import java.awt.Color;

/**
 * This class represents a change color animation.
 */
public class ChangeColor extends AbstractAnimation {

  private double oldR;
  private double oldG;
  private double oldB;
  private double newR;
  private double newG;
  private double newB;

  /**
   * Construct the ChangeColor object with the given start time, end time, shape and color.
   *
   * @param startTime the given start time
   * @param endTime   the given end time
   * @param oldColor  the given old color
   * @param newColor  the given new color
   * @throws IllegalArgumentException when the input shape or color is null, the start ime is
   *                                  negative, and end time is earlier than start time
   */
  public ChangeColor(int startTime, int endTime, Color oldColor, Color newColor) {
    super(startTime, endTime);
    if (newColor == null || oldColor == null) {
      throw new IllegalArgumentException("the input color is null");
    }
    this.oldR = oldColor.getRed();
    this.oldG = oldColor.getGreen();
    this.oldB = oldColor.getBlue();
    this.newR = newColor.getRed();
    this.newG = newColor.getGreen();
    this.newB = newColor.getBlue();

  }

  /**
   * This method plays the animation with the given shape, and return the shape of the given time.
   *
   * @param shape       the given shape
   * @param currentTime the given time
   * @return the shape of the given time
   * @throws IllegalArgumentException when the shape is null or the current is negative
   */
  @Override
  public Shape play(Shape shape, int currentTime) {
    super.play(shape, currentTime);
    if (currentTime <= startTime) {
      return null;
    } else if (currentTime >= endTime) {
      // freeze the canvas at the end
      return shape.createNewShape(shape.getColor());
    } else {
      double intermediateR = getIntermediate(startTime, endTime, currentTime, oldR, newR) / 255;
      double intermediateG = getIntermediate(startTime, endTime, currentTime, oldG, newG) / 255;
      double intermediateB = getIntermediate(startTime, endTime, currentTime, oldB, newB) / 255;
      Color newColor = new Color((float) intermediateR, (float) intermediateG,
              (float) intermediateB);

      return shape.createNewShape(newColor);
    }
  }

  /**
   * Get the string value of the object.
   *
   * @return the string of the object
   */
  @Override
  public String toString() {
    return " changes color from (" + String.format("%.1f", oldR / 255) + ","
            + String.format("%.1f", oldG / 255) + ","
            + String.format("%.1f", oldB / 255) + ")" + " to ("
            + String.format("%.1f", newR / 255) + ","
            + String.format("%.1f", newG / 255) + ","
            + String.format("%.1f", newB / 255) + ")"
            + " from t=" + startTime + " to t=" + endTime;

  }

  @Override
  public String getSVGOfAnimation(double speed) {
    double begin = (this.getStartTime() / speed) * 1000;
    double end = (this.getEndTime() / speed) * 1000;
    double duration = end - begin;

    return " <animate attributeType=\"CSS\" begin=\"" + begin + "ms\" dur=\""
            + duration + "ms\" attributeName=\"fill\" from=\"rgb"
            + "(" + oldR + "," + oldG + "," + oldB + ")" + "\" to=\"rgb"
            + "(" + newR + "," + newG + "," + newB + ")" + "\" fill=\"freeze\" />\n";
  }




}
