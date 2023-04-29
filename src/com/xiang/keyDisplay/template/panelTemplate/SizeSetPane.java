package com.xiang.keyDisplay.template.panelTemplate;

import com.xiang.keyDisplay.listeners.CaretChangeListener;
import com.xiang.keyDisplay.listeners.DocumentsChangeListener;
import com.xiang.keyDisplay.listeners.RefreshMouseAd;
import com.xiang.keyDisplay.main.Main;
import com.xiang.keyDisplay.others.ComponentUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 尺寸设置容器
 * 带title标签,文本域,单位标签
 */
public class SizeSetPane extends JPanel{
    public static final int PX_UNIT = 1;
    public static final int INCH_UNIT = 2;
    JLabel name;
    JTextField field;
    JLabel unit;
    int mode = PX_UNIT;


    public SizeSetPane(String name , int size) {
        setLayout(new BoxLayout(this , BoxLayout.X_AXIS));
        setBackground(new Color(0,0,0,0));
        setMaximumSize(new Dimension(Integer.MAX_VALUE , 30));
        this.name = ComponentUtils.registerLabel(name);
        field = new JTextField();
        field.setText(String.valueOf(size));
        field.setFont(Main.DEFAULT_FONT.deriveFont(18f));
        field.setBackground(new Color(0,0,0,0));
        field.setForeground(Main.DEFAULT_BORDER_COLOR);
        field.setHorizontalAlignment(JTextField.RIGHT);
        field.setBorder(null);
        field.getCaret().addChangeListener(new CaretChangeListener(field));
        field.getDocument().addDocumentListener(new DocumentsChangeListener(field));
        unit =ComponentUtils.registerLabel("px");
        unit.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (mode == 1)
                    setMode(2);
                else
                    setMode(1);
            }
        });
        add(this.name);
        add(field);
        add(unit);
        addMouseListener(RefreshMouseAd.instance);
    }
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Main.DEFAULT_BORDER_COLOR);
        g.drawRect(0 , 0 , getWidth() - 1 , getHeight() - 1);
    }
    public void setMode(int mode){
        if (mode == 1 || mode == 2) {
            this.mode = mode;
            unit.setText(
                    (mode == 1 ? "px" : "in")
            );
            getTopLevelAncestor().repaint();
        }
        else
            throw new IllegalArgumentException("illegal mode arg");
    }

}
