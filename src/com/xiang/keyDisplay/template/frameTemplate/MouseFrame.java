package com.xiang.keyDisplay.template.frameTemplate;

import com.xiang.keyDisplay.main.Main;

import javax.swing.*;
import java.awt.*;

/**
 * 鼠标界面
 * 名
 * cps
 * 点击统计
 */
public class MouseFrame extends CustomizeFrame{
    String mouseKeyName;
    JLabel[] labels;
    int cps;
    int count;
    public MouseFrame() throws HeadlessException {
        super(Main.DEFAULT_BORDER_COLOR, Main.DEFAULT_BG_COLOR, (float) (1 / 6.0));

    }
}
