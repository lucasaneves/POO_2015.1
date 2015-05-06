package Controlador;

import Entidades.ItemPedido;
import Entidades.Mercado;
import Entidades.Produto;
import Exceptions.ErroInternoException;
import Exceptions.MercadoInexistenteException;
import Fachada.Fachada;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
//            Mercado m1 = this.fachada.buscarMercadoCodigo(codigoMercdo);
            Produto p = new Produto(produto.getNome(), produto.getValor(), produto.getMarca());
            this.fachada.adicionarProduto(p);
            return "sucesso.xhtml";
        } catch (ErroInternoException e) {
            e.printStackTrace();
            return "ErroInterno.xhtml";
        }
    }

          
}
