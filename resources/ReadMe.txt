1. The Structure of the project:






2. The changes in our previous project:

In this project, we add an interactive view class called PlaybackViewImpl. In order to reduce code
repetition, PlaybackViewImpl extends GraphicalViewImpl class, since we only need to add buttons to
the panel from GraphicalViewImpl. Also, we only have a single controller called
AnimationControllerImpl, which can manage different view types and connect view with model.
In addition, we add a new command-line argument called "playback" for this new view type.

In terms of test, we add a test class for our controller called AnimationControllerImplTest, which
covers basic tests for our controller.



3. The introduction of the PlaybackViewImpl of our project:

In PlaybackViewImpl, we set buttons in the panel so that users can decide when to speed up, slow
down, pause, resume or loop by clicking at the corresponding button. Additionally, scroll bars
 are implemented in the panel.

The user can click at loop button at anytime to see the looping animations. Meanwhile, other buttons
can also perform their functionalities correctly when it's looping.



4. The introduction of the controller:

The EasyAnimator class works as a mini controller, which is responsible for handling the time,
calling the view and the model, and decides when to display the corresponding view to the users.
Specifically, we have a single controller called AnimationController, which has a start method
to handle time, model and different type of views.

5. Additional class:

We have a class clawed Utils, which contains a static method that is common to EasyAnimator
in order to display the corresponding error message on the screen if the command lines are not
valid. For example, invalid arguments(such as speed is less than or equals 0)
will display an error message board which says invalid speed.


6. Note:

In our SVGViewImplTest, TextBasedViewImplTest and GraphicalViewImplTest, we need to cover the tests
for invalid command-line arguments, so it will display corresponding error message panels before
 our tests are passed.


















