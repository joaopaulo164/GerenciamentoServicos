package br.com.fatec.aulas.api.entity;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import br.com.fatec.aulas.core.impl.ServicoDAOImpl;

@Getter
@Setter
public class Historico extends IdentifiedEntity {
	
	public static final String TABLE = "TBL_HISTORICO";
	public static final String COL_SERVICO_ID = "SERVICO_ID";
	public static final String COL_CLIENTE_ID = "CLIENTE_ID";
	public static final String COL_FUNCIONARIO_ID = "FUNCIONARIO_ID";
	public static final String COL_DATA_REALIZACAO = "DATA_REALIZACAO";
	public static final String COL_VALOR = "VALOR";
	public static final String COL_OBSERVACAO = "OBSERVACAO";


	private List<Servico> servicos;
	private Cliente cliente;
	private Funcionario funcionario;
	private Date dataRealizacao;
	private Double valor;
	private String observacao;

	
	public List<Servico> getServicos() {
		if (this.servicos == null){
			this.servicos = new ServicoDAOImpl().findAllByHistorico(this.id);
		}
		return this.servicos;
	}

}
