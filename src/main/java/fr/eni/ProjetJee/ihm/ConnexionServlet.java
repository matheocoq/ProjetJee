package fr.eni.ProjetJee.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ProjetJee.bll.BLLException;
import fr.eni.ProjetJee.bll.UtilisateurMger;
import fr.eni.ProjetJee.bo.Utilisateur;

/**
 * Servlet implementation class ConnexionServlet
 */
@WebServlet({"/Connexion"})
public class ConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnexionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.getSession(false);
		request.getRequestDispatcher("/WEB-INF/pages/connexion.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String mdp = request.getParameter("mdp");
		try {
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(10*60);
			UtilisateurMger utilisateurMger = UtilisateurMger.getInstance();
			Utilisateur userCo = utilisateurMger.verifConnexion(login, mdp);
			System.out.println("connexion");
			session.setAttribute("utilisateur", userCo);
			response.sendRedirect("/Projet_ENI-Encheres/accueil");
		} catch (BLLException e) {
			System.err.println("Login ou mot de passe incorrect !!");
			request.setAttribute("errorConnexion", true);
			request.getRequestDispatcher("/WEB-INF/pages/connexion.jsp").forward(request, response);
		}
	}

}
