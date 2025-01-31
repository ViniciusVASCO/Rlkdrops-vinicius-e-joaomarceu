package vinicius.joaomarceu.daos;

import vinicius.joaomarceu.model.entities.Tenis;
import vinicius.joaomarceu.model.entities.Vendedor;

public interface VendedorDao {

     Resultado criar(Vendedor vendedor);

    Resultado listar();
    Resultado getById(String cpf);
    Resultado buscarVendedor(String vendedorCPF);

    Resultado atualizar(Vendedor vendedor);

    Resultado deletar(Vendedor vendedor);
}