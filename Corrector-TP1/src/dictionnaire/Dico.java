package dictionnaire;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dico {
    private String path_file;
    public Dico(String path_file){
        this.path_file = path_file;
    }
    //Dictionnaires
    public static String mots(String path_file){
        Path fileName
                = Path.of(path_file);

        // Now calling Files.readString() method to
        // read the file
        String str = null;
        try {
            str = Files.readString(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Printing the string
        return str;
    }
    //Create an arraylist from the string above
    //String to arraylist split at \n
    public <ArrayList> ArrayList dicoGood(){
        String s = mots(path_file);
        List<String> l2 = new java.util.ArrayList<String>(Arrays.asList(s.split("\n")));
        return (ArrayList) l2;
    }

    //Implement Levenshtein distance algo
    public int levenshteinDistance(String mot1, String mot2){
        


        return dist;
    }


    //Create an array that takes a string (which will be a word in the text)
}
