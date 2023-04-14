package com.xiang.keyDisplay.template.panelTemplate;

import com.xiang.keyDisplay.others.ComponentUtils;

import javax.swing.*;
import java.awt.*;

public class SliderPane extends JPanel {
    JLabel name;
    public JSlider slider;
    public JLabel valueLabel;

    public SliderPane(String name, int value) {
        setLayout(null);
        setSize((682 - 120) / 2 - 1, 30);
        setBackground(new Color(0, 0, 0, 0));
        this.name = ComponentUtils.registerLabel(name);
        this.name.setSize(40, 30);
        this.name.setLocation(0, 0);
        add(this.name);
        slider = new JSlider();
        slider.setSize(getWidth() - 80, 30);
        slider.setLocation(40, 0);
        slider.setMaximum(255);
        slider.setMinimum(0);
        slider.setValue(value);
        slider.setBackground(new Color(0, 0, 0, 0));
        add(slider);
        valueLabel = ComponentUtils.registerLabel(String.valueOf(value));
        valueLabel.setSize(40, 40);
        valueLabel.setLocation(getWidth() - 40, 0);
        add(valueLabel);

    }
}
