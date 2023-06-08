package com.xiang.keyDisplay.template.panelTemplate;

import java.awt.*;

/**
 * 圆角矩形边框面板
 */
public class RoundRecBorderPanel extends CustomizePanel {
    float roundRecScale;

    public RoundRecBorderPanel(float roundRecScale, Color borderColor, Color backgroundColor) {
        super(borderColor, backgroundColor);
        this.roundRecScale = roundRecScale;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        paintRoundRec(g2d);
    }

    protected void paintRoundRec(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(borderColor);
        g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, (int) (getWidth() * roundRecScale), (int) (getHeight() * roundRecScale));
        g2d.setColor(backgroundColor);
        g2d.fillRoundRect(1, 1, getWidth() - 3, getHeight() - 3, (int) (getWidth() * roundRecScale), (int) (getHeight() * roundRecScale));
    }
}
