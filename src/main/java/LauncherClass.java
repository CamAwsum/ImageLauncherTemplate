import processing.core.PApplet;
import controlP5.*;
import processing.core.PImage;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class LauncherClass extends PApplet {
    public static PApplet launcherSketch;

    public static int frameWidth = 100, frameHeight = 100;
    public static void main(String [] args) {
        PApplet.main("LauncherClass", args);
    }
    public void settings() {
        launcherSketch = this;

        size(750,500);
    }
    ControlP5 cp5;
    Textfield heightText, widthText, urlText;
    Bang launchBang, urlBang;

    Controller [] controllers = new Controller[]{heightText,widthText,urlText,
                                                 launchBang,urlBang};

    String clipUrl;
    PImage displayImage = null;

    public void setup() {
        colorMode(HSB,360,100,100,100);
        cp5 = new ControlP5(this);

        widthText = cp5.addTextfield("Width")
                .setPosition(25,25)
                .setSize(50, 25)
                .setInputFilter(1)
                .setColorActive(color(0,0,30)).setColorForeground(color(0,0,20)).setColorBackground(color(0,0,10)).setColorLabel(color(0,0,0));
        heightText = cp5.addTextfield("Height")
                .setPosition(100,25)
                .setSize(50, 25)
                .setInputFilter(1)
                .setColorActive(color(0,0,30)).setColorForeground(color(0,0,20)).setColorBackground(color(0,0,10)).setColorLabel(color(0,0,0));
        urlText = cp5.addTextfield("Url")
                .setPosition(25,height-50)
                .setSize(650, 25)
                .setText("https://w9j4x8b4.stackpathcdn.com/wp-content/uploads/sites/21/2019/07/cropped-gamefulLogoColor-1.png")
                .setColorActive(color(0,0,30)).setColorForeground(color(0,0,20)).setColorBackground(color(0,0,10)).setColorLabel(color(0,0,0));
        urlBang = cp5.addBang("loadSelUrl")
                .setPosition(width-50, height-50)
                .setSize(25, 25)
                .setLabel("Reload")
                .setColorActive(color(0,0,30)).setColorForeground(color(0,0,20)).setColorBackground(color(0,0,10)).setColorLabel(color(0,0,0));
        launchBang = cp5.addBang("launchApp")
                .setPosition(width-50, 25)
                .setSize(25, 25)
                .setLabel("Launch")
                .setColorActive(color(0,0,30)).setColorForeground(color(0,0,20)).setColorBackground(color(0,0,10)).setColorLabel(color(0,0,0));

        rectMode(CORNERS);
    }
    /*public void controlEvent(CallbackEvent call) {
        if (call.getController().equals(urlText)) {

        }
    }*/

    public void draw() {
        background(color(0,0,95));
        syncVars();
        fill(color(0,0,100));
        stroke(color(0));
        // rect width is width-50, rect height is height-150
        rect(25,75,width-25,height-75);
        if(displayImage != null) {
            // frameHeight/frameWidth (height-150)/(width-50)
            int highRat = frameHeight / (height - 150);
            int widRat = frameWidth / (width - 50);
            if ( highRat > widRat) {
                println("high: " + ((width-(frameWidth/highRat))/2) + " " + 75 + " " + (frameWidth*highRat-150) + " " +(height-150));
                image(displayImage, (width-(frameWidth*highRat-150))/2, 75, (frameWidth*highRat-150),height-150);
            }
            else {
                println("wid: " +25 + " " + (height/2-frameHeight*widRat) + " " + (width-25) + " " + 100);
                image(displayImage, 25, height/2-frameHeight*widRat, width-50, 100);
            }
        }
        if(!urlText.isUserInteraction()) urlText.setUserInteraction(true);
    }

    public void launchApp() {
        SketchClass.main(args,displayImage);
    }

    public void loadSelUrl() {
        displayImage = loadImage(urlText.getText());
    }

    public void syncVars() {
        if (widthText.getText().length() > 4) {
            widthText.setText(widthText.getText().substring(0,4));
        }
        if (widthText.getText().length() > 0 && heightText.getText().length() > 0 ) {
            frameWidth = Integer.valueOf(widthText.getText());
            frameHeight = Integer.valueOf(heightText.getText());
        }
        if (heightText.getText().length() > 4) {
            heightText.setText(heightText.getText().substring(0,4));
        }
    }
    public void keyPressed() {
        if (key == 'q' && !urlText.isFocus()) {
            exit();
        }
        if (key == ' ' && urlText.isFocus()) {
            println("Pasting from clipboard.");
            clipUrl = getClipBoard();
            urlText.setText(clipUrl);
            urlText.setUserInteraction(false);
        }
    }
    public String getClipBoard(){
        try {
            return (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (HeadlessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedFlavorException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }
}
