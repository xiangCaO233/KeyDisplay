package com.xiang.keyDisplay.others;

import com.xiang.keyDisplay.main.Main;

import javax.swing.*;
import java.awt.*;

public class ComponentUtils {
    /**
     * 注册基本标签,居中,字体大小18f,不设置位置大小
     *
     * @param text 文本显示
     * @return 返回设置完成的标签
     */
    public static JLabel registerLabel(String text) {
        JLabel label = new JLabel(text, JLabel.CENTER);
        label.setFont(Main.DEFAULT_FONT.deriveFont(18f));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(new Color(230, 230, 230, 230));
        return label;
    }

    /**
     * 注册基本按钮,取消焦点框,字体大小16f,不设置位置大小
     *
     * @param text 按钮显示名
     * @return 返回设置完成的按钮
     */
    public static JButton registerButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(Main.DEFAULT_BORDER_COLOR);
        button.setBackground(Main.DEFAULT_BG_COLOR);
        button.setFont(Main.DEFAULT_FONT.deriveFont(16f));
        button.setFocusPainted(false);
        return button;
    }

    public static void setFontSize(Component component, float size) {
        component.setFont(component.getFont().deriveFont(size));
    }
}
