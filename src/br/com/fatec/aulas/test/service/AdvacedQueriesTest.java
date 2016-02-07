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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.aulas.api.entity.Cliente;

/**
 * @author Carlos
 *
 * @version
 */
public class AdvacedQueriesTest {

	@Before
	public void setUp() throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:fatec", "SA", "");
		Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(conn));
		Liquibase liquibase = new Liquibase("br/com/fatec/aulas/liquibase/changelog-master.xml",
				new ClassLoaderResourceAccessor(), database);
		liquibase.forceReleaseLocks();
		liquibase.update("fatec");
	}

	@After
	public void setDown() throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:fatec", "SA", "");
		conn.prepareStatement("DROP SCHEMA PUBLIC CASCADE;").execute();
	}

	@Test
	public void insertTest() throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:fatec", "SA", "");
		PreparedStatement insert = conn.prepareStatement("INSERT INTO " + Cliente.TABLE
				+ " VALUES (1, null, null, null);");
		insert.execute();

		PreparedStatement query = conn.prepareStatement("SELECT * FROM " + Cliente.TABLE + ";");
		ResultSet resultado = query.executeQuery();
		Assert.assertTrue(resultado.next());
		Assert.assertEquals(1L, resultado.getLong(1));
	}

	@Test
	public void selectTest() throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:fatec", "SA", "");
		PreparedStatement query = conn.prepareStatement("SELECT * FROM " + Cliente.TABLE + ";");
		ResultSet resultado = query.executeQuery();
		Assert.assertFalse(resultado.next());
	}

}
