package fr.eni.ProjetJee.ihm;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class filtre
 */
@WebFilter(
		urlPatterns="/*",
		dispatcherTypes= {
					DispatcherType.REQUEST,
					DispatcherType.INCLUDE,
					DispatcherType.FORWARD,
					DispatcherType.ERROR
					}
	)
public class filtre implements Filter {


	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Object seesion = ((HttpServletRequest) request).getSession().getAttribute("utilisateur");
		if(httpRequest.getServletPath().toLowerCase().contains("logoeniencheres.png") ||httpRequest.getServletPath().toLowerCase().contains("logoeni.svg") || httpRequest.getServletPath().toLowerCase().contains("style") ||httpRequest.getServletPath().toLowerCase().contains("accueil")|| httpRequest.getServletPath().toLowerCase()=="" || httpRequest.getServletPath().toLowerCase().contains("connexion") || httpRequest.getServletPath().toLowerCase().contains("inscription") ||seesion!=null)
		{
			
			//Laissons passer la requête vers la ressource qui est autorisée
			chain.doFilter(request, response);
		}
		else
		{
			//renvoyons une 403 à l'utilisateur
			((HttpServletResponse) response).sendRedirect("http://localhost:8080/ProjetJee/Connexion");
		}
	}

	

}
