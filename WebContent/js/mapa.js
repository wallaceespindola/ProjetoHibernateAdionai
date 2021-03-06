var map;
var idInfoBoxAberto;
var infoBox = [];
var markers = [];

function initialize() {	
	var latlng = new google.maps.LatLng(-18.8800397, -47.05878999999999);
	
    var options = {
        zoom: 5,
		center: latlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };

    map = new google.maps.Map(document.getElementById("mapa"), options);
}

initialize();

function abrirInfoBox(id, marker) {
	if (typeof(idInfoBoxAberto) == 'number' && typeof(infoBox[idInfoBoxAberto]) == 'object') {
		infoBox[idInfoBoxAberto].close();
	}

	infoBox[id].open(map, marker);
	idInfoBoxAberto = id;
}

function carregarPontos(pontos) {
	
	//	Limpar Pontos no Mapa
	limparMarcadores();

		var latlngbounds = new google.maps.LatLngBounds();
		
		$.each(pontos, function(index, ponto) {
			
			var Cliente = pontos[index][1].tbCliente;
			var Agendamento = pontos[index][0].tbAgendamento;
			
			/* Verificar qual o tipo de marcardor de acordo com o tipo de serviço
			   C – A caminho
			   E – Em atendimento
			   A – Atendimento Finalizado
			   P – Atendimento Pendente
			*/
			var Marcador = "/ProjetoHibernate/img/marcador.png";
			switch( Agendamento.Cdstatus ){
				case "c" : Marcador = "/ProjetoHibernate/img/marcador_acaminho.png"; break;
				case "e" : Marcador = "/ProjetoHibernate/img/marcador_ematendimento.png"; break;
				case "a" : Marcador = "/ProjetoHibernate/img/marcador_finalizado.png"; break;
				case "p" : Marcador = "/ProjetoHibernate/img/marcador_pendente.png"; break;
			}
			
			var marker = new google.maps.Marker({
				position: new google.maps.LatLng(Agendamento.Latitude, Agendamento.Longitude),
//				title: "OS: " + Id + " Endere�o: " + Endereco,
				title: "Ponto",
				icon: Marcador
			});
			
/*
{
        "tbAgendamento": {
            "IdOS": 1,
            "CdStatus": "c",
            "Servico": "Instala��o Banda Larga 2M",
            "DtReporte": "23/03/2014 00:00:00",
            "Latitude": "-22.9334",
            "Longitude": "-43.1767"
        }
    }, {
        "tbCliente": {
            "Numero": "100",
            "Complemento": "CB 2",
            "IdCliente": 1,
            "Cidade": "Rio de Janeiro",
            "Email": "cliente@gmail.com\n",
            "CEP": "21221000",
            "CPF": "10000000001",
            "NmCliente": "Jos� Silva",
            "Telefone": "34238978",
            "UF": "RJ",
            "Bairro": "Jardim Guanabara",
            "Endereco": "Rua Nogueira Acioli",
            "Celular": "98987647"
        }
 * */
			
		// Verificar numero
		if (Cliente.Numero != undefined) { var NumeroEndereco = ", " + Cliente.Numero + " - "; } 
		else { var NumeroEndereco = " - "; }

		// Verificar CEP
		if (Cliente.CEP != undefined) { var CEP = ", " + Cliente.CEP; } 
		else { var CEP = ""; }
		
		// Verificar Complemento
		if (Cliente.Complemento != undefined) { var Complemento = Cliente.Complemento + " - "; } 
		else { var Complemento = ""; }

		var Endereco = Cliente.Endereco + NumeroEndereco + Cliente.Cidade + ", " + Cliente.UF;
			Endereco += CEP;
			
		var EnderecoCompleto = Cliente.Endereco + NumeroEndereco + Complemento + Cliente.Cidade + ", " + Cliente.UF;
			EnderecoCompleto += CEP;
			
		var tplConteudo = "<p><strong>OS: #" + Agendamento.IdOS + "</strong> - �ltimo Contato: " + Agendamento.DtReporte +"</p>";
			tplConteudo += "<p><strong>Status:</strong> "+ Agendamento.CdStatus +"</p>";
			tplConteudo += "<p><strong>Cliente:</strong> "+ Cliente.NmCliente +"</p>";
			tplConteudo += "<p><strong>Endere�o:</strong> "+ EnderecoCompleto +"</p>";
			tplConteudo += "<p><strong>Telefone / Celular:</strong> "+ Cliente.Telefone + " / " + Cliente.Celular +"</p>";
			tplConteudo += "<p><strong>Email:</strong> "+ Cliente.Email +"</p>";
			tplConteudo += "<p><strong>Equipe:</strong> PegarDadosdaEquipe / <strong>Smartphone: </strong> Agendamento.Smartphone </p>";

			var myOptions = {
				content: tplConteudo,
				pixelOffset: new google.maps.Size(-150, 0)
        	};

			infoBox[Agendamento.IdOS] = new InfoBox(myOptions);
			infoBox[Agendamento.IdOS].marker = marker;
			
			infoBox[Agendamento.IdOS].listener = google.maps.event.addListener(marker, 'click', function (e) {
				abrirInfoBox(Agendamento.IdOS, marker);
			});
			
			markers.push(marker);
			
			latlngbounds.extend(marker.position);
			
		});
		
		var markerCluster = new MarkerClusterer(map, markers);
		
		map.fitBounds(latlngbounds);
}

