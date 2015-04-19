/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidades;

import com.sun.jmx.remote.internal.ArrayQueue;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Jhonatha
 */
@Entity
public class Mercado implements Serializable {
    
    private long codigo;
    private String nome;
    private String cnpj;
    private String endereco;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private String telefone;
    private String senha;
    private List<Produto> produto;
    private List<Pedido> pedidos; //mappedBy no ManyToOne
    
    public Mercado() {
    }

    public Mercado(String nome, String cnpj, String endereco, String bairro, String cidade, String uf, String cep, String telefone, String senha /*, List<Produto> produto, List<Pedido> pedidos*/) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
        this.telefone = telefone;
        this.senha = senha;
        this.produto = new ArrayList<>();
        this.pedidos = new ArrayList<>();
    }
    
    

    //Comparação de objetos
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o.getClass() != Mercado.class) {
            return false;
        }
        Mercado m = (Mercado) o;
        
        return (m.codigo == this.codigo && this.nome.equals(m.nome) && this.cnpj.equals(m.cnpj) && this.endereco.equals(m.endereco)
                && this.produto.equals(m.produto) && this.pedidos.equals(m.pedidos));
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
    
    public String getCnpj() {
        return cnpj;
    }
    
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    public String getEndereco() {
        return endereco;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    
    
    @OneToMany
    public List<Produto> getProduto() {
        return produto;
    }
    
    public void setProduto(List<Produto> produto) {
        this.produto = produto;
    }

    @OneToMany(mappedBy = "mercado")
    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
}
