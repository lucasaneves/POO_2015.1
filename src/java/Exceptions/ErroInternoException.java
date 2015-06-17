package Exceptions;

public class ErroInternoException extends Exception{
    private Exception gerador;

    public Exception getGerador() {
        return gerador;
    }

    public void setGerador(Exception gerador) {
        this.gerador = gerador;
    }
    
    public ErroInternoException(Exception gerador) {
        super(gerador.getMessage());
        this.gerador = gerador;
    }
    
}
