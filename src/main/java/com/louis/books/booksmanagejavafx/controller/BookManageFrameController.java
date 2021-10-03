package com.louis.books.booksmanagejavafx.controller;

import com.louis.books.booksmanagejavafx.beans.BookBeanTableData;
import com.louis.books.booksmanagejavafx.beans.BookTypeBean;
import com.louis.books.booksmanagejavafx.dao.BookDao;
import com.louis.books.booksmanagejavafx.dao.BookTypeDao;
import com.louis.books.booksmanagejavafx.tools.SimpleTools;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Labeled;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.List;

/**
 * @date : 2021/10/2
 */
public class BookManageFrameController {

    private SimpleTools simpleTools = new SimpleTools();

    private BookDao bookDao = new BookDao();

    @FXML
    private TextField bookNameTextFieldForQuery;

    @FXML
    private TextField bookAuthorTextFieldForQuery;

    @FXML
    private ComboBox<?> bookTypeComboBoxForQuery;

    @FXML
    private Button queryButton;

    @FXML
    private Button resetButtonForQuery;

    @FXML
    private TableView<BookBeanTableData> bookManageTableView;

    @FXML
    private TableColumn<BookBeanTableData, String> idTableColumn;

    @FXML
    private TableColumn<BookBeanTableData, String> bookNameTableColumn;

    @FXML
    private TableColumn<BookBeanTableData, String> bookAuthorTableColumn;

    @FXML
    private TableColumn<BookBeanTableData, String> authorSexTableColumn;

    @FXML
    private TableColumn<BookBeanTableData, String> bookPriceTableColumn;

    @FXML
    private TableColumn<BookBeanTableData, String> bookDescriptionTableColumn;

    @FXML
    private TableColumn<BookBeanTableData, String> bookTypeTableColumn;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField bookNameTextFieldForEdit;

    @FXML
    private RadioButton maleRadioButton;

    @FXML
    private RadioButton femaleRadioButton;

    @FXML
    private TextField priceTextField;

    @FXML
    private TextField bookAuthorTextFieldForEdit;

    @FXML
    private ComboBox<?> bookTypeComboBoxForEdit;

    @FXML
    private TextArea bookDescriptionTextArea;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button resetButtonForEdit;

