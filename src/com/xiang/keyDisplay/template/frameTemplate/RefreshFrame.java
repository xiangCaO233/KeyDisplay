package com.xiang.keyDisplay.template.frameTemplate;

import com.xiang.keyDisplay.main.Main;

import java.awt.*;

public class RefreshFrame extends CustomizeFrame {
    //用于渐变,按下后松开一直在变化
    public Color currentBg;
    //标记渐变要到的目标颜色
    public Color targetColor;
    //松开时显示的颜色
    public Color releaseColor;
    //按下时显示的颜色
    public Color pressColor;

    public RefreshFrame(Color bg) throws HeadlessException {
        super(Main.DEFAULT_BORDER_COLOR, bg);
        releaseColor = bg;
        pressColor = Main.DEFAULT_PRESS_COLOR;

        currentBg = releaseColor;
        targetColor = releaseColor;
    }

}
