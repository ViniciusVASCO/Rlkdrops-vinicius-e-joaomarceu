package vinicius.joaomarceu.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import vinicius.joaomarceu.model.entities.Categoria;
import vinicius.joaomarceu.model.entities.Tenis;

public class JDBCTenisDao implements TenisDao {

    private FabricaConexoes fabrica;

    private String INSERTSQL = "INSERT INTO tca_Tenis (Nome_tenis, Descricao_tenis, Estoque_tenis, Valor_tenis, id_marca) VALUES (?, ?, ?, ?, ?)";

    private String SELECTSQL = "SELECT * FROM tca_Tenis";

    public JDBCTenisDao(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criarTenis(Tenis tenis) {
        try(Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement(INSERTSQL, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, tenis.getNome());
            pstm.setString(2, tenis.getDescricao());
            pstm.setInt(3, tenis.getEstoque());
            pstm.setFloat(4, tenis.getValor());
            pstm.setInt(5, tenis.getCategoria().getId());

            int ret = pstm.executeUpdate();

            if(ret == 1){
                ResultSet rs = pstm.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);

                tenis.setId(id);

                 return Resultado.sucesso("TÊNIS CADASTRADO!!!", tenis);

            }

            return Resultado.erro("ERRO DESCONHECIDO");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado listar() {
        try(Connection con = fabrica.getConnection()){
            PreparedStatement pstm = con.prepareStatement(SELECTSQL);

            ResultSet rs = pstm.executeQuery();

            ArrayList<Tenis> lista = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id_tenis");
                String nome = rs.getString("Nome_tenis");
                String descricao = rs.getString("Descricao_tenis");
                int estoque = rs.getInt("Estoque_tenis");
                float valor = rs.getFloat("Valor_tenis");

                Tenis tenis = new Tenis(id, estoque, nome, descricao, valor, null);

                lista.add(tenis);
            }
            return Resultado.sucesso("LISTA DE TÊNIS", lista);
        }
        catch(SQLException e){
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado atualizar(Tenis tenis) {
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public Resultado deletar(Tenis tenis) {
        throw new UnsupportedOperationException("Unimplemented method 'deletar'");
    }
}