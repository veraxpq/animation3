import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import cs5004.animator.controller.AnimationController;
import cs5004.animator.controller.PlaybackViewController;
import cs5004.animator.model.Animation;
import cs5004.animator.model.ChangeColor;
import cs5004.animator.model.Model;
import cs5004.animator.model.ModelImpl;
import cs5004.animator.model.Move;
import cs5004.animator.model.NameOfShape;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Scale;
import cs5004.animator.model.Shape;
import cs5004.animator.view.PlaybackViewImpl;
import cs5004.animator.view.View;

import static org.junit.Assert.*;

public class PlaybackViewControllerTest {
  private AnimationController controller;

  @Before
  public void setUp() throws Exception {
    Model model = new ModelImpl();
    Shape r = new Rectangle(new Point2D.Double(0, 0),
            Color.black, 3, 4, NameOfShape.R, "R", 1, 10);
    Shape c = new Oval(new Point2D.Double(2.0, 4.0), Color.red, 3, 4,
            NameOfShape.C, "C", 1, 200);
    model.addShape(r);
    model.addShape(c);
    Animation a1 = new Move(0, 3, new Point2D.Double(0, 0),
            new Point2D.Double(6.0, 9.0), NameOfShape.R);
    Animation a2 = new ChangeColor(5, 10, Color.black, Color.blue);
    Animation a3 = new Scale(1, 100, r.getWidth(), r.getHeight(), 1,
            10, r.getType());
    model.addAnimation(r.getName(), a1);
    model.addAnimation(r.getName(), a2);
    model.addAnimation(c.getName(), a3);
    PlaybackViewImpl view = new PlaybackViewImpl(200, 70, 360, 360);
    this.controller = new PlaybackViewController(model, view, 50);
  }

  @Test
  public void start() throws InterruptedException {

  }
}