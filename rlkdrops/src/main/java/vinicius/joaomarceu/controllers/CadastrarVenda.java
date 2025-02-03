package vinicius.joaomarceu.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import vinicius.joaomarceu.App;
import vinicius.joaomarceu.model.entities.Cliente;
import vinicius.joaomarceu.model.entities.Tenis;
import vinicius.joaomarceu.model.entities.Vendedor;
import vinicius.joaomarceu.repositories.RepositorioCliente;
import vinicius.joaomarceu.repositories.RepositorioTenis;
import vinicius.joaomarceu.repositories.RepositorioVenda;
import vinicius.joaomarceu.repositories.RepositorioVendedor;

public class CadastrarVenda implements Initializable{

    @FXML
    private ComboBox<Cliente> cbCliente;

    @FXML
    private ComboBox<Vendedor> cbVendedor;

    @FXML
    private TableView<Tenis> tbTenis;

    @FXML
    private TableColumn<Tenis, String> tcEstoque;

    @FXML
    private TableColumn<Tenis, String> tcMarca;

    @FXML
    private TableColumn<Tenis, String> tcTenis;

    @FXML
    private TableColumn<Tenis, String> tcValor;


    private RepositorioVenda repositorioVenda;
    private RepositorioVendedor repositorioVendedor;
    private RepositorioCliente repositorioCliente;
    private RepositorioTenis repositorioTenis;


    public CadastrarVenda(RepositorioVenda repositorioVenda, RepositorioVendedor repositorioVendedor,
            RepositorioCliente repositorioCliente, RepositorioTenis repositorioTenis) {
        this.repositorioVenda = repositorioVenda;
        this.repositorioVendedor = repositorioVendedor;
        this.repositorioCliente = repositorioCliente;
        this.repositorioTenis = repositorioTenis;
    }

    @FXML
    void cadastrar(ActionEvent event) {
        Cliente cliente = cbCliente.getValue();
        Vendedor vendedor = cbVendedor.getValue();
        List<Tenis> tenis = tbTenis.getSelectionModel().getSelectedItems();

        String msg;

        Resultado rs = repositorioVenda.cadastrarVenda(cliente, vendedor, tenis);

        Alert alert;
        msg = rs.getMsg();
        if(rs.foiErro()){
            alert = new Alert(AlertType.ERROR,msg);
        }else{
            tbTenis.getSelectionModel().clearSelection();
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
        tcTenis.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().getNome()));
        tcMarca.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().getCategoria()+""));
        tcEstoque.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().getEstoque()+""));
        tcValor.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().getValor()+""));

        Resultado rs = repositorioTenis.listar();

        if(rs.foiErro()){
            Alert alert = new Alert(AlertType.ERROR,rs.getMsg());
            alert.showAndWait();
            return;
        }

        List<Tenis> lista3 = (List)rs.comoSucesso().getObj();
        
        tbTenis.getItems().addAll(lista3);

        tbTenis.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        Resultado r1 = repositorioVendedor.listarVendedor();
        if(r1.foiSucesso()){
            List<Vendedor> lista = (List)r1.comoSucesso().getObj();
            cbVendedor.getItems().addAll(lista);   
        }
        else{
            Alert alert = new Alert(AlertType.ERROR,r1.getMsg());
            alert.showAndWait();
        }
        Resultado r2 = repositorioCliente.listarCliente();
        if(r2.foiSucesso()){
            List<Cliente> lista2 = (List)r2.comoSucesso().getObj();
            cbCliente.getItems().addAll(lista2);
        }
         else{
            Alert alert = new Alert(AlertType.ERROR,r1.getMsg());
            alert.showAndWait();
        }
    }

}