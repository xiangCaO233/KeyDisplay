package com.xiang.keyDisplay.menus;

import com.xiang.keyDisplay.main.Main;
import com.xiang.keyDisplay.others.ComponentUtils;
import com.xiang.keyDisplay.others.GU;
import com.xiang.keyDisplay.template.frameTemplate.FastSetFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static com.xiang.keyDisplay.main.Main.DEFAULT_BG_COLOR;
import static com.xiang.keyDisplay.main.Main.DEFAULT_BORDER_COLOR;

//快速设置菜单
public class FastSetMenu extends MenuTemplate {
    JLabel title;
    JButton[] buttons;
    String[] buttonNames = new String[]{
            "4k...", "5k...", "6k...", "7k...", "8k...", "9k...", "10k..."
    };
    ArrayList<Menu> childMenus = new ArrayList<>();

    public FastSetMenu() throws HeadlessException {
        super(DEFAULT_BORDER_COLOR, DEFAULT_BG_COLOR);

        setSize(GU.toAbsSize(92, 242));
        title = ComponentUtils.registerLabel("快速设置");
        title.setSize(GU.toAbsSize(90, 30));
        title.setLocation(1, 1);
        addCom(title);

        buttons = new JButton[buttonNames.length];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = ComponentUtils.registerButton(buttonNames[i]);
            buttons[i].setSize(GU.toAbsSize(90, 30));
            buttons[i].setLocation(1, 1 + (i + 1) * 30);
            int finalI = i;
            buttons[i].addActionListener(e -> {
                if (Main.fastSetFrame != null) {
                    Main.fastSetFrame.disposeFrame();
                    Main.fastSetFrame = null;
                }
                Main.fastSetFrame = new FastSetFrame(finalI + 4);
                Main.fastSetFrame.setLocation(
                        (Main.SCREEN_SIZE.width - Main.fastSetFrame.getWidth()) / 2,
                        (Main.SCREEN_SIZE.height - Main.fastSetFrame.getHeight()) / 2
                );
                Main.fastSetFrame.setVisible(true);
            });
            addCom(buttons[i]);
        }
    }

    @Override
    public String toString() {
        return "FastSetMenu{\n" + childMenus +
                "}";
    }
}
