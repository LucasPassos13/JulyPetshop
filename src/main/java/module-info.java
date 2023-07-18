module com.julypetshop.julypetshop {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.julypetshop.app to javafx.fxml;
    exports com.julypetshop.app;
}