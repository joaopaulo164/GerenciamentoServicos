<!DOCTYPE html>
<html>
<head>
	<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Servicos</title>
</head>
	<body>
		<c:forEach var="entidade" items="${listaEntidades}">
			<p>${entidade.nome}</p>
		</c:forEach>
	</body>
</html>