import org.junit.Before;
import org.junit.Test;

import cs5004.animator.model.Model;
import cs5004.animator.model.ModelImpl;

import static org.junit.Assert.assertEquals;


/**
 * This class tests the methods in the AnimationBuilder class.
 */
public class AnimationBuilderTest {
  private ModelImpl.Builder b1;
  private Model m1;
  private Model m2;

  // Sets up animation builder prior to each test
  @Before
  public void setUp() {
    b1 = new ModelImpl.Builder();
    b1.setBounds(0, 10, 370, 360);
    b1.declareShape("r1", "Rectangle");
    b1.addMotion("r1", 0, 2, 0, 5, 12, 20, 250,
            220, 10, 10, 10, 5, 10,
            250, 60, 255);
    m1 = b1.build();

    ModelImpl.Builder b2 = new ModelImpl.Builder();
    b2.setBounds(100, 60, 100, 80);
    b2.declareShape("c1", "ellipse");
    b2.addMotion("c1", 0, 0, 0, 10, 10, 0, 0,
            0, 15, 0, 0, 15, 11, 210, 60, 22);
    m2 = b2.build();
  }

  // Testing the builder when attempting to add a single shape and a single transformation.
  @Test
  public void testSetBounds() {
    assertEquals(370, m1.getCanvasWidth());
    assertEquals(360, m1.getCanvasHeight());
    assertEquals(0, m1.getCanvasX());
    assertEquals(10, m1.getCanvasY());

    assertEquals(100, m2.getCanvasWidth());
    assertEquals(80, m2.getCanvasHeight());
    assertEquals(100, m2.getCanvasX());
    assertEquals(60, m2.getCanvasY());

  }

  // Testing the builder when adding a shape that transforms twice in a single entry.
  @Test
  public void testDeclareShape() {
    assertEquals("Shapes:\n"
            + "Name: r1\n"
            + "Type: rectangle\n"
            + "Min Corner: (2.0, 0.0), Width: 5.0, Height: 12.0, Color: (0.0, 0.0, 0.0)\n"
            + "Appears at t=0\n"
            + "Disappears at t=10\n",
        m1.toString());

    assertEquals("Shapes:\n"
            + "Name: c1\n"
            + "Type: oval\n"
            + "Min Corner: (0.0, 0.0), X Radius: 10.0, Y Radius: 10.0, Color: (0.0, 0.0, 0.0)\n"
            + "Appears at t=0\n"
            + "Disappears at t=15\n",
        m2.toString());
  }

  @Test
  public void testAddMotion() {
    b1 = new ModelImpl.Builder();
    b1.setBounds(0, 10, 370, 360);
    b1.declareShape("r1", "Rectangle");
    b1.addMotion("r1", 0, 2, 0, 5, 12, 20, 250,
            220, 10, 10, 10, 5, 10,
            250, 60, 255);
    b1.addMotion("r1", 0, 2, 0, 5, 12, 20, 250,
            220, 10, 10, 10, 5, 10,
            250, 60, 251);
    b1.addMotion("r1", 11, 2, 0, 5, 12, 20, 250,
            220, 20, 160, 10, 5, 10,
            250, 60, 255);
    m1 = b1.build();
    assertEquals("Shapes:\n"
            + "Name: r1\n"
            + "Type: rectangle\n"
            + "Min Corner: (2.0, 0.0), Width: 5.0, Height: 12.0, Color: (0.0, 0.0, 0.0)\n"
            + "Appears at t=0\n"
            + "Disappears at t=20\n"
            + "\n"
            + "Shape r1 moves from (2.0, 0.0) to (10.0, 10.0) from t=0 to t=10\n"
            + "Shape r1 scales from Width:5.0, Height:12.0 to Width:5.0, Height: "
            + "10.0 from t=0 to t=10\n"
            + "Shape r1 changes color from (0.1,1.0,0.9) to (1.0,0.2,1.0) from t=0 to t=10\n"
            + "Shape r1 moves from (2.0, 0.0) to (160.0, 10.0) from t=11 to t=20\n"
            + "Shape r1 scales from Width:5.0, Height:12.0 to Width:5.0, Height: 10.0 from "
            + "t=11 to t=20\n"
            + "Shape r1 changes color from (0.1,1.0,0.9) to (1.0,0.2,1.0) from t=11 to t=20",
        m1.toString());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShape() {
    b1.declareShape("r1", "moon");
  }




}