package dictionnaire;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Dico {
    String path_file;
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


}
