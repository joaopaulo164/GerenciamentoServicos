package br.com.fatec.aulas.test.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.aulas.api.entity.Servico;
import br.com.fatec.aulas.api.service.EntityDAO;
import br.com.fatec.aulas.core.helper.ClienteFactory;
import br.com.fatec.aulas.core.helper.FuncionarioFactory;
import br.com.fatec.aulas.core.helper.ServicoFactory;
import br.com.fatec.aulas.core.impl.ServicoDAOImpl;
import br.com.fatec.aulas.test.commons.ConfigDBTestCase;
import br.com.fatec.aulas.test.commons.CustomAsserts;


public class ServicoDAOTest extends ConfigDBTestCase {

	private EntityDAO<Servico> entityDAO;
	private ServicoFactory servicoFactory;
	
	@Before
	public void config() {
		this.entityDAO = new ServicoDAOImpl();
		this.servicoFactory = new ServicoFactory();

	}

	@Test
	public void saveServicoTest() {
		
	
		//cria servico
		Servico servicoToSave = this.servicoFactory.criarServico(null, "Manutenção Preventiva", "Limpeza e verificação de fadigas");
		
		Servico savedServico = this.entityDAO.save(servicoToSave);

		CustomAsserts.assertServico(servicoToSave, savedServico);
	}

	
	@Test
	public void findAllTest() {
			
		//cria serviços	
		//servico1
		Servico servicoToSave1 = this.servicoFactory.criarServico(null, "Manutenção Preventiva", "Limpeza e verificação de fadigas");
		
		//servico2
		Servico servicoToSave2 = this.servicoFactory.criarServico(null, "Manutenção Corretiva", "Limpeza e troca de peças");
		
		//servico3
		Servico servicoToSave3 = this.servicoFactory.criarServico(null, "Limpeza", "Limpeza");

		List<Servico> experados = new ArrayList<Servico>();

		experados.add(this.entityDAO.save(servicoToSave1));
		experados.add(this.entityDAO.save(servicoToSave2));
		experados.add(this.entityDAO.save(servicoToSave3));

		List<Servico> Servicos = this.entityDAO.findAll();
		Assert.assertEquals(3, Servicos.size());
		CustomAsserts.assertServicos(experados, Servicos);
	}

	@Test
	public void removeServicoTest() {
			
		//Remove Servico
		Calendar dataRealizacao = Calendar.getInstance();
		dataRealizacao.set(1988, 6, 5);

		Servico servicoToSave = this.servicoFactory.criarServico(null, "Manutenção Preventiva", "Limpeza e verificação de fadigas");
		
		Servico savedServico = this.entityDAO.save(servicoToSave);

		CustomAsserts.assertServico(servicoToSave, savedServico);
		this.entityDAO.remove(savedServico);

		Assert.assertNull(this.entityDAO.findById(savedServico.getId()));
	}

	@Test
	public void updateServicoTest() {
	
		//Atualiza serviço
		Servico servicoToSave = this.servicoFactory.criarServico(null, "Manutenção Preventiva", "Limpeza e verificação de fadigas");
		
		Servico savedServico = this.entityDAO.save(servicoToSave);

		savedServico.setNome("Manutenção Avançada");
		savedServico.setDescricao("Soldagem de placa mãe");

		Servico updatedServico = this.entityDAO.update(savedServico);
		CustomAsserts.assertServico(savedServico, updatedServico);
	}

}
