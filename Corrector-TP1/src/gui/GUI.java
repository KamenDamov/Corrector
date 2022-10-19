package gui;

import dictionnaire.Dico;
import text.Text;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.EventListener;

public class GUI extends JFrame implements EventListener {

    protected JTextArea textArea;
    protected JLabel label;
    //Declare GUI frame
    private JLabel prop;
    private JButton dico;
    private JButton verify;
    protected JButton text;
    private JButton chooseText;

    public GUI() {
        textArea = new JTextArea(300, 300);
        label = new JLabel("Corrector");
        //prop = new JLabel("Proposed words");
        text = new JButton("Import Text");
        dico = new JButton("Import Dictionnary");
        verify = new JButton("Verify");
        add(text);
        add(dico);
        add(verify);
        add(textArea);
        /*
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
        */
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridLayout(3, 3, 20,25));
        setVisible(true);
        pack();

        text.addActionListener(e -> {
            try {
                Text t = new Text();
                t.newInterface(selectFile());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        dico.addActionListener(e -> {
            try {
                Dico.dicoGood(selectFile());
                //d.dicoGood();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        verify.addActionListener(e -> {
            try {
                Dico.check();
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
            //System.out.println(str);
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
        return "";
    }

}



