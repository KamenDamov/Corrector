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
    public static int compute_Levenshtein_distanceDP(String str1,
                                                     String str2)
    {

        // A 2-D matrix to store previously calculated
        // answers of subproblems in order
        // to obtain the final

        int[][] dp = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i <= str1.length(); i++)
        {
            for (int j = 0; j <= str2.length(); j++) {

                // If str1 is empty, all characters of
                // str2 are inserted into str1, which is of
                // the only possible method of conversion
                // with minimum operations.
                if (i == 0) {
                    dp[i][j] = j;
                }

                // If str2 is empty, all characters of str1
                // are removed, which is the only possible
                //  method of conversion with minimum
                //  operations.
                else if (j == 0) {
                    dp[i][j] = i;
                }

                else {
                    // find the minimum among three
                    // operations below


                    dp[i][j] = minm_edits(dp[i - 1][j - 1]
                                    + NumOfReplacement(str1.charAt(i - 1),str2.charAt(j - 1)), // replace
                            dp[i - 1][j] + 1, // delete
                            dp[i][j - 1] + 1); // insert
                }
            }
        }

        return dp[str1.length()][str2.length()];
    }

    // check for distinct characters
    // in str1 and str2

    static int NumOfReplacement(char c1, char c2)
    {
        return c1 == c2 ? 0 : 1;
    }

    // receives the count of different
    // operations performed and returns the
    // minimum value among them.

    static int minm_edits(int... nums)
    {

        return Arrays.stream(nums).min().orElse(
                Integer.MAX_VALUE);
    }

    //Create an array that takes a string (which will be a word in the text)
}
