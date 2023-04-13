package com.xiang.keyDisplay.template.frameTemplate;

import com.xiang.keyDisplay.main.Main;
import com.xiang.keyDisplay.template.panelTemplate.ColorPickPane;

import java.awt.*;

public class SwingColorPicker extends CustomizeFrame {
    Color selectedColor;

    public SwingColorPicker(ColorPickPane pickerPane) {
        super(Main.DEFAULT_BORDER_COLOR, Main.DEFAULT_BG_COLOR);
        selectedColor = pickerPane.color;
        setSize(100, 80);
    }
}
