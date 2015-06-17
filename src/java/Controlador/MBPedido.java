package Controlador;

import Entidades.ItemPedido;
import Entidades.Mercado;
import Entidades.Pedido;
import Entidades.Produto;
import Exceptions.ErroInternoException;
import Fachada.Fachada;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

@ManagedBean
@Named(value = "mBPedido")
@SessionScoped
public class MBPedido implements Serializable{
    private Pedido pedido;
    private Produto produto;
    private Mercado mercado;
    private List<ItemPedido> itempedido;
    private int quantidade;
    
    public MBPedido(){
        this.pedido = new Pedido();
    }
    
    @EJB
    private Fachada fachada;
    
    

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Mercado getMercado() {
        return mercado;
    }

    public void setMercado(Mercado mercado) {
        this.mercado = mercado;
    }

    public List<ItemPedido> getItempedido() {
        return itempedido;
    }

    public void setItempedido(List<ItemPedido> itempedido) {
        this.itempedido = itempedido;
    }

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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    /**Este metodo ira adicionar o pedido
     * 
     * @return
     * @throws ErroInternoException 
     */
    
    public String adicionarPedido() throws ErroInternoException{
        try {
            
            Pedido p = new Pedido(mercado, itempedido);
            this.fachada.adicionarPedido(p);
            return "sucesso.xhtml";
        } catch (ErroInternoException e) {
            e.printStackTrace();
            return "ErroInterno.xhtml";
        } 
    }
}
