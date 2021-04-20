package cs5004.animator.view;


import java.util.List;
import cs5004.animator.model.Shape;

/**
 * This interface represents the view.
 */
public interface View {

  /**
   * Displays the shape list into the current view.
   *
   * @param shapeList the given shape list
   */
  void currentView(List<Shape> shapeList);

  /**
   * Get the description of the object.
   *
   * @return the description of the object
   */
  String getDescription();


  /**
   * Get a view's type.
   *
   * @return type of a given view
   */
  String getViewType();

}
