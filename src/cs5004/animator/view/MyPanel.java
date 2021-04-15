package cs5004.animator.view;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.Scrollable;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import cs5004.animator.model.NameOfShape;
import cs5004.animator.model.Shape;

/**
 * This class represents an animation panel to be included in our GraphicalViewImpl class.
 * This panel takes in the model in order to call the appropriate "getter" functions are
 * retrieve necessary data.
 */
class MyPanel extends JPanel implements Scrollable {
  // a list reading from model
  private List<Shape> shapeList;
  private int offsetX = 0;
  private int offsetY = 0;

  /**
   * To construct a MyPanel object. It takes in the shape list from model in order to retrieve the
   * current data of shapes.
   *
   * @param x the x coordinate of panel
   * @param y the y coordinate of panel
   * @param panelWidth the panel width
   * @param panelWidth the panel height
   * @param shapeList the shape list from model
   */
  public MyPanel(int x, int y, int panelWidth, int panelHeight, List<Shape> shapeList) {
    super(true);
    this.setPreferredSize(new Dimension(panelWidth, panelHeight));
    this.shapeList = shapeList;
    setBackground(Color.WHITE);
    setOpaque(true);
    setLocation(x, y);
    setBorder(new LineBorder(Color.black, 3));

  }

  /**
   * This method update the shape list of the panel with the given shape list.
   *
   * @param updatedShapeList the given shape list
   */
  public void updateShapeList( List<Shape> updatedShapeList) {
     shapeList = updatedShapeList;
  }

  /**
   * This method paint the given graphics into the panel.
   *
   * @param g the given graphics
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    if (shapeList == null) {
      return;
    }
    for (Shape shape : this.shapeList) {
      if (shape.getType() == NameOfShape.C) {
        g2.setColor(shape.getColor());
        g2.fillOval((int)shape.getPosition().getX() - offsetX,
                (int)shape.getPosition().getY() - offsetY,
                (int)shape.getWidth(), (int)shape.getHeight());
      }
      if (shape.getType() == NameOfShape.R) {
        g2.setColor(shape.getColor());
        g2.fillRect((int)shape.getPosition().getX() - offsetX, (int)shape.getPosition().getY()
                        - offsetY, (int)shape.getWidth(), (int)shape.getHeight());
      }
    }
  }

  /**
   * Get the preferred scrollable view port size.
   *
   * @return the preferred scrollable view port size
   */
  @Override
  public Dimension getPreferredScrollableViewportSize() {
    return new Dimension(850, 650);
  }

  /**
   * Get the scrollable unit increment.
   *
   * @param visibleRect the given rectangle
   * @param orientation the given orientation
   * @param direction the given direction
   * @return
   */
  @Override
  public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
    return 6;
  }

  /**
   * Get the scrollable block increment.
   *
   * @param visibleRect the given rectangle
   * @param orientation the given orientation
   * @param direction the given direction
   * @return the scrollable block increment
   */
  @Override
  public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
    return 20;
  }

  /**
   * Get the scrollable tracks view port width.
   *
   * @return the scrollable tracks view port width.
   */
  @Override
  public boolean getScrollableTracksViewportWidth() {
    return false;
  }

  /**
   * Get the scrollable tracks view port height.
   *
   * @return the scrollable tracks view port height.
   */
  @Override
  public boolean getScrollableTracksViewportHeight() {
    return false;
  }

  /**
   * Modify the off set y.
   *
   * @param offsetY the given offset y
   */
  void setOffsetY(int offsetY) {
    this.offsetY = offsetY;
  }

  /**
   * Modify the offset x.
   *
   * @param offsetX the given offset x
   */
  void setOffsetX(int offsetX) {
    this.offsetX = offsetX;
  }




}
