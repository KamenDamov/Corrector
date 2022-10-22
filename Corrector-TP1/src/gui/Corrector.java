package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.EventListener;

public class Corrector extends GUI implements EventListener, ActionListener {
    //TODO
    // This class will produce new interface adding new textarea with buttons
    protected JTextArea taprop;
    public Corrector(){
        super();
        JPanel bas = new JPanel();
        this.taprop = new JTextArea("Hey", 300, 30);
        bas.add(this.taprop);
        taprop.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    }

}
