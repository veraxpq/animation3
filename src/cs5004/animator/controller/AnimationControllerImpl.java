package cs5004.animator.controller;

import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.Timer;

import cs5004.animator.model.Animation;
import cs5004.animator.model.Model;
import cs5004.animator.model.ModelImpl;
import cs5004.animator.model.Shape;
import cs5004.animator.view.PlaybackViewImpl;
import cs5004.animator.view.SVGViewImpl;
import cs5004.animator.view.TextBasedView;
import cs5004.animator.view.TextBasedViewImpl;
import cs5004.animator.view.View;

/**
 * This controller class implements the AnimationController interface, it contains attributes to
 * render different view types.
 */
public class AnimationControllerImpl implements AnimationController {
  protected int tick = 0;
  protected boolean isLoop = false;
  ActionListener a;
  private Model model;
  private View view;
  private int tempo;
  private long endTime;
  private Timer timer;
  private Model modelCopy;
  private String outFile;

  /**
   * This constructor initializes the controller with a model, speed, view and a output file name.
   *
   * @param model the animation model.
   * @param view the object of GraphicalView.
   * @param tempo the speed, in ticks per second.
   * @param outFile the output file name.
   */
  public AnimationControllerImpl(Model model, View view, int tempo, String outFile) {
    if (model == null || view == null || tempo <= 0 || outFile == null) {
      throw new IllegalArgumentException("Invalid inputs");
    }
    this.outFile = outFile;
    this.model = model;
    this.view = view;
    this.tempo = tempo;

    Map<String, Shape> shapeMap = model.getMapOfShapes();
    for (String key : shapeMap.keySet()) {
      if (shapeMap.get(key).getDisappearTime() > endTime) {
        endTime = shapeMap.get(key).getDisappearTime();
      }
    }
    a = e -> {
      if (tick < endTime + 1) {
        List<Shape> curShape = model.getShapeAtTick(tick);
        view.currentView(curShape);
        tick += 1;
      }
    };
    timer = new Timer((1000 / tempo), a);
  }


  @Override
  public void start() throws InterruptedException {
    if (view.getViewType().equalsIgnoreCase("visual")) {
      timer.start();

    } else if (view.getViewType().equalsIgnoreCase("text")) {
      TextBasedView textBasedView = new TextBasedViewImpl(model, tempo);
      textBasedView.currentView(outFile);
    } else if (view.getViewType().equalsIgnoreCase("svg")) {
      TextBasedView svgView = new SVGViewImpl(model, tempo);
      svgView.currentView(outFile);
    } else if (view.getViewType().equalsIgnoreCase("playback")) {
      modelCopy = new ModelImpl();
      copyModel(model, modelCopy);

      // attach action listener for buttons
      ActionListener start = e -> timer.start();
      ((PlaybackViewImpl)view).setStart(start);

      ActionListener pause = e -> timer.stop();
      ((PlaybackViewImpl)view).setPause(pause);

      ActionListener resume = e -> timer.start();
      ((PlaybackViewImpl)view).setResume(resume);

      ActionListener restart = e -> {
        resetModel();
        tick = 0;
        timer.restart();
      };
      ((PlaybackViewImpl)view).setRestart(restart);

      ActionListener increaseSpeed = e -> {
        // increase speed by 20 every time the user clicks the increaseSpeed button
        tempo += 20;
        timer.setDelay(1000 / tempo);
      };
      ((PlaybackViewImpl)view).setIncreaseSpeed(increaseSpeed);

      ActionListener decreaseSpeed = e -> {
        // decrease speed by 20 every time the user clicks the decreaseSpeed button
        tempo -= 20;
        if (tempo < 0) {
          tempo = 1;
        }
        timer.setDelay(1000 / tempo);
      };
      ((PlaybackViewImpl)view).setDecreaseSpeed(decreaseSpeed);

      ActionListener loopButton = e -> {
        isLoop = !isLoop;
      };
      ((PlaybackViewImpl)view).setLoopButton(loopButton);


      a = e -> {
        List<Shape> curShape = model.getShapeAtTick(tick);
        view.currentView(curShape);
        tick += 1;
        if (tick > endTime && isLoop) {
          tick = 0;
          resetModel();
        }
      };
      timer = new Timer((1000 / tempo), a);
      timer.start();
    }

  }

  private void copyModel(Model originalModel, Model copiedModel) {
    Map<String, Shape> originalShapeMap = originalModel.getMapOfShapes();
    Map<String, List<Animation>> originalAnimationMap = originalModel.getMapOfAnimations();
//    copiedModel = new ModelImpl();
    for (String key : originalShapeMap.keySet()) {
      copiedModel.addShape(originalShapeMap.get(key));
    }
    for (String key : originalAnimationMap.keySet()) {
      List<Animation> animationList = originalAnimationMap.get(key);
      for (Animation animation : animationList) {
        copiedModel.addAnimation(key, animation);
      }
    }
  }

  /**
   * This is a private helper method to get the initial state of the model.
   */
  private void resetModel() {
    model = new ModelImpl();
    copyModel(modelCopy, model);
//    Map<String, Shape> originalShapeMap = modelCopy.getMapOfShapes();
//    Map<String, List<Animation>> originalAnimationMap = modelCopy.getMapOfAnimations();
//    for (String key : originalShapeMap.keySet()) {
//      model.addShape(originalShapeMap.get(key));
//    }
//    for (String key : originalAnimationMap.keySet()) {
//      List<Animation> animationList = originalAnimationMap.get(key);
//      for (Animation animation : animationList) {
//        model.addAnimation(key, animation);
//      }
//    }
  }





}

