package br.com.fatec.aulas.core.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.aulas.api.entity.Servico;


public class ServicoFactory {

	public Servico criarServico(Long id, String nome, String descricao) {
		Servico servico = new Servico();
		servico.setId(id);
		servico.setNome(nome);
		servico.setDescricao(descricao);

		return servico;
	}
	

	public Servico criarServico(ResultSet resultado) {
		try {
			return this.criarServico(resultado.getLong(Servico.COL_ID), resultado.getString(Servico.COL_NOME),
					 resultado.getString(Servico.COL_DESCRICAO));
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao criar servico.", e);
		}
	}

	public List<Servico> criarServicos(ResultSet resultado) {
		try {
			List<Servico> clientes = new ArrayList<Servico>();
			while (resultado.next()) {
				clientes.add(this.criarServico(resultado));
			}
			return clientes;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao criar servico.", e);
		}
	}

}
