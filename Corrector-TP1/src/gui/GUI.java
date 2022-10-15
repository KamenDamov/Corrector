package gui;

import org.w3c.dom.events.MouseEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Locale;
import java.util.Objects;

public class GUI extends JFrame {

    private JTextArea textArea;
    private JLabel label;
    //Declare GUI frame
    private JLabel prop;
    private JButton file;

    public GUI() {
        textArea = new JTextArea(30, 30);
        label = new JLabel("Corrector");
        prop = new JLabel("Proposed words");
        file = new JButton("Import");

        JPanel north = new JPanel(new FlowLayout());
        JPanel center = new JPanel(new FlowLayout());
        JPanel east = new JPanel(new FlowLayout());
        JPanel south = new JPanel(new FlowLayout());

        north.add(label);
        center.add(textArea);
        east.add(prop);
        south.add(file);

        add(north, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(east, BorderLayout.EAST);
        add(south, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        file.addActionListener(e -> {
            selectFile();
        });

        pack();
    }

    public void selectFile() {
        JFileChooser chooser = new JFileChooser();
        // optionally set chooser options ...
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            // read  and/or display the file somehow. ....
        } else {
            // user changed their mind
        }
    }
}


