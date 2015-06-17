package Persistencia;

import Entidades.ItemPedido;
import Entidades.Pedido;
import Exceptions.ErroInternoException;
import Exceptions.PedidoInexistenteException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
/**Esta classe herda os metodos do repositorio e lanca no banco de dados
 * 
 */
public class RepositorioPedidoJPA implements RepositorioPedido {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void adicionarPedido(Pedido pedido) throws ErroInternoException {
        try {
            this.em.persist(pedido);
        } catch (Exception e) {
            throw new ErroInternoException(e);

        }
    }

    @Override
    public List<Pedido> listarPedidos() throws ErroInternoException {
        try {
            TypedQuery<Pedido> consulta = this.em.createQuery("select p from Pedido p", Pedido.class);
            return consulta.getResultList();

        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    @Override
    public Pedido buscarPedidoCodigo(long codigo) throws ErroInternoException, PedidoInexistenteException {
        Pedido c = null;
        try {
            c = this.em.find(Pedido.class, codigo);  //buscar pela chave primaria é o find se for por outro atributo já é outro.
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
        if (c == null) {
            throw new PedidoInexistenteException();
        }
        return c;
    }

    @Override
    public void removerPedidoCodigo(long codigo) throws ErroInternoException, PedidoInexistenteException {
        Pedido p = buscarPedidoCodigo(codigo);
        try {
            this.em.remove(p);
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    @Override
    public void atualizarPedido(Pedido pedido) throws ErroInternoException, PedidoInexistenteException {
        Pedido ped = buscarPedidoCodigo(pedido.getCodigo());
        //Perguntar ao professor se isso estaria correto.
        ped.setMercado(pedido.getMercado());
        ped.setItempedido(pedido.getItempedido());

    }

    @Override
    public List<Pedido> buscarPedidoItemPedido(ItemPedido p) throws ErroInternoException {
        try {
            TypedQuery<Pedido> consulta = this.em.createQuery("select p from Pedido p join p.itemPedido ip where ip.codigo= :codigo", Pedido.class);
            consulta.setParameter("codigo", p.getCodigo());
            return consulta.getResultList();
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

}
