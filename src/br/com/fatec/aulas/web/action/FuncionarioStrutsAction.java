package br.com.fatec.aulas.web.action;

import java.util.Date;
import java.util.List;

import br.com.fatec.aulas.api.entity.Cliente;
import br.com.fatec.aulas.api.entity.Funcionario;
import br.com.fatec.aulas.api.service.EntityDAO;
import br.com.fatec.aulas.core.helper.FuncionarioFactory;
import br.com.fatec.aulas.core.impl.FuncionarioDAOImpl;
import com.opensymphony.xwork2.ActionSupport;

public class FuncionarioStrutsAction extends ActionSupport {
	private Long id;
	private String nome;
	private String sobrenome;
	private String telefone;
	private Date dataAdmissao;
	private Date dataDemissao;
	private String email;
	private String descricao;
	private String resultado;
	private List<Funcionario> funcionarios;
	
	FuncionarioFactory funcionarioFactory = new FuncionarioFactory();
	EntityDAO<Funcionario> funcionarioDAO = new FuncionarioDAOImpl();
	
	public String cadastrarFuncionario() {			
		Funcionario funcionarioToSave = funcionarioFactory.criarFuncionario(null, nome, sobrenome, telefone, dataAdmissao, dataDemissao, email);
		Funcionario savedFuncionario = funcionarioDAO.save(funcionarioToSave);
		
		resultado = ("ID: " + savedFuncionario.getId() + " Nome: " + savedFuncionario.getNome() + " Sobrenome: "
		+ savedFuncionario.getSobrenome() + " Telefone: " +savedFuncionario.getTelefone()  + " Data Admissão"
		+ savedFuncionario.getDataAdmissao() + " Data Admissão" + savedFuncionario.getDataDemissao() 
		+ " E-mail: " + savedFuncionario.getEmail());
		
		return SUCCESS;
	}
	
	
	public String listar() {
		funcionarios = funcionarioDAO.findAll();
		return "listar";
	}
	
	
	public String remover() {
		Funcionario funcionarioToRemove = funcionarioDAO.findById(id);
		funcionarioDAO.remove(funcionarioToRemove);
		return listar();
	}
	
public String editar() {
	Funcionario funcionarioToAlter = funcionarioDAO.findById(id);
	funcionarioToAlter.setNome(nome);
	funcionarioToAlter.setSobrenome(sobrenome);
	funcionarioToAlter.setTelefone(telefone);
	funcionarioToAlter.setDataAdmissao(dataAdmissao);
	funcionarioToAlter.setDataDemissao(dataDemissao);
	funcionarioToAlter.setEmail(email);
	funcionarioDAO.update(funcionarioToAlter);
	resultado = ("ID: " + funcionarioToAlter.getId() + " Nome: " + funcionarioToAlter.getNome() + " Sobrenome: " +
	funcionarioToAlter.getSobrenome() + " Telefone: " +funcionarioToAlter.getTelefone()  + " Data Admissao: "
	+ funcionarioToAlter.getDataAdmissao() + " Data Demissao: "
			+ funcionarioToAlter.getDataDemissao() +" E-mail: " + funcionarioToAlter.getEmail());
	
	return SUCCESS;
}
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public Date getDataDemissao() {
		return dataDemissao;
	}

	public void setDataDemissao(Date dataDemissao) {
		this.dataDemissao = dataDemissao;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}


	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

}
