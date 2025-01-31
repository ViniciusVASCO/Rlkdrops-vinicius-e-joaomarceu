package vinicius.joaomarceu.repositories;

import java.util.List;

import  vinicius.joaomarceu.daos.ClienteDao;
import  vinicius.joaomarceu.model.entities.Cliente;

public class RepositorioCliente {
    private ClienteDao dao;

    public RepositorioCliente(ClienteDao dao) {
        this.dao = dao;
    }

    public Resultado cadastrarCliente(String cpf, String nome){

        if(nome.isBlank()||nome.isEmpty()){
            return Resultado.erro("Nome invalido");
        }
        if(cpf.isEmpty()||cpf.isEmpty()){
            return Resultado.erro("CPF invalido");
        }

        Cliente cliente =  new Cliente(cpf, nome);
        return dao.criar(cliente);
      }

      public Resultado alterarCliente(String cpf, String nome){
        Cliente cliente = new Cliente(cpf, nome);

        return dao.atualizar(cpf, cliente);
      }

      public Resultado listarCliente(){
        return dao.listar();
      }
}
