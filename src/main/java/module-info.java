module com.julypetshop.julypetshop {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
                        
    opens com.julypetshop.julypetshop to javafx.fxml;
    exports com.julypetshop.julypetshop;
}