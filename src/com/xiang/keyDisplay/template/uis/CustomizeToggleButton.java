package com.xiang.keyDisplay.template.uis;

import com.xiang.keyDisplay.main.Main;

import javax.swing.*;
import java.awt.*;

/**
 * 自定义切换按钮,也只能干一点,好像必须UIManager那边干一点活这边才能干活
 * by-chatgpt
 */
public class CustomizeToggleButton extends JToggleButton {

    public CustomizeToggleButton(String text) {
        super(text);
        setFocusPainted(false);
        setFont(Main.DEFAULT_FONT.deriveFont(18f));
        setForeground(Main.DEFAULT_BORDER_COLOR);
        setBackground(Main.DEFAULT_BG_COLOR);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, Main.DEFAULT_BORDER_COLOR),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        addActionListener(e -> {
            if (isSelected()) {
                //按钮选中
                setBackground(Main.DEFAULT_PRESS_COLOR);
                System.out.println(e.getSource());


            } else {
                setBackground(Main.DEFAULT_BG_COLOR);
            }
            setForeground(Main.DEFAULT_BORDER_COLOR);
            setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(1, 1, 1, 1, Main.DEFAULT_BORDER_COLOR),
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)
            ));
            revalidate(); // 更新UI
            getTopLevelAncestor().repaint(); // 重绘组件
        });
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        size.width += 20;
        return size;
    }
}

