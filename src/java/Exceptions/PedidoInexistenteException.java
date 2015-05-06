package Exceptions;

public class PedidoInexistenteException extends Exception{

    public PedidoInexistenteException() {
        super("Pedido não existe");
    }

}
