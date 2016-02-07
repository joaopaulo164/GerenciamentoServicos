package br.com.fatec.aulas.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Filtro1 implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		Object filtrosObject = req.getAttribute("filtros");
		List<String> filtros = null;
		if (filtrosObject != null) {
			filtros = (ArrayList<String>) filtrosObject;
		} else {
			filtros = new ArrayList<String>();
		}
		
		filtros.add(this.getClass().getCanonicalName());
		req.setAttribute("filtros", filtros);
		chain.doFilter(req,resp);
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
