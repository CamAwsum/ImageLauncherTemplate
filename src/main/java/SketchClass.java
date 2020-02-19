import processing.core.*;

import java.awt.*;
import java.util.ArrayList;

// Created by Camden Hobson. @CamdenHobson on Twitter.
public class SketchClass extends PApplet {
    public static PApplet appSketch;

    static PImage localImg;
    public static void main(String [] args) { PApplet.main("SketchClass", args); }
    public static int appWid = 1000, appHi = 1000;
    public static void main(String [] args, PImage displayImage) {
        PApplet.main("SketchClass", args);
        localImg = displayImage;
    }

    public void settings() {
        size(appWid,appHi);
        appSketch = this;
    }

    static ControllerClass controller;

    String [] saveNum;
    String shape = "dot";

    boolean paused = false, setupDone = false;

    public void setup() {
        controller = new ControllerClass();
        controller.main(args, this);

        saveNum = loadStrings("data/saveNum.txt");
        if (saveNum == null) {
            println("Creating new saveNum file...");
            saveStrings("data/saveNum.txt", new String[]{"0"});
        }
        else {
            println("SaveNum = " + saveNum[0]);
        }
    }

    public void draw() {
        background(color(255));
    }

    public void keyPressed() {
        // this line checks to see if the key is a "coded" key- basically anything that isn't a letter or a space.
        if (key == CODED) {
            if (keyCode == UP) {
                beginRecord(PDF,"data/output/template_"+saveNum[0]+".pdf");
                colorMode(HSB,360,100,100);
                // DISPLAY FUNCTION
                endRecord();
                println("saveNum before:" + saveNum[0]);
                saveNum[0] = Integer.toString(parseInt(saveNum[0])+1);
                saveStrings("data/saveNum.txt", saveNum);
                println("saveNum after:" + saveNum[0]);
            }
        }
        // this section is for any key that isn't a "code" key.
        switch (key) {
            case 'q':
                dispose();
                this.getSurface().setVisible(false);
                break;
            case 'p':
                paused ^= true;
                break;
        }
    }
}
