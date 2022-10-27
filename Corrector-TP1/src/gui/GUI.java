package gui;

import dictionnaire.Dico;
import jdk.internal.loader.URLClassPath;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

public class GUI extends JFrame implements EventListener, ActionListener {

    protected JFileChooser fc;
    protected ArrayList<String> texte;
    protected ArrayList<String> texteDico;
    protected ArrayList<String> texteAVerif;

    protected ArrayList<String> textArea;
    protected JTextArea ta;
    protected JButton chooser;
    protected JButton dictionnaire;
    protected JButton ecrire;
    protected JButton verif;
    protected JTextArea taCorrect;
    protected int startNewWord;
    protected int endNewWord;
    protected String word;
    //protected String word;

    Highlighter.HighlightPainter myHighlightPainter = new GUI.MyHighlightPainter(Color.red);
    static class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter {
        public MyHighlightPainter(Color color) {
            super(color);
        }
    }

    public GUI() {
        JPanel haut = new JPanel();
        JPanel bas = new JPanel();
        JPanel est = new JPanel();
        this.add(haut, "North");
        this.add(bas, "Center");
        this.add(est, "East");
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
        this.taCorrect = new JTextArea("", 300, 30);
        est.add(this.taCorrect);
        this.fc = new JFileChooser();
        this.texte = new ArrayList();
        this.texteDico = new ArrayList<>();
        this.chooser.addActionListener(this);
        this.dictionnaire.addActionListener(this);
        this.ecrire.addActionListener(this);
        this.verif.addActionListener(this);
/*
        ta.addMouseListener( new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                if ( SwingUtilities.isRightMouseButton(e) )
                {
                    try
                    {
                        int offset = ta.viewToModel( e.getPoint() );
                        //System.out.println( ta.modelToView( offset ) );
                        int start = Utilities.getWordStart(ta,offset);
                        int end = Utilities.getWordEnd(ta, offset);
                        startNewWord = Utilities.getWordStart(ta,offset);
                        endNewWord = Utilities.getWordEnd(ta, offset);
                        String word = ta.getDocument().getText(start, end-start);
                        System.out.println( "Selected word: " + word);
                        int rowStart = Utilities.getRowStart(ta, offset);
                        int rowEnd = Utilities.getRowEnd(ta, offset);
                        System.out.println( "Row start offset: " + rowStart );
                        System.out.println( "Row end   offset: " + rowEnd );
                        ta.select(rowStart, rowEnd);
                        //TODO
                        // Add the words instead of hello
                        // Append to textarea
                        updateInterfaceToDict(word);
                    }
                    catch (Exception e2) {}
                }
                repaint();
            }
        });*/

        ta.addCaretListener( new CaretListener()
        {
            public void caretUpdate(CaretEvent e)
            {
                int caretPosition = ta.getCaretPosition();
                Element root = ta.getDocument().getDefaultRootElement(
                );
                int row = root.getElementIndex( caretPosition );
                int column = caretPosition - root.getElement( row ).getStartOffset();
                //System.out.println( "Row   : " + ( row + 1 ) );
                //System.out.println( "Column: " + ( column + 1 ) );
            }
        });

        ta.addKeyListener( new KeyAdapter()
        {
            public void keyPressed(KeyEvent e)
            {
                //System.out.println( ta.getDocument().getDefaultRootElement().getElementCount() );
            }
        });
        /*
        taCorrect.addMouseListener( new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                if ( SwingUtilities.isRightMouseButton(e) )
                {
                    try
                    {
                        int offset = taCorrect.viewToModel( e.getPoint() );
                        //System.out.println( ta.modelToView( offset ) );
                        int start = Utilities.getWordStart(taCorrect,offset);
                        int end = Utilities.getWordEnd(taCorrect, offset);
                        String word = taCorrect.getDocument().getText(start, end-start);
                        System.out.println( "Selected word: " + word);
                        int rowStart = Utilities.getRowStart(taCorrect, offset);
                        int rowEnd = Utilities.getRowEnd(taCorrect, offset);
                        System.out.println( "Row start offset: " + rowStart );
                        System.out.println( "Row end   offset: " + rowEnd );
                        taCorrect.select(rowStart, rowEnd);
                        //TODO
                        // Add the words instead of hello
                        // Append to textarea
                        //updateDictToInterface(word);

                    }
                    catch (Exception e2) {}
                }
            }
        });

        taCorrect.addCaretListener( new CaretListener()
        {
            public void caretUpdate(CaretEvent e)
            {
                int caretPosition = taCorrect.getCaretPosition();
                Element root = taCorrect.getDocument().getDefaultRootElement(
                );
                int row = root.getElementIndex( caretPosition );
                int column = caretPosition - root.getElement( row ).getStartOffset();
                //System.out.println( "Row   : " + ( row + 1 ) );
                //System.out.println( "Column: " + ( column + 1 ) );
            }
        });

        taCorrect.addKeyListener( new KeyAdapter()
        {
            public void keyPressed(KeyEvent e)
            {
                //System.out.println( ta.getDocument().getDefaultRootElement().getElementCount() );
            }
        });*/

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridLayout(3, 3));
        setVisible(true);
        pack();
    }

    //Create a connector function - Interface to Dico
    /*
    public void updateInterfaceToDict(String word) throws IOException {
        String toAppend = "";
        for (Object key: check().get(word).keySet()) {
            toAppend += key.toString() + "\n";
        }
        taCorrect.append(toAppend);
    }*/

    //Create a connector function - Dico to interface
    public void updateDictToInterface(String word){
        //TODO
        // Change for word that is in the textarea currently
        //System.out.println("JE SUIS LA");
        ta.replaceRange(word, startNewWord, endNewWord);
        taCorrect.selectAll();
        taCorrect.replaceSelection("");
        //ta.insert(word, startNewWord);
    }

    public <ArrayList> java.util.ArrayList<String> chargerFichier(char discrim) {
        if (discrim == 't') {
            int val = this.fc.showOpenDialog(this);

            try {
                if (val == 0) {
                    BufferedReader r = new BufferedReader(new FileReader(this.fc.getSelectedFile()));
                    String line = null;

                    while ((line = r.readLine()) != null) {
                        this.texte.add(line);
                    }

                    r.close();
                }
            } catch (Exception var4) {
                var4.printStackTrace();
            }

            return texte;
        } else {
            int val = this.fc.showOpenDialog(this);

            try {
                if (val == 0) {
                    BufferedReader r = new BufferedReader(new FileReader(this.fc.getSelectedFile()));
                    String line = null;

                    while ((line = r.readLine()) != null) {
                        this.texteDico.add(line);
                    }

                    r.close();
                }
            } catch (Exception var4) {
                var4.printStackTrace();
            }

            return texteDico;
        }
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

    public void afficher(ArrayList<String> t) {
        if (t != null) {
            Iterator var1 = t.iterator();

            while(var1.hasNext()) {
                String s = (String)var1.next();
                ta.append(s + "\n");
            }
        }
    }

    public ArrayList<String> stringArrayList(String s){
        ArrayList<String> list = new ArrayList<>(Arrays.asList(s.split(" ")));
        return list;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.chooser) {
            //texte = this.vectorize(chargerFichier('c'), 'n');
            texte = chargerFichier('t');
            //System.out.println(texte.toString());
            this.afficher(texte);
        }

        if (e.getSource() == this.dictionnaire) {
            //texteDico = this.vectorize(chargerFichier('n'), 'n');
            texteDico = chargerFichier('n');
            //System.out.println(texteDico);
            Dico dict = new Dico(texteDico);
        }

        if (e.getSource() == this.ecrire) {
            this.ecrireFichier();
        }
        if (e.getSource() == this.verif) {
            System.out.println("New Test");
            //texteAVerif = this.vectorize(stringArrayList(ta.getText()), 'o');
            Corrector corr = new Corrector(ta.getText(), ta);
            corr.stringArrayList(corr.words);
            //TODO
            try {
                corr.highlightTextArea();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            ta.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (SwingUtilities.isRightMouseButton(e)) {
                        try {
                            int offset = ta.viewToModel(e.getPoint());
                            //System.out.println( ta.modelToView( offset ) );
                            int start = Utilities.getWordStart(ta, offset);
                            int end = Utilities.getWordEnd(ta, offset);
                            startNewWord = Utilities.getWordStart(ta, offset);
                            endNewWord = Utilities.getWordEnd(ta, offset);
                            word = ta.getDocument().getText(start, end - start);
                            //System.out.println( "Selected word: " + word);
                            int rowStart = Utilities.getRowStart(ta, offset);
                            int rowEnd = Utilities.getRowEnd(ta, offset);
                            //System.out.println( "Row start offset: " + rowStart );
                            //System.out.println( "Row end   offset: " + rowEnd );
                            ta.select(rowStart, rowEnd);
                            taCorrect.append(corr.updateInterfaceToDict(word));
                        } catch (Exception e2) {}
                    }
                    repaint();
                }
            });
            ta.addCaretListener(new CaretListener() {
                public void caretUpdate(CaretEvent e) {
                    int caretPosition = ta.getCaretPosition();
                    Element root = ta.getDocument().getDefaultRootElement(
                    );
                    int row = root.getElementIndex(caretPosition);
                    int column = caretPosition - root.getElement(row).getStartOffset();
                    //System.out.println( "Row   : " + ( row + 1 ) );
                    //System.out.println( "Column: " + ( column + 1 ) );
                }
            });

            ta.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    //System.out.println( ta.getDocument().getDefaultRootElement().getElementCount() );
                }
            });

            taCorrect.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (SwingUtilities.isRightMouseButton(e)) {
                        try {
                            int offset = taCorrect.viewToModel(e.getPoint());
                            //System.out.println( ta.modelToView( offset ) );
                            int start = Utilities.getWordStart(taCorrect, offset);
                            int end = Utilities.getWordEnd(taCorrect, offset);
                            word = taCorrect.getDocument().getText(start, end - start);
                            //System.out.println( "Selected word: " + word);
                            int rowStart = Utilities.getRowStart(taCorrect, offset);
                            int rowEnd = Utilities.getRowEnd(taCorrect, offset);
                            //System.out.println( "Row start offset: " + rowStart );
                            //System.out.println( "Row end   offset: " + rowEnd );
                            taCorrect.select(rowStart, rowEnd);
                            //TODO
                            // Add the words instead of hello
                            // Append to textarea
                            System.out.println("The word :" + word);
                            System.out.println(startNewWord);
                            System.out.println(endNewWord);
                            ta.replaceRange(word, startNewWord, endNewWord);
                            //ta.insert(word, startNewWord);
                            taCorrect.selectAll();
                            taCorrect.replaceSelection("");
                        } catch (Exception e2) {
                        }
                    }
                }
            });

            taCorrect.addCaretListener(new CaretListener() {
                public void caretUpdate(CaretEvent e) {
                    int caretPosition = taCorrect.getCaretPosition();
                    Element root = taCorrect.getDocument().getDefaultRootElement(
                    );
                    int row = root.getElementIndex(caretPosition);
                    int column = caretPosition - root.getElement(row).getStartOffset();
                    //System.out.println( "Row   : " + ( row + 1 ) );
                    //System.out.println( "Column: " + ( column + 1 ) );

                }
            });

            taCorrect.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    //System.out.println( ta.getDocument().getDefaultRootElement().getElementCount() );
                }
            });
        }
    }
}



