import javax.swing.*;
import gui.TextAreaHighlight;
public class Main {
    public static <TextAreaHighlight> void main(String[] args) {
        //Declaring GUI
        /*GUI g = new GUI();
        Dico d = new Dico("./dict.txt");
        //System.out.println((ArrayList<String>) d.dicoGood());
        System.out.println(d.compute_Levenshtein_distanceDP("Kamen", "Komen"));
        gui.TextAreaTest frame = new TextAreaTest();
        frame.setDefaultCloseOperation( EXIT_ON_CLOSE );
        frame.pack();
        frame.setVisible(true);*/
        gui.TextAreaHighlight h = new gui.TextAreaHighlight();
        h.setTitle("TextAreaHighlight");
        h.setVisible(true);
        h.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        h.pack();
        // Highlight the occurrences of the word "public"
        h.textComp.append("Ce syst�me n'est pas vu par le public. Il est priv�.");
        h.highlight("public");

    }
    //TODO
    // Add elements in grid frame
    // Responsiveness of buttons
    // Save files
}
