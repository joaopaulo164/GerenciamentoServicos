package br.com.fatec.aulas.core.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.fatec.aulas.api.entity.Cliente;
import br.com.fatec.aulas.api.service.EntityDAO;
import br.com.fatec.aulas.core.helper.ClienteFactory;
import br.com.fatec.aulas.core.helper.ConfigDBMapper;
import br.com.fatec.aulas.core.service.GeradorIdService;

public class ClienteDAOImpl implements EntityDAO<Cliente> {

	private Connection connection;
	private ClienteFactory clienteFactory;

	public ClienteDAOImpl() {
		this.clienteFactory = new ClienteFactory();
		this.connection = ConfigDBMapper.getInstance().getDefaultConnection();
	}

	public Cliente save(Cliente cliente) {
		PreparedStatement insert = null;
		try {
			insert = this.connection.prepareStatement("INSERT INTO " + Cliente.TABLE + " VALUES (?,?,?,?,?,?);");
			Long id = GeradorIdService.getInstance().getNextId(Cliente.TABLE);
			insert.setLong(1, id);
			insert.setString(2, cliente.getNome());
			insert.setString(3, cliente.getSobrenome());
			insert.setString(4, cliente.getTelefone());
			insert.setDate(5, new Date(cliente.getDataCadastro().getTime()));
			insert.setString(6, cliente.getEmail());
			insert.execute();
			connection.prepareStatement("COMMIT;").execute();
			return this.findById(id);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar cliente.", e);
		}
	}

	public void remove(Cliente cliente) {
		PreparedStatement remove = null;
		try {
			remove = this.connection
					.prepareStatement("DELETE FROM " + Cliente.TABLE + " WHERE " + Cliente.COL_ID + " = ?;");
			remove.setLong(1, cliente.getId());
			remove.execute();
			connection.prepareStatement("COMMIT;").execute();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao remover cliente " + cliente.getNome() + " " + cliente.getSobrenome(), e);
		}
	}

	public List<Cliente> findAll() {
		PreparedStatement query = null;
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + Cliente.TABLE + ";");
			ResultSet resultado = query.executeQuery();
			clientes = this.clienteFactory.criarClientes(resultado);
			connection.prepareStatement("COMMIT;").execute();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar cliente.", e);
		}

		return clientes;
	}

	public Cliente findById(Long id) {
		PreparedStatement query = null;
		Cliente cliente = null;
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + Cliente.TABLE + " WHERE " + Cliente.COL_ID
					+ " = ?;");
			query.setLong(1, id);
			ResultSet resultado = query.executeQuery();
			if (resultado.next()) {
				cliente = this.clienteFactory.criarCliente(resultado);
				if (resultado.next()) {
					throw new RuntimeException("O ID " + id + " está duplicado");
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar cliente.", e);
		}
		return cliente;
	}

	public Cliente update(Cliente cliente) {
		PreparedStatement update = null;
		try {
			update = this.connection.prepareStatement("UPDATE " + Cliente.TABLE + " SET " + Cliente.COL_NOME + " = ?,"
					 + Cliente.COL_SOBRENOME + " = ?," + Cliente.COL_TELEFONE + " = ?," + Cliente.COL_DATA_CADASTRO + " = ? WHERE "
					+ Cliente.COL_ID + " = ?;");
			update.setString(1, cliente.getNome());
			update.setString(2, cliente.getSobrenome());			
			update.setString(3, cliente.getTelefone());
			update.setDate(4, new Date(cliente.getDataCadastro().getTime()));
			update.setLong(5, cliente.getId());
			update.execute();
			connection.prepareStatement("COMMIT;").execute();
			return this.findById(cliente.getId());
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar cliente " + cliente.getNome() + " " + cliente.getSobrenome(), e);
		}
	}
}
