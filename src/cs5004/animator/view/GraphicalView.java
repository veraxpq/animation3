package cs5004.animator.view;


import java.util.List;
import cs5004.animator.model.Shape;


/**
 * This interface represents a GraphicalView.
 */
public interface GraphicalView extends View {

  /**
   * Get the state of the view. Display the animations.
   *
   */
  void currentView(List<Shape> shapeList);

}
