package org.example;

import javax.swing.*;
import java.awt.*;

public class MainFrame {
    private JFrame frame;

    public MainFrame() {
        init();
    }

    private void init() {
        frame = new JFrame();
        var mainPanel = new JPanel();
        var jtextField = new JTextField();
        jtextField.setName("jtextField1");
        mainPanel.add(jtextField);
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }
}
