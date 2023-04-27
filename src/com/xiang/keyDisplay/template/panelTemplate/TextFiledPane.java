package com.xiang.keyDisplay.template.panelTemplate;

import com.xiang.keyDisplay.listeners.CaretChangeListener;
import com.xiang.keyDisplay.listeners.DocumentsChangeListener;
import com.xiang.keyDisplay.listeners.RefreshMouseAd;
import com.xiang.keyDisplay.main.Main;

import javax.swing.*;
import java.awt.*;

/**
 * 文本域容器
 * 带title标签和一个文本域
 */
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
        field.getDocument().addDocumentListener(new DocumentsChangeListener(field));
        field.getCaret().addChangeListener(new CaretChangeListener(field));
        add(field);
        addMouseListener(RefreshMouseAd.instance);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Main.DEFAULT_BORDER_COLOR);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }

    void setText(String text) {
        field.setText(text);
    }
}
