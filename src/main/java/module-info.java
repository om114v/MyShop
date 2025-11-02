module com.shop {
    requires javafx.controls;
    requires java.sql;
    requires java.desktop;
    requires org.xerial.sqlitejdbc;

    exports com.shop.ui;
}