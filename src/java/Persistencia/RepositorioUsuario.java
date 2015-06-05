package Persistencia;

import Entidades.Pedido;
import Entidades.Usuario;
import Exceptions.ErroInternoException;
import Exceptions.UsuarioInexistenteException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;

@Local
public interface RepositorioUsuario extends Serializable {
    public void adicionarUsuario(Usuario usuario) throws ErroInternoException;
    public void removerUsuarioCodigo(long codigo) throws ErroInternoException,UsuarioInexistenteException;
    public void removerUsuarioEmail(String email) throws ErroInternoException, UsuarioInexistenteException;
    public List<Usuario> listarUsuarios() throws ErroInternoException;
    public Usuario buscarUsuarioCodigo(long codigo) throws ErroInternoException, UsuarioInexistenteException;
    public Usuario buscarUsuarioEmail(String email) throws ErroInternoException, UsuarioInexistenteException;
    public List<Usuario> buscarUsuarioNome(String nome) throws ErroInternoException;
    public void atualizarUsuario(Usuario usuario) throws ErroInternoException, UsuarioInexistenteException;
    public List<Usuario> buscarPedidoUsuario(Pedido p) throws ErroInternoException;
    public Usuario autenticarUsuario(String email) throws ErroInternoException, UsuarioInexistenteException;
}
