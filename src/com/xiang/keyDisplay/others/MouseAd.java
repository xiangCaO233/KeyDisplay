package com.xiang.keyDisplay.others;

import com.xiang.keyDisplay.main.Main;
import com.xiang.keyDisplay.menus.MenuFrame;
import com.xiang.keyDisplay.template.frameTemplate.KeyFrame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;

import static com.xiang.keyDisplay.others.MouseMo.*;

/**
 * 鼠标监听器
 */
public class MouseAd extends MouseAdapter {
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 1) {
            mouseInFrameTemp = e.getPoint();
            Point screenMouse = e.getLocationOnScreen();
            mouseRelativePointTemp = new ArrayList<>();
            Collection<KeyFrame> allKeyFrames = Main.keyFrames.values();
            for (KeyFrame keyFrame : allKeyFrames) {
                mouseRelativePointTemp.add(new Point(
                                screenMouse.x - keyFrame.getLocationOnScreen().x,
                                screenMouse.y - keyFrame.getLocationOnScreen().y
                        )
                );
            }
            countFramePointTemp = new Point(
                    screenMouse.x - Main.totalCountFrame.getLocationOnScreen().x,
                    screenMouse.y - Main.totalCountFrame.getLocationOnScreen().y
            );
            chartFramePointTemp = new Point(
                    screenMouse.x - Main.chartFrame.getLocationOnScreen().x,
                    screenMouse.y - Main.chartFrame.getLocationOnScreen().y
            );
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 3) {
            MenuFrame menu = Main.allMenus.get(0);
            menu.setLocation(e.getLocationOnScreen());
            menu.setVisible(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == 1) {
            mouseRelativePointTemp.clear();
            mouseRelativePointTemp = null;
        }
    }
}
