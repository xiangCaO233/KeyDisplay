package com.xiang.keyDisplay.template.frameTemplate;

import com.alibaba.fastjson2.JSONObject;
import com.xiang.keyDisplay.main.Main;
import com.xiang.keyDisplay.main.VKKeys;
import com.xiang.keyDisplay.others.ComponentUtils;
import com.xiang.keyDisplay.others.GU;
import com.xiang.keyDisplay.others.JsonUtil;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayDeque;

/**
 * 鼠标界面
 * 鼠标按键名
 * cps
 * 点击统计
 */
public class MouseFrame extends RefreshFrame{
    //鼠标按键名
    public String mouseKeyName;

    //显示用标签
    public JLabel[] labels;
    //click per second 点击速度
    public int cps;
    //点击总计数
    public int count;
    //时间戳队列,用于检测时间计算kps
    public ArrayDeque<Long> timeStamps;
    //按下标记,用于标识跳过刷新
    public boolean isPressed;


    /**
     * 构建鼠标监听界面
     * @param name LMB,RMB,MMB
     */
    public MouseFrame(String name){
        super();
        setSize(GU.toAbsSize(Main.DEFAULT_SIZE.width, Main.DEFAULT_SIZE.height));



        labels = new JLabel[3];
        for (int i = 0 ; i < labels.length ; i ++){
            labels[i] = ComponentUtils.registerLabel("");
            labels[i].setFont(Main.DEFAULT_FONT.deriveFont(14f));
            labels[i].setSize(
                    getWidth(),
                    getHeight() / 3
            );
            labels[i].setLocation(
                    0, i * getHeight() / 3
            );
            addCom(labels[i]);
        }
        //初始化时间戳队列
        timeStamps = new ArrayDeque<>();
        //显示名
        mouseKeyName = name;
        labels[0].setText(name);
        //cps
        cps = 1;
        labels[1].setText("0");
        //总计数
        count = 0;
        labels[2].setText("0");

        registerMouseListener();
    }
    public MouseFrame(JSONObject config) {
        super();
        this.releaseColor = JsonUtil.json2Color(config.getJSONArray("releaseColor"));
        this.pressColor = JsonUtil.json2Color(config.getJSONArray("pressColor"));

        targetColor = releaseColor;
        currentBg = releaseColor;

        Rectangle bounds = JsonUtil.json2Rec(config.getJSONArray("bounds"));
        setSize(bounds.getSize());
        this.mouseKeyName = config.getString("mouseKeyName");
        cps = 1;
        count = config.getIntValue("(auto)count");

        timeStamps = new ArrayDeque<>();

        //初始化标签
        labels = new JLabel[3];
        for (int i = 0; i < labels.length; i++) {
            labels[i] = ComponentUtils.registerLabel("");
            labels[i].setSize(getWidth(), getHeight() / 3);
            labels[i].setFont(Main.DEFAULT_FONT.deriveFont(14f));
            addCom(labels[i]);
        }
        //设置标签显示
        labels[0].setText(mouseKeyName);
        labels[0].setLocation(0, 0);
        labels[1].setText(String.valueOf(cps - 1));
        labels[1].setLocation(0, getHeight() / 3);
        labels[2].setText(String.valueOf(count));
        labels[2].setLocation(0, getHeight() * 2 / 3);

        registerMouseListener();
    }

    public JSONObject toConfig() {
        JSONObject config = new JSONObject();
        config.put(
                "mouseKeyName", mouseKeyName
        );
        config.put(
                "(auto)keyCode", VKKeys.KeySearch(mouseKeyName)
        );
        config.put(
                "(auto)count", count
        );
        config.put(
                "bounds", JsonUtil.rec2Json(getBounds())
        );
        config.put(
                "releaseColor", JsonUtil.color2Json(releaseColor)
        );
        config.put(
                "pressColor", JsonUtil.color2Json(pressColor)
        );
        config.put(
                "borderColor", JsonUtil.color2Json(borderColor)
        );
        return config;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof  MouseFrame mouseFrame) {
            return mouseKeyName.equals(mouseFrame.mouseKeyName);
        } else
            return false;
    }
}
