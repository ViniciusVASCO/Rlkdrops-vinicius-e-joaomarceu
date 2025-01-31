package vinicius.joaomarceu.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import vinicius.joaomarceu.model.entities.Tenis;
import vinicius.joaomarceu.model.entities.Venda;

public class JDBCVendaDao implements VendaDao {

    private String INSERTSQL = "INSERT INTO tca_Venda(cpf_cliente, Cpf_vendedor) VALUES (?, ?)";
    private String INSERTTENIS = "INSERT INTO tca_Venda_Tenis(id_venda, id_tenis) VALUES (?, ?)";
    private String SELECTSQL = "SELECT * FROM tca_Venda";

    private FabricaConexoes fabricaConexoes;


    public JDBCVendaDao(FabricaConexoes fabricaConexoes) {
        this.fabricaConexoes = fabricaConexoes;
    }

    @Override
    public Resultado criar(Venda venda) {
        try (Connection con = fabricaConexoes.getConnection()) {
            PreparedStatement pstm = con.prepareStatement(INSERTSQL, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, venda.getCliente().getCpf());
            pstm.setString(2, venda.getVendedor().getCpf());

            int res = pstm.executeUpdate();

            if (res == 1) {
                ResultSet rs = pstm.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);

                venda.setId(id);

                PreparedStatement pstm2 = con.prepareStatement(INSERTTENIS);

                for (Tenis t : venda.getTenis()) {
                    pstm2.setInt(1, venda.getId());
                    pstm2.setInt(2, t.getId());

                pstm2.executeUpdate();
                }

                return Resultado.sucesso("VENDA SUCEDIDA", venda);
            }

            return Resultado.erro("ERRO DESCONHECIDO");

        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }


    @Override
    public Resultado listar() {
        try(Connection con = fabricaConexoes.getConnection()){
            PreparedStatement pstm = con.prepareStatement(SELECTSQL);

            ResultSet rs = pstm.executeQuery();

            ArrayList<Venda> lista = new ArrayList<>();

            while (rs.next()) {


                Venda venda = new Venda(null, null);



                lista.add(venda);
            }
            return Resultado.sucesso("LISTA DE VENDA", lista);
        }
        catch(SQLException e){
            return Resultado.erro(e.getMessage());
        }
    }

}