package vinicius.joaomarceu.controllers;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import vinicius.joaomarceu.App;
import vinicius.joaomarceu.model.entities.Categoria;
import vinicius.joaomarceu.model.repositories.RepositorioCategoria;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class ListarCategoria implements Initializable{

    @FXML
    private ListView<Categoria> lstCategoria;

     @FXML
    private TextArea taDescricao;

    private RepositorioCategoria repositorioCategoria;

    public ListarCategoria(RepositorioCategoria repositorioCategoria) {
        this.repositorioCategoria = repositorioCategoria;
    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

    
    @FXML
    void exibirDetalhes(MouseEvent event) {
        Categoria categoria = lstCategoria.getSelectionModel().getSelectedItem();

        if(categoria!=null){
            taDescricao.clear();
            taDescricao.appendText("Nome: "+categoria.getNome()+"\n");
            taDescricao.appendText("Descrição: "+categoria.getDescricao());
        }

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lstCategoria.getItems().clear();
        Resultado resultado = repositorioCategoria.listarCategoria();

        if(resultado.foiErro()){
            Alert alert = new Alert(AlertType.ERROR, resultado.getMsg());
            alert.showAndWait();
        }else{
            List lista = (List)resultado.comoSucesso().getObj();
            lstCategoria.getItems().addAll(lista);
        }
    }

}
