package cs5004.animator.model;

/**
 * This interface represents an Animation object.
 */
public interface Animation {

  /**
   * This method play the animation with the given shape, and return the shape of the given time.
   *
   * @param shape the given shape
   * @param currentTime the given time
   * @return the shape of the given time
   * @throws IllegalArgumentException if the given current time is negative
   */
  Shape play(Shape shape, int currentTime);

  /**
   * Get the start time of the animation.
   *
   * @return the start time of the animation
   */
  int getStartTime();

  /**
   * Get the end time of the animation.
   *
   * @return the end time of the animation
   */
  int getEndTime();

  /**
   * Get the string value of the object.
   *
   * @return the string of the object
   */
  String toString();

  /**
   * Method to return a string with the information about the shape's motion in SVG format.
   *
   * @param speed the ticks per second speed.
   * @return a string of SVG format about the shape's motion.
   */
  String getSVGOfAnimation(double speed);




}
