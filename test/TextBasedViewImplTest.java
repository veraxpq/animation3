import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import cs5004.animator.EasyAnimator;
import cs5004.animator.model.Model;
import cs5004.animator.model.ModelImpl;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.TextBasedView;
import cs5004.animator.view.TextBasedViewImpl;


import static org.junit.Assert.assertEquals;

/**
 * This class tests the methods in the TextBasedViewImpl class.
 */
public class TextBasedViewImplTest {
  TextBasedView v1;

  @Before
  public void setUp() throws FileNotFoundException {
    String filename1 = "toh-3.txt";
    String inFile = System.getProperty("user.dir") + "/src/starterCode/" + filename1;
    Readable readable = new FileReader(inFile);
    AnimationBuilder<Model> builder = new ModelImpl.Builder();
    Model model = AnimationReader.parseFile(readable, builder);
    v1 = new TextBasedViewImpl(model, 1);
  }

  @Test
  public void testCurrentView() throws FileNotFoundException, InterruptedException {
    String str = "-in toh-3.txt -view text -out text-transcript.txt";
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
                      + "Name: disk1\n"
                      + "Type: rectangle\n"
                      + "Min Corner: (190.0, 180.0), Width: 20.0, Height: 30.0, "
                      + "Color: (0.0, 0.0, 0.0)\n"
                      + "Appears at t=1\n"
                      + "Disappears at t=302\n"
                      + "\n"
                      + "Name: disk2\n"
                      + "Type: rectangle\n"
                      + "Min Corner: (167.0, 210.0), Width: 65.0, Height: 30.0, "
                      + "Color: (0.0, 0.0, 0.0)\n"
                      + "Appears at t=1\n"
                      + "Disappears at t=302\n"
                      + "\n"
                      + "Name: disk3\n"
                      + "Type: rectangle\n"
                      + "Min Corner: (145.0, 240.0), Width: 110.0, "
                      + "Height: 30.0, Color: (0.0, 0.0, 0.0)\n"
                      + "Appears at t=1\n"
                      + "Disappears at t=302\n"
                      + "\n"
                      + "Shape disk1 moves from (190.0, 180.0) to (190.0, 180.0) "
                      + "from t=1 to t=25\n"
                      + "Shape disk1 moves from (190.0, 180.0) to (190.0, 50.0) "
                      + "from t=25 to t=35\n"
                      + "Shape disk1 moves from (190.0, 50.0) to (190.0, 50.0) "
                      + "from t=35 to t=36\n"
                      + "Shape disk1 moves from (190.0, 50.0) to (490.0, 50.0) "
                      + "from t=36 to t=46\n"
                      + "Shape disk1 moves from (490.0, 50.0) to (490.0, 50.0) from t=46 "
                      + "to t=47\n"
                      + "Shape disk1 moves from (490.0, 50.0) to (490.0, 240.0) "
                      + "from t=47 to t=57\n"
                      + "Shape disk1 moves from (490.0, 240.0) to (490.0, 240.0) "
                      + "from t=57 to t=89\n"
                      + "Shape disk1 moves from (490.0, 240.0) to (490.0, 50.0) from "
                      + "t=89 to t=99\n"
                      + "Shape disk1 moves from (490.0, 50.0) to (490.0, 50.0) from t=99 to "
                      + "t=100\n"
                      + "Shape disk1 moves from (490.0, 50.0) to (340.0, 50.0) "
                      + "from t=100 to t=110\n"
                      + "Shape disk1 moves from (340.0, 50.0) to (340.0, 50.0) "
                      + "from t=110 to t=111\n"
                      + "Shape disk1 moves from (340.0, 50.0) to (340.0, 210.0) "
                      + "from t=111 to t=121\n"
                      + "Shape disk1 moves from (340.0, 210.0) to (340.0, 210.0) "
                      + "from t=121 to t=153\n"
                      + "Shape disk1 moves from (340.0, 210.0) to (340.0, 50.0) "
                      + "from t=153 to t=163\n"
                      + "Shape disk1 moves from (340.0, 50.0) to (340.0, 50.0) from "
                      + "t=163 to t=164\n"
                      + "Shape disk1 moves from (340.0, 50.0) to (190.0, 50.0) from "
                      + "t=164 to t=174\n"
                      + "Shape disk1 moves from (190.0, 50.0) to (190.0, 50.0) from "
                      + "t=174 to t=175\n"
                      + "Shape disk1 moves from (190.0, 50.0) to (190.0, 240.0) "
                      + "from t=175 to t=185\n"
                      + "Shape disk1 moves from (190.0, 240.0) to (190.0, 240.0) "
                      + "from t=185 to t=217\n"
                      + "Shape disk1 moves from (190.0, 240.0) to (190.0, 50.0) "
                      + "from t=217 to t=227\n"
                      + "Shape disk1 moves from (190.0, 50.0) to (190.0, 50.0) "
                      + "from t=227 to t=228\n"
                      + "Shape disk1 moves from (190.0, 50.0) to (490.0, 50.0) from "
                      + "t=228 to t=238\n"
                      + "Shape disk1 moves from (490.0, 50.0) to (490.0, 50.0) "
                      + "from t=238 to t=239\n"
                      + "Shape disk1 moves from (490.0, 50.0) to (490.0, 180.0) from t=239 "
                      + "to t=249\n"
                      + "Shape disk1 changes color from (0.0,0.2,0.4) to (0.0,1.0,0.0) "
                      + "from t=249 to t=257\n"
                      + "Shape disk1 moves from (490.0, 180.0) to (490.0, 180.0) from "
                      + "t=257 to t=302\n"
                      + "Shape disk2 moves from (167.0, 210.0) to (167.0, 210.0) "
                      + "from t=1 to t=57\n"
                      + "Shape disk2 moves from (167.0, 210.0) to (167.0, 50.0) from "
                      + "t=57 to t=67\n"
                      + "Shape disk2 moves from (167.0, 50.0) to (167.0, 50.0) from t=67 to t=68\n"
                      + "Shape disk2 moves from (167.0, 50.0) to (317.0, 50.0) from t=68 to t=78\n"
                      + "Shape disk2 moves from (317.0, 50.0) to (317.0, 50.0) from t=78 to t=79\n"
                      + "Shape disk2 moves from (317.0, 50.0) to (317.0, 240.0) from t=79"
                      + " to t=89\n"
                      + "Shape disk2 moves from (317.0, 240.0) to (317.0, 240.0) from t=89 "
                      + "to t=185\n"
                      + "Shape disk2 moves from (317.0, 240.0) to (317.0, 50.0) "
                      + "from t=185 to t=195\n"
                      + "Shape disk2 moves from (317.0, 50.0) to (317.0, 50.0) from "
                      + "t=195 to t=196\n"
                      + "Shape disk2 moves from (317.0, 50.0) to (467.0, 50.0) "
                      + "from t=196 to t=206\n"
                      + "Shape disk2 moves from (467.0, 50.0) to (467.0, 50.0) from "
                      + "t=206 to t=207\n"
                      + "Shape disk2 moves from (467.0, 50.0) to (467.0, 210.0) "
                      + "from t=207 to t=217\n"
                      + "Shape disk2 changes color from (0.0,1.0,0.2) to (0.0,1.0,0.0) from "
                      + "t=217 to t=225\n"
                      + "Shape disk2 moves from (467.0, 210.0) to (467.0, 210.0) "
                      + "from t=225 to t=302\n"
                      + "Shape disk3 moves from (145.0, 240.0) to (145.0, 240.0) "
                      + "from t=1 to t=121\n"
                      + "Shape disk3 moves from (145.0, 240.0) to (145.0, 50.0)"
                      + " from t=121 to t=131\n"
                      + "Shape disk3 moves from (145.0, 50.0) to (145.0, 50.0) "
                      + "from t=131 to t=132\n"
                      + "Shape disk3 moves from (145.0, 50.0) to (445.0, 50.0) "
                      + "from t=132 to t=142\n"
                      + "Shape disk3 moves from (445.0, 50.0) to (445.0, 50.0) "
                      + "from t=142 to t=143\n"
                      + "Shape disk3 moves from (445.0, 50.0) to (445.0, 240.0) "
                      + "from t=143 to t=153\n"
                      + "Shape disk3 changes color from (0.0,0.2,0.7) to (0.0,1.0,0.0) "
                      + "from t=153 to t=161\n"
                      + "Shape disk3 moves from (445.0, 240.0) to (445.0, 240.0) "
                      + "from t=161 to t=302\n",
              stringBuilder.toString());
      bufferedReader.close();
    } catch (Exception e) {
      // do nothing
    }

  }




  @Test
  public void testGetDescription() {
    assertEquals("Shapes:\n"
                    + "Name: disk1\n"
                    + "Type: rectangle\n"
                    + "Min Corner: (190.0, 180.0), Width: 20.0, Height: 30.0, "
                    + "Color: (0.0, 0.0, 0.0)\n"
                    + "Appears at t=1\n"
                    + "Disappears at t=302\n"
                    + "\n"
                    + "Name: disk2\n"
                    + "Type: rectangle\n"
                    + "Min Corner: (167.0, 210.0), Width: 65.0, Height: 30.0, "
                    + "Color: (0.0, 0.0, 0.0)\n"
                    + "Appears at t=1\n"
                    + "Disappears at t=302\n"
                    + "\n"
                    + "Name: disk3\n"
                    + "Type: rectangle\n"
                    + "Min Corner: (145.0, 240.0), Width: 110.0, Height: 30.0, "
                    + "Color: (0.0, 0.0, 0.0)\n"
                    + "Appears at t=1\n"
                    + "Disappears at t=302\n"
                    + "\n"
                    + "Shape disk1 moves from (190.0, 180.0) to (190.0, 180.0) from t=1 to t=25\n"
                    + "Shape disk1 moves from (190.0, 180.0) to (190.0, 50.0) from t=25 to t=35\n"
                    + "Shape disk1 moves from (190.0, 50.0) to (190.0, 50.0) from t=35 to t=36\n"
                    + "Shape disk1 moves from (190.0, 50.0) to (490.0, 50.0) from t=36 to t=46\n"
                    + "Shape disk1 moves from (490.0, 50.0) to (490.0, 50.0) from t=46 to t=47\n"
                    + "Shape disk1 moves from (490.0, 50.0) to (490.0, 240.0) from t=47 to t=57\n"
                    + "Shape disk1 moves from (490.0, 240.0) to (490.0, 240.0) from t=57 to t=89\n"
                    + "Shape disk1 moves from (490.0, 240.0) to (490.0, 50.0) from t=89 to t=99\n"
                    + "Shape disk1 moves from (490.0, 50.0) to (490.0, 50.0) from t=99 to t=100\n"
                    + "Shape disk1 moves from (490.0, 50.0) to (340.0, 50.0) from t=100 to t=110\n"
                    + "Shape disk1 moves from (340.0, 50.0) to (340.0, 50.0) from t=110 to t=111\n"
                    + "Shape disk1 moves from (340.0, 50.0) to (340.0, 210.0) from t=111 to t=121\n"
                    + "Shape disk1 moves from (340.0, 210.0) to (340.0, 210.0) "
                    + "from t=121 to t=153\n"
                    + "Shape disk1 moves from (340.0, 210.0) to (340.0, 50.0) from t=153 to t=163\n"
                    + "Shape disk1 moves from (340.0, 50.0) to (340.0, 50.0) from t=163 to t=164\n"
                    + "Shape disk1 moves from (340.0, 50.0) to (190.0, 50.0) from t=164 to t=174\n"
                    + "Shape disk1 moves from (190.0, 50.0) to (190.0, 50.0) from t=174 to t=175\n"
                    + "Shape disk1 moves from (190.0, 50.0) to (190.0, 240.0) from t=175 to t=185\n"
                    + "Shape disk1 moves from (190.0, 240.0) to (190.0, 240.0) "
                    + "from t=185 to t=217\n"
                    + "Shape disk1 moves from (190.0, 240.0) to (190.0, 50.0) from t=217 to t=227\n"
                    + "Shape disk1 moves from (190.0, 50.0) to (190.0, 50.0) from t=227 to t=228\n"
                    + "Shape disk1 moves from (190.0, 50.0) to (490.0, 50.0) from t=228 to t=238\n"
                    + "Shape disk1 moves from (490.0, 50.0) to (490.0, 50.0) from t=238 to t=239\n"
                    + "Shape disk1 moves from (490.0, 50.0) to (490.0, 180.0) from t=239 to t=249\n"
                    + "Shape disk1 changes color from (0.0,0.2,0.4) to (0.0,1.0,0.0) "
                    + "from t=249 to t=257\n"
                    + "Shape disk1 moves from (490.0, 180.0) to (490.0, 180.0) from"
                    + " t=257 to t=302\n"
                    + "Shape disk2 moves from (167.0, 210.0) to (167.0, 210.0) from t=1 to t=57\n"
                    + "Shape disk2 moves from (167.0, 210.0) to (167.0, 50.0) from t=57 to t=67\n"
                    + "Shape disk2 moves from (167.0, 50.0) to (167.0, 50.0) from t=67 to t=68\n"
                    + "Shape disk2 moves from (167.0, 50.0) to (317.0, 50.0) from t=68 to t=78\n"
                    + "Shape disk2 moves from (317.0, 50.0) to (317.0, 50.0) from t=78 to t=79\n"
                    + "Shape disk2 moves from (317.0, 50.0) to (317.0, 240.0) from t=79 to t=89\n"
                    + "Shape disk2 moves from (317.0, 240.0) to (317.0, 240.0) from t=89 to t=185\n"
                    + "Shape disk2 moves from (317.0, 240.0) to (317.0, 50.0) from t=185 to t=195\n"
                    + "Shape disk2 moves from (317.0, 50.0) to (317.0, 50.0) from t=195 to t=196\n"
                    + "Shape disk2 moves from (317.0, 50.0) to (467.0, 50.0) from t=196 to t=206\n"
                    + "Shape disk2 moves from (467.0, 50.0) to (467.0, 50.0) from t=206 to t=207\n"
                    + "Shape disk2 moves from (467.0, 50.0) to (467.0, 210.0) from t=207 to t=217\n"
                    + "Shape disk2 changes color from (0.0,1.0,0.2) to (0.0,1.0,0.0) "
                    + "from t=217 to t=225\n"
                    + "Shape disk2 moves from (467.0, 210.0) to (467.0, 210.0) "
                    + "from t=225 to t=302\n"
                    + "Shape disk3 moves from (145.0, 240.0) to (145.0, 240.0) from t=1 to t=121\n"
                    + "Shape disk3 moves from (145.0, 240.0) to (145.0, 50.0) from t=121 to t=131\n"
                    + "Shape disk3 moves from (145.0, 50.0) to (145.0, 50.0) from t=131 to t=132\n"
                    + "Shape disk3 moves from (145.0, 50.0) to (445.0, 50.0) from t=132 to t=142\n"
                    + "Shape disk3 moves from (445.0, 50.0) to (445.0, 50.0) from t=142 to t=143\n"
                    + "Shape disk3 moves from (445.0, 50.0) to (445.0, 240.0) from t=143 to t=153\n"
                    + "Shape disk3 changes color from (0.0,0.2,0.7) to (0.0,1.0,0.0) "
                    + "from t=153 to t=161\n"
                    + "Shape disk3 moves from (445.0, 240.0) to (445.0, 240.0) from t=161 to t=302",
            v1.getDescription());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadCurrentView() {
    v1.currentView("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadConstructor1() {
    v1 = new TextBasedViewImpl(null, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadConstructor2() {
    v1 = new TextBasedViewImpl(null, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadConstructor3() throws FileNotFoundException {
    String filename1 = "toh-3.txt";
    String inFile = System.getProperty("user.dir") + "/src/starterCode/" + filename1;
    Readable readable = new FileReader(inFile);
    AnimationBuilder<Model> builder = new ModelImpl.Builder();
    Model model = AnimationReader.parseFile(readable, builder);
    v1 = new TextBasedViewImpl(model, -1);
  }



}