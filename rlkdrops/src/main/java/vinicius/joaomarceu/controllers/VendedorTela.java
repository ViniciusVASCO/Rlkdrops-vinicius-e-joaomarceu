package vinicius.joaomarceu.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import vinicius.joaomarceu.App;

public class VendedorTela {

    @FXML
    void ListarVendedor(ActionEvent event) {
        App.pushScreen("LISTARVENDEDOR");
    }

    @FXML
    void cadastrarVendedor(ActionEvent event) {
        App.pushScreen("CADASTRARVENDEDOR");
    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

}