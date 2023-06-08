package com.xiang.keyDisplay.template.panelTemplate;

import javax.swing.*;
import java.awt.*;

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

}
