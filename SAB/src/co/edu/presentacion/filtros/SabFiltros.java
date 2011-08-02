package co.edu.presentacion.filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.edu.unicatolica.modelo.SabUsuario;

public class SabFiltros implements Filter {

	private Long administrador = 1L;
	private Long estudiante = 2L;
	private Long profesor = 3L;
	private Long otros = 4L;

	public void destroy() {
		// TODO Destroy Filtro
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		String pageRequested = req.getRequestURI().toString();
		int cambioAcceso = -1;

		if (session == null) {
			session = req.getSession(true);
			resp.sendRedirect("../index.jspx");
		} else {
			if ((session.getAttribute("usuario") == null)
					&& (!pageRequested.contains("index.jspx"))) {
				resp.sendRedirect("../index.jspx");
			}

			SabUsuario usuario = (SabUsuario) session.getAttribute("usuario");
			if (usuario != null) {
				// Todos Tiene Permiso incluyendo al invitado
				if (pageRequested.contains("consulta.jspx"))
					cambioAcceso = 0;

				if(usuario.getSabRol() != null){
					// Administrador
					if (usuario.getSabRol().getIdRol().equals(administrador)) {
						if (pageRequested.contains("area.jspx"))
							cambioAcceso = 0;
						if (pageRequested.contains("autor.jspx"))
							cambioAcceso = 0;
						if (pageRequested.contains("edicion.jspx"))
							cambioAcceso = 0;
						if (pageRequested.contains("editorial.jspx"))
							cambioAcceso = 0;
						if (pageRequested.contains("estadolibro.jspx"))
							cambioAcceso = 0;
						if (pageRequested.contains("libro.jspx"))
							cambioAcceso = 0;
						if (pageRequested.contains("prestamo.jspx"))
							cambioAcceso = 0;
						if (pageRequested.contains("rol.jspx"))
							cambioAcceso = 0;
						if (pageRequested.contains("usuario.jspx"))
							cambioAcceso = 0;
						if (pageRequested.contains("volumen.jspx"))
							cambioAcceso = 0;
					}
	
					// Estudiante Profesor
					else if (usuario.getSabRol().getIdRol().equals(estudiante)
							|| usuario.getSabRol().getIdRol().equals(profesor)) {
						if (pageRequested.contains("asignarComite.jspx"))
							cambioAcceso = 0;
					}
				}

			}
		}

		if ((session.getAttribute("usuario") != null) && cambioAcceso != 0) {
			resp.sendRedirect("../403.jsp");
		}
		
		try {
			chain.doFilter(request, response); // continue filtering
		} catch (Exception e) {
			// e.printStackTrace();
		}

	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Init Filtro
	}

}
