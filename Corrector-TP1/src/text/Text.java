package text;

import dictionnaire.Dico;
import gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Text extends GUI {
    String t;
    public Text(){
        super();
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
        textArea.append(t);
        check(t);
        pack();
    }
    public void check(String text) throws IOException {
        String s = text;
        List<String> l2 = new java.util.ArrayList<String>(Arrays.asList(s.split(" ")));
        System.out.println((String) Dico.dicoGood());
        System.out.println(s);
    }
}
