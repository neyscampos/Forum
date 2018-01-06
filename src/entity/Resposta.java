package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "resposta")
public class Resposta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idResposta;
	@Column
	private String nome;
	@Column
	private String texto;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Fkidassunto")
	private Assunto assunto;

	public Resposta() {
	}

	public Resposta(Integer idResposta, String nome, String texto, Assunto assunto) {
		this.idResposta = idResposta;
		this.nome = nome;
		this.texto = texto;
		this.assunto = assunto;
	}

	@Override
	public String toString() {
		return "Resposta [idResposta=" + idResposta + ", nome=" + nome + ", texto=" + texto + "]";
	}

	public Assunto getAssunto() {
		return assunto;
	}

	public void setAssunto(Assunto assunto) {
		this.assunto = assunto;
	}

	public Integer getIdResposta() {
		return idResposta;
	}

	public void setIdResposta(Integer idResposta) {
		this.idResposta = idResposta;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
