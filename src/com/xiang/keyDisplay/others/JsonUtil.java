package com.xiang.keyDisplay.others;

import com.alibaba.fastjson2.JSONArray;

import java.awt.*;

public class JsonUtil {
    public static JSONArray color2Json(Color color) {
        JSONArray colorJson = new JSONArray();
        colorJson.add(color.getRed());
        colorJson.add(color.getGreen());
        colorJson.add(color.getBlue());
        colorJson.add(color.getAlpha());
        return colorJson;
    }

    public static JSONArray rec2Json(Rectangle rec) {
        JSONArray recJSON = new JSONArray();
        recJSON.add(rec.x);
        recJSON.add(rec.y);
        recJSON.add(rec.width);
        recJSON.add(rec.height);
        return recJSON;
    }

    public static Color json2Color(JSONArray colorJson) {
        return new Color(
                colorJson.getInteger(0),
                colorJson.getInteger(1),
                colorJson.getInteger(2),
                colorJson.getInteger(3)
        );
    }

    public static Rectangle json2Rec(JSONArray recJson) {
        return new Rectangle(
                recJson.getInteger(0),
                recJson.getInteger(1),
                recJson.getInteger(2),
                recJson.getInteger(3)
        );
    }
}
