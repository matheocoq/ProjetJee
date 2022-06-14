package fr.eni.ProjetJee.ihm;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import fr.eni.ProjetJee.bll.ArticleVenduMger;
import fr.eni.ProjetJee.bll.BLLException;
import fr.eni.ProjetJee.bll.EnchereMger;
import fr.eni.ProjetJee.bll.UtilisateurMger;
import fr.eni.ProjetJee.bo.ArticleVendu;
import fr.eni.ProjetJee.bo.Enchere;
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
			
			//supression article cree
			articleMger.supprimerArticleVenduCree(user);
			//supprimer article en cours
			ArrayList<ArticleVendu> articles=articleMger.articleVenduEnCoursById(user);
			for (ArticleVendu articleVendu : articles) {
				Enchere enchere= enchereMger.lastEnchereByArticle(articleVendu.getNoArticle());
				ArrayList<Enchere> encheres =enchereMger.enchereByArticle(articleVendu);
				if(enchere==null) {
					articleMger.supprimerArticleVendu(articleVendu.getNoArticle());
				}
				else {
					 int montant =  enchere.getMontantEnchere() + enchere.getNoUtilisateur().getCredit();
					 enchere.getNoUtilisateur().setCredit(montant);
					 utilisateurMger.majUtilisateur(enchere.getNoUtilisateur());
					 enchereMger.supprimerEncheresByArticle(articleVendu);
					 articleMger.supprimerArticleVendu(articleVendu.getNoArticle());
				}
			
			}
			//supprime enchere
			ArrayList<ArticleVendu> articlesEnchere =articleMger.articleVenduUserEnchere(user);
			for (ArticleVendu articleVendu : articlesEnchere) {
				Enchere enchereGagner = enchereMger.lastEnchereByArticle(articleVendu.getNoArticle());
				if(enchereGagner.getNoUtilisateur().getNoUtilisateur()==user.getNoUtilisateur()) {
					 int montant =  enchereGagner.getMontantEnchere() + user.getCredit();
					 user.setCredit(montant);
					 utilisateurMger.majUtilisateur(user);
					 enchereMger.supprimerEncheresByArticle(articleVendu);
				}
				else {
					enchereMger.supprimerEncheresById(articleVendu, user);
				}
			}
			
			//ajout dans l'historique et suppression de l'utilisateur
			utilisateurMger.ajouterUtilisateurHistorique(user);
			utilisateurMger.supprimerUtilisateur(user.getNoUtilisateur());
			
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



}
