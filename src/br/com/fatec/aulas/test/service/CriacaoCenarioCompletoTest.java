package br.com.fatec.aulas.test.service;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;
import br.com.fatec.aulas.api.entity.Cliente;
import br.com.fatec.aulas.api.entity.Funcionario;
import br.com.fatec.aulas.api.entity.Historico;
import br.com.fatec.aulas.api.entity.Servico;
import br.com.fatec.aulas.api.service.EntityDAO;
import br.com.fatec.aulas.core.impl.ClienteDAOImpl;
import br.com.fatec.aulas.core.impl.FuncionarioDAOImpl;
import br.com.fatec.aulas.core.impl.HistoricoDAOImpl;
import br.com.fatec.aulas.core.impl.ServicoDAOImpl;
import br.com.fatec.aulas.test.commons.ConfigCenarioTestCase;

public class CriacaoCenarioCompletoTest extends ConfigCenarioTestCase {
	
	private EntityDAO<Cliente> clienteDAO;
	private EntityDAO<Funcionario> funcionarioDAO;
	private EntityDAO<Servico> servicoDAO;
	private EntityDAO<Historico> historicoDAO;
	
	@Before
	public void config(){
		this.clienteDAO = new ClienteDAOImpl();
		this.funcionarioDAO = new FuncionarioDAOImpl();
		this.servicoDAO = new ServicoDAOImpl();
		this.historicoDAO = new HistoricoDAOImpl();
	}

	@Test
	public void CriarCenarioTest(){
		Assert.assertEquals(3,this.clienteDAO.findAll().size());
		Assert.assertEquals(3,this.funcionarioDAO.findAll().size());
		Assert.assertEquals(3,this.servicoDAO.findAll().size());
		Assert.assertEquals(3,this.historicoDAO.findAll().size());
	}
	
}
