package com.louis.books.booksmanagejavafx.controller;

import com.louis.books.booksmanagejavafx.MainApplication;
import com.louis.books.booksmanagejavafx.tools.SimpleTools;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class MainFrameController {

    private SimpleTools simpleTools = new SimpleTools();

    @FXML
    private AnchorPane mainFrameAnchorPane;

    @FXML
    private MenuItem bookTypeAddMenuItem;

    @FXML
    private MenuItem bookTypeManageMenuItem;

    @FXML
    private MenuItem bookAddMenuItem;

    @FXML
    private MenuItem bookManageMenuItem;

    @FXML
    private MenuItem exitMenuItem;

    @FXML
    private MenuItem aboutSoftMenuItem;

    @FXML
    private ImageView mainBookManageImageView;

    @FXML
    void aboutSoft(ActionEvent event) {
        new MainApplication().initAboutSoftFrame();
    }

    @FXML
    void addBookType(ActionEvent event) {
        AnchorPane pane = new MainApplication().initAddBookTypeFrame();
        mainFrameAnchorPane.getChildren().clear();
        mainFrameAnchorPane.getChildren().add(pane);
    }

    @FXML
    void bookAdd(ActionEvent event) {
        AnchorPane pane = new MainApplication().initBookAddFrame();
        mainFrameAnchorPane.getChildren().clear();
        mainFrameAnchorPane.getChildren().add(pane);
    }

    @FXML
    void bookManage(ActionEvent event) {
        AnchorPane pane = new MainApplication().initBookManageFrame();
        mainFrameAnchorPane.getChildren().clear();
        mainFrameAnchorPane.getChildren().add(pane);
    }

    @FXML
    void bookTypeManage(ActionEvent event) {
        AnchorPane pane = new MainApplication().initBookTypeManageFrame();
        mainFrameAnchorPane.getChildren().clear();
        mainFrameAnchorPane.getChildren().add(pane);
    }

    @FXML
    void exitMenuItem(ActionEvent event) {
        System.exit(0);
    }

    public void initialize() {
        simpleTools.setMenuItemImage(
                new MenuItem[]{bookTypeAddMenuItem, bookTypeManageMenuItem, bookAddMenuItem, bookManageMenuItem, exitMenuItem, aboutSoftMenuItem},
                new String[]{"src/main/resources/images/add.png", "src/main/resources/images/edit.png", "src/main/resources/images/add.png",
                        "src/main/resources/images/edit.png", "src/main/resources/images/exit.png", "src/main/resources/images/about.png"});
        mainBookManageImageView.setImage(new Image("file:src/main/resources/images/bookmanagesystem.png"));
    }

}
