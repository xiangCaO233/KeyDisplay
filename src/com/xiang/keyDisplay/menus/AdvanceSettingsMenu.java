package com.xiang.keyDisplay.menus;

import com.xiang.keyDisplay.main.Main;

import javax.swing.*;
import java.awt.*;

public class AdvanceSettingsMenu extends MenuTemplate {
    int minHeight;
    int maxHeight;
    JPanel componentSettings;
    JPanel otherSettings;

    public AdvanceSettingsMenu() throws HeadlessException {
        super(Main.DEFAULT_BORDER_COLOR, Main.DEFAULT_BG_COLOR);

    }
}
