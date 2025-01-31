package vinicius.joaomarceu.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import vinicius.joaomarceu.model.entities.Cliente;
import vinicius.joaomarceu.model.repositories.RepositorioCliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import vinicius.joaomarceu.App;
import javafx.scene.control.Alert.AlertType;

public class CadastrarCategoria {

    @FXML
    private TextArea taDescricao;

    @FXML
    private TextField tfNome;

    private RepositorioCategoria repositorioCategoria;

    public CadastrarCategoria(RepositorioCategoria repositorioCategoria) {
      this.repositorioCategoria = repositorioCategoria;
    }

    @FXML
    void cadastrar(ActionEvent event) {
      String nome = tfNome.getText();
      String descricao = taDescricao.getText();

      Resultado rs = repositorioCategoria.cadastrarCategoria(nome, descricao);

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