package com.xiang.keyDisplay.listeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//按键监听器,默认常量实现非数字字符过滤
public class ComKeyAdapter extends KeyAdapter {
    public static final ComKeyAdapter DIGIT_FILTER_LISTENER = new ComKeyAdapter();

    @Override
    public void keyTyped(KeyEvent e) {
        if (Character.isDigit(e.getKeyChar()) || e.getKeyChar() == '.') {
        } else {
            e.consume();
        }
    }
}
