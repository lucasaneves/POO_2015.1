package Persistencia;

import Entidades.Produto;
import Exceptions.ErroInternoException;
import Exceptions.ProdutoInexistenteException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;

@Local
/**Esta classe contem os metodos que serão usados no repositorioJPA
 * 
 */
public interface RepositorioProduto extends Serializable {
    public void adicionarProduto(Produto produto) throws ErroInternoException;
    public List<Produto> listarProdutos() throws ErroInternoException;
    public Produto buscarProdutoCodigo(long codigo) throws ErroInternoException, ProdutoInexistenteException;
    public List<Produto> buscarProdutoNome (String nome) throws ErroInternoException, ProdutoInexistenteException;
    public List<Produto> buscarProdutoMarca (String marca) throws ErroInternoException, ProdutoInexistenteException;
    public void removerProdutoCodigo(long codigo) throws ErroInternoException, ProdutoInexistenteException;
    public void atualizarProduto(Produto produto) throws ErroInternoException, ProdutoInexistenteException;
    
   
    
}
