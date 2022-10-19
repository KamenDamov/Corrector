package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventListener;
import java.util.Iterator;
import java.util.List;

public class GUI extends JFrame implements EventListener, ActionListener {

    private JFileChooser fc;
    private ArrayList<String> texte;
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
        this.ta = new JTextArea("textarea", 100, 20);
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

    //Structure interne de notre dico
    public ArrayList<String> grabDico(ArrayList<String> chargerFichier) {
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < chargerFichier.size(); i++) {
            strBuilder.append(chargerFichier.get(i));
        }
        String str = strBuilder.toString();
        String clean = str.replaceAll("\\p{P}", "").toLowerCase();
        List<String> cleaned = new ArrayList<String>(Arrays.asList(clean.split(" ")));
        System.out.println(cleaned.toString());
        return chargerFichier;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.chooser) {
            this.chargerFichier();
            this.afficher();
        }

        if (e.getSource() == this.dictionnaire) {
            this.grabDico(chargerFichier());
        }

        if (e.getSource() == this.ecrire) {
            this.ecrireFichier();
        }
        if (e.getSource() == this.verif) {
            System.out.println("Verifier");

            //this.check();
        }
    }


}



