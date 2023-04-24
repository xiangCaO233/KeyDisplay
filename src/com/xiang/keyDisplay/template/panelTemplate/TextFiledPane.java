package com.xiang.keyDisplay.template.panelTemplate;

import com.xiang.keyDisplay.main.Main;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class TextFiledPane extends JPanel {
    JLabel title;
    JTextField field;

    public TextFiledPane(String title, String text) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(new Color(0, 0, 0, 0));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        this.title = new JLabel(title);
        this.title.setFont(Main.DEFAULT_FONT.deriveFont(18f));
        this.title.setBackground(new Color(0, 0, 0, 0));
        this.title.setForeground(Main.DEFAULT_BORDER_COLOR);
        add(this.title);
        field = new JTextField();
        field.setMargin(new Insets(0, 2, 0, 0));
        field.setBackground(new Color(0, 0, 0, 0));
        field.setForeground(Main.DEFAULT_BORDER_COLOR);
        field.setFont(Main.DEFAULT_FONT.deriveFont(18f));
        field.setBorder(null);
        field.setText(text);
        add(field);
        field.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                //插入更新
                field.getTopLevelAncestor().repaint();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                //删除更新
                field.getTopLevelAncestor().repaint();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                //纯更新
                field.getTopLevelAncestor().repaint();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                field.getTopLevelAncestor().repaint();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                field.getTopLevelAncestor().repaint();
            }
        });
    }

    void setText(String text) {
        field.setText(text);
    }
}
