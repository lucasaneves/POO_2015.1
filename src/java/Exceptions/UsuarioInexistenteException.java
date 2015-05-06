
package Exceptions;

public class UsuarioInexistenteException extends Exception{
    
    public UsuarioInexistenteException(){
        super("Usuario não existe");
    }
    
}
