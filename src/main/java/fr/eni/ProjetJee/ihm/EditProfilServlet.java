package fr.eni.ProjetJee.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ProjetJee.bll.BLLException;
import fr.eni.ProjetJee.bll.UtilisateurMger;
import fr.eni.ProjetJee.bo.Utilisateur;
import fr.eni.ProjetJee.dal.DALException;

/**
 * Servlet implementation class EditProfilServlet
 */
@WebServlet("/editProfil")
public class EditProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/editProfil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		
		// recuperation de tous les champs 
		String speudo = request.getParameter("pseudo"); 
		String nom = request.getParameter("nom"); 
		String prenom = request.getParameter("prenom"); 
		String email = request.getParameter("email"); 
		String tel = request.getParameter("tel"); 
		String rue = request.getParameter("rue"); 
		String codePostal = request.getParameter("codePostal"); 
		String ville = request.getParameter("ville"); 
		String mdp = request.getParameter("mdp"); 
		String bddMdp = request.getParameter("hashMdp");
		int noUser = Integer.parseInt(request.getParameter("noUser"));
		UtilisateurMger userMgr = UtilisateurMger.getInstance(); 
		// on verifie la longueur du numero de tel et si le pseudo, l'email ou telephone existe déjà dans la base de données. 
		try {
			if(!tel.isEmpty() && tel.length()<10) { 
				request.setAttribute("errorModification", " Modification incorrect."); 
				request.getRequestDispatcher("/WEB-INF/pages/editProfil.jsp").forward(request, response); 
			}else { 
				//On regarde si le mot de passe saisie est correct 
				if(userMgr.compareHashPassword(mdp,bddMdp)) { 
					Utilisateur user = new Utilisateur(noUser,speudo, nom, prenom, email, tel, rue, codePostal, ville, userMgr.generateHash(mdp), 0,false); 
					try { 
						userMgr.majUtilisateur(user); 
						request.getSession().setAttribute("utilisateur", user); 
					} catch (BLLException e) { 
						// TODO Auto-generated catch block 
						e.printStackTrace(); 
					} 			 
					// on va redirectionné vers la page d'acceuil du user connecté 
					// informer que les infos ont été modifié
					response.sendRedirect("http://localhost:8080/ProjetJee/editProfil"); 
				}else {
					request.setAttribute("errorModification", " Modification incorrect."); 
					request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
				}
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		 
		
	}

}
