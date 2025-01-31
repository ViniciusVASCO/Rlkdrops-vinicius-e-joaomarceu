package vinicius.joaomarceu.repositories;

import java.util.List;

import vinicius.joaomarceu.daos.CategoriaDao;
import vinicius.joaomarceu.daos.TenisDao;
import vinicius.joaomarceu.model.entities.Categoria;
import vinicius.joaomarceu.model.entities.Tenis;

public class RepositorioTenis {
    private TenisDao dao;
    private CategoriaDao categoriaDao;

  public RepositorioTenis(TenisDao dao, CategoriaDao categoriaDao) {
      this.dao = dao;
      this.categoriaDao = categoriaDao;
    }

  public Resultado cadastrarTenis(String nome, String descricao, int estoque, float valor, Categoria categoria){
    if(nome.isEmpty()||nome.isBlank()){
      return Resultado.erro("Nome invalido");
    }
    if(estoque<=0){
        return Resultado.erro("Não há Tênis no estoque");
    }
    if(valor<=0){
        return Resultado.erro("Tênis tem que ter um valor");
    }

    if(categoria==null){
      return Resultado.erro("Escolha uma categoria");
    }

    Tenis tenis = new Tenis(estoque, nome, descricao, valor, categoria);

    return dao.criarTenis(tenis);
  }
  public Resultado listar(){
    Resultado resultado = dao.listar();

    if(resultado.foiSucesso()){
      List<Tenis> lista = (List)resultado.comoSucesso().getObj();

      for(Tenis tenis: lista){
        Resultado r1 = categoriaDao.buscarCategoria(tenis.getId());
        if(r1.foiErro()){
          return r1;
        }
        Categoria categoria = (Categoria)r1.comoSucesso().getObj();
        tenis.setCategoria(categoria);
      }
    }

    return resultado;
  }
}