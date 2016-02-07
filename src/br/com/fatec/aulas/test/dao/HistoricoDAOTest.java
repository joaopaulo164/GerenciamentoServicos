package br.com.fatec.aulas.test.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.aulas.api.entity.Cliente;
import br.com.fatec.aulas.api.entity.Funcionario;
import br.com.fatec.aulas.api.entity.Historico;
import br.com.fatec.aulas.api.entity.Servico;
import br.com.fatec.aulas.api.service.EntityDAO;
import br.com.fatec.aulas.core.helper.ClienteFactory;
import br.com.fatec.aulas.core.helper.FuncionarioFactory;
import br.com.fatec.aulas.core.helper.HistoricoFactory;
import br.com.fatec.aulas.core.helper.ServicoFactory;
import br.com.fatec.aulas.core.impl.ClienteDAOImpl;
import br.com.fatec.aulas.core.impl.FuncionarioDAOImpl;
import br.com.fatec.aulas.core.impl.HistoricoDAOImpl;
import br.com.fatec.aulas.core.impl.ServicoDAOImpl;
import br.com.fatec.aulas.test.commons.ConfigCenarioMinimo;
import br.com.fatec.aulas.test.commons.ConfigDBTestCase;
import br.com.fatec.aulas.test.commons.CustomAsserts;


public class HistoricoDAOTest extends ConfigDBTestCase {

	private EntityDAO<Historico> entityDAO;
	private HistoricoFactory historicoFactory;
	private EntityDAO<Servico> servicoDAO;
	private EntityDAO<Cliente> clienteDAO;
	private EntityDAO<Funcionario> funcionarioDAO;
	private ServicoFactory servicoFactory;
	private ClienteFactory clienteFactory;
	private FuncionarioFactory funcionarioFactory;

	@Before
	public void config() {
		this.servicoFactory = new ServicoFactory();
		this.clienteFactory = new ClienteFactory();
		this.funcionarioFactory = new FuncionarioFactory();
		this.historicoFactory = new HistoricoFactory();
		clienteDAO = new ClienteDAOImpl();
		funcionarioDAO = new FuncionarioDAOImpl();
		servicoDAO = new ServicoDAOImpl();
		this.entityDAO = new HistoricoDAOImpl();

	}

	@Test
	public void saveHistoricoTest() {
		
		//cria cliente
		Calendar dataCadastro = Calendar.getInstance();
		dataCadastro.set(1988, 6, 5);
		Cliente clienteToSave = this.clienteFactory.criarCliente(null, "jose1", "mauro", "3655-5566",
				dataCadastro.getTime(), "jose@jose.com.br");
		Cliente savedCliente = this.clienteDAO.save(clienteToSave);
		
		//cria funcionario
		Calendar dataAdmissao = Calendar.getInstance();
		dataAdmissao.set(1999, 6, 5);
		Calendar dataDemissao = Calendar.getInstance();
		dataDemissao.set(2010, 7, 3);
		Funcionario funcionarioToSave = this.funcionarioFactory.criarFuncionario(null, "joao", "Mendes", "3655-5566",
				dataAdmissao.getTime(), dataDemissao.getTime(), "joao@joao.com.br");
		Funcionario savedFuncionario = this.funcionarioDAO.save(funcionarioToSave);
				
		//cria servico
		List<Servico> lista_servicos =  new ArrayList<Servico>();
		Servico servico1 = this.servicoFactory.criarServico(null, "Manutenção Preventiva", "Limpeza e verificação de fadigas");
		lista_servicos.add(this.servicoDAO.save(servico1));
	

		
		//cria historico
		Calendar dataRealizacao = Calendar.getInstance();
		dataRealizacao.set(1988, 6, 5);
		Historico historicoToSave = this.historicoFactory.criarHistorico(null, lista_servicos,
				savedCliente, savedFuncionario, dataRealizacao.getTime(), 100.50, "Blah");
		
		Historico savedHistorico = this.entityDAO.save(historicoToSave);

		CustomAsserts.assertHistorico(historicoToSave, savedHistorico);
	}


