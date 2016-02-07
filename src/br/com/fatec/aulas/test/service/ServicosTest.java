package br.com.fatec.aulas.test.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import br.com.fatec.aulas.api.entity.Historico;
import br.com.fatec.aulas.api.entity.Servico;
import br.com.fatec.aulas.api.service.EntityDAO;
import br.com.fatec.aulas.api.service.GerenciadorHistorico;
import br.com.fatec.aulas.core.impl.HistoricoDAOImpl;
import br.com.fatec.aulas.core.impl.ServicoDAOImpl;
import br.com.fatec.aulas.core.service.GerenciadorHistoricoImpl;
import br.com.fatec.aulas.test.commons.ConfigCenarioTestCase;


public class ServicosTest extends ConfigCenarioTestCase {
	
	private EntityDAO<Servico> servicoDAO;
	private EntityDAO<Historico> historicoDAO;	
	private GerenciadorHistorico gerenciadorServicos;
	
	@Before
	public void config(){
		
		this.servicoDAO = new ServicoDAOImpl();
		this.historicoDAO = new HistoricoDAOImpl();
		this.gerenciadorServicos = new GerenciadorHistoricoImpl();
	}

	@Test
	public void gerenciaAddServicoTest(){
		Historico historico = historicoDAO.findById(1L);
		Servico servico = servicoDAO.findById(3L);
		gerenciadorServicos.addServicosToHistorico(historico, servico);
		Assert.assertEquals(3,this.historicoDAO.findById(1L).getServicos().size());
		
	}
	
	@Test
	public void gerenciaRemoveServicoTest(){
		Historico historico = historicoDAO.findById(1L);
		Servico servico = servicoDAO.findById(1L);
		gerenciadorServicos.removeServicosToHistorico(historico, servico);
		Assert.assertEquals(1,this.historicoDAO.findById(1L).getServicos().size());
		
	}


}