function limparMarcadores() {
	if (markers) {
		for (i in markers) {
			markers[i].setMap(null);
		}
		markers.length = 0;
	}
	
	if(infoBox){
		for (i in infoBox) {
			infoBox[i].setMap(null);
		}
		infoBox.length = 0;		
	}
	
}

function porEndereco(pontos) {
	
	//	Limpar Pontos no Mapa
	limparMarcadores();
	
	//	Verificar se a busca retornou algum resultado!
	if(pontos.length != 0){

	var latlngbounds = new google.maps.LatLngBounds();

	for ( var i = 0; i < pontos.length; i++) {

		var Cliente = pontos[i][1].tbCliente;
		var Agendamento = pontos[i][0].tbAgendamento;

		// Verificar numero
		if (Cliente.Numero != undefined) { var NumeroEndereco = ", " + Cliente.Numero + " - "; } 
		else { var NumeroEndereco = " - "; }

		// Verificar CEP
		if (Cliente.CEP != undefined) { var CEP = ", " + Cliente.CEP; } 
		else { var CEP = ""; }
		
		// Verificar Complemento
		if (Cliente.Complemento != undefined) { var Complemento = Cliente.Complemento + " - "; } 
		else { var Complemento = ""; }

		var Endereco = Cliente.Endereco + NumeroEndereco + Cliente.Cidade + ", " + Cliente.UF;
			Endereco += CEP;
			
		var IdOS = Agendamento.IdOS;
		
		var NomeCliente = Cliente.NmCliente;
		var EnderecoCompleto = Cliente.Endereco + NumeroEndereco + Complemento + Cliente.Cidade + ", " + Cliente.UF;
			EnderecoCompleto += CEP;
			
		var EmailCliente  = Cliente.Email;
		var DtAgendamento = Agendamento.DtAgendamento;
		var IdEquipe = Agendamento.IdEquipe;
		var CdStatus = Agendamento.CdStatus;
		var Telefone = Cliente.Telefone;
		var Celular  = Cliente.Celular;
		
		var tplConteudo = "<p><strong>OS: #" + IdOS + "</strong> - " + DtAgendamento +"</p>";
			tplConteudo += "<p><strong>Status:</strong> "+ CdStatus +"</p>";
			tplConteudo += "<p><strong>Cliente:</strong> "+ NomeCliente +"</p>";
			tplConteudo += "<p><strong>Endere�o:</strong> "+ EnderecoCompleto +"</p>";
			tplConteudo += "<p><strong>Telefone / Celular:</strong> "+ Telefone + " / " + Celular +"</p>";
			tplConteudo += "<p><strong>Email:</strong> "+ EmailCliente +"</p>";
			tplConteudo += "<p><strong>Equipe:</strong> "+ IdEquipe + " / <strong>Smartphone: </strong>" + Agendamento.Smartphone + "</p>";

		gerarPontoEndereco(Endereco, IdOS, tplConteudo);
	}

		var markerCluster = new MarkerClusterer(map, markers);

		map.fitBounds(latlngbounds);
	} else {
		alert('Nenhum agendamento encontrado!');
	}
		
}

function gerarPontoEndereco(Endereco, Id, Conteudo) {

	var geocoder = new google.maps.Geocoder();
	var latlngbounds = new google.maps.LatLngBounds();

	geocoder.geocode({
		'address' : Endereco + ', Brasil',
		'region' : 'BR'
	}, function(results, status) {
		if (status == google.maps.GeocoderStatus.OK) {
			map.setCenter(results[0].geometry.location);

			var MarcadorIcone = "/ProjetoHibernate/img/marcador.png";

			var marker = new google.maps.Marker({
				map : map,
				position : results[0].geometry.location,
				title: "OS: " + Id + " Endere�o: " + Endereco,
				icon : MarcadorIcone
			});

			var myOptions = {
				content : Conteudo,
				pixelOffset : new google.maps.Size(-150, 0)
			};

			infoBox[Id] = new InfoBox(myOptions);
			infoBox[Id].marker = marker;

			infoBox[Id].listener = google.maps.event.addListener(marker,
					'click', function(e) {
						abrirInfoBox(Id, marker);
					});

			markers.push(marker);

			latlngbounds.extend(marker.position);

		} else {
			alert('Nao foi poss�vel localizar o endere�o: '
					+ status);
		}
	});
}