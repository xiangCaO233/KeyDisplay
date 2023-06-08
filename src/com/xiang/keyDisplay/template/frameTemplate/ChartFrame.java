package com.xiang.keyDisplay.template.frameTemplate;

import com.alibaba.fastjson2.JSONObject;
import com.xiang.keyDisplay.main.Main;
import com.xiang.keyDisplay.others.AreaColor;
import com.xiang.keyDisplay.others.JsonUtil;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberTickUnitSource;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 表格窗体,
 */
public class ChartFrame extends CustomizeFrame {
    public JFreeChart chart;
    public DefaultCategoryDataset dataset;
    public Color backgroundColor;
    public Color borderColor;
    public AreaColor areaColor;

    public ChartFrame(Color border, Color bg, AreaColor area) {
        super(border, bg, new BufferedImage(Main.DEFAULT_SIZE.width, Main.DEFAULT_SIZE.height, BufferedImage.TYPE_INT_ARGB));
        borderColor = border;
        backgroundColor = bg;
        areaColor = area;
        dataset = new DefaultCategoryDataset();
        updateBounds();
        chart = ChartFactory.createAreaChart(
                "kps(近五分钟内)",
                "",
                "",
                dataset
        );
        chart.setBackgroundPaint(new Color(0, 0, 0, 0));
        chart.getLegend().setVisible(false);
        chart.getTitle().setFont(Main.DEFAULT_FONT.deriveFont(20f));
        chart.getTitle().setPaint(new Color(230, 230, 230, 230));
        chart.getPlot().setOutlineVisible(false);
        chart.setBorderVisible(false);
        setAreaColor(area);

        CategoryPlot plot = chart.getCategoryPlot();


        plot.getDomainAxis().setVisible(false);
        plot.setForegroundAlpha(0.6f);

        //纵轴
        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setAutoRange(true);
        rangeAxis.setAutoTickUnitSelection(false);
        rangeAxis.setTickLabelPaint(Color.white);
        rangeAxis.setTickLabelFont(Main.DEFAULT_FONT.deriveFont(12f));
        rangeAxis.setStandardTickUnits(new NumberTickUnitSource());
        plot.getRangeAxis().setLabel("");

        //图表区
        plot.setBackgroundPaint(new Color(0, 0, 0, 0));
        plot.setDomainGridlinesVisible(false);
        plot.setRangeGridlinesVisible(false);

        try {
            setRootImage(
                    chart.createBufferedImage(
                            Main.DEFAULT_SIZE.width * Main.keyFrames.size(),
                            Main.DEFAULT_SIZE.height * 2
                    ));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //注册鼠标监听器
        registerMouseListener();
    }

    public ChartFrame(JSONObject config) {
        super(
                JsonUtil.json2Color(config.getJSONArray("borderColor")),
                JsonUtil.json2Color(config.getJSONArray("backgroundColor")),
                new BufferedImage(Main.DEFAULT_SIZE.width, Main.DEFAULT_SIZE.height, BufferedImage.TYPE_INT_ARGB)
        );
        borderColor = JsonUtil.json2Color(config.getJSONArray("borderColor"));
        backgroundColor = JsonUtil.json2Color(config.getJSONArray("backgroundColor"));
        areaColor = AreaColor.valueOf(config.getString("areaColor"));
        dataset = new DefaultCategoryDataset();

        setSize(JsonUtil.json2Rec(config.getJSONArray("bounds")).getSize());
        chart = ChartFactory.createAreaChart(
                "kps(近五分钟内)",
                "",
                "",
                dataset
        );
        chart.setBackgroundPaint(new Color(0, 0, 0, 0));
        chart.getLegend().setVisible(false);
        chart.getTitle().setFont(Main.DEFAULT_FONT.deriveFont(20f));
        chart.getTitle().setPaint(new Color(230, 230, 230, 230));
        chart.getPlot().setOutlineVisible(false);
        chart.setBorderVisible(false);
        setAreaColor(areaColor);

        CategoryPlot plot = chart.getCategoryPlot();
        plot.getDomainAxis().setVisible(false);
        plot.setForegroundAlpha(0.6f);
        //纵轴
        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setAutoRange(true);
        rangeAxis.setAutoTickUnitSelection(false);
        rangeAxis.setTickLabelPaint(Color.white);
        rangeAxis.setTickLabelFont(Main.DEFAULT_FONT.deriveFont(12f));
        rangeAxis.setStandardTickUnits(new NumberTickUnitSource());
        plot.getRangeAxis().setLabel("");

        //图表区
        plot.setBackgroundPaint(new Color(0, 0, 0, 0));
        plot.setDomainGridlinesVisible(false);
        plot.setRangeGridlinesVisible(false);

        try {
            setRootImage(
                    chart.createBufferedImage(
                            getWidth(),
                            getHeight()
                    ));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //注册鼠标监听器
        registerMouseListener();

    }

    public JSONObject toConfig() {
        JSONObject config = new JSONObject();
        //表格面板配置
        config.put(
                "areaColor", areaColor
        );
        config.put(
                "bounds", JsonUtil.rec2Json(getBounds())
        );
        config.put(
                "backgroundColor", JsonUtil.color2Json(backgroundColor)
        );
        config.put(
                "borderColor", JsonUtil.color2Json(borderColor)
        );
        return config;
    }

    void setAreaColor(AreaColor color) {
        dataset.clear();
        int size = AreaColor.toInt(color);
        for (int i = 0; i < size; i++) {
            dataset.addValue(0, String.valueOf(i), "");
        }

    }

    public void updateBounds() {
        updateSize();
        resetLocation();
    }

    public void updateSize() {
        if (Main.keyFrames.size() >= 4) {
            setSize(
                    Main.DEFAULT_SIZE.width * Main.keyFrames.size(),
                    Main.DEFAULT_SIZE.height * 2
            );
        }
    }

    public void resetLocation() {
        setLocation(
                (Main.SCREEN_SIZE.width - getWidth()) / 2,
                (Main.SCREEN_SIZE.height - Main.DEFAULT_SIZE.height) / 2 - getHeight()
        );
    }
}
