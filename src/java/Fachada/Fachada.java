package Fachada;

import Entidades.ItemPedido;
import Entidades.Mercado;
import Entidades.Pedido;
import Entidades.Produto;
import Entidades.Usuario;
import Exceptions.ErroInternoException;
import Exceptions.ItemPedidoInexistenteException;
import Exceptions.MercadoInexistenteException;
import Exceptions.PedidoInexistenteException;
import Exceptions.ProdutoInexistenteException;
import Exceptions.UsuarioExistenteException;
import Exceptions.UsuarioInexistenteException;
import Negocio.CadastroItemPedido;
import Negocio.CadastroMercado;
import Negocio.CadastroPedido;
import Negocio.CadastroProdutos;
import Negocio.CadastroUsuarios;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class Fachada {

    @EJB
    private CadastroUsuarios cadUsuarios;
    @EJB
    private CadastroMercado cadMercado;
    @EJB
    private CadastroProdutos cadProdutos;
    @EJB
    private CadastroPedido cadPedidos;
    @EJB
    private CadastroItemPedido cadItensPedidos;
    
    public Fachada(){
        
    }
    
    //###############################  USUARIO  ##################################//
    
    public void adicionarUsuario(Usuario usuario) throws ErroInternoException, UsuarioExistenteException{
        this.cadUsuarios.adicionarUsuario(usuario);
    }
    public void removerUsuarioCodigo(long codigo) throws ErroInternoException,UsuarioInexistenteException{
        this.cadUsuarios.removerUsuarioCodigo(codigo);
    }
    public void removerUsuarioEmail(String email) throws ErroInternoException, UsuarioInexistenteException{
        this.cadUsuarios.removerUsuarioEmail(email);
    }
    public List<Usuario> listarUsuarios() throws ErroInternoException{
        return this.cadUsuarios.listarUsuarios();
    }
    public Usuario buscarUsuarioCodigo(long codigo) throws ErroInternoException, UsuarioInexistenteException{
        return this.cadUsuarios.buscarUsuarioCodigo(codigo);
    }
    public Usuario buscarUsuarioEmail(String email) throws ErroInternoException, UsuarioInexistenteException{
        return this.cadUsuarios.buscarUsuarioEmail(email);
    }
    public List<Usuario> buscarUsuarioNome(String nome) throws ErroInternoException{
        return this.cadUsuarios.buscarUsuarioNome(nome);
    }
    public void atualizarUsuario(Usuario usuario) throws ErroInternoException, UsuarioInexistenteException{
        this.cadUsuarios.atualizarUsuario(usuario);
    }
    public List<Usuario> buscarPedidoUsuario(Pedido p) throws ErroInternoException{
        return this.cadUsuarios.buscarPedidoUsuario(p);
    }
    public Usuario autenticarUsuario(String email) throws ErroInternoException, UsuarioInexistenteException{
        return this.cadUsuarios.autenticarUsuario(email);
    }
    
    
    // ################################  MERCADO  #####################################//
    public void adicionarMercado(Mercado mercado) throws ErroInternoException{
        this.cadMercado.adicionarMercado(mercado);
    }
    public void removerMercadoCodigo(long codigo) throws ErroInternoException,MercadoInexistenteException{
        this.cadMercado.buscarMercadoCodigo(codigo);
    }
    public void removerMercadoCnpj(String cnpj) throws ErroInternoException, MercadoInexistenteException{
        this.cadMercado.removerMercadoCnpj(cnpj);
    }
    public List<Mercado> listarMercados() throws ErroInternoException{
        return this.cadMercado.listarMercados();
    }
    public List<Produto> buscarProdutoMercado(Mercado mer) throws ErroInternoException{
        return this.cadMercado.buscarProdutoMercado(mer);
    }
    public Mercado buscarMercadoCodigo(long codigo) throws ErroInternoException, MercadoInexistenteException{
        return this.cadMercado.buscarMercadoCodigo(codigo);
    }
    public Mercado buscarMercadoCnpj(String cnpj) throws ErroInternoException, MercadoInexistenteException{
        return this.cadMercado.buscarMercadoCnpj(cnpj);
    }
    public List<Mercado> buscarMercadoNome(String nome) throws ErroInternoException{
        return this.cadMercado.buscarMercadoNome(nome);
    }
    public void atualizarMercado(Mercado mercado) throws ErroInternoException, MercadoInexistenteException{
        this.cadMercado.atualizarMercado(mercado);
    }
    
    public List<Pedido> buscarPedidoMercado (Mercado m) throws ErroInternoException{
        return this.cadMercado.buscarPedidoMercado(m);
    }
    
    
    // ##################################  PRODUTO  ############################################//
    public void adicionarProduto(Produto produto) throws ErroInternoException{
        this.cadProdutos.adicionarProduto(produto);
    }
    public List<Produto> listarProdutos() throws ErroInternoException{
        return this.cadProdutos.listarProdutos();
    }
    public Produto buscarProdutoCodigo(long codigo) throws ErroInternoException, ProdutoInexistenteException{
        return this.cadProdutos.buscarProdutoCodigo(codigo);
    }
    public List<Produto> buscarProdutoNome (String nome) throws ErroInternoException, ProdutoInexistenteException{
        return this.cadProdutos.buscarProdutoNome(nome);
    }
    public List<Produto> buscarProdutoMarca (String marca) throws ErroInternoException, ProdutoInexistenteException{
        return this.cadProdutos.buscarProdutoMarca(marca);
    }
    
    public void removerProdutoCodigo(long codigo) throws ErroInternoException, ProdutoInexistenteException{
        this.cadProdutos.removerProdutoCodigo(codigo);
    }
    public void atualizarProduto(Produto produto) throws ErroInternoException, ProdutoInexistenteException{
        this.cadProdutos.atualizarProduto(produto);
    }
    
    
    //#################################  PEDIDOS  ##################################
    public void adicionarPedido(Pedido pedido) throws ErroInternoException{
        this.cadPedidos.adicionarPedido(pedido);
    }
    public List<Pedido> listarPedidos() throws ErroInternoException{
        return this.cadPedidos.listarPedidos();
      }
    public Pedido buscarPedidoCodigo(long codigo) throws ErroInternoException, PedidoInexistenteException{
        return this.cadPedidos.buscarPedidoCodigo(codigo);
    }
    public void removerPedidoCodigo(long codigo) throws ErroInternoException, PedidoInexistenteException{
        this.cadPedidos.removerPedidoCodigo(codigo);
    }
    public void atualizarPedido(Pedido pedido) throws ErroInternoException, PedidoInexistenteException{
        this.cadPedidos.atualizarPedido(pedido);
    }
    public List<Pedido> buscarPedidoItemPedido(ItemPedido p) throws ErroInternoException{
        return this.cadPedidos.buscarPedidoItemPedido(p);
    }
 
    
    //#################################  ItensPEDIDO  ##################################
    public void adicionarItemPedido(ItemPedido ipedido) throws ErroInternoException{
        this.cadItensPedidos.adicionarItemPedido(ipedido);
    }
    public List<ItemPedido> listarItensPedidos() throws ErroInternoException{
        return this.cadItensPedidos.listarItensPedido();
                }
    public ItemPedido buscarItemPedidoCodigo(long codigo) throws ErroInternoException, ItemPedidoInexistenteException{
        return this.cadItensPedidos.buscarItemPedidoCodigo(codigo);
    }
    public void removerItemPedidoCodigo(long codigo) throws ErroInternoException, ItemPedidoInexistenteException{
        this.cadItensPedidos.removerItemPedidoCodigo(codigo);
    }
    public void atualizarItemPedido(ItemPedido ipedido) throws ErroInternoException, ItemPedidoInexistenteException{
        this.cadItensPedidos.atualizarItemPedido(ipedido);
    }
    
    
    
}
