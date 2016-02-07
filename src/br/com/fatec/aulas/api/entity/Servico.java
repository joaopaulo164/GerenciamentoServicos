package br.com.fatec.aulas.api.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import br.com.fatec.aulas.core.impl.HistoricoDAOImpl;

@Getter
@Setter
public class Servico extends IdentifiedEntity {
	
	public static final String TABLE = "TBL_SERVICO";
	public static final String COL_NOME = "NOME";
	public static final String COL_DESCRICAO = "DESCRICAO";


	private String nome;
	private String descricao;
	private List<Historico> historicos;

	public List<Historico> getHistoricos() {
		if (this.historicos == null){
			this.historicos = new HistoricoDAOImpl().findAllByServico(this.id);
		}
		return historicos;
	}

}
