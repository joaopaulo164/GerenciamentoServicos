package br.com.fatec.aulas.test.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import org.junit.Assert;
import org.junit.Test;

import br.com.fatec.aulas.api.entity.Cliente;

/**
 * @author Carlos
 *
 * @version 1.0.1
 */
public class BasicoQueriesTest {

	@Test
	public void execLiquibaseTest() throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:fatec", "SA", "");
		Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(conn));
		Liquibase liquibase = new Liquibase("br/com/fatec/aulas/liquibase/changelog-master.xml",
				new ClassLoaderResourceAccessor(), database);
		liquibase.forceReleaseLocks();
		liquibase.update("fatec");

		PreparedStatement insert = conn.prepareStatement("INSERT INTO " + Cliente.TABLE
				+ " VALUES (1, null, null, null);");
		insert.execute();

		PreparedStatement query = conn.prepareStatement("SELECT * FROM " + Cliente.TABLE + ";");
		ResultSet resultado = query.executeQuery();
		Assert.assertTrue(resultado.next());
		Assert.assertEquals(1L, resultado.getLong(1));
	}
}
