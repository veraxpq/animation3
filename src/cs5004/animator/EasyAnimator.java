package cs5004.animator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

import cs5004.animator.controller.AnimationController;
import cs5004.animator.controller.AnimationControllerImpl;
import cs5004.animator.model.Model;
import cs5004.animator.model.ModelImpl;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.util.Utils;
import cs5004.animator.view.GraphicalViewImpl;
import cs5004.animator.view.PlaybackViewImpl;
import cs5004.animator.view.SVGViewImpl;
import cs5004.animator.view.TextBasedViewImpl;
import cs5004.animator.view.View;

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
    //Appendable output = null;
    AnimationController controller;

    for (int i = 0; i < args.length - 1; i += 2) {
      eachInput = args[i];

      switch (eachInput) {
        case "-in":
          inFile = args[i + 1];
          break;
        case "-view":
          viewType = args[i + 1];
//          if (!((viewType.equals("text") || viewType.equals("visual")
//                  || viewType.equals("svg") || viewType.equals("playback")))) {
//            Utils.showErrorMessage("Invalid view type");
//            return;
//          }
          break;
        case "-out":
          outFile = args[i + 1];
          break;
        case "-speed":
          speed = Integer.parseInt(args[i + 1]);
          if (speed < 0) {
            Utils.showErrorMessage("Speed can't be negative");
            return;
          }
          break;
        default:
          Utils.showErrorMessage("Speed can't be negative");
          return;

      }
    }

    if (inFile.isBlank() || viewType.isBlank()) {
      Utils.showErrorMessage("Input file or view type can't be blank");
    }

    // If 'out' is not indicated by the command-line argument -- then it defaults to 'System.out'
    if (outFile.equals("") || outFile.equals("out")) {
      //output = new OutputStreamWriter(System.out);
      outFile = "System.out";
    }

//    else {
//      try {
//        output = new PrintStream(new File(outFile));
//      } catch (IOException e) {
//        System.out.println("Error" + e.getMessage());
//      }
//    }

    Readable readable = null;
    try {
      readable = new FileReader(inFile);
    } catch (FileNotFoundException e) {
      Utils.showErrorMessage("Can't read from the file");
      return;

    }
    AnimationBuilder<Model> builder = new ModelImpl.Builder();
    Model model = AnimationReader.parseFile(readable, builder);

//    switch (viewType) {
//      case "text":
//        View textBasedView = new TextBasedViewImpl(model, speed);
//        //textBasedView.currentView(outFile);
//        controller = new AnimationControllerImpl(model, textBasedView, speed, outFile);
//        controller.start();
//        break;
//      case "svg":
//        View svgView = new SVGViewImpl(model, speed);
//        //svgView.currentView(outFile);
//        controller = new AnimationControllerImpl(model, svgView, speed, outFile);
//        controller.start();
//        break;
//      case "visual":
//        View graphicalView = new GraphicalViewImpl(model.getCanvasX(),
//                model.getCanvasY(), model.getCanvasWidth(),
//                model.getCanvasHeight());
////        controller = new GraphicalViewController(model, graphicalView, speed);
////        controller.start();
//        controller = new AnimationControllerImpl(model, graphicalView, speed, outFile);
//        controller.start();
//        break;
//      case "playback":
//        View playbackViewImpl = new PlaybackViewImpl(model.getCanvasX(),
//                model.getCanvasY(), model.getCanvasWidth(),
//                model.getCanvasHeight());
////        controller = new PlaybackViewController(model, playbackViewImpl, speed);
////        controller.start();
//        controller = new AnimationControllerImpl(model, playbackViewImpl, speed, outFile);
//        controller.start();
//        break;
//      default:
//        Utils.showErrorMessage("Invalid view type");
//    }

    try{
      if (viewType.equalsIgnoreCase("text")) {
        View textBasedView = new TextBasedViewImpl(model, speed);
        //textBasedView.currentView(outFile);
        controller = new AnimationControllerImpl(model, textBasedView, speed, outFile);
        controller.start();
      }
      else if (viewType.equalsIgnoreCase("svg")) {
        View svgView = new SVGViewImpl(model, speed);
        //svgView.currentView(outFile);
        controller = new AnimationControllerImpl(model, svgView, speed, outFile);
        controller.start();
      }
      else if (viewType.equalsIgnoreCase("visual")) {
        View graphicalView = new GraphicalViewImpl(model.getCanvasX(),
                model.getCanvasY(), model.getCanvasWidth(),
                model.getCanvasHeight());
//        controller = new GraphicalViewController(model, graphicalView, speed);
//        controller.start();
        controller = new AnimationControllerImpl(model, graphicalView, speed, outFile);
        controller.start();

      }
      else if (viewType.equalsIgnoreCase("playback")) {
        View playbackViewImpl = new PlaybackViewImpl(model.getCanvasX(),
                model.getCanvasY(), model.getCanvasWidth(),
                model.getCanvasHeight());
//        controller = new PlaybackViewController(model, playbackViewImpl, speed);
//        controller.start();
        controller = new AnimationControllerImpl(model, playbackViewImpl, speed, outFile);
        controller.start();
      }
      else {
        throw new IllegalArgumentException("Invalid view type");
      }
    } catch (Exception e) {
      System.out.println("Error" + e.getMessage());
      Utils.showErrorMessage("Invalid view type");
    }
  }
}