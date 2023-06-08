package com.xiang.keyDisplay.listeners;

import com.xiang.keyDisplay.main.Main;
import com.xiang.keyDisplay.menus.MenuTemplate;
import com.xiang.keyDisplay.template.frameTemplate.KeyFrame;
import com.xiang.keyDisplay.template.frameTemplate.MouseFrame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;

import static com.xiang.keyDisplay.listeners.MouseMoAd.*;

/**
 * 组件鼠标监听器
 */
public class MouseAd extends MouseAdapter {
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 1) {
            mouseInFrameTemp = e.getPoint();
            Point screenMouse = e.getLocationOnScreen();
            keyFrameMouseRelativePointTemp = new ArrayList<>();
            Collection<KeyFrame> allKeyFrames = Main.keyFrames.values();
            for (KeyFrame keyFrame : allKeyFrames) {
                keyFrameMouseRelativePointTemp.add(new Point(
                                screenMouse.x - keyFrame.getLocationOnScreen().x,
                                screenMouse.y - keyFrame.getLocationOnScreen().y
                        )
                );
            }
            mouseFrameMouseRelativePointTemp = new ArrayList<>();
            Collection<MouseFrame> allMouseFrames = Main.mouseFrames.values();
            for (MouseFrame mouseFrame : allMouseFrames) {
                mouseFrameMouseRelativePointTemp.add(new Point(
                                screenMouse.x - mouseFrame.getLocationOnScreen().x,
                                screenMouse.y - mouseFrame.getLocationOnScreen().y
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
            MenuTemplate menu = Main.allMenus.get(0);
            menu.setLocation(e.getLocationOnScreen());
            menu.setVisible(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == 1) {
            keyFrameMouseRelativePointTemp.clear();
            keyFrameMouseRelativePointTemp = null;

            mouseFrameMouseRelativePointTemp.clear();
            mouseFrameMouseRelativePointTemp = null;
        }
    }
}
