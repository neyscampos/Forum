package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "assunto")
public class Assunto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id = 0;
	@Column
	private String assunto;
	@Column
	private String comentario;
	@Column
	private String nome;
	@Column
	private String email;
	@Column
	private String senha;
	@OneToMany(mappedBy = "assunto", fetch = FetchType.EAGER)
	private List<Resposta> respostas;

	public Assunto() {
		this.respostas = new ArrayList<Resposta>();
	}

	public Assunto(Integer id, String assunto, String comentario, String nome, String email, String senha) {
		super();
		this.id = id;
		this.assunto = assunto;
		this.comentario = comentario;
		this.nome = nome;
		this.email = email;
		this.senha = senha;

		this.respostas = new ArrayList<Resposta>();
	}

	public void adicionarReposta(Resposta... respostas) {
		for (Resposta resposta : respostas) {
			this.respostas.add(resposta);
		}
	}

	@Override
	public String toString() {
		return "Assunto [id=" + id + ", assunto=" + assunto + ", comentario=" + comentario + ", nome=" + nome
				+ ", email=" + email + "]";
	}

	public List<Resposta> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
