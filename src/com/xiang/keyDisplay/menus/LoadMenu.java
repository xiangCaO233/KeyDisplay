package com.xiang.keyDisplay.menus;

import com.alibaba.fastjson2.JSON;
import com.xiang.keyDisplay.main.Main;
import com.xiang.keyDisplay.others.ComponentUtils;
import com.xiang.keyDisplay.template.Choosers;
import javafx.application.Platform;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LoadMenu extends MenuTemplate implements Choosers.FileChooseCallBack {
    File[] saveList;
    File file;
    JLabel title;
    JButton[] saveButtons;
    JButton fromFile;

    public LoadMenu() throws HeadlessException {
        super(Main.DEFAULT_BORDER_COLOR, Main.DEFAULT_BG_COLOR);
        title = ComponentUtils.registerLabel("加载存档");
        title.setSize(120, 30);
        title.setLocation(1, 1);
        addCom(title);
        saveList = Main.savesPath.listFiles();
        if (saveList != null) {
            saveButtons = new JButton[saveList.length];
            for (int i = 0; i < saveList.length; i++) {
                saveButtons[i] = ComponentUtils.registerButton(saveList[i].getName());
                saveButtons[i].setSize(120, 30);
                saveButtons[i].setLocation(1, (i + 1) * 30 + 1);

                int finalI = i;
                saveButtons[i].addActionListener(e -> {
                    try {
                        FileInputStream fis = new FileInputStream(saveList[finalI]);
                        if (fis.available() == 0)
                            System.out.println("存档尚未初始化");
                        else
                            Main.loadFromConfig(JSON.parseObject(fis, StandardCharsets.UTF_8));
                        fis.close();
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                addCom(saveButtons[i]);
            }
        }
        fromFile = ComponentUtils.registerButton("打开文件");
        fromFile.setSize(120, 30);
        fromFile.setLocation(1, (saveButtons.length + 1) * 30 + 1);
        fromFile.addActionListener(e -> {
            Platform.runLater(() -> {
                Choosers.sendFile(this);
                if (file != null) {
                    String fileName = file.getName();
                    if (!"json".equals(fileName.substring(fileName.lastIndexOf('.') + 1))) {
                        System.out.println("请选择json文件");
                        return;
                    }
                    try {
                        FileInputStream fis = new FileInputStream(file);
                        Main.loadFromConfig(JSON.parseObject(fis, StandardCharsets.UTF_8));
                        fis.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    System.out.println("取消选择");
                }
            });
        });

        addCom(fromFile);

        setSize(122, 30 * (saveButtons.length + 2) + 2);
    }

    @Override
    public void callback(File file) {
        this.file = file;
    }
}
