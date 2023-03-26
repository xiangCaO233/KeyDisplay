package com.xiang.keyDisplay.template;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;

public class FileChooser extends Application {
    public static javafx.stage.FileChooser fileChooser;
    public static File file;
    public static Stage stage;

    @Override
    public void start(Stage stage) {
        stage.initStyle(StageStyle.UTILITY);
        fileChooser = new javafx.stage.FileChooser();
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

