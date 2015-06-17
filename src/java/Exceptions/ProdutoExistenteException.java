package Exceptions;

public class ProdutoExistenteException extends Exception{
    public ProdutoExistenteException(){
        super("Produto já Existe");
    }
}
