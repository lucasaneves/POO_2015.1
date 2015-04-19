/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Jhonatha
 */
@Entity
public class Pedido implements Serializable {

    private long codigo;
    private Mercado mercado;
    private List<ItemPedido> itempedido;

    //Construtor vazio
    public Pedido() {

    }

    public Pedido(Mercado mercado, List<ItemPedido> itempedido) {
        
        this.mercado = mercado;
        this.itempedido = itempedido;
    }

    //Comparação de objetos
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o.getClass() != Pedido.class) {
            return false;
        }
        Pedido p = (Pedido) o;

        return (p.codigo == this.codigo && this.mercado.equals(p.mercado) && this.itempedido.equals(p.itempedido));
    }

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    @ManyToOne
    public Mercado getMercado() {
        return mercado;
    }

    public void setMercado(Mercado mercado) {
        this.mercado = mercado;
    }

    @OneToMany(cascade = CascadeType.ALL)
    public List<ItemPedido> getItempedido() {
        return itempedido;
    }

    public void setItempedido(List<ItemPedido> itempedido) {
        this.itempedido = itempedido;
    }

}
