package com.xiang.keyDisplay.template.panelTemplate;

import com.xiang.keyDisplay.main.Main;
import com.xiang.keyDisplay.template.frameTemplate.SwingColorPicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * 颜色选择容器.包括标题和修改功能显示的容器
 */

public class ColorPickPane extends JPanel {
    JLabel title;
    ColorBlockPanel colorPane;
    public Color color;

    public ColorPickPane(String title) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(new Color(0, 0, 0, 0));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        this.title = new JLabel(title);
        this.title.setFont(Main.DEFAULT_FONT.deriveFont(18f));
        this.title.setBackground(new Color(0, 0, 0, 0));
        this.title.setForeground(Main.DEFAULT_BORDER_COLOR);
        colorPane = new ColorBlockPanel(color);
        colorPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (Main.swingColorPicker != null) {
                    Main.swingColorPicker.dispose();
                    Main.allMenus.remove(Main.swingColorPicker);
                    Main.advanceSettingsMenu.childMenus.remove(Main.swingColorPicker);
                    Main.swingColorPicker = null;
                }
                Main.swingColorPicker = new SwingColorPicker(
                        ((ColorPickPane) ((JPanel) e.getSource()).getParent())
                );
                Main.allMenus.add(Main.swingColorPicker);
                Main.advanceSettingsMenu.childMenus.add(Main.swingColorPicker);
                Main.swingColorPicker.setVisible(true);
                int x = Main.mainMenu.getX() + Main.mainMenu.getWidth();
                int y = Main.mainMenu.getY();
                if (Main.advanceSettingsMenu.getX() + Main.advanceSettingsMenu.getWidth() > x)
                    x = Main.advanceSettingsMenu.getX() + Main.advanceSettingsMenu.getWidth();
                Main.swingColorPicker.setLocation(x, y);
            }
        });

        add(this.title);
        add(colorPane);

    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Main.DEFAULT_BORDER_COLOR);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }

    public void setColor(Color color) {
        this.color = color;
        colorPane.setCurrentColor(color);
    }

    public Color getColor() {
        return color;
    }
}
