import javax.swing.*;

import dictionnaire.Dico;
import gui.TextAreaHighlight;
import gui.TextAreaTest;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    public static void main(String[] args) {
        //Declaring GUI
        //gui.GUI g = new gui.GUI();
        dictionnaire.Dico d = new dictionnaire.Dico();
        d.grabDictionnary();
        d.grabText();
    }
    //TODO
    // Add elements in grid frame
    // Responsiveness of buttons
    // Save files
}
