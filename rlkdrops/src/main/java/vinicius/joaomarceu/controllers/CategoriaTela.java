package vinicius.joaomarceu.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import vinicius.joaomarceu.App;

public class CategoriaTela {

    @FXML
    void ListarCategoria(ActionEvent event) {
      App.pushScreen("LISTARCATEGORIA");
    }

    @FXML
    void cadastrarCategoria(ActionEvent event) {
      App.pushScreen("CADASTRARCATEGORIA");
    }

    @FXML
    void voltar(ActionEvent event) {
      App.popScreen();
    }

}