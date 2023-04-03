package com.xiang.keyDisplay.template.panelTemplate;

import com.xiang.keyDisplay.main.Main;

import javax.swing.*;
import java.awt.*;

public class WithoutBorderPanel extends JPanel {
    Color bg;

    public WithoutBorderPanel() {
        this.bg = Main.DEFAULT_BG_COLOR;
        setLayout(new FlowLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(bg);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public void setBg(Color bg) {
        this.bg = bg;
    }
}
