package Entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItemPedido implements Serializable {

    private long codigo;
    private int quantidade;
    private Produto produto;

    //Construtor vazio
    public ItemPedido() {

    }

    //Construtor 
    public ItemPedido(int quantidade, Produto produto) {
        
        this.quantidade = quantidade;
        this.produto = produto;
    }

    //Comparação de objetos
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o.getClass() != ItemPedido.class) {
            return false;
        }
        ItemPedido ip = (ItemPedido) o;

        return (this.codigo == ip.codigo && this.quantidade == ip.quantidade && this.produto.equals(ip.produto));
    }

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @ManyToOne
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
