package persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jcommon.encryption.SimpleMD5;

import entity.Assunto;
import entity.Resposta;

public class AssuntoDao {

	Session session;
	Transaction transaction;
	Criteria criteria;
	Query query;

	public void create(Assunto a) throws Exception {
		criptografar(a);
		session = HibernateUtil.getSessionFactory().openSession();

		transaction = (Transaction) session.beginTransaction();
		session.save(a);
		transaction.commit();
		session.close();

	}

	public void create(Assunto a, Resposta r) throws Exception {
		criptografar(a);
		session = HibernateUtil.getSessionFactory().openSession();

		transaction = (Transaction) session.beginTransaction();
		session.save(a);
		r.setAssunto(a);
		session.save(r);
		transaction.commit();
		session.close();

	}

	public void createOnlyResposta(Assunto a, Resposta r) throws Exception {
		session = HibernateUtil.getSessionFactory().openSession();

		transaction = (Transaction) session.beginTransaction();
		r.setAssunto(a);
		session.save(r);
		transaction.commit();
		session.close();

	}

	public List<Assunto> findAll() {
		session = HibernateUtil.getSessionFactory().openSession();

		List<Assunto> lista = session.createQuery("from Assunto").list();
		session.close();
		return lista;
	}

	private void criptografar(Assunto a) {
		SimpleMD5 md5 = new SimpleMD5(a.getSenha(), "11");
		a.setSenha(md5.toHexString());
	}

	public Assunto findByCode(Integer id) {
		session = HibernateUtil.getSessionFactory().openSession();

		Assunto assunto = (Assunto) session.get(Assunto.class, id);
		session.close();
		return assunto;
	}

	public Assunto logar(Assunto a) {
		criptografar(a);
		session = HibernateUtil.getSessionFactory().openSession();

		query = session.createQuery("from Assunto a where a.email = :param1 and a.senha = :param2");
		query.setParameter("param1", a.getEmail());
		query.setParameter("param2", a.getSenha());
		Assunto retorno = (Assunto)query.uniqueResult();
		session.close();
		return retorno;
	}

	// teste
	public static void main(String[] args) {
		try {
			AssuntoDao aDao = new AssuntoDao();
			Assunto a = aDao.findByCode(1);

			Resposta r = new Resposta();
			r.setNome("lu");
			r.setTexto("discussão geral2");
			r.setAssunto(aDao.logar(a));
			new AssuntoDao().createOnlyResposta(a, r);

			System.out.println("Dados gravados");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
