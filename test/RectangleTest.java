import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.awt.geom.Point2D;

import cs5004.animator.model.NameOfShape;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Shape;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * This class tests the methods in the Rectangle class.
 */
public class RectangleTest {
  private Shape r1;
  private Shape r2;
  private Shape r3;
  private Shape r4;
  private Shape r5;

  @Before
  public void setUp() {
    r1 = new Rectangle(new Point2D.Double(0, 0),
            Color.black, 3, 4, NameOfShape.R, "R", 1, 10);
    r2 = new Rectangle(new Point2D.Double(11.2, 28.9),
            Color.pink, 12.6, 6.9, NameOfShape.R, "R1",
            10, 100);
    r3 = new Rectangle(new Point2D.Double(0, 0),
            Color.black, 0, 4, NameOfShape.R, "R", 1, 20);
    r4 = new Rectangle(new Point2D.Double(0, 0),
            Color.black, 3, 0, NameOfShape.R, "R", 1, 200);
    r5 = new Rectangle(new Point2D.Double(0, 0),
            Color.black, 0, 0, NameOfShape.R, "R", 1, 200);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullPoint() {
    r2 = new Rectangle(null,
            Color.black, 3, 4, NameOfShape.R, "R", 1, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullColor() {
    r2 = new Rectangle(new Point2D.Double(0, 0),
            null, 3, 4, NameOfShape.R, "R", 1, 200);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullShape() {
    r2 = new Rectangle(new Point2D.Double(0, 0),
            Color.black, 3, 4, null, "R", 1, 200);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeWidth() {
    r2 = new Rectangle(new Point2D.Double(0, 0),
            Color.black, -3, 4, NameOfShape.R, "R", 1, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHeight() {
    r2 = new Rectangle(new Point2D.Double(0, 0),
            Color.black, 3, -4, NameOfShape.R, "R", 1, 200);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeAppearTime() {
    r2 = new Rectangle(new Point2D.Double(0, 0),
            Color.black, 3, 0, NameOfShape.R, "R", -1, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeDisappearTime() {
    r2 = new Rectangle(new Point2D.Double(0, 0),
            Color.black, 3, 0, NameOfShape.R, "R", 1, -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeAppearAndDisappearTime() {
    r2 = new Rectangle(new Point2D.Double(0, 0),
            Color.black, 3, 0, NameOfShape.R, "R", -1, -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalDisappearTime() {
    r2 = new Rectangle(new Point2D.Double(0, 0),
            Color.black, 3, 0, NameOfShape.R, "R", 100, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullName() {
    r2 = new Rectangle(new Point2D.Double(0, 0),
            Color.black, 3, 0, NameOfShape.R, null, 1, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testZeroName() {
    r2 = new Rectangle(new Point2D.Double(0, 0),
            Color.black, 3, 0, NameOfShape.R, "", 1, 200);
  }

  @Test
  public void testSetUp() {
    assertNotNull(r1);
    assertNotNull(r2);
    assertNotNull(r3);
    assertNotNull(r4);
    assertNotNull(r5);
  }

  @Test
  public void testCreateNewShape() {
    Shape movedR1 = r1.createNewShape(new Point2D.Double(3, 4));

    assertEquals("Name: R\n"
            + "Type: rectangle\n"
            + "Min Corner: (3.0, 4.0), Width: 3.0, Height: 4.0, Color: (0.0, 0.0, 0.0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=10", movedR1.toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNullPointCreateNewShape() {
    Shape movedR2 = r1.createNewShape((Point2D) null);
  }

  @Test
  public void testCreateNewShape2() {
    Shape movedR1 = r1.createNewShape(Color.black);

    assertEquals("Name: R\n"
            + "Type: rectangle\n"
            + "Min Corner: (0.0, 0.0), Width: 3.0, Height: 4.0, Color: (0.0, 0.0, 0.0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=10", movedR1.toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNullColorCreateNewShape() {
    Shape movedR2 = r1.createNewShape((Color) null);
  }

  @Test
  public void testCreateNewShape3() {
    Shape movedR1 = r1.createNewShape(2, 4);

    assertEquals("Name: R\n"
            + "Type: rectangle\n"
            + "Min Corner: (0.0, 0.0), Width: 2.0, Height: 4.0, Color: (0.0, 0.0, 0.0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=10", movedR1.toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNegativeWidthCreateNewShape() {
    Shape movedR2 = r1.createNewShape(-1, 2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNegativeHeightCreateNewShape() {
    Shape movedR2 = r1.createNewShape(1, -2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNegativeWidthAndHeightCreateNewShape() {
    Shape movedR2 = r1.createNewShape(-1, -2);
  }

  @Test
  public void testCopy() {
    Shape copiedR1 = r1.copy();
    assertEquals(Color.black, copiedR1.getColor());
    assertEquals(r1.getPosition(), copiedR1.getPosition());
    assertEquals(r1.getName(), copiedR1.getName());
  }

  @Test
  public void testToString() {
    assertEquals("Name: R\n"
            + "Type: rectangle\n"
            + "Min Corner: (0.0, 0.0), Width: 3.0, Height: 4.0, Color: (0.0, 0.0, 0.0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=10", r1.toString());
    assertEquals("Name: R1\n"
            + "Type: rectangle\n"
            + "Min Corner: (11.2, 28.9), Width: 12.6, Height: 6.9, Color: (1.0, 0.0, 0.0)\n"
            + "Appears at t=10\n"
            + "Disappears at t=100", r2.toString());
  }

  @Test
  public void testGetColor() {
    assertEquals(Color.black, r1.getColor());
    assertEquals(Color.pink, r2.getColor());
  }

  @Test
  public void testGetName() {
    assertEquals("R", r1.getName());
    assertEquals("R1", r2.getName());
  }

  @Test
  public void testGetType() {
    assertEquals(NameOfShape.R, r1.getType());
    assertEquals(NameOfShape.R, r1.getType());
  }

  @Test
  public void testGetPosition() {
    assertEquals(new Point2D.Double(0, 0), r1.getPosition());
    assertEquals(new Point2D.Double(11.2, 28.9), r2.getPosition());
  }

  @Test
  public void testGetAppearTime() {
    assertEquals(1, r1.getAppearTime());
    assertEquals(10, r2.getAppearTime());
  }

  @Test
  public void testGetDisappearTime() {
    assertEquals(10, r1.getDisappearTime());
    assertEquals(100, r2.getDisappearTime());
  }

  @Test
  public void testIsInitialized() {
    assertTrue(r1.isInitialized());
    assertTrue(r2.isInitialized());
    Shape s = new Rectangle("RR", "Rectangle");
    assertFalse(s.isInitialized());
  }

  @Test
  public void testGetSVGOfShape() {
    assertEquals("<rect id=\"R\" x=\"0.0\" y=\"0.0\" width=\"3.0\" height=\"4.0\" "
                    + "fill=\"rgb(0,0,0)\" visibility=\"hidden\">\n"
                    + " <animate attributeType=\"xml\" attributeName=\"visibility\" "
                    + "to=\"visible\" begin=\"1000.0ms\" dur=\"9000.0ms\"  fill=\"freeze\" />\n"
                    + "<animate attributeType=\"xml\" attributeName=\"visibility\" to=\""
                    + "hidden\" begin=\"10000.0ms\" fill=\"freeze\" />",
            r1.getSVGOfShape(1));
    // svg tag changes when speed changes
    assertEquals("<rect id=\"R\" x=\"0.0\" y=\"0.0\" width=\"3.0\" height=\"4.0\" "
                    + "fill=\"rgb(0,0,0)\" visibility=\"hidden\">\n"
                    + " <animate attributeType=\"xml\" attributeName=\"visibility\" to=\""
                    + "visible\" begin=\"500.0ms\" dur=\"4500.0ms\"  fill=\"freeze\" />\n"
                    + "<animate attributeType=\"xml\" attributeName=\"visibility\" to=\"hidden\""
                    + " begin=\"5000.0ms\" fill=\"freeze\" />",
            r1.getSVGOfShape(2));
  }

  @Test
  public void testGetSVGOfEndTag() {
    assertEquals("</rect>", r1.getSVGOfEndTag());
    assertEquals("</rect>", r2.getSVGOfEndTag());
  }

  @Test
  public void testSetDisappearTime() {
    r1.setDisappearTime(116);
    assertEquals(116, r1.getDisappearTime());
    r2.setDisappearTime(9);
    assertEquals(9, r2.getDisappearTime());
  }
}