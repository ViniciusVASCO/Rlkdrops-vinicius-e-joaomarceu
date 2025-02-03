package vinicius.joaomarceu.daos;

import vinicius.joaomarceu.model.entities.Venda;

import com.github.hugoperlin.results.Resultado;

public interface VendaDao {

    Resultado criar(Venda venda);

    Resultado listar();


}
