package cs5004.animator.view;

import java.util.List;
import java.util.Map;

import cs5004.animator.model.Animation;
import cs5004.animator.model.Model;
import cs5004.animator.model.Shape;

/**
 * This class represents the svg view.
 */
public class SVGViewImpl extends TextBasedViewImpl {
  private final Model model;
  private double speed;
  private boolean isLoop = true;

  /**
   * Constructs the SVGViewImpl object with the given model and speed.
   *
   * @param model the given model
   * @param speed the given speed
   */
  public SVGViewImpl(Model model, double speed) {
    super(model, speed);
    this.model = model;
    this.speed = speed;
  }

  /**
   * Get the description of the view.
   *
   * @return the description of the view
   */
  @Override
  public String getDescription() {
    Map<String, Shape> mapOfShapes = model.getMapOfShapes();
    Map<String, List<Animation>> mapOfAnimations = model.getMapOfAnimations();
    int endTime = 0;

    // Setting width and height of the canvas
    int canvasWidth = model.getCanvasX() + model.getCanvasWidth();
    int canvasHeight = model.getCanvasY() + model.getCanvasHeight();
    String str = "";

    str += "<svg width=\"" + model.getCanvasWidth() + "\" height=\""
            + model.getCanvasHeight() + "\" viewBox=\"" + model.getCanvasX() + " "
            + model.getCanvasY() + " " + canvasWidth + " " + canvasHeight
            + "\" version=\"1.1\"\n"
            + "xmlns=\"http://www.w3.org/2000/svg\">\n";

//    boolean flag = true;
//
//    for (String key : mapOfAnimations.keySet()) {
//      List<Animation> animationList = mapOfAnimations.get(key);
//      for (Animation animation : animationList) {
//        endTime = Math.max(endTime, animation.getEndTime());
//      }
//    }
    for (String key : mapOfShapes.keySet()) {
      Shape currentShape = mapOfShapes.get(key);
//      if (flag && isLoop) {
//        str += currentShape.getLoopSVGOfShape(endTime);
//        flag = false;
//      }
      str += currentShape.getSVGOfShape(speed);
      List<Animation> animationList = mapOfAnimations.get(key);
      for (Animation animation : animationList) {
        str += animation.getSVGOfAnimation(speed);
        //str += animation.getSVGOfLoop(speed);
      }
      str += currentShape.getSVGOfEndTag() + "\n";
    }
    str += "</svg>";

    return str;

  }

}





