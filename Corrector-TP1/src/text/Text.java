package text;

import dictionnaire.Dico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.EventListener;

public class Text extends Dico implements EventListener {

    private static String content;

    public Text() {
        super(content);
    }

    public void newInterface(String t) throws IOException {
        textArea.append(content);
        /*JPanel north = new JPanel(new FlowLayout());
        JPanel center = new JPanel(new FlowLayout());

        north.add(label);
        center.add(textArea);

        add(north, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridLayout(3, 3));
        setVisible(true);
        textArea.append(String.valueOf(this.text));
        pack();*/
    }

    public void actionPerformed(MouseEvent e) throws IOException {
        FileWriter logg = new FileWriter("logsheet.txt",true);
        textArea.write(logg);
    }
}
