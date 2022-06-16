package fr.eni.ProjetJee.bll;

import java.time.LocalDateTime;
import java.util.ArrayList;

import fr.eni.ProjetJee.bo.ArticleVendu;
import fr.eni.ProjetJee.bo.Enchere;
import fr.eni.ProjetJee.bo.Utilisateur;
import fr.eni.ProjetJee.dal.DALException;
import fr.eni.ProjetJee.dal.DAOFactory;
import fr.eni.ProjetJee.dal.EnchereDAO;

public class EnchereMger {
	
	EnchereDAO enchereDAO;
	
	private static EnchereMger instance;
	
	/**
	 * Singleton qui permet ainsi la création d'un seul objet (EnchereMger)
	 * @return une instance de EnchereMger
	 */
	public static EnchereMger getInstance() {
		if(instance == null) {
			instance = new EnchereMger();
		}
		return instance;
	}
	
	/**
	 * Récupere l'interface commune à tout les implémentations de EnchereDAO
	 */
	private EnchereMger() {
		enchereDAO = DAOFactory.getDAOEnchere();
	}
	
	/**
	 * ajouterEnchere permet l'ajout d'une nouvelle enchere en dans la BDD
	 * @param enchere
	 * @throws BLLException
	 */
	public void ajouterEnchere(Enchere enchere) throws BLLException {
		try {
			enchereDAO.insert(enchere);
		} catch (DALException e) {
			throw new BLLException("ajouterEnchere Error ", e);
		}
	}
	
	/**
	 * enchereByIndex
	 * @param dateEnchere
	 * @param noUtilisateur
	 * @param noArticleVendu
	 * @return une enchere en fonction de l'index (dateEnchere, noUtilisateur, noArticleVendu) 
	 * @throws BLLException
	 */
	public Enchere enchereByIndex(LocalDateTime dateEnchere, Integer noUtilisateur, Integer noArticleVendu) throws BLLException {
		try {
			return enchereDAO.selecteByIndex(dateEnchere, noUtilisateur, noArticleVendu);
		} catch (DALException e) {
			throw new BLLException("enchereByIndex Error ", e);
		}
	}
	
	/**
	 * enchereByArticle
	 * @param ArticleVendu
	 * @return la liste de toutes les enchères
	 * @throws BLLException
	 */
	public ArrayList<Enchere> enchereByArticle(ArticleVendu ArticleVendu) throws BLLException {
		try {
			return enchereDAO.selectEnchereByArticle(ArticleVendu);
		} catch (DALException e) {
			throw new BLLException("enchereByIndex Error ", e);
		}
	}
	
	/**
	 * enchereByUser
	 * @param user
	 * @return la liste des enchere effectué par un utilisateur
	 * @throws BLLException
	 */
	public ArrayList<Enchere> enchereByUser(Utilisateur user) throws BLLException {
		try {
			return enchereDAO.selectEnchereByUser(user);
		} catch (DALException e) {
			throw new BLLException("enchereByUser Error ", e);
		}
	}
	/**
	 * lastEnchereByArticle
	 * @param noArticleVendu
	 * @return la dernière enchere effectué sur un article 
	 * @throws BLLException
	 */
	public Enchere lastEnchereByArticle(int noArticleVendu) throws BLLException {
		try {
			return enchereDAO.selecteLast(noArticleVendu);
		} catch (DALException e) {
			throw new BLLException("lastEnchereByUserArticle Error ", e);
		}
	}
	
	/**
	 * supprimerEncheresByUser permet la suppression de toutes les encheres effectué par un utilisateur
	 * @param noUtilisateur
	 * @throws BLLException
	 */
	public void supprimerEncheresByUser(int noUtilisateur) throws BLLException {
		try {
			enchereDAO.delete(noUtilisateur);
		} catch (DALException e) {
			throw new BLLException("supprimerEncheresByUser Error ", e);
		}
	}
	
	/**
	 * supprimerEncheresById permet la suppression d'une enchere en fonction de son index
	 * @param article
	 * @param user
	 * @throws BLLException
	 */
	public void supprimerEncheresById(ArticleVendu article, Utilisateur user) throws BLLException {
		try {
			enchereDAO.deleteById(article,user);
		} catch (DALException e) {
			throw new BLLException("supprimerEncheresByUser Error ", e);
		}
	}
	
	/**
	 * supprimerEncheresByArticle permet de supprimer toutes les encheres effectué sur un article.
	 * @param article
	 * @throws BLLException
	 */
	public void supprimerEncheresByArticle(ArticleVendu article) throws BLLException {
		try {
			enchereDAO.deleteByArticle(article);
		} catch (DALException e) {
			throw new BLLException("supprimerEncheresByUser Error ", e);
		}
	}

}
