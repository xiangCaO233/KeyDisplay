package com.xiang.keyDisplay.menus;

import com.xiang.keyDisplay.main.Main;
import com.xiang.keyDisplay.others.ComponentUtils;
import com.xiang.keyDisplay.template.Choosers;
import javafx.application.Platform;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SaveMenu extends MenuTemplate implements Choosers.FileChooseCallBack {
    JLabel title;
    JTextField inputField;
    JButton cancel;
    JButton done;
    JButton saveAs;
    File file;

    public SaveMenu() throws HeadlessException {
        super(Main.DEFAULT_BORDER_COLOR, Main.DEFAULT_BG_COLOR);
        setSize(162, 162);
        title = ComponentUtils.registerLabel("保存为文件");
        title.setSize(getWidth() - 2, 30);
        title.setLocation(1, 1);
        addCom(title);

        inputField = new JTextField();
        inputField.setFont(Main.DEFAULT_FONT.deriveFont(16f));
        inputField.setBackground(Main.DEFAULT_BG_COLOR);
        inputField.setForeground(Main.DEFAULT_BORDER_COLOR);
        inputField.setText("save-" + (Main.savesPath.listFiles().length));
        inputField.setHorizontalAlignment(JTextField.CENTER);
        inputField.setSize(getWidth() - 2, 40);
        inputField.setLocation(1, 31);
        addCom(inputField);

        done = ComponentUtils.registerButton("保存");
        done.setSize(getWidth() - 2, 30);
        done.setLocation(1, 71);
        done.addActionListener(e -> {
            if (inputField.getText() == null || "".equals(inputField.getText())) {
                System.out.println("请输入文件名!");
            } else {
                Main.saveToFile(
                        new File(Main.savesPath.getAbsolutePath() + "\\" + inputField.getText() + ".json")
                );
            }
        });
        addCom(done);

        saveAs = ComponentUtils.registerButton("另存为");
        saveAs.setSize(getWidth() - 2, 30);
        saveAs.setLocation(1, 101);
        saveAs.addActionListener(e -> {
            if (inputField.getText() == null || "".equals(inputField.getText())) {
                System.out.println("请输入文件名!");
            } else {
                Platform.runLater(() -> {
                    Choosers.sendDir(this);
                    if (file != null) {
                        Main.saveToFile(file);
                        try {
                            Desktop.getDesktop().open(file.getParentFile());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    } else {
                        System.out.println("取消保存");
                        setVisible(false);
                    }
                });
            }
        });
        addCom(saveAs);

        cancel = ComponentUtils.registerButton("取消");
        cancel.setSize(getWidth() - 2, 30);
        cancel.setLocation(1, 131);
        cancel.addActionListener(e -> {
            cancel.getTopLevelAncestor().setVisible(false);
        });
        addCom(cancel);

    }

    @Override
    public void callback(File file) {
        this.file = file;
    }
}
