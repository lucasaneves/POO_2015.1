package Persistencia;


import Entidades.ItemPedido;
import Entidades.Pedido;
import Exceptions.ErroInternoException;
import Exceptions.PedidoInexistenteException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;

@Local
public interface RepositorioPedido extends Serializable {
    public void adicionarPedido(Pedido pedido) throws ErroInternoException;
    public List<Pedido> listarPedidos() throws ErroInternoException;
    public Pedido buscarPedidoCodigo(long codigo) throws ErroInternoException, PedidoInexistenteException;
    public void removerPedidoCodigo(long codigo) throws ErroInternoException, PedidoInexistenteException;
    public void atualizarPedido(Pedido pedido) throws ErroInternoException, PedidoInexistenteException;
    public List<Pedido> buscarPedidoItemPedido(ItemPedido p) throws ErroInternoException;
    
}
