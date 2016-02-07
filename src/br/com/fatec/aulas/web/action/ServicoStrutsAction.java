package br.com.fatec.aulas.web.action;

import java.util.Date;
import java.util.List;

import br.com.fatec.aulas.api.entity.Funcionario;
import br.com.fatec.aulas.api.entity.Servico;
import br.com.fatec.aulas.api.service.EntityDAO;
import br.com.fatec.aulas.core.helper.ServicoFactory;
import br.com.fatec.aulas.core.impl.ServicoDAOImpl;
import com.opensymphony.xwork2.ActionSupport;

public class ServicoStrutsAction extends ActionSupport {
	private Long id;
	private String nome;
	private String descricao;
	private String resultado;
	private List<Servico> servicos;
	
	ServicoFactory servicoFactory = new ServicoFactory();
	EntityDAO<Servico> servicoDAO = new ServicoDAOImpl();
	
	public String cadastrarServico() {
		Servico servicoToSave = servicoFactory.criarServico(null, nome, descricao);
		Servico savedServico = servicoDAO.save(servicoToSave);
		
		resultado = ("ID: " + savedServico.getId() + " Nome: " + savedServico.getNome() + "Descrição: " + savedServico.getDescricao());
		
		return SUCCESS;
	}
	
	public String listar() {
		servicos = servicoDAO.findAll();
		return "listar";
	}
	
	
	public String remover() {
		Servico servicoToRemove = servicoDAO.findById(id);
		servicoDAO.remove(servicoToRemove);
		return listar();
	}
	
public String editar() {
	Servico servicoToAlter = servicoDAO.findById(id);
	servicoToAlter.setNome(nome);
	servicoToAlter.setDescricao(descricao);
	servicoDAO.update(servicoToAlter);
	resultado = ("ID: " + servicoToAlter.getId() + " Nome: " + servicoToAlter.getNome() + " Sobrenome: " +
	servicoToAlter.getDescricao());
	
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

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
