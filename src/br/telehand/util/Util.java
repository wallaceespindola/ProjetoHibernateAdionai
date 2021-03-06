package br.telehand.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class Util {

private static final SessionFactory sessionFactory;
    
    static {
        try {
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    /*
     * data: tipo date / formato: ex: "dd/MM/yyyy HH:mm:ss"
     * */
    public static String DateParaString(Date data, String formato) {
		SimpleDateFormat formatador = new SimpleDateFormat(formato);  
		String dataFormatada = formatador.format(data);
		return dataFormatada;
    }
    
}