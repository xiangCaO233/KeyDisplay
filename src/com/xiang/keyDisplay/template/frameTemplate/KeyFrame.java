package com.xiang.keyDisplay.template.frameTemplate;

import com.alibaba.fastjson2.JSONObject;
import com.xiang.keyDisplay.listeners.MouseAd;
import com.xiang.keyDisplay.listeners.MouseMoAd;
import com.xiang.keyDisplay.main.Main;
import com.xiang.keyDisplay.main.VKKeys;
import com.xiang.keyDisplay.others.ComponentUtils;
import com.xiang.keyDisplay.others.GU;
import com.xiang.keyDisplay.others.JsonUtil;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayDeque;

/**
 * 按键窗体
 */
public class KeyFrame extends RefreshFrame {
    //按键名
    public String keyName;
    //当前kps
    public int kps;
    //总计数
    public int counts;
    //按下标记,防止连续触发
    public boolean isPressed;
    //显示用标签
    public JLabel[] labels;

    //时间戳队列,用于检测时间计算kps
    public ArrayDeque<Long> timeStamps;
    Font currentFont;
    boolean showCounts;
    boolean showCps;

    public KeyFrame(String keyName) {
        super();
        this.releaseColor = Main.DEFAULT_BG_COLOR;
        this.pressColor = Main.DEFAULT_PRESS_COLOR;

        targetColor = releaseColor;
        currentBg = releaseColor;

        setSize(GU.toAbsSize(Main.DEFAULT_SIZE.width, Main.DEFAULT_SIZE.height));
        this.keyName = keyName;
        kps = 1;
        //初始化时间戳列表
        timeStamps = new ArrayDeque<>();

        //初始化标签
        labels = new JLabel[3];
        currentFont = Main.DEFAULT_FONT.deriveFont(14f);
        for (int i = 0; i < labels.length; i++) {
            labels[i] = ComponentUtils.registerLabel("");
            //覆盖字体大小
            labels[i].setFont(currentFont);
            labels[i].setSize(getWidth(), getHeight() / 3);
            addCom(labels[i]);
        }
        //设置标签显示
        labels[0].setText(keyName);
        labels[0].setLocation(0, 0);
        labels[1].setText(String.valueOf(kps - 1));
        labels[1].setLocation(0, getHeight() / 3);
        labels[2].setText(String.valueOf(counts));
        labels[2].setLocation(0, getHeight() * 2 / 3);

        addMouseListener(new MouseAd());
        addMouseMotionListener(new MouseMoAd());

    }

    public KeyFrame(JSONObject config) {
        super();
        this.releaseColor = JsonUtil.json2Color(config.getJSONArray("releaseColor"));
        this.pressColor = JsonUtil.json2Color(config.getJSONArray("pressColor"));

        targetColor = releaseColor;
        currentBg = releaseColor;

        Rectangle bounds = JsonUtil.json2Rec(config.getJSONArray("bounds"));
        setSize(bounds.getSize());
        this.keyName = config.getString("keyName");
        kps = 1;
        counts = config.getIntValue("(auto)count");

        timeStamps = new ArrayDeque<>();
        //初始化标签
        labels = new JLabel[3];
        if ("Default Font".equals(config.getString("font"))) {
            currentFont = Main.DEFAULT_FONT.deriveFont(config.getFloat("fontSize"));
        } else {
            currentFont = Font.getFont(config.getString("font")).deriveFont(config.getFloat("fontSize"));
        }
        for (int i = 0; i < labels.length; i++) {
            labels[i] = ComponentUtils.registerLabel("");
            labels[i].setSize(getWidth(), getHeight() / 3);
            labels[i].setFont(currentFont);
            addCom(labels[i]);
        }
        //设置标签显示
        labels[0].setText(keyName);
        labels[0].setLocation(0, 0);
        labels[1].setText(String.valueOf(kps - 1));
        labels[1].setLocation(0, getHeight() / 3);
        labels[2].setText(String.valueOf(counts));
        labels[2].setLocation(0, getHeight() * 2 / 3);

        registerMouseListener();
    }

    public JSONObject toConfig() {
        JSONObject config = new JSONObject();
        config.put(
                "keyName", keyName
        );
        config.put("font", currentFont.getName().equals(Main.DEFAULT_FONT.getName()) ? "Default Font" : getFont().getName());
        config.put("fontSize", currentFont.getSize2D());
        config.put(
                "(auto)keyCode", VKKeys.KeySearch(keyName)
        );
        config.put(
                "(auto)count", counts
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
        if (obj instanceof KeyFrame keyFrame) {
            return keyName.equals(keyFrame.keyName);
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
