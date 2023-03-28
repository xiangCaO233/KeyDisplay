package com.xiang.keyDisplay.template.panelTemplate;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.xiang.keyDisplay.main.Main;
import com.xiang.keyDisplay.main.VKKeys;
import com.xiang.keyDisplay.menus.MenuTemplate;
import com.xiang.keyDisplay.others.ComponentUtils;
import com.xiang.keyDisplay.template.frameTemplate.FastSetFrame;

import javax.swing.*;
import java.util.ArrayList;

import static com.xiang.keyDisplay.main.Main.DEFAULT_BG_COLOR;
import static com.xiang.keyDisplay.main.Main.DEFAULT_BORDER_COLOR;

public class AddKeyMenu extends MenuTemplate {
    public NativeKeyListener keyListener;
    public JLabel title;
    JLabel key;
    int thisKeyCode;
    JButton cancel;
    JButton done;

    public AddKeyMenu() {
        super(DEFAULT_BORDER_COLOR, DEFAULT_BG_COLOR);
        setSize(
                FastSetFrame.KEY_SIZE.width + 2,
                FastSetFrame.KEY_SIZE.height + 90 + 2
        );
        thisKeyCode = -1;
        //标题
        title = ComponentUtils.registerLabel("请设置按键");
        title.setSize(FastSetFrame.KEY_SIZE.width, 30);
        title.setLocation(1, 1);
        addCom(title);
        //key
        key = ComponentUtils.registerLabel("按下按键");
        key.setSize(FastSetFrame.KEY_SIZE);
        key.setLocation(1, 31);
        addCom(key);
        //取消按钮
        cancel = ComponentUtils.registerButton("取消");
        cancel.setSize(FastSetFrame.KEY_SIZE.width, 30);
        cancel.setLocation(1, key.getY() + key.getHeight());
        cancel.addActionListener(e -> {
            disposeSelf();
        });
        addCom(cancel);
        //确定按钮
        done = ComponentUtils.registerButton("确定");
        done.setSize(FastSetFrame.KEY_SIZE.width, 30);
        done.setLocation(1, cancel.getY() + cancel.getHeight());
        done.addActionListener(e -> {
            if (thisKeyCode != -1) {
                if (Main.keyFrames.containsKey(thisKeyCode)) {
                    System.out.println("已存在此按键");
                } else {
                    Main.addKey(thisKeyCode);
                    disposeSelf();
                }
            } else {
                System.out.println("请按下要添加的按键");
            }
        });
        addCom(done);
        ArrayList<Integer> lockList = new ArrayList<>();
        keyListener = new NativeKeyListener() {
            @Override
            public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
                int keyCode = nativeEvent.getRawCode();
                if (!lockList.contains(keyCode)) {
                    //按键锁,防止连续触发
                    lockList.add(keyCode);
                    key.setText(
                            VKKeys.data.get(keyCode)
                    );
                    thisKeyCode = keyCode;
                    repaint();
                }
            }

            @Override
            public void nativeKeyReleased(NativeKeyEvent nativeEvent) {
                int keyCode = nativeEvent.getRawCode();
                if (lockList.contains(keyCode)) {
                    lockList.remove(Integer.valueOf(keyCode));
                }
            }
        };
        GlobalScreen.addNativeKeyListener(keyListener);
    }

    void disposeSelf() {

        keyListener = null;
        dispose();
        Main.addKeyMenu = null;
    }
}
