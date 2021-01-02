package br.telehand.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.telehand.model.TbAgenda;
import br.telehand.util.Util;

public class AgendaDAO {

	private static final Log log = LogFactory.getLog(AgendaDAO.class);	
	
	public List<TbAgenda> listarPorIdServico(int IdServico) {
		
		System.out.println("======================");
		System.out.println("IdServico: " + IdServico);
		System.out.println("======================");

		List<TbAgenda> list = null;
		Session session = Util.getSessionFactory().openSession();
		
		try {
			session.beginTransaction();
			list = session.createQuery("from TbAgenda").list();
			session.getTransaction().commit();
			
		} catch (HibernateException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			
		} finally {
			session.close();
		}
		
		System.out.println("----------------- lista todos ----------------- ");
		System.out.println(list);
		System.out.println("----------------- lista todos ----------------- ");
		
		return list;
		
	}
	
}
