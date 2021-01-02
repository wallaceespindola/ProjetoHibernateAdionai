package br.telehand.dao;

// Generated 27/03/2014 15:10:19 by Hibernate Tools 4.0.0

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.telehand.model.TbAtendimento;
import br.telehand.model.TbReporte;
import br.telehand.util.Util;
/**
 * Home object for domain model class TbAtendimento.
 * @see controller.TbAtendimento
 * @author Hibernate Tools
 */
public class AtendimentoDAO {

	private static final Log log = LogFactory.getLog(AtendimentoDAO.class);	

	public TbAtendimento findById(int id) {
		log.debug("getting TbAtendimento instance with id: " + id);
		try {
			
			Session session = Util.getSessionFactory().openSession();
			
	        org.hibernate.Criteria criteria = session.createCriteria(TbAtendimento.class);
	        
	        TbAtendimento retorno = (TbAtendimento) criteria.add(Restrictions.eq("idAtendimento", id)).uniqueResult();
	        
			if (retorno == null) { 
				log.debug("Atendimento encontrado");
			} else {
				log.debug("Atendimento nao encontrado");
			}
			return retorno;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<TbAtendimento> listarIdServico(int IdServico) {

		List<TbAtendimento> list = null;
		
		Session session = Util.getSessionFactory().openSession();
		
		try {
			session.beginTransaction();
			
			Criteria cr = session.createCriteria(TbAtendimento.class, "a");
					 cr.createAlias("a.tbOs.tbServico", "s")
					 .add(Restrictions.eq("s.idServico",IdServico));
					 list = cr.list();
			
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		
		return list;
	}
	
	public List<TbAtendimento> listarTodos() {
		
		List<TbAtendimento> list = null;
		Session session = Util.getSessionFactory().openSession();
		
		try {
			session.beginTransaction();
			list = session.createQuery("from TbAtendimento").list();
			session.getTransaction().commit();
			
		} catch (HibernateException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			
		} finally {
			session.close();
		}
		return list;
	}
	
	public TbAtendimento porIdJanela(int id) {
		log.debug("getting TbAtendimento instance with id: " + id);
		try {

			Session session = Util.getSessionFactory().openSession();
			Criteria cr = session.createCriteria(TbAtendimento.class);
			TbAtendimento retorno = (TbAtendimento) cr.add( Restrictions.eq("tbAgenda.idJanela", id) ).uniqueResult();
			
			if (retorno == null) {
				log.debug("Servico encontrado");
			} else {
				log.debug("Servico nao encontrado");
			}
			return retorno;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}


}
