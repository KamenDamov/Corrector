import dictionnaire.Dico;

import javax.crypto.spec.PSource;

public class Main {
    public static void main(String[] args) {
        //Declaring GUI
        gui.GUI g = new gui.GUI();
        dictionnaire.Dico d = new dictionnaire.Dico("./dict.txt");
        System.out.println((String) d.dicoGood());
    }
    //TODO
    //1) Implement levenshtein algo
    //2) Update method
    //3) Verify button
}
