<!DOCTYPE html>
<html>
<head>
	<%@ taglib prefix="fatec" tagdir = "/WEB-INF/tags" %>
	<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fatec:genericpage title="Gerenciamento de Clientes">
<jsp:attribute name="body">
	<h1><b>Servicos</b></h1>
		<table style="width:100%">
			  <tr>
    			<th>ID</th>
    			<th>Nome</th>
    			<th>Descricao</th>
    			<th>Editar</th>
    			<th>Remover</th>
  			</tr>
				<c:forEach var="servico" items="${servicos}">
					<tr>
    					<th>${servico.id}</th>
    					<th>${servico.nome}</th>
    					<th>${servico.descricao}</th>
    					<th><a href="http://localhost:8081/23-empresa-CRUDS/formAlteraServico.html">Editar</a></th>
    					<th><a href="http://localhost:8081/23-empresa-CRUDS/ServicoStruts!remover?id=${servico.id}">Remover</a></th>
  					</tr>
  				</c:forEach>
		</table>
		<a href="http://localhost:8081/23-empresa-CRUDS/index.html">Voltar</a>
</jsp:attribute>
</fatec:genericpage>
</html>