package vinicius.joaomarceu.model.entities;

import java.util.List;

public class Venda {

    private Cliente cliente;
    private Vendedor vendedor;
    private List<Tenis> tenis;


    public Venda(Cliente cliente, Vendedor vendedor) {
        this.cliente = cliente;
        this.vendedor = vendedor;
    }

 private int id;
    public Venda(int id, Cliente cliente, Vendedor vendedor) {
        this.id = id;
        this.cliente = cliente;
        this.vendedor = vendedor;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public Cliente getCliente() {
        return cliente;
    }


    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public Vendedor getVendedor() {
        return vendedor;
    }


    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }


    public List<Tenis> getTenis() {
        return tenis;
    }


    public void setTenis(List<Tenis> tenis) {
        this.tenis = tenis;
    }
}
