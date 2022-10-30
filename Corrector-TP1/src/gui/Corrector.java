package gui;

import dictionnaire.Dico;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.*;

import static dictionnaire.Dico.*;

public class Corrector {

    //public String words;
    public JTextArea ta;
    Dico d = new Dico();
    public JList menu;
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
        //System.out.println(list);
        return list;
    }


    //Méthode pour ajouter les éléments
    public String updateInterfaceToDict(String word, String words/*, JTextArea ta, int startNewWord, int endNewWord*/) throws IOException {
        word = word.replaceAll("\\p{Punct}","");
        String toAppend = "";
        //System.out.println(Dico.check(stringArrayList(words)).get(word).keySet());
       // menu = new JMenu();
       // word1 = new JMenuItem("Allo");
       // menu.add(word1);

        //l1.addElement("Item1");
        //l1.addElement("Item2");
        //l1.addElement("Item3");
        //l1.addElement("Item4");
        //l1.addElement("Item5");
        //System.out.println(word);
        Set keys = check(d.vectorize(stringArrayList(words))).get(word).keySet();
        System.out.println("My keys:" + keys);
        for (Object key: keys) {
            //System.out.println(key);
            System.out.println("I'm working");
            toAppend += key.toString() + "\n";
            //l1.addElement(key.toString());
        }
        /*JList<String> list = new JList<>(l1);
        list.setBounds(100,100, 100,100);
        ta.add(list);
        ListSelectionListener sl = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                System.out.println("First index: " + list.getSelectedValue());
                ta.replaceRange(list.getSelectedValue(), startNewWord, endNewWord);

                System.out.println(", Last index: " + e.getLastIndex());
            }
        };
        //list.addListSelectionListener(sl );*/
        System.out.println(toAppend);
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
