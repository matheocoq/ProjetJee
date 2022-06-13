package fr.eni.ProjetJee.ihm;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import fr.eni.ProjetJee.bll.ArticleVenduMger;
import fr.eni.ProjetJee.bll.BLLException;
import fr.eni.ProjetJee.bll.CategorieMger;
import fr.eni.ProjetJee.bo.ArticleVendu;
import fr.eni.ProjetJee.bo.Categorie;
import fr.eni.ProjetJee.bo.Utilisateur;
import fr.eni.ProjetJee.dal.DALException;

/**
 * Servlet implementation class AccueilServlet
 */
@WebServlet({"", "/accueil"})
public class AccueilServlet extends HttpServlet {
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategorieMger categorieMger = CategorieMger.getInstance();
		ArticleVenduMger articleVenduMger = ArticleVenduMger.getInstance();
		ArrayList<ArticleVendu> articles=null;
		 try {
			List<Categorie> categories = categorieMger.getCategories();
			articles=articleVenduMger.allArticleVendu();
			Object seesion = request.getSession().getAttribute("utilisateur");
			if(seesion!=null) {
				if(request.getParameter("rechercher")!=null) {
					String name= request.getParameter("nom");
					String categorie= request.getParameter("categorie");
					String checkbox= request.getParameter("radioAchat");
					String ouvertes= request.getParameter("ouvertes");
					String mesEnchere= request.getParameter("mesEnchere");
					String mesEnchereReporter= request.getParameter("mesEnchereReporter");
					String mesVenteCours= request.getParameter("mesVenteCours");
					String mesVenteDebutees= request.getParameter("mesVenteDebutees");
					String mesVentetTerminees= request.getParameter("mesVentetTerminees");
					articles=articleVenduMger.filtreCo(categorie ,name ,(Utilisateur) seesion ,checkbox ,ouvertes ,mesEnchere,mesEnchereReporter,mesVenteCours,mesVenteDebutees,mesVentetTerminees);
				}
				else {
					articles=articleVenduMger.allArticleVendu();
				}
				request.setAttribute("categories", categories);
				request.setAttribute("articles", articles);
				request.getRequestDispatcher("WEB-INF/pages/accueil.jsp").forward(request, response);
			}
			else {
				if(request.getParameter("rechercher")!=null) {

					String nom= request.getParameter("nom");
					String categorie= request.getParameter("categorie");
					articles=articleVenduMger.filtreNonCo(categorie,nom);
				}
				else {
					articles=articleVenduMger.allArticleVendu();
				}
				request.setAttribute("categories", categories);
				request.setAttribute("articles", articles);
				request.getRequestDispatcher("WEB-INF/pages/accueilNonCo.jsp").forward(request, response);
			}
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
