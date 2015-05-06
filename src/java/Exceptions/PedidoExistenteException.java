
package Exceptions;

public class PedidoExistenteException extends Exception{
    public PedidoExistenteException(){
        super("Pedido já Existe");
    }
    
}
