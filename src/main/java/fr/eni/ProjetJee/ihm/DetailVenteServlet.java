package fr.eni.ProjetJee.ihm;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ProjetJee.bll.ArticleVenduMger;
import fr.eni.ProjetJee.bll.BLLException;
import fr.eni.ProjetJee.bll.EnchereMger;
import fr.eni.ProjetJee.bll.RetraitManager;
import fr.eni.ProjetJee.bll.UtilisateurMger;
import fr.eni.ProjetJee.bo.ArticleVendu;
import fr.eni.ProjetJee.bo.Enchere;
import fr.eni.ProjetJee.bo.Retrait;
import fr.eni.ProjetJee.bo.Utilisateur;

/**
 * Servlet implementation class DetailVenteServlet
 */
@WebServlet("/DetailVente")
public class DetailVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EnchereMger encherMger;
	private ArticleVenduMger articleVenduMger;
	private RetraitManager retraitMger;
	private UtilisateurMger utilisateurMger;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailVenteServlet() {
        super();
        encherMger = EnchereMger.getInstance();
        articleVenduMger = ArticleVenduMger.getInstance();
        retraitMger = RetraitManager.getInstance();
        utilisateurMger = UtilisateurMger.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			try {
				Integer idArticleVendue = Integer.parseInt(request.getParameter("article"));
				String propositionError = request.getParameter("propositionError");
				Enchere enchere = encherMger.lastEnchereByArticle(idArticleVendue);
				ArticleVendu article = articleVenduMger.articleVenduById(idArticleVendue);
				Retrait retrait = retraitMger.getRetraitByArticle(idArticleVendue);
				
				request.setAttribute("enchere", enchere);
				request.setAttribute("article", article);
				request.setAttribute("retrait", retrait);
				
				if (propositionError != null) {
					request.setAttribute("propositionError", true);
				}
				
				request.getRequestDispatcher("/WEB-INF/pages/detailVente.jsp").forward(request, response);
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NumberFormatException e) {
			System.err.println("Pas d'article séléctioné !!");
			response.sendRedirect("/ProjetJee/accueil");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try { 
			Integer idArticle = Integer.parseInt(request.getParameter("article"));
			Integer proposition = Integer.parseInt(request.getParameter("proposition"));
			try {
				Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
				
				Enchere enchere = encherMger.lastEnchereByArticle(idArticle);
				ArticleVendu article = articleVenduMger.articleVenduById(idArticle);
				LocalDateTime now = LocalDateTime.now();
				
				if (utilisateur == null) {
					response.sendRedirect("/ProjetJee/Connexion");
					return;
				}
				
				if (proposition >= utilisateur.getCredit() || (proposition <= article.getMiseAPrix() || (enchere != null && proposition <= enchere.getMontantEnchere()))) {
					System.err.println("Propositin incorrect !!");
					response.sendRedirect("/ProjetJee/DetailVente?article="+idArticle+"&propositionError=true");
					return;
				}
				
				// Faire en mode transaction sql pour sécuriser le débit crédit des users.
				if (enchere != null) {
					Utilisateur ancienEnchereure = enchere.getNoUtilisateur();
					ancienEnchereure.setCredit(enchere.getMontantEnchere() + ancienEnchereure.getCredit());
					utilisateurMger.majUtilisateur(ancienEnchereure);
				}
				
				utilisateur.setCredit(utilisateur.getCredit() - proposition);
				utilisateurMger.majUtilisateur(utilisateur);
				
				request.getSession().setAttribute("utilisateur", utilisateur);
				
				Enchere newEnchere = new Enchere(now, proposition, utilisateur, article);
				encherMger.ajouterEnchere(newEnchere);
				
				response.sendRedirect("/ProjetJee/DetailVente?article="+idArticle);
				
			} catch (BLLException e) {
				System.err.println(e.getMessage());
				response.sendRedirect("/ProjetJee/DetailVente?article="+idArticle);
			}
		} catch (NumberFormatException e) {
			System.err.println(e.getMessage());
			response.sendRedirect("/ProjetJee/accueil");
		}
	}

}
