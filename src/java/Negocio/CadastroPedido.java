package Negocio;

import Entidades.ItemPedido;
import Entidades.Pedido;
import Exceptions.ErroInternoException;
import Exceptions.PedidoInexistenteException;
import Persistencia.RepositorioPedido;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class CadastroPedido {

    @EJB
    private RepositorioPedido repPedidos;

    public void adicionarPedido(Pedido pedido) throws ErroInternoException {
        try {
            Pedido ped = repPedidos.buscarPedidoCodigo(pedido.getCodigo());
            throw new PedidoInexistenteException();

        } catch (PedidoInexistenteException e) {
            this.repPedidos.adicionarPedido(pedido);
        }
    }

    public List<Pedido> listarPedidos() throws ErroInternoException {
        return this.repPedidos.listarPedidos();
    }

    public Pedido buscarPedidoCodigo(long codigo) throws ErroInternoException, PedidoInexistenteException {
        return this.repPedidos.buscarPedidoCodigo(codigo);
    }

    public void removerPedidoCodigo(long codigo) throws ErroInternoException, PedidoInexistenteException {
        this.repPedidos.removerPedidoCodigo(codigo);
    }

    public void atualizarPedido(Pedido pedido) throws ErroInternoException, PedidoInexistenteException {

        Pedido ped = this.repPedidos.buscarPedidoCodigo(pedido.getCodigo());
        // se tiver alguma regra
        this.repPedidos.atualizarPedido(pedido);
    }

    public List<Pedido> buscarPedidoItemPedido(ItemPedido p) throws ErroInternoException {
        return this.repPedidos.buscarPedidoItemPedido(p);
    }

}
