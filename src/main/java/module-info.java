module org.example.uap_pemlan {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires org.apache.poi.poi;

    opens org.example.uap_pemlan to javafx.fxml;
    opens com.tiketbioskop.Controller to javafx.fxml;
    opens com.tiketbioskop.main to javafx.graphics;

    exports org.example.uap_pemlan;
}