package com.xiang.keyDisplay.template.frameTemplate;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.xiang.keyDisplay.main.Main;
import com.xiang.keyDisplay.main.VKKeys;
import com.xiang.keyDisplay.template.panelTemplate.FastSetKeyPane;
import com.xiang.keyDisplay.template.panelTemplate.PointerPane;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

//快速设置界面
public class FastSetFrame extends CustomizeFrame {
    public static final Dimension KEY_SIZE = new Dimension(90, 60);
    //按键数
    int keyCount;
    //按键指针,指向正在设置的位置
    PointerPane pointerPane;
    //标题
    JLabel title;
    //key容器
    FastSetKeyPane[] keys;
    //取消按钮
    JButton cancel;
    //重置所有按键按钮
    JButton resetAll;
    //完成按钮
    JButton done;
    //新按键监听器
    NativeKeyListener keyListener;
    //设置结果
    int[] keycodes;

    public FastSetFrame(int keyCount) throws HeadlessException {
        super(Main.DEFAULT_BORDER_COLOR, Main.DEFAULT_BG_COLOR);
        this.keyCount = keyCount;
        setSize(keyCount * KEY_SIZE.width + 2, 98 + KEY_SIZE.height);
        /*
          -------------------------------
          (height 1)
                                       title(height 30)
           ---    ---   ---  ---  --- ---  ---
           |       |   |       |  |      |  |      |  |      |  |      |  |      |
           |(key)|  |       |  |      |  |      |  |      |  |      |  |      |  (height 60 , width 90 per)
           |       |   |       |  |      |  |      |  |      |  |      |  |      |
           ---   ---  ---   ---  --- ---  ---
          (height 5)                                                                                                       (total 158)
           ---
           |(ptr)|  (height 30 , width 40 per)
           ---
           (height 1)
           buttons (height 30 , width 60 per)
           (height 1)
         */
        //标题
        title = new JLabel("快速设置-" + keyCount + "k", JLabel.CENTER);
        title.setSize(getWidth() - 2, 30);
        title.setFont(Main.DEFAULT_FONT.deriveFont(18f));
        title.setForeground(Main.DEFAULT_BORDER_COLOR);
        title.setLocation(1, 1);
        addCom(title);
        //keys
        keys = new FastSetKeyPane[keyCount];
        for (int i = 0; i < keys.length; i++) {
            keys[i] = new FastSetKeyPane();
            keys[i].setSize(KEY_SIZE);
            keys[i].setLocation(i * KEY_SIZE.width + 1, 31);
            addCom(keys[i]);
        }
        //箭头面板
        pointerPane = new PointerPane();
        pointerPane.setSize(40, 30);
        updatePointer();
        addCom(pointerPane);
        //初始化结果数组
        keycodes = new int[keyCount];
        //初始化监听器
        ArrayList<Integer> lockList = new ArrayList<>();
        keyListener = new NativeKeyListener() {
            @Override
            public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
                int keyCode = nativeEvent.getRawCode();
                if (!lockList.contains(keyCode)) {
                    //按键锁,防止连续触发
                    lockList.add(keyCode);
                    if (pointerPane.index < keyCount) {
                        //设置按键
                        keys[pointerPane.index].key = VKKeys.data.get(keyCode);
                        //初始化要返回的结果按键数组
                        keycodes[pointerPane.index] = keyCode;
                        //移动指针
                        pointerPane.index++;
                        //更新指针容器坐标
                        updatePointer();
                        //重绘界面
                        pointerPane.getTopLevelAncestor().repaint();
                    }
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
        //宽度误差
        int deviation = (getWidth() - 2) % 3;
        System.out.println(deviation);
        //取消按钮
        cancel = new JButton("取消");
        cancel.setForeground(Main.DEFAULT_BORDER_COLOR);
        cancel.setBackground(Main.DEFAULT_BG_COLOR);
        cancel.setSize((int) ((getWidth() - 2 - deviation) / 3.0) - 1, 30);
        cancel.setFont(Main.DEFAULT_FONT.deriveFont(16f));
        cancel.setLocation(1, pointerPane.getY() + pointerPane.getHeight() + 1);
        cancel.addActionListener(e -> {
            disposeFrame();
        });
        addCom(cancel);
        //重置按钮
        resetAll = new JButton("重置");
        resetAll.setForeground(Main.DEFAULT_BORDER_COLOR);
        resetAll.setBackground(Main.DEFAULT_BG_COLOR);
        resetAll.setSize((int) ((getWidth() - 2 - deviation) / 3.0) - 1, 30);
        resetAll.setFont(Main.DEFAULT_FONT.deriveFont(16f));
        resetAll.setLocation(
                1 + cancel.getX() + cancel.getWidth(),
                pointerPane.getY() + pointerPane.getHeight() + 1
        );
        resetAll.addActionListener(e -> {
            resetAll();
        });
        addCom(resetAll);

        //完成按钮
        done = new JButton("确定");
        done.setForeground(Main.DEFAULT_BORDER_COLOR);
        done.setBackground(Main.DEFAULT_BG_COLOR);
        done.setSize((int) ((getWidth() - 2 - deviation) / 3.0) - 1, 30);
        done.setFont(Main.DEFAULT_FONT.deriveFont(16f));
        done.setLocation(
                1 + resetAll.getX() + resetAll.getWidth(),
                pointerPane.getY() + pointerPane.getHeight() + 1
        );
        done.addActionListener(e -> {
            if (pointerPane.index == keyCount) {
                Main.setKeys(keycodes);
                disposeFrame();
            } else {
                System.out.println("未完成!");
            }
        });
        addCom(done);

        GlobalScreen.addNativeKeyListener(keyListener);
    }

    public void resetAll() {
        Arrays.fill(keycodes, 0);
        for (int i = 0; i < keyCount; i++) {
            keys[i].key = "";
        }
        pointerPane.index = 0;
        updatePointer();
        pointerPane.getTopLevelAncestor().repaint();
    }

    public void updatePointer() {
        Point firstKeyPoint = keys[0].getLocation();
        Dimension keySize = keys[0].getSize();
        pointerPane.setLocation(
                firstKeyPoint.x + keySize.width / 2 + pointerPane.index * keySize.width - pointerPane.getWidth() / 2,
                (firstKeyPoint.y + keySize.height) + 5
        );
    }

    public void disposeFrame() {
        GlobalScreen.removeNativeKeyListener(keyListener);
        dispose();
    }
}
