package br.com.fatec.aulas.api.service;

import br.com.fatec.aulas.api.entity.Historico;
import br.com.fatec.aulas.api.entity.IdentifiedEntity;
import br.com.fatec.aulas.api.entity.Servico;

public interface GerenciadorHistorico {
	
	void addServicosToHistorico(Historico entityType, Servico... servicos);
	
	void removeServicosToHistorico(Historico historico, Servico... servicosArray);

}
