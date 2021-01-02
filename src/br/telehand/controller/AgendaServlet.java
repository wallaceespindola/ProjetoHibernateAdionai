package br.telehand.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import br.telehand.dao.VwAgendaComAgendamentoDAO;
import br.telehand.dao.VwAgendaSemAgendamentoDAO;
import br.telehand.model.VwAgendaComAgendamento;
import br.telehand.model.VwAgendaSemAgendamento;
import br.telehand.util.Util;

/**
 * Servlet implementation class AgendaServlet
 */
@WebServlet("/AgendaServlet")
public class AgendaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgendaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* ====================================================================
		 *  CADASTRAR AGENDAMENTO : RESGATAR HRs E DIA, TRANSPORTAR PARA O FORM
		 * ==================================================================== */
		if( (request.getParameter("Controle") != null) && (request.getParameter("Controle").equals("CadastrarAgendamento")) ){
			
			request.setAttribute("hInicial", request.getParameter("hInicial") );
			request.setAttribute("hFinal", request.getParameter("hFinal") );
			request.setAttribute("dia", request.getParameter("dia") );
			request.setAttribute("idServico", request.getParameter("idServico").toString() );
			request.setAttribute("idJanela", request.getParameter("idJanela").toString() );
			request.setAttribute("idEquipe", request.getParameter("idEquipe").toString() );
			request.setAttribute("usuarioLogado", request.getParameter("usuarioLogado").toString() );
			request.setAttribute("dt_agendamento", request.getParameter("dt_agendamento").toString() );
			request.setAttribute("matriculaTecnico", request.getParameter("matriculaTecnico").toString() );
			
			RequestDispatcher view = request.getRequestDispatcher("atendimento/calendario/formCadastrar.jsp");
			view.forward(request, response);
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* ====================================================================
		 *  RESGATAR AGENDA COM/SEM AGENDAMENTOS POR IdServico
		 * ==================================================================== */
		if( (request.getParameter("Controle") != null) && (request.getParameter("Controle").equals("CalendarioAgendamento")) ){
		
		Integer IdServico = Integer.parseInt(request.getParameter("slcServico"));
		
		JSONArray jArr = new JSONArray(); // Array que vai receber a lista de arrays com os dados dos agendamentos/janelas
		JSONObject s = new JSONObject(); // Array de resultado de sucesso
		s.put("success", 1); // Notificar 1 = sucesso
		
		VwAgendaComAgendamentoDAO vcDAO = new VwAgendaComAgendamentoDAO();
		List<VwAgendaComAgendamento> listaC = vcDAO.listarPorIdServico(IdServico);
		
		System.out.println("================== Com agendamento ==================");

		for(int i=0; i<listaC.size(); i++){
			VwAgendaComAgendamento obj = (VwAgendaComAgendamento) listaC.get(i);
			
			// Gerar numero unico para cada objeto
			Integer hash = obj.getId().hashCode();
			if( hash < 0 ){ hash = hash*-1;	}
			
			System.out.println(hash);
			
			JSONObject objJSON = new JSONObject();
			objJSON.put("id", hash);			
			objJSON.put("url", "http://localhost:8080/ProjetoHibernate");
			objJSON.put("class", "event-important"); // event-warning = bolinha vermelha
			
			// Transformar String em Date
			SimpleDateFormat fTotal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				
				// Pegar apenas a hora final e hora inicial, removendo o 1970-xx-xx
				DateFormat fHora = DateFormat.getTimeInstance();
				String hInicial = fHora.format(obj.getId().getHrInicial());
				String hFinal = fHora.format(obj.getId().getHrFinal());
				
				// Dividir o 'yyyy-MM-dd HH:mm:ss' => 'yyyy-MM-dd'
				String data = obj.getId().getData();
				String[] divide = data.split(" ");
				String ano_mes_dia = divide[0];
				
				// Montar 'yyyy-MM-dd HH:mm:ss' com a hora inicial e hora final certas
				String dataFormatadaInicial = ano_mes_dia + " " + hInicial;
				String dataFormatadaFinal = ano_mes_dia + " " + hFinal;
				
				// Transformar as datas montadas em Date
				Date dateI = fTotal.parse(dataFormatadaInicial);
				Date dateF = fTotal.parse(dataFormatadaFinal);
				
				// Transformar as datas montadas em Millisegundos
				long lDateI = dateI.getTime();
				long lDateF = dateF.getTime();
				
				objJSON.put("start", lDateI);
				objJSON.put("end", lDateF);
				
				// Montar Titulo
				objJSON.put("title", "Agenda Ocupada - " + hInicial + " - " + hFinal);
				
				// Inserir o resultado no array
				jArr.put(objJSON);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
		
		// Agenda sem agendamento
		VwAgendaSemAgendamentoDAO vsDAO = new VwAgendaSemAgendamentoDAO();
		List<VwAgendaSemAgendamento> listaS = vsDAO.listarPorIdServico(IdServico);
		System.out.println("================== Sem agendamento ==================");

		for(int i=0; i<listaS.size(); i++){
			VwAgendaSemAgendamento obj = (VwAgendaSemAgendamento) listaS.get(i);
		
			// Gerar numero unico para cada objeto
			Integer hash = obj.getId().hashCode();
			if( hash < 0 ){ hash = hash*-1;	}

			Integer IdEquipe = obj.getId().getIdEquipe();
			Integer IdJanela = obj.getId().getIdJanela();
			Integer matriculaTecnico = obj.getId().getNrMatriculaTecnico();
			
			JSONObject objJSON = new JSONObject();
			objJSON.put("id", hash);
			objJSON.put("class", "event-success"); // event-success = bolinha verde
			
			// Transformar String em Date
			SimpleDateFormat fTotal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				
				// Pegar apenas a hora final e hora inicial, removendo o 1970-xx-xx
				DateFormat fHora = DateFormat.getTimeInstance();
				String hInicial = fHora.format(obj.getId().getHrInicial());
				String hFinal = fHora.format(obj.getId().getHrFinal());
				
				// Dividir o 'yyyy-MM-dd HH:mm:ss' => 'yyyy-MM-dd'
				String ano_mes_dia = Util.DateParaString(obj.getId().getAnoMesDia(), "yyyy-MM-dd");
				
				// Montar 'yyyy-MM-dd HH:mm:ss' com a hora inicial e hora final certas
				String dataFormatadaInicial = ano_mes_dia + " " + hInicial;
				String dataFormatadaFinal = ano_mes_dia + " " + hFinal;
				
				// Transformar as datas montadas em Date
				Date dateI = fTotal.parse(dataFormatadaInicial);
				Date dateF = fTotal.parse(dataFormatadaFinal);
				
				// Transformar as datas montadas em Millisegundos
				long lDateI = dateI.getTime();
				long lDateF = dateF.getTime();
				
				objJSON.put("start", lDateI);
				objJSON.put("end", lDateF);
				
				// Montar Titulo
				objJSON.put("title", "Agenda Disponível - " + hInicial + " - " + hFinal);
				objJSON.put("url", "http://localhost:8080/ProjetoHibernate/AgendaServlet.do?hInicial=" + 
				hInicial + "&hFinal=" + hFinal + "&dia=" + ano_mes_dia + "&idServico="+ IdServico + "&idEquipe=" + IdEquipe + 
				"&usuarioLogado=1" + "&idJanela=" + IdJanela + "&dt_agendamento=" + dataFormatadaInicial + "&matriculaTecnico=" + matriculaTecnico + "&Controle=CadastrarAgendamento");
				
				// Inserir o resultado no array
				jArr.put(objJSON);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
		
		s.put("result", jArr);
		
		String resposta = s.toString();
		
		response.setContentType("text/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");	  
		response.getWriter().write(resposta);
		} // FIM RESGATAR AGENDA COM/SEM AGENDAMENTOS POR IdServico
		
		
	}
}
