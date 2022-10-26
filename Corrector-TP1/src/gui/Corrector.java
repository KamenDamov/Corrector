package gui;

import dictionnaire.Dico;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventListener;
import java.util.HashMap;

import static dictionnaire.Dico.*;

public class Corrector implements EventListener{
    //TODO
    // This class will produce new interface adding new textarea with buttons
    public String words;
    public Corrector(String words){
        this.words = words;
    }

    //Vectorize the words
    public ArrayList<String> stringArrayList(String s) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList(words.split(" ")));
        System.out.println(list);
        return list;
    }

    public String updateInterfaceToDict(String word) throws IOException {
        String toAppend = "";
        System.out.println(Dico.check(stringArrayList(words)).get(word).keySet());
        for (Object key: Dico.check(stringArrayList(words)).get(word).keySet()) {
            System.out.println(key);
            toAppend += key.toString() + "\n";
        }
        return toAppend;
    }

    //Add highilighting method!!!!


    public void updateDictToInterface(String word){
        //TODO
        // Change for word that is in the textarea currently
        System.out.println(word);
//        ta.replaceRange(word, startNewWord, endNewWord);
//        taCorrect.selectAll();
//        taCorrect.replaceSelection("");
        //ta.insert(word, startNewWord);
    }

}
