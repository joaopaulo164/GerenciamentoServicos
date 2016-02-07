package br.com.fatec.aulas.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.aulas.api.entity.Servico;
import br.com.fatec.aulas.api.service.EntityDAO;
import br.com.fatec.aulas.core.helper.ConfigDBMapper;
import br.com.fatec.aulas.core.helper.ServicoFactory;
import br.com.fatec.aulas.core.impl.ServicoDAOImpl;

public class CadastroServicoServlet extends HttpServlet {
	
	private EntityDAO<Servico> entityDAO = new ServicoDAOImpl();
	private ServicoFactory servicoFactory = new ServicoFactory();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServerException, IOException{
		
		String nome = req.getParameter("nome");
		String descricao = req.getParameter("descricao");
		
		//cria servico
		Servico servicoToSave = this.servicoFactory.criarServico(null, nome, descricao);
		this.entityDAO.save(servicoToSave);
		
		System.out.println("ID:" + servicoToSave.getId() + " Nome" + servicoToSave.getNome() + "Descrição: " + servicoToSave.getDescricao());

		
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println(" Nome:" + req.getParameter("nome") + " Descrição: " + req.getParameter("descricao")+ " cadastrado!!!");
		out.println("</body>");
		out.println("</html>");
	}

}
