package br.telehand.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.telehand.model.TbReporte;
import br.telehand.util.Util;
/**
 * Home object for domain model class TbReporte.
 * @see controller.TbReporte
 * @author Hibernate Tools
 */
public class ReporteDAO {

	private static final Log log = LogFactory.getLog(ReporteDAO.class);

	public List<TbReporte> listarPorIdServico(int IdServico) {

/*		List<TbReporte> list = null;
		
		Session session = Util.getSessionFactory().openSession();
		
		try {
			session.beginTransaction();
//			String getOptions = "SELECT a from TbReporte a JOIN a.tbOs b ON b.tbServico.idServico = :id";�
			String getOptions = "SELECT r from TbReporte r JOIN r.tbAtendimentos.tbOs.tbServicos s ON s.idServico = :id";
			list = session.createQuery(getOptions).setParameter("id", IdServico).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return list;
	}*/
		
		List<TbReporte> list = null;
		
		Session session = Util.getSessionFactory().openSession();
		
		try {
			session.beginTransaction();
			
			Criteria cr = session.createCriteria(TbReporte.class, "r");
					 cr.createAlias("r.tbAtendimento.tbOs.tbServico", "a")
					 .add(Restrictions.eq("a.idServico",IdServico));
					 list = cr.list();
					 
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return list;
	}
	
}
