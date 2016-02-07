package br.com.fatec.aulas.test.commons;

import java.sql.Connection;
import java.sql.DriverManager;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import org.junit.After;
import org.junit.Before;

import br.com.fatec.aulas.core.helper.ConfigDBMapper;


public abstract class ConfigDBTestCase {

	@Before
	public void setUp() throws Exception {
		ConfigDBMapper.getInstance().setDefaultConnectionName("fatec");
		Connection conn = ConfigDBMapper.getInstance().getDefaultConnection();
		Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(conn));
		Liquibase liquibase = new Liquibase("br/com/fatec/aulas/liquibase/changelog-master.xml",
				new ClassLoaderResourceAccessor(), database);
		liquibase.forceReleaseLocks();
		liquibase.update("fatec");
		conn.prepareStatement("COMMIT;").execute();
	}

	@After
	public void setDown() throws Exception {
		ConfigDBMapper.getInstance().setDefaultConnectionName("fatec");
		Connection conn = ConfigDBMapper.getInstance().getDefaultConnection();
		conn.prepareStatement("COMMIT;").execute();
		conn.prepareStatement("DROP SCHEMA PUBLIC CASCADE;").execute();
	}

}
