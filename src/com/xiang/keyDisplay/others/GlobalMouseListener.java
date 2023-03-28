package com.xiang.keyDisplay.others;

import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseListener;
import com.xiang.keyDisplay.main.Main;
import com.xiang.keyDisplay.menus.MenuTemplate;

import java.awt.*;

/**
 * 全局鼠标监听
 */
public class GlobalMouseListener implements NativeMouseListener {
    @Override
    public void nativeMousePressed(NativeMouseEvent e) {
        System.out.println(e.getButton());
        boolean inMenu = false;
        Point mouseLoc = new Point(
                (int)(e.getPoint().x / Main.SCALE_X),
                (int)(e.getPoint().y / Main.SCALE_Y)
        );
        for (int i = Main.allMenus.size() - 1; i >= 0; i--) {
            MenuTemplate menu = Main.allMenus.get(i);
            Rectangle bound = menu.getBounds();
            if (bound.contains(mouseLoc)) {
                inMenu = true;
                //按在某个菜单中,将其子菜单及子菜单的所有子菜单全部设置为不可见
                if (menu.childMenus.size() > 0) {
                    //有子菜单
                    for (MenuTemplate child : menu.childMenus) {
                        setInvisible(child);
                    }
                }
                //无子菜单(鼠标按在最底层菜单中),不操作
            }
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
