package com.xiang.keyDisplay.others;

import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseListener;
import com.xiang.keyDisplay.main.Main;
import com.xiang.keyDisplay.menus.MenuTemplate;
import com.xiang.keyDisplay.template.frameTemplate.MouseFrame;

import java.awt.*;

/**
 * 全局鼠标监听
 */
public class GlobalMouseListener implements NativeMouseListener {
    @Override
    public void nativeMouseReleased(NativeMouseEvent e) {
        //MouseFrame操作
        MouseFrame frame = Main.mouseFrames.get(e.getButton());
        if (frame != null) {
            //设置刷新线程将要渐变的目标颜色
            frame.targetColor = frame.releaseColor;
            //设置为松开状态
            frame.isPressed = false;

        }
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent e) {
        //MouseFrame操作
        MouseFrame frame = Main.mouseFrames.get(e.getButton());
        if (frame != null) {
            frame.isPressed = true;
            //总计数++
            frame.labels[2].setText(String.valueOf(++frame.count));
            //cps++
            frame.labels[1].setText(String.valueOf((++frame.cps) - 1));
            //更新时间戳
            frame.timeStamps.add(System.currentTimeMillis());
            //直接设置填充颜色
            frame.currentBg = frame.pressColor;
            frame.setRootBg(frame.currentBg);
            frame.repaint();

        }

        //菜单逻辑
        boolean inMenu = false;
        //获取计算缩放后坐标
        Point mouseLoc = new Point(
                (int)(e.getPoint().x / Main.SCALE_X),
                (int)(e.getPoint().y / Main.SCALE_Y)
        );
        for (int i = Main.allMenus.size() - 1; i >= 0; i--) {
            MenuTemplate menu = Main.allMenus.get(i);
            Rectangle bound = menu.getBounds();
            if (bound.contains(mouseLoc)) {
                menu.isIn = true;
                inMenu = true;
                //按在某个菜单中,将其子菜单及子菜单的所有子菜单全部设置为不可见
                if (menu.childMenus.size() > 0) {
                    //有子菜单
                    for (MenuTemplate child : menu.childMenus) {
                        //只设置没按到的子菜单不可见
                        if (!child.isIn)
                            setInvisible(child);
                    }
                }
                //无子菜单(鼠标按在最底层菜单中),不操作
            } else
                menu.isIn = false;
        }
        //未点在任何菜单中
        if (!inMenu) {
            //设置主菜单不可见(包括其全部子菜单)
            setInvisible(Main.allMenus.get(0));
        }
    }

    void setInvisible(MenuTemplate menu) {
        //递归设置不可见
        if (menu.childMenus.size() > 0) {
            for (MenuTemplate child : menu.childMenus) {
                setInvisible(child);
            }
        }
        menu.setVisible(false);

    }
}
