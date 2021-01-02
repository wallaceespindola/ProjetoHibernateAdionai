package br.telehand.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONObject;

import br.telehand.dao.AtendimentoDAO;
import br.telehand.dao.ViewClienteDAO;
import br.telehand.model.TbAtendimento;
import br.telehand.model.ViewClientes;
import br.telehand.util.Util;

@WebServlet("/AtendimentoServlet")
public class AtendimentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AtendimentoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	/* ---------------------------------------------------- 
	 * TESTE DE PROCEDURE
	 * ----------------------------------------------------
	 */
	if ((request.getParameter("Controle") != null)
			&& (request.getParameter("Controle").equals("CadastrarAgendamento")) ){
		
		Integer idServico = Integer.parseInt(request.getParameter("idServico"));
		Integer idCategoria = Integer.parseInt(request.getParameter("slcCategoria"));
		
		String CPF = "";
		String CNPJ = "";
		
		// Verificar se CPF existe e guarda-lo na variavel CPF
		if( request.getParameter("cpf") != null ){ CPF = request.getParameter("cpf").toString(); }
		
		// Verificar se CNPJ existe e guarda-lo na variavel CNPJ
		if( request.getParameter("cnpj") != null ){ CNPJ = request.getParameter("cnpj").toString(); }
		
		String descricao = request.getParameter("descricao").toString();
		
		Integer matriculaTecnico = Integer.parseInt(request.getParameter("matriculaTecnico"));
		
		Integer usuarioLogado = Integer.parseInt(request.getParameter("usuarioLogado").toString());
		
		Integer idJanela = Integer.parseInt(request.getParameter("idJanela").toString());
		
		Integer idEquipe = Integer.parseInt(request.getParameter("idEquipe").toString());
		
		String dt_agendamento = request.getParameter("dt_agendamento").toString();
		
		
		// Inicia a sesssao
		Session session = Util.getSessionFactory().openSession();
		
		try {
			session.beginTransaction();
						
			Query query = session.createSQLQuery(
							"CALL pr_ins_agendamento(:p_id_servico, :p_id_categoria, :p_cpf, :p_cnpj, :p_tx_detalhe, :p_nr_matricula_tecnico, :p_nr_matricula_usuario, :p_id_janela, :p_id_equipe, :p_dt_agendamento)")
							.setParameter("p_id_servico", idServico)
							.setParameter("p_id_categoria", idCategoria)
							.setParameter("p_cpf", CPF)
							.setParameter("p_cnpj", CNPJ)
							.setParameter("p_tx_detalhe", descricao)
							.setParameter("p_nr_matricula_tecnico", matriculaTecnico)
							.setParameter("p_nr_matricula_usuario", usuarioLogado)
							.setParameter("p_id_janela", idJanela)
							.setParameter("p_id_equipe", idEquipe)
							.setParameter("p_dt_agendamento", dt_agendamento);
			
			int exRows = query.executeUpdate();
			
			System.out.println("ex: " + exRows);

			
//			System.out.println("=>");
//			
//			for( int i=0;i<result.size(); i++ ){
//				Testes testes = (Testes) result.get(i);
//				System.out.println(testes.getNome());
//			}
			
			
			
//			Query query = session.createSQLQuery(
//					"CALL BuscarNome(:p_id)")
//					.addEntity(Testes.class)
//					.setParameter("p_id", idCategoria);
//			
//			List result = query.list();
//			System.out.println("=>");
//			
//			for( int i=0;i<result.size(); i++ ){
//				Testes testes = (Testes) result.get(i);
//				System.out.println(testes.getNome());
//			}
			
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		
	}
		
		
		
		/* ---------------------------------------------------- 
		 * BUSCAR ATENDIMENTO
		 * ----------------------------------------------------
		 */
		if ((request.getParameter("Controle") != null)
				&& (request.getParameter("Controle").equals("Buscar"))
				&& (request.getParameter("slcServico") != null)) {

			Integer servicoSelecionado = Integer.parseInt(request.getParameter("slcServico"));

			// Recebe todo conteúdo para transformar em JSON
			JSONArray jArr = new JSONArray();

			// Buscar Agendamento que contenha Obj Servico
			AtendimentoDAO DAOAtendimento = new AtendimentoDAO();
			List<TbAtendimento> agendamentos = DAOAtendimento.listarIdServico(servicoSelecionado);
			
			for (int i = 0; i < agendamentos.size(); i++) {
				TbAtendimento agendamento = (TbAtendimento) agendamentos.get(i);
				
				JSONObject jObj = new JSONObject();
				jObj.put("IdAtendimento", agendamento.getIdAtendimento());
				jObj.put("IdOS", agendamento.getTbOs().getIdOs());
				jObj.put("DtAgendamento", Util.DateParaString(agendamento.getDtAgendamento(), "dd/MM/yyyy HH:mm:ss"));
				jObj.put("NrMatricula", agendamento.getNrMatricula());
				jObj.put("CdStatus", String.valueOf(agendamento.getCdStatus()));
				jObj.put("IdJanela", agendamento.getTbAgenda().getTbJanela().getIdJanela());
				jObj.put("IdEquipe", agendamento.getTbAgenda().getTbEquipe().getIdEquipe());
				jObj.put("Smartphone", agendamento.getTbAgenda().getTbEquipe().getNrSmarthfone());
				jObj.put("Servico", agendamento.getTbOs().getTbServico().getNmServico());
				
				// TODO : Alterar no UCS15
//				OK - nome do cliente.
//				OK - endereço do cliente
//				OK - telefone residencial do cliente
//				OK - telefone celular do cliente
//				OK - Dia e horário do agendamento
//				OK - Número da OS 
//
//				serviço
//				código da equipe
//				nome dos técnicos
//				smarthfone da equipe
				
				JSONObject jObj2 = new JSONObject();
				jObj2.put("tbAgendamento", jObj);
				
				String CNPJ = "";
				String CPF = "";
				
				if( (agendamento.getTbOs().getNrCnpj()) != null ){
					CNPJ = agendamento.getTbOs().getNrCnpj().toString();
				}
				
				if( (agendamento.getTbOs().getNrCpf()) != null ){
					CPF = agendamento.getTbOs().getNrCpf().toString();
				}
				
				// Resgatar dados do cliente
				ViewClienteDAO vDAO = new ViewClienteDAO();
				
				ViewClientes cliente = vDAO.findByDoc(CPF,CNPJ);
				
				JSONObject jObjC = new JSONObject();
				jObjC.put("IdCliente", cliente.getId_cliente());
				jObjC.put("CPF", cliente.getCpf());
				jObjC.put("CNPJ", cliente.getCnpj());
				jObjC.put("NmCliente", cliente.getNmCliente());
				jObjC.put("Email", cliente.getCdEmail());
				jObjC.put("Endereco", cliente.getEndereco());
				jObjC.put("Complemento", cliente.getComplemento());
				jObjC.put("Numero", cliente.getNumero());
				jObjC.put("Bairro", cliente.getBairro());
				jObjC.put("Cidade", cliente.getCidade());
				jObjC.put("UF", cliente.getUf());
				jObjC.put("CEP", cliente.getCep());
				jObjC.put("Telefone", cliente.getNrTel().toString());
				
				if(cliente.getNrCelular() != null){
					jObjC.put("Celular", cliente.getNrCelular().toString());
				} else {
					jObjC.put("Celular", "");
				}

				JSONObject jObjC2 = new JSONObject();
				jObjC2.put("tbCliente", jObjC);

				List lista = new ArrayList<>();
				lista.add(jObj2);
				lista.add(jObjC2);
				jArr.put(lista);
				
			}
			
			  String resposta = jArr.toString();

			  response.setContentType("text/json;charset=utf-8");
			  response.setHeader("Cache-Control", "no-cache");
			  
			  response.getWriter().write(resposta);

		} // Final controle buscar
		
		/* ---------------------------------------------------- 
		 * BUSCAR CALENDARIO
		 * ----------------------------------------------------
		 */
		if ((request.getParameter("Controle") != null)
				&& (request.getParameter("Controle").equals("BuscarCalendario"))
				&& (request.getParameter("slcServico") != null)) {

			Integer servicoSelecionado = Integer.parseInt(request.getParameter("slcServico"));

			// Recebe todo conteúdo para transformar em JSON
			JSONArray jArr = new JSONArray();

			// Buscar Agendamento que contenha Obj Servico
			AtendimentoDAO DAOAtendimento = new AtendimentoDAO();
			List<TbAtendimento> agendamentos = DAOAtendimento.listarIdServico(servicoSelecionado);
			
			for (int i = 0; i < agendamentos.size(); i++) {
				TbAtendimento agendamento = (TbAtendimento) agendamentos.get(i);
				
				JSONObject jObj = new JSONObject();
				jObj.put("IdAtendimento", agendamento.getIdAtendimento());
				jObj.put("IdOS", agendamento.getTbOs().getIdOs());
				jObj.put("DtAgendamento", Util.DateParaString(agendamento.getDtAgendamento(), "dd/MM/yyyy HH:mm:ss"));
				jObj.put("NrMatricula", agendamento.getNrMatricula());
				jObj.put("CdStatus", String.valueOf(agendamento.getCdStatus()));
				jObj.put("IdJanela", agendamento.getTbAgenda().getTbJanela().getIdJanela());
				jObj.put("IdEquipe", agendamento.getTbAgenda().getTbEquipe().getIdEquipe());
				jObj.put("Smartphone", agendamento.getTbAgenda().getTbEquipe().getNrSmarthfone());
				jObj.put("Servico", agendamento.getTbOs().getTbServico().getNmServico());
				
				JSONObject jObj2 = new JSONObject();
				jObj2.put("tbAgendamento", jObj);
				
				String CNPJ = "";
				String CPF = "";
				
				if( (agendamento.getTbOs().getNrCnpj()) != null ){
					CNPJ = agendamento.getTbOs().getNrCnpj().toString();
				}
				
				if( (agendamento.getTbOs().getNrCpf()) != null ){
					CPF = agendamento.getTbOs().getNrCpf().toString();
				}
				
				// Resgatar dados do cliente
				ViewClienteDAO vDAO = new ViewClienteDAO();
				
				ViewClientes cliente = vDAO.findByDoc(CPF,CNPJ);
				
				JSONObject jObjC = new JSONObject();
				jObjC.put("IdCliente", cliente.getId_cliente());
				jObjC.put("CPF", cliente.getCpf());
				jObjC.put("CNPJ", cliente.getCnpj());
				jObjC.put("NmCliente", cliente.getNmCliente());
				jObjC.put("Email", cliente.getCdEmail());
				jObjC.put("Endereco", cliente.getEndereco());
				jObjC.put("Complemento", cliente.getComplemento());
				jObjC.put("Numero", cliente.getNumero());
				jObjC.put("Bairro", cliente.getBairro());
				jObjC.put("Cidade", cliente.getCidade());
				jObjC.put("UF", cliente.getUf());
				jObjC.put("CEP", cliente.getCep());
				jObjC.put("Telefone", cliente.getNrTel().toString());
				
				if(cliente.getNrCelular() != null){
					jObjC.put("Celular", cliente.getNrCelular().toString());
				} else {
					jObjC.put("Celular", "");
				}

				JSONObject jObjC2 = new JSONObject();
				jObjC2.put("tbCliente", jObjC);

				List lista = new ArrayList<>();
				lista.add(jObj2);
				lista.add(jObjC2);
				jArr.put(lista);
				
			}
			
			  String resposta = jArr.toString();

			  response.setContentType("text/json;charset=utf-8");
			  response.setHeader("Cache-Control", "no-cache");

			  response.getWriter().write(resposta);

		} // Final controle buscar
		
	} // Final doPost

}
