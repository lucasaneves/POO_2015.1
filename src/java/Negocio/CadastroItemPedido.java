package Negocio;

import Entidades.ItemPedido;
import Exceptions.ErroInternoException;
import Exceptions.ItemPedidoInexistenteException;
import Persistencia.RepositorioItemPedido;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class CadastroItemPedido {
    
    @EJB
    private RepositorioItemPedido repItemPedidos;

    public void adicionarItemPedido(ItemPedido ipedido) throws ErroInternoException {
        try {
            ItemPedido iped = repItemPedidos.buscarItemPedidoCodigo(ipedido.getCodigo());
            throw new ItemPedidoInexistenteException();

        } catch (ItemPedidoInexistenteException e) {
            this.repItemPedidos.adicionarItemPedido(ipedido);
        }
    }
/** esse metodo vai listar todos os itens do pedido
 * @return
 * @throws ErroInternoException 
 */
    public List<ItemPedido> listarItensPedido() throws ErroInternoException {
        return this.repItemPedidos.listarItensPedido();
    }

    public ItemPedido buscarItemPedidoCodigo(long codigo) throws ErroInternoException, ItemPedidoInexistenteException {
        return this.repItemPedidos.buscarItemPedidoCodigo(codigo);
    }

    public void removerItemPedidoCodigo(long codigo) throws ErroInternoException, ItemPedidoInexistenteException {
        this.repItemPedidos.removerItemPedidoCodigo(codigo);
    }

    public void atualizarItemPedido(ItemPedido ipedido) throws ErroInternoException, ItemPedidoInexistenteException {

        ItemPedido iped = this.repItemPedidos.buscarItemPedidoCodigo(ipedido.getCodigo());
        // se tiver alguma regra
        this.repItemPedidos.atualizarItemPedido(ipedido);
    }

    
}