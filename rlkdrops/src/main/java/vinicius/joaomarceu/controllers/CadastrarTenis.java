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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import vinicius.joaomarceu.App;
import vinicius.joaomarceu.model.entities.Categoria;
import vinicius.joaomarceu.repositories.RepositorioCategoria;
import vinicius.joaomarceu.repositories.RepositorioTenis;

public class CadastrarTenis implements Initializable {

    private RepositorioTenis repositorioTenis;
    private RepositorioCategoria repositorioCategoria;

    public CadastrarTenis(RepositorioTenis repositorioTenis, RepositorioCategoria repositorioCategoria) {
        this.repositorioTenis = repositorioTenis;
        this.repositorioCategoria = repositorioCategoria;
    }

    @FXML
    private ComboBox<Categoria> cbCategoria;

    @FXML
    private TextArea taDescricao;

    @FXML
    private TextField tfEstoque;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfValor;

    @FXML
    void cadastro(ActionEvent event) {
        String descricao = taDescricao.getText();
        String nome = tfNome.getText();
        String sEstoque = tfEstoque.getText();
        String sValor = tfValor.getText();
        Categoria categoria = cbCategoria.getValue();

        String msg;

        int estoque = 0;
        estoque = Integer.valueOf(sEstoque);

        float valor = 0;
        valor = Float.valueOf(sValor);

        Resultado rs = repositorioTenis.cadastrarTenis(nome, descricao, estoque, valor, categoria);

        Alert alert;
        msg = rs.getMsg();
        if(rs.foiErro()){
            alert = new Alert(AlertType.ERROR,msg);
        }else{
            alert = new Alert(AlertType.INFORMATION,msg);

        }

        alert.showAndWait();
    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Resultado r1 = repositorioCategoria.listarCategoria();
        if(r1.foiSucesso()){
            List<Categoria> lista = (List)r1.comoSucesso().getObj();
            cbCategoria.getItems().addAll(lista);   
        }
        else{
            Alert alert = new Alert(AlertType.ERROR,r1.getMsg());
            alert.showAndWait();
        }
    }

}