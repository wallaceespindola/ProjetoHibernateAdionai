package br.telehand.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.telehand.model.ViewClientes;
import br.telehand.util.Util;

public class ViewClienteDAO {
	
	private static final Log log = LogFactory.getLog(ViewClientes.class);
	
	public ViewClientes findByDoc(String CPF, String CNPJ) {
		
		log.debug("getting ViewClientes instance with CPF: " + CPF + " CNPJ: " + CNPJ);
		
		String OP = null;
		String NUM = null;
		
		if( CPF != ""){  OP = "CPF";  NUM = CPF; }
		if( CNPJ != ""){ OP = "CNPJ"; NUM = CNPJ; }
				
		try {
			
			Session session = Util.getSessionFactory().openSession();
			
	        org.hibernate.Criteria criteria = session.createCriteria(ViewClientes.class);
	        
	        ViewClientes retorno = null;
	        
	        if( CPF != "" && CPF != null ){
	        	retorno = (ViewClientes) criteria.add(Restrictions.eq("cpf", CPF)).uniqueResult();
	        } else {
	        	retorno = (ViewClientes) criteria.add(Restrictions.eq("cnpj", CNPJ)).uniqueResult();
	        }
	        
			if (retorno == null) {
				log.debug("ViewClientes encontrado");
			} else {
				log.debug("ViewClientes nao encontrado");
			}
			
			return retorno;
			
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

}
