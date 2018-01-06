package manager;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
			dao.create(assunto, new Resposta());
			assunto = new Assunto();

			fc.addMessage(null, new FacesMessage("Dados Gravados"));

		} catch (Exception e) {
			fc.addMessage(null, new FacesMessage("Error : " + e.getMessage()));
		}
	}

	public String logar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			logado = new AssuntoDao().findByLogin(assunto);

			if (logado != null) {
				HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
				HttpSession session = request.getSession(true);
				session.setAttribute("usuariolog", logado);

				fc.addMessage(null, new FacesMessage("Usuário Logado : " + logado.getNome()));
				return "sistema.jsf?faces-redirect";
			} else {
				fc.addMessage(null, new FacesMessage("Acesso Negado"));
			}

		} catch (Exception e) {
			fc.addMessage(null, new FacesMessage("Error : " + e.getMessage()));
		}
		return null;
	}

	public void mostrarDados() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
			HttpSession session = request.getSession();
			
			Assunto as = (Assunto) session.getAttribute("usuariolog");
			resposta.setAssunto(as);
			resposta.setNome(as.getNome());
			
		} catch (Exception e) {
			fc.addMessage(null, new FacesMessage("Error : " + e.getMessage()));
		}
	}

	public void gravarResposta() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			AssuntoDao dao = new AssuntoDao();
			HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
			HttpSession session = request.getSession();
			
			Assunto as = (Assunto) session.getAttribute("usuariolog");
			
			dao.createOnlyResposta(as, resposta);
			assunto = new Assunto();

			fc.addMessage(null, new FacesMessage("Dados Gravados"));

		} catch (Exception e) {
			fc.addMessage(null, new FacesMessage("Error : " + e.getMessage()));
		}
	}

}