	@Test
	public void findAllTest() {
		
		//cria clientes

		Calendar dataCadastro = Calendar.getInstance();
		
		//cliente 1
		dataCadastro.set(1988, 6, 5);
		Cliente clienteToSave1 = this.clienteFactory.criarCliente(null, "jose2", "mauro", "3655-5566",
				dataCadastro.getTime(), "jose@jose.com.br");
		Cliente savedCliente1 = this.clienteDAO.save(clienteToSave1);
		
		//cliente 2
		dataCadastro.set(1999, 8, 1);
		Cliente clienteToSave2 = this.clienteFactory.criarCliente(null, "wilson", "hanks", "3658-1122",
				dataCadastro.getTime(), "wilson@hanks.com.br");
		Cliente savedCliente2 = this.clienteDAO.save(clienteToSave2);
		

		//cria funcionarios
		Calendar dataAdmissao = Calendar.getInstance();
		Calendar dataDemissao = Calendar.getInstance();
		
		//funcionario1
		dataAdmissao.set(1999, 6, 5);
		dataDemissao.set(2010, 7, 3);
		Funcionario funcionarioToSave1 = this.funcionarioFactory.criarFuncionario(null, "jonny", "Mendes", "3655-5566",
				dataAdmissao.getTime(), dataDemissao.getTime(), "joao@joao.com.br");
		Funcionario savedFuncionario1 = this.funcionarioDAO.save(funcionarioToSave1);
		
		//funcionario2
		dataAdmissao.set(2001, 6, 5);
		dataDemissao.set(2010, 7, 3);
		Funcionario funcionarioToSave2 = this.funcionarioFactory.criarFuncionario(null, "jose3", "nazaré", "3675-8899",
				dataAdmissao.getTime(), dataDemissao.getTime(), "jose@jose.com.br");
		Funcionario savedFuncionario2 = this.funcionarioDAO.save(funcionarioToSave2);	

		//cria servicos
		
		//servicos1
		List<Servico> lista_servicos1 =  new ArrayList<Servico>();
		Servico servico1 = this.servicoFactory.criarServico(null, "Manutenção Preventiva", "Limpeza e verificação de fadigas");
		lista_servicos1.add(this.servicoDAO.save(servico1));
		
		//servicos2
		List<Servico> lista_servicos2 =  new ArrayList<Servico>();
		Servico servico2 = this.servicoFactory.criarServico(null, "Manutenção Corretiva", "Limpeza e troca de peças");
		lista_servicos2.add(this.servicoDAO.save(servico2));
		
		//servicos3
		List<Servico> lista_servicos3 =  new ArrayList<Servico>();
		Servico servico3 = this.servicoFactory.criarServico(null, "Limpeza", "Limpeza");
		lista_servicos3.add(this.servicoDAO.save(servico3));
		
		//cria historico
		Calendar dataRealizacao = Calendar.getInstance();
		
		//historico1
		dataRealizacao.set(1978, 6, 5);
		Historico historicoToSave1 = this.historicoFactory.criarHistorico(null, lista_servicos1,
				savedCliente1, savedFuncionario1, dataRealizacao.getTime(), 100.50, "S/A");
		
		
		//historico2
		dataRealizacao.set(1980, 1, 7);
		Historico historicoToSave2 = this.historicoFactory.criarHistorico(null, lista_servicos2,
				savedCliente2, savedFuncionario2, dataRealizacao.getTime(), 290.80, "S/A");
		
		
		//historico3
		dataRealizacao.set(2000, 9, 9);
		Historico historicoToSave3 = this.historicoFactory.criarHistorico(null, lista_servicos3,
				savedCliente1, savedFuncionario2, dataRealizacao.getTime(), 40.50, "S/A");

		List<Historico> experados = new ArrayList<Historico>();

		experados.add(this.entityDAO.save(historicoToSave1));
		experados.add(this.entityDAO.save(historicoToSave2));
		experados.add(this.entityDAO.save(historicoToSave3));

		List<Historico> historicos = this.entityDAO.findAll();
		Assert.assertEquals(3, historicos.size());
		CustomAsserts.assertHistoricos(experados, historicos);
	}

