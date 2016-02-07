package br.com.fatec.aulas.test.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.aulas.api.entity.Funcionario;
import br.com.fatec.aulas.api.service.EntityDAO;
import br.com.fatec.aulas.core.helper.FuncionarioFactory;
import br.com.fatec.aulas.core.impl.FuncionarioDAOImpl;
import br.com.fatec.aulas.test.commons.ConfigDBTestCase;
import br.com.fatec.aulas.test.commons.CustomAsserts;


public class FuncionarioDAOTest extends ConfigDBTestCase {

	private EntityDAO<Funcionario> entityDAO;
	private FuncionarioFactory funcionarioFactory;

	@Before
	public void config() {
		this.entityDAO = new FuncionarioDAOImpl();
		this.funcionarioFactory = new FuncionarioFactory();
	}

	@Test
	public void saveFuncionarioTest() {
		Calendar dataAdmissao = Calendar.getInstance();
		dataAdmissao.set(1999, 6, 5);
		
		Calendar dataDemissao = Calendar.getInstance();
		dataDemissao.set(2010, 7, 3);
		

		Funcionario funcionarioToSave = this.funcionarioFactory.criarFuncionario(null, "joao", "Mendes", "3655-5566",
				dataAdmissao.getTime(), dataDemissao.getTime(), "joao@joao.com.br");
		Funcionario savedFuncionario = this.entityDAO.save(funcionarioToSave);

		CustomAsserts.assertFuncionario(funcionarioToSave, savedFuncionario);
	}


	
	@Test
	public void findAllTest() {
		Calendar dataAdmissao = Calendar.getInstance();
		Calendar dataDemissao = Calendar.getInstance();
		
		dataAdmissao.set(1999, 6, 5);
		dataDemissao.set(2001, 8, 1);
		Funcionario funcionarioToSave1 = this.funcionarioFactory.criarFuncionario(null, "joao", "santos", "3654-7788",
				dataAdmissao.getTime(), dataDemissao.getTime(), "");
		
		dataAdmissao.set(2000, 6, 7);
		dataDemissao.set(2011, 4, 3);
		Funcionario funcionarioToSave2 = this.funcionarioFactory.criarFuncionario(null, "jose", "luiz", "9134-7790",
				dataAdmissao.getTime(), dataDemissao.getTime(), "jose@jose.com.br");
		
		dataAdmissao.set(2001, 6, 9);
		dataDemissao.set(2014, 1, 2);
		Funcionario funcionarioToSave3 = this.funcionarioFactory.criarFuncionario(null, "paulo", "cruz", "8860-1234",
				dataAdmissao.getTime(), dataDemissao.getTime(), "paulo@paulo.com");

		List<Funcionario> experados = new ArrayList<Funcionario>();

		experados.add(this.entityDAO.save(funcionarioToSave1));
		experados.add(this.entityDAO.save(funcionarioToSave2));
		experados.add(this.entityDAO.save(funcionarioToSave3));

		List<Funcionario> funcionarios = this.entityDAO.findAll();
		Assert.assertEquals(3, funcionarios.size());
		CustomAsserts.assertFuncionarios(experados, funcionarios);
	}

	@Test
	public void removeFuncionarioTest() {
		Calendar dataAdmissao = Calendar.getInstance();
		dataAdmissao.set(1999, 6, 5);
		
		Calendar dataDemissao = Calendar.getInstance();
		dataDemissao.set(1999, 6, 5);

		Funcionario funcionarioToSave = this.funcionarioFactory.criarFuncionario(null, "joao", "Ingles", "9178-0987",
				dataAdmissao.getTime(), dataDemissao.getTime(),"joao@joao.com.br");
		Funcionario savedFuncionario = this.entityDAO.save(funcionarioToSave);

		CustomAsserts.assertFuncionario(funcionarioToSave, savedFuncionario);
		this.entityDAO.remove(savedFuncionario);

		Assert.assertNull(this.entityDAO.findById(savedFuncionario.getId()));
	}

	@Test
	public void updateFuncionarioTest() {
		Calendar dataAdmissao = Calendar.getInstance();
		dataAdmissao.set(1999, 6, 5);
		
		Calendar dataDemissao = Calendar.getInstance();
		dataDemissao.set(2015, 4, 3);

		Funcionario funcionarioToSave = this.funcionarioFactory.criarFuncionario(null, "joao", "Ingles", "9178-0987",
				dataAdmissao.getTime(), dataDemissao.getTime(), "joao@joao.com.br");
		Funcionario savedFuncionario = this.entityDAO.save(funcionarioToSave);

		savedFuncionario.setNome("john");
		savedFuncionario.setSobrenome("john");
		savedFuncionario.setTelefone("3567-1234");
		savedFuncionario.setDataAdmissao(dataAdmissao.getTime());
		savedFuncionario.setDataDemissao(dataDemissao.getTime());
		savedFuncionario.setEmail("joaopaulo@joaopaulo.com.br");

		Funcionario updatedFuncionario = this.entityDAO.update(savedFuncionario);
		CustomAsserts.assertFuncionario(savedFuncionario, updatedFuncionario);
	}


}
