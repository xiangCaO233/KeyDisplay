package com.xiang.keyDisplay.template.panelTemplate;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RecBorderImagePanel extends RecBorderPanel implements IImagePane {
    BufferedImage image;

    public RecBorderImagePanel(Color borderColor, Color backgroundColor, BufferedImage image) {
        super(borderColor, backgroundColor);
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        paintRec(g2d);
        g2d.drawImage(image, 1, 1, null);
    }

    @Override
    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
