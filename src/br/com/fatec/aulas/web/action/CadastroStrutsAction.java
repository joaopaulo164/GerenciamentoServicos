package br.com.fatec.aulas.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import br.com.fatec.aulas.api.entity.Cliente;
import br.com.fatec.aulas.api.entity.Funcionario;
import br.com.fatec.aulas.api.entity.Historico;
import br.com.fatec.aulas.api.entity.Servico;
import br.com.fatec.aulas.api.service.EntityDAO;
import br.com.fatec.aulas.core.helper.ClienteFactory;
import br.com.fatec.aulas.core.helper.FuncionarioFactory;
import br.com.fatec.aulas.core.helper.HistoricoFactory;
import br.com.fatec.aulas.core.helper.ServicoFactory;
import br.com.fatec.aulas.core.impl.ClienteDAOImpl;
import br.com.fatec.aulas.core.impl.FuncionarioDAOImpl;
import br.com.fatec.aulas.core.impl.HistoricoDAOImpl;
import br.com.fatec.aulas.core.impl.ServicoDAOImpl;
import com.opensymphony.xwork2.ActionSupport;

public class CadastroStrutsAction extends ActionSupport {
	private String nome;
	private String sobrenome;
	private String telefone;
	private Date dataCadastro;
	private Date dataAdmissao;
	private Date dataDemissao;
	private String email;
	private String descricao;
	private String resultado;
	
	//historico
	private Long idCliente;
	private Long idFuncionario;
	private Long idServico;
	private Date dataRealizacao;
	private Double valor;
	private String observacao;

	
	
	
	public String cadastrarServico() {
		ServicoFactory servicoFactory = new ServicoFactory();
		EntityDAO<Servico> servicoDAO = new ServicoDAOImpl();
		
		Servico servicoToSave = servicoFactory.criarServico(null, nome, descricao);
		Servico savedServico = servicoDAO.save(servicoToSave);
		
		resultado = ("ID: " + savedServico.getId() + " Nome: " + savedServico.getNome() + "Descrição: " + savedServico.getDescricao());
		
		return SUCCESS;
	}
	
	public String cadastrarCliente() {
		ClienteFactory clienteFactory = new ClienteFactory();
		EntityDAO<Cliente> clienteDAO = new ClienteDAOImpl();
		
		Cliente clienteToSave = clienteFactory.criarCliente(null, nome, sobrenome, telefone, dataCadastro, email);
		Cliente savedCliente = clienteDAO.save(clienteToSave);
		
		resultado = ("ID: " + savedCliente.getId() + " Nome: " + savedCliente.getNome() + " Sobrenome: " +
		savedCliente.getSobrenome() + " Telefone: " +savedCliente.getTelefone()  + " Data Cadastro: "
		+ savedCliente.getDataCadastro() + " E-mail: " + savedCliente.getEmail());
		
		return SUCCESS;
	}
	
	public String cadastrarFuncionario() {	
		FuncionarioFactory funcionarioFactory = new FuncionarioFactory();
		EntityDAO<Funcionario> funcionarioDAO = new FuncionarioDAOImpl();
		
		Funcionario funcionarioToSave = funcionarioFactory.criarFuncionario(null, nome, sobrenome, telefone, dataAdmissao, dataDemissao, email);
		Funcionario savedFuncionario = funcionarioDAO.save(funcionarioToSave);
		
		resultado = ("ID: " + savedFuncionario.getId() + " Nome: " + savedFuncionario.getNome() + " Sobrenome: "
		+ savedFuncionario.getSobrenome() + " Telefone: " +savedFuncionario.getTelefone()  + " Data Admissão"
		+ savedFuncionario.getDataAdmissao() + " Data Admissão" + savedFuncionario.getDataDemissao() 
		+ " E-mail: " + savedFuncionario.getEmail());
		
		return SUCCESS;
	}

	public String cadastrarHistorico() {				
		//Cliente
		EntityDAO<Cliente> clienteDAO = new ClienteDAOImpl();
		Cliente cliente = clienteDAO.findById(idCliente);
		System.out.println("cliente ID: " + idCliente);
		
		//cria funcionario
		EntityDAO<Funcionario> funcionarioDAO = new FuncionarioDAOImpl();
		Funcionario funcionario = funcionarioDAO.findById(idFuncionario);
		System.out.println("funcionario ID: " + idFuncionario);
				
		//cria servico
		EntityDAO<Servico> servicoDAO = new ServicoDAOImpl();
		List<Servico> lista_servicos =  new ArrayList<Servico>();
		Servico servico1 = servicoDAO.findById(idServico);
		lista_servicos.add(servicoDAO.save(servico1));
		System.out.println("Servicos: " + lista_servicos);
	
		//cria historico
		HistoricoFactory historicoFactory = new HistoricoFactory();
		EntityDAO<Historico> historicoDAO = new HistoricoDAOImpl();
		Historico historicoToSave = historicoFactory.criarHistorico(null, lista_servicos,
				cliente, funcionario, dataRealizacao, valor, observacao);
		System.out.println("data: " + dataRealizacao+ " valor: " + valor + " obs:" + observacao);
		
		Historico savedHistorico = historicoDAO.save(historicoToSave);
		
		resultado = ("ID: " + savedHistorico.getId() + " Servicos: " + savedHistorico.getServicos() + " Cliente: "
		+ savedHistorico.getCliente().getNome() + " Funcionario: " + savedHistorico.getFuncionario().getNome()
		+ " Data Realizacao: " + savedHistorico.getDataRealizacao() + " Valor: " + savedHistorico.getValor()
		+ " Observacao: " + savedHistorico.getObservacao());
		
		System.out.println("Resultado: " + resultado);
		
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

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Long getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public Long getIdServico() {
		return idServico;
	}

	public void setIdServico(Long idServico) {
		this.idServico = idServico;
	}

	public Date getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
