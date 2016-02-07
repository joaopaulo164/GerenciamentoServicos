package br.com.fatec.aulas.test.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import br.com.fatec.aulas.api.entity.Cliente;
import br.com.fatec.aulas.test.commons.ConfigDBTestCase;

/**
 * @author Carlos
 *
 * @version
 */
public class AdvacedPlusQueriesTest extends ConfigDBTestCase {

	@Test
	public void insertComParametrosTest() throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:fatec", "SA", "");
		PreparedStatement insert = conn.prepareStatement("INSERT INTO " + Cliente.TABLE + " VALUES (?, ?, ?, ?);");

		insert.setLong(1, 1L);
		insert.setString(2, "carlos");
		insert.setString(3, "0000000010");

		Calendar dataNascimento = Calendar.getInstance();
		dataNascimento.set(1988, 6, 5);

		insert.setDate(4, new java.sql.Date(dataNascimento.getTimeInMillis()));

		insert.execute();

		PreparedStatement query = conn.prepareStatement("SELECT * FROM " + Cliente.TABLE + ";");
		ResultSet resultado = query.executeQuery();
		Assert.assertTrue(resultado.next());
		Assert.assertEquals(1L, resultado.getLong(1));
		Assert.assertEquals("carlos", resultado.getString(2));
		Assert.assertEquals("0000000010", resultado.getString(3));
	}

	@Test
	public void selectTest() throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:fatec", "SA", "");
		PreparedStatement query = conn.prepareStatement("SELECT * FROM " + Cliente.TABLE + ";");
		ResultSet resultado = query.executeQuery();
		Assert.assertFalse(resultado.next());
	}

}
