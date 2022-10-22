package gui;

import dictionnaire.Dico;
import jdk.internal.loader.URLClassPath;

import javax.swing.*;
import javax.swing.text.*;
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
    Highlighter.HighlightPainter myHighlightPainter = new GUI.MyHighlightPainter(Color.red);
    class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter {
        public MyHighlightPainter(Color color) {
            super(color);
        }
    }

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

    //Sorting the hashmap
    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =
                new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    public HashMap<String, HashMap> check() throws IOException {
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
            HashMap<String, Integer> wordLevenDistanceMap = new HashMap<String, Integer>();
            for (int j = 0; j < texteDico.size(); j++) {
                if (compute_Levenshtein_distanceDP(toCheck, texteDico.get(j))!=0) {
                    seen = true;
                    //Compute levenshtein distance as word was not found
                }
            }
            if (seen == false){
                System.out.println(toCheck);
                for (int k = 0; k < texteDico.size(); k++) {
                    wordLevenDistanceMap.put(texteDico.get(k), compute_Levenshtein_distanceDP(toCheck, texteDico.get(k)));
                }
                System.out.println(wordAndDistance.toString());
                Map<String, Integer> hm1 = sortByValue(wordLevenDistanceMap);
                HashMap<String, Integer> top5Distances= new HashMap<String, Integer>();
                int n = 0;
                for (Map.Entry<String, Integer> en : hm1.entrySet()) {
                    if (n == 5) {
                        break;
                    }
                    top5Distances.put(en.getKey(), en.getValue());
                    //System.out.println("Key = " + en.getKey() +
                    //        ", Value = " + en.getValue());
                    n++;
                    wordAndDistance.put(toCheck, top5Distances);
                }
            }
        }
        for (String key : wordAndDistance.keySet()) {
            highlight(key);
        }
        return wordAndDistance;
    }

    public void highlight(String pattern) {
        // First remove all old highlights
        removeHighlights(this.ta);

        try {
            Highlighter hilite = this.ta.getHighlighter();
            Document doc = this.ta.getDocument();
            String text = doc.getText(0, doc.getLength());
            int pos = 0;

            // Search for pattern
            while ((pos = text.indexOf(pattern, pos)) >= 0) {
                // Create highlighter using private painter and apply around pattern
                hilite.addHighlight(pos, pos+pattern.length(), myHighlightPainter);
                pos += pattern.length();
            }
        } catch (BadLocationException e) {
        }
    }

    // Removes only our private highlights
    public void removeHighlights(JTextComponent textComp) {
        Highlighter hilite = textComp.getHighlighter();
        Highlighter.Highlight[] hilites = hilite.getHighlights();

        for (int i=0; i<hilites.length; i++) {
            if (hilites[i].getPainter() instanceof TextAreaHighlight.MyHighlightPainter) {
                hilite.removeHighlight(hilites[i]);
            }
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
            texteDico = this.vectorize(chargerFichier(), 'n');
            //System.out.println(texteDico);
        }

        if (e.getSource() == this.ecrire) {
            this.ecrireFichier();
        }
        if (e.getSource() == this.verif) {
            //System.out.println(stringArrayList(ta.getText()));
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



