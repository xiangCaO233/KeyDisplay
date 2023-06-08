package com.xiang.keyDisplay.template.panelTemplate;

import com.xiang.keyDisplay.others.GU;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 颜色块面板,包括悬浮事件
 */
public class ColorBlockPanel extends JPanel {
    boolean isInBlockPanel;
    Color currentColor;

    public ColorBlockPanel(Color color) {
        currentColor = color;
        setColorSize(24, 24);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isInBlockPanel = true;
                getTopLevelAncestor().repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isInBlockPanel = false;
                getTopLevelAncestor().repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(currentColor);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(GU.antiColor(currentColor));
        g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        if (isInBlockPanel) {
            g2d.drawRect(1, 1, getWidth() - 3, getHeight() - 3);
            g2d.drawRect(2, 2, getWidth() - 5, getHeight() - 5);
        }
    }

    /**
     * 自动适配大小,对照1080p分辨率
     *
     * @param width
     * @param height
     */
    public void setColorSize(int width, int height) {
        setMaximumSize(
                new Dimension(width, height)
        );
        setMinimumSize(
                new Dimension(width, height)
        );
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }
}
