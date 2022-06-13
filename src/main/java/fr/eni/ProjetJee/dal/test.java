package fr.eni.ProjetJee.dal;

import fr.eni.ProjetJee.bo.Utilisateur;

public class test {

	public static void main(String[] args) {
		
	UtilisateursDAO utilisateurDAO =	DAOFactory.getDAOUtilisateur();
	Utilisateur u = new Utilisateur("test", "test", "test", "test", "test", "test", "test", "test", "test", 200);
	try {
		utilisateurDAO.insert(u);
	} catch (DALException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}

}
