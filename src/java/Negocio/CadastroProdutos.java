
package Negocio;

import Entidades.Produto;
import Exceptions.ErroInternoException;
import Exceptions.ProdutoInexistenteException;
import Persistencia.RepositorioProduto;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class CadastroProdutos {
    
 @EJB
private RepositorioProduto repProdutos;
    
    public void adicionarProduto(Produto produto) throws ErroInternoException{
        try {
//            Produto prod = repProdutos.buscarProdutoCodigo(produto.getCodigo());
            throw new ProdutoInexistenteException();

        } catch (ProdutoInexistenteException e) {
            this.repProdutos.adicionarProduto(produto);
        }
    }
       
}
