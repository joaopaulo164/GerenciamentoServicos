package br.com.fatec.aulas.test.commons;

import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;

public class ConfigCenarioMinimo extends ConfigDBTestCase {
	
	@Before
	public void upCenario() throws Exception {
		Connection jdbcConnection = DriverManager.getConnection("jdbc:hsqldb:mem:fatec","SA","");
		IDatabaseConnection conn = new DatabaseConnection(jdbcConnection);
		ClassLoader classLoader  = this.getClass().getClassLoader();
		FlatXmlDataSet dataSetXML = new FlatXmlDataSetBuilder().build(classLoader.getResourceAsStream("br/com/fatec/aulas/test/resources/cenarios/minimo.xml"));
		DatabaseOperation.CLEAN_INSERT.execute(conn, dataSetXML);
	}

}
