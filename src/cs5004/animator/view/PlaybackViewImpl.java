package cs5004.animator.view;


import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JScrollBar;
import javax.swing.JPanel;


import cs5004.animator.model.Shape;

public class PlaybackViewImpl extends GraphicalViewImpl {
//  public class PlaybackViewImpl extends JFrame implements View {
  private JButton start;
  private JButton pause;
  private JButton resume;
  private JButton restart;
  private JButton increaseSpeed;
  private JButton decreaseSpeed;
  private MyPanel panel;
  private JToggleButton loopButton;

  public PlaybackViewImpl(int x, int y, int canvasWidth, int canvasHeight) {
    super(x, y, canvasWidth, canvasHeight);
//    super("Animation Viewer");
//    if (canvasWidth < 0 || canvasHeight < 0) {
//      throw new IllegalArgumentException("the input width and height cannot be negative");
//    }
//    BorderLayout borderLayout = new BorderLayout();
//    setLayout(borderLayout);
//
//    // set the frame size
//    setSize(canvasWidth + 100, canvasHeight + 200);
//    setLocation(0, 0);
//    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    // set the panel size
    this.panel = super.getPanel();
//    this.panel = new MyPanel(x, y, canvasWidth, canvasHeight, null);
//    this.getContentPane().add(this.panel, BorderLayout.CENTER);
//    // add the scroll bar
//    JScrollBar jScrollBarWidth = new JScrollBar(JScrollBar.HORIZONTAL, 0, 100,
//            -1 * canvasWidth, canvasWidth);
//    JScrollBar jScrollBarHeight = new JScrollBar(JScrollBar.VERTICAL, 0, 100,
//            -1 * canvasHeight, canvasHeight);

    this.setPanelButtons();

    /**
     * To construct a listenerHeight object, which implements AdjustmentListener Interface,
     * in order to set up the vertical scroll bar.
     *
     */
//    class ListenerHeight implements AdjustmentListener {
//
//      @Override
//      public void adjustmentValueChanged(AdjustmentEvent e) {
//        setOffY(e.getValue());
//      }
//
//    }


    /**
     * To construct a listenerWidth object, which implements AdjustmentListener Interface,
     * in order to set up the horizontal scroll bar.
     *
     */
//    class ListenerWidth implements AdjustmentListener {
//
//      @Override
//      public void adjustmentValueChanged(AdjustmentEvent e) {
//        setOffX(e.getValue());
//      }
//
//    }

//    jScrollBarHeight.addAdjustmentListener(new ListenerHeight());
//    jScrollBarWidth.addAdjustmentListener(new ListenerWidth());
//    this.getContentPane().add(jScrollBarHeight, BorderLayout.LINE_END);
//    this.getContentPane().add(jScrollBarWidth, BorderLayout.PAGE_END);

//    this.setVisible(true);
//    this.add(this.panel);
//    this.panel.setVisible(true);
//    this.pack();

  }

  private JPanel setPanelButtons() {
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    buttonPanel.setPreferredSize(new Dimension(300, 50));
    buttonPanel.setBackground(Color.WHITE);

    // play, pause or restart animation
    this.start = new JButton("Start");
    this.pause = new JButton("Pause");
    this.resume = new JButton("Resume");
    this.restart = new JButton("Restart");
    buttonPanel.add(this.start);
    buttonPanel.add(this.pause);
    buttonPanel.add(this.resume);
    buttonPanel.add(this.restart);

    // increase or decrease animation speed
    this.increaseSpeed = new JButton("Increase Speed");
    this.decreaseSpeed = new JButton("Decrease Speed");
    buttonPanel.add(this.increaseSpeed);
    buttonPanel.add(this.decreaseSpeed);

    // loop animation
    this.loopButton = new JCheckBox("Loop");
    buttonPanel.add(this.loopButton);
    this.getContentPane().add(buttonPanel, BorderLayout.NORTH);

    return buttonPanel;
  }

  /**
   * Give the view an actionListener for the buttons in the view.
   *
   * @param e the event for the button
   * @throws UnsupportedOperationException if the view does not support this functionality
   */

  public void setStart(ActionListener e) {
    this.start.addActionListener(e);
  }

  public void setResume(ActionListener e) {
    this.resume.addActionListener(e);
  }

  public void setRestart(ActionListener e) {
    this.restart.addActionListener(e);
  }

  public void setPause(ActionListener e) {
    this.pause.addActionListener(e);
  }

  public void setIncreaseSpeed(ActionListener e) {
    this.increaseSpeed.addActionListener(e);
  }

  public void setDecreaseSpeed(ActionListener e) {
    this.decreaseSpeed.addActionListener(e);
  }

  public void setLoopButton(ActionListener e) {
    this.loopButton.addActionListener(e);
  }







  @Override
  public void currentView(List<Shape> shapeList) {
    panel.updateShapeList(shapeList);
    this.repaint();
  }

  @Override
  public String getDescription() {
    return null;
  }

  @Override
  public String getViewType() {
    return "playback";
  }


  /**
   * This is a private helper function to set off the panel's width.
   *
   * @param x the given value to set off from canvas height
   */
  void setOffX(int x) {
    this.panel.setOffsetX(x);
  }


  /**
   * This is a private helper function to set off the panel's height.
   *
   * @param y the given value to set off form canvas width
   */
  void setOffY(int y) {
    this.panel.setOffsetY(y);
  }
}
