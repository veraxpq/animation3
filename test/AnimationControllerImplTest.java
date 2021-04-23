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
import cs5004.animator.view.TextBasedViewImpl;
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
  public void setUp() throws FileNotFoundException, InterruptedException {
    String filename1 = "smalldemo.txt";
    Readable readable = new FileReader(filename1);
    AnimationBuilder<Model> builder = new ModelImpl.Builder();
    m1 = AnimationReader.parseFile(readable, builder);
    v1 = new TextBasedViewImpl(m1, 1);
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
  public void start() throws InterruptedException, FileNotFoundException {
    // test start() by using EasyAnimator
    String str = "-in smalldemo.txt -view text -out text-transcript.txt";
    String[] args = str.split(" ");
    EasyAnimator.main(args);

    BufferedReader bufferedReader = null;
    try {
      bufferedReader = new BufferedReader(new FileReader("text-transcript.txt"));
      StringBuilder stringBuilder = new StringBuilder();
      String line = bufferedReader.readLine();

      while (line != null) {
        stringBuilder.append(line);
        stringBuilder.append("\n");
        line = bufferedReader.readLine();
      }
      assertEquals(v1.getDescription() + "\n", stringBuilder.toString());
      assertEquals("Shapes:\n"
              + "Name: R\n"
              + "Type: rectangle\n"
              + "Min Corner: (200.0, 200.0), Width: 50.0, Height: 100.0, Color: (1.0, 0.0, 0.0)\n"
              + "Appears at t=1\n"
              + "Disappears at t=100\n"
              + "\n"
              + "Name: C\n"
              + "Type: oval\n"
              + "Min Corner: (440.0, 70.0), X Radius: 120.0, Y Radius: 60.0, Color: "
              + "(0.0, 0.0, 1.0)\n"
              + "Appears at t=6\n"
              + "Disappears at t=100\n"
              + "\n"
              + "Shape R moves from (200.0, 200.0) to (300.0, 300.0) from t=10 to t=50\n"
              + "Shape R moves from (300.0, 300.0) to (300.0, 300.0) from t=50 to t=51\n"
              + "Shape R scales from Width:50.0, Height:100.0 to Width:25.0, Height: 100.0 "
              + "from t=51 to t=70\n"
              + "Shape R moves from (300.0, 300.0) to (200.0, 200.0) from t=70 to t=100\n"
              + "Shape C moves from (440.0, 70.0) to (440.0, 250.0) from t=20 to t=50\n"
              + "Shape C moves from (440.0, 250.0) to (440.0, 370.0) from t=50 to t=70\n"
              + "Shape C changes color from (0.0,0.0,1.0) to (0.0,0.7,0.3) from t=50 to t=70\n"
              + "Shape C changes color from (0.0,0.7,0.3) to (0.0,1.0,0.0) from t=70 to t=80\n"
              + "Shape C moves from (440.0, 370.0) to (440.0, 370.0) from t=80 to t=100\n",
          stringBuilder.toString());
      bufferedReader.close();
    } catch (Exception e) {
      // do nothing
    }


  }
}