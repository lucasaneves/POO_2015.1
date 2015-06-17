package Exceptions;
/** 
 * Excecao da existencia do pedido
 * @author Ozair
 */
public class ItemPedidoExistenteException  extends Exception{
    public ItemPedidoExistenteException(){
        super("ItemPedido já Existe");
    }
}
