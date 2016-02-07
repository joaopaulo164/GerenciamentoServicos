<!DOCTYPE html>
<html>
<head>
	<%@ taglib prefix="fatec" tagdir = "/WEB-INF/tags" %>
	<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fatec:genericpage title="Gerenciamento de Clientes">
<jsp:attribute name="body">
	<h1><b>HISTORICOS</b></h1>
		<table style="width:100%">
			  <tr>
    			<th>ID</th>
    			<th>Cliente</th>
    			<th>Funcionario</th>
    			<th>Servicos</th>
    			<th>Data Realizacao</th>
    			<th>Valor</th>
    			<th>Observacao</th>
    			<th></th>
    			<th></th>
  			</tr>
				<c:forEach var="historico" items="${historicos}">
					<tr>
    					<th>${historico.id}</th>
    					<th>${historico.getCliente().getNome()} ${historico.getCliente().getSobrenome()}</th>
    					<th>${historico.getFuncionario().getNome()} ${historico.getCliente().getSobrenome()}</th>
    					<th><a href="http://localhost:8081/23-empresa-CRUDS/HistoricoStruts!listarServicos?id=${historico.id}" target="new">Expandir</a></th>
    					<th>${historico.dataRealizacao}</th>
    					<th>${historico.valor}</th>
    					<th>${historico.observacao}</th>
    					<th><a href="http://localhost:8081/23-empresa-CRUDS/formAlteraHistorico.html">Editar</a></th>
    					<th><a href="http://localhost:8081/23-empresa-CRUDS/HistoricoStruts!remover?id=${historico.id}">Remover</a></th>
  					</tr>
  				</c:forEach>
		</table>
		<a href="http://localhost:8081/23-empresa-CRUDS/index.html">Voltar</a>
</jsp:attribute>
</fatec:genericpage>
</html>