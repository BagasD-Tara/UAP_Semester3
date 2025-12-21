package com.tiketbioskop.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class FilmListController {

    @FXML
    private ListView<String> filmListView;

    public void initialize() {
        filmListView.getItems().addAll(
                "Avengers",
                "Interstellar",
                "Inception"
        );
    }
}

