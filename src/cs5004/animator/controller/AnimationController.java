package cs5004.animator.controller;


/**
 * This interface represents an AnimationController object. This interface contains the start
 * method which will execute a different process depending on the view type in order to properly
 * display the animation.
 */
public interface AnimationController {

  /**
   * Method to start the animation. Generating different view according to the view's type.
   *
   * @throws InterruptedException if the program is interrupted externally
   */
  void start() throws InterruptedException;
}
