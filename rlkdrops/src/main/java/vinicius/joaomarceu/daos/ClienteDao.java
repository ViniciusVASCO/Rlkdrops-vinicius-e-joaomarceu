package vinicius.joaomarceu.daos;

import vinicius.joaomarceu.model.entities.Cliente;

public interface ClienteDao {
    Resultado criar(Cliente cliente);

    Resultado listar();
    Resultado getById(String cpf);
    Resultado buscarCliente(String clienteCPF);

    Resultado atualizar(String cpf, Cliente cliente);

    Resultado deletar(Cliente cliente);
}
