package Controlador;

import Entidades.ItemPedido;
import Entidades.Mercado;
import Entidades.Pedido;
import Entidades.Produto;
import Entidades.Usuario;
import Exceptions.ErroInternoException;
import Exceptions.UsuarioExistenteException;
import Exceptions.UsuarioInexistenteException;
import Fachada.Fachada;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.Path.Node;

@ManagedBean
@Named(value = "mBUsuario")
@SessionScoped
public class MBUsuario implements Serializable {

    private Usuario usuario;
    private Pedido ped;
    private Produto produto;
    private ItemPedido itempedido = new ItemPedido();
    //private ArrayList carrinhoCompras = new ArrayList<>();
    ArrayList<ItemPedido> itempedidoList = new ArrayList<>();
     ArrayList<ItemPedido> itempedidoNovo = new ArrayList();
   
    private float valorTotal;
    private int quantidade;

    public MBUsuario() {
        this.usuario = new Usuario();
        this.ped = new Pedido();
        this.produto = new Produto();
        
    }

    @EJB
    private Fachada fachada;

    private long remover;
    private String login;
    private long codigo;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Fachada getFachada() {
        return fachada;
    }

    public void setFachada(Fachada fachada) {
        this.fachada = fachada;
    }

    public long getRemover() {
        return remover;
    }

    public void setRemover(long remover) {
        this.remover = remover;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String adicionarUsuario() throws ErroInternoException, UsuarioExistenteException {
        try {
            Usuario u = new Usuario(usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getDataNascimento(), usuario.getCpf(), usuario.getEndereco(), usuario.getBairro(), usuario.getCidade(), usuario.getUf(), usuario.getCep(), usuario.getTelefone());
            this.fachada.adicionarUsuario(u);
            return "index.xhtml";
        } catch (ErroInternoException e) {
            e.printStackTrace();
            return "ErroInterno.xhtml";
        } catch (UsuarioExistenteException ex) {
            return "UsuarioExistente.xhtml";
        }
        //this.cadUsuarios.adicionarUsuario(usuario);
    }

    public String removerUsuarioCodigo() throws ErroInternoException, UsuarioInexistenteException {
        try {
            this.fachada.removerUsuarioCodigo(remover);
            return "sucesso.xhtml";
        } catch (ErroInternoException e) {
            e.printStackTrace();
            return "ErroInterno.xhtml";
        } catch (UsuarioInexistenteException ex) {
            return "UsuarioInexistente.xhtml";
        }

    }

    public String removerUsuarioEmail() throws ErroInternoException, UsuarioInexistenteException {
        try {
            this.fachada.removerUsuarioEmail(usuario.getEmail());
            return "UsuarioIndex.xhtml";
        } catch (ErroInternoException e) {
            e.printStackTrace();
            return "ErroInterno.xhtml";
        } catch (UsuarioInexistenteException ex) {
            return "UsuarioInexistente.xhtml";
        }

    }

    public List<Usuario> getListaTodos() {
        try {
            return this.fachada.listarUsuarios();
        } catch (ErroInternoException e) {
            e.printStackTrace();
            return null;
        }

    }

    public String buscarUsuarioCodigo() throws ErroInternoException, UsuarioInexistenteException {
        try {
            this.fachada.buscarUsuarioCodigo(getCodigo());
            return "sucesso.xhtml";
        } catch (ErroInternoException z) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", " Aconteceu um Erro interno, Tente novamente mais tarde!"));
            return null;
        } catch (UsuarioInexistenteException zz) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", " Este Usuario Não Existe!"));
            return null;
        }

    }

    public String buscarUsuarioEmail() throws ErroInternoException, UsuarioInexistenteException {
        try {

            this.fachada.buscarUsuarioEmail(usuario.getEmail());
            return "sucesso.xhtml";
        } catch (ErroInternoException z) {
            return "ErroInterno.xhtml";
        } catch (UsuarioInexistenteException zz) {
            return "UsuarioInexistente.xhtml";
        }
    }

    public String atualizarUsuario() throws ErroInternoException, UsuarioInexistenteException {
        try {
            Usuario att = new Usuario(usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getDataNascimento(), usuario.getCpf(), usuario.getEndereco(), usuario.getBairro(), usuario.getCidade(), usuario.getUf(), usuario.getCep(), usuario.getTelefone());
            this.fachada.atualizarUsuario(att);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", " Usuario Atualizado com Sucesso!"));
            return null;
        } catch (ErroInternoException exz) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", " Erro Interno! "));
            return null;
        } catch (UsuarioInexistenteException ez) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", " Este Usuario Não Existe!"));
            return null;
        }

    }

    public String autenticarUsuario() throws ErroInternoException, UsuarioInexistenteException {
        try {
            Usuario us = this.fachada.buscarUsuarioEmail(usuario.getEmail());
            if (us.getEmail().equals(usuario.getEmail()) && us.getSenha().equals(usuario.getSenha())) {
                setLogin(us.getNome());
                setCodigo(us.getCodigo());
                usuario.setEmail(us.getEmail());
                usuario.setCpf(us.getCpf());
                return "UsuarioIndex.xhtml";

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Login ou Senha Invalido!"));
                return null;
            }
        } catch (ErroInternoException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Login ou Senha Invalidos!"));
            return null;
        } catch (UsuarioInexistenteException ue) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Login ou Senha Invalido!"));
            return null;
        }
    }

    public String logoutUsuario() {
        setLogin(".");
        return "index.xhtml";
    }

    
    
    public String adicionarAoCarrinho() {
        try {
            ItemPedido ip = new ItemPedido(getQuantidade(), produto);
            itempedidoList.add(ip);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Produto adicionado ao Carrinho!"));
            return null;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Erro Interno!"));
            return null;
        }
    }

    public String removerCarrinho(ItemPedido itemPedido) {
        itempedidoList.remove(itemPedido);
        return null;
    }

    public String limparCampos() {
        itempedidoList.clear();
        return "UsuarioIndex.xhtml";

    }

    public List<ItemPedido> getCarrinhoCompras(){
       return itempedidoList;
    }


    public String finalizarPedido() throws ErroInternoException {
        itempedidoNovo.clear();
        try{
        for (ItemPedido iped : itempedidoList) {
            itempedidoNovo.add(iped);
        }
        Produto prod = itempedidoList.get(0).getProduto();
        Mercado m = prod.getMercado();
        Pedido p = new Pedido(m, itempedidoNovo);
        this.fachada.adicionarPedido(p);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Compra finalizada com Sucesso!"));
            itempedidoList.clear();
            itempedidoNovo.clear();
            return null;
        }catch (Exception  e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", " Ocorreu um erro tente novamente mais tarde!"));
            return null;
        }
    }
 
    //public List<Usuario> buscarPedidoUsuario(Pedido p) throws ErroInternoException{
    //   return this.cadUsuarios.buscarPedidoUsuario(p);
    //}
    public Pedido getPed() {
        return ped;
    }

    public void setPed(Pedido ped) {
        this.ped = ped;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

}
