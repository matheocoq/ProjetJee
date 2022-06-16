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
import fr.eni.ProjetJee.bll.RetraitManager;
import fr.eni.ProjetJee.bll.UtilisateurMger;
import fr.eni.ProjetJee.bo.ArticleVendu;
import fr.eni.ProjetJee.bo.Enchere;
import fr.eni.ProjetJee.bo.Retrait;
import fr.eni.ProjetJee.bo.Utilisateur;

/**
 * Servlet implementation class SupprimerServlet
 */
@WebServlet("/supprimer")
public class SupprimerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur user = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		UtilisateurMger utilisateurMger = UtilisateurMger.getInstance();
		ArticleVenduMger articleMger = ArticleVenduMger.getInstance();
		EnchereMger enchereMger = EnchereMger.getInstance();
		RetraitManager retraitMger = RetraitManager.getInstance();
		try {
			//supprime enchere OK
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
			
		
			ArrayList<ArticleVendu> listeArticle=articleMger.articleVenduByUtilisateur(user);
			for (ArticleVendu article : listeArticle) {
				if(article.getEtatVente().equals("Créée")) {
					if(article.getLieuRetrait()!=null) {
						retraitMger.removeRetrait(article.getLieuRetrait().getNoRetrait());
					}
				
					articleMger.supprimerArticleVendu(article.getNoArticle());
				}
				if(article.getEtatVente().equals("En cours")) {
					Enchere enchere= enchereMger.lastEnchereByArticle(article.getNoArticle());
					if(enchere==null) {
						if(article.getLieuRetrait()!=null) {
							retraitMger.removeRetrait(article.getLieuRetrait().getNoRetrait());
						}
					
						articleMger.supprimerArticleVendu(article.getNoArticle());
					}
					else {
						 int montant =  enchere.getMontantEnchere() + enchere.getNoUtilisateur().getCredit();
						 enchere.getNoUtilisateur().setCredit(montant);
						 utilisateurMger.majUtilisateur(enchere.getNoUtilisateur());
						 enchereMger.supprimerEncheresByArticle(article);
						 if(article.getLieuRetrait()!=null) {
								retraitMger.removeRetrait(article.getLieuRetrait().getNoRetrait());
						 }
						
						 articleMger.supprimerArticleVendu(article.getNoArticle());
					}
				}
				if(article.getEtatVente().equals("Enchères terminées")) {
					 if(article.getGagnant()!=null) {
			
						int montant =  article.getPrixDeVente() + article.getGagnant().getCredit();
						article.getGagnant().setCredit(montant);
						utilisateurMger.majUtilisateur(article.getGagnant());
						
					 }
					 enchereMger.supprimerEncheresByArticle(article);
					 if(article.getLieuRetrait()!=null) {
							retraitMger.removeRetrait(article.getLieuRetrait().getNoRetrait());
					 }
				
					 articleMger.supprimerArticleVendu(article.getNoArticle());
				}
				if(article.getEtatVente().equals("Retrait effectué")) {
					enchereMger.supprimerEncheresByArticle(article);
					 if(article.getLieuRetrait()!=null) {
							retraitMger.removeRetrait(article.getLieuRetrait().getNoRetrait());
					 }
					
					 articleMger.supprimerArticleVendu(article.getNoArticle());
				}
			}
			
			// suppression de l'utilisateur
			
			utilisateurMger.supprimerUtilisateur(user.getNoUtilisateur());
			HttpSession session = request.getSession(false);
			if (session != null && session.getAttribute("utilisateur") != null) {
				session.invalidate();
			}
			response.sendRedirect("/ProjetJee/accueil");
			
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



}
