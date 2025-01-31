package vinicius.joaomarceu.utils;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import vinicius.joaomarceu.daos.FabricaConexoes;

public class DBUtils {

    public static int getLastId(PreparedStatement pstm) throws SQLException{

        Connection con = pstm.getConnection();
        int id = -1;

        if(con.getMetaData().getDatabaseProductName().equals("SQLite")){
            ResultSet rs2 = con.prepareStatement("select last_insert_rowid();").executeQuery();
            rs2.next();
            id = rs2.getInt(1);
            rs2.close();
        }else{
            ResultSet rs = pstm.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        }
        return id;
    }

    public static boolean checkDataBase(FabricaConexoes fabrica){
        try(Connection con = fabrica.getConnection()){

            PreparedStatement stm = con.prepareStatement("SELECT name FROM sqlite_master WHERE type='table' AND name=?");

            stm.setString(1, "contatos");

            ResultSet rs = stm.executeQuery();

            if(rs.next()){
                return true;
            }
            return false;


        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.exit(1);
            return false;
        }
    }

    public static void createDataBase(FabricaConexoes fabrica){
        try(Connection con = fabrica.getConnection()){

            Statement stm = con.createStatement();

            stm.execute("CREATE TABLE IF NOT EXISTS contatos (" + //
                    "    id INTEGER PRIMARY KEY AUTOINCREMENT," + //
                    "    nome varchar(255) NOT NULL," + //
                    "    telefone varchar(255) NOT NULL" + //
                    ");");


        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public static void resetDataBase(FabricaConexoes fabrica){
        try(Connection con = fabrica.getConnection()){

            Statement stm = con.createStatement();

            stm.executeUpdate("DROP TABLE IF EXISTS contatos");
            stm.executeUpdate("CREATE TABLE IF NOT EXISTS contatos (" + //
                    "    id INTEGER PRIMARY KEY AUTOINCREMENT," + //
                    "    nome varchar(255) NOT NULL," + //
                    "    telefone varchar(255) NOT NULL" + //
                    ");");

        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }


}