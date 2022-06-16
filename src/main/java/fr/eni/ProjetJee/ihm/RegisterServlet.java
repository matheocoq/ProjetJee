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
 * Servlet implementation class RegisterServer
 */
@WebServlet("/inscription")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/pages/inscription.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// recuperation de tous les champs
		String speudo = req.getParameter("pseudo");
		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");
		String email = req.getParameter("email");
		String tel = req.getParameter("tel");
		String rue = req.getParameter("rue");
		String codePostal = req.getParameter("codePostal");
		String ville = req.getParameter("ville");
		String mdp = req.getParameter("mdp");
		String confirmation = req.getParameter("confirmation");
		
		
		UtilisateurMger userMgr = UtilisateurMger.getInstance();
		try {
			// on verifie la longueur du numero de tel et si le pseudo, l'email ou telephone existe déjà dans la base de données.
			if((!tel.isEmpty() && tel.length() <10) || userMgr.checkPseudoEmailTel(speudo,email,tel)) {
				req.setAttribute("errorInscription", " Inscription incorrect.");
				req.getRequestDispatcher("/WEB-INF/pages/inscription.jsp").forward(req, resp);
			}else {
				// on verifie si le mdp et la confirmation sont les mêmes
				if(mdp.equals(confirmation)) {
					prenom = prenom.substring(0, 1).toUpperCase()+prenom.substring(1);
					Utilisateur user = new Utilisateur(0, speudo, nom.toUpperCase(), prenom, email, tel, rue, codePostal, ville.toUpperCase(), userMgr.generateHash(mdp), 100,false);
					try {
						
						userMgr.ajouterUtilisateur(user);
						req.getSession().setAttribute("utilisateur", user);
					} catch (BLLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					// on redirectionné vers la page d'acceuil du user connecté
					resp.sendRedirect("http://localhost:8080/ProjetJee/");
				}else {
					// le mot de passe et la confirmation doivent être identiques.
					//System.out.println("mdp et confirmation sont pas identiques!");
					req.setAttribute("errorInscription", " Inscription incorrect.");
					req.getRequestDispatcher("/WEB-INF/pages/inscription.jsp").forward(req, resp);
				}
			}
		} catch (DALException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
	}

}
