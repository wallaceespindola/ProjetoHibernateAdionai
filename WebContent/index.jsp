<%@page import="br.telehand.dao.AtendimentoDAO"%>
<%@page import="br.telehand.dao.AgendaDAO"%>
<%@page import="br.telehand.model.ViewClientes"%>
<%@page import="br.telehand.dao.ViewClienteDAO"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Telehand</title>
	
	<jsp:include page="templates/scripts.jsp"></jsp:include>
	<jsp:include page="templates/estilos.jsp"></jsp:include>
</head>

<body>
<div class="container">

	<!-- Include de menu -->
	<jsp:include page="templates/menu.jsp"></jsp:include>
	
	<%
		
    long lDateTime = new Date().getTime();
    out.println("Date() - Time in milliseconds: " + lDateTime);

    Calendar lCDateTime = Calendar.getInstance();
    out.println("Calender - Time in milliseconds :" + lCDateTime.getTimeInMillis());
	
	%>

</div><!-- /.container -->

</body>
</html>