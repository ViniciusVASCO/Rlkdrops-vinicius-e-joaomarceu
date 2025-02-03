package vinicius.joaomarceu.repositories;

import java.util.List;
import vinicius.joaomarceu.daos.ClienteDao;
import vinicius.joaomarceu.daos.VendaDao;
import vinicius.joaomarceu.daos.VendedorDao;
import vinicius.joaomarceu.model.entities.Categoria;
import vinicius.joaomarceu.model.entities.Cliente;
import vinicius.joaomarceu.model.entities.Tenis;
import vinicius.joaomarceu.model.entities.Venda;
import vinicius.joaomarceu.model.entities.Vendedor;

import com.github.hugoperlin.results.Resultado;

public class RepositorioVenda {

    private VendaDao vendaDao;
    private VendedorDao vendedorDao;
    private ClienteDao clienteDao;

    public RepositorioVenda(VendaDao vendaDao, VendedorDao vendedorDao, ClienteDao clienteDao) {
        this.vendaDao = vendaDao;
        this.vendedorDao = vendedorDao;
        this.clienteDao = clienteDao;
    }

    public Resultado cadastrarVenda(Cliente cliente, Vendedor vendedor, List<Tenis> tenis){
         if(vendedor == null){
            return Resultado.erro("Coloque 1 vendedor");
        }
        if(cliente == null){
            return Resultado.erro("Coloque 1 cliente");
        }
        if(tenis.size()==0){
            return Resultado.erro("Coloque 1 musica");
        }

        Venda venda = new Venda(cliente, vendedor);
        venda.setTenis(tenis);

        return vendaDao.criar(venda);
    }

    public Resultado listar(){
        Resultado resultado = vendaDao.listar();

        if(resultado.foiSucesso()){
            List<Venda> lista = (List)resultado.comoSucesso().getObj();

            for(Venda v: lista){
                Resultado r1 = vendedorDao.buscarVendedor(v.getVendedor().getCpf());
                if(r1.foiErro()){
                    return r1;
                }
                Vendedor vendedor = (Vendedor)r1.comoSucesso().getObj();
                v.setVendedor(vendedor);

                Resultado r2 = clienteDao.buscarCliente(v.getCliente().getCpf());
                if(r2.foiErro()){
                    return r2;
                }
                Cliente cliente = (Cliente)r2.comoSucesso().getObj();
                v.setCliente(cliente);
            }
        }

        return resultado;
    }

}