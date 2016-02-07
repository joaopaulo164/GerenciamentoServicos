package br.com.fatec.aulas.test.service;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;
import br.com.fatec.aulas.api.entity.Cliente;
import br.com.fatec.aulas.api.service.EntityDAO;
import br.com.fatec.aulas.core.impl.ClienteDAOImpl;
import br.com.fatec.aulas.test.commons.ConfigCenarioTestCase;

public class CriacaoCenarioBasicoTest extends ConfigCenarioTestCase {
	
	private EntityDAO<Cliente> clienteDAO;
	
	@Before
	public void config(){
		this.clienteDAO = new ClienteDAOImpl();
	}

	@Test
	public void CriarCenarioTest(){
		Assert.assertEquals(3,this.clienteDAO.findAll().size());
	}
	
}
