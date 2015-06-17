package Persistencia;

import Entidades.Produto;
import Exceptions.ErroInternoException;
import Exceptions.ProdutoInexistenteException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
/** essa classe ira herdar os metodos do repositorio e lancar no banco de dados
 * 
 * @author amaericanas
 */
@Stateless
public class RepositorioProdutoJPA implements RepositorioProduto {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void adicionarProduto(Produto produto) throws ErroInternoException {
        try {
            this.em.persist(produto);
        } catch (Exception e) {
            throw new ErroInternoException(e);

        }
    }

    @Override
    public List<Produto> listarProdutos() throws ErroInternoException {
        try {
            TypedQuery<Produto> consulta = this.em.createQuery("select p from Produto p", Produto.class);
            return consulta.getResultList();

        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

   

    @Override
    public Produto buscarProdutoCodigo(long codigo) throws ErroInternoException, ProdutoInexistenteException {
        Produto p = null;
        try {
            p = this.em.find(Produto.class, codigo);  //buscar pela chave primaria é o find se for por outro atributo já é outro.
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
        if (p == null) {
            throw new ProdutoInexistenteException();
        }
        return p;
    }

    @Override
    public List<Produto> buscarProdutoNome(String nome) throws ErroInternoException, ProdutoInexistenteException {
        try {
            TypedQuery<Produto> consulta = this.em.createQuery("select p from Produto p where p.nome like :nome", Produto.class);
            consulta.setParameter("nome", "%" + nome + "%");
            return consulta.getResultList();

        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    @Override
    public List<Produto> buscarProdutoMarca(String marca) throws ErroInternoException, ProdutoInexistenteException {
        try {
            TypedQuery<Produto> consulta = this.em.createQuery("select p from Produto p where p.marca like :marca", Produto.class);
            consulta.setParameter("marca", "%" + marca + "%");
            return consulta.getResultList();

        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    @Override
    public void removerProdutoCodigo(long codigo) throws ErroInternoException, ProdutoInexistenteException {
        Produto p = buscarProdutoCodigo(codigo);
        try {
            this.em.remove(p);
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    @Override
    public void atualizarProduto(Produto produto) throws ErroInternoException, ProdutoInexistenteException {
        Produto p = buscarProdutoCodigo(produto.getCodigo());
        p.setNome(produto.getNome());
        p.setMarca(produto.getMarca());
        p.setValor(produto.getValor());

        try {
            this.em.merge(p);
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

}
