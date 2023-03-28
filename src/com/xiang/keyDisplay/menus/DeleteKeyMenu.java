package com.xiang.keyDisplay.menus;

import com.xiang.keyDisplay.main.Main;
import com.xiang.keyDisplay.others.ComponentUtils;
import com.xiang.keyDisplay.template.frameTemplate.KeyFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

import static com.xiang.keyDisplay.main.Main.DEFAULT_BG_COLOR;
import static com.xiang.keyDisplay.main.Main.DEFAULT_BORDER_COLOR;

public class DeleteKeyMenu extends MenuTemplate {
    JLabel title;
    ArrayList<JButton> allKeys;
    JButton back;
    ArrayList<Menu> childMenus = new ArrayList<>();

    public DeleteKeyMenu() throws HeadlessException {
        super(DEFAULT_BORDER_COLOR, DEFAULT_BG_COLOR);
        //标题
        title = ComponentUtils.registerLabel("删除按键");
        title.setSize(120, 30);
        title.setLocation(1, 1);
        addCom(title);

        Collection<KeyFrame> keyFrames = Main.keyFrames.values();
        allKeys = new ArrayList<>();
        int index = 0;
        for (KeyFrame keyFrame : keyFrames) {
            allKeys.add(ComponentUtils.registerButton(keyFrame.keyName));
            allKeys.get(index).setSize(120, 30);
            allKeys.get(index).addActionListener(e -> {
                JButton srcButton = ((JButton) e.getSource());
                String buttonName = srcButton.getText();
                if (allKeys.size() > 1) {
                    Main.deleteKey(buttonName);
                    removeCom(srcButton);
                    allKeys.remove(srcButton);
                    updateBounds();
                } else {
                    System.out.println("只剩下一个按键了!");
                }
            });
            addCom(allKeys.get(index));
            index++;
        }

        back = ComponentUtils.registerButton("返回");
        back.setSize(120, 30);
        back.addActionListener(e -> {
            back.getTopLevelAncestor().setVisible(false);
        });
        addCom(back);
        updateBounds();
    }

    public void updateBounds() {
        int index = 0;
        for (JButton button : allKeys) {
            button.setLocation(1, 1 + ((index++) + 1) * 30);
        }
        back.setLocation(1, 1 + (allKeys.size() + 1) * 30);
        setSize(122, 30 * (allKeys.size() + 2) + 2);
        repaint();
    }

    @Override
    public String toString() {
        return "DeleteKeyMenu{\n" + childMenus +
                "}";
    }
}
