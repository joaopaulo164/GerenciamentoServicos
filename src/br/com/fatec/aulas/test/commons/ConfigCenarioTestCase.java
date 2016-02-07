package br.com.fatec.aulas.test.commons;

import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;

import br.com.fatec.aulas.core.helper.ConfigDBMapper;

public class ConfigCenarioTestCase extends ConfigDBTestCase {
	
	@Before
	public void upCenario() throws Exception {
		ConfigDBMapper.getInstance().setDefaultConnectionName("fatec");
		Connection jdbcConnection = ConfigDBMapper.getInstance().getDefaultConnection();
		IDatabaseConnection conn = new DatabaseConnection(jdbcConnection);
		ClassLoader classLoader  = this.getClass().getClassLoader();
		FlatXmlDataSet dataSetXML = new FlatXmlDataSetBuilder().build(classLoader.getResourceAsStream("br/com/fatec/aulas/test/resources/cenarios/basico.xml"));
		DatabaseOperation.CLEAN_INSERT.execute(conn, dataSetXML);
	}

}
