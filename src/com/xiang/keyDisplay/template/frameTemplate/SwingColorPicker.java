package com.xiang.keyDisplay.template.frameTemplate;

import com.xiang.keyDisplay.main.Main;
import com.xiang.keyDisplay.menus.MenuTemplate;
import com.xiang.keyDisplay.others.ComponentUtils;
import com.xiang.keyDisplay.others.GU;
import com.xiang.keyDisplay.template.panelTemplate.ColorBlockPanel;
import com.xiang.keyDisplay.template.panelTemplate.ColorPickPane;
import com.xiang.keyDisplay.template.panelTemplate.SliderPane;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 颜色选择功能面板,Frame
 */
public class SwingColorPicker extends MenuTemplate {
    //预设颜色
    String[] colorStrs = new String[]{
            "#FFFAFA", "#F8F8FF", "#F5F5F5", "#DCDCDC", "#FFFAF0",
            "#FDF5E6", "#FAF0E6", "#FAEBD7", "#FFEFD5", "#FFEBCD",
            "#FFE4C4", "#FFDAB9", "#FFDEAD", "#FFE4B5", "#FFF8DC",
            "#FFFFF0", "#FFFACD", "#FFF5EE", "#F0FFF0", "#F5FFFA",
            "#F0FFFF", "#F0F8FF", "#E6E6FA", "#FFF0F5", "#FFE4E1",
            "#FFFFFF", "#000000", "#2F4F4F", "#696969", "#708090",
            "#778899", "#BEBEBE", "#D3D3D3", "#191970", "#000080",
            "#6495ED", "#483D8B", "#6A5ACD", "#7B68EE", "#8470FF",
            "#0000CD", "#4169E1", "#0000FF", "#1E90FF", "#00BFFF",
            "#87CEEB", "#87CEFA", "#4682B4", "#B0C4DE", "#ADD8E6",
            "#B0E0E6", "#AFEEEE", "#00CED1", "#48D1CC", "#40E0D0",
            "#00FFFF", "#E0FFFF", "#5F9EA0", "#66CDAA", "#7FFFD4",
            "#006400", "#556B2F", "#8FBC8F", "#2E8B57", "#3CB371",
            "#20B2AA", "#98FB98", "#00FF7F", "#7CFC00", "#00FF00",
            "#7FFF00", "#00FA9A", "#ADFF2F", "#32CD32", "#9ACD32",
            "#228B22", "#6B8E23", "#BDB76B", "#EEE8AA", "#FAFAD2",
            "#FFFFE0", "#FFFF00", "#FFD700", "#EEDD82", "#DAA520",
            "#B8860B", "#BC8F8F", "#CD5C5C", "#8B4513", "#A0522D",
            "#CD853F", "#DEB887", "#F5F5DC", "#F5DEB3", "#F4A460",
            "#D2B48C", "#D2691E", "#B22222", "#A52A2A", "#E9967A",
            "#FA8072", "#FFA07A", "#FFA500", "#FF8C00", "#FF7F50",
            "#F08080", "#FF6347", "#FF4500", "#FF0000", "#FF69B4",
            "#FF1493", "#FFC0CB", "#FFB6C1", "#DB7093", "#B03060",
            "#C71585", "#D02090", "#FF00FF", "#EE82EE", "#DDA0DD",
            "#DA70D6", "#BA55D3", "#9932CC", "#9400D3", "#8A2BE2",
            "#A020F0", "#9370DB", "#D8BFD8", "#FFFAFA", "#EEE9E9",
            "#CDC9C9", "#8B8989", "#FFF5EE", "#EEE5DE", "#CDC5BF",
            "#8B8682", "#FFEFDB", "#EEDFCC", "#CDC0B0", "#8B8378",
            "#FFE4C4", "#EED5B7", "#CDB79E", "#8B7D6B", "#FFDAB9",
            "#EECBAD", "#CDAF95", "#8B7765", "#FFDEAD", "#EECFA1",
            "#CDB38B", "#8B795E", "#FFFACD", "#EEE9BF", "#CDC9A5",
            "#8B8970", "#FFF8DC", "#EEE8CD", "#CDC8B1", "#8B8878",
            "#FFFFF0", "#EEEEE0", "#CDCDC1", "#8B8B83", "#F0FFF0",
            "#E0EEE0", "#C1CDC1", "#838B83", "#FFF0F5", "#EEE0E5",
            "#CDC1C5", "#8B8386", "#FFE4E1", "#EED5D2", "#CDB7B5",
            "#8B7D7B", "#F0FFFF", "#E0EEEE", "#C1CDCD", "#838B8B",
            "#836FFF", "#7A67EE", "#6959CD", "#473C8B", "#4876FF",
            "#436EEE", "#3A5FCD", "#27408B", "#0000FF", "#0000EE",
            "#0000CD", "#00008B", "#1E90FF", "#1C86EE", "#1874CD",
            "#104E8B", "#63B8FF", "#5CACEE", "#4F94CD", "#36648B",
            "#00BFFF", "#00B2EE", "#009ACD", "#00688B", "#87CEFF",
            "#7EC0EE", "#6CA6CD", "#4A708B", "#B0E2FF", "#A4D3EE",
            "#8DB6CD", "#607B8B", "#C6E2FF", "#B9D3EE", "#9FB6CD",
            "#6C7B8B", "#CAE1FF", "#BCD2EE", "#A2B5CD", "#6E7B8B",
            "#BFEFFF", "#B2DFEE", "#9AC0CD", "#68838B", "#E0FFFF",
            "#D1EEEE", "#B4CDCD", "#7A8B8B", "#BBFFFF", "#AEEEEE",
            "#96CDCD", "#668B8B", "#98F5FF", "#8EE5EE", "#7AC5CD",
            "#53868B", "#00F5FF", "#00E5EE", "#00C5CD", "#00868B",
            "#00FFFF", "#00EEEE", "#00CDCD", "#008B8B", "#97FFFF",
            "#8DEEEE", "#79CDCD", "#528B8B", "#7FFFD4", "#76EEC6",
            "#66CDAA", "#458B74", "#C1FFC1", "#B4EEB4", "#9BCD9B",
            "#698B69", "#54FF9F", "#4EEE94", "#43CD80", "#2E8B57",
            "#9AFF9A", "#90EE90", "#7CCD7C", "#548B54", "#00FF7F",
            "#00EE76", "#00CD66", "#008B45", "#00FF00", "#00EE00",
            "#00CD00", "#008B00", "#7FFF00", "#76EE00", "#66CD00",
            "#458B00", "#C0FF3E", "#B3EE3A", "#9ACD32", "#698B22",
            "#CAFF70", "#BCEE68", "#A2CD5A", "#6E8B3D", "#FFF68F",
            "#EEE685", "#CDC673", "#8B864E", "#FFEC8B", "#EEDC82",
            "#CDBE70", "#8B814C", "#FFFFE0", "#EEEED1", "#CDCDB4",
            "#8B8B7A", "#FFFF00", "#EEEE00", "#CDCD00", "#8B8B00",
            "#FFD700", "#EEC900", "#CDAD00", "#8B7500", "#FFC125",
            "#EEB422", "#CD9B1D", "#8B6914", "#FFB90F", "#EEAD0E",
            "#CD950C", "#8B658B", "#FFC1C1", "#EEB4B4", "#CD9B9B",
            "#8B6969", "#FF6A6A", "#EE6363", "#CD5555", "#8B3A3A",
            "#FF8247", "#EE7942", "#CD6839", "#8B4726", "#FFD39B",
            "#EEC591", "#CDAA7D", "#8B7355", "#FFE7BA", "#EED8AE",
            "#CDBA96", "#8B7E66", "#FFA54F", "#EE9A49", "#CD853F",
            "#8B5A2B", "#FF7F24", "#EE7621", "#CD661D", "#8B4513",
            "#FF3030", "#EE2C2C", "#CD2626", "#8B1A1A", "#FF4040",
            "#EE3B3B", "#CD3333", "#8B2323", "#FF8C69", "#EE8262",
            "#CD7054", "#8B4C39", "#FFA07A", "#EE9572", "#CD8162",
            "#8B5742", "#FFA500", "#EE9A00", "#CD8500", "#8B5A00",
            "#FF7F00", "#EE7600", "#CD6600", "#8B4500", "#FF7256",
            "#EE6A50", "#CD5B45", "#8B3E2F", "#FF6347", "#EE5C42",
            "#CD4F39", "#8B3626", "#FF4500", "#EE4000", "#CD3700",
            "#8B2500", "#FF0000", "#EE0000", "#CD0000", "#8B0000",
            "#FF1493", "#EE1289", "#CD1076", "#8B0A50", "#FF6EB4",
            "#EE6AA7", "#CD6090", "#8B3A62", "#FFB5C5", "#EEA9B8",
            "#CD919E", "#8B636C", "#FFAEB9", "#EEA2AD", "#CD8C95",
            "#8B5F65", "#FF82AB", "#EE799F", "#CD6889", "#8B475D",
            "#FF34B3", "#EE30A7", "#CD2990", "#8B1C62", "#FF3E96",
            "#EE3A8C", "#CD3278", "#8B2252", "#FF00FF", "#EE00EE",
            "#CD00CD", "#8B008B", "#FF83FA", "#EE7AE9", "#CD69C9",
            "#8B4789", "#FFBBFF", "#EEAEEE", "#CD96CD", "#8B668B",
            "#E066FF", "#D15FEE", "#B452CD", "#7A378B", "#BF3EFF",
            "#B23AEE", "#9A32CD", "#68228B", "#9B30FF", "#912CEE",
            "#7D26CD", "#551A8B", "#AB82FF", "#9F79EE", "#8968CD",
            "#5D478B", "#FFE1FF", "#EED2EE", "#CDB5CD", "#8B7B8B",
            "#1C1C1C", "#363636", "#4F4F4F", "#696969", "#828282",
            "#9C9C9C", "#B5B5B5", "#CFCFCF", "#E8E8E8", "#A9A9A9",
            "#00008B", "#008B8B", "#8B008B", "#8B0000"};
    //选择的颜色
    Color selectedColor;
    //选中颜色容器显示
    public ColorBlockPanel selectColorPanel;
    //所绑定设置的容器
    ColorPickPane bindPickerPane;
    //预设颜色颜色盒
    JPanel colorsBox;
    //red条
    SliderPane redSlider;
    //green条
    SliderPane greenSlider;
    //blue条
    SliderPane blueSlider;
    //alpha条
    SliderPane alphaSlider;
    //十六进制字符串
    JLabel colorHexLabel;
    JTextField colorHex;
    JButton getHex;
    //rbga字符串
    JLabel colorRgbaLabel;
    JTextField colorRgba;
    JButton getRgba;
    //确定按钮
    JButton done;
    //取消按钮
    JButton cancel;

