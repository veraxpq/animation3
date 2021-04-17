1. The Structure of the project:

├── src
│   ├── cs5004
│   │   └── animator
│   │       ├── EasyAnimator.java
│   │       ├── controller
│   │       │   ├── AnimationController.java
│   │       │   └── GraphicalViewController.java
│   │       ├── model
│   │       │   ├── AbstractAnimation.java
│   │       │   ├── AbstractShape.java
│   │       │   ├── Animation.java
│   │       │   ├── ChangeColor.java
│   │       │   ├── Model.java
│   │       │   ├── ModelImpl.java
│   │       │   ├── Move.java
│   │       │   ├── NameOfShape.java
│   │       │   ├── Oval.java
│   │       │   ├── Rectangle.java
│   │       │   ├── Scale.java
│   │       │   └── Shape.java
│   │       ├── util
│   │       │   ├── AnimationBuilder.java
│   │       │   ├── AnimationReader.java
│   │       │   └── Utils.java
│   │       └── view
│   │           ├── GraphicalView.java
│   │           ├── GraphicalViewImpl.java
│   │           ├── MyPanel.java
│   │           ├── SVGViewImpl.java
│   │           ├── TextBasedView.java
│   │           ├── TextBasedViewImpl.java
│   │           └── View.java
│   └── starterCode
│       ├── big-bang-big-crunch.txt
│       ├── buildings.txt
│       ├── hanoi.txt
│       ├── simple-example-with-loopback.svg
│       ├── simple-example.svg
│       ├── smalldemo.txt
│       ├── toh-12.txt
│       ├── toh-3.txt
│       ├── toh-5.txt
│       └── toh-8.txt
├── test
│   ├── AnimationBuilderTest.java
│   ├── ChangeColorTest.java
│   ├── ModelImplTest.java
│   ├── MoveTest.java
│   ├── OvalTest.java
│   ├── RectangleTest.java
│   ├── SVGViewImplTest.java
│   ├── ScaleTest.java
│   └── TextBasedViewImplTest.java



2. The changes in our previous model:

In the last version of our model, when we call the play() method in animation class, if the time is
earlier than the appear time, we set the return shape to be the shape of the appear time. But later
we found that the shape should not show up before the appear time, so we return null if the time is
before the appear time in play() method.

Meanwhile, we add some fields and methods in the model, since we need to display the shapes in a panel.
We add setCanvas, getCanvasX, getCanvasY, getCanvasWidth, getCanvasHeight methods in model interface. 
We add canvasPositionX, canvasPositionY, canvasWidth and canvasHeight fields in the modelImpl class.

To generate the svg strings of the view, we add some methods in the concrete
Shape classes. For example, we add getSVGOfShape and getSVGOfEndTag methods in Rectangle class.



3. The introduction of the three views of our project:

The root interface of the view is the View interface, which contains currentView and getDescription
methods. Since there are two different kinds of views, which is text and visual, we create two different
interfaces to extend View interface. The TextBasedView represents the view displayed in text, and can be 
divided into two kinds of views: TextView and SVGView. 

TextView:
In TextView, the shapes and animations are represented by words description. Users can input the command 
line to choose which input file to read from, and which output file to produce. In terms of reading from file,
we only use the 1st motion(since all attributes are the same) to initialize Shape objects and add the following 
motion to our model.

SVGView:
At first, all shapes are hidden, when the current time is equal to the shape's appear time, they will be 
visible, then it will be invisible when the current time is equal to the shape's disappear time.

GraphicalView:
In GraphicalView, the animations are displayed in the canvas. Users can input the command line to choose 
the speed of the animation, and the input file. Also, users can use scroll bars to see different
parts when the Animation Viewer is representing the animations.

The implementation of TextBasedView is TextViewImpl, which contains
currentView method, which reads the file with the given file name, and generates corresponding output file. 
GetDescription method returns the formatted strings of the model.

The implementation of SVGView is SVGViewImpl, which contains a method getDescription to transform the model's data
into the svg formatted string. SVGViewImpl extends TextBasedViewImpl.

The implementation of GraphicalView is GraphicalViewImpl, which displays the animations in visual views. The 
various attributes of the frame are set in this method, and an instance of MyPanel is created to add to the frame. 
Additionally, scroll bars are implemented in the panel.



4. The introduction of the controller:

The EasyAnimator class works as a mini controller, which is responsible for handling the time, calling the view and the 
model, and decides when to display the corresponding view to the users. Specifically, We have a GraphicalViewController which
has a start method to handle time, model and GraphicalView.



5. Additional class:

We have a class clawed Utils, which contains a static method that is common to EasyAnimator in order to display the error message 
on the screen if the command lines are not valid. For example, invalid arguments(such as speed is less than or equals 0) 
will display an error message board.



6. Note:

When playing with the JAR file called Animator.jar, just move the Animator.jar from resources file to the project file directory,
and the user don't need to put the input txt file outside of starterCode file, because we already specify the path(starterCode) for 
all input txt files in our program.










