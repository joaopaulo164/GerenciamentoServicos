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



public class FiltroServlet extends HttpServlet {
	
	private EntityDAO<Servico> entityDAO = new ServicoDAOImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServerException, IOException, ServletException{
		
	//	PrintWriter out = resp.getWriter();
	//	out.println(req.getAttribute("filtros"));
		
		Object listaEntidades = entityDAO.findAll();
		req.setAttribute("listaEntidades", listaEntidades);
		req.getRequestDispatcher("/listarEntidades.jsp").forward(req, resp);
		


	}

}
