package com.louis.books.booksmanagejavafx.controller;

/**
 * @date : 2021/9/28
 */

import com.louis.books.booksmanagejavafx.dao.BookTypeDao;
import com.louis.books.booksmanagejavafx.tools.SimpleTools;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BookTypeAddController {

    private SimpleTools simpleTools = new SimpleTools();

    @FXML
    private TextField bookTypeName;

    @FXML
    private TextArea bookTypeDescription;

    @FXML
    private Button addButton;

    @FXML
    private Button resetButton;

    @FXML
    void add(ActionEvent event) {
        String bookTypeNameText = bookTypeName.getText();
        String bookTypeDescriptionText = bookTypeDescription.getText();
        String sql = "insert into tb_booktype(btName,btDescription) value('" + bookTypeNameText + "','" + bookTypeDescriptionText + "');";
        boolean isOK = new BookTypeDao().dataChange(sql);
        if (isOK) {
            simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "添加成功！");
            simpleTools.clearTextField(bookTypeName, bookTypeDescription);
        } else {
            simpleTools.informationDialog(Alert.AlertType.ERROR, "提示", "错误", "添加失败！");
        }
    }

    @FXML
    void reset(ActionEvent event) {
        simpleTools.clearTextField(bookTypeName, bookTypeDescription);
    }

    public void initialize() {
        simpleTools.setLabeledImage(new Labeled[]{addButton, resetButton},
                new String[]{"src/main/resources/images/add.png", "src/main/resources/images/reset.png"});
    }
}

