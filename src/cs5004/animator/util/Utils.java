package cs5004.animator.util;


import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Utils {
  public static void showErrorMessage() {
    JFrame frame = new JFrame();
    frame.setSize(100, 100);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JOptionPane.showMessageDialog(frame, "the command-line arguments are not valid",
            "Error Message", JOptionPane.ERROR_MESSAGE);
  }

}
