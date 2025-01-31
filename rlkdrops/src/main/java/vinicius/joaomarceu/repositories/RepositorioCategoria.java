package vinicius.joaomarceu.repositories;

import vinicius.joaomarceu.daos.CategoriaDao;
import vinicius.joaomarceu.model.entities.Categoria;

public class RepositorioCategoria {
  private CategoriaDao dao;

  public RepositorioCategoria(CategoriaDao dao) {
    this.dao = dao;
  }

  public Resultado cadastrarCategoria(String nome, String descricao){
    if(nome.isEmpty()||nome.isBlank()){
      return Resultado.erro("Nome invalido");
    }
    if(descricao.isEmpty()||descricao.isBlank()){
      return Resultado.erro("Descrição invalida");
    }
    Categoria categoria = new Categoria(nome, descricao);

    return dao.criar(categoria);
  }

  public Resultado listarCategoria(){
    return dao.listar();
  }

}
