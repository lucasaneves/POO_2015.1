package Negocio;

import Entidades.Pedido;
import Entidades.Usuario;
import Exceptions.ErroInternoException;
import Exceptions.UsuarioExistenteException;
import Exceptions.UsuarioInexistenteException;
import Persistencia.RepositorioUsuario;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
/**Esta classe contem todas as regras de negocio e as exceções da entidade usuarios
 * 
 */
public class CadastroUsuarios {

    @EJB
    private RepositorioUsuario repUsuarios;

    public void adicionarUsuario(Usuario u) throws ErroInternoException, UsuarioExistenteException {
        try {
            Usuario us = repUsuarios.buscarUsuarioCodigo(u.getCodigo());
            throw new UsuarioInexistenteException();
        } catch (UsuarioInexistenteException e) {
            this.repUsuarios.adicionarUsuario(u);
        }
    }

    public void removerUsuarioCodigo(long codigo) throws ErroInternoException, UsuarioInexistenteException {
        this.repUsuarios.removerUsuarioCodigo(codigo);
        
    }

    public void removerUsuarioEmail(String email) throws ErroInternoException, UsuarioInexistenteException {
        this.repUsuarios.removerUsuarioEmail(email);
    }

    public List<Usuario> listarUsuarios() throws ErroInternoException{
        return this.repUsuarios.listarUsuarios();
    }
    
    public Usuario buscarUsuarioCodigo(long codigo) throws ErroInternoException, UsuarioInexistenteException{
        return this.repUsuarios.buscarUsuarioCodigo(codigo);
    }
    
    public Usuario buscarUsuarioEmail(String email) throws ErroInternoException, UsuarioInexistenteException{
        return this.repUsuarios.buscarUsuarioEmail(email);
    }
    
    public List<Usuario> buscarUsuarioNome(String nome) throws ErroInternoException{
        return this.repUsuarios.buscarUsuarioNome(nome);
    }
    
    public void atualizarUsuario(Usuario usuario) throws ErroInternoException, UsuarioInexistenteException{
        Usuario us = this.repUsuarios.buscarUsuarioEmail(usuario.getEmail());
        if (us != null){   
            this.repUsuarios.atualizarUsuario(usuario);
        } else {
        }
        
        
    }
    
    public List<Usuario> buscarPedidoUsuario(Pedido p) throws ErroInternoException{
        return this.repUsuarios.buscarPedidoUsuario(p);
    }

    public Usuario autenticarUsuario(String email) throws ErroInternoException, UsuarioInexistenteException{
        return this.repUsuarios.autenticarUsuario(email);
        
    }
}
