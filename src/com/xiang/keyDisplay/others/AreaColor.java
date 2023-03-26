package com.xiang.keyDisplay.others;

/**
 * JfreeChart区域图区域填充颜色(按数据集数量切换)
 */
public enum AreaColor {
    RED, BLUE, GREEN, YELLOW, PURPLE, CYAN, PINK;

    public static int toInt(AreaColor color) {
        switch (color) {
            case RED -> {
                return 0;
            }
            case BLUE -> {
                return 1;
            }
            case GREEN -> {
                return 2;
            }
            case YELLOW -> {
                return 3;
            }
            case PURPLE -> {
                return 4;
            }
            case CYAN -> {
                return 5;
            }
            case PINK -> {
                return 6;
            }
        }
        return 0;
    }
}
