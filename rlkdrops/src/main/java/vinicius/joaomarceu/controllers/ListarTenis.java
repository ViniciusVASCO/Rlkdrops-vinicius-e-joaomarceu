package vinicius.joaomarceu.controllers;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import vinicius.joaomarceu.App;
import vinicius.joaomarceu.model.entities.Categoria;
import vinicius.joaomarceu.model.entities.Tenis;
import vinicius.joaomarceu.model.repositories.RepositorioCategoria;
import vinicius.joaomarceu.model.repositories.RepositorioTenis;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class ListarTenis implements Initializable{

    @FXML
    private ListView<Tenis> lstTenis;

     @FXML
    private TextArea taDescricao;

    private RepositorioTenis repositorioTenis;

    
    public ListarTenis(RepositorioTenis repositorioTenis) {
        this.repositorioTenis = repositorioTenis;
    }


    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

    
    @FXML
    void exibirDetalhes(MouseEvent event) {
        Tenis tenis = lstTenis.getSelectionModel().getSelectedItem();

        if(tenis!=null){
            taDescricao.clear();
            taDescricao.appendText("Nome: "+tenis.getNome()+"\n");
            taDescricao.appendText("Valor: "+tenis.getValor()+"\n");
            taDescricao.appendText("Quantidade de pares: "+tenis.getEstoque()+"\n");
            taDescricao.appendText("Descrição: "+tenis.getDescricao()+"\n");
            // Verifica se a categoria está presente antes de acessá-la
        
            taDescricao.appendText("Categoria: " + tenis.getCategoria().getNome());
        }

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
         lstTenis.getItems().clear();
        Resultado resultado = repositorioTenis.listar();

        if(resultado.foiErro()){
            Alert alert = new Alert(AlertType.ERROR, resultado.getMsg());
            alert.showAndWait();
        }else{
            List lista = (List)resultado.comoSucesso().getObj();
            lstTenis.getItems().addAll(lista);
        }
    }

}
