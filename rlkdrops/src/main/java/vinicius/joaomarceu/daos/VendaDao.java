package vinicius.joaomarceu.daos;

import vinicius.joaomarceu.model.entities.Venda;

public interface VendaDao {

    Resultado criar(Venda venda);

    Resultado listar();


}
