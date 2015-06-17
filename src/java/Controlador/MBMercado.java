package Controlador;

import Entidades.Categoria;
import Entidades.Mercado;
import Entidades.Pedido;
import Entidades.Produto;
import Entidades.Usuario;
import Exceptions.ErroInternoException;
import Exceptions.MercadoExistenteException;
import Exceptions.MercadoInexistenteException;
import Fachada.Fachada;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@ManagedBean
@Named(value = "mBMercado")
@SessionScoped

public class MBMercado implements Serializable {
    private Mercado mercado;
    private Categoria categorias;
    private String login;
    private long codigo;
    private MBProduto mbproduto;
    private Usuario usuario;
    
    public MBMercado(){
        this.mercado = new Mercado();
    }
    
    @EJB
    private Fachada fachada;

    public Mercado getMercado() {
        return mercado;
    }

    public void setMercado(Mercado mercado) {
        this.mercado = mercado;
    }

    public Fachada getFachada() {
        return fachada;
    }

    public void setFachada(Fachada fachada) {
        this.fachada = fachada;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public MBProduto getMbproduto() {
        return mbproduto;
    }

    public void setMbproduto(MBProduto mbproduto) {
        this.mbproduto = mbproduto;
    }
    
    
    
    /**esse metodo adiciona mercado
     * 
     * @return
     * @throws ErroInternoException 
     */
    public String adicionarMercado() throws ErroInternoException{
        try{
            Mercado m = new Mercado(mercado.getNome(), mercado.getCnpj(), mercado.getEndereco(), mercado.getBairro(), mercado.getCidade(), mercado.getUf(), mercado.getCep(), mercado.getTelefone(), mercado.getSenha());
            this.fachada.adicionarMercado(m);
            return "index.xhtml";
        } catch (ErroInternoException e) {
            e.printStackTrace();
            return "ErroInterno.xhtml";
        }
        }
    
    public String removerMercadoCodigo() throws ErroInternoException,MercadoInexistenteException{
        try {
            this.fachada.removerMercadoCodigo(mercado.getCodigo());
            return "index.xhtml";
        } catch (ErroInternoException e) {
            e.printStackTrace();
            return "ErroInterno.xhtml";
        } catch (MercadoInexistenteException ex) {
            return "MercadoInexistente.xhtml";
        }
    }
    public String removerMercadoCnpj() throws ErroInternoException, MercadoInexistenteException{
        try {
            this.fachada.removerMercadoCnpj(mercado.getCnpj());
            return "index.xhtml";
        } catch (ErroInternoException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Erro Interno, Tente Novamente mais tarde"));
            return null;
        } catch (MercadoInexistenteException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Este Mercado não está cadastrado em nosso sistema!"));
            return null;
        }
    }
    public List<Mercado> getlistarMercados() throws ErroInternoException{
         try {
            return this.fachada.listarMercados();
        } catch (ErroInternoException e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Produto> getListbuscarProdutoMercado() throws ErroInternoException, MercadoInexistenteException{
         try {
             //Mercado m = new Mercado(mercado.getNome(), mercado.getCnpj(), mercado.getEndereco(), mercado.getBairro(), mercado.getCidade(), mercado.getUf(), mercado.getCep(), mercado.getTelefone(), mercado.getSenha());
             Mercado m1 =  this.fachada.buscarMercadoCodigo(getCodigo());
             return this.fachada.buscarProdutoMercado(m1);
            
          } catch (ErroInternoException z) {
            return null;
        }     
    }
    
    public List<Pedido> getListbuscarPedidoMercado() throws ErroInternoException, MercadoInexistenteException{
        try{
            Mercado m2 = this.fachada.buscarMercadoCodigo(getCodigo());
            return this.fachada.buscarPedidoMercado(m2);
        }catch (ErroInternoException z) {
            return null;
        }
        
    }
    public String buscarMercadoCodigo() throws ErroInternoException, MercadoInexistenteException{
        try {

            this.fachada.buscarMercadoCodigo(mercado.getCodigo());
            return "sucesso.xhtml";
          } catch (ErroInternoException z) {
            return "ErroInterno.xhtml";
        } catch (MercadoInexistenteException zz) {
            return "MercadoInexistente.xhtml";
        }
    }
    public String buscarMercadoCnpj() throws ErroInternoException, MercadoInexistenteException{
        try {
            this.fachada.buscarMercadoCnpj(mercado.getCnpj());
            return "sucesso.xhtml";
          } catch (ErroInternoException z) {
            return "ErroInterno.xhtml";
        } catch (MercadoInexistenteException zz) {
            return "MercadoInexistente.xhtml";
        }
    }
    
    //Saber qual o certo
    public String buscarMercadoNome() throws ErroInternoException{
        try {
            this.fachada.buscarMercadoNome(mercado.getNome());
            return "sucesso.xhtml";
          } catch (ErroInternoException z) {
            return "ErroInterno.xhtml";
        }  
    }
    public String atualizarMercado() throws ErroInternoException, MercadoInexistenteException{
        try {
            Mercado att = new Mercado(mercado.getNome(), mercado.getCnpj(), mercado.getEndereco(), mercado.getBairro(), mercado.getCidade(), mercado.getUf(), mercado.getCep(), mercado.getTelefone(), mercado.getSenha());
            this.fachada.atualizarMercado(att);
FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", " Mercado Atualizado com Sucesso!"));
            return null;
        } catch (ErroInternoException exz) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", " Erro Interno! "));
            return null;
        } catch (MercadoInexistenteException ez) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", " Este Mercado Não Existe!"));
            return null;
        }
    }
    
    //ver isso aqui
    
    //ver isso aqui.
    
    
        public String autenticarMercado() throws ErroInternoException, MercadoInexistenteException {
        try {
            Mercado mer = this.fachada.buscarMercadoCnpj(mercado.getCnpj());
            if (mer.getCnpj().equals(mercado.getCnpj()) && mer.getSenha().equals(mercado.getSenha())) {
                setLogin(mer.getNome());
                setCodigo(mer.getCodigo());
                mercado.setCnpj(mer.getCnpj());
                return "MercadoIndex.xhtml";

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Login ou Senha Invalidos!"));
                return null;
            }
        } catch (ErroInternoException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Login ou Senha Invalidos!"));
            return null;
        } catch (MercadoInexistenteException ue) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Login ou Senha Invalidos!"));
            return null;
        }
    }

    public String logoutMercado() {
        setLogin(" ");
        return "index.xhtml";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
