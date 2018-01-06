package manager;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.sun.faces.config.FacesConfigInfo;

import entity.Assunto;
import entity.Resposta;
import persistence.AssuntoDao;

@ManagedBean(name = "mb")
@RequestScoped
public class BeanForum implements Serializable {
	private static final long serialVersionUID = 1L;

	private Assunto assunto;
	private Resposta resposta;
	private Assunto logado;

	private List<Assunto> listaAssuntos;

	
	public BeanForum() {
		assunto = new Assunto();
		resposta = new Resposta();
	}
	
	public List<Assunto> getListaAssuntos() {
		listaAssuntos = new AssuntoDao().findAll();
		return listaAssuntos;
	}

	public void setListaAssuntos(List<Assunto> listaAssuntos) {
		this.listaAssuntos = listaAssuntos;
	}

	public Assunto getAssunto() {
		return assunto;
	}

	public void setAssunto(Assunto assunto) {
		this.assunto = assunto;
	}

	public Resposta getResposta() {
		return resposta;
	}

	public void setResposta(Resposta resposta) {
		this.resposta = resposta;
	}

	public Assunto getLogado() {
		return logado;
	}

	public void setLogado(Assunto logado) {
		this.logado = logado;
	}
	
	
	public void gravarPrimeira() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			
			AssuntoDao dao = new AssuntoDao();
			dao.create(assunto);
			assunto = new Assunto();
			
			fc.addMessage(null,  new FacesMessage("Dados Gravados"));
			
		} catch (Exception e) {
			fc.addMessage(null, new FacesMessage("Error : " + e.getMessage()));
		}
	}

}
