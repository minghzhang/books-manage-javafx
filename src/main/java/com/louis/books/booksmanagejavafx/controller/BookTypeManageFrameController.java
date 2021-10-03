package com.louis.books.booksmanagejavafx.controller;

import com.louis.books.booksmanagejavafx.beans.BookTypeBeanTableData;
import com.louis.books.booksmanagejavafx.dao.BookTypeDao;
import com.louis.books.booksmanagejavafx.tools.SimpleTools;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * @date : 2021/9/29
 */
public class BookTypeManageFrameController {

    private SimpleTools simpleTools = new SimpleTools();

    private BookTypeDao bookTypeDao = new BookTypeDao();

    @FXML
    private TextField bookTypeNameTextFieldForQuery;

    @FXML
    private Button queryButton;

    @FXML
    private TableView<BookTypeBeanTableData> bookTypeManageTableView;

    @FXML
    private TableColumn<BookTypeBeanTableData, String> idTableColumn;

    @FXML
    private TableColumn<BookTypeBeanTableData, String> bookTypeNameColumn;

    @FXML
    private TableColumn<BookTypeBeanTableData, String> bookTypeDescriptionTableColumn;

    @FXML
    private VBox formBox;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField bookTypeNameTextFieldForEdit;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    void delete(ActionEvent event) {
        String id = idTextField.getText();
        String sql = "delete from tb_booktype where btId='" + id + "';";
        boolean confirmed = simpleTools.informationDialog(Alert.AlertType.WARNING, "提示", "告警", "是否删除");
        if (confirmed) {
            boolean ok = bookTypeDao.dataChange(sql);
            if (!ok) {
                simpleTools.informationDialog(Alert.AlertType.ERROR, "提示", "信息", "删除失败！");
                return;
            }
            initialize();
            simpleTools.clearTextField(idTextField, bookTypeNameTextFieldForEdit, descriptionTextArea);
            simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "删除成功！");
        }
    }

    @FXML
    void edit(ActionEvent event) {
        String id = idTextField.getText();
        String name = bookTypeNameTextFieldForEdit.getText();
        String description = descriptionTextArea.getText();
        String sql = "update tb_booktype set btName='" + name + "', btDescription='" + description + "' where btId='" + id + "';";
        boolean ok = bookTypeDao.dataChange(sql);
        if (ok) {
            initialize();
            simpleTools.clearTextField(idTextField, bookTypeNameTextFieldForEdit, descriptionTextArea);
            simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "修改成功！");
        } else {
            simpleTools.informationDialog(Alert.AlertType.ERROR, "提示", "信息", "修改失败！");
        }
    }

    @FXML
    void query(ActionEvent event) {
        String bookTypeName = bookTypeNameTextFieldForQuery.getText();
        String sql = "";
        if (simpleTools.isEmpty(bookTypeName)) {
            sql = "select * from tb_booktype";
        } else {
            sql = "select * from tb_booktype where btName like '%" + bookTypeName + "%'";
        }
        simpleTools.setBookTypeTableViewData(bookTypeManageTableView, simpleTools.getBookTypeTableViewData(sql),
                idTableColumn, bookTypeNameColumn, bookTypeDescriptionTableColumn);
    }

    public void showBookTypeDetail(BookTypeBeanTableData bookTypeBeanTableData) {
        if (bookTypeBeanTableData == null) {
            return;
        }
        idTextField.setText(bookTypeBeanTableData.getBookTypeId());
        bookTypeNameTextFieldForEdit.setText(bookTypeBeanTableData.getBookTypeName());
        descriptionTextArea.setText(bookTypeBeanTableData.getBookTypeDescription());
    }

    public void initialize() {
        simpleTools.setLabeledImage(new Labeled[]{editButton, deleteButton},
                new String[]{"src/main/resources/images/edit.png", "src/main/resources/images/delete.png"});
        idTextField.setEditable(false);
        String sql = "select * from tb_booktype";
        simpleTools.setBookTypeTableViewData(bookTypeManageTableView, simpleTools.getBookTypeTableViewData(sql),
                idTableColumn, bookTypeNameColumn, bookTypeDescriptionTableColumn);

        bookTypeManageTableView.getSelectionModel()
                .selectedItemProperty()
                .addListener(((observable, oldValue, newValue) -> showBookTypeDetail(newValue)));
    }

}
