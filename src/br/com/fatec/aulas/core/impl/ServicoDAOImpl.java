package br.com.fatec.aulas.core.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.fatec.aulas.api.entity.Servico;
import br.com.fatec.aulas.api.service.EntityDAO;
import br.com.fatec.aulas.core.helper.ConfigDBMapper;
import br.com.fatec.aulas.core.helper.ServicoFactory;
import br.com.fatec.aulas.core.service.GeradorIdService;

public class ServicoDAOImpl implements EntityDAO<Servico> {

	private Connection connection;
	private ServicoFactory servicoFactory;
	private ServHistDAOImpl servHistDAOImpl;

	public ServicoDAOImpl() {
		this.servicoFactory = new ServicoFactory();
		this.servHistDAOImpl = new ServHistDAOImpl();
		this.connection = ConfigDBMapper.getInstance().getDefaultConnection();
	}

	public Servico save(Servico servico) {
		PreparedStatement insert = null;
		try {
			insert = this.connection.prepareStatement("INSERT INTO " + Servico.TABLE + " VALUES (?,?,?);");
			Long idServico = GeradorIdService.getInstance().getNextId(Servico.TABLE);
			insert.setLong(1, idServico);
			insert.setString(2, servico.getNome());
			insert.setString(3, servico.getDescricao());
			insert.execute();
			servico.setId(idServico);
			this.servHistDAOImpl.updateHistoricosByServico(servico);
			connection.prepareStatement("COMMIT;").execute();
			return this.findById(idServico);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar servico.", e);
		}
	}

	public void remove(Servico servico) {
		PreparedStatement remove = null;
		try {
			remove = this.connection
					.prepareStatement("DELETE FROM " + Servico.TABLE + " WHERE " + Servico.COL_ID + " = ?;");
			remove.setLong(1, servico.getId());
			remove.execute();
			connection.prepareStatement("COMMIT;").execute();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao remover servico " + servico.getNome(), e);
		}
	}

	public List<Servico> findAll() {
		PreparedStatement query = null;
		List<Servico> servicos = new ArrayList<Servico>();
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + Servico.TABLE + ";");
			ResultSet resultado = query.executeQuery();
			servicos = this.servicoFactory.criarServicos(resultado);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar cliente.", e);
		}

		return servicos;
	}

	public Servico findById(Long id) {
		PreparedStatement query = null;
		Servico servico = null;
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + Servico.TABLE + " WHERE " + Servico.COL_ID
					+ " = ?;");
			query.setLong(1, id);
			ResultSet resultado = query.executeQuery();
			if (resultado.next()) {
				servico = this.servicoFactory.criarServico(resultado);
				if (resultado.next()) {
					throw new RuntimeException("O ID " + id + " está duplicado");
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar cliente.", e);
		}

		return servico;
	}

	public Servico update(Servico servico) {
		PreparedStatement update = null;
		try {
			update = this.connection.prepareStatement("UPDATE " + Servico.TABLE + " SET " + Servico.COL_NOME + " = ?,"
					+ Servico.COL_DESCRICAO + " = ? WHERE "
					+ Servico.COL_ID + " = ?;");
			update.setString(1, servico.getNome());
			update.setString(2, servico.getDescricao());			
			update.setLong(3, servico.getId());
			update.execute();
			connection.prepareStatement("COMMIT;").execute();
			return this.findById(servico.getId());
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar servico " + servico.getNome(), e);
		}
	}

	public List<Servico> findAllByHistorico(Long historico_id) {
		PreparedStatement query = null;
		List<Servico> historico_encontrados = new ArrayList<Servico>();
		try {
			query = this.connection.prepareStatement("SELECT * FROM TBL_SERVICO WHERE ID IN (SELECT SERVICO_ID " +
					"FROM TBL_HIST_SERV WHERE historico_id = ?)");
			query.setLong(1, historico_id);
			ResultSet resultado = query.executeQuery();
			historico_encontrados = this.servicoFactory.criarServicos(resultado);
			query.close();
			return historico_encontrados;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao buscar historico.", e);
		}
	}
}
