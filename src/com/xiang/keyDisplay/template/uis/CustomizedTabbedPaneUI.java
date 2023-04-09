package com.xiang.keyDisplay.template.uis;

import com.xiang.keyDisplay.main.Main;

import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

public class CustomizedTabbedPaneUI extends BasicTabbedPaneUI {
    @Override
    protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        super.paintTabBackground(g, tabPlacement, tabIndex, x, y, w, h, isSelected);
        g.setColor(!isSelected ?
                Main.DEFAULT_BG_COLOR : Main.DEFAULT_PRESS_COLOR);
        switch (tabPlacement) {
            case LEFT:
                g.fillRect(x + 1, y + 1, w - 1, h - 3);
                break;
            case RIGHT:
                g.fillRect(x, y + 1, w - 2, h - 3);
                break;
            case BOTTOM:
                g.fillRect(x + 1, y, w - 3, h - 1);
                break;
            case TOP:
            default:
                g.fillRect(x + 1, y + 1, w - 3, h - 1);
        }
    }

    @Override
    protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
        //写死1边距
        int width = tabPane.getWidth();
        int height = tabPane.getHeight();
        int x = 1;
        int y = 1;
        int w = width - 1 - 1;
        int h = height - 1 - 1;
        switch (tabPlacement) {
            case LEFT:
                x += calculateTabAreaWidth(tabPlacement, runCount, maxTabWidth);
                w -= (x - 1);
                break;
            case RIGHT:
                w -= calculateTabAreaWidth(tabPlacement, runCount, maxTabWidth);
                //w += tabAreaInsets.left;

                break;
            case BOTTOM:
                h -= calculateTabAreaHeight(tabPlacement, runCount, maxTabHeight);
                //h += tabAreaInsets.top;

                break;
            case TOP:
            default:
                y += calculateTabAreaHeight(tabPlacement, runCount, maxTabHeight);
                //y -= tabAreaInsets.bottom;
                h -= (y - 1);
        }
        // Fill region behind content area
        g.setColor(Main.DEFAULT_BG_COLOR);
        g.fillRect(x, y, w, h);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Main.DEFAULT_BORDER_COLOR);
        g2d.setStroke(new BasicStroke(3f));
        g2d.drawLine(x, y, x + width, y);

    }

    @Override
    protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        g.setColor(lightHighlight);

        switch (tabPlacement) {
            case LEFT:
                g.drawLine(x + 1, y + h - 2, x + 1, y + h - 2); // bottom-left highlight
                g.drawLine(x, y + 2, x, y + h - 3); // left highlight
                g.drawLine(x + 1, y + 1, x + 1, y + 1); // top-left highlight
                g.drawLine(x + 2, y, x + w - 1, y); // top highlight

                g.setColor(shadow);
                g.drawLine(x + 2, y + h - 2, x + w - 1, y + h - 2); // bottom shadow

                g.setColor(darkShadow);
                g.drawLine(x + 2, y + h - 1, x + w - 1, y + h - 1); // bottom dark shadow
                break;
            case RIGHT:
                g.drawLine(x, y, x + w - 3, y); // top highlight

                g.setColor(shadow);
                g.drawLine(x, y + h - 2, x + w - 3, y + h - 2); // bottom shadow
                g.drawLine(x + w - 2, y + 2, x + w - 2, y + h - 3); // right shadow

                g.setColor(darkShadow);
                g.drawLine(x + w - 2, y + 1, x + w - 2, y + 1); // top-right dark shadow
                g.drawLine(x + w - 2, y + h - 2, x + w - 2, y + h - 2); // bottom-right dark shadow
                g.drawLine(x + w - 1, y + 2, x + w - 1, y + h - 3); // right dark shadow
                g.drawLine(x, y + h - 1, x + w - 3, y + h - 1); // bottom dark shadow
                break;
            case BOTTOM:
                g.drawLine(x, y, x, y + h - 3); // left highlight
                g.drawLine(x + 1, y + h - 2, x + 1, y + h - 2); // bottom-left highlight

                g.setColor(shadow);
                g.drawLine(x + 2, y + h - 2, x + w - 3, y + h - 2); // bottom shadow
                g.drawLine(x + w - 2, y, x + w - 2, y + h - 3); // right shadow

                g.setColor(darkShadow);
                g.drawLine(x + 2, y + h - 1, x + w - 3, y + h - 1); // bottom dark shadow
                g.drawLine(x + w - 2, y + h - 2, x + w - 2, y + h - 2); // bottom-right dark shadow
                g.drawLine(x + w - 1, y, x + w - 1, y + h - 3); // right dark shadow
                break;
            case TOP:
            default:
                g.drawLine(x, y + 2, x, y + h - 1); // left highlight
                g.drawLine(x + 1, y + 1, x + 1, y + 1); // top-left highlight
                g.drawLine(x + 2, y, x + w - 3, y); // top highlight

                g.setColor(shadow);
                g.drawLine(x + w - 2, y + 2, x + w - 2, y + h - 1); // right shadow

                g.setColor(darkShadow);
                g.drawLine(x + w - 1, y + 2, x + w - 1, y + h - 1); // right dark-shadow
                g.drawLine(x + w - 2, y + 1, x + w - 2, y + 1); // top-right shadow
        }
    }

    @Override
    protected void paintTab(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect) {
        super.paintTab(g, tabPlacement, rects, tabIndex, iconRect, textRect);
    }

    @Override
    protected LayoutManager createLayoutManager() {
        return new CustomizedTabbedPaneLayout();
    }

    public class CustomizedTabbedPaneLayout extends BasicTabbedPaneUI.TabbedPaneLayout {
        @Override
        protected void calculateTabRects(int tabPlacement, int tabCount) {
            super.calculateTabRects(tabPlacement, tabCount);
            setRec(0);
            tabInsets.bottom = -1;
        }

        void setRec(int inset) {
            for (int i = 0; i < rects.length; i++) {
                rects[i].x += (inset * i);
            }
        }

    }
}
