package vinicius.joaomarceu.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vinicius.joaomarceu.model.entities.Categoria;
import vinicius.joaomarceu.model.entities.Cliente;
import vinicius.joaomarceu.model.entities.Vendedor;

public class JDBCVendedorDao implements VendedorDao {

    private String INSERTSQL = "INSERT INTO tca_Vendedor(Cpf_vendedor, Nome_vendedor) VALUES (?, ?)";
    private String SELECTSQL = "SELECT * FROM tca_Vendedor";
    private String SELECTSQL2 = "SELECT * FROM tca_Vendedor where Cpf_vendedor=?";
    private String SELECTSQL3 = "SELECT Cpf_vendedor FROM tca_venda where id_venda =?";

    private FabricaConexoes fabricaConexoes;

    public JDBCVendedorDao(FabricaConexoes fabricaConexoes) {
        this.fabricaConexoes = fabricaConexoes;
    }

    @Override
    public Resultado criar(Vendedor vendedor) {
          try (Connection con = fabricaConexoes.getConnection()){

            PreparedStatement pstm = con.prepareStatement(INSERTSQL);

            pstm.setString(1, vendedor.getCpf());
            pstm.setString(2, vendedor.getNome());

            int ret = pstm.executeUpdate();

            if(ret == 1){
                return Resultado.sucesso("Vendedor cadastrado", vendedor);
            }

            return Resultado.erro("Erro, Vendedor não cadastrado!!");

        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado listar() {
        try(Connection con = fabricaConexoes.getConnection()) {
            PreparedStatement pstm = con.prepareStatement(SELECTSQL);

            ResultSet rs = pstm.executeQuery();

            ArrayList<Vendedor> lista = new ArrayList<>();

            while (rs.next()) {
                String nome = rs.getString("Nome_vendedor");
                String cpf = rs.getString("Cpf_vendedor");

                Vendedor vendedor = new Vendedor(cpf, nome);

                lista.add(vendedor);
            }
            return Resultado.sucesso("LISTA DE VENDEDORES", lista);

        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado atualizar(Vendedor vendedor) {
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public Resultado deletar(Vendedor vendedor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletar'");
    }

    @Override
    public Resultado getById(String cpf) {
        try (Connection con = fabricaConexoes.getConnection()) {

            PreparedStatement pstm = con.prepareStatement(SELECTSQL2);

            pstm.setString(1, cpf);

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("Nome_marca");
                String sCpf = rs.getString("Cpf_vendedor");

                Vendedor vendedor = new Vendedor(sCpf, nome);
                return Resultado.sucesso("Vendedor encontrado!", vendedor);
            }

            return Resultado.erro("Categoria não encontrado!");

        }
        catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado buscarVendedor(String vendedorCPF) {
        try(Connection con = fabricaConexoes.getConnection()) {
            PreparedStatement pstm = con.prepareStatement(SELECTSQL3);
            pstm.setString(1, vendedorCPF);

            ResultSet rs = pstm.executeQuery();
            rs.next();
            String cpfVendedor = rs.getString("Cpf_vendedor");
            return getById(vendedorCPF);

          } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
          }
    }
}
