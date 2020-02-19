import processing.core.PApplet;
import controlP5.*;

// Created by Camden Hobson. @CamdenHobson on Twitter.
public class ControllerClass extends PApplet {
    public static PApplet controllerSketch;

    public static SketchClass toControl;


    public static void main(String [] args) {
        PApplet.main("ControllerClass", args);
    }
    public static void main(String [] args, SketchClass tToControl) {
        PApplet.main("ControllerClass", args);
        toControl = tToControl;
    }

    public void settings() {
        controllerSketch = this;
        size(600,250);
    }
    ControlP5 cp5;

    // this creates an array that holds every controller used in the program. This isn't automatically completed, it has to be done by hand.
    Controller [] controllers = new Controller[]{

    };
    /* the code in this function is called on frame 0 of the program, before draw() is ever called, but after setup().
     * This is where you'll want to start adding values to variables before draw() happens.                            */
    public void setup () {
        colorMode(HSB, 360, 100, 100, 100);
        // this line creates a new Controller Interface for "this" PApplet, essential for all controllers on the program.
        cp5 = new ControlP5(this);

        // this line assigns a slider with label "slider1" to the slider1 variable.  The label does not have to be the same as the variable name, but is here for simplicity.

        toControl.setupDone = true;
    }

    // the code in this function is called every frame by the sketch.  This is essentially the main function of the program, although it may have the least amount of code.
    public void draw () {
        // this line creates a background for the app which essentially "clears" the app each frame, so it starts fresh again.
        background(color(0,0,75));
        // this line calls the syncVars() function.
        syncVars();
        noStroke();
        fill(0,0,0,40);
        rect(0,0,width,height);

        // this is a fragment I need to delete
        line(0,height/2,width,height/2);
        line(width/2,0,width/2,height);
    }

    public void syncVars () {
    }
    public void upStep () {
        println("upStep");
    }
    public void downStep () {
        println("downStep");
    }
    public void shapeToggle () {
        switch (toControl.shape) {
            case "square":
                toControl.shape = "tri";
                break;
            case "tri":
                toControl.shape = "dot";
                break;
            case "dot":
                toControl.shape = "square";
                break;
        }
        println(toControl.shape);
    }
    // this function is called whenever a key input is registered by the application.
    public void keyPressed() {
        // this line checks to see if the key is a "coded" key- basically anything that isn't a letter or a space.
        if (key == CODED) {

        }
        // this section is for any key that isn't a "code" key.
        else {
            // this section closes the program if 'q' is pressed.
            switch (key) {
                case 'q':
                    dispose();
                    this.getSurface().setVisible(false);
                    break;
                case 'p':
                    toControl.paused ^= true;
                    break;
            }
        }
    }
}
