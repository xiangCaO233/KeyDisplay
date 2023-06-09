package com.xiang.keyDisplay.template.panelTemplate;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RoundRecBorderImagePanel extends RoundRecBorderPanel implements IImagePane {
    BufferedImage image;

    public RoundRecBorderImagePanel(Color borderColor, Color backgroundColor, float roundRecScale, BufferedImage image) {
        super(roundRecScale, borderColor, backgroundColor);
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        paintRoundRec(g2d);
        g2d.drawImage(image, 1, 1, null);
    }

    @Override
    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
