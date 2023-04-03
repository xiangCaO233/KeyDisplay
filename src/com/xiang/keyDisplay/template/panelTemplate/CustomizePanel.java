package com.xiang.keyDisplay.template.panelTemplate;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 自定义面板
 */
public class CustomizePanel extends JPanel {
    Color borderColor;
    Color backgroundColor;

    public CustomizePanel(Color borderColor, Color backgroundColor) {
        setLayout(null);
        this.borderColor = borderColor;
        this.backgroundColor = backgroundColor;

    }

    /**
     * 设置容器背景颜色
     *
     * @param color
     */
    public void setBackgroundColor(Color color) {
        backgroundColor = color;
    }

    /**
     * 设置容器边框颜色
     *
     * @param color
     */
    public void setBorderColor(Color color) {
        backgroundColor = color;
    }

    /**
     * 仅适用背景带图片的面板
     * RoundRecBorderImagePanel
     * 对其他类型使用会抛出类转换异常
     *
     * @param image
     */
    public void setImage(BufferedImage image) throws Exception {

        ((RoundRecBorderImagePanel) this).image = image;
    }
}
