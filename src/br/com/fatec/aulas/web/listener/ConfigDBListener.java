package br.com.fatec.aulas.web.listener;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import br.com.fatec.aulas.core.helper.ConfigDBMapper;

public class ConfigDBListener implements ServletContextListener  {


	public void contextInitialized(ServletContextEvent arg0) {
		try {
			ConfigDBMapper.getInstance().setDefaultConnectionName("fatec");
			Connection conn = ConfigDBMapper.getInstance().getDefaultConnection();
			Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(conn));
			Liquibase liquibase = new Liquibase("br/com/fatec/aulas/liquibase/changelog-master.xml",
					new ClassLoaderResourceAccessor(), database);
			liquibase.forceReleaseLocks();
			liquibase.update("fatec");
			conn.prepareStatement("COMMIT;").execute();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}	
	

	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			ConfigDBMapper.getInstance().setDefaultConnectionName("fatec");
			Connection conn = ConfigDBMapper.getInstance().getDefaultConnection();
			conn.prepareStatement("COMMIT;").execute();
			conn.prepareStatement("DROP SCHEMA PUBLIC CASCADE;").execute();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
