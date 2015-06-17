package Persistencia;

import Entidades.Pedido;
import Entidades.Usuario;
import Exceptions.ErroInternoException;
import Exceptions.UsuarioInexistenteException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class RepositorioUsuarioJPA implements RepositorioUsuario {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void adicionarUsuario(Usuario usuario) throws ErroInternoException {
        try {
            this.em.persist(usuario);
        } catch (Exception e) {
            throw new ErroInternoException(e);

        }
    }

    @Override
    public Usuario buscarUsuarioCodigo(long codigo) throws ErroInternoException, UsuarioInexistenteException {
        Usuario c = null;
        try {
            c = this.em.find(Usuario.class, codigo);  //buscar pela chave primaria é o find se for por outro atributo já é outro.
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
        if (c == null) {
            throw new UsuarioInexistenteException();
        }
        return c;
    }

    @Override
    public Usuario buscarUsuarioEmail(String email) throws ErroInternoException, UsuarioInexistenteException {
        try {
            TypedQuery<Usuario> consulta = this.em.createQuery("select c from Usuario c where c.email = :email", Usuario.class);
            consulta.setParameter("email", email);
            return consulta.getSingleResult();

        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    @Override
    public void removerUsuarioCodigo(long codigo) throws ErroInternoException, UsuarioInexistenteException {
        Usuario c = buscarUsuarioCodigo(codigo);
        try {
            this.em.remove(c);
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    @Override
    public void removerUsuarioEmail(String email) throws ErroInternoException, UsuarioInexistenteException {
        Usuario u = buscarUsuarioEmail(email);
        try {
            this.em.remove(u);
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    @Override
    public List<Usuario> listarUsuarios() throws ErroInternoException {
        try {
            TypedQuery<Usuario> consulta = this.em.createQuery("select c from Usuario c", Usuario.class);
            return consulta.getResultList();

        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    @Override
    public List<Usuario> buscarUsuarioNome(String nome) throws ErroInternoException {
        try {
            TypedQuery<Usuario> consulta = this.em.createQuery("select c from Usuario c where c.nome like :nome", Usuario.class);
            consulta.setParameter("nome", "%" + nome + "%");
            return consulta.getResultList();

        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    @Override
    public void atualizarUsuario(Usuario usuario) throws ErroInternoException, UsuarioInexistenteException {
        Usuario c = buscarUsuarioEmail(usuario.getEmail());
        c.setNome(usuario.getNome());
        c.setSenha(usuario.getSenha());
        c.setDataNascimento(usuario.getDataNascimento());
        c.setEndereco(usuario.getEndereco());
        c.setBairro(usuario.getBairro());
        c.setCidade(usuario.getCidade());
        c.setCep(usuario.getCep());
        c.setUf(usuario.getUf());
        c.setTelefone(usuario.getTelefone());
        try {
            this.em.merge(c);
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    @Override
    public List<Usuario> buscarPedidoUsuario(Pedido p) throws ErroInternoException {
     
     try {
            TypedQuery<Usuario> consulta = this.em.createQuery("select u from Usuario u join u.pedido p where p.codigo= :codigo", Usuario.class);
            consulta.setParameter("codigo", p.getCodigo());
            return consulta.getResultList();
            

        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    @Override
    public Usuario autenticarUsuario(String email) throws ErroInternoException, UsuarioInexistenteException {
        try {
            TypedQuery<Usuario> consulta = this.em.createQuery("select c from Usuario c where c.email = :email", Usuario.class);
            consulta.setParameter("email", email);
            return consulta.getSingleResult();

        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

}
