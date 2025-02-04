package vinicius.joaomarceu;

import io.github.hugoperlin.navigatorfx.BaseAppNavigator;
import io.github.hugoperlin.navigatorfx.ScreenRegistryFXML;
import vinicius.joaomarceu.controllers.CadastrarCategoria;
import vinicius.joaomarceu.controllers.CadastrarCliente;
import vinicius.joaomarceu.controllers.CadastrarTenis;
import vinicius.joaomarceu.controllers.CadastrarVenda;
import vinicius.joaomarceu.controllers.CadastrarVendedor;
import vinicius.joaomarceu.controllers.CategoriaTela;
import vinicius.joaomarceu.controllers.ClienteTela;
import vinicius.joaomarceu.controllers.ListarCategoria;
import vinicius.joaomarceu.controllers.ListarCliente;
import vinicius.joaomarceu.controllers.ListarTenis;
import vinicius.joaomarceu.controllers.ListarVendedor;
import vinicius.joaomarceu.controllers.Principal;
import vinicius.joaomarceu.controllers.TenisTela;
import vinicius.joaomarceu.controllers.VendedorTela;
import vinicius.joaomarceu.daos.CategoriaDao;
import vinicius.joaomarceu.daos.ClienteDao;
import vinicius.joaomarceu.daos.FabricaConexoes;
import vinicius.joaomarceu.daos.JDBCCategoriaDao;
import vinicius.joaomarceu.daos.JDBCClienteDao;
import vinicius.joaomarceu.daos.JDBCTenisDao;
import vinicius.joaomarceu.daos.JDBCVendaDao;
import vinicius.joaomarceu.daos.JDBCVendedorDao;
import vinicius.joaomarceu.daos.TenisDao;
import vinicius.joaomarceu.daos.VendaDao;
import vinicius.joaomarceu.daos.VendedorDao;
import vinicius.joaomarceu.repositories.RepositorioCategoria;
import vinicius.joaomarceu.repositories.RepositorioCliente;
import vinicius.joaomarceu.repositories.RepositorioTenis;
import vinicius.joaomarceu.repositories.RepositorioVenda;
import vinicius.joaomarceu.repositories.RepositorioVendedor;

/**
 * JavaFX App
 */
public class App extends BaseAppNavigator {


    private VendedorDao vendedorDao = new JDBCVendedorDao(FabricaConexoes.getInstance());
    private RepositorioVendedor repositorioVendedor = new RepositorioVendedor(vendedorDao);

    private ClienteDao clienteDao = new JDBCClienteDao(FabricaConexoes.getInstance());
    private RepositorioCliente  repositorioCliente = new RepositorioCliente(clienteDao);

    private CategoriaDao categoriaDao = new JDBCCategoriaDao(FabricaConexoes.getInstance());
    private RepositorioCategoria repositorioCategoria = new RepositorioCategoria(categoriaDao);

    private TenisDao tenisDao = new JDBCTenisDao(FabricaConexoes.getInstance());
    private RepositorioTenis repositorioTenis = new RepositorioTenis(tenisDao, categoriaDao);

    private VendaDao vendaDao = new JDBCVendaDao(FabricaConexoes.getInstance());
    private RepositorioVenda repositorioVenda = new RepositorioVenda(vendaDao, vendedorDao, clienteDao);

    @Override
    public void init() throws Exception {
        super.init();

    }


    @Override
    public String getHome() {
        return "PRINCIPAL";
    }


    @Override
    public String getAppTitle() {
        return "Projeto Integrador";
    }

    @Override
    public void registrarTelas() {
        registraTela("PRINCIPAL", new ScreenRegistryFXML(App.class, "principal.fxml", o->new Principal()));

        registraTela("CATEGORIA", new ScreenRegistryFXML(App.class, "categoria.fxml", o-> new CategoriaTela()));
        registraTela("CADASTRARCATEGORIA", new ScreenRegistryFXML(App.class, "cadastrar_categoria.fxml", o-> new CadastrarCategoria(repositorioCategoria)));
        registraTela("LISTARCATEGORIA", new ScreenRegistryFXML(App.class, "listar_categoria.fxml", o-> new ListarCategoria(repositorioCategoria)));

        registraTela("TENIS", new ScreenRegistryFXML(App.class, "tenis.fxml", o-> new TenisTela() ));
        registraTela("CADASTRARTENIS", new ScreenRegistryFXML(App.class, "cadastrar_tenis.fxml", o-> new CadastrarTenis(repositorioTenis, repositorioCategoria)));
        registraTela("LISTARTENIS", new ScreenRegistryFXML(App.class, "listar_tenis.fxml", o-> new ListarTenis(repositorioTenis)));

        registraTela("CLIENTE", new ScreenRegistryFXML(App.class, "cliente.fxml", o-> new ClienteTela()));
        registraTela("CADASTRARCLIENTE", new ScreenRegistryFXML(App.class, "cadastrar_cliente.fxml", o-> new CadastrarCliente(repositorioCliente)));
        registraTela("LISTARCLIENTE", new ScreenRegistryFXML(App.class, "listar_cliente.fxml", o-> new ListarCliente(repositorioCliente)));

        registraTela("VENDEDOR", new ScreenRegistryFXML(App.class, "vendedor.fxml", o -> new VendedorTela()));
        registraTela("CADASTRARVENDEDOR", new ScreenRegistryFXML(App.class, "cadastrar_vendedor.fxml", o -> new CadastrarVendedor(repositorioVendedor)));
        registraTela("LISTARVENDEDOR", new ScreenRegistryFXML(App.class, "listar_vendedor.fxml", o -> new ListarVendedor(repositorioVendedor)));

        registraTela("VENDA", new ScreenRegistryFXML(App.class, "venda.fxml", o-> new CadastrarVenda(repositorioVenda, repositorioVendedor, repositorioCliente, repositorioTenis)));
    }

}