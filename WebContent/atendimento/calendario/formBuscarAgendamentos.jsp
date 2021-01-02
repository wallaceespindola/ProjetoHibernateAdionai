		<%@page import="br.telehand.dao.ServicoDAO"%>
		
		<%
			ServicoDAO DAOServico = new ServicoDAO();
		%>

		<div class="row-fluid">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">Calend�rio</h3>
				</div>
				<div class="panel-body">
					<form method="POST">
						<fieldset>
							<legend>Filtrar</legend>
							<div class="col-sm-6">
								<label>Tipo(s) de Servi�o(s)</label>
								<%="<select name='slcServico' id='slcServico' class='form-control'>"
									+ DAOServico.gerarOptions() + "</select>"%>
							</div>
							
							<div class="col-sm-6"><button type="submit" class="btn btn btn-primary" value="Buscar" id="CalendarioAgendamento">
								<span class="glyphicon glyphicon-search"></span> Buscar</button>
							</div>
							
						</fieldset>
						<input type="hidden" id="Controle" name="Controle" value="CalendarioAgendamento" />
					</form>
				</div>
			</div>
		</div>