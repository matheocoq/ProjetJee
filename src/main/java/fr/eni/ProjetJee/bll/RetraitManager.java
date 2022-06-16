package fr.eni.ProjetJee.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ProjetJee.bo.Retrait;
import fr.eni.ProjetJee.dal.DALException;
import fr.eni.ProjetJee.dal.DAOFactory;
import fr.eni.ProjetJee.dal.RetraitDAO;

public class RetraitManager {
	
	private RetraitDAO daoRetrait;
	
	
	private static RetraitManager instance;
	
	/**
	 * Singleton qui permet ainsi la création d'un seul objet (RetraitManager)
	 * @return une instance de RetraitManager
	 */
	public static RetraitManager getInstance() {
		if(instance == null) {
			instance = new RetraitManager();
		}
		return instance;
	}
	
	/**
	 * Récupere l'interface commune à tout les implémentations de RetraitDAO
	 */
	private RetraitManager() {
		daoRetrait = DAOFactory.getDAORetrait();
	}
	
	/**
	 * getRetraits
	 * @return la liste de tout les retraits
	 * @throws BLLException
	 */
	public List<Retrait> getRetraits() throws BLLException{
		List<Retrait> retraits = new ArrayList<>();
		try {
			retraits = daoRetrait.selectAll();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retraits;
	}
	
	/**
	 * addRetraits permet d'ajouter un retrait dans la BDD
	 * @param retrait
	 * @throws BLLException
	 */
	public void addRetraits(Retrait retrait) throws BLLException{
		try {
			daoRetrait.insert(retrait);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * updateRetrait permet de mettre à jour les information d'un retrait en fonction de son ID
	 * @param retrait
	 * @throws BLLException
	 */
	public void updateRetrait(Retrait retrait) throws BLLException{
		try {
			daoRetrait.update(retrait);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * removeRetrait permet de supprimer un retrait en fonction de son ID
	 * @param no_retrait
	 * @throws BLLException
	 */
	public void removeRetrait(int no_retrait) throws BLLException{
		try {
			daoRetrait.delete(no_retrait);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * getArticleById
	 * @param noRetrait
	 * @return l'article associé a un retrait en fonction de l'ID du retrait.
	 * @throws BLLException
	 */
	public Retrait getArticleById(int noRetrait) throws BLLException{
		Retrait retrait = null;
		try {
			retrait =  daoRetrait.selectById(noRetrait);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retrait;
	}

}
