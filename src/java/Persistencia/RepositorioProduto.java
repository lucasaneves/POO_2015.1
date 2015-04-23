
package Persistencia;

import Entidades.Produto;
import Exceptions.ErroInternoException;
import java.io.Serializable;
import javax.ejb.Local;

@Local
public interface RepositorioProduto extends Serializable {
    public void adicionarProduto(Produto produto) throws ErroInternoException;
       
}
