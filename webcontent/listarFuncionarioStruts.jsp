<!DOCTYPE html>
<html>
<head>
	<%@ taglib prefix="fatec" tagdir = "/WEB-INF/tags" %>
	<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fatec:genericpage title="Gerenciamento de Clientes">
<jsp:attribute name="body">
	<h1><b>Funcionarios</b></h1>
		<table style="width:100%">
			  <tr>
    			<th>ID</th>
    			<th>Nome</th>
    			<th>Sobrenome</th>
    			<th>Telefone</th>
    			<th>Data Admissao</th>
    			<th>Data Demissão</th>
    			<th>E-mail</th>
    			<th>Editar</th>
    			<th>Remover</th>
  			</tr>
				<c:forEach var="funcionario" items="${funcionarios}">
					<tr>
    					<th>${funcionario.id}</th>
    					<th>${funcionario.nome}</th>
    					<th>${funcionario.sobrenome}</th>
    					<th>${funcionario.telefone}</th>
    					<th>${funcionario.dataAdmissao}</th>
    					<th>${funcionario.dataDemissao}</th>
    					<th>${funcionario.email}</th>
    					<th><a href="http://localhost:8081/23-empresa-CRUDS/formAlteraFuncionario.html">Editar</a></th>
    					<th><a href="http://localhost:8081/23-empresa-CRUDS/FuncionarioStruts!remover?id=${funcionario.id}">Remover</a></th>
  					</tr>
  				</c:forEach>
		</table>
		<a href="http://localhost:8081/23-empresa-CRUDS/index.html">Voltar</a>
</jsp:attribute>
</fatec:genericpage>
</html>