package vinicius.joaomarceu.daos;

import com.github.hugoperlin.results.Resultado;

import vinicius.joaomarceu.model.entities.Categoria;

public interface CategoriaDao {
  Resultado criar(Categoria categoria);

  Resultado listar();
  Resultado getById(int id);
  Resultado buscarCategoria(int tenisId);

  Resultado autalizar(Categoria categoria);

  Resultado deletar(Categoria categoria);
}
