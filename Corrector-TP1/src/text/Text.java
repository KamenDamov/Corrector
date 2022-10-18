package text;

import gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Text extends GUI {

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
        textArea.append(String.valueOf(this.text));
        pack();
    }

}
