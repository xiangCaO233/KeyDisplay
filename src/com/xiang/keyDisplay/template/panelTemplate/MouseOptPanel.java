package com.xiang.keyDisplay.template.panelTemplate;

import com.xiang.keyDisplay.main.Main;
import com.xiang.keyDisplay.main.VKKeys;
import com.xiang.keyDisplay.others.GU;
import com.xiang.keyDisplay.template.frameTemplate.MouseFrame;
import com.xiang.keyDisplay.template.uis.CustomizeToggleButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class MouseOptPanel extends JPanel {
    //选择区外壳
    JPanel buttonsShell;
    //切换按钮容器
    JPanel buttonListVbox;
    //全选按钮
    CustomizeToggleButton selectAllButton;
    //外壳容器
    JPanel settingsShell;
    //全部设置容器
    JPanel settings;
    JPanel settingsPanel1;
    JPanel settingsPanel2;
    //应用按钮容器
    ApplyButtonsPane applyButtonsPane;
    //切换按钮,全部鼠标按键
    CustomizeToggleButton[] mousesButtons;
    //背景颜色选择器
    ColorPickPane bgPicker;
    //激活颜色选择器
    ColorPickPane activePicker;
    //字体设置框
    TextFiledPane fontSetting;
    //字体大小设置框
    TextFiledPane fontSizeSetting;
    //宽度设置框
    SizeSetPane widthSetting;
    //高度设置框
    SizeSetPane heightSetting;
    //显示计数
    CustomizeToggleButton showCounts;
    //显示cps
    CustomizeToggleButton showCps;


    public MouseOptPanel() {

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(Main.DEFAULT_BG_COLOR);

        buttonsShell = new JPanel();
        buttonsShell.setLayout(new BoxLayout(buttonsShell, BoxLayout.Y_AXIS));
        buttonsShell.setBackground(new Color(0, 0, 0, 0));
        buttonsShell.setMaximumSize(GU.toAbsSize(90, Integer.MAX_VALUE));

        buttonListVbox = new JPanel();
        buttonListVbox.setBackground(new Color(0, 0, 0, 0));
        buttonListVbox.setLayout(new BoxLayout(buttonListVbox, BoxLayout.Y_AXIS));
        buttonListVbox.setMaximumSize(GU.toAbsSize(90, Integer.MAX_VALUE));

        mousesButtons = new CustomizeToggleButton[Main.mouseFrames.size()];
        Collection<Integer> mouseKeys = Main.mouseFrames.keySet();
        int index = 0;
        for (Integer mouseKey : mouseKeys) {
            mousesButtons[index] = new CustomizeToggleButton(Main.mouseFrames.get(mouseKey).mouseKeyName);
            mousesButtons[index].setMaximumSize(GU.toAbsSize(Integer.MAX_VALUE, 30));
            mousesButtons[index].addActionListener(e -> {
                selectAllButton.setSelected(isAllSelected());
                if (selectAllButton.isSelected())
                    selectAllButton.setName("全不选");
                else
                    selectAllButton.setName("全选");
                getTopLevelAncestor().repaint();
            });
            buttonListVbox.add(mousesButtons[index]);
            index++;
        }

        selectAllButton = new CustomizeToggleButton("全选");
        selectAllButton.setMaximumSize(GU.toAbsSize(100, 30));
        selectAllButton.addActionListener(e -> {
            if (selectAllButton.isSelected()) {
                selectAllButton.setName("全不选");
                for (CustomizeToggleButton button : mousesButtons) {
                    button.setSelected(true);
                }
            } else {
                selectAllButton.setName("全选");
                for (CustomizeToggleButton button : mousesButtons) {
                    button.setSelected(false);
                }
            }
            getTopLevelAncestor().repaint();

        });


        buttonsShell.add(buttonListVbox);
        buttonsShell.add(selectAllButton);

        add(buttonsShell);

        bgPicker = new ColorPickPane("背景:");
        bgPicker.setColor(Main.DEFAULT_BG_COLOR);
        activePicker = new ColorPickPane("触发:");
        activePicker.setColor(Main.DEFAULT_PRESS_COLOR);
        fontSetting = new TextFiledPane("字体:", "Default Font");
        showCounts = new CustomizeToggleButton("显示计数");
        showCounts.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        showCps = new CustomizeToggleButton("显示cps");
        showCps.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        //取最后一个鼠标按键的字体大小
        Set<Integer> keySet = Main.mouseFrames.keySet();
        int key = -1;
        for (Integer i : keySet) {
            key = i;
        }
        if (key != -1) {
            //有鼠标监听
            MouseFrame mouseFrame = Main.mouseFrames.get(key);
            fontSizeSetting = new TextFiledPane("字号:", String.valueOf(mouseFrame.labels[0].getFont().getSize2D()));
            widthSetting = new SizeSetPane("宽:", mouseFrame.getWidth());
            heightSetting = new SizeSetPane("高:", mouseFrame.getHeight());
        } else {
            //无鼠标监听
            fontSizeSetting = new TextFiledPane("字号:", String.valueOf(0));
            widthSetting = new SizeSetPane("宽:", Main.DEFAULT_SIZE.width);
            heightSetting = new SizeSetPane("高:", Main.DEFAULT_SIZE.height);
        }

        //外壳
        settingsShell = new JPanel();
        settingsShell.setLayout(new BoxLayout(settingsShell, BoxLayout.Y_AXIS));
        settingsShell.setBackground(new Color(0, 0, 0, 0));
        //全部设置
        settings = new JPanel();
        settings.setLayout(new BoxLayout(settings, BoxLayout.X_AXIS));
        settings.setBackground(new Color(0, 0, 0, 0));
        //细分设置
        settingsPanel1 = new JPanel();
        settingsPanel1.setLayout(new BoxLayout(settingsPanel1, BoxLayout.Y_AXIS));
        settingsPanel1.setMaximumSize(GU.toAbsSize(Integer.MAX_VALUE, Integer.MAX_VALUE));
        settingsPanel1.setBackground(new Color(0, 0, 0, 0));
        settingsPanel1.add(bgPicker);
        settingsPanel1.add(fontSetting);
        settingsPanel1.add(widthSetting);
        settingsPanel1.add(showCounts);

        settingsPanel2 = new JPanel();
        settingsPanel2.setLayout(new BoxLayout(settingsPanel2, BoxLayout.Y_AXIS));
        settingsPanel2.setMaximumSize(GU.toAbsSize(Integer.MAX_VALUE, Integer.MAX_VALUE));
        settingsPanel2.setBackground(new Color(0, 0, 0, 0));
        settingsPanel2.add(activePicker);
        settingsPanel2.add(fontSizeSetting);
        settingsPanel2.add(heightSetting);
        settingsPanel2.add(showCps);

        settings.add(settingsPanel1);
        settings.add(settingsPanel2);

        applyButtonsPane = new ApplyButtonsPane();
        //确认按钮事件
        applyButtonsPane.setDoneAction(e -> {
            ArrayList<MouseFrame> selectedFrames = getSelectedFrames();
            if (!selectedFrames.isEmpty())
                apply(selectedFrames);
            new Thread(() -> {
                Main.advanceSettingsMenu.dispose();
                Main.advanceSettingsMenu = null;
            }).start();
        });
        //取消按钮事件
        applyButtonsPane.setCancelAction(
                e -> new Thread(() -> {
                    Main.advanceSettingsMenu.dispose();
                    Main.advanceSettingsMenu = null;
                }).start()
        );
        //应用按钮事件
        applyButtonsPane.setDoneAction(e -> {
            ArrayList<MouseFrame> selectedFrames = getSelectedFrames();
            if (!selectedFrames.isEmpty())
                apply(selectedFrames);
        });

        settingsShell.add(settings);
        settingsShell.add(applyButtonsPane);

        add(settingsShell);

    }

    ArrayList<MouseFrame> getSelectedFrames() {
        ArrayList<MouseFrame> frames = new ArrayList<>();
        for (CustomizeToggleButton but : mousesButtons) {
            if (but.isSelected())
                frames.add(Main.mouseFrames.get(VKKeys.KeySearch(but.getName())));
        }
        return frames;
    }

    void applyColor(ArrayList<MouseFrame> frames) {
        for (MouseFrame frame : frames) {
            frame.backgroundColor = bgPicker.getColor();
            frame.releaseColor = frame.backgroundColor;
            frame.pressColor = activePicker.getColor();
            frame.repaint();
        }
    }

    void applyFont(ArrayList<MouseFrame> frames) {
        try {
            for (MouseFrame frame : frames) {
                if ("Default Font".equals(fontSetting.field.getText())) {
                    frame.setAllLabelsFont(
                            Main.DEFAULT_FONT.deriveFont(Float.parseFloat(fontSizeSetting.field.getText()))
                    );
                } else
                    frame.setAllLabelsFont(
                            Font.getFont(fontSetting.field.getText()).deriveFont(Float.parseFloat(fontSizeSetting.field.getText()))
                    );
            }
        } catch (Exception ignored) {
        }
    }

    void applySize(ArrayList<MouseFrame> frames) {
        try {
            for (MouseFrame frame : frames) {
                frame.updateSize(new Dimension(
                        Integer.parseInt(widthSetting.field.getText()),
                        Integer.parseInt(heightSetting.field.getText())
                ));
            }
        } catch (Exception ignored) {
        }
    }

    void apply(ArrayList<MouseFrame> frames) {
        applyColor(frames);
        applyFont(frames);
        applySize(frames);
    }

    boolean isAllSelected() {
        boolean isAllSelected = true;
        for (CustomizeToggleButton toggleButton : mousesButtons) {
            if (!toggleButton.isSelected())
                isAllSelected = false;
        }
        return isAllSelected;
    }
}
