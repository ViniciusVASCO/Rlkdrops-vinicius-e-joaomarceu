package vinicius.joaomarceu.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import  vinicius.joaomarceu.model.entities.Categoria;
import  vinicius.joaomarceu.model.entities.Cliente;
import  vinicius.joaomarceu.model.entities.Vendedor;

public class JDBCClienteDao implements ClienteDao {

    private String INSERTSQL = "INSERT INTO tca_Cliente(cpf_cliente, Nome_cliente) Values (?, ?)";
    private String SELECTSQL = "SELECT * FROM tca_Cliente";
    private String SELECTSQL2 = "SELECT * FROM tca_Cliente WHERE cpf_cliente = ?";
    private String SELECTSQL3 = "SELECT Cpf_cliente FROM tca_Venda WHERE cpf_cliente = ?";

    private FabricaConexoes fabricaConexoes;

    public JDBCClienteDao(FabricaConexoes fabricaConexoes) {
        this.fabricaConexoes = fabricaConexoes;
    }

    @Override
    public Resultado criar(Cliente cliente) {
        try (Connection con = fabricaConexoes.getConnection()){

            PreparedStatement pstm = con.prepareStatement(INSERTSQL);

            pstm.setString(1, cliente.getCpf());
            pstm.setString(2, cliente.getNome());
            int ret = pstm.executeUpdate();

            if(ret == 1){
                return Resultado.sucesso("Cliente cadastrado", cliente);
            }

            return Resultado.erro("Erro, Cliente não cadastrado!!");

        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado listar() {
        try(Connection con = fabricaConexoes.getConnection()) {
            PreparedStatement pstm = con.prepareStatement(SELECTSQL);

            ResultSet rs = pstm.executeQuery();

            ArrayList<Cliente> lista = new ArrayList<>();

            while (rs.next()) {
                String nome = rs.getString("Nome_cliente");
                String cpf = rs.getString("Cpf_cliente");

                Cliente cliente = new Cliente(cpf, nome);

                lista.add(cliente);
            }
            return Resultado.sucesso("LISTA DE CATEGORIA", lista);

        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado atualizar(String cpf, Cliente cliente) {
        try (Connection con = fabricaConexoes.getConnection()){

            PreparedStatement pstm = con.prepareStatement("UPDATE tca_Cliente SET Nome_cliente=? where cpf_cliente=?");

            pstm.setString(1, cliente.getNome());
            pstm.setString(2, cpf);

            int ret = pstm.executeUpdate();

            if(ret == 1){
                return Resultado.sucesso("CLIENTE ATUALIZADO", cliente);
            }
            return Resultado.erro("ERRO NÃO IDENTIFICADO!");

        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado deletar(Cliente cliente) {

        throw new UnsupportedOperationException("Unimplemented method 'deletar'");
    }

    @Override
    public Resultado getById(String cpf) {
        try (Connection con = fabricaConexoes.getConnection()) {

            PreparedStatement pstm = con.prepareStatement(SELECTSQL2);

            pstm.setString(1, cpf);

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("Nome_cliente");
                String sCpf = rs.getString("Cpf_cliente");

                Cliente cliente = new Cliente(sCpf, nome);
                return Resultado.sucesso("Cliente encontrado!", cliente);
            }

            return Resultado.erro("Cliente não encontrado!");

        }
        catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado buscarCliente(String clienteCPF) {
        try(Connection con = fabricaConexoes.getConnection()) {
            PreparedStatement pstm = con.prepareStatement(SELECTSQL3);
            pstm.setString(1, clienteCPF);

            ResultSet rs = pstm.executeQuery();
            rs.next();
            int idCategoria = rs.getInt("cpf_cliente");

            return getById(clienteCPF);

          } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
          }
    }

}
