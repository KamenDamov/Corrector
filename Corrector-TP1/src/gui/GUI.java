package gui;

import dictionnaire.Dico;
import jdk.internal.loader.URLClassPath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

public class GUI extends JFrame implements EventListener, ActionListener {

    private JFileChooser fc;
    protected ArrayList<String> texte;
    protected ArrayList<String> texteDico;
    protected ArrayList<String> texteAVerif;

    protected ArrayList<String> textArea;
    private JTextArea ta;
    private JButton chooser;
    private JButton dictionnaire;
    private JButton ecrire;
    private JButton verif;

    public GUI() {
        JPanel haut = new JPanel();
        JPanel bas = new JPanel();
        this.add(haut, "North");
        this.add(bas, "Center");
        this.ta = new JTextArea("textarea", 300, 30);
        bas.add(this.ta);
        this.chooser = new JButton("choisir");
        haut.add(this.chooser);
        this.dictionnaire = new JButton("dictionnaire");
        haut.add(this.dictionnaire);
        this.ecrire = new JButton("ecrire");
        haut.add(ecrire);
        this.verif = new JButton("verifier");
        haut.add(verif);
        this.fc = new JFileChooser();
        this.texte = new ArrayList();
        this.chooser.addActionListener(this);
        this.dictionnaire.addActionListener(this);
        this.ecrire.addActionListener(this);
        this.verif.addActionListener(this);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridLayout(3, 3));
        setVisible(true);
        pack();
    }

    public <ArrayList> java.util.ArrayList<String> chargerFichier() {
        int val = this.fc.showOpenDialog(this);

        try {
            if (val == 0) {
                BufferedReader r = new BufferedReader(new FileReader(this.fc.getSelectedFile()));
                String line = null;

                while((line = r.readLine()) != null) {
                    this.texte.add(line);
                }

                r.close();
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return texte;
    }

    public void ecrireFichier() {
        int val = this.fc.showOpenDialog(this);

        try {
            if (val == 0) {
                BufferedWriter r = new BufferedWriter(new FileWriter(this.fc.getSelectedFile()));
                if (texte != null) for (String s: texte) r.write(s+ "\n");
                r.close();
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }


    public void afficher() {
        if (this.texte != null) {
            Iterator var1 = this.texte.iterator();

            while(var1.hasNext()) {
                String s = (String)var1.next();
                this.ta.append(s + "\n");
            }
        }

    }

    public ArrayList<String> stringArrayList(String s){
        ArrayList<String> list = new ArrayList<>(Arrays.asList(s.split(" ")));
        return list;
    }

    //Structure interne de notre dico
    public ArrayList<String> vectorize(ArrayList<String> chargerFichier, char v) {
        if (v == 'o') {
            ArrayList<String> newText = new ArrayList<>();
            for (int i = 0; i < chargerFichier.size(); i++) {
                newText.add(i, chargerFichier.get(i).toLowerCase());
            }
            return newText;
        } else {/*
            StringBuilder strBuilder = new StringBuilder();
            for (int i = 0; i < chargerFichier.size(); i++) {
                strBuilder.append(chargerFichier.get(i));
            }
            String str = strBuilder.toString();
            String clean = str.replaceAll("\\p{P}", " ").toLowerCase();
            List<String> texte = new ArrayList<String>(Arrays.asList(clean.split(" ")));
            //System.out.println(cleaned.toString());
            /*
            for (int i = 0; i < texte.size(); i++) {
                //System.out.println(cleaned.get(i));
                if (texte.get(i) == null || texte.get(i).isEmpty()) {
                    texte.remove(i);
                }
            }*/
            //System.out.println(cleaned);
            return (ArrayList) chargerFichier;
        }
    }
    public void check() throws IOException {
        //TODO
        // Naive algo: 2 for loops equating to O(n*m) algo
        // For words that are not in dico
        // Add in a hashmap KEY == DicoWord and VAL == Levenshtein Distance
        // Sort on Val and keep only top 5
        //Outer hashmap containing words, with its 5 closest contendants
        HashMap<String, HashMap> wordAndDistance = new HashMap<String, HashMap>();
        for (int i = 0; i < texteAVerif.size(); i++) {
            String toCheck = texteAVerif.get(i);
            boolean seen = false;
            for (int j = 0; j < texteDico.size(); j++) {
                if (toCheck == texteDico.get(j)) {
                    seen = true;
                }
            }
            if (seen == false) {
                HashMap<Integer, String> wordLevenDistanceMap = new HashMap<Integer, String>();
                System.out.println(toCheck);
                //Compute levenshtein distance as word was not found
                for (int k = 0; k < texteDico.size(); k++) {
                    //TODO
                    // Create hashmap have words in dico and their distance with current word
                    wordLevenDistanceMap.put(compute_Levenshtein_distanceDP(toCheck, texteDico.get(k)), texteDico.get(k));
                }
                //TODO
                // Create a hashmap to store words and associated top 5 closest words
                // 1) Sort the hashmap words
                // 2) Keep first 5 elements
                // 3) Create hashmap
                System.out.println(wordLevenDistanceMap.size());
                Map<Integer, String> map=new HashMap<Integer, String>();
                System.out.println("After Sorting");

                //using TreeMap constructor to sort the HashMap
                TreeMap<Integer, String> tm=new  TreeMap<Integer, String>(wordLevenDistanceMap);
                Iterator itr=tm.keySet().iterator();

                //Refill the hash map
                //Will be used as inner hashmap
                HashMap<String, Integer> top5Distance = new HashMap<String, Integer>();

                //Iterator to stop at top 5
                int n = 0;
                while(itr.hasNext() && n < 5)
                {
                    Integer key=(int)itr.next();
                    top5Distance.put(wordLevenDistanceMap.get(key), key);
                    System.out.println("Distance:  "+key+"     Word:   "+wordLevenDistanceMap.get(key));
                    n++;
                }
                //System.out.println(top5Distance.toString());
                wordAndDistance.put(toCheck, top5Distance);
            }
            //System.out.println(wordAndDistance.toString());
        }
    }

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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.chooser) {
            texte = this.vectorize(chargerFichier(), 'n');
            //System.out.println(texte);
            this.afficher();
        }

        if (e.getSource() == this.dictionnaire) {
            System.out.println(chargerFichier());
            texteDico = this.vectorize(chargerFichier(), 'n');
            //System.out.println(texteDico);
        }

        if (e.getSource() == this.ecrire) {
            this.ecrireFichier();
        }
        if (e.getSource() == this.verif) {
            System.out.println(stringArrayList(ta.getText()));
            texteAVerif = this.vectorize(stringArrayList(ta.getText()), 'o');
            //System.out.println(texteAVerif);
            try {
                this.check();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}



