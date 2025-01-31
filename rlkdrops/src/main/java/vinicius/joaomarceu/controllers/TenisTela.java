package vinicius.joaomarceu.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import vinicius.joaomarceu.App;

public class TenisTela {

    @FXML
    void ListarTenis(ActionEvent event) {
        App.pushScreen("LISTARTENIS");
    }

    @FXML
    void cadastrarTenis(ActionEvent event) {
        App.pushScreen("CADASTRARTENIS");
    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

}
