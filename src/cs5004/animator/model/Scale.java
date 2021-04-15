package cs5004.animator.model;

/**
 * This class represents an AbstractAnimation object.
 */
public class Scale extends AbstractAnimation {

  private double oldWidth;
  private double oldHeight;
  private double newWidth;
  private double newHeight;
  private NameOfShape type;


  /**
   * Construct a move object and initializes it with the given parameters.
   *
   * @param startTime the given start time
   * @param endTime   the given end time
   * @param oldWidth  the old width
   * @param oldHeight the old height
   * @param newWidth  the given width
   * @param newHeight the given height
   */
  public Scale(int startTime, int endTime, double oldWidth, double oldHeight,
               double newWidth, double newHeight, NameOfShape type) {
    super(startTime, endTime);

    if (newWidth < 0 || newHeight < 0) {
      throw new IllegalArgumentException("the input width and height cannot be negative");
    }
    this.oldWidth = oldWidth;
    this.oldHeight = oldHeight;
    this.newWidth = newWidth;
    this.newHeight = newHeight;
    this.type = type;
  }

  /**
   * This method play the animation with the given shape, and return the shape of the given time.
   *
   * @param shape       the given shape
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
      return shape.createNewShape(newWidth, newHeight);
    } else {
      double intermediateWidth = getIntermediate(startTime, endTime, currentTime,
              oldWidth, newWidth);
      double intermediateHeight = getIntermediate(startTime, endTime, currentTime,
              oldHeight, newHeight);
      return shape.createNewShape(intermediateWidth, intermediateHeight);
    }
  }

  /**
   * Get the string value of the object.
   *
   * @return the string of the object
   */
  public String toString() {
    String str = "";
    String str1 = " from t=" + startTime + " to t=" + endTime;
    if (type == NameOfShape.R) {
      str += " scales from Width:" + String.format("%.1f", oldWidth) + ", Height:"
              + String.format("%.1f", oldHeight) + " to Width:"
              + String.format("%.1f", newWidth) + ", Height: " + String.format("%.1f", newHeight)
              + str1;
    } else if (type == NameOfShape.C) {
      str += " scales from X radius: " + String.format("%.1f", oldWidth) + ", Y radius:"
              + String.format("%.1f", oldHeight) + " to X radius:"
              + String.format("%.1f", newWidth) + ", Y radius: "
              + String.format("%.1f", newHeight) + str1;
    }
    return str;
  }

  @Override
  public String getSVGOfAnimation(double speed) {
    double begin = (this.getStartTime() / speed) * 1000;
    double end = (this.getEndTime() / speed) * 1000;
    double dur = end - begin;

    String str = "";

    String str1 = " <animate attributeType=\"xml\" begin=\""
             + begin + "ms\" dur=\"" + dur + "ms\" attributeName=\"";

    String str2 = "\" " + "from=\"" + this.oldWidth
            + "\" to=\"" + this.newWidth + "\" fill=\"freeze\" /> \n";

    String str3 = " <animate attributeType=\"xml\" begin=\""
            + begin + "ms\" dur=\"" + dur + "ms\" attributeName=\"";

    String str4 = "\" " + "from=\"" + this.oldHeight
            + "\" to=\"" + this.newHeight + "\" fill=\"freeze\" />\n";


    if (type == NameOfShape.R) {
      str += str1 + "width" + str2 + str3 + "height" + str4;
    }
    else if (type == NameOfShape.C) {
      str += str1 + "rx" + str2 + str3 + "ry" + str4;
    }
    return str;
  }



}
