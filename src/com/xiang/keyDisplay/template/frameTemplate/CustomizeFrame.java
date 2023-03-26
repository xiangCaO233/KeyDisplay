package com.xiang.keyDisplay.template.frameTemplate;

import com.xiang.keyDisplay.others.MouseAd;
import com.xiang.keyDisplay.others.MouseMo;
import com.xiang.keyDisplay.template.panelTemplate.CustomizePanel;
import com.xiang.keyDisplay.template.panelTemplate.RecBorderPanel;
import com.xiang.keyDisplay.template.panelTemplate.RoundRecBorderImagePanel;
import com.xiang.keyDisplay.template.panelTemplate.RoundRecBorderPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 自定义界面
 */
public class CustomizeFrame extends JFrame {
    private CustomizePanel rootPane;
    public Color borderColor;
    public Color backgroundColor;

    /**
     * 构建一个矩形自定义界面
     *
     * @param border 边框颜色
     * @param bg     背景颜色
     * @throws HeadlessException
     */
    public CustomizeFrame(Color border, Color bg) throws HeadlessException {
        defaultSet();
        borderColor = border;
        backgroundColor = bg;
        rootPane = new RecBorderPanel(border, bg);
        setContentPane(rootPane);
    }

    /**
     * 构建一个圆角矩形自定义界面
     *
     * @param border     边框颜色
     * @param bg         背景颜色
     * @param roundScale 圆角比例
     * @throws HeadlessException
     */
    public CustomizeFrame(Color border, Color bg, float roundScale) throws HeadlessException {
        defaultSet();
        borderColor = border;
        backgroundColor = bg;
        rootPane = new RoundRecBorderPanel(roundScale, border, bg);
        setContentPane(rootPane);
    }

    public CustomizeFrame(Color border, Color bg, float roundScale, BufferedImage image) throws HeadlessException {
        defaultSet();
        borderColor = border;
        backgroundColor = bg;
        rootPane = new RoundRecBorderImagePanel(border, bg, roundScale, image);
        setContentPane(rootPane);
    }

    private void defaultSet() {
        setLayout(null);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        setAlwaysOnTop(true);
        //隐藏任务栏图标
        setType(Type.UTILITY);
    }

    public void registerMouseListener() {
        addMouseListener(new MouseAd());
        addMouseMotionListener(new MouseMo());
    }

    /**
     * 设置界面中根容器背景颜色
     *
     * @param bg 背景颜色
     */
    public void setRootBg(Color bg) {
        rootPane.setBackgroundColor(bg);
    }

    /**
     * 设置界面中根容器边框颜色
     *
     * @param border 背景颜色
     */
    public void setRootBorder(Color border) {
        rootPane.setBorderColor(border);
    }

    /**
     * 只有RoundRecBorderImagePanel可用
     *
     * @param image 背景图片
     * @throws Exception 类转换异常
     */

    public void setRootImage(BufferedImage image) throws Exception {
        rootPane.setImage(image);
    }

    public void addCom(Component component) {
        rootPane.add(component);
    }

    public void removeCom(Component component) {
        rootPane.remove(component);
    }
}
