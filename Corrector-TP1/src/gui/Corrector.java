package gui;

import dictionnaire.Dico;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;

import static dictionnaire.Dico.*;

public class Corrector {

    //public String words;
    public JTextArea ta;
    Dico d = new Dico();
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
        //System.out.println(list);
        return list;
    }

    public String updateInterfaceToDict(String word, String words) throws IOException {
        String toAppend = "";
        //System.out.println(Dico.check(stringArrayList(words)).get(word).keySet());
        Set keys = check(stringArrayList(words)).get(word).keySet();
        for (Object key: keys) {
            //System.out.println(key);
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

    //Add highilighting method!!!!
    public void highlight(String pattern, JTextArea ta, String words) {
        // First remove all old highlights
        removeHighlights(ta);

        try {
            Highlighter hilite = ta.getHighlighter();
            int pos = 0;

            // Search for pattern
            while ((pos = words.indexOf(pattern, pos)) >= 0) {
                // Create highlighter using private painter and apply around pattern
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
