import org.junit.Before;
import org.junit.Test;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;


import cs5004.animator.EasyAnimator;
import cs5004.animator.controller.AnimationController;
import cs5004.animator.controller.AnimationControllerImpl;
import cs5004.animator.model.Model;
import cs5004.animator.model.ModelImpl;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.SVGViewImpl;
import cs5004.animator.view.View;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the methods in the AnimationControllerImpl class.
 */
public class AnimationControllerImplTest {
  AnimationController c;
  Model m1;
  View v1;

  @Before
  public void setUp() throws FileNotFoundException {
    String filename1 = "smalldemo.txt";
    Readable readable = new FileReader(filename1);
    AnimationBuilder<Model> builder = new ModelImpl.Builder();
    m1 = AnimationReader.parseFile(readable, builder);
    v1 = new SVGViewImpl(m1, 1);
    String out = "";
    int speed = 1;
    c = new AnimationControllerImpl(m1, v1, speed, out);

  }

  @Test (expected = IllegalArgumentException.class)
  public void testBadConstructor1() {
    c = new AnimationControllerImpl(null, v1, 1, "out.txt");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBadConstructor2() {
    c = new AnimationControllerImpl(m1, null, 100, "out.svg");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBadConstructor3() {
    c = new AnimationControllerImpl(m1, v1, 0, "");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBadConstructor4() {
    c = new AnimationControllerImpl(m1, v1, 1, null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBadConstructor5() {
    c = new AnimationControllerImpl(null, null, -2, null);
  }


  @Test
  public void start() {



  }
}