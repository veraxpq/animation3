import org.junit.Before;
import org.junit.Test;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import cs5004.animator.EasyAnimator;
import cs5004.animator.model.Model;
import cs5004.animator.model.ModelImpl;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.SVGViewImpl;
import cs5004.animator.view.TextBasedView;
import cs5004.animator.view.View;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the methods in the SVGViewImpl class.
 */
public class SVGViewImplTest {
  TextBasedView v1;
  TextBasedView v2;

  @Before
  public void setUp() throws FileNotFoundException {
    String filename1 = "smalldemo.txt";
    Readable readable = new FileReader(filename1);
    AnimationBuilder<Model> builder = new ModelImpl.Builder();
    Model model = AnimationReader.parseFile(readable, builder);
    v1 = new SVGViewImpl(model, 1);

    String filename2 = "toh-3.txt";
    Readable readable1 = new FileReader(filename2);
    AnimationBuilder<Model> builder1 = new ModelImpl.Builder();
    Model model1 = AnimationReader.parseFile(readable1, builder1);
    v2 = new SVGViewImpl(model1, 20);
  }



  @Test
  public void testGetDescription() {
    assertEquals("<svg width=\"360\" height=\"360\" viewBox=\"200 70 560 430\" "
                    + "version=\"1.1\"\n"
                    + "xmlns=\"http://www.w3.org/2000/svg\">\n"
                    + "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" "
                    + "fill=\"rgb(255,0,0)\" visibility=\"hidden\">\n"
                    + " <animate attributeType=\"xml\" attributeName=\"visibility\" to=\"visible\" "
                    + "begin=\"1000.0ms\" dur=\"99000.0ms\"  fill=\"freeze\" />\n"
                    + "<animate attributeType=\"xml\" attributeName=\"visibility\" to=\"hidden\" "
                    + "begin=\"100000.0ms\" fill=\"freeze\" /> <animate attributeType=\"xml\" "
                    + "begin=\"10000.0ms\" dur=\"40000.0ms\" attributeName=\"x\" from=\"200.0\" "
                    + "to=\"300.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"40000.0ms\" "
                    + "attributeName=\"y\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"50000.0ms\" dur=\"1000.0ms\" "
                    + "attributeName=\"x\" from=\"300.0\" to=\"300.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"50000.0ms\" dur=\"1000.0ms\" "
                    + "attributeName=\"y\" from=\"300.0\" to=\"300.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"51000.0ms\" dur=\"19000.0ms\" "
                    + "attributeName=\"width\" from=\"50.0\" to=\"25.0\" fill=\"freeze\" /> \n"
                    + " <animate attributeType=\"xml\" begin=\"51000.0ms\" dur=\"19000.0ms\" "
                    + "attributeName=\"height\" from=\"100.0\" to=\"100.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"70000.0ms\" dur=\"30000.0ms\" "
                    + "attributeName=\"x\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"70000.0ms\" dur=\"30000.0ms\" "
                    + "attributeName=\"y\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n"
                    + "</rect>\n"
                    + "<ellipse id=\"C\" cx=\"500.0\" cy=\"100.0\" rx=\"60.0\" ry=\"30.0\" "
                    + "fill=\"rgb(0,0,255)\" visibility=\"hidden\">\n"
                    + " <animate attributeType=\"xml\" attributeName=\"visibility\" to=\"visible\" "
                    + "begin=\"6000.0ms\" dur=\"94000.0ms\"  fill=\"freeze\" />\n"
                    + "<animate attributeType=\"xml\" attributeName=\"visibility\" to=\"hidden\" "
                    + "begin=\"100000.0ms\" fill=\"freeze\" /> <animate attributeType=\"xml\" "
                    + "begin=\"20000.0ms\" dur=\"30000.0ms\" attributeName=\"cx\" from=\"440.0\" "
                    + "to=\"440.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"20000.0ms\" dur=\"30000.0ms\" "
                    + "attributeName=\"cy\" from=\"70.0\" to=\"250.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"50000.0ms\" dur=\"20000.0ms\" "
                    + "attributeName=\"cx\" from=\"440.0\" to=\"440.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"50000.0ms\" dur=\"20000.0ms\" "
                    + "attributeName=\"cy\" from=\"250.0\" to=\"370.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"CSS\" begin=\"50000.0ms\" dur=\"20000.0ms\" "
                    + "attributeName=\"fill\" from=\"rgb(0.0,0.0,255.0)\" to=\""
                    + "rgb(0.0,170.0,85.0)\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"CSS\" begin=\"70000.0ms\" dur=\"10000.0ms\" "
                    + "attributeName=\"fill\" from=\"rgb(0.0,170.0,85.0)\" to=\""
                    + "rgb(0.0,255.0,0.0)\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"80000.0ms\" dur=\"20000.0ms\" "
                    + "attributeName=\"cx\" from=\"440.0\" to=\"440.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"80000.0ms\" dur=\"20000.0ms\" "
                    + "attributeName=\"cy\" from=\"370.0\" to=\"370.0\" fill=\"freeze\" />\n"
                    + "</ellipse>\n"
                    + "</svg>",
            v1.getDescription());

    assertEquals("<svg width=\"410\" height=\"220\" viewBox=\"145 50 555 270\" "
                    + "version=\"1.1\"\n"
                    + "xmlns=\"http://www.w3.org/2000/svg\">\n"
                    + "<rect id=\"disk1\" x=\"190.0\" y=\"180.0\" "
                    + "width=\"20.0\" height=\"30.0\" " + "fill=\"rgb(0,49,90)\" "
                    + "visibility=\"hidden\">\n"
                    + " <animate attributeType=\"xml\" "
                    + "attributeName=\"visibility\" to=\"visible\" "
                    + "begin=\"50.0ms\" dur=\"15050.0ms\"  fill=\"freeze\" />\n"
                    + "<animate attributeType=\"xml\" attributeName=\"visibility\" to=\"hidden\" "
                    + "begin=\"15100.0ms\" fill=\"freeze\" /> <animate attributeType=\"xml\" "
                    + "begin=\"50.0ms\" dur=\"1200.0ms\" attributeName=\"x\" from=\"190.0\" "
                    + "to=\"190.0\" "
                    + "fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"1200.0ms\" "
                    + "attributeName=\"y\" from=\"180.0\" to=\"180.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"1250.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"x\" from=\"190.0\" to=\"190.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"1250.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"y\" from=\"180.0\" to=\"50.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"1750.0ms\" dur=\"50.0ms\" "
                    + "attributeName=\"x\" from=\"190.0\" to=\"190.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"1750.0ms\" dur=\"50.0ms\" "
                    + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"1800.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"x\" from=\"190.0\" to=\"490.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"1800.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"2300.0ms\" dur=\"50.0ms\" "
                    + "attributeName=\"x\" from=\"490.0\" to=\"490.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"2300.0ms\" dur=\"50.0ms\" "
                    + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"2350.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"x\" from=\"490.0\" to=\"490.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"2350.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"y\" from=\"50.0\" to=\"240.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"2850.0ms\" dur=\"1600.0ms\" "
                    + "attributeName=\"x\" from=\"490.0\" to=\"490.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"2850.0ms\" dur=\"1600.0ms\" "
                    + "attributeName=\"y\" from=\"240.0\" to=\"240.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"4450.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"x\" from=\"490.0\" to=\"490.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"4450.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"y\" from=\"240.0\" to=\"50.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"4950.0ms\" dur=\"50.0ms\" "
                    + "attributeName=\"x\" from=\"490.0\" to=\"490.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"4950.0ms\" dur=\"50.0ms\" "
                    + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"5000.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"x\" from=\"490.0\" to=\"340.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"5000.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"5500.0ms\" dur=\"50.0ms\" "
                    + "attributeName=\"x\" from=\"340.0\" to=\"340.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"5500.0ms\" dur=\"50.0ms\" "
                    + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"5550.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"x\" from=\"340.0\" to=\"340.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"5550.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"y\" from=\"50.0\" to=\"210.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"6050.0ms\" dur=\"1600.0ms\" "
                    + "attributeName=\"x\" from=\"340.0\" to=\"340.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"6050.0ms\" dur=\"1600.0ms\" "
                    + "attributeName=\"y\" from=\"210.0\" to=\"210.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"7650.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"x\" from=\"340.0\" to=\"340.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"7650.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"y\" from=\"210.0\" to=\"50.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"8150.0ms\" dur=\"50.0ms\" "
                    + "attributeName=\"x\" from=\"340.0\" to=\"340.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"8150.0ms\" dur=\"50.0ms\" "
                    + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"8200.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"x\" from=\"340.0\" to=\"190.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"8200.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"8700.0ms\" dur=\"50.0ms\" "
                    + "attributeName=\"x\" from=\"190.0\" to=\"190.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"8700.0ms\" dur=\"50.0ms\" "
                    + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"8750.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"x\" from=\"190.0\" to=\"190.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"8750.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"y\" from=\"50.0\" to=\"240.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"9250.0ms\" "
                    + "dur=\"1600.0ms\" attributeName=\"x\" from=\"190.0\" to=\"190.0\" "
                    + "fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"9250.0ms\" dur=\"1600.0ms\" "
                    + "attributeName=\"y\" from=\"240.0\" to=\"240.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"10850.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"x\" from=\"190.0\" to=\"190.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"10850.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"y\" from=\"240.0\" to=\"50.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"11350.0ms\" dur=\"50.0ms\" "
                    + "attributeName=\"x\" from=\"190.0\" to=\"190.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"11350.0ms\" dur=\"50.0ms\" "
                    + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"11400.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"x\" from=\"190.0\" to=\"490.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"11400.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"11900.0ms\" dur=\"50.0ms\" "
                    + "attributeName=\"x\" from=\"490.0\" to=\"490.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"11900.0ms\" dur=\"50.0ms\" "
                    + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"11950.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"x\" from=\"490.0\" to=\"490.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"11950.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"y\" from=\"50.0\" to=\"180.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"CSS\" begin=\"12450.0ms\" dur=\"400.0ms\" "
                    + "attributeName=\"fill\" from=\"rgb(0.0,49.0,90.0)\" to=\""
                    + "rgb(0.0,255.0,0.0)\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"12850.0ms\" dur=\"2250.0ms\" "
                    + "attributeName=\"x\" from=\"490.0\" to=\"490.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"12850.0ms\" dur=\"2250.0ms\" "
                    + "attributeName=\"y\" from=\"180.0\" to=\"180.0\" fill=\"freeze\" />\n"
                    + "</rect>\n"
                    + "<rect id=\"disk2\" x=\"167.0\" y=\"210.0\" width=\"65.0\" "
                    + "height=\"30.0\" fill=\"rgb(6,247,41)\" visibility=\"hidden\">\n"
                    + " <animate attributeType=\"xml\" attributeName=\"visibility\" to=\"visible\""
                    + " begin=\"50.0ms\" dur=\"15050.0ms\"  fill=\"freeze\" />\n"
                    + "<animate attributeType=\"xml\" attributeName=\"visibility\" to=\"hidden\" "
                    + "begin=\"15100.0ms\" fill=\"freeze\" /> <animate attributeType=\"xml\" "
                    + "begin=\"50.0ms\" dur=\"2800.0ms\" attributeName=\"x\" from=\"167.0\" "
                    + "to=\"167.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"2800.0ms\" "
                    + "attributeName=\"y\" from=\"210.0\" to=\"210.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"2850.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"x\" from=\"167.0\" to=\"167.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"2850.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"y\" from=\"210.0\" to=\"50.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"3350.0ms\" dur=\"50.0ms\" "
                    + "attributeName=\"x\" from=\"167.0\" to=\"167.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"3350.0ms\" dur=\"50.0ms\" "
                    + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"3400.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"x\" from=\"167.0\" to=\"317.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"3400.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"3900.0ms\" dur=\"50.0ms\" "
                    + "attributeName=\"x\" from=\"317.0\" to=\"317.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"3900.0ms\" dur=\"50.0ms\" "
                    + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"3950.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"x\" from=\"317.0\" to=\"317.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"3950.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"y\" from=\"50.0\" to=\"240.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"4450.0ms\" dur=\"4800.0ms\" "
                    + "attributeName=\"x\" from=\"317.0\" to=\"317.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"4450.0ms\" dur=\"4800.0ms\" "
                    + "attributeName=\"y\" from=\"240.0\" to=\"240.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"9250.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"x\" from=\"317.0\" to=\"317.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"9250.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"y\" from=\"240.0\" to=\"50.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"9750.0ms\" dur=\"50.0ms\" "
                    + "attributeName=\"x\" from=\"317.0\" to=\"317.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"9750.0ms\" dur=\"50.0ms\" "
                    + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"9800.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"x\" from=\"317.0\" to=\"467.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"9800.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"10300.0ms\" dur=\"50.0ms\" "
                    + "attributeName=\"x\" from=\"467.0\" to=\"467.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"10300.0ms\" dur=\"50.0ms\" "
                    + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"10350.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"x\" from=\"467.0\" to=\"467.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"10350.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"y\" from=\"50.0\" to=\"210.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"CSS\" begin=\"10850.0ms\" dur=\"400.0ms\" "
                    + "attributeName=\"fill\" from=\"rgb(6.0,247.0,41.0)\" to=\""
                    + "rgb(0.0,255.0,0.0)\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"11250.0ms\" dur=\"3850.0ms\" "
                    + "attributeName=\"x\" from=\"467.0\" to=\"467.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"11250.0ms\" dur=\"3850.0ms\" "
                    + "attributeName=\"y\" from=\"210.0\" to=\"210.0\" fill=\"freeze\" />\n"
                    + "</rect>\n"
                    + "<rect id=\"disk3\" x=\"145.0\" y=\"240.0\" width=\"110.0\" height=\"30.0\" "
                    + "fill=\"rgb(11,45,175)\" visibility=\"hidden\">\n"
                    + " <animate attributeType=\"xml\" attributeName=\"visibility\" to=\"visible\" "
                    + "begin=\"50.0ms\" dur=\"15050.0ms\"  fill=\"freeze\" />\n"
                    + "<animate attributeType=\"xml\" attributeName=\"visibility\" to=\"hidden\""
                    + " begin=\"15100.0ms\" fill=\"freeze\" /> <animate attributeType=\"xml\""
                    + " begin=\"50.0ms\" dur=\"6000.0ms\" attributeName=\"x\" from=\"145.0\" "
                    + "to=\"145.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"6000.0ms\" "
                    + "attributeName=\"y\" from=\"240.0\" to=\"240.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"6050.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"x\" from=\"145.0\" to=\"145.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"6050.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"y\" from=\"240.0\" to=\"50.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"6550.0ms\" dur=\"50.0ms\" "
                    + "attributeName=\"x\" from=\"145.0\" to=\"145.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"6550.0ms\" dur=\"50.0ms\" "
                    + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"6600.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"x\" from=\"145.0\" to=\"445.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"6600.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"7100.0ms\" dur=\"50.0ms\" "
                    + "attributeName=\"x\" from=\"445.0\" to=\"445.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"7100.0ms\" dur=\"50.0ms\" "
                    + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"7150.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"x\" from=\"445.0\" to=\"445.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"7150.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"y\" from=\"50.0\" to=\"240.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"CSS\" begin=\"7650.0ms\" dur=\""
                    + "400.0000000000009ms\" "
                    + "attributeName=\"fill\" from=\"rgb(11.0,45.0,175.0)\" to=\""
                    + "rgb(0.0,255.0,0.0)\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"8050.000000000001ms\" "
                    + "dur=\"7049.999999999999ms\" attributeName=\"x\" from=\"445.0\" "
                    + "to=\"445.0\" fill=\"freeze\" />\n"
                    + " <animate attributeType=\"xml\" begin=\"8050.000000000001ms\" "
                    + "dur=\"7049.999999999999ms\" attributeName=\"y\" from=\"240.0\" "
                    + "to=\"240.0\" fill=\"freeze\" />\n"
                    + "</rect>\n"
                    + "</svg>",
            v2.getDescription());
  }