	@Test
	public void removeHistoricoTest() {
		
		//cria cliente
		Calendar dataCadastro = Calendar.getInstance();
		dataCadastro.set(1988, 6, 5);
		Cliente clienteToSave = this.clienteFactory.criarCliente(null, "jose4", "mauro", "3655-5566",
				dataCadastro.getTime(), "jose@jose.com.br");
		Cliente savedCliente = this.clienteDAO.save(clienteToSave);
		
		//cria funcionario
		Calendar dataAdmissao = Calendar.getInstance();
		dataAdmissao.set(1999, 6, 5);
		Calendar dataDemissao = Calendar.getInstance();
		dataDemissao.set(2010, 7, 3);
		Funcionario funcionarioToSave = this.funcionarioFactory.criarFuncionario(null, "joao", "Mendes", "3655-5566",
				dataAdmissao.getTime(), dataDemissao.getTime(), "joao@joao.com.br");
		Funcionario savedFuncionario = this.funcionarioDAO.save(funcionarioToSave);
		
		
		//cria servico
		List<Servico> lista_servicos =  new ArrayList<Servico>();
		Servico servico1 = this.servicoFactory.criarServico(null, "Manutenção Preventiva", "Limpeza e verificação de fadigas");
		lista_servicos.add(this.servicoDAO.save(servico1));

		
		//Remove historico
		Calendar dataRealizacao = Calendar.getInstance();
		dataRealizacao.set(1988, 6, 5);

		Historico historicoToSave = this.historicoFactory.criarHistorico(null, lista_servicos,
				savedCliente, savedFuncionario, dataRealizacao.getTime(), 100.50, "S/A");
		
		Historico savedHistorico = this.entityDAO.save(historicoToSave);

		CustomAsserts.assertHistorico(historicoToSave, savedHistorico);
		this.entityDAO.remove(savedHistorico);

		Assert.assertNull(this.entityDAO.findById(savedHistorico.getId()));
	}

	@Test
	public void updateHistoricoTest() {
		
		//cria cliente
		Calendar dataCadastro = Calendar.getInstance();
		dataCadastro.set(1988, 6, 5);
		Cliente clienteToSave = this.clienteFactory.criarCliente(null, "jose", "mauro", "3655-5566",
				dataCadastro.getTime(), "jose@jose.com.br");
		Cliente savedCliente = this.clienteDAO.save(clienteToSave);
		
		//cria funcionario
		Calendar dataAdmissao = Calendar.getInstance();
		dataAdmissao.set(1999, 6, 5);
		Calendar dataDemissao = Calendar.getInstance();
		dataDemissao.set(2010, 7, 3);
		Funcionario funcionarioToSave = this.funcionarioFactory.criarFuncionario(null, "joao", "Mendes", "3655-5566",
				dataAdmissao.getTime(), dataDemissao.getTime(), "joao@joao.com.br");
		Funcionario savedFuncionario = this.funcionarioDAO.save(funcionarioToSave);


		//cria servico
		List<Servico> lista_servicos =  new ArrayList<Servico>();
		Servico servico1 = this.servicoFactory.criarServico(null, "Manutenção Preventiva", "Limpeza e verificação de fadigas");
		lista_servicos.add(this.servicoDAO.save(servico1));
		
		
		//Atualiza serviço
		Calendar dataRealizacao = Calendar.getInstance();
		dataRealizacao.set(1988, 6, 5);

		Historico historicoToSave = this.historicoFactory.criarHistorico(null, lista_servicos,
				savedCliente, savedFuncionario, dataRealizacao.getTime(), 100.50, "S/A");
		
		Historico savedHistorico = this.entityDAO.save(historicoToSave);

		savedHistorico.setServicos(lista_servicos);
		savedHistorico.setCliente(savedCliente);
		savedHistorico.setFuncionario(savedFuncionario);
		dataRealizacao.set(1988, 6, 4);
		savedHistorico.setDataRealizacao(dataRealizacao.getTime());
		savedHistorico.setValor(639.80);

		Historico updatedHistorico = this.entityDAO.update(savedHistorico);
		CustomAsserts.assertHistorico(savedHistorico, updatedHistorico);
	}
	

}
