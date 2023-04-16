package com.xiang.keyDisplay.template.panelTemplate;

import com.xiang.keyDisplay.others.ComponentUtils;
import com.xiang.keyDisplay.others.GU;

import javax.swing.*;
import java.awt.*;

/**
 * 滑动组件容器,包含显示名,滑动组件,值标签
 */
public class SliderPane extends JPanel {
    JLabel name;
    public JSlider slider;
    public JLabel valueLabel;

    public SliderPane(String name, int value) {

        setLayout(null);
        setSize(GU.toAbsSize((682 - 120) / 2 - 1, 30));
        setBackground(new Color(0, 0, 0, 0));
        this.name = ComponentUtils.registerLabel(name);
        this.name.setSize(GU.toAbsSize(40, 30));
        this.name.setLocation(0, 0);
        add(this.name);
        //初始化滑动组件
        slider = new JSlider();
        slider.setSize(getWidth() - GU.absX(80), GU.absY(30));
        slider.setLocation(GU.toAbsPos(40, 0));
        slider.setMaximum(255);
        slider.setMinimum(0);
        slider.setValue(value);
        slider.setBackground(new Color(0, 0, 0, 0));
        add(slider);
        //初始化值显示标签
        valueLabel = ComponentUtils.registerLabel(String.valueOf(value));
        valueLabel.setSize(GU.toAbsSize(40, 40));
        valueLabel.setLocation(getWidth() - GU.absX(40), 0);
        add(valueLabel);

    }
}
