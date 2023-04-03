package com.xiang.keyDisplay.menus;

import com.xiang.keyDisplay.main.Main;
import com.xiang.keyDisplay.template.panelTemplate.WithoutBorderPanel;
import com.xiang.keyDisplay.template.uis.CustomCheckBoxUI;
import com.xiang.keyDisplay.template.uis.CustomizedTabbedPaneUI;

import javax.swing.*;
import java.awt.*;

public class AdvanceSettingsMenu extends MenuTemplate {
    WithoutBorderPanel mouseOpts;
    WithoutBorderPanel chartOpts;
    WithoutBorderPanel countOpts;
    WithoutBorderPanel otherOpts;
    WithoutBorderPanel componentOpts;
    WithoutBorderPanel keyOpts;
    JCheckBox visible;

    public AdvanceSettingsMenu() {
        super(Main.DEFAULT_BORDER_COLOR, Main.DEFAULT_BG_COLOR);
        setSize(350, 300);

        mouseOpts = new WithoutBorderPanel();
        visible = new JCheckBox("啊啊啊啊");
        visible.setUI(new CustomCheckBoxUI());
        mouseOpts.add(visible);

        chartOpts = new WithoutBorderPanel();
        countOpts = new WithoutBorderPanel();
        otherOpts = new WithoutBorderPanel();
        componentOpts = new WithoutBorderPanel();
        keyOpts = new WithoutBorderPanel();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setSize(350, 300);
        tabbedPane.setFont(Main.DEFAULT_FONT.deriveFont(18f));
        tabbedPane.setForeground(Main.DEFAULT_BORDER_COLOR);
        tabbedPane.addTab("鼠标", null, mouseOpts, "鼠标界面设置");
        tabbedPane.addTab("按键", keyOpts);
        tabbedPane.addTab("表格", chartOpts);
        tabbedPane.addTab("计数", countOpts);
        tabbedPane.addTab("全局", componentOpts);
        tabbedPane.addTab("其他", otherOpts);

        tabbedPane.setUI(new CustomizedTabbedPaneUI());
        tabbedPane.setBackground(new Color(0, 0, 0, 0));
        addCom(tabbedPane);


    }
}
