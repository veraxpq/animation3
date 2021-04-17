package cs5004.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import cs5004.animator.model.Animation;
import cs5004.animator.model.Model;
import cs5004.animator.model.ModelImpl;
import cs5004.animator.model.Shape;
import cs5004.animator.view.PlaybackViewImpl;

public class PlaybackViewController implements AnimationController {
  protected int tick = 0;
  //  private int[] listOfSpeeds = {1, 10, 100, 500, 1000, 10000};
//  private int indexCurrentSpeed = 3;
  protected boolean isLoop = false;
  ActionListener a;
  private Model model;
  private PlaybackViewImpl view;
  private int tempo;
  private long endTime;
  private Timer timer;
  private Model modelCopy;


  public PlaybackViewController(Model model, PlaybackViewImpl view, int tempo) {
    this.model = model;
    this.view = view;
    this.tempo = tempo;
    Map<String, Shape> originalShapeMap = model.getMapOfShapes();
    Map<String, List<Animation>> originalAnimationMap = model.getMapOfAnimations();
    modelCopy = new ModelImpl();

    for (String key : originalShapeMap.keySet()) {
      modelCopy.addShape(originalShapeMap.get(key));
    }
    for (String key : originalAnimationMap.keySet()) {
      List<Animation> animationList = originalAnimationMap.get(key);
      for (Animation animation : animationList) {
        modelCopy.addAnimation(key, animation);
      }
    }

    a = e -> {
      List<Shape> curShape = model.getShapeAtTick(tick);
      view.currentView(curShape);
      tick += 1;
      System.out.println(tick);
      System.out.println(tick > endTime && isLoop);
//      if (tick >= endTime && isLoop) {
      if (tick > endTime) {
        tick = 0;
      }
    };
    timer = new Timer((1000 / tempo), a);

    this.attachActionListener();
  }

  private void resetModel() {
    model = new ModelImpl();
    Map<String, Shape> originalShapeMap = modelCopy.getMapOfShapes();
    Map<String, List<Animation>> originalAnimationMap = modelCopy.getMapOfAnimations();
    for (String key : originalShapeMap.keySet()) {
      model.addShape(originalShapeMap.get(key));
    }
    for (String key : originalAnimationMap.keySet()) {
      List<Animation> animationList = originalAnimationMap.get(key);
      for (Animation animation : animationList) {
        model.addAnimation(key, animation);
      }
    }
  }

  @Override
  public void start() throws InterruptedException {

    Map<String, Shape> shapeMap = model.getMapOfShapes();
    for (String key : shapeMap.keySet()) {
      if (shapeMap.get(key).getDisappearTime() > endTime) {
        endTime = shapeMap.get(key).getDisappearTime();
      }
    }
    a = e -> {
      List<Shape> curShape = model.getShapeAtTick(tick);
      view.currentView(curShape);
      tick += 1;
      System.out.println(tick);
      System.out.println(tick > endTime && isLoop);
      if (tick > endTime && isLoop) {
        tick = 0;
        resetModel();
      }
    };
    timer = new Timer((1000 / tempo), a);
    timer.start();
  }

  private void attachActionListener() {
    ActionListener start = e -> timer.start();
    view.setStart(start);

    ActionListener pause = e -> timer.stop();
    view.setPause(pause);

    ActionListener resume = e -> timer.start();
    view.setResume(resume);

    ActionListener restart = e -> {
      resetModel();
      tick = 0;
      timer.restart();
    };
    view.setRestart(restart);

    ActionListener increaseSpeed = e -> timer.setDelay(1000 / (tempo + 20));
    view.setIncreaseSpeed(increaseSpeed);

    ActionListener decreaseSpeed = e -> {
      int newSpeed = tempo - 20;
      if (newSpeed < 0) {
        newSpeed = 1;
      }
      timer.setDelay(1000 / newSpeed);
    };
    view.setDecreaseSpeed(decreaseSpeed);

    ActionListener loopButton = e -> {
      isLoop = !isLoop;
      System.out.println(isLoop);
    };
    view.setLoopButton(loopButton);
  }
}
