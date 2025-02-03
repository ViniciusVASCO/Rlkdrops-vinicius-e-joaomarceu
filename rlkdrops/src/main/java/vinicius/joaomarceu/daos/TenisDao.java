package vinicius.joaomarceu.daos;

import com.github.hugoperlin.results.Resultado;

import vinicius.joaomarceu.model.entities.Tenis;

public interface TenisDao {
    Resultado criarTenis(Tenis tenis);

    Resultado listar();

    Resultado atualizar(Tenis tenis);

    Resultado deletar(Tenis tenis);

}