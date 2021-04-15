package cs5004.animator.model;

/**
 * This class represents an abstract class for Animator interface.
 */
public abstract class AbstractAnimation implements Animation {
  int startTime;
  int endTime;


  /**
   * Constructs an AbstractAnimator object and initializes it with the given start time and end
   * time.
   *
   * @param startTime the given start time
   * @param endTime   the given end time
   */
  public AbstractAnimation(int startTime, int endTime) {
    if (startTime < 0 || endTime < 0) {
      throw new IllegalArgumentException("the time cannot be negative");
    }
    if (startTime > endTime) {
      throw new IllegalArgumentException("start time is later than end time");
    }
    this.startTime = startTime;
    this.endTime = endTime;
  }



  /**
   * This method plays the animator with the given shape, and return the shape of the given time.
   *
   * @param shape the given shape
   * @param currentTime the given time
   * @return the shape of the given time
   * @throws IllegalArgumentException when the shape is null or the current is negative
   */
  @Override
  public Shape play(Shape shape, int currentTime) {
    if (shape == null) {
      return null;
    }
    if (currentTime < 0) {
      throw new IllegalArgumentException("please input a non negative time");
    }
    return null;
  }

  /**
   * Get the start time of the animator.
   *
   * @return the start time of the animator
   */
  @Override
  public int getStartTime() {
    return startTime;
  }

  /**
   * Get the end time of the animator.
   *
   * @return the end time of the animator
   */
  @Override
  public int getEndTime() {
    return endTime;
  }


  /**
   * Calculate and get the intermediate value at the current time.
   *
   * @param t1 start time
   * @param t2 end time
   * @param t  current time
   * @param a  start value
   * @param b  end value
   * @return the intermediate value at the current time
   * @throws IllegalArgumentException if t1 or t2 or t is less than 0
   */
  double getIntermediate(double t1, double t2, double t, double a, double b) {
    if (t < 0 || t1 < 0 || t2 < 0) {
      throw new IllegalArgumentException("time should be positive value");
    }
    return a * (t2 - t) / (t2 - t1) + b * (t - t1) / (t2 - t1);
  }

}
