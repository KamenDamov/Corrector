import dictionnaire.Dico;

import javax.crypto.spec.PSource;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //Declaring GUI
        gui.GUI g = new gui.GUI();
        dictionnaire.Dico d = new dictionnaire.Dico("./dict.txt");
        //System.out.println((ArrayList<String>) d.dicoGood());
        System.out.println(d.compute_Levenshtein_distanceDP("Kamen", "Komen"));
    }
    //TODO
    // Add elements in grid frame
    // Responsiveness of buttons
    // Save files
}
