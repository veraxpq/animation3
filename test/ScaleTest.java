import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.awt.geom.Point2D;

import cs5004.animator.model.Animation;
import cs5004.animator.model.NameOfShape;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Scale;
import cs5004.animator.model.Shape;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * This class tests the methods in the Scale class.
 */
public class ScaleTest {
  Animation c1;
  Animation c2;
  Animation c3;
  Animation c4;
  Animation c5;
  Shape s1;

  @Before
  public void setUp() {
    s1 = new Oval(new Point2D.Double(2, 0), Color.black,
            3, 4, NameOfShape.R, "R", 1, 200);
    c1 = new Scale(5, 10, s1.getWidth(), s1.getHeight(), 6,
            8, s1.getType());
    c2 = new Scale(1, 100, s1.getWidth(), s1.getHeight(), 1,
            10, s1.getType());
    c3 = new Scale(5, 10, s1.getWidth(), s1.getHeight(),
            0, 8, s1.getType());
    c4 = new Scale(5, 10, s1.getWidth(), s1.getHeight(),
            6, 0, s1.getType());
    c5 = new Scale(5, 10, s1.getWidth(), s1.getHeight(),
            0, 0, s1.getType());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testNegativeStartTimeAndEndTimeAnimation() {
    Animation c2 = new Scale(-5, -10, s1.getWidth(), s1.getHeight(),
            6, 8, s1.getType());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeStartTimeAnimation() {
    Animation c2 = new Scale(-5, 10, s1.getWidth(),
            s1.getHeight(), 6, 8, s1.getType());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeEndTimeAnimation() {
    Animation c2 = new Scale(5, -10, s1.getWidth(), s1.getHeight(),
            6, 8, s1.getType());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalEndTimeAnimation() {
    Animation c2 = new Scale(50, 10, s1.getWidth(), s1.getHeight(),
            6, 8, s1.getType());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHeightAndWidthAnimation() {
    Animation c2 = new Scale(5, 10, s1.getWidth(), s1.getHeight(),
            -6, -8, s1.getType());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeWidthAnimation() {
    Animation c2 = new Scale(5, 10, s1.getWidth(), s1.getHeight(),
            -6, 8, s1.getType());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHeightTimeAnimation() {
    Animation c2 = new Scale(5, 10, s1.getWidth(), s1.getHeight(),
            6, -8, s1.getType());
  }

  @Test
  public void testSetUp() {
    assertNotNull(c1);
    assertNotNull(c2);
    assertNotNull(c3);
    assertNotNull(c4);
    assertNotNull(c5);
  }

  @Test
  public void play() {
    Shape s2 = c1.play(s1, 2);
    assertNull(s2);

    Shape s4 = c1.play(s1, 5);
    assertNull(s4);

    Shape s3 = c1.play(s1, 7);
    assertEquals("Name: R\n"
            + "Type: oval\n"
            + "Min Corner: (2.0, 0.0), X Radius: 4.2, Y Radius: 5.6, Color: (0.0, 0.0, 0.0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=200", s3.toString());

    Shape s5 = c1.play(s1, 10);
    assertEquals("Name: R\n"
            + "Type: oval\n"
            + "Min Corner: (2.0, 0.0), X Radius: 6.0, Y Radius: 8.0, Color: (0.0, 0.0, 0.0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=200", s5.toString());

    Shape s6 = c1.play(s1, 11);
    assertEquals("Name: R\n"
            + "Type: oval\n"
            + "Min Corner: (2.0, 0.0), X Radius: 6.0, Y Radius: 8.0, Color: (0.0, 0.0, 0.0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=200", s6.toString());

    Shape s7 = c3.play(s1, 1);
    assertNull(s7);

    Shape s8 = c3.play(s1, 8);
    assertEquals("Name: R\n"
            + "Type: oval\n"
            + "Min Corner: (2.0, 0.0), X Radius: 1.2, Y Radius: 6.4, Color: (0.0, 0.0, 0.0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=200", s8.toString());
    Shape s9 = c3.play(s1, 11);
    assertEquals("Name: R\n"
            + "Type: oval\n"
            + "Min Corner: (2.0, 0.0), X Radius: 0.0, Y Radius: 8.0, Color: (0.0, 0.0, 0.0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=200", s9.toString());
    Shape s10 = c5.play(s1, 11);
    assertEquals("Name: R\n"
            + "Type: oval\n"
            + "Min Corner: (2.0, 0.0), X Radius: 0.0, Y Radius: 0.0, Color: (0.0, 0.0, 0.0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=200", s10.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeTimeShapePlay() {
    c1.play(s1, -2);
  }

  @Test
  public void testToString() {
    assertEquals(" scales from X radius: 3.0, Y radius:4.0 to X radius:6.0,"
            + " Y radius: 8.0 from t=5 to t=10", c1.toString());
    assertEquals(" scales from X radius: 3.0, Y radius:4.0 to X radius:1.0,"
            + " Y radius: 10.0 from t=1 to t=100", c2.toString());

  }

  @Test
  public void testGetStartTime() {
    assertEquals(5, c1.getStartTime());
    assertEquals(1, c2.getStartTime());

  }

  @Test
  public void testGetEndTime() {
    assertEquals(10, c1.getEndTime());
    assertEquals(100, c2.getEndTime());

  }

  @Test
  public void testGetSVGOfAnimation() {
    assertEquals(" <animate attributeType=\"xml\" begin=\"5000.0ms\" dur=\"5000.0ms\" "
                    + "attributeName=\"rx\" from=\"3.0\" to=\"6.0\" fill=\"freeze\" /> \n"
                    + " <animate attributeType=\"xml\" begin=\"5000.0ms\" dur=\"5000.0ms\" "
                    + "attributeName=\"ry\" from=\"4.0\" to=\"8.0\" fill=\"freeze\" />\n",
        c1.getSVGOfAnimation(1));
    assertEquals(" <animate attributeType=\"xml\" begin=\"500.0ms\" dur=\"49500.0ms\""
                    + " attributeName=\"rx\" from=\"3.0\" to=\"1.0\" fill=\"freeze\" /> \n"
                    + " <animate attributeType=\"xml\" begin=\"500.0ms\" dur=\"49500.0ms\" "
                    + "attributeName=\"ry\" from=\"4.0\" to=\"10.0\" fill=\"freeze\" />\n",
        c2.getSVGOfAnimation(2));
  }
}