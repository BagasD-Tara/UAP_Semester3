package com.tiketbioskop.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class AddFilmController {

    @FXML
    private TextField titleField;

    @FXML
    private TextField genreField;

    @FXML
    private TextField durationField;

    @FXML
    private TextField priceField;

    public void handleSaveFilm(ActionEvent event) {
        String title = titleField.getText();
        String genre = genreField.getText();
        int duration = Integer.parseInt(durationField.getText());
        double price = Double.parseDouble(priceField.getText());

        System.out.println("Film disimpan: " + title);
    }
}
