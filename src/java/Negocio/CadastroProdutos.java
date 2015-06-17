package Negocio;

import Entidades.Produto;
import Exceptions.ErroInternoException;
import Exceptions.ProdutoInexistenteException;
import Persistencia.RepositorioProduto;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class CadastroProdutos {
    
    @EJB
    private RepositorioProduto repProdutos;
    
    public void adicionarProduto(Produto produto) throws ErroInternoException{
        try {
            Produto prod = repProdutos.buscarProdutoCodigo(produto.getCodigo());
            throw new ProdutoInexistenteException();

        } catch (ProdutoInexistenteException e) {
            this.repProdutos.adicionarProduto(produto);
        }
    }
    public List<Produto> listarProdutos() throws ErroInternoException{
        return this.repProdutos.listarProdutos();
    }
    public Produto buscarProdutoCodigo(long codigo) throws ErroInternoException, ProdutoInexistenteException{
        return this.repProdutos.buscarProdutoCodigo(codigo);
    }
    public List<Produto> buscarProdutoNome (String nome) throws ErroInternoException, ProdutoInexistenteException{
        return this.repProdutos.buscarProdutoNome(nome);
    }
    public List<Produto> buscarProdutoMarca (String marca) throws ErroInternoException, ProdutoInexistenteException{
        return this.repProdutos.buscarProdutoMarca(marca);
    }
    
    public void removerProdutoCodigo(long codigo) throws ErroInternoException, ProdutoInexistenteException{
        this.repProdutos.removerProdutoCodigo(codigo);
    }
    public void atualizarProduto(Produto produto) throws ErroInternoException, ProdutoInexistenteException{
       Produto prod = this.repProdutos.buscarProdutoCodigo(produto.getCodigo());
        // se tiver alguma regra
        this.repProdutos.atualizarProduto(produto); 
    }
    
}
