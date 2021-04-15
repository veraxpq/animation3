import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.awt.geom.Point2D;

import cs5004.animator.model.NameOfShape;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Shape;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * This class tests the methods in the Oval class.
 */
public class OvalTest {
  private Shape o1;
  private Shape o2;
  private Shape o3;
  private Shape o4;
  private Shape o5;

  @Before
  public void setUp() {
    o1 = new Oval(new Point2D.Double(2.0, 4.0),
            Color.red, 3, 4, NameOfShape.C,
            "C", 1, 200);
    o2 = new Oval(new Point2D.Double(12.0, 33.3),
            Color.green, 1.8, 55.9, NameOfShape.C,
            "C1", 20, 300);
    o3 = new Oval(new Point2D.Double(0, 0), Color.black, 0,
            4, NameOfShape.R, "R", 1, 200);
    o4 = new Oval(new Point2D.Double(0, 0), Color.black, 3,
            0, NameOfShape.R, "R", 1, 200);
    o5 = new Oval(new Point2D.Double(0, 0), Color.black, 0,
            0, NameOfShape.R, "R", 1, 200);
  }

  @Test
  public void testSetUp() {
    assertNotNull(o1);
    assertNotNull(o2);
    assertNotNull(o3);
    assertNotNull(o4);
    assertNotNull(o5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullPoint() {
    o2 = new Oval(null,
            Color.black, 3, 4, NameOfShape.R, "R",
            1, 200);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullColor() {
    o2 = new Oval(new Point2D.Double(0, 0),
            null, 3, 4, NameOfShape.R, "R", 1,
            200);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullShape() {
    o2 = new Oval(new Point2D.Double(0, 0),
            Color.black, 3, 4, null, "R", 1,
            200);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeWidth() {
    o2 = new Oval(new Point2D.Double(0, 0),
            Color.black, -3, 4, NameOfShape.R, "R", 1,
            200);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHeight() {
    o2 = new Oval(new Point2D.Double(0, 0),
            Color.black, 3, -4, NameOfShape.R, "R", 1,
            200);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeAppearTime() {
    o2 = new Oval(new Point2D.Double(0, 0),
            Color.black, 3, 0, NameOfShape.R, "R", -1,
            200);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeDisappearTime() {
    o2 = new Oval(new Point2D.Double(0, 0),
            Color.black, 3, 0, NameOfShape.R, "R", 1,
            -200);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalDisappearTime() {
    o2 = new Oval(new Point2D.Double(0, 0),
            Color.black, 3, 0, NameOfShape.R, "R", 100,
            50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullName() {
    o2 = new Oval(new Point2D.Double(0, 0),
            Color.black, 3, 0, NameOfShape.R, null, 1,
            200);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testZeroName() {
    o2 = new Oval(new Point2D.Double(0, 0),
            Color.black, 3, 0, NameOfShape.R, "", 1,
            200);
  }

  @Test
  public void testCreateNewShape() {
    Shape movedO1 = o1.createNewShape(new Point2D.Double(3.0, 4.0));
    assertEquals("Name: C\n"
            + "Type: oval\n"
            + "Min Corner: (3.0, 4.0), X Radius: 3.0, Y Radius: 4.0, Color: (1.0, 0.0, 0.0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=200", movedO1.toString());
  }


  @Test (expected = IllegalArgumentException.class)
  public void testNullPointCreateNewShape() {
    Shape movedR2 = o1.createNewShape((Point2D) null);
  }

  @Test
  public void testCreateNewShape2() {
    Shape movedR1 = o1.createNewShape(Color.black);

    assertEquals("Name: C\n"
            + "Type: oval\n"
            + "Min Corner: (2.0, 4.0), X Radius: 3.0, Y Radius: 4.0, Color: (0.0, 0.0, 0.0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=200", movedR1.toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNullColorCreateNewShape() {
    Shape movedR2 = o1.createNewShape((Color) null);
  }

  @Test
  public void testCreateNewShape3() {
    Shape movedR1 = o1.createNewShape(2, 4);

    assertEquals("Name: C\n"
            + "Type: oval\n"
            + "Min Corner: (2.0, 4.0), X Radius: 2.0, Y Radius: 4.0, Color: (1.0, 0.0, 0.0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=200", movedR1.toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNegativeWidthCreateNewShape() {
    Shape movedR2 = o1.createNewShape(-1,
            2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNegativeHeightCreateNewShape() {
    Shape movedR2 = o1.createNewShape(1,
            -2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNegativeWidthAndHeightCreateNewShape() {
    Shape movedR2 = o1.createNewShape(-1, -2);
  }

  @Test
  public void testCopy() {
    Shape copiedO1 = o1.copy();
    assertEquals(o1.getColor(), copiedO1.getColor());
    assertEquals(o1.getPosition(), copiedO1.getPosition());
    assertEquals(o1.getName(), copiedO1.getName());
  }

  @Test
  public void testToString() {
    assertEquals("Name: C\n" + "Type: oval\n"
                    + "Min Corner: (2.0, 4.0), X Radius: 3.0, Y Radius: 4.0, Color: "
                    + "(1.0, 0.0, 0.0)\n"
                    + "Appears at t=1\n"
                    + "Disappears at t=200", o1.toString());

    assertEquals("Name: C1\n"
            + "Type: oval\n"
            + "Min Corner: (12.0, 33.3), X Radius: 1.8, Y Radius: 55.9, Color: (0.0, 1.0, 0.0)\n"
            + "Appears at t=20\n"
            + "Disappears at t=300", o2.toString());
  }

  @Test
  public void testGetColor() {
    assertEquals(Color.red, o1.getColor());
    assertEquals(Color.green, o2.getColor());
  }

  @Test
  public void testGetName() {
    assertEquals("C", o1.getName());
    assertEquals("C1", o2.getName());
  }

  @Test
  public void testGetType() {
    assertEquals(NameOfShape.C, o1.getType());
    assertEquals(NameOfShape.C, o2.getType());
  }

  @Test
  public void testGetPosition() {
    assertEquals(new Point2D.Double(2, 4), o1.getPosition());
    assertEquals(new Point2D.Double(12, 33.3), o2.getPosition());
  }

  @Test
  public void testGetAppearTime() {
    assertEquals(1, o1.getAppearTime());
    assertEquals(20, o2.getAppearTime());
  }

  @Test
  public void testGetDisappearTime() {
    assertEquals(200, o1.getDisappearTime());
    assertEquals(300, o2.getDisappearTime());
  }

  @Test
  public void testIsInitialized() {
    assertTrue(o1.isInitialized());
    assertTrue(o2.isInitialized());
    Shape s = new Oval("o", "Oval");
    assertFalse(s.isInitialized());
  }

  @Test
  public void testGetSVGOfShape() {
    assertEquals("<ellipse id=\"C\" cx=\"3.5\" cy=\"6.0\" rx=\"1.5\" ry=\"2.0\" "
            + "fill=\"rgb(255,0,0)\" visibility=\"hidden\">\n"
            + " <animate attributeType=\"xml\" attributeName=\"visibility\" "
            + "to=\"visible\" begin=\"1000.0ms\" dur=\"199000.0ms\"  fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" attributeName=\"visibility\" to=\"hidden\" "
            + "begin=\"200000.0ms\" fill=\"freeze\" />", o1.getSVGOfShape(1));

    assertEquals("<ellipse id=\"C1\" cx=\"12.9\" cy=\"61.25\" rx=\"0.9\" ry=\"27.95\" "
            + "fill=\"rgb(0,255,0)\" visibility=\"hidden\">\n"
            + " <animate attributeType=\"xml\" attributeName=\"visibility\" "
            + "to=\"visible\" begin=\"20000.0ms\" dur=\"280000.0ms\"  fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" attributeName=\"visibility\" to=\"hidden\" "
            + "begin=\"300000.0ms\" fill=\"freeze\" />", o2.getSVGOfShape(1));
  }

  @Test
  public void testGetSVGOfEndTag() {
    assertEquals("</ellipse>", o1.getSVGOfEndTag());
    assertEquals("</ellipse>", o2.getSVGOfEndTag());
  }

  @Test
  public void testSetDisappearTime() {
    o1.setDisappearTime(11);
    assertEquals(11, o1.getDisappearTime());
    o2.setDisappearTime(199);
    assertEquals(199, o2.getDisappearTime());
  }


}