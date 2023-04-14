package com.xiang.keyDisplay.others;

import java.awt.*;
import java.util.ArrayList;

public class GraphicUtils {
    public static Color antiColor(Color src) {
        return new Color(255 - src.getRed(), 255 - src.getGreen(), 255 - src.getBlue(), src.getAlpha());
    }

    /**
     * 将颜色转为hex
     *
     * @param srcColor 源颜色
     * @return hex字符串(包含 #)
     */
    public static String color2Hex(Color srcColor) {
        String r = Integer.toHexString(srcColor.getRed());
        String g = Integer.toHexString(srcColor.getGreen());
        String b = Integer.toHexString(srcColor.getBlue());
        String a = "ff";

        if (r.length() == 1) {
            r = ("0" + r);
        }
        if (g.length() == 1) {
            g = ("0" + g);
        }
        if (b.length() == 1) {
            b = ("0" + b);
        }
        r = r.toUpperCase();
        g = g.toUpperCase();
        b = b.toUpperCase();

        if (srcColor.getAlpha() != 255) {
            a = Integer.toHexString(srcColor.getAlpha());
            if (a.length() == 1) {
                a = ("0" + a);
            }
            a = a.toUpperCase();
            return "#" + r + g + b + a;
        } else {
            return "#" + r + g + b;
        }
    }

    /**
     * 将hex字符串转化为颜色
     *
     * @param colorStr 颜色字符串
     * @return 颜色对象
     */
    public static Color hex2Color(String colorStr) {
        if (colorStr != null && !"".equals(colorStr)) {
            colorStr = colorStr.replaceAll("#", "");
            if (colorStr.length() == 6) {
                return new Color(
                        Integer.parseInt(colorStr.substring(0, 2), 16),
                        Integer.parseInt(colorStr.substring(2, 4), 16),
                        Integer.parseInt(colorStr.substring(4, 6), 16)
                );
            } else if (colorStr.length() == 8) {
                return new Color(
                        Integer.parseInt(colorStr.substring(0, 2), 16),
                        Integer.parseInt(colorStr.substring(2, 4), 16),
                        Integer.parseInt(colorStr.substring(4, 6), 16),
                        Integer.parseInt(colorStr.substring(6, 8), 16)
                );
            }
        }
        return null;
    }

    public static ArrayList<Integer> divisors(int N) {
        ArrayList<Integer> res = new ArrayList<>();
        res.add(1);
        if (N == 1) return res;
        res.add(N);
        final int sqrt = (int) Math.sqrt(N);
        for (int i = 2; i < sqrt; i++) {
            if (N % i == 0) {
                res.add(N / i);
                res.add(i);
            }
        }
        if (sqrt * sqrt == N) res.add(sqrt);
        return res;
    }

    public static void main(String[] args) {
        for (int i = 1; i < 455; i++) {
            System.out.println("一排" + i + "个:余数" + 454 % i);
        }
    }
}
