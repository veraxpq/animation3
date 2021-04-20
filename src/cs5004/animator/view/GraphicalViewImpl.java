package cs5004.animator.view;


import java.awt.BorderLayout;
import javax.swing.JScrollBar;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.List;


import javax.swing.JFrame;
import cs5004.animator.model.Shape;


/**
 * This class represents a visual view of our EasyAnimator. This class extends JFrame in order to
 * display each frame of the animation properly as the MyPanel refreshes. This class is able to set
 * the frame's title, size. Also, this class is able to set its panel's size, and scroll bars. This
 * class contains methods in order to make the animations visible and refresh the animation for each
 * tick in a given animation.
 */
public class GraphicalViewImpl extends JFrame implements GraphicalView {

  private MyPanel panel;

  /**
   * To construct a GraphicalViewImpl object in order to display animations. The various attributes
   * of the frame are set in this method, and an instance of MyPanel is created to add to the frame.
   * Additionally, a scroll bar is implemented in the panel.
   *
   * @param x            the ticks per second the animation is set to run at
   * @param y            the current model used to represent the animation
   * @param canvasWidth  the width of canvas
   * @param canvasHeight the height of canvas
   */
  public GraphicalViewImpl(int x, int y, int canvasWidth, int canvasHeight) {
    super("Animation Viewer");
    if (canvasWidth < 0 || canvasHeight < 0) {
      throw new IllegalArgumentException("the input width and height cannot be negative");
    }
    BorderLayout borderLayout = new BorderLayout();
    setLayout(borderLayout);

    // set the frame size
    setSize(canvasWidth + 100, canvasHeight + 200);
    setLocation(0, 0);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // set the panel size
    this.panel = new MyPanel(x, y, canvasWidth, canvasHeight, null);
    this.getContentPane().add(this.panel, BorderLayout.CENTER);
    // add the scroll bar
    JScrollBar jScrollBarWidth = new JScrollBar(JScrollBar.HORIZONTAL, 0, 100,
            -1 * canvasWidth, canvasWidth);
    JScrollBar jScrollBarHeight = new JScrollBar(JScrollBar.VERTICAL, 0, 100,
            -1 * canvasHeight, canvasHeight);


    /**
     * To construct a listenerHeight object, which implements AdjustmentListener Interface,
     * in order to set up the vertical scroll bar.
     *
     */
    class ListenerHeight implements AdjustmentListener {

      @Override
      public void adjustmentValueChanged(AdjustmentEvent e) {
        setOffY(e.getValue());
      }

    }


    /**
     * To construct a listenerWidth object, which implements AdjustmentListener Interface,
     * in order to set up the horizontal scroll bar.
     *
     */
    class ListenerWidth implements AdjustmentListener {

      @Override
      public void adjustmentValueChanged(AdjustmentEvent e) {
        setOffX(e.getValue());
      }

    }

    jScrollBarHeight.addAdjustmentListener(new ListenerHeight());
    jScrollBarWidth.addAdjustmentListener(new ListenerWidth());
    this.getContentPane().add(jScrollBarHeight, BorderLayout.LINE_END);
    this.getContentPane().add(jScrollBarWidth, BorderLayout.PAGE_END);

    this.setVisible(true);
    this.add(this.panel);
    this.panel.setVisible(true);
    this.pack();

  }


  /**
   * This method displays the given shape list in the panel.
   *
   * @param shapeList the given shape list.
   */
  @Override
  public void currentView(List<Shape> shapeList) {
    panel.updateShapeList(shapeList);
    this.repaint();
  }

  /**
   * Get the formatted string of the view.
   *
   * @return the formatted string of the view.
   */
  @Override
  public String getDescription() {
    return null;
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
