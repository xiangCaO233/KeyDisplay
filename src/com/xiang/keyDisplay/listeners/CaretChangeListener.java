package com.xiang.keyDisplay.listeners;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
