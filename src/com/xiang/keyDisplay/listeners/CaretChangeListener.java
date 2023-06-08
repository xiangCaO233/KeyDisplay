package com.xiang.keyDisplay.listeners;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
//文本输入框光标变化监听器,刷新面板
public class CaretChangeListener implements ChangeListener {

    JComponent component;

    public CaretChangeListener(JComponent component) {
        this.component = component;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        component.getTopLevelAncestor().repaint();

    }

}
