package cs5004.animator.view;

/**
 * This class represents the TextBasedView object.
 */
public interface TextBasedView extends View {

  /**
   * This method displays the description.
   *
   * @param description the given description
   */
  void currentView(String description);

  /**
   * Get the description of the view.
   *
   * @return the description of the view
   */
  String getDescription();

}
