package com.xiang.keyDisplay.threads;

import com.xiang.keyDisplay.main.Main;
import com.xiang.keyDisplay.template.frameTemplate.KeyFrame;
import com.xiang.keyDisplay.template.frameTemplate.MouseFrame;

import java.util.Collection;
import java.util.TimerTask;

/**
 * kps计算任务
 */
public class KpsAndCpsTask extends TimerTask {
    @Override
    public void run() {
        //keyframe.kps处理
        Collection<KeyFrame> keyFrames = Main.keyFrames.values();
        for (KeyFrame keyFrame : keyFrames) {
            //处理kps
            if (keyFrame.kps > 0) {
                if (keyFrame.kps == 1) {
                    keyFrame.timeStamps.add(System.currentTimeMillis());
                    while (keyFrame.timeStamps.size() > 1) {
                        keyFrame.timeStamps.poll();
                    }
                }
                if ((System.currentTimeMillis() - (keyFrame.timeStamps.peek() == null ? 0 : keyFrame.timeStamps.peek())) > 1000) {
                    if (keyFrame.timeStamps.size() > 1) {
                        keyFrame.timeStamps.poll();
                    }
                    if (keyFrame.kps == 0) {
                        continue;
                    }
                    keyFrame.labels[1].setText(String.valueOf((--keyFrame.kps) - 1));
                    synchronized (Main.totalCountFrame) {
                        Main.totalCountFrame.labels[1].setText("当前:" + ((--Main.totalCountFrame.currentKps) - Main.keyFrames.size()));
                    }
                    keyFrame.repaint();
                }
            }
        }

        //mouseframe.cps处理
        Collection<MouseFrame> mouseFrames = Main.mouseFrames.values();
        for (MouseFrame mouseFrame : mouseFrames) {
            //处理kps
            if (mouseFrame.cps > 0) {
                if (mouseFrame.cps == 1) {
                    mouseFrame.timeStamps.add(System.currentTimeMillis());
                    while (mouseFrame.timeStamps.size() > 1) {
                        mouseFrame.timeStamps.poll();
                    }
                }
                if ((System.currentTimeMillis() - (mouseFrame.timeStamps.peek() == null ? 0 : mouseFrame.timeStamps.peek())) > 1000) {
                    if (mouseFrame.timeStamps.size() > 1) {
                        mouseFrame.timeStamps.poll();
                    }
                    if (mouseFrame.cps == 0) {
                        continue;
                    }
                    mouseFrame.labels[1].setText(String.valueOf((--mouseFrame.cps) - 1));
                    /*synchronized (Main.totalCountFrame) {
                        Main.totalCountFrame.labels[1].setText("当前:" + ((--Main.totalCountFrame.currentKps) - Main.keyFrames.size()));
                    }*/
                    mouseFrame.repaint();
                }
            }
        }
    }
}
