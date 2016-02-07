<!DOCTYPE html>
<html>
<head>
	<%@ taglib prefix="fatec" tagdir = "/WEB-INF/tags" %>
	<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fatec:genericpage title="Gerenciamento de Clientes">
<jsp:attribute name="body">
	<h1><b>CLIENTES</b></h1>
		<table style="width:100%">
			  <tr>
    			<th>ID</th>
    			<th>Nome</th>
    			<th>Sobrenome</th>
    			<th>Telefone</th>
    			<th>Data Cadastro</th>
    			<th>E-mail</th>
    			<th>Editar</th>
    			<th>Remover</th>
  			</tr>
				<c:forEach var="cliente" items="${clientes}">
					<tr>
    					<th>${cliente.id}</th>
    					<th>${cliente.nome}</th>
    					<th>${cliente.sobrenome}</th>
    					<th>${cliente.telefone}</th>
    					<th>${cliente.dataCadastro}</th>
    					<th>${cliente.email}</th>
    					<th><a href="http://localhost:8081/23-empresa-CRUDS/formAlteraCliente.html">Editar</a></th>
    					<th><a href="http://localhost:8081/23-empresa-CRUDS/ClienteStruts!remover?id=${cliente.id}">Remover</a></th>
  					</tr>
  				</c:forEach>
		</table>
		<a href="http://localhost:8081/23-empresa-CRUDS/index.html">Voltar</a>
</jsp:attribute>
</fatec:genericpage>
</html>