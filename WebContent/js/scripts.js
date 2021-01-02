$(document).ready(function() {

	/* ====================================================================
	 *  MASCARAS
	 * ==================================================================== */
	$(".dataHora").mask("99/99/9999 99:99:99");
	
	/* ====================================================================
	 *  BUSCAR REPORTE : MAPA
	 * ==================================================================== */
    $('#frmBuscarReporte #Buscar').click(function(e){
    	e.preventDefault();
        
    	var Controle = $("#Controle").val();
    	var slcServico = $("#slcServico").val();
    	
    	$.ajax({
            type: "POST",
            data: { Controle:Controle, slcServico:slcServico },
             
            url: "/ProjetoHibernate/ReporteServlet.do",
            dataType: "json",
            success: function(result){
            	$('#ajaxModal').modal('hide');
            	if( result.length > 0 ){
            		carregarPontos(result);
            	} else {
            		if( $("#modalRetorno") ){
            			$("#modalRetorno").remove();
            		}
            		$tplModal = $tplModalSmall.replace("@mensagem", "Nenhuma equipe encontrada!");
            		$("body").append($tplModal);
            		$('#modalRetorno').modal('show');
            		initialize();
            	}
            },
            beforeSend: function(){
            	if( $("#ajaxModal") ){ $("#ajaxModal").remove(); }
            	$("body").append($tplAjaxLoad);
                $('#ajaxModal').modal('show');
            },
        });
    });
   
	/* ====================================================================
	 *  BUSCAR ATENDIMENTO : MAPA
	 * ==================================================================== */
    $("#frmBuscarAgendamento #Buscar").click(function(e){
    	e.preventDefault();
        
    	var Controle = $("#Controle").val();
    	var slcServico = $("#slcServico").val();
    	
    	$.ajax({
            type: "POST",
            data: { Controle:Controle, slcServico:slcServico },
             
            url: "/ProjetoHibernate/AtendimentoServlet.do",
            dataType: "json",
            success: function(result){
            	$('#ajaxModal').modal('hide');
            	if( result.length > 0 ){
            		porEndereco(result);
            	} else {
            		if( $("#modalRetorno") ){ $("#modalRetorno").remove(); }
            		$tplModal = $tplModalSmall.replace("@mensagem", "Nenhum atendimento encontrado!");
            		$("body").append($tplModal);
            		$('#modalRetorno').modal('show');
            		initialize();
            	}
            },
            beforeSend: function(){
            	if( $("#ajaxModal") ){ $("#ajaxModal").remove(); }
            	$("body").append($tplAjaxLoad);
                $('#ajaxModal').modal('show');
            },
        });
    });
    
	/* ====================================================================
	 *  TEMPLATE DE MODAL
	 * ==================================================================== */
    var $tplModalSmall = "" +
		"<div class='modal fade' id='modalRetorno' tabindex='-1' role='dialog' aria-labelledby='myModalLabel' aria-hidden='true'>" +
			"<div class='modal-dialog'>" + 
				"<div class='modal-content'>" +
					"<div class='modal-header'>" +
						"<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>&times;</button>" +
						"<h4 class='modal-title' id='myModalLabel'>Mensagem</h4>" +
					"</div>" +
					"<div class='modal-body'>@mensagem</div>" +
					"<div class='modal-footer'>" +
						"<button type='button' class='btn btn-default' data-dismiss='modal'>Fechar</button>" +
					"</div>" +
				"</div>" +
			"</div>" +
		"</div>";
    
	/* ====================================================================
	 *  TEMPLATE DE AJAX LOADING
	 * ==================================================================== */
    var $tplAjaxLoad = "" +
	"<div class='modal fade' id='ajaxModal' tabindex='-1' role='dialog' aria-labelledby='myModalLabel' aria-hidden='true'>" +
	"<div class='modal-dialog'>" + 
		"<div class='modal-content'>" +
			"<div class='modal-body'><p class='text-center'><img src='/ProjetoHibernate/img/carregando.gif'></p><p class='text-center'>Carregando...</p></div>" +
		"</div>" +
	"</div>" +
	"</div>";
    
	/* ====================================================================
	 *  BUSCAR SERVICO : MONTAR MODAL COM FORM
	 * ==================================================================== */
//    $("#BuscarServico").on("click", function(e){
//    	e.preventDefault();
//    	$.ajax({
//            type: "POST",
//            url: "/ProjetoHibernate/servico/formBuscarServico.jsp",
//            dataType: "html",
//            success: function(result){
//            	$('#ajaxModal').modal('hide');
//            		if( $("#modalRetorno") ){ $("#modalRetorno").remove(); }
//            		$tplModal = result;
//            		$("body").append($tplModal);
//            		$('#modalRetorno').modal('show');
//            		$(".dataHora").mask("99/99/9999 99:99:99");
//            },
//            beforeSend: function(){
//            	if( $("#ajaxModal") ){ $("#ajaxModal").remove(); }
//            	$("body").append($tplAjaxLoad);
//                $('#ajaxModal').modal('show');
//            },
//        });
//    });
    
	/* ====================================================================
	 *  CADASTRAR SERVICO : MONTAR MODAL COM FORM
	 * ==================================================================== */
//    $("#CadastrarServico").on("click", function(e){
//    	e.preventDefault();
//    	$.ajax({
//            type: "POST",
//            url: "/ProjetoHibernate/servico/formCadastrarServico.jsp",
//            dataType: "html",
//            success: function(result){
//            		$('#ajaxModal').modal('hide');
//            		if( $("#modalRetorno") ){ $("#modalRetorno").remove(); }
//            		$tplModal = result;
//            		$("body").append($tplModal);
//            		$('#modalRetorno').modal('show');
//            		$(".dataHora").mask("99/99/9999 99:99:99");
//            		
//            		btnCadastrarServico();
//            		
//            },
//            beforeSend: function(){
//            	if( $("#ajaxModal") ){ $("#ajaxModal").remove(); }
//            	$("body").append($tplAjaxLoad);
//                $('#ajaxModal').modal('show');
//            },
//        });
//    });
//    
//    
//    function btnCadastrarServico(){
//		$("#btnCadastrarServico").on("click", function(e){
//			e.preventDefault();
//			var dados = $("#formCadastrarServico").serialize();
//			
////			Mandar Ajax
//	    	$.ajax({
//	            type: "POST",
//	            data: dados,
//	            url: "/ProjetoHibernate/ServicoServlet.do",
//	            dataType: "JSON",
//	            success: function(result){
//            		if( $("#modalRetorno") ){ $("#modalRetorno").remove(); }
//            		
//            		if( result == "OK" ){ $tplModal = $tplModalSmall.replace("@mensagem", "Serviço cadastrado com sucesso!"); }
//            		else { $tplModal = $tplModalSmall.replace("@mensagem", "Erro ao cadastrar serviço!"); }
//
//            		$("body").append($tplModal);
//            		$('#modalRetorno').modal('show');
//            		
//            		
////	            	$(".mensagemRetorno").show().html(msg);
//	            		
//	            },
//	            beforeSend: function(){
//	            	if( $("#ajaxModal") ){ $("#ajaxModal").remove(); }
//	            	$("body").append($tplAjaxLoad);
//	                $('#ajaxModal').modal('show');
//	            },
//	        });
//
//		});
//    }
    
    
    $(".confirm-delete").on("click", function(e){
    	e.preventDefault();
    	$("#ConfirmarApagarServico").modal('show');
    	var id = $(this).data('id');
    	$("#IdApagar").val(id);
    });
    
    
    // Agendamento Cadastrar
    if( $("#CadastrarAgendamento") ){
    	$(".controle-cnpj").hide();
    	$(".controle-cpf").hide();
    	$("#TipoCliente").modal({backdrop : 'static', keyboard: false});
    }
    
    $("#cliente-cpf").on("click", function(e){
    	e.preventDefault();
    	$("#TipoCliente").modal("hide");
    	$(".controle-cnpj").hide();
    	$(".controle-cpf").show("fast");
    });
    
    $("#cliente-cnpj").on("click", function(e){
    	e.preventDefault();
    	$("#TipoCliente").modal("hide");
    	$(".controle-cpf").hide();
    	$(".controle-cnpj").show("fast");
    });
    
    
    $("#btnBuscarClienteAgendamento").on("click", function(e){
    	e.preventDefault();
    	
    	var Controle = "BuscarCliente";
    	var cpf = $("#cpf").val();
    	var cnpj = $("#cnpj").val();
    	
    	$.ajax({
            type: "POST",
            data: { Controle:Controle, cpf : cpf, cnpj : cnpj},
             
            url: "/ProjetoHibernate/ClienteServlet.do",
            dataType: "json",
            success: function(result){
            	$('#ajaxModal').modal('hide');
            	
            	if( result.length > 0 ){
                	
            		if( cpf!="" ){
                		$("#Nome").val(result);
                	} else {
                		$("#razao").val(result);
                	}
            		
            		$("#descricao").focus();
                	
            	} else {
            		if( $("#modalRetorno") ){
            			$("#modalRetorno").remove();
            		}
            		$tplModal = $tplModalSmall.replace("@mensagem", "Cliente não encontrado!");
            		$("body").append($tplModal);
            		$('#modalRetorno').modal('show');
            		
            	}
            	

        	},
            beforeSend: function(){
            	if( $("#ajaxModal") ){ $("#ajaxModal").remove(); }
            	$("body").append($tplAjaxLoad);
                $('#ajaxModal').modal('show');
            },
            
    	});
    });
    
});