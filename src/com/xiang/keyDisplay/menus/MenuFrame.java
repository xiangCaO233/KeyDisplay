package com.xiang.keyDisplay.menus;

import com.xiang.keyDisplay.template.frameTemplate.CustomizeFrame;

import java.awt.*;
import java.util.ArrayList;

public abstract class MenuFrame extends CustomizeFrame {
    public ArrayList<MenuFrame> childMenus;

    public MenuFrame(Color border, Color bg) throws HeadlessException {
        super(border, bg);
        childMenus = new ArrayList<>();
    }

    public void addChild(MenuFrame menuFrame) {
        childMenus.add(menuFrame);
    }
}
