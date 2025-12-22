package com.tiketbioskop.Controller;

import com.tiketbioskop.Model.User;
import com.tiketbioskop.Utill.Session;
import com.tiketbioskop.Utill.ExcelUtill;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    public void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        User user = ExcelUtill.login(username, password);

        if (user == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Gagal");
            alert.setContentText("Username atau Password salah!");
            alert.show();
            return;
        }

        Session.setUser(user);

        try {
            Stage stage = (Stage) usernameField.getScene().getWindow();
            FXMLLoader loader;

            if (user.getRole().equals("ADMIN")) {
                loader = new FXMLLoader(getClass().getResource("/com/tiketbioskop/view/admin_dashboard.fxml"));
            } else {
                loader = new FXMLLoader(getClass().getResource("/com/tiketbioskop/view/buyer_dashboard.fxml"));
            }

            stage.setScene(new Scene(loader.load()));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}