    public SwingColorPicker(ColorPickPane pickerPane) {
        super(Main.DEFAULT_BORDER_COLOR, Main.DEFAULT_BG_COLOR);
        bindPickerPane = pickerPane;
        selectedColor = pickerPane.color;
        setSize(682, 384);

        colorsBox = new JPanel();
        colorsBox.setLayout(null);
        colorsBox.setSize(680, 260);
        colorsBox.setLocation(1, 1);
        colorsBox.setBackground(new Color(0, 0, 0, 0));
        add(colorsBox);

        //初始化选择结果颜色容器
        selectColorPanel = new ColorBlockPanel(bindPickerPane.color);
        selectColorPanel.setSize(120, 120);
        add(selectColorPanel);

        //共13行
        selectColorPanel.setLocation(
                ((getWidth() - 2) - selectColorPanel.getWidth()) / 2 + 1,
                13 * 20 + 2
        );
        //sliders
        //red-调整器
        redSlider = registerSlider("R", 0);
        add(redSlider);
        //green-调整器
        greenSlider = registerSlider("G", 1);
        add(greenSlider);
        //blue-调整器
        blueSlider = registerSlider("B", 2);
        add(blueSlider);
        //alpha-调整器
        alphaSlider = registerSlider("A", 3);
        add(alphaSlider);
        //初始化全部快捷颜色设置
        int index = 1;
        ColorBlockListener listener = new ColorBlockListener();
        for (String colorStr : colorStrs) {
            ColorBlockPanel colorBlockPanel = new ColorBlockPanel(GU.hex2Color(colorStr));
            colorBlockPanel.setSize(19, 19);
            //总行数
            int x_rows = colorsBox.getWidth() / (colorBlockPanel.getWidth() + 1) + 1;
            //当前下标所对应列位置
            int y_row = index % x_rows;
            //x位置,按列位置计算 1对应 单元宽度
            int xLoc = (y_row - 1) * (colorBlockPanel.getWidth() + 1) + 1;
            int yLoc = (index / x_rows) * (colorBlockPanel.getHeight() + 1) + 1;

            colorBlockPanel.setLocation(xLoc, yLoc);
            index++;
            colorBlockPanel.addMouseListener(listener);
            colorsBox.add(colorBlockPanel);
        }
        //hex
        colorHexLabel = ComponentUtils.registerLabel("Hex->");
        colorHexLabel.setSize(80, 40);
        colorHexLabel.setLocation(selectColorPanel.getX() + selectColorPanel.getWidth() + 1, selectColorPanel.getY());
        add(colorHexLabel);

        colorHex = new JTextField();
        colorHex.setSize(136, 40);
        colorHex.setLocation(colorHexLabel.getX() + colorHexLabel.getWidth() + 1, colorHexLabel.getY());
        colorHex.setBackground(Main.DEFAULT_BG_COLOR);
        colorHex.setForeground(Main.DEFAULT_BORDER_COLOR);
        colorHex.setBorder(null);
        colorHex.setFont(Main.DEFAULT_FONT.deriveFont(18f));
        colorHex.setText(GU.color2Hex(pickerPane.color));
        registerDocListener(colorHex);
        add(colorHex);

        getHex = ComponentUtils.registerButton("get");
        getHex.setSize(60, 40);
        getHex.setLocation(colorHex.getX() + colorHex.getWidth() + 1, colorHex.getY());
        getHex.addActionListener(e -> {
            try {
                Color color = GU.hex2Color(colorHex.getText());
                selectedColor = color;
                selectColorPanel.setCurrentColor(selectedColor);
                updateSliders();
                colorRgba.setText(String.valueOf(selectedColor.getRGB()));
            } catch (Exception ignored) {
            }
        });
        add(getHex);

        //rgba
        colorRgbaLabel = ComponentUtils.registerLabel("rgba->");
        colorRgbaLabel.setSize(80, 40);
        colorRgbaLabel.setLocation(colorHexLabel.getX(), colorHexLabel.getY() + colorHexLabel.getHeight() + 1);
        add(colorRgbaLabel);

        colorRgba = new JTextField();
        colorRgba.setSize(136, 40);
        colorRgba.setLocation(colorRgbaLabel.getX() + colorRgbaLabel.getWidth() + 1, colorRgbaLabel.getY());
        colorRgba.setBackground(Main.DEFAULT_BG_COLOR);
        colorRgba.setForeground(Main.DEFAULT_BORDER_COLOR);
        colorRgba.setBorder(null);
        colorRgba.setFont(Main.DEFAULT_FONT.deriveFont(18f));
        colorRgba.setText(String.valueOf(pickerPane.color.getRGB()));
        add(colorRgba);

        getRgba = ComponentUtils.registerButton("get");
        getRgba.setSize(60, 40);
        getRgba.setLocation(colorRgba.getX() + colorRgba.getWidth() + 1, colorRgba.getY());
        getRgba.addActionListener(e -> {
            try {
                Color color = new Color(Integer.parseInt(colorRgba.getText()), true);
                selectedColor = color;
                selectColorPanel.setCurrentColor(selectedColor);
                updateSliders();
                colorHex.setText(GU.color2Hex(color));
            } catch (Exception ignored) {
            }
        });
        add(getRgba);

        done = ComponentUtils.registerButton("确定");
        done.setSize(139, 38);
        done.setLocation(colorRgbaLabel.getX(), colorRgbaLabel.getY() + colorRgbaLabel.getHeight() + GU.absY(1));
        done.addActionListener(e -> {
            bindPickerPane.setColor(selectedColor);
            setVisible(false);
            bindPickerPane.getTopLevelAncestor().repaint();
        });
        cancel = ComponentUtils.registerButton("取消");
        cancel.setSize(139, 38);
        cancel.setLocation(done.getX() + done.getWidth(), done.getY());
        cancel.addActionListener(e -> {
            setVisible(false);
        });
        add(done);
        add(cancel);
    }

