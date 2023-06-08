package com.xiang.keyDisplay.listeners;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.xiang.keyDisplay.main.Main;
import com.xiang.keyDisplay.template.frameTemplate.KeyFrame;

//全局键盘监听
public class GlobalKeyListener implements NativeKeyListener {
    public static boolean ctrl;

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
        int keycode = nativeEvent.getRawCode();
        KeyFrame keyFrame = Main.keyFrames.get(keycode);
        if (keyFrame != null) {
            if (!keyFrame.isPressed) {
                //单按键总计数++
                keyFrame.labels[2].setText(String.valueOf(++keyFrame.counts));
                //kps++
                keyFrame.labels[1].setText(String.valueOf((++keyFrame.kps) - 1));
                //总计数窗体
                Main.totalCountFrame.labels[1].setText("当前:" + (++Main.totalCountFrame.currentKps - Main.keyFrames.size()));
                if (Main.totalCountFrame.currentKps > Main.totalCountFrame.maxKps) {
                    Main.totalCountFrame.maxKps = Main.totalCountFrame.currentKps;
                    Main.totalCountFrame.labels[0].setText("最大:" + (Main.totalCountFrame.maxKps - Main.keyFrames.size()));
                }
                Main.totalCountFrame.labels[2].setText("总计:" + ++Main.totalCountFrame.totalCount);
                //表格窗体
                if (Main.totalCountFrame.currentKps > 10) {
                    Main.chartFrame.chart.getCategoryPlot().getRangeAxis().setAutoTickUnitSelection(true);
                }
                //添加时间戳
                keyFrame.timeStamps.add(System.currentTimeMillis());
                //直接修改填充按下颜色
                keyFrame.currentBg = keyFrame.pressColor;
                keyFrame.setRootBg(keyFrame.currentBg);
                //重绘组件
                keyFrame.repaint();
                //设置为按下状态
                keyFrame.isPressed = true;
            }
        }
        if (162 == keycode) {
            //ctrl
            if (!ctrl) {
                ctrl = true;
            }
        }

    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeEvent) {
        KeyFrame keyFrame = Main.keyFrames.get(nativeEvent.getRawCode());
        if (keyFrame != null) {
            if (keyFrame.isPressed) {
                //设置刷新线程将要渐变的目标颜色
                keyFrame.targetColor = keyFrame.releaseColor;
                //设置为松开状态
                keyFrame.isPressed = false;
            }
        }
        if (ctrl) {
            ctrl = false;
        }
    }
}
