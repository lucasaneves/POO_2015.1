package Persistencia;

import Entidades.ItemPedido;
import Exceptions.ErroInternoException;
import Exceptions.ItemPedidoInexistenteException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
/**Esta classe herda os metodos do repositorio e lanca no banco de dados
 * 
 */
public class RepositorioItemPedidoJPA implements RepositorioItemPedido{

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void adicionarItemPedido(ItemPedido ip) throws ErroInternoException {
        try {
            this.em.persist(ip);
        } catch (Exception e) {
            throw new ErroInternoException(e);

        }
    }

    @Override
    public List<ItemPedido> listarItensPedido() throws ErroInternoException {
        try {
            TypedQuery<ItemPedido> consulta = this.em.createQuery("select p from ItemPedido p", ItemPedido.class);
            return consulta.getResultList();

        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    @Override
    public ItemPedido buscarItemPedidoCodigo(long codigo) throws ErroInternoException, ItemPedidoInexistenteException {
        ItemPedido c = null;
        try {
            c = this.em.find(ItemPedido.class, codigo);  //buscar pela chave primaria é o find se for por outro atributo já é outro.
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
        if (c == null) {
            throw new ItemPedidoInexistenteException();
        }
        return c;
    }

    @Override
    public void removerItemPedidoCodigo(long codigo) throws ErroInternoException, ItemPedidoInexistenteException {
        ItemPedido ip = buscarItemPedidoCodigo(codigo);
        try {
            this.em.remove(ip);
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    @Override
    public void atualizarItemPedido(ItemPedido ip) throws ErroInternoException, ItemPedidoInexistenteException {
        ItemPedido iped = buscarItemPedidoCodigo(ip.getCodigo());
        //Perguntar ao professor se isso estaria correto.
        iped.setQuantidade(ip.getQuantidade());
        

    }
    
}
