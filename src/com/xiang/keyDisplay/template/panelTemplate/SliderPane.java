package com.xiang.keyDisplay.template.panelTemplate;

import com.xiang.keyDisplay.main.Main;
import com.xiang.keyDisplay.others.ComponentUtils;
import com.xiang.keyDisplay.others.GU;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 滑动组件容器,包含显示名,滑动组件,值标签
 */
public class SliderPane extends JPanel {
    JLabel name;
    public JSlider slider;
    public JTextField valueField;
    public Thread t;

    public SliderPane(String name, int value) {

        setLayout(null);
        setSize(GU.toAbsSize((682 - 120) / 2 - 1, 30));
        setBackground(Main.DEFAULT_BG_COLOR);
        this.name = ComponentUtils.registerLabel(name);
        this.name.setSize(GU.toAbsSize(40, 30));
        this.name.setLocation(0, 0);
        add(this.name);
        //初始化滑动组件
        slider = new JSlider();
        slider.setSize(getWidth() - GU.absX(80), GU.absY(30));
        slider.setLocation(GU.toAbsPos(40, 0));
        slider.setMaximum(255);
        slider.setMinimum(0);
        slider.setValue(value);
        slider.setBackground(new Color(0, 0, 0, 0));
        add(slider);
        //初始化值显示标签
        valueField = new JTextField(String.valueOf(value));
        valueField.setSize(GU.toAbsSize(40, 40));
        valueField.setLocation(getWidth() - GU.absX(40), 0);
        valueField.setFont(Main.DEFAULT_FONT.deriveFont(18f));
        valueField.setHorizontalAlignment(SwingConstants.CENTER);
        valueField.setForeground(new Color(230, 230, 230, 230));
        valueField.setBackground(new Color(0, 0, 0, 0));
        valueField.setBorder(null);
        valueField.setText(String.valueOf(value));

        valueField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isControlDown())
                    //取消粘贴功能
                    e.consume();
            }

            @Override
            public void keyTyped(KeyEvent e) {
                //禁止除数字外输入
                int keyChar = e.getKeyChar();
                if (keyChar < KeyEvent.VK_0 || keyChar > KeyEvent.VK_9) {
                    e.consume();
                }
            }
        });
        valueField.addCaretListener(e -> {
            //光标更新重绘组件
            valueField.getTopLevelAncestor().repaint();
        });
        /*valueLabel = ComponentUtils.registerLabel(String.valueOf(value));
        valueLabel.setSize(GU.toAbsSize(40, 40));
        valueLabel.setLocation(getWidth() - GU.absX(40), 0);*/
        add(valueField);
    }
}
