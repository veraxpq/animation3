import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.awt.geom.Point2D;

import cs5004.animator.model.Animation;
import cs5004.animator.model.Move;
import cs5004.animator.model.NameOfShape;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Shape;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * This class tests the methods in the Move class.
 */
public class MoveTest {
  Animation c1;
  Animation c2;
  Shape s1;

  @Before
  public void setUp() {
    s1 = new Oval(new Point2D.Double(2, 0), Color.black,
            3, 4, NameOfShape.R, "R", 1, 200);
    c1 = new Move(5, 10, s1.getPosition(),
            new Point2D.Double(6.0, 9.0), NameOfShape.R);
    c2 = new Move(35, 90, s1.getPosition(),
            new Point2D.Double(2.0, 4.1), NameOfShape.R);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullOriginalPointAnimation() {
    Animation c2 = new Move(0, 3, null,
            new Point2D.Double(6.0, 9.0), NameOfShape.R);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullDesPointAnimation() {
    Animation c2 = new Move(0, 3,
            new Point2D.Double(6.0, 9.0), null, NameOfShape.R);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeStartTimeAnimation() {
    Animation c2 = new Move(-3, 3, new Point2D.Double(2.0, 3.0),
            new Point2D.Double(6.0, 9.0), NameOfShape.R);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeEndTimeAnimation() {
    Animation c2 = new Move(3, -3, new Point2D.Double(2.0, 3.0),
            new Point2D.Double(6.0, 9.0), NameOfShape.R);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalEndTimeAnimation() {
    Animation c2 = new Move(3, 1, new Point2D.Double(2.0, 3.0),
            new Point2D.Double(6.0, 9.0), NameOfShape.R);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeStartAndEndTimeAnimation() {
    Animation c2 = new Move(-3, -3, new Point2D.Double(2.0, 3.0),
            new Point2D.Double(6.0, 9.0), NameOfShape.R);
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

    Shape s3 = c1.play(s1, 7);
    assertEquals("Point2D.Double[3.5999999999999996, 3.6]", s3.getPosition().toString());

    Shape s5 = c1.play(s1, 10);
    assertEquals("Point2D.Double[6.0, 9.0]", s5.getPosition().toString());

    Shape s6 = c1.play(s1, 11);
    assertEquals("Point2D.Double[6.0, 9.0]", s6.getPosition().toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeTimeShapePlay() {
    c1.play(s1, -2);
  }

  @Test
  public void testToString() {
    assertEquals(" moves from (2.0, 0.0) to (6.0, 9.0) from t=5 to t=10", c1.toString());
    assertEquals(" moves from (2.0, 0.0) to (2.0, 4.1) from t=35 to t=90", c2.toString());
  }

  @Test
  public void testGetStartTime() {
    assertEquals(5, c1.getStartTime());
    assertEquals(35, c2.getStartTime());

  }

  @Test
  public void testGetEndTime() {
    assertEquals(10, c1.getEndTime());
    assertEquals(90, c2.getEndTime());

  }

  @Test
  public void testGetSVGOfAnimation() {
    assertEquals(" <animate attributeType=\"xml\" begin=\"5000.0ms\" "
                    + "dur=\"5000.0ms\" attributeName=\"x\" from=\"2.0\" to=\"6.0\""
                    + " fill=\"freeze\" />\n" + " <animate attributeType=\"xml\" "
                    + "begin=\"5000.0ms\" dur=\"5000.0ms\" attributeName=\"y\" "
                    + "from=\"0.0\" to=\"9.0\" fill=\"freeze\" />\n",
        c1.getSVGOfAnimation(1));
    assertEquals(" <animate attributeType=\"xml\" begin=\"17500.0ms\" "
                    + "dur=\"27500.0ms\" attributeName=\"x\" from=\"2.0\" to=\"2.0\" "
                    + "fill=\"freeze\" />\n" + " <animate attributeType=\"xml\" "
                    + "begin=\"17500.0ms\" dur=\"27500.0ms\" attributeName=\"y\" "
                    + "from=\"0.0\" to=\"4.1\" fill=\"freeze\" />\n",
        c2.getSVGOfAnimation(2));
  }
}