package fr.eni.ProjetJee.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ProjetJee.bll.ArticleVenduMger;
import fr.eni.ProjetJee.bll.BLLException;
import fr.eni.ProjetJee.bll.EnchereMger;
import fr.eni.ProjetJee.bll.UtilisateurMger;
import fr.eni.ProjetJee.bo.Utilisateur;

/**
 * Servlet implementation class SupprimerServlet
 */
@WebServlet("/SupprimerServlet")
public class SupprimerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idUtilisateur = Integer.parseInt(request.getParameter("user"));
		HttpSession session = request.getSession(false);
		
		UtilisateurMger utilisateurMger = UtilisateurMger.getInstance();
		ArticleVenduMger articleMger = ArticleVenduMger.getInstance();
		EnchereMger enchereMger = EnchereMger.getInstance();
		try {
			Utilisateur user = utilisateurMger.utilisateurById(idUtilisateur);
			utilisateurMger.ajouterUtilisateurHistorique(user);
			articleMger.supprimerArticleVenduCree(user);
			enchereMger.supprimerEncheresById(null, user)
			
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



}
