package com.xiang.keyDisplay.template.panelTemplate;

import com.xiang.keyDisplay.main.Main;
import com.xiang.keyDisplay.others.GU;
import com.xiang.keyDisplay.template.uis.CustomizeToggleButton;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.Set;

public class MouseOptPanel extends JPanel {
    JPanel buttonListVbox;
    CustomizeToggleButton[] mousesButtons;
    JPanel settingsPanel1;
    JPanel settingsPanel2;

    ColorPickPane bgPicker;
    ColorPickPane activePicker;
    TextFiledPane fontSetting;
    TextFiledPane fontSizeSetting;


    public MouseOptPanel() {

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(Main.DEFAULT_BG_COLOR);

        buttonListVbox = new JPanel();
        buttonListVbox.setBackground(new Color(0, 0, 0, 0));
        buttonListVbox.setLayout(new BoxLayout(buttonListVbox, BoxLayout.Y_AXIS));
        buttonListVbox.setMaximumSize(GU.toAbsSize(Integer.MAX_VALUE / 5, Integer.MAX_VALUE));
        add(buttonListVbox);

        bgPicker = new ColorPickPane("背景颜色");
        bgPicker.setColor(Main.DEFAULT_BG_COLOR);
        activePicker = new ColorPickPane("触发颜色");
        activePicker.setColor(Main.DEFAULT_PRESS_COLOR);
        fontSetting = new TextFiledPane("字体:", "Default Font");
        //取最后一个鼠标按键的字体大小
        Set<Integer> keySet = Main.mouseFrames.keySet();
        int key = -1;
        for (Integer i : keySet) {
            key = i;
        }
        fontSizeSetting = new TextFiledPane(
                "字体大小:",
                (key == -1 ? String.valueOf(0) : String.valueOf(Main.mouseFrames.get(key).labels[0].getFont().getSize2D()))
        );

        settingsPanel1 = new JPanel();
        settingsPanel1.setLayout(new BoxLayout(settingsPanel1, BoxLayout.Y_AXIS));
        settingsPanel1.setMaximumSize(GU.toAbsSize(Integer.MAX_VALUE, Integer.MAX_VALUE));
        settingsPanel1.setBackground(new Color(0, 0, 0, 0));
        settingsPanel1.add(bgPicker);
        settingsPanel1.add(fontSetting);
        settingsPanel1.add(fontSizeSetting);

        add(settingsPanel1);

        settingsPanel2 = new JPanel();
        settingsPanel2.setLayout(new BoxLayout(settingsPanel2, BoxLayout.Y_AXIS));
        settingsPanel2.setMaximumSize(GU.toAbsSize(Integer.MAX_VALUE, Integer.MAX_VALUE));
        settingsPanel2.setBackground(new Color(0, 0, 0, 0));
        settingsPanel2.add(activePicker);
        add(settingsPanel2);

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
