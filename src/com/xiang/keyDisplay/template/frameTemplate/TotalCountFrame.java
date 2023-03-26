package com.xiang.keyDisplay.template.frameTemplate;

import com.alibaba.fastjson2.JSONObject;
import com.xiang.keyDisplay.main.Main;
import com.xiang.keyDisplay.others.ComponentUtils;
import com.xiang.keyDisplay.others.JsonUtil;
import com.xiang.keyDisplay.others.MouseAd;
import com.xiang.keyDisplay.others.MouseMo;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

import static com.xiang.keyDisplay.main.Main.saveMaxKps;

public class TotalCountFrame extends CustomizeFrame {
    public JLabel[] labels;
    public int maxKps;
    public int currentKps;
    public long totalCount;
    public Color backgroundColor;
    public Color borderColor;

    public TotalCountFrame(Color border, Color bg) {
        super(border, bg, (float) (1 / 18.0));
        borderColor = border;
        backgroundColor = bg;
        maxKps = Main.keyFrames.size();
        currentKps = Main.keyFrames.size();
        totalCount = 0;

        //初始化标签
        labels = new JLabel[3];
        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel("", JLabel.CENTER);
            labels[i].setForeground(Color.white);
            labels[i].setFont(Main.DEFAULT_FONT.deriveFont(14f));
            //控件居中
            labels[i].setHorizontalAlignment(SwingConstants.CENTER);
            addCom(labels[i]);
        }
        //设置标签显示
        labels[0].setText("最大:" + (maxKps - Main.keyFrames.size()));

        labels[1].setText("当前:" + (currentKps - Main.keyFrames.size()));

        labels[2].setText("总计:" + totalCount);
        updateBounds();
        addMouseListener(new MouseAd());
        addMouseMotionListener(new MouseMo());
    }

    public TotalCountFrame(JSONObject config) {
        super(
                JsonUtil.json2Color(config.getJSONArray("borderColor")),
                JsonUtil.json2Color(config.getJSONArray("backgroundColor")),
                (float) (1 / 18.0)
        );
        borderColor = JsonUtil.json2Color(config.getJSONArray("borderColor"));
        backgroundColor = JsonUtil.json2Color(config.getJSONArray("backgroundColor"));
        maxKps = Main.keyFrames.size() + config.getIntValue("(auto)maxKps");
        currentKps = Main.keyFrames.size();
        totalCount = config.getIntValue("(auto)total");

        //初始化标签
        labels = new JLabel[3];
        for (int i = 0; i < labels.length; i++) {
            labels[i] = ComponentUtils.registerLabel("");
            labels[i].setFont(Main.DEFAULT_FONT.deriveFont(14f));
            addCom(labels[i]);
        }
        //设置标签显示
        labels[0].setText("最大:" + (maxKps - Main.keyFrames.size()));
        labels[1].setText("当前:" + (currentKps - Main.keyFrames.size()));
        labels[2].setText("总计:" + totalCount);

        setFrameSize(
                JsonUtil.json2Rec(
                        config.getJSONArray("bounds")
                ).getSize()
        );
        addMouseListener(new MouseAd());
        addMouseMotionListener(new MouseMo());

    }

    public JSONObject toConfig() {
        JSONObject config = new JSONObject();
        //计数面板配置
        config.put(
                "(auto)total", totalCount
        );
        int kps = 0;
        if (saveMaxKps) {
            kps = maxKps;
        }
        config.put(
                "(auto)maxKps", kps
        );
        config.put(
                "bounds", JsonUtil.rec2Json(getBounds())
        );
        config.put(
                "backgroundColor", JsonUtil.color2Json(backgroundColor)
        );
        config.put(
                "borderColor", JsonUtil.color2Json(borderColor)
        );
        return config;
    }

    public void updateBounds() {
        updateSize();
        resetLocation();
    }

    public void setFrameSize(Dimension size) {
        setSize(size);
        labels[0].setSize(getWidth() / 4, getHeight());
        labels[0].setLocation(0, 0);

        labels[1].setSize(getWidth() / 4, getHeight());
        labels[1].setLocation(getWidth() / 4, 0);

        labels[2].setSize(getWidth() / 2, getHeight());
        labels[2].setLocation(getWidth() / 2, 0);
        maxKps = Main.keyFrames.size();
        currentKps = Main.keyFrames.size();
    }

    public void updateSize() {
        if (Main.keyFrames.size() < 5) {

        } else {
            Collection<KeyFrame> keyFrameCollection = Main.keyFrames.values();
            int sum = 0;
            for (KeyFrame keyFrame : keyFrameCollection) {
                sum += keyFrame.getWidth();
            }
            setSize(sum, 36);
            labels[0].setSize(getWidth() / 4, getHeight());
            labels[0].setLocation(0, 0);

            labels[1].setSize(getWidth() / 4, getHeight());
            labels[1].setLocation(getWidth() / 4, 0);

            labels[2].setSize(getWidth() / 2, getHeight());
            labels[2].setLocation(getWidth() / 2, 0);
        }
        maxKps = Main.keyFrames.size();
        currentKps = Main.keyFrames.size();
    }

    public void resetLocation() {
        setLocation(
                (Main.SCREEN_SIZE.width - getWidth()) / 2,
                (Main.SCREEN_SIZE.height - Main.DEFAULT_SIZE.height) / 2 + Main.DEFAULT_SIZE.height
        );
    }
}
