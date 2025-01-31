package vinicius.joaomarceu;


import java.util.List;
import com.mysql.cj.xdevapi.Client;

import vinicius.joaomarceu.daos.CategoriaDao;
import vinicius.joaomarceu.daos.ClienteDao;
import vinicius.joaomarceu.daos.FabricaConexoes;
import vinicius.joaomarceu.daos.JDBCClienteDao;
import vinicius.joaomarceu.daos.JDBCCategoriaDao;
import vinicius.joaomarceu.daos.JDBCTenisDao;
import vinicius.joaomarceu.daos.JDBCVendedorDao;
import vinicius.joaomarceu.daos.TenisDao;
import vinicius.joaomarceu.daos.VendedorDao;
import vinicius.joaomarceu.model.entities.Categoria;
import vinicius.joaomarceu.model.entities.Cliente;
import vinicius.joaomarceu.model.entities.Tenis;
import vinicius.joaomarceu.model.entities.Vendedor;

public class Teste {
  public static void main(String[] args) {
   //categoriaTeste();
   //tenisTeste();
   //clienteTeste();
   vendedorTeste();
  }

  public static Categoria categoriaTeste(){
     CategoriaDao dao = new JDBCCategoriaDao(FabricaConexoes.getInstance());
    Categoria categoria = new Categoria("OUS", "Marca internacional para skatistas");
    Resultado res = dao.criar(categoria);

    System.out.println(res.getMsg());

    return categoria;

    /*List<Categoria> lista = (List)dao.listar().comoSucesso().getObj();

    for(Categoria c: lista){
      System.out.println(c.getId());
      System.out.println(c.getNome());
      System.out.println(c.getDescricao()+"\n");
    }*/
  }

  public static void tenisTeste(){
    Categoria categoria = categoriaTeste();
    TenisDao dao = new JDBCTenisDao(FabricaConexoes.getInstance());
    List<Tenis> lista = (List)dao.listar().comoSucesso().getObj();


    for(Tenis t: lista){
      System.out.println(t.getId());
      System.out.println(t.getNome());
      System.out.println(t.getDescricao());
      System.out.println(t.getEstoque());
      System.out.println(t.getValor()+"\n");
    }
  }

  public static void clienteTeste(){
    ClienteDao clienteDao = new JDBCClienteDao(FabricaConexoes.getInstance());
    //Cliente cliente = new Cliente("10873850912", "Fellipe Fernandes");

    /*Resultado res = clienteDao.criar(cliente);

    System.out.println(res.getMsg());*/

     List<Cliente> lista = (List)clienteDao.listar().comoSucesso().getObj();

    for(Cliente c: lista){
      System.out.println(c.getNome());
      System.out.println(c.getCpf()+"\n");
    }
  }

  public static void vendedorTeste(){
    VendedorDao vendedorDao = new JDBCVendedorDao(FabricaConexoes.getInstance());
    /*Vendedor vendedor = new Vendedor("12345678912", "Cleiton");

    Resultado res = vendedorDao.criar(vendedor);

    System.out.println(res.getMsg());*/

    List<Vendedor> lista = (List)vendedorDao.listar().comoSucesso().getObj();

    for(Vendedor v: lista){
      System.out.println(v.getNome());
      System.out.println(v.getCpf()+"\n");
    }
  }

}