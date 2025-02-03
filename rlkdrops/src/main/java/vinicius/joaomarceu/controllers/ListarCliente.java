package vinicius.joaomarceu.controllers;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


import vinicius.joaomarceu.App;
import vinicius.joaomarceu.model.entities.Cliente;
import vinicius.joaomarceu.repositories.RepositorioCliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import com.github.hugoperlin.results.Resultado;

public class ListarCliente implements Initializable {

    @FXML
    private ListView<Cliente> lstCliente;

    private Cliente selecionado;

    private RepositorioCliente repositorioCliente;

    public ListarCliente(RepositorioCliente repositorioCliente) {
        this.repositorioCliente = repositorioCliente;
    }

    @FXML
    void selecionar(MouseEvent event) {
        selecionado = lstCliente.getSelectionModel().getSelectedItem();
    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

    @FXML
    void alterar(ActionEvent event) {
        if(selecionado!= null){
            App.pushScreen("CADASTRARCLIENTE", o->new CadastrarCliente(repositorioCliente, selecionado));
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
         lstCliente.getItems().clear();
        
        lstCliente.getSelectionModel()
              .setSelectionMode(SelectionMode.MULTIPLE);
        
        
        Resultado r = repositorioCliente.listarCliente();

        if(r.foiSucesso()){
            List<Cliente> lista = (List)r.comoSucesso().getObj();
            lstCliente.getItems().addAll(lista);
        }else{
            Alert alert = new Alert(AlertType.ERROR, r.getMsg());
            alert.showAndWait();
        }
    }
}
