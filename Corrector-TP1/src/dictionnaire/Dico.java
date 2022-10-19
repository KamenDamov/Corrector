package dictionnaire;

import gui.GUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dico extends GUI {

    //Idea:
    //Create a static method to keep dico in this class only
    //Get text in text area through accessor method in Text class.
    //public static String content;

    public Dico() {
        super();
    }

    //Notre dictionnaire
    public ArrayList<String> grabDico(ArrayList<String> chargerFichier) {
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < chargerFichier.size(); i++) {
            strBuilder.append(chargerFichier.get(i));
        }
        String str = strBuilder.toString();
        String clean = str.replaceAll("\\p{P}", "").toLowerCase();
        List<String> texte = new ArrayList<String>(Arrays.asList(clean.split(" ")));
        //System.out.println(cleaned.toString());
        for (int i = 0; i < texte.size(); i++) {
            //System.out.println(cleaned.get(i));
            if (texte.get(i) == "\n" || texte.get(i).isEmpty()) {
                texte.remove(i);
            }
        }
        //System.out.println(cleaned);
        return (ArrayList) texte;
    }
    /*
    public ArrayList<String> grabText(ArrayList<String> ta){

    }
    */
     */
    public <ArrayList> java.util.ArrayList<String> grabDictionnary(){
        System.out.println(this.grabDico(texte));
        return super.texte;
    }

    //Stocker dictionnaire
    public void check(String content) throws IOException {
        //TODO
        // Add listener to grab text from text area!!!
        //System.out.println(dicoGood().toString());
        //System.out.println(textArea.getText());
        System.out.println("Hello, I'm the check function");
        //String s = text;
        //List<String> l2 = new java.util.ArrayList<String>(Arrays.asList(s.split(" ")));
        //System.out.println(dicoGood().toString());
        //System.out.println(s);
    }

    //Implement Levenshtein distance algo
    public int compute_Levenshtein_distanceDP(String str1,
                                                     String str2)
    {

        // A 2-D matrix to store previously calculated
        // answers of subproblems in order
        // to obtain the final

        int[][] dp = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i <= str1.length(); i++)
        {
            for (int j = 0; j <= str2.length(); j++) {

                if (i == 0) {
                    dp[i][j] = j;
                }

                else if (j == 0) {
                    dp[i][j] = i;
                }

                else {
                    dp[i][j] = minm_edits(dp[i - 1][j - 1]
                                    + NumOfReplacement(str1.charAt(i - 1),str2.charAt(j - 1)), // replace
                            dp[i - 1][j] + 1, // delete
                            dp[i][j - 1] + 1); // insert
                }
            }
        }

        return dp[str1.length()][str2.length()];
    }

    static int NumOfReplacement(char c1, char c2)
    {
        return c1 == c2 ? 0 : 1;
    }

    static int minm_edits(int... nums)
    {

        return Arrays.stream(nums).min().orElse(
                Integer.MAX_VALUE);
    }

}
