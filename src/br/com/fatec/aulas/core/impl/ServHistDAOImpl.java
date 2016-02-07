package br.com.fatec.aulas.core.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import br.com.fatec.aulas.api.entity.Historico;
import br.com.fatec.aulas.api.entity.Servico;
import br.com.fatec.aulas.core.helper.ConfigDBMapper;
import br.com.fatec.aulas.core.helper.HistoricoFactory;
import br.com.fatec.aulas.core.service.GeradorIdService;

public class ServHistDAOImpl {

	private Connection connection;

	public ServHistDAOImpl() {
		this.connection = ConfigDBMapper.getInstance().getDefaultConnection();
	}

	public void updateServicosByHistorico(Historico historico) {
		if (historico.getServicos() != null) {
			historico.getServicos();
			try{
				PreparedStatement deleteServicos = connection.prepareStatement("DELETE FROM TBL_HIST_SERV WHERE HISTORICO_ID = ?");
				deleteServicos.setLong(1, historico.getId());
				deleteServicos.execute();
				deleteServicos.close();
				connection.prepareStatement("COMMIT;").execute();
				for (Servico servico:historico.getServicos()) {
					PreparedStatement insert = connection.
							prepareStatement("INSERT INTO TBL_HIST_SERV VALUES (?,?,?)");
					Long id = GeradorIdService.getInstance().getNextId("TBL_HIST_SERV");
					insert.setLong(1, id);
					insert.setLong(2, historico.getId());
					insert.setLong(3, servico.getId());
					insert.execute();
					insert.close();
					connection.prepareStatement("COMMIT;").execute();
				}
			} catch (SQLException e) {
				throw new RuntimeException("Erro ao buscar historico.", e);
			}
		}
	}

	public void updateHistoricosByServico(Servico servico) {

		if (servico.getHistoricos() != null) {
			servico.getHistoricos();
			try{
				PreparedStatement deleteHistoricos = connection.prepareStatement("DELETE FROM TBL_HIST_SERV WHERE SERVICO_ID = ?");
				deleteHistoricos.setLong(1, servico.getId());
				deleteHistoricos.execute();
				deleteHistoricos.close();
				connection.prepareStatement("COMMIT;").execute();
				for (Historico historico:servico.getHistoricos()) {
					PreparedStatement insert = connection.
							prepareStatement("INSERT INTO TBL_HIST_SERV VALUES (?,?,?)");
					Long id = GeradorIdService.getInstance().getNextId("TBL_HIST_SERV");
					insert.setLong(1, id);
					insert.setLong(2, servico.getId());
					insert.setLong(3, historico.getId());
					insert.execute();
					insert.close();
					connection.prepareStatement("COMMIT;").execute();
				}
			} catch (SQLException e) {
				throw new RuntimeException("Erro ao buscar servico.", e);
			}
		}
		
	}
	
}