    public SwingColorPicker() {
        super(Main.DEFAULT_BORDER_COLOR, Main.DEFAULT_BG_COLOR);
        selectedColor = new Color(0, 0, 0);
        setSize(100, 80);
    }

    class ColorBlockListener extends MouseAdapter {
        //颜色块鼠标事件
        @Override
        public void mousePressed(MouseEvent e) {
            Color current = ((ColorBlockPanel) e.getSource()).getCurrentColor();
            selectColorPanel.setCurrentColor(current);
            selectedColor = current;
            updateSliders();
            updateHexAndRgba();
            ((ColorBlockPanel) e.getSource()).getTopLevelAncestor().repaint();
        }
    }

    /**
     * 构造成型sliderPane
     *
     * @param name  名
     * @param index 下标
     * @return
     */

    SliderPane registerSlider(String name, int index) {
        //初始化颜色值
        int currentVal = 0;
        switch (index) {
            case 0:
                currentVal = selectedColor.getRed();
                break;
            case 1:
                currentVal = selectedColor.getGreen();
                break;
            case 2:
                currentVal = selectedColor.getBlue();
                break;
            case 3:
                currentVal = selectedColor.getAlpha();
                break;
        }
        //调整器初始化
        SliderPane sliderPane = new SliderPane(name, currentVal);
        sliderPane.setLocation(1, selectColorPanel.getY() + 30 * index);
        sliderPane.slider.addChangeListener(e -> {
            int value = sliderPane.slider.getValue();
            Color before = selectColorPanel.getCurrentColor();
            Color setColor = selectColor(before, index, value);
            sliderPane.valueField.setText(String.valueOf(value));
            selectColorPanel.setCurrentColor(setColor);
            selectedColor = setColor;
            updateHexAndRgba();
            repaint();
        });
        sliderPane.valueField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                //插入更新
                fixLength(e);
                sliderPane.valueField.getTopLevelAncestor().repaint();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                //删除更新
                fixLength(e);
                sliderPane.valueField.getTopLevelAncestor().repaint();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                //纯更新
                fixLength(e);
                sliderPane.valueField.getTopLevelAncestor().repaint();
            }

