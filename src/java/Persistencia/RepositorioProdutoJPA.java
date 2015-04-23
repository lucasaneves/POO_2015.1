
package Persistencia;

import Entidades.Produto;
import Exceptions.ErroInternoException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


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


}
