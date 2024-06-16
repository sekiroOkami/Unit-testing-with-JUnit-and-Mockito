package org.example;

import javax.swing.*;
import java.awt.*;

public class TextFieldFrame  {
    public JFrame getFrame() {
        return frame;
    }

    private JFrame frame;
    public TextFieldFrame() throws HeadlessException {
        init();
    }

    private void init() {
        frame = new JFrame();
        var panel = new JPanel();
        var textField = new JTextField("hi");
        textField.setName("textField");
        panel.add(textField);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    }
}
