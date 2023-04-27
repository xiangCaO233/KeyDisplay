package com.xiang.keyDisplay.template.uis;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSpinnerUI;
import java.awt.*;

public class VSCodeSpinnerUI extends BasicSpinnerUI {

    protected JComponent createEditor() {
        JTextField editor = new JTextField("0");
        editor.setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 4));
        editor.setBackground(Color.BLACK);
        editor.setForeground(Color.WHITE);
        editor.setHorizontalAlignment(JTextField.CENTER);
        return editor;
    }

    protected Component createNextButton() {
        JButton nextButton = new JButton("▲");
        nextButton.setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 10));
        nextButton.setBackground(Color.BLACK);
        nextButton.setForeground(Color.WHITE);
        nextButton.setFocusable(false);
        return nextButton;
    }

    protected Component createPreviousButton() {
        JButton previousButton = new JButton("▼");
        previousButton.setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 10));
        previousButton.setBackground(Color.BLACK);
        previousButton.setForeground(Color.WHITE);
        previousButton.setFocusable(false);
        return previousButton;
    }

}

