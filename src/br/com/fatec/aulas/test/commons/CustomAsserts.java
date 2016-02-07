package br.com.fatec.aulas.test.commons;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;

import br.com.fatec.aulas.api.entity.Cliente;
import br.com.fatec.aulas.api.entity.Funcionario;
import br.com.fatec.aulas.api.entity.Historico;
import br.com.fatec.aulas.api.entity.Servico;

public class CustomAsserts {

	public static void assertDate(Date expected, Date actual) {
		Calendar cExpected = Calendar.getInstance();
		Calendar cActual = Calendar.getInstance();
		cExpected.setTimeInMillis(expected.getTime());
		cActual.setTimeInMillis(actual.getTime());

		Assert.assertEquals(cExpected.get(Calendar.YEAR), cActual.get(Calendar.YEAR));
		Assert.assertEquals(cExpected.get(Calendar.MONTH), cActual.get(Calendar.MONTH));
		Assert.assertEquals(cExpected.get(Calendar.DAY_OF_MONTH), cActual.get(Calendar.DAY_OF_MONTH));

	}
	
	public static void assertCliente(Cliente expected, Cliente actual) {
		Assert.assertEquals(expected.getNome(), actual.getNome());
		Assert.assertEquals(expected.getSobrenome(), actual.getSobrenome());
		Assert.assertEquals(expected.getTelefone(), actual.getTelefone());
		CustomAsserts.assertDate(expected.getDataCadastro(), actual.getDataCadastro());
	}

	public static void assertClientes(List<Cliente> expected, List<Cliente> actual) {
		for (int i = 0; i < expected.size(); i++) {
			assertCliente(expected.get(i), actual.get(i));
		}
	}
	
	public static void assertFuncionario(Funcionario expected, Funcionario actual) {
		Assert.assertEquals(expected.getNome(), actual.getNome());
		Assert.assertEquals(expected.getSobrenome(), actual.getSobrenome());
		Assert.assertEquals(expected.getTelefone(), actual.getTelefone());
		CustomAsserts.assertDate(expected.getDataAdmissao(), actual.getDataAdmissao());
		CustomAsserts.assertDate(expected.getDataDemissao(), actual.getDataDemissao());
	}

	public static void assertFuncionarios(List<Funcionario> expected, List<Funcionario> actual) {
		for (int i = 0; i < expected.size(); i++) {
			assertFuncionario(expected.get(i), actual.get(i));
		}
	}

	public static void assertServico(Servico expected, Servico actual) {
		Assert.assertEquals(expected.getNome(), actual.getNome());
		Assert.assertEquals(expected.getDescricao(), actual.getDescricao());
	}

	public static void assertServicos(List<Servico> expected, List<Servico> actual) {
		for (int i = 0; i < expected.size(); i++) {
			assertServico(expected.get(i), actual.get(i));
		}
	}

	public static void assertHistorico(Historico expected, Historico actual) {
		assertServicos(expected.getServicos(), actual.getServicos());
		assertCliente(expected.getCliente(), actual.getCliente());;
		assertFuncionario(expected.getFuncionario(), actual.getFuncionario());
		assertDate(expected.getDataRealizacao(), actual.getDataRealizacao());
		Assert.assertEquals(expected.getValor(), actual.getValor());
		Assert.assertEquals(expected.getObservacao(), actual.getObservacao());
	}	

	
	public static void assertHistoricos(List<Historico> expected, List<Historico> actual) {
		for (int i = 0; i < expected.size(); i++) {
			assertHistorico(expected.get(i), actual.get(i));
		}
	}
	
	
}
