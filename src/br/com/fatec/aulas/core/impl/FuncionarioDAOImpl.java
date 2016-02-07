package br.com.fatec.aulas.core.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.aulas.api.entity.Funcionario;
import br.com.fatec.aulas.api.service.EntityDAO;
import br.com.fatec.aulas.core.helper.ConfigDBMapper;
import br.com.fatec.aulas.core.helper.FuncionarioFactory;
import br.com.fatec.aulas.core.service.GeradorIdService;


public class FuncionarioDAOImpl implements EntityDAO<Funcionario> {

	private Connection connection;
	private FuncionarioFactory funcionarioFactory;

	public FuncionarioDAOImpl() {
		this.funcionarioFactory = new FuncionarioFactory();
		this.connection = ConfigDBMapper.getInstance().getDefaultConnection();
	}

	public Funcionario save(Funcionario funcionario) {
		PreparedStatement insert = null;
		try {
			insert = this.connection.prepareStatement("INSERT INTO " + Funcionario.TABLE + " VALUES (?,?,?,?,?,?,?);");
			Long id = GeradorIdService.getInstance().getNextId(Funcionario.TABLE);
			insert.setLong(1, id);
			insert.setString(2, funcionario.getNome());
			insert.setString(3, funcionario.getSobrenome());
			insert.setString(4, funcionario.getTelefone());
			insert.setDate(5, new Date(funcionario.getDataAdmissao().getTime()));
			insert.setDate(6, new Date(funcionario.getDataDemissao().getTime()));
			insert.setString(7, funcionario.getEmail());
			insert.execute();
			connection.prepareStatement("COMMIT;").execute();
			return this.findById(id);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar Funcionario.", e);
		}
	}

	public void remove(Funcionario funcionario) {
		PreparedStatement remove = null;
		try {
			remove = this.connection
					.prepareStatement("DELETE FROM " + Funcionario.TABLE + " WHERE " + Funcionario.COL_ID + " = ?;");
			remove.setLong(1, funcionario.getId());
			remove.execute();
			connection.prepareStatement("COMMIT;").execute();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao remover Funcionario " + funcionario.getNome(), e);
		}
	}

	public List<Funcionario> findAll() {
		PreparedStatement query = null;
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + Funcionario.TABLE + ";");
			ResultSet resultado = query.executeQuery();
			funcionarios = this.funcionarioFactory.criarFuncionarios(resultado);
			connection.prepareStatement("COMMIT;").execute();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar clientes.", e);
		}

		return funcionarios;
	}

	public Funcionario findById(Long id) {
		PreparedStatement query = null;
		Funcionario funcionario = null;
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + Funcionario.TABLE + " WHERE " + Funcionario.COL_ID
					+ " = ?;");
			query.setLong(1, id);
			ResultSet resultado = query.executeQuery();
			if (resultado.next()) {
				funcionario = this.funcionarioFactory.criarFuncionario(resultado);
				if (resultado.next()) {
					throw new RuntimeException("O ID " + id + " está duplicado");
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar Funcionario.", e);
		}
		return funcionario;
	}

	public Funcionario update(Funcionario funcionario) {
		PreparedStatement update = null;
		try {
			update = this.connection.prepareStatement("UPDATE " + Funcionario.TABLE + " SET " + Funcionario.COL_NOME + " = ?,"
					+ Funcionario.COL_SOBRENOME + " = ?," + Funcionario.COL_TELEFONE + " = ?,"
					+ Funcionario.COL_DATA_ADMISSAO + " = ?," + Funcionario.COL_DATA_DEMISSAO + " = ?,"
					+ Funcionario.COL_EMAIL + " = ? WHERE " + Funcionario.COL_ID + " = ?;");
			update.setString(1, funcionario.getNome());
			update.setString(2, funcionario.getSobrenome());
			update.setString(3, funcionario.getTelefone());
			update.setDate(4, new Date(funcionario.getDataAdmissao().getTime()));
			update.setDate(5, new Date(funcionario.getDataDemissao().getTime()));
			update.setString(6, funcionario.getEmail());
			update.setLong(7, funcionario.getId());
			update.execute();
			connection.prepareStatement("COMMIT;").execute();
			return this.findById(funcionario.getId());
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar Funcionario " + funcionario.getNome(), e);
		}
	}
}
