package fr.eni.ProjetJee.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ProjetJee.bll.BLLException;
import fr.eni.ProjetJee.bll.UtilisateurMger;
import fr.eni.ProjetJee.bo.Categorie;
import fr.eni.ProjetJee.bo.Utilisateur;
import fr.eni.ProjetJee.dal.CategorieDAO;
import fr.eni.ProjetJee.dal.DALException;
import fr.eni.ProjetJee.dal.DAOFactory;
import fr.eni.ProjetJee.dal.UtilisateursDAO;

@WebServlet("/testConnexion")
public class TestConnectionServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			UtilisateurMger utilisateurMger = UtilisateurMger.getInstance();
			Utilisateur user = new Utilisateur("bvrignaud", "baptiste", "vrignaud", "email2@gmail.com", "0651515146", "45 rue de la croix", "44100", "Nantes", utilisateurMger.generateHash("monMdp"), 100000000);
			utilisateurMger.ajouterUtilisateur(user);
			
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
