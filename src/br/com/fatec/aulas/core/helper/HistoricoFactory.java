package br.com.fatec.aulas.core.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import br.com.fatec.aulas.api.entity.Cliente;
import br.com.fatec.aulas.api.entity.Funcionario;
import br.com.fatec.aulas.api.entity.Historico;
import br.com.fatec.aulas.api.entity.Servico;
import br.com.fatec.aulas.api.service.EntityDAO;
import br.com.fatec.aulas.core.impl.ClienteDAOImpl;
import br.com.fatec.aulas.core.impl.FuncionarioDAOImpl;


public class HistoricoFactory {

	private static EntityDAO<Cliente> clienteDAO = new ClienteDAOImpl();
	private static EntityDAO<Funcionario> funcionarioDAO = new FuncionarioDAOImpl();
	public Historico criarHistorico(Long id, Long cliente_id, Long funcionario_id,
			Date dataRealizacao, Double valor, String observacao) {
		Historico historico = new Historico();
		
		historico.setId(id);
		historico.setCliente(clienteDAO.findById(cliente_id));
		historico.setFuncionario(funcionarioDAO.findById(funcionario_id));
		historico.setDataRealizacao(dataRealizacao);
		historico.setValor(valor);
		historico.setObservacao(observacao);

		return historico;
	}


	public Historico criarHistorico(ResultSet resultado) {
		try {
			return this.criarHistorico(resultado.getLong(Historico.COL_ID),
					resultado.getLong(Historico.COL_CLIENTE_ID),
					 resultado.getLong(Historico.COL_FUNCIONARIO_ID),
					 resultado.getDate(Historico.COL_DATA_REALIZACAO),
					 resultado.getDouble(Historico.COL_VALOR),
					resultado.getString(Historico.COL_OBSERVACAO));
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao criar servico.", e);
		}
	}


	public List<Historico> criarHistoricos(ResultSet resultado) {
		try {
			List<Historico> historico = new ArrayList<Historico>();
			while (resultado.next()) {
				historico.add(this.criarHistorico(resultado));
			}
			return historico;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao criar servico.", e);
		}
	}


	public Historico criarHistorico(Long id,
			List<Servico> lista_servicos, Cliente savedCliente,
			Funcionario savedFuncionario, Date dataRealizacao, Double valor, String observacao) {
		Historico historico = new Historico();
		
		historico.setId(id);
		historico.setServicos(lista_servicos);
		historico.setCliente(savedCliente);
		historico.setFuncionario(savedFuncionario);
		historico.setDataRealizacao(dataRealizacao);
		historico.setValor(valor);
		historico.setObservacao(observacao);

		return historico;
	}

}
