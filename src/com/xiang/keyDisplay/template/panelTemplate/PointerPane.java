package com.xiang.keyDisplay.template.panelTemplate;

import com.xiang.keyDisplay.main.Main;
import com.xiang.keyDisplay.others.GU;

import javax.swing.*;
import java.awt.*;

public class PointerPane extends JPanel {
    public int index;

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Main.DEFAULT_BORDER_COLOR);
        /*
         我tm直接硬画
         */
        //画箭头
        //箭头顶点
        Point vertex = new Point((int) (getWidth() / 2.0), (int) (getHeight() / 10.0));
        //左顶点
        Point leftVertex = new Point((int) (getWidth() / 4.0), (int) (getHeight() / 2.0));
        //右顶点
        Point rightVertex = new Point((int) (getWidth() * (3 / 4.0)), (int) (getHeight() / 2.0));
        //左中点
        Point leftCenter = new Point((int) (getWidth() / 4.0 + getWidth() / 10.0), (int) (getHeight() / 2.0));
        //右中点
        Point rightCenter = new Point((int) (getWidth() - (getWidth() / 4.0 + getWidth() / 10.0)), (int) (getHeight() / 2.0));
        // 左底点
        Point leftBottom = new Point((int) (getWidth() / 4.0 + getWidth() / 10.0), (int) (getHeight() - (getHeight() / 10.0)));
        //右底点
        Point rightBottom = new Point((int) (getWidth() - (getWidth() / 4.0 + getWidth() / 10.0)), (int) (getHeight() - (getHeight() / 10.0)));
        //左斜线
        g2d.drawLine(vertex.x, vertex.y, leftVertex.x, leftVertex.y);
        //右斜线
        g2d.drawLine(vertex.x, vertex.y, rightVertex.x, rightVertex.y);
        //中间左
        g2d.drawLine(leftVertex.x, leftVertex.y, leftCenter.x, leftCenter.y);
        //中间右
        g2d.drawLine(rightVertex.x, rightVertex.y, rightCenter.x, rightCenter.y);
        //左竖线
        g2d.drawLine(leftCenter.x, leftCenter.y, leftBottom.x, leftBottom.y);
        //右竖线
        g2d.drawLine(rightCenter.x, rightCenter.y, rightBottom.x, rightBottom.y);
        //底部
        g2d.drawLine(leftBottom.x, leftBottom.y, rightBottom.x, rightBottom.y);
    }

    public void updateLocation(Point keyLocation, Dimension keySize, int index) {
        setLocation(
                keyLocation.x + keySize.width / 2 + index * keySize.width - getWidth() / 2,
                (keyLocation.y + keySize.height) + GU.absY(5)
        );
    }
}
