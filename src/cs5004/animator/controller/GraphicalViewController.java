//package cs5004.animator.controller;
//
//import java.awt.event.ActionListener;
//import java.util.List;
//import java.util.Map;
//
//import javax.swing.Timer;
//
//
//import cs5004.animator.model.Model;
//import cs5004.animator.model.Shape;
//import cs5004.animator.view.GraphicalView;
//
///**
// * This controller class implements the AnimationController interface, it contains attributes to
// * render the visual view, such as time and model.
// */
//public class GraphicalViewController implements AnimationController {
//  private Model model;
//  private GraphicalView view;
//  private int tempo;
//  private long endTime;
//  private int tick = 0;
//  ActionListener a;
//
//  /**
//   * This constructor initializes the controller with a model, speed, and a view.
//   *
//   * @param model the animation model.
//   * @param view the object of GraphicalView.
//   * @param tempo the speed, in ticks per second.
//   */
//  public GraphicalViewController(Model model, GraphicalView view, int tempo) {
//    this.model = model;
//    this.view = view;
//    this.tempo = tempo;
//  }
//
//  @Override
//  public void start() throws InterruptedException {
//
//    Map<String, Shape> shapeMap = model.getMapOfShapes();
//    for (String key : shapeMap.keySet()) {
//      if (shapeMap.get(key).getDisappearTime() > endTime) {
//        endTime = shapeMap.get(key).getDisappearTime();
//      }
//    }
//    a = e -> {
//      if (tick < endTime + 1) {
//        List<Shape> curShape = model.getShapeAtTick(tick);
//        view.currentView(curShape);
//        tick += 1;
//      }
//    };
//    Timer timer = new Timer((1000 / tempo), a);
//    timer.start();
//
//
//  }
//
//
//}
