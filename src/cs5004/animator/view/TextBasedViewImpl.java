package cs5004.animator.view;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;


import cs5004.animator.model.Model;
import cs5004.animator.model.Shape;


/**
 * This class represents the implementation of the TextBasedView class.
 */
public class TextBasedViewImpl implements TextBasedView {
  private final Model model;

  /**
   * Constructs the TextBasedViewImpl object and initialized it with the given speed.
   *
   * @param model the given model
   * @param speed the given speed
   */
  public TextBasedViewImpl(Model model, double speed) {
    if (model == null || speed <= 0) {
      throw new IllegalArgumentException("Invalid file name");
    }
    this.model = model;
  }

  /**
   * Displays the given file into the current view.
   *
   * @param fileName the given file name
   */
  @Override
  public void currentView(String fileName) {
    if (fileName == null || fileName.isBlank()) {
      throw new IllegalArgumentException("Invalid file name");
    }
    String description = this.getDescription();
    try {
      BufferedWriter fileWriter;
      if (fileName.equals("System.out")) {
        fileWriter = new BufferedWriter(new OutputStreamWriter(System.out));
      } else {
        File file = new File(fileName);
        fileWriter = new BufferedWriter(new FileWriter(file));
      }
      fileWriter.write(description);
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Displays the shape list into the current view.
   *
   * @param shapeList the given shape list
   */
  @Override
  public void currentView(List<Shape> shapeList) {
    return;
  }

  /**
   * Get the description of the object.
   *
   * @return the description of the object
   */
  @Override
  public String getDescription() {
    // TXT output file doesn't need speed
    return model.toString();
  }





}



