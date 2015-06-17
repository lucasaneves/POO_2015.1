package Persistencia;

import Entidades.ItemPedido;
import Exceptions.ErroInternoException;
import Exceptions.ItemPedidoInexistenteException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;

@Local
/**Esta classe contem os metodos que serão usados no repositorioJPA
 * 
 */
public interface RepositorioItemPedido extends Serializable {

    public void adicionarItemPedido(ItemPedido ip) throws ErroInternoException;

    public List<ItemPedido> listarItensPedido() throws ErroInternoException;

    public ItemPedido buscarItemPedidoCodigo(long codigo) throws ErroInternoException, ItemPedidoInexistenteException;

    public void removerItemPedidoCodigo(long codigo) throws ErroInternoException, ItemPedidoInexistenteException;

    public void atualizarItemPedido(ItemPedido ip) throws ErroInternoException, ItemPedidoInexistenteException;

}
