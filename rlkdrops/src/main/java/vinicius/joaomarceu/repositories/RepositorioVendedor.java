package vinicius.joaomarceu.repositories;

import vinicius.joaomarceu.daos.VendedorDao;
import vinicius.joaomarceu.model.entities.Cliente;
import vinicius.joaomarceu.model.entities.Vendedor;

public class RepositorioVendedor {
        private VendedorDao dao;

        public RepositorioVendedor(VendedorDao dao) {
            this.dao = dao;
        }

        public Resultado cadastrarVendedor(String cpf, String nome){

        if(nome.isBlank()||nome.isEmpty()){
            return Resultado.erro("Nome invalido");
        }
        if(cpf.isEmpty()||cpf.isEmpty()){
            return Resultado.erro("CPF invalido");
        }


        Vendedor vendedor =  new Vendedor(cpf, nome);
        return dao.criar(vendedor);
      }
      public Resultado listarVendedor(){
        return dao.listar();
      }
}