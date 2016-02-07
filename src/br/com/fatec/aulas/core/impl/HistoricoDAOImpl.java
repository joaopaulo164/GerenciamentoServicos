package br.com.fatec.aulas.core.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.fatec.aulas.api.entity.Historico;
import br.com.fatec.aulas.api.service.EntityDAO;
import br.com.fatec.aulas.core.helper.ConfigDBMapper;
import br.com.fatec.aulas.core.helper.HistoricoFactory;
import br.com.fatec.aulas.core.service.GeradorIdService;


public class HistoricoDAOImpl implements EntityDAO<Historico> {

	private Connection connection;
	private HistoricoFactory historicoFactory;
	private ServHistDAOImpl servHistDAOImpl;

	/** */
	public HistoricoDAOImpl() {
		this.historicoFactory = new HistoricoFactory();
		this.servHistDAOImpl = new ServHistDAOImpl();
		this.connection = ConfigDBMapper.getInstance().getDefaultConnection();
	}

	public Historico save(Historico historico) {
		PreparedStatement insert = null;
		try {
			insert = this.connection.prepareStatement("INSERT INTO " + Historico.TABLE + " VALUES (?,?,?,?,?,?);");
			Long idHistorico = GeradorIdService.getInstance().getNextId(Historico.TABLE);
			insert.setLong(1, idHistorico);
			insert.setLong(2, historico.getCliente().getId());
			insert.setLong(3, historico.getFuncionario().getId());
			insert.setDate(4, new Date(historico.getDataRealizacao().getTime()));
			insert.setDouble(5, historico.getValor());
			insert.setString(6, historico.getObservacao());
			insert.execute();
			historico.setId(idHistorico);
			this.servHistDAOImpl.updateServicosByHistorico(historico);
			connection.prepareStatement("COMMIT;").execute();
			return this.findById(idHistorico);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar servico.", e);
		}
	}

	public void remove(Historico historico) {
		PreparedStatement remove = null;
		try {
			remove = this.connection
					.prepareStatement("DELETE FROM TBL_HIST_SERV WHERE HISTORICO_ID = ?;");
			remove.setLong(1, historico.getId());
			remove.execute();
			
			remove = this.connection
					.prepareStatement("DELETE FROM " + Historico.TABLE + " WHERE " + Historico.COL_ID + " = ?;");
			remove.setLong(1, historico.getId());
			remove.execute();
			connection.prepareStatement("COMMIT;").execute();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao remover o historico de servico " + historico.getId(), e);
		}
	}

	public List<Historico> findAll() {
		PreparedStatement query = null;
		List<Historico> servicos = new ArrayList<Historico>();
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + Historico.TABLE + ";");
			ResultSet resultado = query.executeQuery();
			servicos = this.historicoFactory.criarHistoricos(resultado);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar cliente.", e);
		}
		return servicos;
	}

	public Historico findById(Long id) {
		PreparedStatement query = null;
		Historico historico = null;
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + Historico.TABLE
					+ " WHERE " + Historico.COL_ID
					+ " = ?;");
			query.setLong(1, id);
			ResultSet resultado = query.executeQuery();
			if (resultado.next()) {
				historico = this.historicoFactory.criarHistorico(resultado);
				if (resultado.next()) {
					throw new RuntimeException("O ID " + id + " está duplicado");
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar historico.", e);
		}
		return historico;
	}

	public Historico update(Historico historico) {
		PreparedStatement update = null;
		try {
			update = this.connection.prepareStatement("UPDATE "+ Historico.TABLE + " SET "
					+ Historico.COL_CLIENTE_ID + " = ?," 
					+ Historico.COL_FUNCIONARIO_ID + " = ?,"
					+ Historico.COL_DATA_REALIZACAO + " = ?," 
					+ Historico.COL_VALOR  + " = ?,"
					+ Historico.COL_OBSERVACAO + " = ? WHERE "
					+ Historico.COL_ID + " = ?;");
			update.setLong(1, historico.getCliente().getId());
			update.setLong(2, historico.getFuncionario().getId());
			update.setDate(3, new Date(historico.getDataRealizacao().getTime()));
			update.setDouble(4, historico.getValor());
			update.setString(5, historico.getObservacao());
			update.setLong(6, historico.getId());
			update.execute();
			this.servHistDAOImpl.updateServicosByHistorico(historico);
			connection.prepareStatement("COMMIT;").execute();
			return this.findById(historico.getId());
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar cliente " + historico.getId(), e);
		}
	}

	public List<Historico> findAllByServico(Long servico_id) {
		PreparedStatement query = null;
		List<Historico> servico_encontrados = new ArrayList<Historico>();
		try {
			query = this.connection.prepareStatement("SELECT * FROM TBL_HISTORICO WHERE ID IN (SELECT HISTORICO_ID " +
					"FROM TBL_HIST_SERV WHERE servico_id = ?)");
			query.setLong(1, servico_id);
			ResultSet resultado = query.executeQuery();
			servico_encontrados = this.historicoFactory.criarHistoricos(resultado);
			query.close();
			connection.prepareStatement("COMMIT;").execute();
			return servico_encontrados;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao buscar servico.", e);
		}
	}

}
