package dictionnaire;

import gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Dico extends GUI {
    private String text;

    public Dico(String text) {
        this.text = text;
    }

    //Dictionnaires
    //Create an arraylist from the string above
    //String to arraylist split at \n
    public <ArrayList> ArrayList dicoGood(String content) throws IOException {
        String s = content;
        List<String> l2 = new java.util.ArrayList<String>(Arrays.asList(s.split(" ")));
        System.out.println("HELLO");
        System.out.println(l2.toString());
        return (ArrayList) l2;
    }

    public void newInterface(String t) throws IOException {
        JPanel north = new JPanel(new FlowLayout());
        JPanel center = new JPanel(new FlowLayout());

        north.add(label);
        center.add(textArea);

        add(north, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridLayout(3, 3));
        setVisible(true);
        textArea.append(this.text);
        pack();
    }

    public void check(String text) throws IOException {
        String s = text;
        List<String> l2 = new java.util.ArrayList<String>(Arrays.asList(s.split(" ")));
        System.out.println((String) dicoGood(this.text));
        System.out.println(s);
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
