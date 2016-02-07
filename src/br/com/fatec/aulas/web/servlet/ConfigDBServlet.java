package br.com.fatec.aulas.web.servlet;

import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import br.com.fatec.aulas.core.helper.ConfigDBMapper;

public class ConfigDBServlet extends HttpServlet {
	
	@Override
	public void init() throws ServletException{
		
		ConfigDBMapper.getInstance().setDefaultConnectionName("fatec");
	}

}
