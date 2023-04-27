package com.xiang.keyDisplay.listeners;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DocumentsChangeListener implements DocumentListener {
    JComponent component;


    public DocumentsChangeListener(JComponent component) {
        this.component = component;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        //插入更新
        component.getTopLevelAncestor().repaint();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        //删除更新
        component.getTopLevelAncestor().repaint();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        //纯更新
        component.getTopLevelAncestor().repaint();
    }
}
