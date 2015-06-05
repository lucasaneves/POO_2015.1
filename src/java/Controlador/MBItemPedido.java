package Controlador;

import Entidades.ItemPedido;
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
@Named(value = "mBItemPedido")
@SessionScoped
public class MBItemPedido implements Serializable{
    private ItemPedido itempedido;
    
    public MBItemPedido(){
        this.itempedido = new ItemPedido();
    }
    
    @EJB
    private Fachada fachada;
    
    

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

    
    
    public String adicionarItemPedido() throws ErroInternoException{
        try {
            ItemPedido ip = new ItemPedido( itempedido.getQuantidade(), itempedido.getProduto());
            this.fachada.adicionarItemPedido(ip);
            return "sucesso.xhtml";
        } catch (ErroInternoException e) {
            e.printStackTrace();
            return "ErroInterno.xhtml";
        } 
    }
    
    public List<ItemPedido> getlistansItemPedidos() {
        try {
            return this.fachada.listarItensPedidos();
        } catch (ErroInternoException e) {
            e.printStackTrace();
            return null;
        }
    }
}
