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
    Font currentFont;
    boolean showCounts;
    boolean showCps;


    /**
     * 构建鼠标监听界面
     *
     * @param name LMB,RMB,MMB
     */
    public MouseFrame(String name) {
        super();
        setSize(GU.toAbsSize(Main.DEFAULT_SIZE.width, Main.DEFAULT_SIZE.height));
        showCounts = true;
        showCps = true;
        labels = new JLabel[3];
        currentFont = Main.DEFAULT_FONT.deriveFont(14f);
        for (int i = 0 ; i < labels.length ; i ++){
            labels[i] = ComponentUtils.registerLabel("");
            labels[i].setFont(currentFont);
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
        if ("Default Font".equals(config.getString("font"))) {
            currentFont = Main.DEFAULT_FONT.deriveFont(config.getFloat("fontSize"));
        } else {
            currentFont = Font.getFont(config.getString("font")).deriveFont(config.getFloat("fontSize"));
        }
        showCounts = config.getBoolean("showCounts");
        showCps = config.getBoolean("showCps");
        //初始化标签
        labels = new JLabel[3];
        for (int i = 0; i < labels.length; i++) {
            labels[i] = ComponentUtils.registerLabel("");
            labels[i].setFont(currentFont);
            addCom(labels[i]);
        }
        //设置标签显示
        labels[0].setText(mouseKeyName);
        labels[1].setText(String.valueOf(cps - 1));
        labels[2].setText(String.valueOf(count));

        updateLabel();

        registerMouseListener();
    }

    public JSONObject toConfig() {
        JSONObject config = new JSONObject();
        config.put(
                "mouseKeyName", mouseKeyName
        );
        config.put("showCounts", showCounts);
        config.put("showCps", showCps);
        config.put("font", currentFont.getName().equals(Main.DEFAULT_FONT.getName()) ? "Default Font" : getFont().getName());
        config.put("fontSize", currentFont.getSize2D());
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
        if (obj instanceof MouseFrame mouseFrame) {
            return mouseKeyName.equals(mouseFrame.mouseKeyName);
        } else
            return false;
    }

    public void setAllLabelsFont(Font font) {
        currentFont = font;
        for (JLabel label : labels) {
            label.setFont(font);
        }
    }

    public void updateSize(Dimension size) {
        setSize(size);
        updateLabel();
    }

    public void updateLabel() {
        if (!showCounts) {
            if (!showCps) {
                //仅显示按键名
                for (JLabel label : labels) {
                    label.setSize(getWidth(), getHeight());
                }
                labels[0].setLocation(0, 0);
                labels[1].setVisible(false);
                labels[2].setVisible(false);
            } else {
                //显示按键名及cps
                for (JLabel label : labels) {
                    label.setSize(getWidth(), getHeight() / 2);
                }
                labels[0].setLocation(0, 0);
                labels[1].setLocation(0, getHeight() / 2);
                labels[1].setVisible(true);
                labels[2].setVisible(false);
            }
        } else {
            if (!showCps) {
                //显示按键名和计数
                for (JLabel label : labels) {
                    label.setSize(getWidth(), getHeight() / 2);
                }
                labels[0].setLocation(0, 0);
                labels[1].setVisible(false);
                labels[2].setLocation(0, getHeight() / 2);
                labels[2].setVisible(true);
            } else {
                //全部显示
                for (JLabel label : labels) {
                    label.setSize(getWidth(), getHeight() / 3);
                }
                labels[0].setLocation(0, 0);
                labels[1].setLocation(0, getHeight() / 3);
                labels[1].setVisible(true);
                labels[2].setLocation(0, getHeight() * 2 / 3);
                labels[2].setVisible(true);
            }
        }
    }

    public void setShowCounts(boolean isShow) {
        this.showCounts = isShow;
        updateLabel();
    }

    public void setShowCps(boolean isShow) {
        this.showCps = isShow;
        updateLabel();
    }
}
