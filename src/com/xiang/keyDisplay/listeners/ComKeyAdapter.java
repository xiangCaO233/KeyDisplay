package com.xiang.keyDisplay.listeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
