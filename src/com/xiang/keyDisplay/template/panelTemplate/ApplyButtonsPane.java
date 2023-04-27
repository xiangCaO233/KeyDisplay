package com.xiang.keyDisplay.template.panelTemplate;

import com.xiang.keyDisplay.main.Main;
import com.xiang.keyDisplay.others.ComponentUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ApplyButtonsPane extends JPanel {
    JButton done;
    JButton cancel;
    JButton apply;

    public ApplyButtonsPane() {
        setLayout(new BoxLayout(this , BoxLayout.X_AXIS));
        setBackground(new Color(0,0,0,0));
        setMaximumSize(new Dimension(Integer.MAX_VALUE , 30));
        done = ComponentUtils.registerButton("确定");
        done.setMaximumSize(new Dimension(Integer.MAX_VALUE , 30));
        cancel = ComponentUtils.registerButton("取消");
        cancel.setMaximumSize(new Dimension(Integer.MAX_VALUE , 30));
        apply = ComponentUtils.registerButton("应用");
        apply.setMaximumSize(new Dimension(Integer.MAX_VALUE , 30));
        add(done);
        add(cancel);
        add(apply);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Main.DEFAULT_BORDER_COLOR);
        g.drawRect(0 , 0 , getWidth() - 1 , getHeight() - 1);
    }
    public void setDoneAction(ActionListener listener){
        done.addActionListener(listener);
    }
    public void setCancelAction(ActionListener listener){
        cancel.addActionListener(listener);
    }
    public void setApplyAction(ActionListener listener){
        apply.addActionListener(listener);
    }
}
