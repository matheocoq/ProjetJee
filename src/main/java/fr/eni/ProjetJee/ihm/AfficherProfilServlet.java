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
 * Servlet implementation class AfficherProfilServlet
 */
@WebServlet("/AfficherProfil")
public class AfficherProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AfficherProfilServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			try {
				
				int idUtilisateur = Integer.parseInt(request.getParameter("user"));
				HttpSession session = request.getSession(false);
				
				UtilisateurMger utilisateurMger = UtilisateurMger.getInstance();
				Utilisateur user = utilisateurMger.utilisateurById(idUtilisateur);
				request.setAttribute("user", user);
				
				Utilisateur userSession = null;
				
				if (session != null && session.getAttribute("utilisateur") != null) {
					userSession = (Utilisateur) session.getAttribute("utilisateur");
				}

				if (userSession != null && userSession.getNoUtilisateur() == user.getNoUtilisateur() ) {
					request.setAttribute("owner", true);
				}
				
				request.getRequestDispatcher("/WEB-INF/pages/afficherProfil.jsp").forward(request, response);
				
			} catch (NumberFormatException e) {
				System.err.println("Id de l'utilisateur incorrect");
				response.sendRedirect("/Projet_ENI-Encheres/accueil");
			}
		} catch (BLLException e) {
			System.err.println(e.getMessage());
			response.sendRedirect("/Projet_ENI-Encheres/accueil");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