  @Test (expected = FileNotFoundException.class)
  public void setUpNonFile() throws FileNotFoundException {
    String filename1 = "nosuchfile.txt";
    Readable readable = new FileReader(filename1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testIllegalFileArgs() throws IOException, InterruptedException {
    String str = "-in nosuchfile.txt -view text -out text-transcript.txt";
    String[] args = str.split(" ");
    EasyAnimator.main(args);

    BufferedReader bufferedReader = null;
    bufferedReader = new BufferedReader(new FileReader("text-transcript.txt"));
    StringBuilder stringBuilder = new StringBuilder();
    String line = bufferedReader.readLine();

    while (line != null) {
      stringBuilder.append(line);
      stringBuilder.append("\n");
      line = bufferedReader.readLine();
    }
  }

  @Test (expected = IllegalArgumentException.class)
  public void testIllegalView() throws IOException, InterruptedException {
    String str = "-in toh-3.txt -view texts -out text-transcript.txt";
    String[] args = str.split(" ");
    EasyAnimator.main(args);

    BufferedReader bufferedReader = null;
    bufferedReader = new BufferedReader(new FileReader("text-transcript.txt"));
    StringBuilder stringBuilder = new StringBuilder();
    String line = bufferedReader.readLine();

    while (line != null) {
      stringBuilder.append(line);
      stringBuilder.append("\n");
      line = bufferedReader.readLine();
    }
  }

  @Test (expected = IllegalArgumentException.class)
  public void testIllegalSpeed() throws IOException, InterruptedException {
    String str = "-in toh-3.txt -view text -out text-transcript.txt -speed -2";
    String[] args = str.split(" ");
    EasyAnimator.main(args);

    BufferedReader bufferedReader = null;
    bufferedReader = new BufferedReader(new FileReader("text-transcript.txt"));
    StringBuilder stringBuilder = new StringBuilder();
    String line = bufferedReader.readLine();

    while (line != null) {
      stringBuilder.append(line);
      stringBuilder.append("\n");
      line = bufferedReader.readLine();
    }
  }

  @Test (expected = IllegalArgumentException.class)
  public void testIllegalSetUp1() {
    View v = new SVGViewImpl(null, 3);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testIllegalSetUp2() {
    View v = new SVGViewImpl(new ModelImpl(), -2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testIllegalSetUp3() {
    View v = new SVGViewImpl(null, -2);
  }



}