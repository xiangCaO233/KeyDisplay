package com.xiang.keyDisplay.others;

import com.xiang.keyDisplay.main.Main;
import com.xiang.keyDisplay.template.frameTemplate.CustomizeFrame;
import com.xiang.keyDisplay.template.frameTemplate.KeyFrame;
import com.xiang.keyDisplay.template.frameTemplate.MouseFrame;
import com.xiang.keyDisplay.template.frameTemplate.RefreshFrame;

import java.awt.*;
import java.util.Collection;

/**
 * 刷新线程,处理背景色和kps
 */
public class RefreshThread extends Thread {

    @Override
    public void run() {
        //停止标志
        while (!Thread.currentThread().isInterrupted()) {
            //刷新率
            try {
                Thread.sleep(1000 / Main.fps);
            } catch (InterruptedException e) {
            }
            //转化速率
            double speed = 0;
            switch (Main.speedValue) {
                case 10:
                    speed = 0.8;
                    break;
                case 9:
                    speed = 0.9;
                    break;
                case 8:
                    speed = 0.95;
                    break;
                case 7:
                    speed = 0.97;
                    break;
                case 6:
                    speed = 0.99;
                    break;
                case 5:
                    speed = 0.995;
                    break;
                case 4:
                    speed = 0.9999;
                    break;
                case 3:
                    speed = 0.99999;
                    break;
                case 2:
                    speed = 0.99999999;
                    break;
                case 1:
                    speed = 0.9999999999;
                    break;
            }
            //设置keyframe.currentBg
            Collection<KeyFrame> keyFrames = Main.keyFrames.values();
            for (KeyFrame keyFrame : keyFrames) {
                if (keyFrame.kps < 1) {
                    System.out.println("修复");
                    keyFrame.kps = 1;
                }
                if (Main.totalCountFrame.currentKps < Main.keyFrames.size()) {
                    System.out.println("修复");
                    Main.totalCountFrame.currentKps = Main.keyFrames.size();
                }

                if (keyFrame.isPressed || keyFrame.currentBg.equals(keyFrame.targetColor)) {
                    continue;
                }
                //current -> target
                updateFrameColor(keyFrame , speed);
            }
            //mouseFrame处理
            Collection<MouseFrame> mouseFrames = Main.mouseFrames.values();
            for (MouseFrame mouseFrame : mouseFrames) {
                if (mouseFrame.cps < 1) {
                    System.out.println("修复");
                    mouseFrame.cps = 1;
                }
                /*if (Main.totalCountFrame.currentKps < Main.keyFrames.size()) {
                    System.out.println("修复");
                    Main.totalCountFrame.currentKps = Main.keyFrames.size();
                }*/
//                System.out.println(mouseFrame.targetColor);
                if (mouseFrame.isPressed || mouseFrame.currentBg.equals(mouseFrame.targetColor)) {
                    continue;
                }
                updateFrameColor(mouseFrame , speed);
            }
        }
        System.out.println("刷新线程退出");
    }
    static void updateFrameColor(RefreshFrame frame , double speed){
        //current -> target
        //目标颜色
        Color setColor = new Color(
                frame.targetColor.getRed() - (int) ((frame.targetColor.getRed() - frame.currentBg.getRed()) * speed),
                frame.targetColor.getGreen() - (int) ((frame.targetColor.getGreen() - frame.currentBg.getGreen()) * speed),
                frame.targetColor.getBlue() - (int) ((frame.targetColor.getBlue() - frame.currentBg.getBlue()) * speed),
                frame.targetColor.getAlpha() - (int) ((frame.targetColor.getAlpha() - frame.currentBg.getAlpha()) * speed)
        );
        //设置当前颜色
        frame.currentBg = setColor;
        //更新颜色
        frame.setRootBg(frame.currentBg);
        //重绘界面
        frame.repaint();
    }
}
