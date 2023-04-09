package com.xiang.keyDisplay.template.uis;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicToggleButtonUI;

/**
 * 自定义ui管理器,直接修改程序默认toggleButton的各种状态颜色
 * vscode风格,但是只能干一点活,不能干很多
 * by-chatgpt
 */
public class CustomButtonUIManager {

    public static void setCustomButtonUIManager() {
        // 设置自定义颜色
        UIManager.put("ToggleButton.background", new ColorUIResource(49, 54, 59));
        UIManager.put("ToggleButton.foreground", new ColorUIResource(153, 153, 153));
        UIManager.put("ToggleButton.select", new ColorUIResource(68, 76, 86));
        UIManager.put("ToggleButton.selectBorder", new ColorUIResource(23, 24, 26));
        UIManager.put("ToggleButton.border", new ColorUIResource(38, 41, 46));

        // 设置自定义UI管理器
        UIManager.put("ToggleButtonUI", BasicToggleButtonUI.class.getName());
    }

}


