package br.com.fatec.aulas.core.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.fatec.aulas.api.entity.Cliente;

public class ClienteFactory {

	public Cliente criarCliente(Long id, String nome, String sobrenome, String telefone, Date dataCadastro) {
		Cliente cliente = new Cliente();
		cliente.setId(id);
		cliente.setNome(nome);
		cliente.setSobrenome(sobrenome);
		cliente.setTelefone(telefone);
		cliente.setDataCadastro(dataCadastro);
		return cliente;
	}
	
	public Cliente criarCliente(Long id, String nome, String sobrenome, String telefone, Date dataCadastro, String mail) {
		Cliente cliente = new Cliente();
		cliente.setId(id);
		cliente.setNome(nome);
		cliente.setSobrenome(sobrenome);
		cliente.setTelefone(telefone);
		cliente.setDataCadastro(dataCadastro);
		cliente.setEmail(mail);
		return cliente;
	}

	public Cliente criarCliente(ResultSet resultado) {
		try {
			return this.criarCliente(resultado.getLong(Cliente.COL_ID), resultado.getString(Cliente.COL_NOME),
					 resultado.getString(Cliente.COL_SOBRENOME),resultado.getString(Cliente.COL_TELEFONE),
					 resultado.getDate(Cliente.COL_DATA_CADASTRO),resultado.getString(Cliente.COL_EMAIL));
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao criar cliente.", e);
		}
	}

	public List<Cliente> criarClientes(ResultSet resultado) {
		try {
			List<Cliente> clientes = new ArrayList<Cliente>();
			while (resultado.next()) {
				clientes.add(this.criarCliente(resultado));
			}
			return clientes;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao criar cliente.", e);
		}
	}

}
