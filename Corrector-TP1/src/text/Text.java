package text;

import gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.EventListener;

public class Text extends GUI implements EventListener {

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

    public static String actionPerformed(MouseEvent e) {
        return text;
    }
}
