package manager;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import entity.Assunto;
import entity.Resposta;

@ManagedBean(name = "mb")
@RequestScoped
public class BeanForum implements Serializable {
	private static final long serialVersionUID = 1L;

	private Assunto assunto;
	private Resposta resposta;
	private Assunto logado;

	private List<Assunto> listaAssuntos;

	public List<Assunto> getListaAssuntos() {
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

}
