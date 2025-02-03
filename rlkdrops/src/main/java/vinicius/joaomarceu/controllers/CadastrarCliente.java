package vinicius.joaomarceu.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import vinicius.joaomarceu.App;
import vinicius.joaomarceu.model.entities.Cliente;
import vinicius.joaomarceu.repositories.RepositorioCliente;

public class CadastrarCliente implements Initializable{

  @FXML
  private Button btAcao;

  @FXML
  private TextField tfCpf;

  @FXML
  private TextField tfNome;

  private RepositorioCliente repositorioCliente;
  private Cliente anterior;

  public CadastrarCliente(RepositorioCliente repositorioCliente) {
    this.repositorioCliente = repositorioCliente;
  }

  public CadastrarCliente(RepositorioCliente repositorioCliente, Cliente anterior) {
    this.repositorioCliente = repositorioCliente;
    this.anterior = anterior;
  }


  @FXML
  void cadastrar(ActionEvent event) {
      String nome = tfNome.getText();
    String cpf = tfCpf.getText();

    Resultado rs; 
    if(anterior ==null){
      rs = repositorioCliente.cadastrarCliente(cpf, nome);
    }
    else{
      rs = repositorioCliente.alterarCliente(cpf, nome);
    }

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

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    if(anterior!=null){
      tfCpf.setText(anterior.getCpf());
      tfNome.setText(anterior.getNome());

      btAcao.setText("ATUALIZAR");
    }
  }

}