    @FXML
    void delete(ActionEvent event) {
        String id = idTextField.getText();
        String deleteSql = "delete from tb_book where bId='" + id + "';";
        boolean isOK = bookDao.dataChange(deleteSql);
        if (isOK) {
            initialize();
            resetEdit(null);
            simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "删除成功！");
        } else {
            simpleTools.informationDialog(Alert.AlertType.ERROR, "提示", "错误", "删除失败！");
        }
    }

    @FXML
    void edit(ActionEvent event) {
        String id = idTextField.getText();
        String bookName = bookNameTextFieldForEdit.getText();
        String authorSex = "";
        if (maleRadioButton.isSelected()) {
            authorSex = maleRadioButton.getText();
        } else if (femaleRadioButton.isSelected()) {
            authorSex = femaleRadioButton.getText();
        }
        String price = priceTextField.getText();
        String bookAuthor = bookAuthorTextFieldForEdit.getText();
        String bookType = (String) bookTypeComboBoxForEdit.getSelectionModel().selectedItemProperty().getValue();
        String description = bookDescriptionTextArea.getText();

        String bookTypeSql = "select * from tb_booktype where btName='" + bookType + "';";
        List bookTypeList = new BookTypeDao().getRecordsDataBySql(bookTypeSql);
        BookTypeBean bookTypeBean = (BookTypeBean) bookTypeList.get(0);
        int bookTypeId = bookTypeBean.getBookTypeId();
// 组装修改SQL语句
        String alterSQL =
                "update tb_book set bBookName='" + bookName + "',bAuthor='" + bookAuthor + "',bSex='" + authorSex +
                        "',bPrice=" + price + ",bBookDescription='" + description + "',btId=" + bookTypeId + " where " +
                        "bId=" + id + ";";

        boolean isOK = bookDao.dataChange(alterSQL);
        if (isOK) {
            initialize();
            resetEdit(null);
            simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "修改成功！");
        } else {
            simpleTools.informationDialog(Alert.AlertType.ERROR, "提示", "错误", "修改失败！");
        }
    }

    @FXML
    void query(ActionEvent event) {
        // 查询SQL语句
        String sql = "select bId,bBookName,bAuthor,bSex,bPrice,bBookDescription,btName from tb_book,tb_booktype where" +
                " tb_book.btId=tb_booktype.btId ";
        // 判断用户是否输入图书名称，模糊查询
        if (!simpleTools.isEmpty(bookNameTextFieldForQuery.getText())) {
            sql += " and bBookName like '%" + bookNameTextFieldForQuery.getText() + "%'";
        }
        // 判断用户是否输入作者名称，模糊查询
        if (!simpleTools.isEmpty(bookAuthorTextFieldForQuery.getText())) {
            sql += " and bAuthor like '%" + bookAuthorTextFieldForQuery.getText() + "%'";
        }
        // 判断用户是否选择图书类别
        String booktype = (String) bookTypeComboBoxForQuery.getSelectionModel().selectedItemProperty().getValue();
        if (!simpleTools.isEmpty(booktype)) {
            sql += " and btName='" + booktype + "';";
        }
        // 通过SQL语句查询到的数据重新填充表格，刷新表格显示的数据
        simpleTools.setBookTableViewData(bookManageTableView, simpleTools.getBookTableViewData(sql),
                idTableColumn, bookNameTableColumn, bookAuthorTableColumn, authorSexTableColumn, bookPriceTableColumn, bookDescriptionTableColumn, bookTypeTableColumn);
    }

    @FXML
    void resetEdit(ActionEvent event) {
        simpleTools.clearTextField(bookNameTextFieldForEdit, priceTextField, bookAuthorTextFieldForEdit, bookDescriptionTextArea);
        simpleTools.clearSelectedComboBox(bookTypeComboBoxForEdit);
        simpleTools.clearSelectedRadioButton(maleRadioButton, femaleRadioButton);
    }

    @FXML
    void resetQuery(ActionEvent event) {
        simpleTools.clearTextField(bookNameTextFieldForQuery, bookAuthorTextFieldForQuery);
        simpleTools.clearSelectedComboBox(bookTypeComboBoxForQuery);
    }


    public void initialize() {
        simpleTools.setLabeledImage(new Labeled[]{editButton, deleteButton, resetButtonForEdit}, new String[]{
                "src/main/resources/images/edit.png", "src/main/resources/images/delete.png", "src/main/resources/images/reset.png"
        });

        idTextField.setDisable(true);
        String sql = "select bId,bBookName,bAuthor,bSex,bPrice,bBookDescription,btName from tb_book,tb_booktype where tb_book.btId=tb_booktype.btId;";

        simpleTools.setBookTableViewData(bookManageTableView, simpleTools.getBookTableViewData(sql),
                idTableColumn,
                bookNameTableColumn,
                bookAuthorTableColumn,
                authorSexTableColumn,
                bookPriceTableColumn,
                bookDescriptionTableColumn,
                bookTypeTableColumn);

        String bookTypeSql = "select * from tb_booktype;";
        List bookTypeList = new BookTypeDao().getRecordsDataBySql(bookTypeSql);
        String[] typeNames = new String[bookTypeList.size()];
        for (int i = 0; i < bookTypeList.size(); i++) {
            BookTypeBean bookTypeBean = (BookTypeBean) bookTypeList.get(i);
            typeNames[i] = bookTypeBean.getBookTypeName();
        }

        simpleTools.addComboBoxItems(bookTypeComboBoxForQuery, typeNames);
        simpleTools.addComboBoxItems(bookTypeComboBoxForEdit, typeNames);

        //为表格注册事件监听器
        bookManageTableView.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> showBookDetails(newValue));
    }

    private void showBookDetails(BookBeanTableData bookBeanTableData) {
        if (bookManageTableView.getSelectionModel().getSelectedIndex() < 0) {
            return;
        }
        idTextField.setText(bookBeanTableData.getBookId());
        bookNameTextFieldForEdit.setText(bookBeanTableData.getBookName());
        if (bookBeanTableData.getBookAuthorSex().equals("男")) {
            maleRadioButton.setSelected(true);
        } else if (bookBeanTableData.getBookAuthorSex().equals("女")) {
            femaleRadioButton.setSelected(true);
        }
        priceTextField.setText(bookBeanTableData.getBookPrice());
        bookAuthorTextFieldForEdit.setText(bookBeanTableData.getBookAuthor());
        String bookType = bookBeanTableData.getBookType();

        int index = 0;
        ObservableList<?> inputList = FXCollections.observableArrayList(bookTypeComboBoxForQuery.getItems());
        for (int i = 0; i < inputList.size(); i++) {
            if (bookType.equals(inputList.get(i))) {
                index = i;
                break;
            }
        }

        bookTypeComboBoxForEdit.getSelectionModel().select(index);
        bookDescriptionTextArea.setText(bookBeanTableData.getBookDescription());
    }
}
