package br.com.fatec.aulas.core.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.fatec.aulas.core.helper.ConfigDBMapper;
import br.com.fatec.aulas.test.commons.ConfigCenarioMinimo;
import br.com.fatec.aulas.test.commons.ConfigDBTestCase;

public class GeradorIdService extends ConfigDBTestCase {

	
	private static GeradorIdService instance;
	private Long idSequence = 1L;
	public static Connection connection;
	private String tableName;


	private GeradorIdService() {

	}

	public static GeradorIdService getInstance() {
		if (instance == null) {
			instance = new GeradorIdService();
			connection = ConfigDBMapper.getInstance().getDefaultConnection();;
		}
		return instance;
	}

	public synchronized Long getNextId(String tableName) {
		try {
			PreparedStatement query = connection.prepareStatement("SELECT NEXT_ID FROM TBL_IDS WHERE TABLE_NAME = ?;");
			query.setString(1, tableName);
			ResultSet resultNextId = query.executeQuery();
			resultNextId.next();
			idSequence = resultNextId.getLong("NEXT_ID");
			PreparedStatement updateID = connection.prepareStatement("UPDATE TBL_IDS SET NEXT_ID = ? WHERE TABLE_NAME = ?;");
			updateID.setLong(1, idSequence + 1);
			updateID.setString(2, tableName);
			updateID.execute();
			updateID.close();
			connection.prepareStatement("COMMIT;").execute();
			return idSequence++;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao pegar ID para a tabela " + tableName + " ", e);
		}
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
