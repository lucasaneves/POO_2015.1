
package Exceptions;

public class ItemPedidoInexistenteException extends Exception{

    public ItemPedidoInexistenteException() {
        super("ItemPedido não existe");
    }
}
