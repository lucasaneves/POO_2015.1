package Persistencia;

import Entidades.Mercado;
import Entidades.Pedido;
import Entidades.Produto;
import Entidades.Usuario;
import Exceptions.ErroInternoException;
import Exceptions.MercadoInexistenteException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class RepositorioMercadoJPA implements RepositorioMercado {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void adicionarMercado(Mercado mercado) throws ErroInternoException {
        try {
            this.em.persist(mercado);
        } catch (Exception e) {
            throw new ErroInternoException(e);

        }
    }

    @Override
    public Mercado buscarMercadoCodigo(long codigo) throws ErroInternoException, MercadoInexistenteException {
        Mercado m = null;
        try {
            m = this.em.find(Mercado.class, codigo);  //buscar pela chave primaria é o find se for por outro atributo já é outro.
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
        if (m == null) {
            throw new MercadoInexistenteException();
        }
        return m;
    }

    @Override
    public Mercado buscarMercadoCnpj(String cnpj) throws ErroInternoException, MercadoInexistenteException {
        try {
            TypedQuery<Mercado> consulta = this.em.createQuery("select m from Mercado m where m.cnpj like :cnpj", Mercado.class);
            consulta.setParameter("cnpj", "%" + cnpj + "%");
            return consulta.getSingleResult();

        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    @Override
    public void removerMercadoCodigo(long codigo) throws ErroInternoException, MercadoInexistenteException {
        Mercado m = buscarMercadoCodigo(codigo);
        try {
            this.em.remove(m);
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    @Override
    public void removerMercadoCnpj(String cnpj) throws ErroInternoException, MercadoInexistenteException {
        Mercado m = buscarMercadoCnpj(cnpj);
        try {
            this.em.remove(m);
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    @Override
    public List<Mercado> listarMercados() throws ErroInternoException {
        try {
            TypedQuery<Mercado> consulta = this.em.createQuery("select m from Mercado m", Mercado.class);
            return consulta.getResultList();

        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }
    @Override
        public List<Produto> buscarProdutoMercado(Mercado mercado) throws ErroInternoException {
        try {
            //TypedQuery<Produto> consulta = this.em.createQuery("select m from Mercado m join m.produto p where m.codigo= :codigo", Produto.class);
            TypedQuery<Produto> consulta = this.em.createQuery("SELECT p FROM Produto p WHERE p.mercado= :mercado", Produto.class);
            consulta.setParameter("mercado", mercado);
            return consulta.getResultList();

        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }
@Override
    public List<Pedido> buscarPedidoMercado(Mercado mercado) throws ErroInternoException {
        try {
            TypedQuery<Pedido> consulta = this.em.createQuery("select p from Pedido p where p.mercado= :mercado", Pedido.class);
            consulta.setParameter("mercado", mercado);
            return consulta.getResultList();
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }
    @Override
    public List<Mercado> buscarMercadoNome(String nome) throws ErroInternoException {
        try {
            TypedQuery<Mercado> consulta = this.em.createQuery("select m from Mercado m where m.nome like :nome", Mercado.class);
            consulta.setParameter("nome", "%" + nome + "%");
            return consulta.getResultList();

        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    @Override
    public void atualizarMercado(Mercado mercado) throws ErroInternoException, MercadoInexistenteException {
        //Mercado m = buscarMercadoCodigo(mercado.getCodigo());
        Mercado m = buscarMercadoCnpj(mercado.getCnpj());
        m.setNome(mercado.getNome());
         m.setEndereco(mercado.getEndereco());
         m.setBairro(mercado.getBairro());
         m.setCidade(mercado.getCidade());
         m.setCep(mercado.getCep());
         m.setUf(mercado.getUf());
         m.setTelefone(mercado.getTelefone());
         m.setSenha(mercado.getSenha());
        try {
            this.em.merge(m);
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    

    

}
