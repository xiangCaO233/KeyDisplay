package com.xiang.keyDisplay.template.panelTemplate;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RoundRecBorderImagePanel extends CustomizePanel {
    float roundScale;
    BufferedImage image;

    public RoundRecBorderImagePanel(Color borderColor, Color backgroundColor, float roundScale, BufferedImage image) {
        super(borderColor, backgroundColor);
        this.roundScale = roundScale;
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(borderColor);
        g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, (int) (getWidth() * roundScale), (int) (getHeight() * roundScale));
        g2d.setColor(backgroundColor);
        g2d.fillRoundRect(1, 1, getWidth() - 3, getHeight() - 3, (int) (getWidth() * roundScale), (int) (getHeight() * roundScale));
        g2d.drawImage(image, 1, 1, null);
    }
}
