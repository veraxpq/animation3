package cs5004.animator.util;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This class contains static methods that are common to a few classes, eg. EasyAnimator
 */
public class Utils {

  /**
   * Display the error message on the screen if the command lines are not valid.
   *
   **/
  public static void showErrorMessage() {
    JFrame frame = new JFrame();
    frame.setSize(100, 100);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JOptionPane.showMessageDialog(frame, "the command-line arguments are not valid",
            "Error Message", JOptionPane.ERROR_MESSAGE);
    throw new IllegalArgumentException("the command-line arguments are not valid\",\n" +
            "            \"Error Message");
  }

}
