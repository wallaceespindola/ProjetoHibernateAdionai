package br.telehand.dao;

// Generated 27/03/2014 15:10:19 by Hibernate Tools 4.0.0

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.telehand.model.TbServico;
import br.telehand.util.Util;
/**
 * Home object for domain model class TbServico.
 * @see controller.TbServico
 * @author Hibernate Tools
 */
public class ServicoDAO {

	private static final Log log = LogFactory.getLog(ServicoDAO.class);
	
	public List<TbServico> listarTodosServicos() {
		List<TbServico> list = null;
		
		Session session = Util.getSessionFactory().openSession();
		
		try {
			session.beginTransaction();
			list = session.createQuery("from TbServico").list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return list;
	}
	
	public TbServico selectById(int id) {
		log.debug("getting TbAtendimento instance with id: " + id);
		try {

			Session session = Util.getSessionFactory().openSession();
			Criteria cr = session.createCriteria(TbServico.class);
			TbServico retorno = (TbServico) cr.add( Restrictions.eq("idServico", id) ).uniqueResult();
			
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
	
    public String cadastrarServico(TbServico servico) {
        Session sessao = Util.getSessionFactory().openSession();
        String retorno = null;
        try {
            sessao.beginTransaction();
            sessao.save(servico);
            sessao.flush();
            sessao.getTransaction().commit();
//            retorno = "OK";
            retorno = "Servi�o cadastrado com sucesso!";
        } catch (Exception ex) {
            sessao.getTransaction().rollback();
//            retorno = "ERRO";
            retorno = "Erro ao cadastrar servi�o!";
        } finally {
            sessao.close();
        }
        return retorno;
    }
    
    public String atualizarServico(TbServico servico) {
        Session session = Util.getSessionFactory().openSession();
        String retorno = null;
        try {
            session.beginTransaction();
            session.update(servico);
            session.flush();
            session.getTransaction().commit();
            retorno = "Servi�o atualizado com sucesso!";
        } catch (Exception e) {
            retorno = "Erro na atualizacao do servico";
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return retorno;
    }

    public String apagarServico(TbServico servico) {
    	
    	Session session = Util.getSessionFactory().openSession();
    	
        String retorno = null;
        
        try {
            session.beginTransaction();
            TbServico auxiliar = (TbServico) session.get(TbServico.class, servico.getIdServico());
            session.delete(auxiliar);
            session.getTransaction().commit();
            retorno = "Servi�o apagado com sucesso!";
        } catch (Exception e) {
            retorno = "Ocorreu um erro ao apagar o servi�o!";
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return retorno;
    }
    

	public List<TbServico> buscarServico(String nm_servico, String qt_inicio, String qt_fim, String qtd_emp, String dt_vigencia) {
		
		log.debug("getting TbServico instance with id: ");
		
		List<TbServico> list = null;
		
		try {
			
			Session session = Util.getSessionFactory().openSession();
			
			Criteria cr = session.createCriteria(TbServico.class);
			
			// Remove os duplicados
			cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);  
			
		    if( !nm_servico.equals("") ){
		    	cr.add( Restrictions.and( Restrictions.like("nmServico", nm_servico, MatchMode.ANYWHERE)) );
		    }
		    
		    // Verificar qt_inicio
			if( !qt_inicio.equals("") ){
				cr.add( Restrictions.and( Restrictions.eq("qtInicio", Integer.parseInt(qt_inicio)) ) );
			}
			
		    // Verificar qt_fim
			if( !qt_fim.equals("") ){
				cr.add( Restrictions.and( Restrictions.eq("qtFim", Integer.parseInt(qt_fim)) ) );
			}
			
		    // Verificar qtd_emp
			if( !qtd_emp.equals("") ){
				cr.add( Restrictions.and( Restrictions.eq("qtEmp", Integer.parseInt(qtd_emp)) ) );
			}
			
		    // Verificar dt_vigencia
			if( !dt_vigencia.equals("") ){
				
				SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");    
		        try {  
		            Date dataCerta = (Date) formataData.parse(dt_vigencia);
		            cr.add( Restrictions.and( Restrictions.eq("dtVigencia", dataCerta) ) );
		        } catch (ParseException e) {  
		            e.printStackTrace();  
		        }  
			}
			
			List retorno = cr.list();

			return retorno;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
 
    /* ================================================================================================
	 * METODOS AUXILIARES
	 * ================================================================================================ */
	
    public String gerarOptions(){
    	String retorno = "";
    	
    	List<TbServico> lista = this.listarTodosServicos();
    	
    	for(int i = 0; i<lista.size(); i++){
    		retorno += "<option value='"+ lista.get(i).getIdServico() +"'>" + lista.get(i).getNmServico() + "</option>";
    	}
    	
    	return retorno;
    }
	
}
