package com.louis.books.booksmanagejavafx;

import com.louis.books.booksmanagejavafx.controller.LogupFrameController;
import com.louis.books.booksmanagejavafx.controller.SoftInformationFrameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        this.primaryStage.setTitle("图书管理系统");
        initLogupFrame();
    }

    private void initLogupFrame() {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("LogupFrame.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle("登陆");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);

            LogupFrameController controller = loader.getController();
            controller.setStage(primaryStage);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initMainFrame() {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("mainFrame.fxml"));
        try {
            Parent root = loader.load();
            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("图书管理系统主界面");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            mainFrameStage.initOwner(primaryStage);

            Scene scene = new Scene(root);
            mainFrameStage.setScene(scene);
            mainFrameStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AnchorPane initAddBookTypeFrame() {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("bookTypeAddFrame.fxml"));
        try {
            AnchorPane root = loader.load();
            return root;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public AnchorPane initBookTypeManageFrame() {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("bookTypeManageFrame.fxml"));
        try {
            AnchorPane root = loader.load();
            return root;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public AnchorPane initBookAddFrame() {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("bookAddFrame.fxml"));
        try {
            AnchorPane root = loader.load();
            return root;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public AnchorPane initBookManageFrame() {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("bookManageFrame.fxml"));
        try {
            AnchorPane root = loader.load();
            return root;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Scene initAboutSoftFrame() {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("softInformationFrame.fxml"));
        try {
            AnchorPane page = loader.load();
            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("关于软件");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            mainFrameStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            mainFrameStage.setScene(scene);

            SoftInformationFrameController controller = loader.getController();
            controller.setDialogStage(mainFrameStage);
            mainFrameStage.showAndWait();
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        launch();
    }
}