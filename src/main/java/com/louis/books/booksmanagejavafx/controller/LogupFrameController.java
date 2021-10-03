package com.louis.books.booksmanagejavafx.controller;

import com.louis.books.booksmanagejavafx.MainApplication;
import com.louis.books.booksmanagejavafx.tools.SimpleTools;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @date : 2021/9/28
 */
public class LogupFrameController {

    private SimpleTools simpleTools = new SimpleTools();

    private Stage stage;

    @FXML
    private Label systemLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private TextField username;

    @FXML
    private Label passwordLabel;

    @FXML
    private TextField password;

    @FXML
    private Button loginButton;

    @FXML
    private Button resetButton;

    @FXML
    void login(ActionEvent event) {
        stage.close();
        boolean ok = simpleTools.isLogIn(username, password, "张三", "123456");
        if (ok) {
            new MainApplication().initMainFrame();
        }
    }

    @FXML
    void rest(ActionEvent event) {
        simpleTools.clearTextField(username, password);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    /**
     * initialize方法是初始化方法，可以使用该方法给界面初始化一些信息，比如说赋初值，设置图标，添加数据等。
     */
    public void initialize() {
        Labeled[] labeleds = {systemLabel, usernameLabel, passwordLabel, loginButton, resetButton};
        String[] imagePaths = {"src/main/resources/images/logo.png",
                "src/main/resources/images/userName.png",
                "src/main/resources/images/password.png",
                "src/main/resources/images/login.png",
                "src/main/resources/images/reset.png"};
        simpleTools.setLabeledImage(labeleds, imagePaths);
    }
}
