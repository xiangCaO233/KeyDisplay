package com.xiang.keyDisplay.template.panelTemplate;

import com.xiang.keyDisplay.main.Main;
import com.xiang.keyDisplay.others.GU;
import com.xiang.keyDisplay.template.uis.CustomizeToggleButton;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class MouseOptPanel extends JPanel {
    JPanel buttonListVbox;
    CustomizeToggleButton[] mousesButtons;
    JPanel settingsPanel;

    ColorPickPane bgPicker;

    public MouseOptPanel() {

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(Main.DEFAULT_BG_COLOR);

        buttonListVbox = new JPanel();
        buttonListVbox.setBackground(new Color(0, 0, 0, 0));
        buttonListVbox.setLayout(new BoxLayout(buttonListVbox, BoxLayout.Y_AXIS));
        buttonListVbox.setMaximumSize(GU.toAbsSize(Integer.MAX_VALUE / 5, Integer.MAX_VALUE));
        add(buttonListVbox);

        bgPicker = new ColorPickPane("背景颜色");

        settingsPanel = new JPanel();
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
        settingsPanel.setMaximumSize(GU.toAbsSize(Integer.MAX_VALUE, Integer.MAX_VALUE));
        settingsPanel.setBackground(new Color(0, 0, 0, 0));
        settingsPanel.add(bgPicker);
        add(settingsPanel);

        mousesButtons = new CustomizeToggleButton[Main.mouseFrames.size()];
        Collection<Integer> mouseKeys = Main.mouseFrames.keySet();
        int index = 0;
        for (Integer mouseKey : mouseKeys) {
            mousesButtons[index] = new CustomizeToggleButton(Main.mouseFrames.get(mouseKey).mouseKeyName);
            mousesButtons[index].setMaximumSize(GU.toAbsSize(Integer.MAX_VALUE, 30));
            buttonListVbox.add(mousesButtons[index]);
            index++;
        }
    }
}
