package br.com.fatec.aulas.test.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.aulas.api.entity.Cliente;
import br.com.fatec.aulas.api.service.EntityDAO;
import br.com.fatec.aulas.core.helper.ClienteFactory;
import br.com.fatec.aulas.core.impl.ClienteDAOImpl;
import br.com.fatec.aulas.test.commons.ConfigDBTestCase;
import br.com.fatec.aulas.test.commons.CustomAsserts;


public class ClienteDAOTest extends ConfigDBTestCase {

	private EntityDAO<Cliente> entityDAO;
	private ClienteFactory clienteFactory;

	@Before
	public void config() {
		this.entityDAO = new ClienteDAOImpl();
		this.clienteFactory = new ClienteFactory();
	}

	@Test
	public void saveClienteTest() {
		Calendar dataCadastro = Calendar.getInstance();
		dataCadastro.set(1988, 6, 5);

		Cliente clienteToSave = this.clienteFactory.criarCliente(null, "jose", "mauro", "3655-5566", dataCadastro.getTime());
		Cliente savedCliente = this.entityDAO.save(clienteToSave);

		CustomAsserts.assertCliente(clienteToSave, savedCliente);
		
	}

	@Test
	public void saveClienteEmailTest() {
		Calendar dataCadastro = Calendar.getInstance();
		dataCadastro.set(1988, 6, 5);

		Cliente clienteToSave = this.clienteFactory.criarCliente(null, "juan","montoya", "98158-4455", dataCadastro.getTime(),"carlos@carlos.com.br");
		Cliente savedCliente = this.entityDAO.save(clienteToSave);

		CustomAsserts.assertCliente(clienteToSave, savedCliente);
	}
	
	@Test
	public void findAllTest() {
		Calendar dataCadastro = Calendar.getInstance();
		dataCadastro.set(1978, 6, 5);

		Cliente clienteToSave1 = this.clienteFactory.criarCliente(null, "ivan", "ivanov", "3456-1122", dataCadastro.getTime());
		dataCadastro.set(1980, 1, 7);
		Cliente clienteToSave2 = this.clienteFactory.criarCliente(null, "john", "lenon", "1234-5678", dataCadastro.getTime());
		dataCadastro.set(2000, 9, 9);
		Cliente clienteToSave3 = this.clienteFactory.criarCliente(null, "graça", "jesus", "9736-9898", dataCadastro.getTime());

		List<Cliente> experados = new ArrayList<Cliente>();

		experados.add(this.entityDAO.save(clienteToSave1));
		experados.add(this.entityDAO.save(clienteToSave2));
		experados.add(this.entityDAO.save(clienteToSave3));

		List<Cliente> clientes = this.entityDAO.findAll();
		Assert.assertEquals(3, clientes.size());
		CustomAsserts.assertClientes(experados, clientes);
	}

	@Test
	public void removeClienteTest() {
		Calendar dataCadastro = Calendar.getInstance();
		dataCadastro.set(1988, 6, 5);

		Cliente clienteToSave = this.clienteFactory.criarCliente(null,  "jose", "mauro", "3655-5566", dataCadastro.getTime());
		Cliente savedCliente = this.entityDAO.save(clienteToSave);

		CustomAsserts.assertCliente(clienteToSave, savedCliente);
		this.entityDAO.remove(savedCliente);

		Assert.assertNull(this.entityDAO.findById(savedCliente.getId()));
	}

	@Test
	public void updateClienteTest() {
		Calendar dataCadastro = Calendar.getInstance();
		dataCadastro.set(1988, 6, 5);

		Cliente clienteToSave = this.clienteFactory.criarCliente(null,  "jose", "mauro", "3655-5566", dataCadastro.getTime());
		Cliente savedCliente = this.entityDAO.save(clienteToSave);

		savedCliente.setNome("josep");
		savedCliente.setSobrenome("milton");
		savedCliente.setTelefone("3654-6655");
		dataCadastro.set(1988, 6, 4);
		savedCliente.setDataCadastro(dataCadastro.getTime());

		Cliente updatedCliente = this.entityDAO.update(savedCliente);
		CustomAsserts.assertCliente(savedCliente, updatedCliente);
	}


}
