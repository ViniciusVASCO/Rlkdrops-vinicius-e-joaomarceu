package vinicius.joaomarceu.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import vinicius.joaomarceu.App;

public class ClienteTela {

    @FXML
    void ListarCliente(ActionEvent event) {
        App.pushScreen("LISTARCLIENTE");
    }


    @FXML
    void cadastrarCliente(ActionEvent event) {
        App.pushScreen("CADASTRARCLIENTE");
    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

}