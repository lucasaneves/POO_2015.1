package Persistencia;

import Entidades.Mercado;
import Entidades.Pedido;
import Entidades.Produto;
import Entidades.Usuario;
import Exceptions.ErroInternoException;
import Exceptions.MercadoInexistenteException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;

@Local
/**Esta classe contem os metodos que serão usados no repositorioJPA
 * 
 */
public interface RepositorioMercado extends Serializable {
    public void adicionarMercado(Mercado mercado) throws ErroInternoException;
    public void removerMercadoCodigo(long codigo) throws ErroInternoException,MercadoInexistenteException;
    public void removerMercadoCnpj(String cnpj) throws ErroInternoException, MercadoInexistenteException;
    public List<Mercado> listarMercados() throws ErroInternoException;
    public List<Produto> buscarProdutoMercado(Mercado mercado) throws ErroInternoException;
    public Mercado buscarMercadoCodigo(long codigo) throws ErroInternoException, MercadoInexistenteException;
    public Mercado buscarMercadoCnpj(String cnpj) throws ErroInternoException, MercadoInexistenteException;
    public List<Mercado> buscarMercadoNome(String nome) throws ErroInternoException;
    public void atualizarMercado(Mercado mercado) throws ErroInternoException, MercadoInexistenteException;
    public List<Pedido> buscarPedidoMercado (Mercado mercado) throws ErroInternoException;
    
}
