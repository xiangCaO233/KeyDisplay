package com.xiang.keyDisplay.menus;

import com.xiang.keyDisplay.main.Main;
import com.xiang.keyDisplay.others.ComponentUtils;
import com.xiang.keyDisplay.template.frameTemplate.KeyFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class AdvanceSettingsMenu extends MenuTemplate {
    int minHeight;
    int maxHeight;
    JPanel componentSettings;
    JPanel otherSettings;
    JButton mouseOpts;
    JButton chartOpts;
    JButton countOpts;

    public AdvanceSettingsMenu() throws HeadlessException {
        super(Main.DEFAULT_BORDER_COLOR, Main.DEFAULT_BG_COLOR);
        minHeight = 300;
        maxHeight = 480;
        int height = minHeight;
        int temp =( Main.keyFrames.size() + 4 ) * 30;
        boolean lessThanMin;
        boolean moreThanMax;
        if (temp >= minHeight){
            if (temp < maxHeight){
                height = temp;
                lessThanMin = false;
                moreThanMax = false;
            }else{
                height = maxHeight;
                lessThanMin = false;
                moreThanMax = true;
            }
        }else {
            lessThanMin = true;
            moreThanMax = false;
        }
        setSize(320 , height);

        int buttonHeight;
        if (lessThanMin || moreThanMax){
            buttonHeight = (300 / Main.keyFrames.size() + 4);
        }else {
            buttonHeight = 30;
        }

        mouseOpts = ComponentUtils.registerButton("鼠标");
        mouseOpts.setSize(90 , buttonHeight);
        mouseOpts.setLocation(1 ,1 );
        addCom(mouseOpts);

        chartOpts = ComponentUtils.registerButton("图表");
        chartOpts.setSize(90 , buttonHeight);
        chartOpts.setLocation(1 , 31);
        addCom(chartOpts);

        countOpts = ComponentUtils.registerButton("计数");
        countOpts.setSize(90 , buttonHeight);
        countOpts.setLocation(1 , 61);
        addCom(countOpts);


    }
}
