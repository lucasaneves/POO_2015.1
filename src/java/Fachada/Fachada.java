package Fachada;


import Entidades.Produto;
import Exceptions.ErroInternoException;
import Negocio.CadastroProdutos;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class Fachada {

    @EJB
    private CadastroProdutos cadProdutos;
    
    public Fachada(){
        
    }
  
   
 // PRODUTOS
    public void adicionarProduto(Produto produto) throws ErroInternoException{
        this.cadProdutos.adicionarProduto(produto);
    }
    
}
