module UAP.Pemlan {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.apache.poi.poi;

    opens com.tiketbioskop.main to javafx.graphics;
    opens com.tiketbioskop.Controller to javafx.fxml;

    exports com.tiketbioskop.main;
}