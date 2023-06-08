package com.xiang.keyDisplay.threads;

import com.xiang.keyDisplay.main.Main;

import java.util.TimerTask;

public class ChartRefreshTask extends TimerTask {
    int time = 0;

    @Override
    public void run() {
        Main.chartFrame.dataset.addValue(
                Main.totalCountFrame.currentKps - Main.keyFrames.size(), "kps", String.valueOf(++time)
        );
        if (Main.chartFrame.dataset.getColumnKeys().size() > 300 * Main.fps) {
            Main.chartFrame.dataset.removeColumn(0);
        }
        try {
            Main.chartFrame.setRootImage(
                    Main.chartFrame.chart.createBufferedImage(
                            Main.chartFrame.getWidth() - 2,
                            Main.chartFrame.getHeight() - 2
                    )
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Main.chartFrame.repaint();
    }
}
