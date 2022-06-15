package fr.eni.ProjetJee.bll;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ProjetJee.bo.Categorie;
import fr.eni.ProjetJee.bo.Enchere;
import fr.eni.ProjetJee.bo.Retrait;
import fr.eni.ProjetJee.dal.CategorieDAO;
import fr.eni.ProjetJee.dal.DALException;
import fr.eni.ProjetJee.dal.DAOFactory;
import fr.eni.ProjetJee.dal.EnchereDAO;

public class CategorieMger {
	CategorieDAO categorieDAO;
	
	private static CategorieMger instance;
	
	/**
	 * Singleton qui permet ainsi la création d'un seul objet (CategorieMger)
	 * @return une instance de CategorieMger
	 */
	public static CategorieMger getInstance() {
		if(instance == null) {
			instance = new CategorieMger();
		}
		return instance;
	}
	
	/**
	 * Récupere l'interface commune à tout les implémentations de CategorieDAO
	 */
	private CategorieMger() {
		categorieDAO = DAOFactory.getDAOCategorie();
	}
	
	/**
	 * ajouterCategorie permet l'ajout d'une nouvelle catégorie en dans la BDD
	 * @param categorie
	 * @throws BLLException
	 */
	public void ajouterCategorie(Categorie categorie) throws BLLException {
		try {
			categorieDAO.insert(categorie);
		} catch (DALException e) {
			throw new BLLException("ajouterEnchere Error ", e);
		}
	}
	
	/**
	 * getCategories
	 * @return la liste de toute les catégories
	 * @throws BLLException
	 */
	public List<Categorie> getCategories() throws BLLException{
		List<Categorie> categories = new ArrayList<>();
		try {
			categories = categorieDAO.selectAll();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categories;
	}
	
	/**
	 * updateCategorie permet de modifier les informations d'un catégorie stocker en BDD
	 * @param categorie
	 * @throws BLLException
	 */
	public void updateCategorie(Categorie categorie) throws BLLException{
		try {
			categorieDAO.update(categorie);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * removeCategorie permet la suppression d'une catégorie en fonction de son ID
	 * @param no_categorie
	 * @throws BLLException
	 */
	public void removeCategorie(int no_categorie) throws BLLException{
		try {
			categorieDAO.delete(no_categorie);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * getCategorieById
	 * @param noCategorie
	 * @return une catégorie en fonction de l'ID renseigné 
	 * @throws BLLException
	 */
	public Categorie getCategorieById(int noCategorie) throws BLLException{
		Categorie categorie = null;
		try {
			categorie =  categorieDAO.selectById(noCategorie);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categorie;
	}
}
