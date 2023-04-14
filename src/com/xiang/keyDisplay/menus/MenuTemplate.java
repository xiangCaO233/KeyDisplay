package com.xiang.keyDisplay.menus;

import com.xiang.keyDisplay.template.frameTemplate.CustomizeFrame;

import java.awt.*;
import java.util.ArrayList;

public abstract class MenuTemplate extends CustomizeFrame {
    public ArrayList<MenuTemplate> childMenus;
    public boolean isIn;

    public MenuTemplate(Color border, Color bg) throws HeadlessException {
        super(border, bg);
        childMenus = new ArrayList<>();
    }

    public void addChild(MenuTemplate menuTemplate) {
        childMenus.add(menuTemplate);
    }
}
