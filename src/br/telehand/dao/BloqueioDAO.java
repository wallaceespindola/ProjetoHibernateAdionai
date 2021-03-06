package br.telehand.dao;

// Generated 27/03/2014 15:10:19 by Hibernate Tools 4.0.0

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import br.telehand.model.TbBloqueio;
import br.telehand.model.TbBloqueioId;
/**
 * Home object for domain model class TbBloqueio.
 * @see controller.TbBloqueio
 * @author Hibernate Tools
 */
public class BloqueioDAO {

	private static final Log log = LogFactory.getLog(BloqueioDAO.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(TbBloqueio transientInstance) {
		log.debug("persisting TbBloqueio instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TbBloqueio instance) {
		log.debug("attaching dirty TbBloqueio instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbBloqueio instance) {
		log.debug("attaching clean TbBloqueio instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TbBloqueio persistentInstance) {
		log.debug("deleting TbBloqueio instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TbBloqueio merge(TbBloqueio detachedInstance) {
		log.debug("merging TbBloqueio instance");
		try {
			TbBloqueio result = (TbBloqueio) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TbBloqueio findById(TbBloqueioId id) {
		log.debug("getting TbBloqueio instance with id: " + id);
		try {
			TbBloqueio instance = (TbBloqueio) sessionFactory
					.getCurrentSession().get("controller.TbBloqueio", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbBloqueio instance) {
		log.debug("finding TbBloqueio instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("controller.TbBloqueio")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
