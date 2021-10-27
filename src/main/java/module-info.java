module booksmanagejavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires java.desktop;
    opens com.louis.books.booksmanagejavafx to javafx.fxml;
    exports com.louis.books.booksmanagejavafx;
    exports com.louis.books.booksmanagejavafx.controller;
    opens com.louis.books.booksmanagejavafx.controller to javafx.fxml;
}