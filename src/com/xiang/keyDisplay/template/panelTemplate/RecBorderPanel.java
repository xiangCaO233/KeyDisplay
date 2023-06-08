package com.xiang.keyDisplay.template.panelTemplate;

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
        paintRec(g2d);
    }

    protected void paintRec(Graphics2D g2d) {
        g2d.setColor(borderColor);
        g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        g2d.setColor(backgroundColor);
        g2d.fillRect(1, 1, getWidth() - 2, getHeight() - 2);
    }
}
