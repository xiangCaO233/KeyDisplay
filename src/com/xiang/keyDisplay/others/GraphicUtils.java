package com.xiang.keyDisplay.others;

import java.awt.*;

public class GraphicUtils {
    public static Color antiColor(Color src) {
        return new Color(255 - src.getRed(), 255 - src.getGreen(), 255 - src.getBlue(), src.getAlpha());
    }
}
