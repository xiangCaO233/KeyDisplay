package com.xiang.keyDisplay.template.panelTemplate;

import com.xiang.keyDisplay.others.GU;

import java.awt.*;

/**
 * 矩形带边框面板
 */
public class RecBorderPanel extends CustomizePanel {
    public RecBorderPanel(Color borderColor, Color backgroundColor) {
        super(borderColor, backgroundColor);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(borderColor);
        g2d.drawRect(0, 0, getWidth() - GU.absX(1), getHeight() - GU.absY(1));
        g2d.setColor(backgroundColor);
        g2d.fillRect(GU.absX(1), GU.absY(1), getWidth() - GU.absX(2), getHeight() - GU.absY(2));
    }
}
