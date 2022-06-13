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
			Utilisateur user = new Utilisateur("testCon", "test", "connexion", "email@gmail.com", "0651515166", "45", "44100", "Nantes", utilisateurMger.generateHash("monMdp"), 0);
			utilisateurMger.ajouterUtilisateur(user);
			CategorieDAO categorieDAO = DAOFactory.getDAOCategorie();
			Categorie u = new Categorie("Informatique");
			Categorie u1 = new Categorie("Ameublement");
			Categorie u2 = new Categorie("Sport&Loisir");
			Categorie u3 = new Categorie("Vetement");
			try {
				categorieDAO.insert(u);
				categorieDAO.insert(u1);
				categorieDAO.insert(u2);
				categorieDAO.insert(u3);
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
