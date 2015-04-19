/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Jhonatha
 */
@Entity
public class Produto implements Serializable {

    private long codigo;
    private String nome;
    private float valor;
    private String marca;
    private Mercado mercado;
    
    // trocar por classe private String categoria;

    public Produto() {

    }

    public Produto(String nome, float valor, String marca) {
        this.nome = nome;
        this.valor = valor;
        this.marca = marca;
        
    }

    //Comparação de objetos
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o.getClass() != Produto.class) {
            return false;
        }
        Produto p = (Produto) o;

        return (p.codigo == this.codigo && this.nome.equals(p.nome) && this.valor == p.valor && this.marca.equals(p.marca)
                );
    }

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @OneToOne
    public Mercado getMercado() {
        return mercado;
    }

    public void setMercado(Mercado mercado) {
        this.mercado = mercado;
    }
    

   
}
