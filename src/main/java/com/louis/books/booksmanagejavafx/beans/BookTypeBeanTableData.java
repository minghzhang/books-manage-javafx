package com.louis.books.booksmanagejavafx.beans;

import javafx.beans.property.SimpleStringProperty;

/**
 * @date : 2021/9/29
 * 由于要将数据库查询到的记录显示到表格中，但是JavaFX中表格显示数据的类型不同，因此需要再创建实体类。
 */
public class BookTypeBeanTableData {

    private SimpleStringProperty bookTypeId;

    private SimpleStringProperty bookTypeName;

    private SimpleStringProperty bookTypeDescription;

    public BookTypeBeanTableData() {
    }

    public BookTypeBeanTableData(String bookTypeId, String bookTypeName, String bookTypeDescription) {
        this.bookTypeId = new SimpleStringProperty(bookTypeId);
        this.bookTypeName = new SimpleStringProperty(bookTypeName);
        this.bookTypeDescription = new SimpleStringProperty(bookTypeDescription);
    }

    public String getBookTypeId() {
        return bookTypeId.get();
    }

    public SimpleStringProperty bookTypeIdProperty() {
        return bookTypeId;
    }

    public void setBookTypeId(String bookTypeId) {
        this.bookTypeId.set(bookTypeId);
    }

    public String getBookTypeName() {
        return bookTypeName.get();
    }

    public SimpleStringProperty bookTypeNameProperty() {
        return bookTypeName;
    }

    public void setBookTypeName(String bookTypeName) {
        this.bookTypeName.set(bookTypeName);
    }

    public String getBookTypeDescription() {
        return bookTypeDescription.get();
    }

    public SimpleStringProperty bookTypeDescriptionProperty() {
        return bookTypeDescription;
    }

    public void setBookTypeDescription(String bookTypeDesciption) {
        this.bookTypeDescription.set(bookTypeDesciption);
    }

}
