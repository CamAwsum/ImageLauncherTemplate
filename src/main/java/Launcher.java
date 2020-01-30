import processing.core.PApplet;
import controlP5.*;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class Launcher extends PApplet {
    public static PApplet launcherSketch;

    public static void main(String [] args) {
        PApplet.main("Launcher", args);
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

    public void setup() {
        colorMode(HSB,360,100,100,100);
        cp5 = new ControlP5(this);

        widthText = cp5.addTextfield("Width")
                .setPosition(25,25)
                .setSize(50, 25)
                .setInputFilter(1)
                .setColorActive(color(0,0,30)).setColorForeground(color(0,0,20)).setColorBackground(color(0,0,10)).setColorLabel(color(0,0,50));
        heightText = cp5.addTextfield("Height")
                .setPosition(100,25)
                .setSize(50, 25)
                .setInputFilter(1)
                .setColorActive(color(0,0,30)).setColorForeground(color(0,0,20)).setColorBackground(color(0,0,10)).setColorLabel(color(0,0,50));
        urlText = cp5.addTextfield("Url")
                .setPosition(25,height-50)
                .setSize(650, 25)
                .setColorActive(color(0,0,30)).setColorForeground(color(0,0,20)).setColorBackground(color(0,0,10)).setColorLabel(color(0,0,50));
        urlBang = cp5.addBang("loadSelUrl")
                .setPosition(width-50, height-50)
                .setSize(25, 25)
                .setLabel("Reload")
                .setColorActive(color(0,0,30)).setColorForeground(color(0,0,20)).setColorBackground(color(0,0,10)).setColorLabel(color(0,0,50));
        launchBang = cp5.addBang("launchApp")
                .setPosition(width-50, 25)
                .setSize(25, 25)
                .setLabel("Launch")
                .setColorActive(color(0,0,30)).setColorForeground(color(0,0,20)).setColorBackground(color(0,0,10)).setColorLabel(color(0,0,50));
    }
    public void controlEvent(CallbackEvent call) {
        if (call.getController().equals(urlText)) {

        }
    }
    public void draw() {
        background(color(0,0,95));
        syncVars();
        fill(0);
        ellipse(mouseX,mouseY,50,50);
    }
    public void syncVars() {
        if (widthText.getText().length() > 4) {
            widthText.setText(widthText.getText().substring(0,4));
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
