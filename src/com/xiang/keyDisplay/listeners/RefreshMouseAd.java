package com.xiang.keyDisplay.listeners;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RefreshMouseAd extends MouseAdapter {
    public static RefreshMouseAd instance = new RefreshMouseAd();
    @Override
    public void mousePressed(MouseEvent e) {
        ((JComponent)(e.getComponent())).getTopLevelAncestor().repaint();
    }
}
