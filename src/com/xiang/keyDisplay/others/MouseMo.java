package com.xiang.keyDisplay.others;

import com.xiang.keyDisplay.main.Main;
import com.xiang.keyDisplay.template.frameTemplate.ChartFrame;
import com.xiang.keyDisplay.template.frameTemplate.KeyFrame;
import com.xiang.keyDisplay.template.frameTemplate.MouseFrame;
import com.xiang.keyDisplay.template.frameTemplate.TotalCountFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Collection;

public class MouseMo extends MouseMotionAdapter {
    public static ArrayList<Point> keyFrameMouseRelativePointTemp;
    public static ArrayList<Point> mouseFrameMouseRelativePointTemp;
    public static Point countFramePointTemp;
    public static Point chartFramePointTemp;
    public static Point mouseInFrameTemp;

    @Override
    public void mouseDragged(MouseEvent e) {
        //拖拽事件
        if (e.getModifiersEx() == InputEvent.BUTTON1_DOWN_MASK || e.getModifiersEx() == (InputEvent.CTRL_DOWN_MASK | InputEvent.BUTTON1_DOWN_MASK)) {
            Point mouse = e.getLocationOnScreen();
            if (GlobalKeyListener.ctrl) {
                //按键容器
                Collection<KeyFrame> allKeyFrames = Main.keyFrames.values();
                int index1 = 0;
                for (KeyFrame keyFrame : allKeyFrames) {
                    keyFrame.setLocation(
                            mouse.x - keyFrameMouseRelativePointTemp.get(index1).x,
                            mouse.y - keyFrameMouseRelativePointTemp.get(index1).y
                    );
                    index1++;
                }
                //鼠标容器
                Collection<MouseFrame> allMouseFrames = Main.mouseFrames.values();
                int index2 = 0;
                for (MouseFrame mouseFrame : allMouseFrames) {
                    mouseFrame.setLocation(
                            mouse.x - keyFrameMouseRelativePointTemp.get(index2).x,
                            mouse.y - keyFrameMouseRelativePointTemp.get(index2).y
                    );
                    index2++;
                }

                Main.totalCountFrame.setLocation(
                        mouse.x - countFramePointTemp.x,
                        mouse.y - countFramePointTemp.y
                );
                Main.chartFrame.setLocation(
                        mouse.x - chartFramePointTemp.x,
                        mouse.y - chartFramePointTemp.y
                );
            } else {
                Point lastLoc = new Point(mouse.x - mouseInFrameTemp.x, mouse.y - mouseInFrameTemp.y);
                //磁吸
                //建立磁吸列表
                ArrayList<JFrame> allFramesWithoutSelf = new ArrayList<>();
                //检测是否是自身,不磁吸自身
                //按键
                Collection<KeyFrame> keyFrames = Main.keyFrames.values();
                if (e.getSource() instanceof KeyFrame) {
                    for (KeyFrame keyFrame : keyFrames) {
                        if (!(keyFrame.equals(e.getSource()))) {
                            allFramesWithoutSelf.add(keyFrame);
                        }
                    }
                } else allFramesWithoutSelf.addAll(keyFrames);
                //鼠标
                Collection<MouseFrame> mouseFrames = Main.mouseFrames.values();
                if (e.getSource() instanceof MouseFrame) {
                    for (MouseFrame mouseFrame : mouseFrames) {
                        if (!(mouseFrame.equals(e.getSource()))) {
                            allFramesWithoutSelf.add(mouseFrame);
                        }
                    }
                } else allFramesWithoutSelf.addAll(mouseFrames);

                if (!(e.getSource() instanceof TotalCountFrame)) {
                    allFramesWithoutSelf.add(Main.totalCountFrame);
                }
                if (!(e.getSource() instanceof ChartFrame)) {
                    allFramesWithoutSelf.add(Main.chartFrame);
                }
                //实现
                //鼠标等效位置
                ArrayList<Point> thisPs = new ArrayList<>();
                //左上角点
                thisPs.add(
                        new Point(
                                mouse.x - mouseInFrameTemp.x,
                                mouse.y - mouseInFrameTemp.y
                        )
                );
                //右上角点
                thisPs.add(
                        new Point(
                                mouse.x - mouseInFrameTemp.x + e.getComponent().getWidth(),
                                mouse.y - mouseInFrameTemp.y
                        )
                );
                //左下角点
                thisPs.add(
                        new Point(
                                mouse.x - mouseInFrameTemp.x,
                                mouse.y - mouseInFrameTemp.y + e.getComponent().getHeight()
                        )
                );
                //右下角点
                thisPs.add(
                        new Point(
                                mouse.x - mouseInFrameTemp.x + e.getComponent().getWidth(),
                                mouse.y - mouseInFrameTemp.y + e.getComponent().getHeight()
                        )
                );
                for (JFrame frame : allFramesWithoutSelf) {
                    Point otherLoc = frame.getLocationOnScreen();
                    for (int i = 0; i < thisPs.size(); i++) {
                        //i = 0时,不处理
                        //i = 1或3时,x - width(偏移本身宽度)
                        //i = 2或3时,y - height(偏移本身高度)
                        //左侧
                        if (Math.abs(otherLoc.x - thisPs.get(i).x) < 6) {
                            lastLoc.x = otherLoc.x - (i % 2) * e.getComponent().getWidth();
                        }
                        //顶部
                        if (Math.abs(otherLoc.y - thisPs.get(i).y) < 6) {
                            lastLoc.y = otherLoc.y - ((i >= 2) ? e.getComponent().getHeight() : 0);
                        }
                        //横轴中间
                        if (Math.abs((otherLoc.x + frame.getWidth() / 2) - thisPs.get(i).x) < 6) {
                            lastLoc.x = otherLoc.x + frame.getWidth() / 2 - (i % 2) * e.getComponent().getWidth();
                        }
                        //纵轴中间
                        if (Math.abs((otherLoc.y + frame.getHeight() / 2) - thisPs.get(i).y) < 6) {
                            lastLoc.y = otherLoc.y + frame.getHeight() / 2 - ((i >= 2) ? e.getComponent().getHeight() : 0);
                        }
                        //横轴正中间
                        if (Math.abs((otherLoc.x + frame.getWidth() / 2) - thisPs.get(i).x - e.getComponent().getWidth() / 2) < 6) {
                            lastLoc.x = otherLoc.x + frame.getWidth() / 2 - e.getComponent().getWidth() / 2 - (i % 2) * e.getComponent().getWidth();
                        }
                        //纵轴正中间
                        if (Math.abs((otherLoc.y + frame.getHeight() / 2) - thisPs.get(i).y - e.getComponent().getHeight() / 2) < 6) {
                            lastLoc.y = otherLoc.y + frame.getHeight() / 2 - e.getComponent().getHeight() / 2 - ((i >= 2) ? e.getComponent().getHeight() : 0);
                        }
                        //右侧
                        if (Math.abs(otherLoc.x + frame.getWidth() - thisPs.get(i).x) < 6) {
                            lastLoc.x = otherLoc.x + frame.getWidth() - (i % 2) * e.getComponent().getWidth();
                        }
                        //底部
                        if (Math.abs(otherLoc.y + frame.getHeight() - thisPs.get(i).y) < 6) {
                            lastLoc.y = otherLoc.y + frame.getHeight() - ((i >= 2) ? e.getComponent().getHeight() : 0);
                        }
                    }
                }
                e.getComponent().setLocation(lastLoc);
            }
        }

    }
}
