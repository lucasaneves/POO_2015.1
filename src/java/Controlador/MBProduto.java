package Controlador;

import Entidades.ItemPedido;
import Entidades.Mercado;
import Entidades.Produto;
import Exceptions.ErroInternoException;
import Exceptions.MercadoInexistenteException;
import Exceptions.ProdutoInexistenteException;
import Exceptions.UsuarioInexistenteException;
import Fachada.Fachada;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@ManagedBean
@Named(value = "mBProduto")
@SessionScoped
public class MBProduto implements Serializable {
    
    private Produto produto;
    private ItemPedido itempedido;
    private int quantidade;
    private Mercado m;
    private long codigoMercdo;
    private String NomeMercado;
    
    public MBProduto() {
        this.produto = new Produto();
    }
    
    @EJB
    private Fachada fachada;
    
    public Produto getProduto() {
        return produto;
    }
    
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    public Fachada getFachada() {
        return fachada;
    }
    
    public void setFachada(Fachada fachada) {
        this.fachada = fachada;
    }
    
    public ItemPedido getItempedido() {
        return itempedido;
    }
    
    public void setItempedido(ItemPedido itempedido) {
        this.itempedido = itempedido;
    }
    
    public int getQuantidade() {
        return quantidade;
    }
    
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public Mercado getM() {
        return m;
    }
    
    public void setM(Mercado m) {
        this.m = m;
    }
    
    public long getCodigoMercdo() {
        return codigoMercdo;
    }
    
    public void setCodigoMercdo(long codigoMercdo) {
        this.codigoMercdo = codigoMercdo;
    }
    
    public String getNomeMercado() {
        return NomeMercado;
    }
    
    public void setNomeMercado(String NomeMercado) {
        this.NomeMercado = NomeMercado;
    }
    
    public String adicionarProduto() throws ErroInternoException, MercadoInexistenteException {
        try {
            Mercado m1 = this.fachada.buscarMercadoCodigo(codigoMercdo);
            Produto p = new Produto(produto.getNome(), produto.getValor(), produto.getMarca(), m1);
            this.fachada.adicionarProduto(p);
            return "MercadoIndex.xhtml";
        } catch (ErroInternoException e) {
            e.printStackTrace();
            return "ErroInterno.xhtml";
        }
    }

    public String adicionarCarrinho() throws ErroInternoException {
        try {
            Produto p = new Produto(produto.getNome(), produto.getValor(), produto.getMarca(), getM());
            itempedido = new ItemPedido(getQuantidade(), p);
            this.fachada.adicionarItemPedido(itempedido);
            return "sucesso.xhtml";
        } catch (ErroInternoException e) {
            e.printStackTrace();
            return "ErroInterno.xhtml";
        }
    }
    
    public List<Produto> getlistarProdutos() {
        try {
            return this.fachada.listarProdutos();
        } catch (ErroInternoException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    public String buscarProdutoCodigo() throws ErroInternoException, ProdutoInexistenteException {
        try {
            this.fachada.buscarProdutoCodigo(produto.getCodigo());
            return "sucesso.xhtml";
        } catch (ErroInternoException z) {
            return "ErroInterno.xhtml";
        } catch (ProdutoInexistenteException zz) {
            return "ProdutoInexistente.xhtml";
        }
    }
    
    public String buscarProdutoNome() throws ErroInternoException, ProdutoInexistenteException {
        try {
            this.fachada.buscarProdutoNome(produto.getNome());
            return "UsuarioBuscarProdutoList.xhtml";
        } catch (ErroInternoException z) {
            return "ErroInterno.xhtml";
        } catch (ProdutoInexistenteException zz) {
            return "ProdutoInexistente.xhtml";
        }
    }
    public List<Produto> getListbuscarProdutoNome() throws ErroInternoException, ProdutoInexistenteException {
        try {
           return this.fachada.buscarProdutoNome(produto.getNome());
           
        } catch (ErroInternoException z) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Erro Interno, tente novamente mais tarde!"));
            return null;
        } catch (ProdutoInexistenteException zz) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Produto não cadastrado em nossos sistemas!"));
            return null;
        }
    }
    
    public String buscarProdutoMarca() throws ErroInternoException, ProdutoInexistenteException {
        try {
            this.fachada.buscarProdutoMarca(produto.getMarca());
            return "sucesso.xhtml";
        } catch (ErroInternoException z) {
            return "ErroInterno.xhtml";
        } catch (ProdutoInexistenteException zz) {
            return "ProdutoInexistente.xhtml";
        }
    }
    
    public String removerProdutoCodigo() throws ErroInternoException, ProdutoInexistenteException {
        try {
            this.fachada.removerProdutoCodigo(produto.getCodigo());
            return "MercadoIndex.xhtml";
        } catch (ErroInternoException e) {
            e.printStackTrace();
            return "ErroInterno.xhtml";
        } catch (ProdutoInexistenteException ex) {
            return "ProdutoInexistente.xhtml";
        }
    }
    
    public String atualizarProduto() throws ErroInternoException, ProdutoInexistenteException {
        try {
            Produto att = new Produto(produto.getNome(), produto.getValor(), produto.getMarca(), getM());
            this.fachada.atualizarProduto(att);
            return "sucesso.xhtml";
        } catch (ErroInternoException exz) {
            return "ErroInterno.xhtml";
        } catch (ProdutoInexistenteException ez) {
            return "ProdutoInexistente.xhtml";
        }
    }
    
    
    
}
