package com.tiketbioskop.Controller;

import com.tiketbioskop.Model.User;
import com.tiketbioskop.Utill.Session;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AdminDashboardController {

    @FXML
    private Label welcomeLabel;

    @FXML
    public void initialize() {
        User user = Session.getUser();
        welcomeLabel.setText("Selamat Datang, " + user.getNama() + " (Admin)");
    }

    public void handleAddFilm() {
        changeScene("/com/tiketbioskop/view/add_film.fxml");
    }

    public void handleFilmList() {
        changeScene("/com/tiketbioskop/view/film_list.fxml");
    }

    public void handleLogout() {
        Session.clear();
        changeScene("/com/tiketbioskop/view/login.fxml");
    }

    private void changeScene(String fxmlPath) {
        try {
            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
