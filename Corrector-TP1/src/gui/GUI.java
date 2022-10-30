package gui;

import dictionnaire.Dico;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class GUI extends JFrame implements EventListener, ActionListener {

    JScrollPane scrollPane;
    protected JFileChooser fc;
    protected ArrayList<String> texte;
    protected ArrayList<String> texteDico;
    protected ArrayList<String> texteAVerif;
    protected JTextArea ta;
    protected JButton chooser;
    protected JButton dictionnaire;
    protected JButton ecrire;
    protected JButton verif;
    protected JTextArea taCorrect;
    protected JList<String> list;
    protected int startNewWord;
    protected int endNewWord;
    protected JScrollPane sp;
    protected JMenu menu;
    protected JMenuItem word2;

    String word;
    String word1;
    Corrector corr;
    Dico dico;

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
        this.menu = new JMenu();
        this.word2 = new JMenuItem("Bonjour");
        this.ta = new JTextArea("textarea", 700, 70);
        this.sp = new JScrollPane(ta,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        bas.add(this.sp);
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
        this.corr = new Corrector();
        this.dico = new Dico();

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridLayout(3, 3));
        setVisible(true);
        pack();

        ta.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    try {
                        taCorrect.selectAll();
                        taCorrect.replaceSelection("");
                        int offset = ta.viewToModel(e.getPoint());
                        int start = Utilities.getWordStart(ta, offset);
                        int end = Utilities.getWordEnd(ta, offset);
                        startNewWord = Utilities.getWordStart(ta, offset);
                        endNewWord = Utilities.getWordEnd(ta, offset);
                        word = ta.getDocument().getText(start, end - start);
                        int rowStart = Utilities.getRowStart(ta, offset);
                        int rowEnd = Utilities.getRowEnd(ta, offset);
                        ta.select(rowStart, rowEnd);
                        taCorrect.append(corr.updateInterfaceToDict(word, ta.getText()));

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
            }
        });

        ta.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
            }
        });

        taCorrect.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    try {
                        int offset = taCorrect.viewToModel(e.getPoint());
                        int start = Utilities.getWordStart(taCorrect, offset);
                        int end = Utilities.getWordEnd(taCorrect, offset);
                        word1 = taCorrect.getDocument().getText(start, end - start);
                        int rowStart = Utilities.getRowStart(taCorrect, offset);
                        int rowEnd = Utilities.getRowEnd(taCorrect, offset);
                        taCorrect.select(rowStart, rowEnd);
                        ta.replaceRange(word1, startNewWord, endNewWord);
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
            }
        });

        taCorrect.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
            }
        });
    }


    //Méthode pour charger un fichier
    //Produit une ArrayList de tout les mots dans le fichier
    //2 chemins possibles: Soit une entrée pour créer une ArrayList pour les mots du texte, et une entrée
    //pour les mots du dictionnaire.
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

            return this.texte;
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

            return this.texteDico;
        }
    }

    //Méthode pour sauvegarder un texte.
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

    //Methode pour afficher du texte, notamment lorsqu'on importe du texte
    public void afficher(ArrayList<String> t) {
        if (t != null) {
            Iterator var1 = t.iterator();

            while (var1.hasNext()) {
                String s = (String) var1.next();
                ta.append(s + "\n");
            }
        }
    }

    //Méthode qui active les multiples actions suite au clique d'un des 4 boutons
    public void actionPerformed(ActionEvent e) {
        //Clique sur le bouton choisir
        if (e.getSource() == this.chooser) {
            texte = chargerFichier('t');
            ta.selectAll();
            ta.replaceSelection("");
            this.afficher(texte);
        }

        //Clique sur le bouton dictionnaire
        if (e.getSource() == this.dictionnaire) {
            texteDico.clear();
            texteDico = chargerFichier('n');
            dico.readDico(texteDico);
        }

        //Sauvegarder un fichier
        if (e.getSource() == this.ecrire) {
            this.ecrireFichier();
        }

        //Lancer une vérification
        if (e.getSource() == this.verif) {
            try {
                corr.highlightTextArea(ta.getText(), ta);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}




