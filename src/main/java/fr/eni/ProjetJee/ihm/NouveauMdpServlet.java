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
 * Servlet implementation class NouveauMdpServlet
 */
@WebServlet("/nouveauMdp")
public class NouveauMdpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/nouveauMdp.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// on enregistre le nouveau mot de passe
		Utilisateur user = (Utilisateur)request.getSession().getAttribute("utilisateur");
		UtilisateurMger userMgr = UtilisateurMger.getInstance();
		String actuelMdp = request.getParameter("mdpActuel");
		String newMdp = request.getParameter("newMdp"); 
		String confirmation = request.getParameter("confirmation"); 
		
		// verification du mot de passe actuel
		
		if(userMgr.compareHashPassword(actuelMdp, user.getMotDePasse())) {
			
			System.out.println("mot de passe actuel ok");
			if(actuelMdp.equals(newMdp)) {
				request.setAttribute("newMdpError", " Nouveau mot de passe incorrect."); 
				request.getRequestDispatcher("/WEB-INF/pages/nouveauMdp.jsp").forward(request, response); 
			}
			
			if(newMdp.equals(confirmation)) { 
				user.setMotDePasse(userMgr.generateHash(newMdp));
				try { 
					userMgr.majUtilisateur(user); 
					request.getSession().setAttribute("utilisateur", user); 
				} catch (BLLException e) { 
					// TODO Auto-generated catch block 
					e.printStackTrace(); 
				} 								 
				// on redirectionné vers la page de modification de profil
				
				//request.setAttribute("infosNewMdp", "Nouveau mot de passe pris en compte.");
				HttpSession session = request.getSession(false);
				if (session != null && session.getAttribute("utilisateur") != null) {
					session.invalidate();
				}
				response.sendRedirect("http://localhost:8080/ProjetJee/Connexion"); 
			}else { 
				// le nouveau mot de passe et la confirmation doivent être identiques. 
				request.setAttribute("newMdpError", " Nouveau mot de passe incorrect."); 
				request.getRequestDispatcher("/WEB-INF/pages/nouveauMdp.jsp").forward(request, response); 
			} 
			
		}else {
			request.setAttribute("newMdpError", " Saisissez votre mot de passe actuel."); 
			request.getRequestDispatcher("/WEB-INF/pages/nouveauMdp.jsp").forward(request, response); 
		}
		
		
		
	}

}