            private void fixLength(DocumentEvent e) {
                //修正文本域长度,最高为3
                Document doc = e.getDocument();
                if (doc.getLength() > 3) {
                    sliderPane.t = new Thread(() -> {
                        try {
                            sliderPane.valueField.setText(doc.getText(0, 3));
                        } catch (BadLocationException ex) {
                            throw new RuntimeException(ex);
                        }
                    });
                    sliderPane.t.start();
                }
            }
        });
        sliderPane.valueField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                //丢失焦点事件,检测是否在范围之内
                int value = Integer.parseInt(sliderPane.valueField.getText());
                if (value > 255)
                    sliderPane.valueField.setText(String.valueOf(255));
                //获取最终修正后数值
                int latestValue = Integer.parseInt(sliderPane.valueField.getText());
                //设置slider位置
                sliderPane.slider.setValue(latestValue);
                Color before = selectColorPanel.getCurrentColor();
                Color setColor = selectColor(before, index, latestValue);
                selectColorPanel.setCurrentColor(setColor);
                selectedColor = setColor;
                updateHexAndRgba();
                sliderPane.valueField.getTopLevelAncestor().repaint();
            }
        });
        return sliderPane;
    }

    void registerDocListener(JTextField textField) {
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                //插入更新
                textField.getTopLevelAncestor().repaint();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                //删除更新
                textField.getTopLevelAncestor().repaint();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                //纯更新
                textField.getTopLevelAncestor().repaint();
            }
        });
    }


    /**
     * 选择颜色,传入选择前颜色与选择器下标和选择器值
     *
     * @param before 选择前颜色
     * @param index  选择器下标(用于区分rgba修改位置)
     * @param value  选择器实时值
     * @return 选择结果
     */
    private Color selectColor(Color before, int index, int value) {
        Color setColor = new Color(0, 0, 0, 0);
        switch (index) {
            case 0:
                setColor = new Color(
                        value, before.getGreen(), before.getBlue(), before.getAlpha()
                );
                break;
            case 1:
                setColor = new Color(
                        before.getRed(), value, before.getBlue(), before.getAlpha()
                );
                break;
            case 2:
                setColor = new Color(
                        before.getRed(), before.getGreen(), value, before.getAlpha()
                );
                break;
            case 3:
                setColor = new Color(
                        before.getRed(), before.getGreen(), before.getBlue(), value
                );
                break;
        }
        return setColor;
    }

    public void updateHexAndRgba() {
        colorHex.setText(GU.color2Hex(selectedColor));
        colorRgba.setText(String.valueOf(selectedColor.getRGB()));
    }

    public void updateSliders() {
        redSlider.slider.setValue(selectedColor.getRed());
        greenSlider.slider.setValue(selectedColor.getGreen());
        blueSlider.slider.setValue(selectedColor.getBlue());
        alphaSlider.slider.setValue(selectedColor.getAlpha());

        redSlider.valueField.setText(String.valueOf(selectedColor.getRed()));
        greenSlider.valueField.setText(String.valueOf(selectedColor.getGreen()));
        blueSlider.valueField.setText(String.valueOf(selectedColor.getBlue()));
        alphaSlider.valueField.setText(String.valueOf(selectedColor.getAlpha()));
    }
}
