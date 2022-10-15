package dictionnaire;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Dico {
    private String path_file;
    private ArrayList myLis

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
    public <ArrayList> ArrayList dicoGood(String str){
        String s = mots(path_file);

        ArrayList<String> myList = new ArrayList<String>(){

        };
        return myList;
    }

}
