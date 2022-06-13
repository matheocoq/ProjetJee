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
	
	public static CategorieMger getInstance() {
		if(instance == null) {
			instance = new CategorieMger();
		}
		return instance;
	}
	
	private CategorieMger() {
		categorieDAO = DAOFactory.getDAOCategorie();
	}
	
	public void ajouterCategorie(Categorie categorie) throws BLLException {
		try {
			categorieDAO.insert(categorie);
		} catch (DALException e) {
			throw new BLLException("ajouterEnchere Error ", e);
		}
	}
	
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
	
	public void updateCategorie(Categorie categorie) throws BLLException{
		try {
			categorieDAO.update(categorie);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void removeCategorie(int no_categorie) throws BLLException{
		try {
			categorieDAO.delete(no_categorie);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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
