import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.awt.geom.Point2D;

import cs5004.animator.model.Animation;
import cs5004.animator.model.ChangeColor;
import cs5004.animator.model.NameOfShape;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Shape;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * This class tests the methods in the ChangeColor class.
 */
public class ChangeColorTest {
  Animation c1;
  Animation c2;
  Shape s1;

  @Before
  public void setUp() {
    s1 = new Oval(new Point2D.Double(2, 0), Color.black,
            3, 4, NameOfShape.R, "R", 1, 200);
    c1 = new ChangeColor(5, 10, Color.black, Color.blue);
    c2 = new ChangeColor(15, 100, Color.blue, Color.yellow);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNullShapeAnimation() {
    Animation c2 = new ChangeColor(0,
            3, null, Color.white);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNegativeStartTimeAnimation() {
    Animation c2 = new ChangeColor(-3, 3, Color.black, Color.white);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNegativeEndTimeTimeAnimation() {
    Animation c2 = new ChangeColor(3, -3, Color.black, Color.white);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNegativeStartAndEndTimeTimeAnimation() {
    Animation c2 = new ChangeColor(-3, -3, Color.black, Color.white);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testIllegalEndTimeTimeAnimation() {
    Animation c2 = new ChangeColor(3, 1, Color.black, Color.white);
  }

  @Test
  public void testSetUp() {
    assertNotNull(c1);
  }

  @Test
  public void play() {
    Shape s2 = c1.play(s1, 2);
    assertNull(s2);

    Shape s4 = c1.play(s1, 5);
    assertNull(s4);

    // color is changing with time goes by
    Shape s7 = c1.play(s1, 6);
    assertEquals("java.awt.Color[r=0,g=0,b=51]", s7.getColor().toString());
    Shape s3 = c1.play(s1, 7);
    assertEquals("java.awt.Color[r=0,g=0,b=102]", s3.getColor().toString());

    // at the end, color changes to blue
    Shape s5 = c1.play(s1, 10);
    assertEquals(Color.black, s5.getColor());

    // after the color change is done, it changes back to the original color
    Shape s6 = c1.play(s1, 11);
    assertEquals(Color.black, s6.getColor());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNegativeTimeShapePlay() {
    c1.play(s1, -2);
  }

  @Test
  public void testToString() {
    assertEquals(" changes color from (0.0,0.0,0.0) to (0.0,0.0,1.0) from t=5 to t=10",
            c1.toString());
    assertEquals(" changes color from (0.0,0.0,1.0) to (1.0,1.0,0.0) from t=15 to t=100",
            c2.toString());
  }

  @Test
  public void testGetStartTime() {
    assertEquals(5, c1.getStartTime());
    assertEquals(15, c2.getStartTime());

  }

  @Test
  public void testGetEndTime() {
    assertEquals(10, c1.getEndTime());
    assertEquals(100, c2.getEndTime());

  }

  @Test
  public void testGetSVGOfAnimation() {
    assertEquals(" <animate attributeType=\"CSS\" begin=\"5000.0ms\" dur=\"5000.0ms\" "
                + "attributeName=\"fill\" from=\"rgb(0.0,0.0,0.0)\" to=\"rgb(0.0,0.0,255.0)\""
                + " fill=\"freeze\" />\n",
        c1.getSVGOfAnimation(1));
    assertEquals(" <animate attributeType=\"CSS\" begin=\"7500.0ms\" dur=\"42500.0ms\" "
                    + "attributeName=\"fill\" from=\"rgb(0.0,0.0,255.0)\" to=\"rgb"
                    + "(255.0,255.0,0.0)\" " + "fill=\"freeze\" />\n",
        c2.getSVGOfAnimation(2));
  }
}