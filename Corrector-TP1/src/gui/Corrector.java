package gui;

import dictionnaire.Dico;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;

import static dictionnaire.Dico.*;

public class Corrector implements EventListener{
    //TODO
    // This class will produce new interface adding new textarea with buttons
    public String words;
    public JTextArea ta;
    Highlighter.HighlightPainter myHighlightPainter = new GUI.MyHighlightPainter(Color.red);
    class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter {
        public MyHighlightPainter(Color color) {
            super(color);
        }
    }

    public Corrector(String words, JTextArea ta){
        this.words = words;
        this.ta = ta;
    }

    //Vectorize the words
    public ArrayList<String> stringArrayList(String s) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList(this.words.split(" ")));
        //System.out.println(list);
        return list;
    }

    public String updateInterfaceToDict(String word) throws IOException {
        String toAppend = "";
        //System.out.println(Dico.check(stringArrayList(words)).get(word).keySet());
        Set keys = check(stringArrayList(this.words)).get(word).keySet();
        for (Object key: keys) {
            //System.out.println(key);
            toAppend += key.toString() + "\n";
        }
        return toAppend;
    }

    //CReate a method that ouputs words not in dico
    public void highlightTextArea() throws IOException {
        Set<String> keys = check(stringArrayList(this.words)).keySet();
        for (String key : keys) {
            highlight(key);
        }
    }


    //Add highilighting method!!!!
    public void highlight(String pattern) {
        // First remove all old highlights
        removeHighlights(this.ta);

        try {
            Highlighter hilite = this.ta.getHighlighter();
            //Document doc = this.ta.getDocument();
            //String text = doc.getText(0, doc.getLength());
            int pos = 0;

            // Search for pattern
            while ((pos = this.words.indexOf(pattern, pos)) >= 0) {
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
