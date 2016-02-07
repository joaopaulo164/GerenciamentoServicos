package br.com.fatec.aulas.core.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import br.com.fatec.aulas.api.entity.Historico;
import br.com.fatec.aulas.api.entity.Servico;
import br.com.fatec.aulas.api.service.EntityDAO;
import br.com.fatec.aulas.api.service.GerenciadorHistorico;
import br.com.fatec.aulas.core.impl.HistoricoDAOImpl;
import br.com.fatec.aulas.core.impl.ServHistDAOImpl;

public class GerenciadorHistoricoImpl implements GerenciadorHistorico {

	private EntityDAO<Historico> historicoDAO = new HistoricoDAOImpl();
	private ServHistDAOImpl servHistDAOImpl = new ServHistDAOImpl();

	public void addServicosToHistorico(Historico historico, Servico... servicosArray) {
		historico = this.historicoDAO.findById(historico.getId());
		Set<Servico> servicosToAdd = new HashSet<Servico>();
		servicosToAdd.addAll(Arrays.asList(servicosArray));
		List<Servico> servicosUtilizados = historico.getServicos();
		for (Servico servicoToAdd: new ArrayList<Servico>(servicosToAdd)) {
			if (servicosUtilizados.contains(servicosArray)) {
				servicosToAdd.remove(servicoToAdd);
			}
		} 
		servicosUtilizados.addAll(servicosToAdd);
		historico.setServicos(servicosUtilizados);
		this.servHistDAOImpl.updateServicosByHistorico(historico);
  }

	public void removeServicosToHistorico(Historico historico, Servico... servicosArray) {
		historico = this.historicoDAO.findById(historico.getId());
		Set<Servico> servicosToRemove = new HashSet<Servico>();
		servicosToRemove.addAll(Arrays.asList(servicosArray));
		List<Servico> servicosUtilizados = historico.getServicos();
		servicosUtilizados.removeAll(servicosToRemove);
		historico.setServicos(servicosUtilizados);
		this.servHistDAOImpl.updateServicosByHistorico(historico);
	}

}
