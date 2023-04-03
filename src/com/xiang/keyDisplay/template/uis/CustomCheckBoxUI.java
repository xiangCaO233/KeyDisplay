package com.xiang.keyDisplay.template.uis;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicCheckBoxUI;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class CustomCheckBoxUI extends BasicCheckBoxUI {
    private static final float focusWidth = 2.0f;

    private static final Color uncheckedBorderColor = new Color(191, 191, 191);
    private static final Color uncheckedBackgroundColor = new Color(255, 255, 255);
    private static final Color checkedBorderColor = new Color(0, 122, 204);
    private static final Color checkedBackgroundColor = new Color(0, 122, 204);

    private static final Border uncheckedBorder = new CompoundBorder(
            new MatteBorder(2, 2, 2, 2, uncheckedBorderColor),
            new MatteBorder(1, 1, 1, 1, uncheckedBackgroundColor));
    private static final Border checkedBorder = new CompoundBorder(
            new MatteBorder(2, 2, 2, 2, checkedBorderColor),
            new MatteBorder(1, 1, 1, 1, checkedBackgroundColor));

    @Override
    protected void installDefaults(AbstractButton b) {
        super.installDefaults(b);
        LookAndFeel.installProperty(b, "opaque", false);
        b.setBorderPainted(true);
        b.setBorder(uncheckedBorder);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        AbstractButton b = (AbstractButton) c;
        ButtonModel model = b.getModel();

        Dimension dim = c.getSize();
        int x = 0;
        int y = 0;
        int w = dim.width;
        int h = dim.height;

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Draw the border
        Border border = b.isSelected() ? checkedBorder : uncheckedBorder;
        b.setBorder(border);

        // Draw the check box
        g2d.setStroke(new BasicStroke(2.0f));
        g2d.setColor(b.isSelected() ? checkedBorderColor : uncheckedBorderColor);
        g2d.draw(new RoundRectangle2D.Float(x + 1, y + 1, w - 3, h - 3, 8, 8));

        // Draw the check mark
        if (model.isSelected()) {
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int size = Math.min(w, h); // 获取正方形区域的边长
            /*int padding = size / 4; // 对号离正方形边界的距离为正方形边长的1/10

            g2d.drawLine(padding, size/2, size/2, size-padding); // 画对号的第一条线段
            g2d.drawLine(size/2, size-padding, size-padding, padding); // 画对号的第二条线段*/

            int padding = size / 4; // 对号离正方形边界的距离为正方形边长的1/4


            int[] xPoints = {padding, size / 4, 3 * size / 4};
            int[] yPoints = {size / 2, size - padding / 2, padding};

            g2d.setStroke(new BasicStroke(size / 8, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.drawPolyline(xPoints, yPoints, 3); // 使用多边形绘制对号

        }

        // Draw the focus indicator
        if (b.hasFocus()) {
            g2d.setStroke(new BasicStroke(focusWidth));
            g2d.setColor(b.isSelected() ? checkedBorderColor : uncheckedBorderColor);
            g2d.draw(new RoundRectangle2D.Float(x + focusWidth / 2, y + focusWidth / 2, w - focusWidth, h - focusWidth, 8, 8));
        }

        g2d.dispose();

    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        FontMetrics fm = c.getFontMetrics(c.getFont());
        int height = fm.getHeight() + 6;
        int width = height;
        return new Dimension(width, height);
    }
}
