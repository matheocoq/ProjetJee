package fr.eni.ProjetJee.bll;

import java.time.LocalDateTime;

import fr.eni.ProjetJee.bo.Enchere;
import fr.eni.ProjetJee.dal.DALException;
import fr.eni.ProjetJee.dal.DAOFactory;
import fr.eni.ProjetJee.dal.EnchereDAO;

public class EnchereMger {
	
	EnchereDAO enchereDAO;
	
	private static EnchereMger instance;
	
	public static EnchereMger getInstance() {
		if(instance == null) {
			instance = new EnchereMger();
		}
		return instance;
	}
	
	private EnchereMger() {
		enchereDAO = DAOFactory.getDAOEnchere();
	}
	
	public void ajouterEnchere(Enchere enchere) throws BLLException {
		try {
			enchereDAO.insert(enchere);
		} catch (DALException e) {
			throw new BLLException("ajouterEnchere Error ", e);
		}
	}
	
	public Enchere enchereByIndex(LocalDateTime dateEnchere, Integer noUtilisateur, Integer noArticleVendu) throws BLLException {
		try {
			return enchereDAO.selecteByIndex(dateEnchere, noUtilisateur, noArticleVendu);
		} catch (DALException e) {
			throw new BLLException("enchereByIndex Error ", e);
		}
	}
	
	public Enchere lastEnchereByArticle(int noArticleVendu) throws BLLException {
		try {
			return enchereDAO.selecteLast(noArticleVendu);
		} catch (DALException e) {
			throw new BLLException("lastEnchereByUserArticle Error ", e);
		}
	}
	
	public void supprimerEncheresByUser(int noUtilisateur) throws BLLException {
		try {
			enchereDAO.delete(noUtilisateur);
		} catch (DALException e) {
			throw new BLLException("supprimerEncheresByUser Error ", e);
		}
	}

}
