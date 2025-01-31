package vinicius.joaomarceu.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import  vinicius.joaomarceu.model.entities.Categoria;

public class JDBCCategoriaDao implements CategoriaDao {

  private FabricaConexoes fabrica;

  private String INSERTSQL = "INSERT INTO tca_Marca(Nome_marca, Descricao_marca) VALUES (?, ?)";
  private String SELECTSQL = "SELECT * FROM tca_Marca";
  private String SELECTSQL2 = "SELECT * FROM tca_Marca where id_marca=?";
  private String SELECTSQL3 = "SELECT id_marca FROM tca_Tenis where id_tenis =?";

  public JDBCCategoriaDao(FabricaConexoes fabrica) {
    this.fabrica = fabrica;
  }

  @Override
  public Resultado criar(Categoria categoria) {
    try(Connection con = fabrica.getConnection()) {
      PreparedStatement pstm = con.prepareStatement(INSERTSQL, Statement.RETURN_GENERATED_KEYS);
      pstm.setString(1, categoria.getNome());
      pstm.setString(2, categoria.getDescricao());

      int ret = pstm.executeUpdate();

      if(ret == 1){
        ResultSet rs = pstm.getGeneratedKeys();
        rs.next();
        int id = rs.getInt(1);

        categoria.setId(id);

        return Resultado.sucesso("CATEGORIA CADASTRADA!!", categoria);
      }
      return Resultado.erro("ERRO DESCONHECIDO!!");

    } catch (SQLException e) {
        return Resultado.erro(e.getMessage());
      }
  }

  @Override
  public Resultado listar() {
    try(Connection con = fabrica.getConnection()) {
      PreparedStatement pstm = con.prepareStatement(SELECTSQL);

      ResultSet rs = pstm.executeQuery();

      ArrayList<Categoria> lista = new ArrayList<>();

      while (rs.next()) {
        int id = rs.getInt("id_marca");
        String nome = rs.getString("Nome_marca");
        String descricao = rs.getString("Descricao_marca");

        Categoria categoria = new Categoria(id, nome, descricao);

        lista.add(categoria);
      }
      return Resultado.sucesso("LISTA DE CATEGORIA", lista);
      
    } catch (SQLException e) {
      return Resultado.erro(e.getMessage());
    }
  }

  @Override
  public Resultado autalizar(Categoria categoria) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'autalizar'");
  }

  @Override
  public Resultado deletar(Categoria categoria) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deletar'");
  }

  @Override
  public Resultado getById(int id) {
    try (Connection con = fabrica.getConnection()) { 

      PreparedStatement pstm = con.prepareStatement(SELECTSQL2); 

      pstm.setInt(1, id);

      ResultSet rs = pstm.executeQuery(); 

      if (rs.next()) {
          String nome = rs.getString("Nome_marca"); 
          String descricao = rs.getString("Descricao_marca"); 

          Categoria categoria = new Categoria(id, nome, descricao); 
          return Resultado.sucesso("Categoria encontrado!", categoria); 
      }

      return Resultado.erro("Categoria não encontrado!");

    } 
    catch (SQLException e) { 
        return Resultado.erro(e.getMessage()); 
    }
  }

  @Override
  public Resultado buscarCategoria(int tenisId) {
    try(Connection con = fabrica.getConnection()) {
      PreparedStatement pstm = con.prepareStatement(SELECTSQL3);
      pstm.setInt(1, tenisId);

      ResultSet rs = pstm.executeQuery();
      rs.next();
      int idCategoria = rs.getInt("id_marca");

      return getById(idCategoria); 

    } catch (SQLException e) {
      return Resultado.erro(e.getMessage());
    }
  }
  
}
