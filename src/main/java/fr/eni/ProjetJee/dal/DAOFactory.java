package fr.eni.ProjetJee.dal;

import fr.eni.ProjetJee.dal.sqlServer.ArticleVenduDAOSqlServerlmpl;
import fr.eni.ProjetJee.dal.sqlServer.CategorieDAOImpl;
import fr.eni.ProjetJee.dal.sqlServer.EnchereDAOsqlServerImpl;
import fr.eni.ProjetJee.dal.sqlServer.RetraitDAOImpl;
import fr.eni.ProjetJee.dal.sqlServer.UtilisateurDAOSqlServerImpl;

public class DAOFactory {

	public static UtilisateursDAO getDAOUtilisateur() {
		return new UtilisateurDAOSqlServerImpl();
	}
	
	public static ArticleVenduDAO getDAOArticleVendu() {
		return new ArticleVenduDAOSqlServerlmpl();
	}
	
	public static EnchereDAO getDAOEnchere() {
		return new EnchereDAOsqlServerImpl();
	}
	
	public static CategorieDAO getDAOCategorie() {
		return new CategorieDAOImpl();
	}
	
	public static RetraitDAO getDAORetrait() {
		return new RetraitDAOImpl();
	}
	
}
