package br.com.fatec.aulas.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import br.com.fatec.aulas.api.entity.Cliente;
import br.com.fatec.aulas.api.entity.Funcionario;
import br.com.fatec.aulas.api.entity.Historico;
import br.com.fatec.aulas.api.entity.Servico;
import br.com.fatec.aulas.api.service.EntityDAO;
import br.com.fatec.aulas.api.service.GerenciadorHistorico;
import br.com.fatec.aulas.core.helper.ClienteFactory;
import br.com.fatec.aulas.core.helper.FuncionarioFactory;
import br.com.fatec.aulas.core.helper.HistoricoFactory;
import br.com.fatec.aulas.core.helper.ServicoFactory;
import br.com.fatec.aulas.core.impl.ClienteDAOImpl;
import br.com.fatec.aulas.core.impl.FuncionarioDAOImpl;
import br.com.fatec.aulas.core.impl.HistoricoDAOImpl;
import br.com.fatec.aulas.core.impl.ServicoDAOImpl;
import br.com.fatec.aulas.core.service.GerenciadorHistoricoImpl;

import com.opensymphony.xwork2.ActionSupport;

public class HistoricoStrutsAction extends ActionSupport {

	private String resultado;
	
	//historico
	private Long id;
	private Long idCliente;
	private Long idFuncionario;
	private Long idServico;
	private Date dataRealizacao;
	private Double valor;
	private String observacao;
	private List<Historico> historicos;
	private List<Servico> servicos;
	
	EntityDAO<Cliente> clienteDAO = new ClienteDAOImpl();
	EntityDAO<Funcionario> funcionarioDAO = new FuncionarioDAOImpl();
	EntityDAO<Servico> servicoDAO = new ServicoDAOImpl();
	HistoricoFactory historicoFactory = new HistoricoFactory();
	EntityDAO<Historico> historicoDAO = new HistoricoDAOImpl();
	GerenciadorHistorico gerenciadorServicos = new GerenciadorHistoricoImpl();

	public String cadastrarHistorico() {				
		//Cliente
		Cliente cliente = clienteDAO.findById(idCliente);
		System.out.println("cliente ID: " + idCliente);
		
		//cria funcionario
		Funcionario funcionario = funcionarioDAO.findById(idFuncionario);
		System.out.println("funcionario ID: " + idFuncionario);
				
		//cria servico
		List<Servico> lista_servicos =  new ArrayList<Servico>();
		Servico servico1 = servicoDAO.findById(idServico);
		lista_servicos.add(servicoDAO.save(servico1));
		System.out.println("Servicos: " + lista_servicos);
	
		//cria historico
		Historico historicoToSave = historicoFactory.criarHistorico(null, lista_servicos,
				cliente, funcionario, dataRealizacao, valor, observacao);
		System.out.println("data: " + dataRealizacao+ " valor: " + valor + " obs:" + observacao);
		
		
		Historico savedHistorico = historicoDAO.save(historicoToSave);
		
		servicos = savedHistorico.getServicos();
		
		resultado = ("ID: " + savedHistorico.getId() + " Servicos: " + savedHistorico.getServicos() + " Cliente: "
		+ savedHistorico.getCliente().getNome() + " Funcionario: " + savedHistorico.getFuncionario().getNome()
		+ " Data Realizacao: " + savedHistorico.getDataRealizacao() + " Valor: " + savedHistorico.getValor()
		+ " Observacao: " + savedHistorico.getObservacao());
		
		System.out.println("Resultado: " + resultado);
		
		return SUCCESS;
	}

	public String listar() {
		historicos = historicoDAO.findAll();
		return "listar";
	}
	
	public String listarServicos() {
		servicos = historicoDAO.findById(id).getServicos();
		return "listarServicos";
	}
	
	
	public String remover() {
		Historico servicoToRemove = historicoDAO.findById(id);
		historicoDAO.remove(servicoToRemove);
		return listar();
	}
	
public String editar() {
	Cliente clienteToAlter = clienteDAO.findById(idCliente);
	Funcionario funcionarioToAlter = funcionarioDAO.findById(idFuncionario);
	Historico historicoToAlter = historicoDAO.findById(id);
	
	historicoToAlter.setCliente(clienteToAlter);
	historicoToAlter.setFuncionario(funcionarioToAlter);
	historicoToAlter.setDataRealizacao(dataRealizacao);
	historicoToAlter.setValor(valor);
	historicoToAlter.setObservacao(observacao);

	
	historicoDAO.update(historicoToAlter);
	resultado = ("ID: " + historicoToAlter.getId() + " Cliente: " + historicoToAlter.getCliente().getNome() 
			+ " Funcionario: " + historicoToAlter.getFuncionario().getNome()
			+ " Data Realizacao: " + historicoToAlter.getDataRealizacao() + " Valor: " + historicoToAlter.getValor()
			+ " Observacao: " + historicoToAlter.getObservacao());
	
	return SUCCESS;
}

public String addServico() {
	Historico historico = historicoDAO.findById(id);
	Servico servico = servicoDAO.findById(idServico);
	gerenciadorServicos.addServicosToHistorico(historico, servico);

	resultado = ("Servico"+ servico.getNome() +" incluído no Historico ID: " + historico.getId() + "com sucesso!!!");
	servicos = historico.getServicos();
	
	return SUCCESS;
}

public String removeServico() {
	Historico historico = historicoDAO.findById(id);
	Servico servico = servicoDAO.findById(idServico);
	gerenciadorServicos.removeServicosToHistorico(historico, servico);

	resultado = ("Servico ID:"+ idServico +" removido do Historico ID: " + historico.getId() + "com sucesso!!!");
	servicos = historico.getServicos();
	
	return SUCCESS;
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
	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public List<Historico> getHistoricos() {
		return historicos;
	}


	public void setHistoricos(List<Historico> historicos) {
		this.historicos = historicos;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}
}
