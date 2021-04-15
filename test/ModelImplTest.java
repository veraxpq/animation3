import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.List;

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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * This class tests the methods in the ModelImpl class.
 */
public class ModelImplTest {

  private Model m1;
  private Model m2;


  @Before
  public void setUp() {
    m1 = new ModelImpl();
    m2 = new ModelImpl();
  }

  @Test
  public void testSetUp() {
    assertNotNull(m1);
    assertNotNull(m2);
  }


  @Test (expected = IllegalArgumentException.class)
  public void testAddRepetitiveName() {
    Shape origin = new Rectangle(new Point2D.Double(200, 200), Color.green, 50,
            100, NameOfShape.R, "R", 1, 100);
    Shape sameName = new Rectangle(new Point2D.Double(200, 200), Color.green, 50,
            100, NameOfShape.R, "R", 1, 50);
    m1.addShape(origin);
    m1.addShape(sameName);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddRepetitiveName2() {
    m1.addShape(new Rectangle(new Point2D.Double(200, 200), Color.green, 50,
            100, NameOfShape.R, "R", 1, 100));
    m1.addShape(new Rectangle(new Point2D.Double(200, 200), Color.green, 50,
            100, NameOfShape.R, "R", 1, 50));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBadAddShape1() {
    Model m = new ModelImpl();
    m.addShape(null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBadAddShape2() {
    Model m = new ModelImpl();
    m.addShape(new Rectangle(null,
            Color.black, 3, 4, NameOfShape.R, "R",
            1, 200));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBadAddShape3() {
    Model m = new ModelImpl();
    m.addShape(new Point2D.Double(0, 0),
            null, 3, 4, NameOfShape.R, "R", 1, 200);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBadAddShape4() {
    Model m = new ModelImpl();
    m.addShape(new Point2D.Double(0, 0),
            Color.cyan, -3, 4, NameOfShape.R, "R", 1, 20);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBadAddShape5() {
    Model m = new ModelImpl();
    m.addShape(new Point2D.Double(0, 0),
            Color.cyan, 3, -1, NameOfShape.R, "R", 1, 20);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBadAddShape6() {
    Model m = new ModelImpl();
    m.addShape(new Point2D.Double(0, 0),
            Color.cyan, 3, 1, null, "R", 1, 20);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBadAddShape7() {
    Model m = new ModelImpl();
    m.addShape(new Point2D.Double(0, 0),
            Color.cyan, 3, 1, NameOfShape.R, "R", -1, 20);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBadAddShape8() {
    Model m = new ModelImpl();
    m.addShape(new Point2D.Double(0, 0),
            Color.cyan, 3, 1, NameOfShape.R, "R", 1, -2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBadAddShape9() {
    Model m = new ModelImpl();
    m.addShape(new Point2D.Double(0, 0),
            Color.cyan, 3, 1, NameOfShape.R, "R", 11, 2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBadAddShape10() {
    Model m = new ModelImpl();
    m.addShape(new Point2D.Double(0, 0),
            Color.cyan, 3, -1, null, "R", 11, -2);
  }

  @Test
  public void testAddShape0() {
    m2.addShape(new Rectangle(new Point2D.Double(200, 200), Color.green, 50,
            100, NameOfShape.R, "R", 1, 100));
    m2.addShape(new Oval(new Point2D.Double(500, 100),
            Color.blue, 60, 30, NameOfShape.C,
            "C", 6, 100));
    assertEquals("Shapes:\n"
            + "Name: R\n"
            + "Type: rectangle\n"
            + "Min Corner: (200.0, 200.0), Width: 50.0, Height: 100.0, Color: (0.0, 1.0, 0.0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=100\n"
            + "\n"
            + "Name: C\n"
            + "Type: oval\n"
            + "Min Corner: (500.0, 100.0), X Radius: 60.0, Y Radius: 30.0, Color: (0.0, 0.0, 1.0)\n"
            + "Appears at t=6\n"
            + "Disappears at t=100\n", m2.toString());
    assertEquals("[Name: R\n"
            + "Type: rectangle\n"
            + "Min Corner: (200.0, 200.0), Width: 50.0, Height: 100.0, Color: (0.0, 1.0, 0.0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=100, Name: C\n"
            + "Type: oval\n"
            + "Min Corner: (500.0, 100.0), X Radius: 60.0, Y Radius: 30.0, Color: (0.0, 0.0, 1.0)\n"
            + "Appears at t=6\n"
            + "Disappears at t=100]",
        m2.getListOfShapes().toString());
  }

  @Test
  public void testAddShape1() {
    Shape s1 = new Rectangle(new Point2D.Double(10, 11),
            Color.darkGray, 77, 88, NameOfShape.R,
            "R1", 10, 100);
    Shape s2 = new Oval(new Point2D.Double(500, 100),
            Color.pink, 55, 16, NameOfShape.C,
            "C1", 2, 50);
    Shape s3 = new Oval(new Point2D.Double(10, 100),
            Color.magenta, 2, 160, NameOfShape.C,
            "C2", 10, 80);

    m1.addShape(s1);
    assertEquals("Shapes:\n"
            + "Name: R1\n"
            + "Type: rectangle\n"
            + "Min Corner: (10.0, 11.0), Width: 77.0, Height: 88.0, Color: (0.0, 0.0, 0.0)\n"
            + "Appears at t=10\n"
            + "Disappears at t=100\n", m1.toString());

    m1.addShape(s2);
    m1.addShape(s3);
    assertEquals("Shapes:\n"
            + "Name: R1\n"
            + "Type: rectangle\n"
            + "Min Corner: (10.0, 11.0), Width: 77.0, Height: 88.0, Color: (0.0, 0.0, 0.0)\n"
            + "Appears at t=10\n"
            + "Disappears at t=100\n"
            + "\n"
            + "Name: C1\n"
            + "Type: oval\n"
            + "Min Corner: (500.0, 100.0), X Radius: 55.0, Y Radius: 16.0, Color: (1.0, 0.0, 0.0)\n"
            + "Appears at t=2\n"
            + "Disappears at t=50\n"
            + "\n"
            + "Name: C2\n"
            + "Type: oval\n"
            + "Min Corner: (10.0, 100.0), X Radius: 2.0, Y Radius: 160.0, Color: (1.0, 0.0, 1.0)\n"
            + "Appears at t=10\n"
            + "Disappears at t=80\n", m1.toString());
  }

  @Test
  public void testAddShape2() {
    m1.addShape(new Point2D.Double(10, 89), Color.cyan, 4, 8,
            NameOfShape.R, "R1", 0, 77);
    assertEquals("Shapes:\n"
            + "Name: R1\n"
            + "Type: rectangle\n"
            + "Min Corner: (10.0, 89.0), Width: 4.0, Height: 8.0, Color: (0.0, 1.0, 1.0)\n"
            + "Appears at t=0\n"
            + "Disappears at t=77\n", m1.toString());
    m1.addShape(new Point2D.Double(1, 9), Color.black, 14, 18,
            NameOfShape.C, "C1", 100, 200);
    assertEquals("Shapes:\n"
            + "Name: R1\n"
            + "Type: rectangle\n"
            + "Min Corner: (10.0, 89.0), Width: 4.0, Height: 8.0, Color: (0.0, 1.0, 1.0)\n"
            + "Appears at t=0\n"
            + "Disappears at t=77\n"
            + "\n"
            + "Name: C1\n"
            + "Type: oval\n"
            + "Min Corner: (1.0, 9.0), X Radius: 14.0, Y Radius: 18.0, Color: (0.0, 0.0, 0.0)\n"
            + "Appears at t=100\n"
            + "Disappears at t=200\n", m1.toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddBadAnimation1() {
    Model m = new ModelImpl();
    Animation a1 = new ChangeColor(2, 10, Color.black, Color.cyan);
    // add Animation before add shape
    m.addAnimation("R", a1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddBadAnimation2() {
    Model m = new ModelImpl();
    Animation a1 = new ChangeColor(2, 10, Color.black, Color.cyan);
    // add Animation before add shape with invalid ID
    m.addAnimation("", a1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddBadAnimation4() {
    Model m = new ModelImpl();
    Shape s1 = new Rectangle(new Point2D.Double(101, 11),
            Color.yellow, 10, 88, NameOfShape.R,
            "R", 1, 100);
    Animation a1 = new ChangeColor(2, 10, Color.black, Color.cyan);
    // add Animation with invalid ID
    m.addAnimation("REC", a1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddBadAnimation5() {
    Model m = new ModelImpl();
    Shape s1 = new Rectangle(new Point2D.Double(101, 11),
            Color.yellow, 10, 88, NameOfShape.R,
            "R", 1, 100);
    Animation a1 = new ChangeColor(2, 10, Color.black, Color.cyan);
    // add Animation with invalid ID
    m.addAnimation(null, a1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddBadAnimation6() {
    Model m = new ModelImpl();
    Shape s1 = new Rectangle(new Point2D.Double(101, 11),
            Color.yellow, 10, 88, NameOfShape.R,
            "R", 1, 100);
    // add Animation with invalid Animation
    m.addAnimation("R", null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddBadAnimation7() {
    Model m = new ModelImpl();
    Shape s1 = new Rectangle(new Point2D.Double(101, 11),
            Color.yellow, 10, 88, NameOfShape.R,
            "R", 1, 100);
    // add Animation with multiple invalid inputs
    m.addAnimation("Rectangle", null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddBadAnimation8() {
    Model m = new ModelImpl();
    Shape s1 = new Rectangle(new Point2D.Double(101, 11),
            Color.yellow, 10, 88, NameOfShape.R,
            "R", 1, 100);
    Animation a1 = new Move(4, 20, new Point2D.Double(101, 11),
            new Point2D.Double(6.7, 9.8), NameOfShape.R);
    Animation a2 = new Move(5, 10, new Point2D.Double(6.7, 9.8),
            new Point2D.Double(64, 9), NameOfShape.R);

    // a shape's different moves can't be overlapped in time
    m.addAnimation("R", a1);
    m.addAnimation("R", a2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddBadAnimation9() {
    Model m = new ModelImpl();
    Shape s1 = new Rectangle(new Point2D.Double(101, 11),
            Color.yellow, 10, 88, NameOfShape.R,
            "R", 22, 100);
    Animation a1 = new Move(4, 20, new Point2D.Double(101, 11),
            new Point2D.Double(6.7, 9.8), NameOfShape.R);

    // can't add a animation which time is not in the shape's valid time range
    m.addAnimation("R", a1);

  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddBadAnimation10() {
    Model m = new ModelImpl();
    Shape s1 = new Rectangle(new Point2D.Double(101, 11),
            Color.yellow, 10, 88, NameOfShape.R,
            "R", 1, 100);
    Animation a1 = new Move(4, 20, new Point2D.Double(101, 11),
            new Point2D.Double(6.7, 9.8), NameOfShape.R);
    Animation a2 = new Move(21, 30, new Point2D.Double(6.7, 9.8),
            new Point2D.Double(64, 9), NameOfShape.R);
    Animation a3 = new Move(5, 22, new Point2D.Double(64, 9),
            new Point2D.Double(63, 98), NameOfShape.R);

    // a shape's different moves can't be overlapped in time
    m.addAnimation("R", a1);
    m.addAnimation("R", a2);
    m.addAnimation("R", a3);
  }




  @Test
  public void testAddAnimation() {
    Shape s1 = new Rectangle(new Point2D.Double(101, 11),
            Color.yellow, 10, 88, NameOfShape.R,
            "R", 1, 100);
    Shape s2 = new Oval(new Point2D.Double(500, 100),
            Color.blue, 55, 16, NameOfShape.C,
            "C", 2, 52);
    m1.addShape(s1);
    m1.addShape(s2);

    Animation a1 = new ChangeColor(2, 10, s1.getColor(), Color.cyan);
    m1.addAnimation("R", a1);
    Animation a2 = new Scale(4, 100, s1.getWidth(), s1.getHeight(),
            22, 55, s1.getType());
    m1.addAnimation("R", a2);
    assertEquals("Shapes:\n"
            + "Name: R\n"
            + "Type: rectangle\n"
            + "Min Corner: (101.0, 11.0), Width: 10.0, Height: 88.0, Color: (1.0, 1.0, 0.0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=100\n"
            + "\n"
            + "Name: C\n"
            + "Type: oval\n"
            + "Min Corner: (500.0, 100.0), X Radius: 55.0, Y Radius: 16.0, Color: (0.0, 0.0, 1.0)\n"
            + "Appears at t=2\n"
            + "Disappears at t=52\n"
            + "\n"
            + "Shape R changes color from (1.0,1.0,0.0) to (0.0,1.0,1.0) from t=2 to t=10\n"
            + "Shape R scales from Width:10.0, Height:88.0 to Width:22.0, Height: 55.0 "
            + "from t=4 to t=100", m1.toString());

    Animation a3 = new Scale(3, 6, s2.getWidth(), s2.getHeight(),
            6.0, 12.0, s2.getType());
    m1.addAnimation("C", a3);
    Animation a4 = new Move(4, 20, s1.getPosition(),
            new Point2D.Double(6.7, 9.8), NameOfShape.R);
    m1.addAnimation("R", a4);

    assertEquals("Shapes:\n"
            + "Name: R\n"
            + "Type: rectangle\n"
            + "Min Corner: (101.0, 11.0), Width: 10.0, Height: 88.0, Color: (1.0, 1.0, 0.0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=100\n"
            + "\n"
            + "Name: C\n"
            + "Type: oval\n"
            + "Min Corner: (500.0, 100.0), X Radius: 55.0, Y Radius: 16.0, Color: (0.0, 0.0, 1.0)\n"
            + "Appears at t=2\n"
            + "Disappears at t=52\n"
            + "\n"
            + "Shape R changes color from (1.0,1.0,0.0) to (0.0,1.0,1.0) from t=2 to t=10\n"
            + "Shape R scales from Width:10.0, Height:88.0 to Width:22.0, Height: 55.0 from t=4 "
            + "to t=100\n"
            + "Shape R moves from (101.0, 11.0) to (6.7, 9.8) from t=4 to t=20\n"
            + "Shape C scales from X radius: 55.0, Y radius:16.0 to X radius:6.0, "
            + "Y radius: 12.0 from t=3 to t=6", m1.toString());
    // add a 2nd move to R
    Animation a5 = new Move(22, 35, new Point2D.Double(6.7, 9.8),
            new Point2D.Double(10, 33), NameOfShape.R);
    m1.addAnimation("R", a5);
    assertEquals("Shapes:\n"
            + "Name: R\n"
            + "Type: rectangle\n"
            + "Min Corner: (101.0, 11.0), Width: 10.0, Height: 88.0, Color: (1.0, 1.0, 0.0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=100\n"
            + "\n"
            + "Name: C\n"
            + "Type: oval\n"
            + "Min Corner: (500.0, 100.0), X Radius: 55.0, Y Radius: 16.0, "
            + "Color: (0.0, 0.0, 1.0)\n"
            + "Appears at t=2\n"
            + "Disappears at t=52\n"
            + "\n"
            + "Shape R changes color from (1.0,1.0,0.0) to (0.0,1.0,1.0) from t=2 to t=10\n"
            + "Shape R scales from Width:10.0, Height:88.0 to Width:22.0, "
            + "Height: 55.0 from t=4 to t=100\n"
            + "Shape R moves from (101.0, 11.0) to (6.7, 9.8) from t=4 to t=20\n"
            + "Shape R moves from (6.7, 9.8) to (10.0, 33.0) from t=22 to t=35\n"
            + "Shape C scales from X radius: 55.0, Y radius:16.0 to X radius:6.0, "
            + "Y radius: 12.0 from t=3 to t=6", m1.toString());

    // add a 3rd move to R
    Animation a6 = new Move(1, 3, new Point2D.Double(10, 33),
            new Point2D.Double(100, 337), NameOfShape.R);
    m1.addAnimation("R", a6);
    assertEquals("Shapes:\n"
            + "Name: R\n"
            + "Type: rectangle\n"
            + "Min Corner: (101.0, 11.0), Width: 10.0, Height: 88.0, Color: (1.0, 1.0, 0.0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=100\n"
            + "\n"
            + "Name: C\n"
            + "Type: oval\n"
            + "Min Corner: (500.0, 100.0), X Radius: 55.0, Y Radius: 16.0, Color: (0.0, 0.0, 1.0)\n"
            + "Appears at t=2\n"
            + "Disappears at t=52\n"
            + "\n"
            + "Shape R moves from (10.0, 33.0) to (100.0, 337.0) from t=1 to t=3\n"
            + "Shape R changes color from (1.0,1.0,0.0) to (0.0,1.0,1.0) from t=2 to t=10\n"
            + "Shape R scales from Width:10.0, Height:88.0 to Width:22.0, "
            + "Height: 55.0 from t=4 to t=100\n"
            + "Shape R moves from (101.0, 11.0) to (6.7, 9.8) from t=4 to t=20\n"
            + "Shape R moves from (6.7, 9.8) to (10.0, 33.0) from t=22 to t=35\n"
            + "Shape C scales from X radius: 55.0, Y radius:16.0 to X radius:6.0, "
            + "Y radius: 12.0 from t=3 to t=6", m1.toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBadGetShapeAtTick() {
    Model m = new ModelImpl();
    Shape s1 = new Rectangle(new Point2D.Double(101, 11),
            Color.yellow, 10, 88, NameOfShape.R,
            "R", 1, 100);
    Animation a1 = new ChangeColor(2, 10, s1.getColor(), Color.cyan);
    m.addShape(s1);
    m.addAnimation("R", a1);
    m.getShapeAtTick(-1);
  }


  @Test
  public void testGetShapeAtTick() {
    Shape s1 = new Rectangle(new Point2D.Double(200, 200), Color.green, 50,
            100, NameOfShape.R, "R", 0, 100);
    Shape s2 = new Oval(new Point2D.Double(500, 100),
            Color.blue, 60, 30, NameOfShape.C,
            "C", 10, 200);
    Shape s3 = new Rectangle(new Point2D.Double(50, 10),
            Color.yellow, 10, 28, NameOfShape.C,
            "R1", 150, 300);
    m1.addShape(s1);
    m1.addShape(s2);
    m1.addShape(s3);

    Animation a1 = new Scale(3, 60, s1.getWidth(), s1.getHeight(),
            6.0, 12.0, s1.getType());
    Animation a2 = new Move(4, 20, s1.getPosition(),
            new Point2D.Double(6.7, 9.8), NameOfShape.R);
    Animation a3 = new ChangeColor(100, 180, s2.getColor(), Color.red);
    Animation a4 = new Move(155, 250, s3.getPosition(),
            new Point2D.Double(20, 100), NameOfShape.R);

    // add Animation by unsorted time order
    m1.addAnimation("C", a3);
    m1.addAnimation("R1", a4);
    m1.addAnimation("R", a2);
    m1.addAnimation("R", a1);

    List<Shape> list0 = m1.getShapeAtTick(1);
    List<Shape> list1 = m1.getShapeAtTick(5);
    List<Shape> list2 = m1.getShapeAtTick(11);
    List<Shape> list3 = m1.getShapeAtTick(100);
    List<Shape> list4 = m1.getShapeAtTick(101);
    List<Shape> list5 = m1.getShapeAtTick(151);
    List<Shape> list6 = m1.getShapeAtTick(4);

    assertEquals("[]", list0.toString());
    // when t = 4, height and width changes, but position should not change
    assertEquals("[Name: R\n"
            + "Type: rectangle\n"
            + "Min Corner: (115.43125, 116.7875), Width: 49.2, "
            + "Height: 98.5, Color: (0.0, 1.0, 0.0)\n"
            + "Appears at t=0\n"
            + "Disappears at t=100]", list6.toString());
    // when t = 5, both the position and the height, width change
    assertEquals("[Name: R\n"
            + "Type: rectangle\n"
            + "Min Corner: (187.91875, 188.1125), Width: 48.5, Height: 96.9, "
            + "Color: (0.0, 1.0, 0.0)\n"
            + "Appears at t=0\n"
            + "Disappears at t=100]", list1.toString());

    assertEquals("[Name: R\n"
            + "Type: rectangle\n"
            + "Min Corner: (115.43125, 116.7875), Width: 43.8, "
            + "Height: 87.6, Color: (0.0, 1.0, 0.0)\n"
            + "Appears at t=0\n"
            + "Disappears at t=100]", list2.toString());

    // when t = 100（boundary point）, R shouldn't disappear, C shouldn't change color
    assertEquals("[]", list3.toString());

    // when t = 101, R should disappear, C should change color
    assertEquals("[Name: C\n"
            + "Type: oval\n"
            + "Min Corner: (500.0, 100.0), X Radius: 60.0, Y Radius: 30.0, Color: (0.0, 0.0, 0.0)\n"
            + "Appears at t=10\n"
            + "Disappears at t=200]", list4.toString());

    // when t = 151, R should disappear, C should change color, R1 should move
    assertEquals("[Name: C\n"
            + "Type: oval\n"
            + "Min Corner: (500.0, 100.0), X Radius: 60.0, Y Radius: 30.0, Color: (0.0, 0.0, 0.0)\n"
            + "Appears at t=10\n"
            + "Disappears at t=200]", list5.toString());
  }

  @Test
  public void testToString() {
    Shape s1 = new Rectangle(new Point2D.Double(200, 200),
            Color.red, 50, 100, NameOfShape.R,
            "R", 1, 1000);
    Shape s2 = new Oval(new Point2D.Double(500, 100),
            Color.blue, 60, 30, NameOfShape.C,
            "C", 6, 500);

    m1.addShape(s1);
    Animation a1 = new Move(1, 50, new Point2D.Double(200, 200),
            new Point2D.Double(300, 300), NameOfShape.R);
    m1.addAnimation(s1.getName(), a1);
    m1.addShape(s2);

    Animation a2 = new Scale(51, 70, s1.getWidth(), s1.getHeight(),
            25, 100, s1.getType());
    m1.addAnimation(s1.getName(), a2);

    Animation a3 = new ChangeColor(100, 180, s2.getColor(), Color.red);
    m1.addAnimation(s2.getName(), a3);
    assertEquals("Shapes:\n"
                    + "Name: R\n"
                    + "Type: rectangle\n"
                    + "Min Corner: (200.0, 200.0), Width: 50.0, Height: 100.0,"
                    + " Color: (1.0, 0.0, 0.0)\n"
                    + "Appears at t=1\n"
                    + "Disappears at t=1000\n"
                    + "\n"
                    + "Name: C\n"
                    + "Type: oval\n"
                    + "Min Corner: (500.0, 100.0), X Radius: 60.0, Y Radius: 30.0, "
                    + "Color: (0.0, 0.0, 1.0)\n"
                    + "Appears at t=6\n"
                    + "Disappears at t=500\n"
                    + "\n"
                    + "Shape R moves from (200.0, 200.0) to (300.0, 300.0) from t=1 to t=50\n"
                    + "Shape R scales from Width:50.0, Height:100.0 to Width:25.0,"
                    + " Height: 100.0 from t=51 "
                    + "to t=70\n"
                    + "Shape C changes color from (0.0,0.0,1.0) to (1.0,0.0,0.0) "
                    + "from t=100 to t=180",
            m1.toString());

    Shape s3 = new Oval(new Point2D.Double(200, 100),
            Color.yellow, 30, 70, NameOfShape.C,
            "C1", 6, 100);
    // scale an oval
    Animation a4 = new Scale(20, 70, s3.getWidth(), s3.getHeight(),
            40, 90, s3.getType());
    // add shape first
    m1.addShape(s3);
    assertEquals("Shapes:\n"
                    + "Name: R\n"
                    + "Type: rectangle\n"
                    + "Min Corner: (200.0, 200.0), Width: 50.0, Height: 100.0, Color: "
                    + "(1.0, 0.0, 0.0)\n"
                    + "Appears at t=1\n"
                    + "Disappears at t=1000\n"
                    + "\n"
                    + "Name: C\n"
                    + "Type: oval\n"
                    + "Min Corner: (500.0, 100.0), X Radius: 60.0, Y Radius: 30.0, "
                    + "Color: (0.0, 0.0, 1.0)\n"
                    + "Appears at t=6\n"
                    + "Disappears at t=500\n"
                    + "\n"
                    + "Name: C1\n"
                    + "Type: oval\n"
                    + "Min Corner: (200.0, 100.0), X Radius: 30.0, Y Radius: 70.0, "
                    + "Color: (1.0, 1.0, 0.0)\n"
                    + "Appears at t=6\n"
                    + "Disappears at t=100\n"
                    + "\n"
                    + "Shape R moves from (200.0, 200.0) to (300.0, 300.0) from t=1 to t=50\n"
                    + "Shape R scales from Width:50.0, Height:100.0 to Width:25.0, Height: 100.0 "
                    + "from t=51 to t=70\n"
                    + "Shape C changes color from (0.0,0.0,1.0) to (1.0,0.0,0.0) from t=100 "
                    + "to t=180",
            m1.toString());
    // then add Animation for this shape
    m1.addAnimation(s3.getName(), a4);
    assertEquals("Shapes:\n"
            + "Name: R\n"
            + "Type: rectangle\n"
            + "Min Corner: (200.0, 200.0), Width: 50.0, Height: 100.0, Color: (1.0, 0.0, 0.0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=1000\n"
            + "\n"
            + "Name: C\n"
            + "Type: oval\n"
            + "Min Corner: (500.0, 100.0), X Radius: 60.0, Y Radius: 30.0, Color: (0.0, 0.0, 1.0)\n"
            + "Appears at t=6\n"
            + "Disappears at t=500\n"
            + "\n"
            + "Name: C1\n"
            + "Type: oval\n"
            + "Min Corner: (200.0, 100.0), X Radius: 30.0, Y Radius: 70.0, Color: (1.0, 1.0, 0.0)\n"
            + "Appears at t=6\n"
            + "Disappears at t=100\n"
            + "\n"
            + "Shape R moves from (200.0, 200.0) to (300.0, 300.0) from t=1 to t=50\n"
            + "Shape R scales from Width:50.0, Height:100.0 to Width:25.0, Height: 100.0 "
            + "from t=51 to t=70\n"
            + "Shape C changes color from (0.0,0.0,1.0) to (1.0,0.0,0.0) from t=100 to t=180\n"
            + "Shape C1 scales from X radius: 30.0, Y radius:70.0 to X radius:40.0, Y radius: "
            + "90.0 from t=20 to t=70", m1.toString());
  }

  @Test
  public void getShape() {
    m2.addShape(new Rectangle(new Point2D.Double(200, 200), Color.green, 50,
            100, NameOfShape.R, "R", 1, 100));
    m2.addShape(new Oval(new Point2D.Double(500, 100),
            Color.blue, 60, 30, NameOfShape.C,
            "C", 6, 100));
    Shape s1 = m2.getShape("R");
    assertEquals("Name: R\n"
            + "Type: rectangle\n"
            + "Min Corner: (200.0, 200.0), Width: 50.0, Height: 100.0, Color: (0.0, 1.0, 0.0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=100", s1.toString());

    Shape s2 = m2.getShape("C");
    assertEquals("Name: C\n"
            + "Type: oval\n"
            + "Min Corner: (500.0, 100.0), X Radius: 60.0, Y Radius: 30.0, "
            + "Color: (0.0, 0.0, 1.0)\n"
            + "Appears at t=6\n"
            + "Disappears at t=100", s2.toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testGetNullShape() {
    m2.getShape(null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testGetNonExistentShape() {
    m2.getShape("no");
  }

  @Test
  public void testRemoveShape() {
    Shape s1 = new Rectangle(new Point2D.Double(200, 200), Color.green, 50,
            100, NameOfShape.R, "R", 1, 100);
    Shape s2 = new Oval(new Point2D.Double(500, 100),
            Color.blue, 60, 30, NameOfShape.C,
            "C", 6, 100);
    m2.addShape(s2);
    m2.addShape(s1);

    Animation a2 = new Scale(51, 70, s1.getWidth(), s1.getHeight(),
            25, 100, s1.getType());
    m2.addAnimation(s1.getName(), a2);

    Animation a3 = new ChangeColor(100, 180, s2.getColor(), Color.red);
    m2.addAnimation(s2.getName(), a3);

    assertEquals("Shapes:\n"
            + "Name: C\n"
            + "Type: oval\n"
            + "Min Corner: (500.0, 100.0), X Radius: 60.0, Y Radius: 30.0, Color: (0.0, 0.0, 1.0)\n"
            + "Appears at t=6\n"
            + "Disappears at t=180\n"
            + "\n"
            + "Name: R\n"
            + "Type: rectangle\n"
            + "Min Corner: (200.0, 200.0), Width: 50.0, Height: 100.0, Color: (0.0, 1.0, 0.0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=100\n"
            + "\n"
            + "Shape C changes color from (0.0,0.0,1.0) to (1.0,0.0,0.0) from t=100 to t=180\n"
            + "Shape R scales from Width:50.0, Height:100.0 to Width:25.0, "
            + "Height: 100.0 from t=51 to t=70", m2.toString());

    m2.removeShape("R");
    assertEquals("Shapes:\n"
            + "Name: C\n"
            + "Type: oval\n"
            + "Min Corner: (500.0, 100.0), X Radius: 60.0, Y Radius: 30.0, Color: (0.0, 0.0, 1.0)\n"
            + "Appears at t=6\n"
            + "Disappears at t=180\n"
            + "\n"
            + "Shape C changes color from (0.0,0.0,1.0) to "
            + "(1.0,0.0,0.0) from t=100 to t=180", m2.toString());

    m2.removeShape("C");
    assertEquals("Shapes:", m2.toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNullRemoveShape() {
    m2.removeShape((String)null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testRemoveNonExistentShape() {
    m2.removeShape("no");
  }

  @Test
  public void testRemoveShapeByShape() {
    Shape s1 = new Rectangle(new Point2D.Double(200, 200), Color.green, 50,
            100, NameOfShape.R, "R", 1, 100);
    Shape s2 = new Oval(new Point2D.Double(500, 100),
            Color.blue, 60, 30, NameOfShape.C,
            "C", 6, 100);
    m2.addShape(s2);
    m2.addShape(s1);

    Animation a2 = new Scale(51, 70, s1.getWidth(), s1.getHeight(),
            25, 100, s1.getType());
    m2.addAnimation(s1.getName(), a2);

    Animation a3 = new ChangeColor(100, 180, s2.getColor(), Color.red);
    m2.addAnimation(s2.getName(), a3);

    assertEquals("Shapes:\n"
            + "Name: C\n"
            + "Type: oval\n"
            + "Min Corner: (500.0, 100.0), X Radius: 60.0, Y Radius: 30.0, Color: (0.0, 0.0, 1.0)\n"
            + "Appears at t=6\n"
            + "Disappears at t=180\n"
            + "\n"
            + "Name: R\n"
            + "Type: rectangle\n"
            + "Min Corner: (200.0, 200.0), Width: 50.0, Height: 100.0, Color: (0.0, 1.0, 0.0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=100\n"
            + "\n"
            + "Shape C changes color from (0.0,0.0,1.0) to (1.0,0.0,0.0) from t=100 to t=180\n"
            + "Shape R scales from Width:50.0, Height:100.0 to Width:25.0, "
            + "Height: 100.0 from t=51 to t=70", m2.toString());

    m2.removeShape(s1);
    assertEquals("Shapes:\n"
            + "Name: C\n"
            + "Type: oval\n"
            + "Min Corner: (500.0, 100.0), X Radius: 60.0, Y Radius: 30.0, Color: (0.0, 0.0, 1.0)\n"
            + "Appears at t=6\n"
            + "Disappears at t=180\n"
            + "\n"
            + "Shape C changes color from (0.0,0.0,1.0) to (1.0,0.0,0.0) from "
            + "t=100 to t=180", m2.toString());

    m2.removeShape(s2);
    assertEquals("Shapes:", m2.toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNullRemoveShapeByShape() {
    m2.removeShape((Shape) null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testRemoveNonExistentShapeByNullShape() {
    Shape s = null;
    m2.removeShape(s);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testRemoveNonExistShapeByNonExistShape() {
    Shape s = new Rectangle(new Point2D.Double(200, 200), Color.green, 50,
            100, NameOfShape.R, "h", 1, 100);
    m2.removeShape(s);
  }

  @Test
  public void testGetMapOfShapes() {
    Shape s1 = new Rectangle(new Point2D.Double(101, 11),
            Color.yellow, 10, 88, NameOfShape.R,
            "R", 1, 100);
    Shape s2 = new Oval(new Point2D.Double(500, 100),
            Color.blue, 55, 16, NameOfShape.C,
            "C", 2, 52);
    m1.addShape(s1);
    m1.addShape(s2);

    Animation a1 = new ChangeColor(2, 10, s1.getColor(), Color.cyan);
    m1.addAnimation("R", a1);
    assertEquals("{R=Name: R\n"
            + "Type: rectangle\n"
            + "Min Corner: (101.0, 11.0), Width: 10.0, Height: 88.0, Color: (1.0, 1.0, 0.0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=100, C=Name: C\n"
            + "Type: oval\n"
            + "Min Corner: (500.0, 100.0), X Radius: 55.0, Y Radius: 16.0, Color: (0.0, 0.0, 1.0)\n"
            + "Appears at t=2\n"
            + "Disappears at t=52}",
        m1.getMapOfShapes().toString());
  }



  @Test
  public void testGetMapOfAnimations() {
    Shape s1 = new Rectangle(new Point2D.Double(101, 11),
            Color.yellow, 10, 88, NameOfShape.R,
            "R", 1, 100);
    Shape s2 = new Oval(new Point2D.Double(500, 100),
            Color.blue, 55, 16, NameOfShape.C,
            "C", 2, 52);
    m1.addShape(s1);
    m1.addShape(s2);
    Animation a1 = new ChangeColor(2, 10, s1.getColor(), Color.cyan);
    m1.addAnimation("R", a1);
    Animation a2 = new Scale(51, 70, s1.getWidth(), s1.getHeight(),
            25, 100, s1.getType());
    m1.addAnimation("C", a2);

    assertEquals("{R=Name: R\n"
            + "Type: rectangle\n"
            + "Min Corner: (101.0, 11.0), Width: 10.0, Height: 88.0, Color: (1.0, 1.0, 0.0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=100, C=Name: C\n"
            + "Type: oval\n"
            + "Min Corner: (500.0, 100.0), X Radius: 55.0, Y Radius: 16.0, Color: (0.0, 0.0, 1.0)\n"
            + "Appears at t=2\n"
            + "Disappears at t=70}",
        m1.getMapOfShapes().toString());
  }

  @Test
  public void testSetCanvas() {
    m1.setCanvas(1,2,77,99);
    assertEquals(1,  m1.getCanvasX());
    assertEquals(2,  m1.getCanvasY());
    assertEquals(77,  m1.getCanvasWidth());
    assertEquals(99,  m1.getCanvasHeight());
  }

  @Test
  public void testGetCanvasX() {
    m1.setCanvas(10,2,77,100);
    assertEquals(10,  m1.getCanvasX());
  }

  @Test
  public void testGetCanvasY() {
    m1.setCanvas(10,62,80,100);
    assertEquals(62,  m1.getCanvasY());
  }

  @Test
  public void testGetCanvasWidth() {
    m1.setCanvas(10,62,80,100);
    assertEquals(80,  m1.getCanvasWidth());
  }

  @Test
  public void testGetCanvasHeight() {
    m1.setCanvas(10,62,80,1000);
    assertEquals(1000,  m1.getCanvasHeight());
  }

  @Test
  public void testGetListOfShapes() {
    Shape s1 = new Rectangle(new Point2D.Double(101, 11),
            Color.yellow, 10, 88, NameOfShape.R,
            "R", 1, 100);
    Shape s2 = new Oval(new Point2D.Double(500, 100),
            Color.blue, 55, 16, NameOfShape.C,
            "C", 2, 52);
    m1.addShape(s1);
    m1.addShape(s2);
    assertEquals("[Name: R\n"
            + "Type: rectangle\n"
            + "Min Corner: (101.0, 11.0), Width: 10.0, Height: 88.0, Color: (1.0, 1.0, 0.0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=100, Name: C\n"
            + "Type: oval\n"
            + "Min Corner: (500.0, 100.0), X Radius: 55.0, Y Radius: 16.0, Color: (0.0, 0.0, 1.0)\n"
            + "Appears at t=2\n"
            + "Disappears at t=52]",
        m1.getListOfShapes().toString());
  }
}