package com.xiang.keyDisplay.main;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseListener;
import com.xiang.keyDisplay.menus.*;
import com.xiang.keyDisplay.others.*;
import com.xiang.keyDisplay.template.FileChooser;
import com.xiang.keyDisplay.template.frameTemplate.*;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    //默认配置
    public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    public static final GraphicsDevice SCREEN_DEVICE = getDevice();

    private static GraphicsDevice getDevice() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    }

    public static final Dimension SCREEN_DEVICE_SIZE = new Dimension(
            SCREEN_DEVICE.getDisplayMode().getWidth(),
            SCREEN_DEVICE.getDisplayMode().getHeight()
    );
    public static final double SCALE_X = SCREEN_DEVICE.getDefaultConfiguration().getDefaultTransform().getScaleX();
    public static final double SCALE_Y = SCREEN_DEVICE.getDefaultConfiguration().getDefaultTransform().getScaleY();
    public static final String[] DEFAULT_KEYS = new String[]{"A", "S", "D", "SPACE", "J", "K", "L"};
    public static final String[] DEFAULT_MOUSE_BUTTONS = new String[]{"LMB" , "RMB"};
    public static final Dimension DEFAULT_SIZE = new Dimension(60, 80);
    public static final Color DEFAULT_BORDER_COLOR = new Color(220, 220, 220);
    public static final Color DEFAULT_BG_COLOR = new Color(24, 15, 36, 220);
    public static final Color DEFAULT_PRESS_COLOR = new Color(88, 108, 140, 180);
    //获取屏幕硬件启动时刷新率
    public static final int DEFAULT_FPS = SCREEN_DEVICE.getDisplayMode().getRefreshRate();
    public static final AreaColor DEFAULT_AREA_COLOR = AreaColor.CYAN;

    public static int fps;
    public static int speedValue = 7;
    public static final Font DEFAULT_FONT = initFont();

    private static Font initFont() {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, Main.class.getResourceAsStream("FZSJ-LXQWTJW.TTF"));
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    //所有按键窗体: keycode-frameTemplate.KeyFrame
    public static LinkedHashMap<Integer, KeyFrame> keyFrames;
    //所有鼠标按键窗体: mouseButton-frameTemplate.MouseFrame
    public static LinkedHashMap<Integer, MouseFrame> mouseFrames;
    //总计数窗体
    public static TotalCountFrame totalCountFrame;
    //菜单窗体
    public static ArrayList<MenuTemplate> allMenus;
    public static MainMenu mainMenu;
    public static FastSetMenu fastSetMenu;
    //删除按键菜单
    public static DeleteKeyMenu deleteKeyMenu;
    //保存菜单
    public static SaveMenu saveMenu;
    //加载菜单
    public static LoadMenu loadMenu;
    //添加按键菜单
    public static AddKeyMenu addKeyMenu;
    //高级设置菜单
    public static AdvanceSettingsMenu advanceSettingsMenu;
    //快速设置界面
    public static FastSetFrame fastSetFrame;
    //表格窗体
    public static ChartFrame chartFrame;


    //刷新线程
    static RefreshThread refreshThread;
    //计时器
    static Timer kpsAndCpsTimer;
    static Timer chartTimer;
    //全局监听器
    static NativeKeyListener globalKeyListener;
    static NativeMouseListener globalMouseListener;
    //完成标志
    private static volatile boolean isInitDone;
    private static volatile boolean isThreadClosed;
    //存档路径
    public static File savesPath;
    //最近存档文件
    public static File latestFile;
    //是否保存最大kps
    public static boolean saveMaxKps;

    public Main() {
        if (!latestFile.exists()) {
            try {
                latestFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            start();
        } else {
            try {
                FileInputStream fis = new FileInputStream(latestFile);
                if (fis.available() == 0)
                    start();
                else
                    start(JSON.parseObject(fis, StandardCharsets.UTF_8));
                fis.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 启动程序(通过默认配置)
     */
    void start() {
        fps = DEFAULT_FPS;
        initKeyFrames(DEFAULT_KEYS);
        initMouseFrames(DEFAULT_MOUSE_BUTTONS);
        initMenus();
        initAllThreads();
    }

    /**
     * 启动程序(通过配置文件)
     *
     * @param config
     */
    static void start(JSONObject config) {
        fps = config.getIntValue("fps");
        //初始化按键列表
        keyFrames = new LinkedHashMap<>();
        JSONArray keysConfig = config.getJSONArray("按键");
        for (int i = 0; i < keysConfig.size(); i++) {
            JSONObject keyConfig = keysConfig.getJSONObject(i);
            KeyFrame keyFrame = new KeyFrame(keyConfig);
            keyFrames.put(VKKeys.KeySearch(keyFrame.keyName), keyFrame);
            keyFrame.setVisible(true);
            keyFrame.setLocation(JsonUtil.json2Rec(keyConfig.getJSONArray("bounds")).getLocation());
        }
        //初始化按键列表
        mouseFrames = new LinkedHashMap<>();
        JSONArray mouseKeysConfig = config.getJSONArray("鼠标");
        for (int i = 0; i < mouseKeysConfig.size(); i++) {
            JSONObject mouseKeyConfig = mouseKeysConfig.getJSONObject(i);
            MouseFrame mouseFrame = new MouseFrame(mouseKeyConfig);
            mouseFrames.put(VKKeys.KeySearch(mouseFrame.mouseKeyName), mouseFrame);
            mouseFrame.setVisible(true);
            mouseFrame.setLocation(JsonUtil.json2Rec(mouseKeyConfig.getJSONArray("bounds")).getLocation());
        }
        JSONObject chartConfig = config.getJSONObject("表格界面");
        JSONObject countConfig = config.getJSONObject("计数界面");

        //计数面板
        totalCountFrame = new TotalCountFrame(countConfig);
        totalCountFrame.setVisible(true);
        totalCountFrame.setLocation(JsonUtil.json2Rec(countConfig.getJSONArray("bounds")).getLocation());
        totalCountFrame.repaint();
        //表格面板
        chartFrame = new ChartFrame(chartConfig);
        chartFrame.setVisible(true);
        chartFrame.setLocation(JsonUtil.json2Rec(chartConfig.getJSONArray("bounds")).getLocation());
        chartFrame.repaint();

        initMenus();
        initAllThreads();

    }

    /**
     * 加载存档
     */
    public static void loadFromConfig(JSONObject config) {
        stopAllThread();
        disposeAllFrame();
        start(config);
    }

    /**
     * 初始化界面(通过数组)
     *
     * @param keyStrs
     */
    private static void initKeyFrames(String[] keyStrs) {
        isInitDone = false;
        //初始化默认按键列表
        keyFrames = new LinkedHashMap<>();
        for (int i = 0; i < keyStrs.length; i++) {
            KeyFrame keyFrame = new KeyFrame(
                    keyStrs[i]
            );
            keyFrames.put(
                    VKKeys.KeySearch(keyStrs[i]),
                    keyFrame
            );
            //整体居中
            keyFrame.setLocation(
                    (SCREEN_SIZE.width - (keyStrs.length * DEFAULT_SIZE.width)) / 2 + (i) * DEFAULT_SIZE.width,
                    (SCREEN_SIZE.height - DEFAULT_SIZE.height) / 2
            );
            keyFrame.setVisible(true);
        }
        //初始化总计数frame
        totalCountFrame = new TotalCountFrame(DEFAULT_BORDER_COLOR, DEFAULT_BG_COLOR);
        /*totalCountFrame.setLocation(
                (SCREEN_SIZE.width - totalCountFrame.getWidth()) / 2,
                (SCREEN_SIZE.height - DEFAULT_SIZE.height) / 2 + DEFAULT_SIZE.height
        );*/
        totalCountFrame.setVisible(true);
        //初始化表格frame
        chartFrame = new ChartFrame(DEFAULT_BORDER_COLOR, DEFAULT_BG_COLOR, DEFAULT_AREA_COLOR);
        /*chartFrame.setLocation(
                (SCREEN_SIZE.width - chartFrame.getWidth()) / 2,
                (SCREEN_SIZE.height - DEFAULT_SIZE.height) / 2 - chartFrame.getHeight()
        );*/
        chartFrame.setVisible(true);

        /*
        mainMenu.childMenus.add(fastSetMenu);*/
        isInitDone = true;
    }
    private static void initMouseFrames(String[] mouseButtons){
        isInitDone = false;
        mouseFrames = new LinkedHashMap<>();
        int minX = SCREEN_SIZE.width,minY = SCREEN_SIZE.height , maxX = 0 , maxY = 0 , maxsWidth = DEFAULT_SIZE.width;
        if (keyFrames.size() > 0){
            Collection<KeyFrame> keys = keyFrames.values();
            for (KeyFrame key : keys){
                Point keyLoc = key.getLocationOnScreen();
                if (keyLoc.x < minX)
                    minX = keyLoc.x;
                if (keyLoc.y < minY)
                    minY = keyLoc.y;
                if (keyLoc.x > maxX){
                    maxX = keyLoc.x;
                    maxsWidth = key.getWidth();
                }

                if (keyLoc.y > maxY)
                    maxY = keyLoc.y;

            }
        }
        for (String mouseStr : mouseButtons){
            MouseFrame mouseFrame = new MouseFrame(mouseStr);
            mouseFrames.put(
                    VKKeys.KeySearch(mouseStr) , mouseFrame
            );
            mouseFrame.setVisible(true);
        }
        MouseFrame mouseLeft = mouseFrames.get(1);
        mouseLeft.setLocation(
                minX - mouseLeft.getWidth() ,
                minY
        );
        MouseFrame mouseRight = mouseFrames.get(2);
        mouseRight.setLocation(
                maxX + maxsWidth ,
                minY
        );

        isInitDone = true;
    }

    /**
     * 初始化全部菜单
     */
    private static void initMenus() {
        //初始化菜单
        allMenus = new ArrayList<>();
        mainMenu = new MainMenu();
        fastSetMenu = new FastSetMenu();
        deleteKeyMenu = new DeleteKeyMenu();
        addKeyMenu = new AddKeyMenu();
        saveMenu = new SaveMenu();
        loadMenu = new LoadMenu();
        advanceSettingsMenu = new AdvanceSettingsMenu();

        allMenus.add(mainMenu);
        allMenus.add(fastSetMenu);
        allMenus.add(deleteKeyMenu);
        allMenus.add(addKeyMenu);
        allMenus.add(saveMenu);
        allMenus.add(loadMenu);
        allMenus.add(advanceSettingsMenu);

        mainMenu.addChild(fastSetMenu);
        mainMenu.addChild(deleteKeyMenu);
        mainMenu.addChild(addKeyMenu);
        mainMenu.addChild(saveMenu);
        mainMenu.addChild(loadMenu);
        mainMenu.addChild(advanceSettingsMenu);

    }

    /**
     * 初始化全部线程,监听器和计时器
     */
    private static void initAllThreads() {
        isThreadClosed = false;
        //初始化刷新线程
        refreshThread = new RefreshThread();
        refreshThread.start();
        //初始化表格刷新定时器
        chartTimer = new Timer();
        chartTimer.schedule(
                new ChartRefreshTask(), 0, 1000 / Main.fps
        );
        //初始化kps处理定时器
        kpsAndCpsTimer = new Timer();
        kpsAndCpsTimer.schedule(
                new KpsAndCpsTask(), 0, 50
        );
        globalKeyListener = new GlobalKeyListener();
        GlobalScreen.addNativeKeyListener(globalKeyListener);
        globalMouseListener = new GlobalMouseListener();
        GlobalScreen.addNativeMouseListener(globalMouseListener);

        if (FileChooser.stage == null) {
            FileChooser.open();
        }
    }

    /**
     * 退出程序
     */
    public static void stop() {
        saveAsLatest();
        GlobalScreen.removeNativeKeyListener(globalKeyListener);
        GlobalScreen.removeNativeMouseListener(globalMouseListener);
        try {
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException e) {
            throw new RuntimeException(e);
        }
        stopAllThread();

        disposeAllFrame();
        System.exit(0);
    }

    /**
     * 释放全部界面
     */
    private static void disposeAllFrame() {
        if (!isThreadClosed) {
            Thread.onSpinWait();
        }
        Collection<KeyFrame> allKey = keyFrames.values();
        for (KeyFrame key : allKey) {
            key.dispose();
        }
        keyFrames.clear();
        keyFrames = null;
        for (MenuTemplate menu : allMenus) {
            menu.dispose();
        }
        Collection<MouseFrame> allMouses = mouseFrames.values();
        for (MouseFrame mouseFrame : allMouses) {
            mouseFrame.dispose();
        }
        mouseFrames.clear();
        mouseFrames = null;

        totalCountFrame.dispose();
        chartFrame.dispose();
    }

    /**
     * 终止所有线程和计时器
     */
    private static void stopAllThread() {
        isThreadClosed = false;
        refreshThread.interrupt();
        refreshThread.stop();
        chartTimer.cancel();
        kpsAndCpsTimer.cancel();
        GlobalScreen.removeNativeKeyListener(globalKeyListener);
        GlobalScreen.removeNativeMouseListener(globalMouseListener);

        globalKeyListener = null;
        globalMouseListener = null;

        isThreadClosed = true;
        FileChooser.close();
    }

    /**
     * 快速设置keyFrame(清除所有面板,快速创建)
     *
     * @param keycodes
     */
    public static void setKeys(int[] keycodes) {
        String[] keys = new String[keycodes.length];
        for (int i = 0; i < keys.length; i++) {
            keys[i] = VKKeys.data.get(keycodes[i]);
        }
        stopAllThread();
        disposeAllFrame();


        initKeyFrames(keys);
        initMouseFrames(DEFAULT_MOUSE_BUTTONS);
        while (!isInitDone) {
            Thread.onSpinWait();
        }
        initAllThreads();
    }

    /**
     * 添加按钮行为
     *
     * @param keyCode 要添加的按钮keyCode
     */
    public static void addKey(int keyCode) {

        KeyFrame keyFrame = new KeyFrame(VKKeys.data.get(keyCode));
        //取最后一个按键位置
        Iterator<Integer> itr = keyFrames.keySet().iterator();
        int lastKey = 0;
        while (itr.hasNext()) {
            lastKey = itr.next();
        }
        Point lastLocation = keyFrames.get(lastKey).getLocationOnScreen();

        keyFrame.setLocation(
                lastLocation.x + keyFrames.get(lastKey).getWidth(),
                lastLocation.y
        );
        keyFrames.put(
                keyCode,
                keyFrame
        );
        keyFrame.setVisible(true);

        totalCountFrame.updateSize();
        chartFrame.updateSize();
    }

    /**
     * 删除按钮行为
     *
     * @param keyName 要删除的按钮名
     */
    public static void deleteKey(String keyName) {
        int keyCode = VKKeys.KeySearch(keyName);
        KeyFrame key = keyFrames.get(keyCode);
        keyFrames.remove(keyCode);
        key.dispose();

        totalCountFrame.updateSize();
        chartFrame.updateSize();
    }

    /**
     * 保存为文件
     */
    public static void saveToFile(File file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            JSON.writeTo(
                    fos, createConfigJson(), JSONWriter.Feature.LargeObject
            );
            fos.flush();
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 保存为最新配置文件
     */
    public static void saveAsLatest() {
        saveToFile(latestFile);
    }

    /**
     * 创建实时配置文件
     *
     * @return
     */
    public static JSONObject createConfigJson() {
        JSONObject latestConfig = new JSONObject();
        //表格面板配置
        JSONObject chartFrameConfig = chartFrame.toConfig();
        //计数面板配置
        JSONObject countFrameConfig = totalCountFrame.toConfig();
        //按键配置
        JSONArray keyFramesConfig = getAllKeysConfigs();
        //鼠标按键配置
        JSONArray mouseFramesConfig = getAllMouseKeysConfig();

        long time = System.currentTimeMillis();
        latestConfig.put("time", time);
        latestConfig.put("自动保存时间", new Date(time).toString());
        latestConfig.put("fps", fps);

        latestConfig.put("计数界面", countFrameConfig);
        latestConfig.put("表格界面", chartFrameConfig);
        latestConfig.put("按键", keyFramesConfig);
        latestConfig.put("鼠标", mouseFramesConfig);
        return latestConfig;
    }

    /**
     * 获取全部按键配置文件
     *
     * @return
     */
    public static JSONArray getAllKeysConfigs() {
        JSONArray keyFramesConfig = new JSONArray();
        //按键配置
        Set<Integer> codeSet = keyFrames.keySet();
        for (Integer code : codeSet) {
            JSONObject keyConfig = keyFrames.get(code).toConfig();
            keyFramesConfig.add(keyConfig);
        }
        return keyFramesConfig;
    }
    public static JSONArray getAllMouseKeysConfig() {
        JSONArray mouseFramesConfig = new JSONArray();
        //按键配置
        Set<Integer> codeSet = mouseFrames.keySet();
        for (Integer code : codeSet) {
            JSONObject mouseKeyConfig = mouseFrames.get(code).toConfig();
            mouseFramesConfig.add(mouseKeyConfig);
        }
        return mouseFramesConfig;
    }

    public static void main(String[] args) {
        String fileStr = Main.class.getResource("").toExternalForm();
        File filePath = new File(
                fileStr.substring(
                        fileStr.indexOf('/') + 1,
                        (fileStr.indexOf('!') == -1 ? fileStr.length() : fileStr.indexOf('!')))
        );
        //存档路径
        savesPath = new File(filePath.getParentFile().getAbsoluteFile() + "\\saves");
        if (!savesPath.exists()) {
            savesPath.mkdirs();
        }
        //最近存档文件
        latestFile = new File(savesPath + "\\latest.json");
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            throw new RuntimeException(e);
        }
        new Main();


    }

}
