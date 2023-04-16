package com.xiang.keyDisplay.template.panelTemplate;

import com.xiang.keyDisplay.others.GU;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RoundRecBorderImagePanel extends RoundRecBorderPanel {
    BufferedImage image;

    public RoundRecBorderImagePanel(Color borderColor, Color backgroundColor, float roundRecScale, BufferedImage image) {
        super(roundRecScale, borderColor, backgroundColor);
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        paintRoundRec(g2d);
        g2d.drawImage(image, GU.absX(1), GU.absY(1), null);
    }
}
