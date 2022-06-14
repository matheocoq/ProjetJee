package fr.eni.ProjetJee.ihm;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ProjetJee.bll.ArticleVenduMger;
import fr.eni.ProjetJee.bll.BLLException;
import fr.eni.ProjetJee.bll.CategorieMger;
import fr.eni.ProjetJee.bll.RetraitManager;
import fr.eni.ProjetJee.bo.ArticleVendu;
import fr.eni.ProjetJee.bo.Categorie;
import fr.eni.ProjetJee.bo.Retrait;
import fr.eni.ProjetJee.bo.Utilisateur;

/**
 * Servlet implementation class NouvelleVenteServlet
 */
@WebServlet("/NouvelleVente")
public class NouvelleVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	
	private CategorieMger categorieMger;
	private RetraitManager retraitMger;
	private ArticleVenduMger articleVenduMger;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NouvelleVenteServlet() {
        super();
        // TODO Auto-generated constructor stub
        categorieMger = CategorieMger.getInstance();
        retraitMger = RetraitManager.getInstance();
        articleVenduMger = ArticleVenduMger.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			LocalDateTime now = LocalDateTime.now();
			List<Categorie> categorieList = categorieMger.getCategories();

			request.setAttribute("categorieList", categorieList);
			request.setAttribute("dateNow", DF.format(now));
			request.getRequestDispatcher("/WEB-INF/pages/nouvelleVente.jsp").forward(request, response);
		} catch (BLLException e) {
			System.err.println(e.getMessage());
			response.sendRedirect("/ProjetJee/accueil");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			try {
				Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
				
				String article = request.getParameter("article");
				String description = request.getParameter("description");
				Integer idCategorie = Integer.parseInt(request.getParameter("categorie"));
				// A revoire pour l'upload file en java
				String photo = request.getParameter("photo");
				Integer prix = Integer.parseInt(request.getParameter("prix"));
				String dateDebut = request.getParameter("dateDebut");
				String dateFin = request.getParameter("dateFin");
				String rue = request.getParameter("rue");
				String codePostal = request.getParameter("codePostal");
				String ville = request.getParameter("ville");
				
				if ("".equals(article) || "".equals(description) || "".equals(dateDebut) || "".equals(dateFin) || "".equals(rue) || "".equals(codePostal) || "".equals(ville)) {
					System.err.println("Un ou plusieur champ innatendu !!");
					request.setAttribute("nouvelleVenteError", true);
					request.getRequestDispatcher("/WEB-INF/pages/nouvelleVente.jsp").forward(request, response);
					return;
				}
				
				LocalDateTime dateTimeDebut = LocalDateTime.parse(dateDebut + " 00:00", DTF);
				LocalDateTime dateTimeFin = LocalDateTime.parse(dateFin + " 00:00", DTF);
				
				if (dateTimeDebut.isAfter(dateTimeFin)) {
					request.setAttribute("nouvelleVenteError", true);
					request.getRequestDispatcher("/WEB-INF/pages/nouvelleVente.jsp").forward(request, response);
					return;
				}
				
				Categorie categorie = categorieMger.getCategorieById(idCategorie);
				Retrait retrait = new Retrait(rue, codePostal, ville);
				ArticleVendu articleVendu = new ArticleVendu(article, description, dateTimeDebut, dateTimeFin, "Créée", prix, 0, photo, utilisateur, retrait, categorie);				
				
				articleVenduMger.ajouterArticleVendu(articleVendu);
				retraitMger.addRetraits(retrait);
				articleVendu.setLieuRetrait(retrait);
				articleVenduMger.majArticleVendu(articleVendu);
		
				System.out.println("" + articleVendu.getNomArticle() + " à été ajouté avec succes !!");
				response.sendRedirect("/ProjetJee/NouvelleVente");
				
			} catch (BLLException e) {
				System.err.println(e.getMessage());
				response.sendRedirect("/ProjetJee/NouvelleVente");
			}
		} catch (NumberFormatException e) {
			System.err.println("Le prix attendu ou la catégorie est incorrect !!");
			request.setAttribute("nouvelleVenteError", true);
			request.getRequestDispatcher("/WEB-INF/pages/nouvelleVente.jsp").forward(request, response);
		}
	}

}