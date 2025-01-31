package vinicius.joaomarceu.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import vinicius.joaomarceu.App;

public class Principal {

    @FXML
    void categoriaTela(ActionEvent event) {
        App.pushScreen("CATEGORIA");
    }

    @FXML
    void clienteTela(ActionEvent event) {
        App.pushScreen("CLIENTE");
    }

    @FXML
    void tenisTela(ActionEvent event) {
        App.pushScreen("TENIS");
    }

    @FXML
    void vendedorTela(ActionEvent event) {
        App.pushScreen("VENDEDOR");
    }

     @FXML
    void vendaTela(ActionEvent event) {
        App.pushScreen("VENDA");
    }

    @FXML
    void vendaLista(ActionEvent event) {
        App.pushScreen("LISTARVENDA");
    }

}
