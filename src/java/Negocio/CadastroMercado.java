package Negocio;

import Entidades.Mercado;
import Entidades.Pedido;
import Entidades.Produto;
import Exceptions.ErroInternoException;
import Exceptions.MercadoInexistenteException;
import Exceptions.PedidoInexistenteException;
import Persistencia.RepositorioMercado;
import Persistencia.RepositorioPedido;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class CadastroMercado {

    @EJB
    private RepositorioMercado repMercado;
    @EJB
    private RepositorioPedido repPedido;

    public void adicionarMercado(Mercado mercado) throws ErroInternoException{
        try {
            Mercado m = repMercado.buscarMercadoCodigo(mercado.getCodigo());
            throw new MercadoInexistenteException();

        } catch (MercadoInexistenteException e) {
            this.repMercado.adicionarMercado(mercado);
        }
        
    }

    public void removerMercadoCodigo(long codigo) throws ErroInternoException, MercadoInexistenteException{
        //this.repMercado.removerMercadoCodigo(codigo);
        try{
            Mercado mer = repMercado.buscarMercadoCodigo(codigo);
            List<Pedido> ped = mer.getPedidos();
            
            
            for(Pedido p : ped) {
                this.repPedido.removerPedidoCodigo(p.getCodigo());
            }
            
            this.repMercado.removerMercadoCodigo(codigo);
        }catch (PedidoInexistenteException e) {
            throw new ErroInternoException(e);
        }
    }

    public void removerMercadoCnpj(String cnpj) throws ErroInternoException, MercadoInexistenteException{
        //this.repMercado.removerMercadoCnpj(cnpj);
        try{
            Mercado mer = repMercado.buscarMercadoCnpj(cnpj);
            List<Pedido> ped = mer.getPedidos();
            for(Pedido p : ped) {
                this.repPedido.removerPedidoCodigo(p.getCodigo());
            }
            this.repMercado.removerMercadoCodigo(mer.getCodigo());
        }catch (PedidoInexistenteException e) {
            throw new ErroInternoException(e);
        }
    }
/** Lista todos os mercados cadastrados
 *
 * @return
 * @throws ErroInternoException 
 */
    public List<Mercado> listarMercados() throws ErroInternoException{
        return this.repMercado.listarMercados();
        
    }
    public List<Produto> buscarProdutoMercado(Mercado mercado) throws ErroInternoException{
        return this.repMercado.buscarProdutoMercado(mercado);
    }

    public Mercado buscarMercadoCodigo(long codigo) throws ErroInternoException, MercadoInexistenteException{
        return this.repMercado.buscarMercadoCodigo(codigo);
    }

    public Mercado buscarMercadoCnpj(String cnpj) throws ErroInternoException, MercadoInexistenteException{
        return this.repMercado.buscarMercadoCnpj(cnpj);
    }

    public List<Mercado> buscarMercadoNome(String nome) throws ErroInternoException{
        return this.repMercado.buscarMercadoNome(nome);
    }

    public void atualizarMercado(Mercado mercado) throws ErroInternoException, MercadoInexistenteException{
        //this.repMercado.atualizarMercado(mercado);
        Mercado m1 = this.repMercado.buscarMercadoCnpj(mercado.getCnpj());
        if (m1 != null){   
             this.repMercado.atualizarMercado(mercado);
        } 
       
    }

    

    public List<Pedido> buscarPedidoMercado(Mercado m) throws ErroInternoException{
        return this.repMercado.buscarPedidoMercado(m);
    }
}
