package com.louis.books.booksmanagejavafx.controller;


import com.louis.books.booksmanagejavafx.beans.BookTypeBean;
import com.louis.books.booksmanagejavafx.dao.BookDao;
import com.louis.books.booksmanagejavafx.dao.BookTypeDao;
import com.louis.books.booksmanagejavafx.tools.SimpleTools;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Labeled;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.List;

public class BookAddFrameController {

    private SimpleTools simpleTools = new SimpleTools();

    @FXML
    private TextField bookNameTextField;

    @FXML
    private TextField bookAuthorTextField;

    @FXML
    private RadioButton maleRadioButton;

    @FXML
    private RadioButton femaleRadioButton;

    @FXML
    private TextField bookPriceTextField;

    @FXML
    private ComboBox<?> bookTypeComboBox;

    @FXML
    private TextArea bookDescriptionTextArea;

    @FXML
    private Button addButton;

    @FXML
    private Button resetButton;

    @FXML
    void add(ActionEvent event) {
        String bookName = bookNameTextField.getText();
        String bookAuthor = bookAuthorTextField.getText();
        String sex = "";
        if (maleRadioButton.isSelected()) {
            sex = maleRadioButton.getText();
        } else if (femaleRadioButton.isSelected()) {
            sex = femaleRadioButton.getText();
        }
        String price = bookPriceTextField.getText();
        String type = (String) bookTypeComboBox.getSelectionModel().selectedItemProperty().getValue();
        String description = bookDescriptionTextArea.getText();

        String bookTypeSql = "select * from tb_booktype where btName='" + type + "'";
        List bookTypeList = new BookTypeDao().getRecordsDataBySql(bookTypeSql);
        BookTypeBean bookTypeBean = (BookTypeBean) bookTypeList.get(0);
        int bookTypeId = bookTypeBean.getBookTypeId();

        String sql =
                "insert into tb_book(bBookName, bAuthor, bSex, bPrice, bBookDescription, btId) values('" + bookName + "'," +
                        "'" + bookAuthor + "','" + sex + "'," + price + ",'" + description + "'," + bookTypeId + ");";

        boolean isOK = new BookDao().dataChange(sql);
        if (isOK) {
            resetContent();
            simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "添加成功！");
        } else {
            simpleTools.informationDialog(Alert.AlertType.ERROR, "提示", "错误", "添加失败！");
        }
    }

    private void resetContent() {
        simpleTools.clearTextField(bookNameTextField, bookAuthorTextField, bookPriceTextField, bookDescriptionTextArea);
        simpleTools.clearSelectedRadioButton(maleRadioButton, femaleRadioButton);
        simpleTools.clearSelectedComboBox(bookTypeComboBox);
    }

    @FXML
    void reset(ActionEvent event) {
        resetContent();
    }

    public void initialize() {
        simpleTools.setLabeledImage(new Labeled[]{addButton, resetButton},
                new String[]{"src/main/resources/images/add.png", "src/main/resources/images/reset.png"});

        String sql = "select * from tb_booktype";
        List bookTypeList = new BookTypeDao().getRecordsDataBySql(sql);
        String[] typeNames = new String[bookTypeList.size()];
        for (int i = 0; i < bookTypeList.size(); i++) {
            BookTypeBean bookTypeBean = (BookTypeBean) bookTypeList.get(i);
            typeNames[i] = bookTypeBean.getBookTypeName();
        }
        //初始化下拉列表框的选项
        simpleTools.addComboBoxItems(bookTypeComboBox, typeNames);
    }

}

