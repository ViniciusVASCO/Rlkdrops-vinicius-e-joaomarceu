package vinicius.joaomarceu.controllers;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import vinicius.joaomarceu.App;
import vinicius.joaomarceu.model.entities.Vendedor;
import vinicius.joaomarceu.repositories.RepositorioVendedor;

public class ListarVendedor implements Initializable{

    @FXML
    private ListView<Vendedor> lstVendedor;

    @FXML
    private TextArea taDescricao;

    private RepositorioVendedor repositorioVendedor;

    public ListarVendedor(RepositorioVendedor repositorioVendedor) {
        this.repositorioVendedor = repositorioVendedor;
    }

    @FXML
    void exibirDetalhes(MouseEvent event) {
        Vendedor vendedor = lstVendedor.getSelectionModel().getSelectedItem();
        if(vendedor!= null){
            taDescricao.clear();
            taDescricao.appendText("Nome: "+vendedor.getNome()+"\n");
            taDescricao.appendText("CPF: "+vendedor.getCpf());
        }
    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lstVendedor.getItems().clear();
        Resultado resultado = repositorioVendedor.listarVendedor();

        if(resultado.foiErro()){
            Alert alert = new Alert(AlertType.ERROR, resultado.getMsg());
            alert.showAndWait();
        }else{
             List lista = (List)resultado.comoSucesso().getObj();
            lstVendedor.getItems().addAll(lista);
        }

    }

}
