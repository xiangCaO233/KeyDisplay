package com.xiang.keyDisplay.template.panelTemplate;

import com.xiang.keyDisplay.main.Main;
import com.xiang.keyDisplay.others.GraphicUtils;
import com.xiang.keyDisplay.template.frameTemplate.SwingColorPicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ColorPickPane extends JPanel {
    JLabel title;
    JPanel colorPane;
    public Color color;
    boolean isInColorPane;

    public ColorPickPane(String title) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(new Color(0, 0, 0, 0));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        this.title = new JLabel(title);
        this.title.setFont(Main.DEFAULT_FONT.deriveFont(18f));
        this.title.setBackground(new Color(0, 0, 0, 0));
        this.title.setForeground(Main.DEFAULT_BORDER_COLOR);
        color = Main.DEFAULT_BG_COLOR;
        colorPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(color);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.setColor(GraphicUtils.antiColor(color));
                g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
                if (isInColorPane) {
                    g2d.drawRect(1, 1, getWidth() - 3, getHeight() - 3);
                    g2d.drawRect(2, 2, getWidth() - 5, getHeight() - 5);
                }
            }
        };
        colorPane.setMaximumSize(new Dimension(26, 26));
        colorPane.setSize(30, 30);
        colorPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("az");
                SwingColorPicker picker = new SwingColorPicker(((ColorPickPane) ((JPanel) e.getSource()).getParent()));
                picker.setVisible(true);
                picker.setLocation(e.getLocationOnScreen());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                isInColorPane = true;
                colorPane.getTopLevelAncestor().repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isInColorPane = false;
                colorPane.getTopLevelAncestor().repaint();
            }
        });

        add(this.title);
        add(colorPane);

    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
