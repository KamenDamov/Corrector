package gui;

import dictionnaire.Dico;
import org.w3c.dom.events.MouseEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Objects;

public class GUI extends JFrame {

    private JTextArea textArea;
    private JLabel label;
    //Declare GUI frame
    private JLabel prop;
    private JButton dico;
    private JButton verify;
    private JButton text;
    private JButton chooseText;

    public GUI() {
        textArea = new JTextArea(30, 30);
        label = new JLabel("Corrector");
        prop = new JLabel("Proposed words");
        text = new JButton("Import Text");
        dico = new JButton("Import Dictionnary");
        verify = new JButton("Verify");

        JPanel north = new JPanel(new FlowLayout());
        JPanel center = new JPanel(new FlowLayout());
        JPanel south = new JPanel(new FlowLayout());

        north.add(text);
        center.add(dico);
        south.add(verify);

        add(north, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        //add(east, BorderLayout.EAST);
        add(south, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridLayout(3, 3));
        setVisible(true);

        text.addActionListener(e -> {
            try {
                selectFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        dico.addActionListener(e -> {
            try {
                Dico d = new Dico();
                d.dicoGood(selectFile());

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        pack();

    }

    //Add text in textarea
    public String selectFile() throws IOException {
        JFileChooser chooser = new JFileChooser();

        // optionally set chooser options ...
        StringWriter log = null;
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            //Convert file to string

            Path fileName
                    = Path.of(f.getAbsolutePath());

            // Now calling Files.readString() method to
            // read the file
            String str = Files.readString(fileName);

            // Printing the string
            System.out.println(str);
            return str;
            /*
            textArea = new JTextArea(30, 30);
            JPanel north = new JPanel(new FlowLayout());
            add(north, BorderLayout.NORTH);
            north.add(textArea);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            getContentPane().setLayout(new GridLayout(3, 3));
            setVisible(true);
            pack();
            textArea.append(str);*/
        } else {
            System.out.println("NO GOOOOOD");
        }
        return null;
    }

}



