package br.com.fatec.aulas.core.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.fatec.aulas.api.entity.Funcionario;

public class FuncionarioFactory {

	public Funcionario criarFuncionario(Long id, String nome, String sobrenome, String telefone, Date dataAdmissao) {
		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		funcionario.setNome(nome);
		funcionario.setNome(sobrenome);
		funcionario.setTelefone(telefone);
		funcionario.setDataAdmissao(dataAdmissao);
		return funcionario;
	}

	public Funcionario criarFuncionario(Long id, String nome, String sobrenome, String telefone, Date dataAdmissao,
			Date dataDemissao, String mail) {
		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		funcionario.setNome(nome);
		funcionario.setSobrenome(sobrenome);
		funcionario.setTelefone(telefone);
		funcionario.setDataAdmissao(dataAdmissao);
		funcionario.setDataDemissao(dataDemissao);
		funcionario.setEmail(mail);
		return funcionario;
	}
	

	public Funcionario criarFuncionario(ResultSet resultado) {
		try {
			return this.criarFuncionario(resultado.getLong(Funcionario.COL_ID), resultado.getString(Funcionario.COL_NOME),
					resultado.getString(Funcionario.COL_SOBRENOME), resultado.getString(Funcionario.COL_TELEFONE),
					resultado.getDate(Funcionario.COL_DATA_ADMISSAO), resultado.getDate(Funcionario.COL_DATA_DEMISSAO),
					resultado.getString(Funcionario.COL_EMAIL));
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao criar funcionario.", e);
		}
	}

	public List<Funcionario> criarFuncionarios(ResultSet resultado) {
		try {
			List<Funcionario> funcionario = new ArrayList<Funcionario>();
			while (resultado.next()) {
				funcionario.add(this.criarFuncionario(resultado));
			}
			return funcionario;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao criar funcionario.", e);
		}
	}

}
