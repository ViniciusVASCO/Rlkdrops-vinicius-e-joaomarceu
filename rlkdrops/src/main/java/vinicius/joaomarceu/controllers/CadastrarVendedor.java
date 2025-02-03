package vinicius.joaomarceu.controllers;

import com.github.hugoperlin.results.Resultado;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import vinicius.joaomarceu.App;
import vinicius.joaomarceu.repositories.RepositorioVendedor;

public class CadastrarVendedor {

    @FXML
    private TextField tfCpf;

    @FXML
    private TextField tfNome;

    private RepositorioVendedor repositorioVendedor;

    public CadastrarVendedor(RepositorioVendedor repositorioVendedor) {
        this.repositorioVendedor = repositorioVendedor;
    }

    @FXML
    void cadastrar(ActionEvent event) {
        String nome = tfNome.getText();
        String cpf = tfCpf.getText();

        Resultado rs = repositorioVendedor.cadastrarVendedor(cpf, nome);

        Alert alert;

        if(rs.foiErro()){
            alert = new Alert(AlertType.ERROR, rs.getMsg());
        }
        else{
            alert = new Alert(AlertType.INFORMATION, rs.getMsg());
        }
        alert.showAndWait();
    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

}