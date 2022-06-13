package fr.eni.ProjetJee.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ProjetJee.bll.ArticleVenduMger;
import fr.eni.ProjetJee.bll.BLLException;
import fr.eni.ProjetJee.bll.EnchereMger;
import fr.eni.ProjetJee.bo.ArticleVendu;
import fr.eni.ProjetJee.bo.Enchere;

/**
 * Servlet implementation class DetailVenteServlet
 */
@WebServlet("/DetailVente")
public class DetailVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EnchereMger encherMger;
	private ArticleVenduMger articleVenduMger;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailVenteServlet() {
        super();
        encherMger = EnchereMger.getInstance();
        articleVenduMger = ArticleVenduMger.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			try {
				Integer idArticleVendue = Integer.parseInt(request.getParameter("article"));
				Enchere enchere = encherMger.lastEnchereByArticle(idArticleVendue);
				ArticleVendu article = articleVenduMger.articleVenduById(idArticleVendue);
				
				request.setAttribute("enchere", enchere);
				request.setAttribute("article", article);
				
				request.getRequestDispatcher("/WEB-INF/pages/detailVente.jsp").forward(request, response);
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NumberFormatException e) {
			System.err.println("Pas d'article séléctioné !!");
			response.sendRedirect("/ProjetJee/DetailVente");
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
