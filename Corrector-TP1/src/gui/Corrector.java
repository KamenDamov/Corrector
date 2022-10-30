package gui;

import dictionnaire.Dico;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;

import static dictionnaire.Dico.*;

public class Corrector {

    //public String words;
    public JTextArea ta;
    Dico d = new Dico();
    DefaultListModel<String> l1 = new DefaultListModel<>();
    Set keys;

    Highlighter.HighlightPainter myHighlightPainter = new GUI.MyHighlightPainter(Color.red);
    class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter {
        public MyHighlightPainter(Color color) {
            super(color);
        }
    }

    public Corrector(){}

    //Vectorize the words
    public ArrayList<String> stringArrayList(String words) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList(words.split(" ")));
        return list;
    }


    //Méthode pour ajouter les éléments
    public String updateInterfaceToDict(String word, String words/*, JTextArea ta, int startNewWord, int endNewWord*/) throws IOException {
        word = word.replaceAll("\\p{Punct}","");
        String toAppend = "";
        Set keys = check(d.vectorize(stringArrayList(words))).get(word).keySet();
        for (Object key: keys) {
            toAppend += key.toString() + "\n";
        }
        return toAppend;
    }

    //CReate a method that ouputs words not in dico
    public void highlightTextArea(String words, JTextArea ta) throws IOException {
        Set<String> keys = check(d.vectorize(stringArrayList(words))).keySet();
        for (String key : keys) {

            highlight(key, ta, words);
        }
    }

    //Méthode pour surligner le texte.
    public void highlight(String pattern, JTextArea ta, String words) {
        // Enlever vieux surlignement
        removeHighlights(ta);

        try {
            Highlighter hilite = ta.getHighlighter();
            int pos = 0;

            // Recherche de pattern
            while ((pos = words.indexOf(pattern, pos)) >= 0) {
                // Créer highlighter
                hilite.addHighlight(pos, pos+pattern.length(), this.myHighlightPainter);
                pos += pattern.length();
            }
        } catch (BadLocationException e) {
        }
    }

    public void removeHighlights(JTextComponent textComp) {
        Highlighter hilite = textComp.getHighlighter();
        Highlighter.Highlight[] hilites = hilite.getHighlights();

        for (int i=0; i<hilites.length; i++) {
            if (hilites[i].getPainter() instanceof TextAreaHighlight.MyHighlightPainter) {
                hilite.removeHighlight(hilites[i]);
            }
        }
    }
}
