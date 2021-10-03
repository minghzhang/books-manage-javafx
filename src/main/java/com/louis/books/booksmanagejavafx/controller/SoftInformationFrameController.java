package com.louis.books.booksmanagejavafx.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @date : 2021/10/3
 */
public class SoftInformationFrameController {

    private Stage dialogStage;

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private ImageView imageView;

    @FXML
    private Hyperlink hyperlink;

    @FXML
    private Button closeButton;

    public void initialize() {
        hyperlink.setText("相关Github链接");
        imageView.setImage(new Image("file:src/main/resources/images/panda.png"));
    }

    @FXML
    void closeButtonEvent(ActionEvent event) {
        dialogStage.close();
    }

    @FXML
    void hyperlinkEvent(ActionEvent event) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://github.com/lck100/JavaExerciseProject/tree/master/1.%E7%AE%A1%E5%AE%B6%E5%A9%86%E7%B3%BB%E7%BB%9F/%E7%AE%A1%E5%AE%B6%E5%A9%86%E7%B3%BB%E7%BB%9F%EF%BC%88JavaFX%E7%89%88%EF%BC%89"));
    }

}
