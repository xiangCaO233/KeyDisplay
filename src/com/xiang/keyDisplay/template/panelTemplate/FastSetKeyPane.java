package com.xiang.keyDisplay.template.panelTemplate;

import com.xiang.keyDisplay.main.Main;
import com.xiang.keyDisplay.others.GU;

import javax.swing.*;
import java.awt.*;

public class FastSetKeyPane extends JPanel {
    public String key = "";

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Main.DEFAULT_BORDER_COLOR);
        g2d.drawRect(0, 0, getWidth() - GU.absX(1), getHeight() - GU.absY(1));
        g2d.setColor(Main.DEFAULT_BG_COLOR);
        g2d.fillRect(GU.absX(1), GU.absY(1), getWidth() - GU.absX(2), getHeight() - GU.absY(2));
        g2d.setFont(Main.DEFAULT_FONT.deriveFont(20f));
        g2d.setColor(Main.DEFAULT_BORDER_COLOR);
        int keyWidth = g2d.getFontMetrics().stringWidth(key);
        g2d.drawString(key, (getWidth() - keyWidth) / 2, (getHeight() + GU.absY(18)) / 2);
    }
}
