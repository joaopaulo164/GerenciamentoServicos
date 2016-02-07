package br.com.fatec.aulas.web.action;

import java.util.Date;
import java.util.List;

import br.com.fatec.aulas.api.entity.Cliente;
import br.com.fatec.aulas.api.service.EntityDAO;
import br.com.fatec.aulas.core.helper.ClienteFactory;
import br.com.fatec.aulas.core.impl.ClienteDAOImpl;
import com.opensymphony.xwork2.ActionSupport;

public class ClienteStrutsAction extends ActionSupport {
	private Long id;
	private String nome;
	private String sobrenome;
	private String telefone;
	private Date dataCadastro;
	private String email;
	private String descricao;
	private String resultado;
	private List<Cliente> clientes;
	ClienteFactory clienteFactory = new ClienteFactory();
	EntityDAO<Cliente> clienteDAO = new ClienteDAOImpl();

	
	public String cadastrarCliente() {	
		Cliente clienteToSave = clienteFactory.criarCliente(null, nome, sobrenome, telefone, dataCadastro, email);
		Cliente savedCliente = clienteDAO.save(clienteToSave);
		
		resultado = ("ID: " + savedCliente.getId() + " Nome: " + savedCliente.getNome() + " Sobrenome: " +
		savedCliente.getSobrenome() + " Telefone: " +savedCliente.getTelefone()  + " Data Cadastro: "
		+ savedCliente.getDataCadastro() + " E-mail: " + savedCliente.getEmail());
		
		return SUCCESS;
	}
	
	public String listar() {
		clientes = clienteDAO.findAll();
		return "listar";
	}
	
	
	public String remover() {
		Cliente clienteToRemove = clienteDAO.findById(id);
		clienteDAO.remove(clienteToRemove);
		return listar();
	}
	
public String editar() {
	Cliente clienteToAlter = clienteDAO.findById(id);
	clienteToAlter.setNome(nome);
	clienteToAlter.setSobrenome(sobrenome);
	clienteToAlter.setTelefone(telefone);
	clienteToAlter.setDataCadastro(dataCadastro);
	clienteToAlter.setEmail(email);
	clienteDAO.update(clienteToAlter);
	resultado = ("ID: " + clienteToAlter.getId() + " Nome: " + clienteToAlter.getNome() + " Sobrenome: " +
	clienteToAlter.getSobrenome() + " Telefone: " +clienteToAlter.getTelefone()  + " Data Cadastro: "
	+ clienteToAlter.getDataCadastro() + " E-mail: " + clienteToAlter.getEmail());
	
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

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Long getId() {
		return id;
	}

	public Long setId(Long id) {
		return this.id = id;
	}

}
