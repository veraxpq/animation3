Submit a text README file explaining your design. Make sure you explain your design changes
from the previous assignment.

In terms of reading from file, we only use the 1st motion to initialize Shape objects(since all
attributes are the same) and add the following motion to our model.

Invalid arguments(such as speed is less than or equals 0) will display a error message board.

SVGViewImpl extends TextBasedViewImpl.

SVG: At first, all shapes are hidden, when the current time is equal to the shape's appear time,
it will be visible, then it will
be invisible when the current time is equal to the shape's disappear time.

The user can use scroll bars to see different parts when the Animation Viewer is displaying animations.


When playing with the JAR file called Animator.jar, just move the Animator.jar from resources file to the project file directory, but
the user don't need to put the input txt file outside of starterCode file, because we already specify the path(starterCode) for all input txt files
in our program,


HW6:
The Structure of the project:

├── src
│   └── cs5004
│       └── project
│           ├── AbstractAnimation.java
│           ├── AbstractShape.java
│           ├── Animation.java
│           ├── Appear.java
│           ├── ChangeColor.java
│           ├── Disappear.java
│           ├── Model.java
│           ├── ModelImpl.java
│           ├── Move.java
│           ├── NameOfShape.java
│           ├── Oval.java
│           ├── Rectangle.java
│           ├── Scale.java
│           └── Shape.java
├── test
│       ├─ChangeColorTest.class
│       ├── ModelImplTest.class
│       ├── MoveTest.class
│       ├── OvalTest.class
│       ├── RectangleTest.class
│       └── ScaleTest.class

In our design, there are three interfaces: Shape, Animation and Model. 

The shape interface includes methods related to Shape objects: getColor() to get the color of the shape; getType() to get the Enum type; getName() to get the name, getPosition() to get the 2D position of the left upper corner of the shape; copy() to get the copied shape; toString() to get the formatted string; createNewShape() to create a new shape with the given parameters. The abstract class  AbstractShape is designed to decrease the repetition of the codes, which implements some common methods of all the shapes, like getColor(), getType() and so on. Currently, there are two concrete classes of AbstractShape class, Rectangle and Oval. Each specific class has its own fields, for example, the Rectangle has its width and height.

The Animation interface includes the methods of animation: play() to get the shape copy at the specific time when applying the animation; getStartTime() to get its starting time; getEndTime() to get its ending time; toString() to get the formatted string of the object. We try to keep Animation and Shape independent, in order to reduce coupling. The AbstractAnimation class has two common fields and implements all the common methods of all the Animation objects. And we have three concrete subclasses, ChangeColor, Move and Scale. ChangeColor class changes the color by keeping returning a new shape copy with new given color within the given time period; Move class moves the given shape copy from the start point to the end point; Scale class zooms in or out the given shape copy from original size to new size.

Finally, we have the Model interface to apply the animation to the shapes. There are eight public methods in the interface, two addShapes, addAnimation, two removeShapes, getShape, getShapeAtTick and toString. The implementation class of the Model interface is ModelImpl, which has two fields mapOfShapes and mapOfAnimations. The mapOfShapes is a HashMap containing all the shapes added into the ModelImpl object, which can be accessed by calling the name of the shape. The mapOfAnimations contains all the animations in the ModelImpl object, and the key of the map is also the name of the shape. In this way, we could easily access any shape and animation by calling the name of the shape, and the time complexity is O(1). In getShapeAtTick(), we always return a list of immutable shapes at a certain tick, since we always create new shapes (or shape copy) when applying animation to shape. In addAnimation(), a shape's different moves can't be overlapped in time, in this case, we will throw IllegalArgumentException.



