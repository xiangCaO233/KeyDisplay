package com.xiang.keyDisplay.listeners;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//刷新监听器,当鼠标按下时刷新所在组件的界面
public class RefreshMouseAd extends MouseAdapter {
    public static RefreshMouseAd instance = new RefreshMouseAd();

    @Override
    public void mousePressed(MouseEvent e) {
        ((JComponent)(e.getComponent())).getTopLevelAncestor().repaint();
    }
}
