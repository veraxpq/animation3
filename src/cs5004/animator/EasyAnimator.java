package cs5004.animator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

import cs5004.animator.controller.AnimationController;
import cs5004.animator.controller.GraphicalViewController;
import cs5004.animator.model.Model;
import cs5004.animator.model.ModelImpl;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.util.Utils;
import cs5004.animator.view.GraphicalView;
import cs5004.animator.view.GraphicalViewImpl;
import cs5004.animator.view.SVGViewImpl;
import cs5004.animator.view.TextBasedView;
import cs5004.animator.view.TextBasedViewImpl;

/**
 * This class represents an easy controller of the model.
 */
public final class EasyAnimator {
  /**
   * This is the main method which is the entry point for our program.
   *
   * @param args takes in string arguments into the main
   * @throws FileNotFoundException is thrown if the program can not find the files specified in the
   *                               arguments
   * @throws InterruptedException if the program is interrupted
   */
  public static void main(String[] args) throws FileNotFoundException, InterruptedException {
    String eachInput;
    String inFile = "";
    String viewType = "";
    String outFile = "";
    int speed = 1;
    Appendable output = null;
    AnimationController controller;

    for (int i = 0; i < args.length - 1; i += 2) {
      eachInput = args[i];

      switch (eachInput) {
        case "-in":
          inFile = args[i + 1];
          break;
        case "-view":
          // The options for the view name are "text", "visual" and "svg".
          viewType = args[i + 1];
          if (!((viewType.equals("text") || viewType.equals("visual")
                  || viewType.equals("svg")))) {
            Utils.showErrorMessage();
            return;
          }
          break;
        case "-out":
          outFile = args[i + 1];
          break;
        case "-speed":
          speed = Integer.parseInt(args[i + 1]);
          if (speed < 0) {
            Utils.showErrorMessage();
            return;
          }
          break;
        default:
          Utils.showErrorMessage();
          return;

      }
    }

    if (inFile.equals("") || viewType.equals("")) {
      Utils.showErrorMessage();
    }

    // If 'out' is not indicated by the command-line argument -- then it defaults to 'System.out'
    if (outFile.equals("") || outFile.equals("out")) {
      output = new OutputStreamWriter(System.out);
      outFile = "System.out";
    } else {
      try {
        output = new PrintStream(new File(outFile));
      } catch (IOException e) {
        System.out.println("Error" + e.getMessage());
      }
    }

    inFile = System.getProperty("user.dir") + "/src/starterCode/" + inFile;
    Readable readable = null;
    try {
      readable = new FileReader(inFile);
    } catch (FileNotFoundException e) {
      Utils.showErrorMessage();
      return;

    }
    AnimationBuilder<Model> builder = new ModelImpl.Builder();
    Model model = AnimationReader.parseFile(readable, builder);

    switch (viewType) {
      case "text":
        TextBasedView textBasedView = new TextBasedViewImpl(model, speed);
        textBasedView.currentView(outFile);
        break;
      case "svg":
        TextBasedView svgView = new SVGViewImpl(model, speed);
        svgView.currentView(outFile);
        break;
      case "visual":
        GraphicalView graphicalView = new GraphicalViewImpl(model.getCanvasX(),
                model.getCanvasY(), model.getCanvasWidth(),
                model.getCanvasHeight());

        controller = new GraphicalViewController(model, graphicalView, speed);
        controller.start();
        break;
      default:
        Utils.showErrorMessage();
    }
  }
}