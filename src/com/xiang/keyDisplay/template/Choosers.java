package com.xiang.keyDisplay.template;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;

public class Choosers extends Application {
    public static FileChooser fileChooser;
    public static ColorPicker picker;
    public static File file;
    public static Color color;
    public static Stage stage;
    public static VBox box;

    @Override
    public void start(Stage stage) {
        stage.initStyle(StageStyle.UTILITY);
        box = new VBox();
        Scene scene = new Scene(box, Color.TRANSPARENT);
        stage.setScene(scene);
        fileChooser = new javafx.stage.FileChooser();
        picker = new ColorPicker();
        box.getChildren().add(picker);
        fileChooser.getExtensionFilters().add(
                new javafx.stage.FileChooser.ExtensionFilter(
                        "Json配置文件", ".json"
                )
        );
        this.stage = stage;

    }

    public static void sendDir(FileChooseCallBack fileChooseCallBack) {

        fileChooser.setInitialFileName("save.json");
        fileChooser.setTitle("另存为...");
        file = fileChooser.showSaveDialog(stage);
        fileChooseCallBack.callback(file);
    }

    public static void sendFile(FileChooseCallBack fileChooseCallBack) {
        fileChooser.setTitle("打开存档...");
        file = fileChooser.showOpenDialog(stage);
        fileChooseCallBack.callback(file);
    }
    public static void open() {
        launch();
    }

    public static void close() {
        Platform.exit();
    }

    public interface FileChooseCallBack {
        void callback(File file);
    }
}

