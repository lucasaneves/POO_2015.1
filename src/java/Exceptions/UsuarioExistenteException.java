package Exceptions;

public class UsuarioExistenteException extends Exception{
    
    public UsuarioExistenteException(){
        super("Usuario ja existe!");
    }
    
}
