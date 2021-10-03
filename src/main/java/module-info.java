module com.louis.books.booksmanagejavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires java.desktop;
    opens com.louis.books.booksmanagejavafx to javafx.fxml;
    exports com.louis.books.booksmanagejavafx;
    exports com.louis.books.booksmanagejavafx.controller;
    opens com.louis.books.booksmanagejavafx.controller to javafx.fxml;
}