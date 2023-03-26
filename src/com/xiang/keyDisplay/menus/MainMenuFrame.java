package com.xiang.keyDisplay.menus;

import com.xiang.keyDisplay.main.Main;
import com.xiang.keyDisplay.others.ComponentUtils;
import com.xiang.keyDisplay.template.panelTemplate.AddKeyMenu;

import javax.swing.*;
import java.awt.*;

import static com.xiang.keyDisplay.main.Main.DEFAULT_BG_COLOR;
import static com.xiang.keyDisplay.main.Main.DEFAULT_BORDER_COLOR;

/**
 * 菜单类
 * 最外层为JFrame,含有标题,按钮数组,按钮名数组
 */
public class MainMenuFrame extends MenuFrame {
    JLabel title;
    JButton[] buttons;
    String[] buttonNames = new String[]{
            "快速设置...", "添加按键...", "删除按键...", "高级设置...", "保存配置...", "加载配置...", "说明...", "关于...", "退出"
    };

    public MainMenuFrame() throws HeadlessException {
        super(DEFAULT_BORDER_COLOR, DEFAULT_BG_COLOR);

        setSize(122, 302);
        title = ComponentUtils.registerLabel("menu");
        title.setSize(120, 30);
        title.setLocation(1, 1);
        addCom(title);

        buttons = new JButton[buttonNames.length];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = ComponentUtils.registerButton(buttonNames[i]);
            buttons[i].setSize(120, 30);
            buttons[i].setLocation(1, 1 + (i + 1) * 30);
            addCom(buttons[i]);
        }
        //按钮事件
        buttons[0].addActionListener(e -> {
            //快速设置按钮
            Point myLoc = buttons[0].getLocationOnScreen();
            Main.fastSetMenu.setLocation(
                    myLoc.x + buttons[0].getWidth() + 2,
                    myLoc.y
            );
            Main.fastSetMenu.setVisible(true);
        });
        buttons[1].addActionListener(e -> {
            //添加按键按钮
            Point thisButtonPoint = ((JButton) (e.getSource())).getLocationOnScreen();
            if (Main.addKeyMenu == null) {
                Main.addKeyMenu = new AddKeyMenu();
            }
            Main.addKeyMenu.setLocation(
                    thisButtonPoint.x + buttons[0].getWidth(),
                    thisButtonPoint.y
            );
            Main.addKeyMenu.setVisible(true);
            //丢失焦点
            Main.addKeyMenu.title.requestFocus();
        });
        buttons[2].addActionListener(e -> {
            //删除按钮
            Point thisButtonPoint = ((JButton) (e.getSource())).getLocationOnScreen();
            if (Main.deleteKeyMenu != null) {
                Main.allMenus.remove(Main.deleteKeyMenu);
                Main.mainMenu.childMenus.remove(Main.deleteKeyMenu);
                Main.deleteKeyMenu.dispose();
            }
            Main.deleteKeyMenu = new DeleteKeyMenu();
            Main.allMenus.add(Main.deleteKeyMenu);
            Main.mainMenu.childMenus.add(Main.deleteKeyMenu);
            Main.deleteKeyMenu.setLocation(
                    thisButtonPoint.x + buttons[0].getWidth(),
                    thisButtonPoint.y
            );
            Main.deleteKeyMenu.setVisible(true);
        });
        buttons[3].addActionListener(e -> {
            //高级设置按钮
        });
        buttons[4].addActionListener(e -> {

            //保存按钮
            Point thisButtonPoint = ((JButton) (e.getSource())).getLocationOnScreen();
            if (Main.saveMenu == null) {
                Main.saveMenu = new SaveMenu();
            } else {
                Main.allMenus.get(0).childMenus.remove(Main.saveMenu);
                Main.allMenus.remove(Main.saveMenu);
                Main.saveMenu.dispose();
                Main.saveMenu = new SaveMenu();
                Main.allMenus.add(Main.saveMenu);
                Main.allMenus.get(0).childMenus.add(Main.saveMenu);

            }
            Main.saveMenu.setVisible(true);
            Main.saveMenu.setLocation(thisButtonPoint.x + buttons[4].getWidth(), thisButtonPoint.y);

        });
        buttons[5].addActionListener(e -> {
            //加载按钮
            Point thisButtonPoint = ((JButton) (e.getSource())).getLocationOnScreen();
            if (Main.loadMenu == null) {
                Main.loadMenu = new LoadMenu();
            } else {
                Main.allMenus.get(0).childMenus.remove(Main.loadMenu);
                Main.allMenus.remove(Main.loadMenu);
                Main.loadMenu.dispose();
                Main.loadMenu = new LoadMenu();
                Main.allMenus.add(Main.loadMenu);
                Main.allMenus.get(0).childMenus.add(Main.loadMenu);
            }
            Main.loadMenu.setVisible(true);
            Main.loadMenu.setLocation(thisButtonPoint.x + buttons[4].getWidth(), thisButtonPoint.y);
        });
        buttons[8].addActionListener(e -> {
            //退出按钮
            Main.stop();
        });
        registerMouseListener();
    }

    @Override
    public String toString() {
        return "MainMenu{\n" + childMenus +
                "}";
    }
}
