package fr.eni.ProjetJee.bll;

import java.util.ArrayList;

import fr.eni.ProjetJee.bo.ArticleVendu;
import fr.eni.ProjetJee.bo.Categorie;
import fr.eni.ProjetJee.bo.Utilisateur;
import fr.eni.ProjetJee.dal.ArticleVenduDAO;
import fr.eni.ProjetJee.dal.DALException;
import fr.eni.ProjetJee.dal.DAOFactory;

public class ArticleVenduMger {
	
	ArticleVenduDAO articleVenduDAO;
	
	private static ArticleVenduMger instance;
	
	/**
	 * Singleton qui permet ainsi la création d'un seul objet (ArticleVenduMger)
	 * @return une instance de ArticleVenduMger
	 */
	public static ArticleVenduMger getInstance() {
		if (instance == null) {
			instance = new ArticleVenduMger();
		}
		return instance;
	}
	
	/**
	 * Récupere l'interface commune à tout les implémentations de ArtilceVenduDAO
	 */
	private ArticleVenduMger () {
		articleVenduDAO = DAOFactory.getDAOArticleVendu();
	}
	
	/**
	 * ajouterArticleVendu permet l'ajout d'un article dans la BDD
	 * @param articleVendu
	 * @throws BLLException
	 */
	public void ajouterArticleVendu(ArticleVendu articleVendu) throws BLLException {
		try {
			articleVenduDAO.insert(articleVendu);
		} catch (DALException e) {
			throw new BLLException("ajouterArticleVendu Error ", e);
		}
	}
	
	/**
	 * articleVenduById
	 * @param noArticle
	 * @return un article en fonction de se ID
	 * @throws BLLException
	 */
	public ArticleVendu articleVenduById(int noArticle) throws BLLException {
		try {
			// DAL -> mettre un selectById pas juste select
			return articleVenduDAO.select(noArticle);
		} catch (DALException e) {
			throw new BLLException("articleVenduById Error ", e);
		}
	}
	
	/**
	 * allArticleVendu
	 * @return une liste d'article 
	 * @throws BLLException
	 */
	public ArrayList<ArticleVendu> allArticleVendu() throws BLLException {
		try {
			// DAL -> mettre un selectAll pas juste select
			return articleVenduDAO.select();
		} catch (DALException e) {
			throw new BLLException("allArticleVendu Error ", e);
		}
	}
	
	/**
	 * articleVenduByUtilisateur
	 * @param utilisateur
	 * @return la liste des articles créée par un utilisateur
	 * @throws BLLException
	 */
	public ArrayList<ArticleVendu> articleVenduByUtilisateur(Utilisateur utilisateur) throws BLLException {
		try {
			// (DALE -> mettre id du user en parametre pas l'utilisateur en entier)
			return articleVenduDAO.selectByUtilisateur(utilisateur);
		} catch (DALException e) {
			throw new BLLException("articleVenduByUtilisateur Error ", e);
		}
	}
	
	/**
	 * articleVenduByCategorie
	 * @param categorie
	 * @return la liste des articles en fonction de  l' catégorie
	 * @throws BLLException
	 */
	public ArrayList<ArticleVendu> articleVenduByCategorie(int categorie) throws BLLException {
		try {
			// (DALE -> mettre id de la categorie en parametre pas la categorie en entier)
			return articleVenduDAO.selectByCategorie(categorie);
		} catch (DALException e) {
			throw new BLLException("articleVenduByCategorie Error ", e);
		}
	}
	
	/**
	 * articleVenduByCategorieName
	 * @param categorie
	 * @param name
	 * @return la liste des articles en cours en fonction de la catégorie et d'un mot clé
	 * @throws BLLException
	 */
	public ArrayList<ArticleVendu> articleVenduByCategorieName(int categorie , String name) throws BLLException {
		try {
			// (DALE -> mettre id de la categorie en parametre pas la categorie en entier)
			return articleVenduDAO.selectByCategorieName(categorie,name);
		} catch (DALException e) {
			throw new BLLException("articleVenduByCategorie Error ", e);
		}
	}
	
	/**
	 * articleVenduEnCoursById
	 * @param user
	 * @return ???
	 * @throws BLLException
	 */
	public ArrayList<ArticleVendu> articleVenduEnCoursById(Utilisateur user) throws BLLException {
		try {
			// (DALE -> mettre id de la categorie en parametre pas la categorie en entier)
			return articleVenduDAO.selectEnCourById(user);
		} catch (DALException e) {
			throw new BLLException("articleVenduEnCoursById Error ", e);
		}
	}
	
	/**
	 * articleVenduUserEnchere
	 * @param user
	 * @return les articles ou l'utilisateur a fait une enchère
	 * @throws BLLException
	 */
	public ArrayList<ArticleVendu> articleVenduUserEnchere(Utilisateur user) throws BLLException {
		try {
			// (DALE -> mettre id de la categorie en parametre pas la categorie en entier)
			return articleVenduDAO.selectUserEnchere(user);
		} catch (DALException e) {
			throw new BLLException("articleVenduEnCoursById Error ", e);
		}
	}
	
	/**
	 * articleVenduByName
	 * @param name
	 * @return article par mot clé
	 * @throws BLLException
	 */
	public ArrayList<ArticleVendu> articleVenduByName(String name) throws BLLException {
		try {
			// (DALE -> mettre id de la categorie en parametre pas la categorie en entier)
			return articleVenduDAO.selectByName(name);
		} catch (DALException e) {
			throw new BLLException("articleVenduByCategorie Error ", e);
		}
	}
	
	/**
	 * supprimerArticleVendu permet la suppression d'un article en focntion de son ID
	 * @param idArticleVendu
	 * @throws BLLException
	 */
	public void supprimerArticleVendu(int idArticleVendu) throws BLLException {
		try {
			articleVenduDAO.delete(idArticleVendu);
		} catch (DALException e) {
			throw new BLLException("supprimerArticleVendu Error ", e);
		}
	}
	
	/**
	 * supprimerArticleVenduCree permet la suppression de tout les articles d'un utilisateur
	 * @param user
	 * @throws BLLException
	 */
	public void supprimerArticleVenduCree(Utilisateur user) throws BLLException {
		try {
			articleVenduDAO.deleteCreeByUtilisateur(user);
		} catch (DALException e) {
			throw new BLLException("supprimerArticleVendu Error ", e);
		}
	}
	
	/**
	 * majArticleVendu permet de modifier les informations d'un article stocker dans la BDD
	 * @param articleVendu
	 * @throws BLLException
	 */
	public void majArticleVendu(ArticleVendu articleVendu) throws BLLException {
		try {
			articleVenduDAO.update(articleVendu);
		} catch (DALException e) {
			throw new BLLException("majArticleVendu Error ", e);
		}
	}
	
	/**
	 * filtreNonCo
	 * @param categorie
	 * @param nom
	 * @return filtre de la page non connecté
	 * @throws BLLException
	 */
	public ArrayList<ArticleVendu> filtreNonCo(String categorie,String nom) throws BLLException{
		System.out.println(categorie);
		System.out.println(categorie=="Toute");
		if(categorie.equals("Toute")) {
			if(nom.equals("")) {
				return allArticleVendu();
			}
			else {
				return articleVenduByName("%"+nom+"%");
			}
		}
		else {
			if(nom.equals("")) {
				return articleVenduByCategorie(Integer.parseInt(categorie));
			}
			else {
				return articleVenduByCategorieName(Integer.parseInt(categorie), "%"+nom+"%");
			}
		}
		
	}
	/**
	 * filtreCo
	 * @param categorie
	 * @param name
	 * @param utilisateur
	 * @param checkbox
	 * @param ouvertes
	 * @param mesEnchere
	 * @param mesEnchereReporter
	 * @param mesVenteCours
	 * @param mesVenteDebutees
	 * @param mesVentetTerminees
	 * @return filtre de la page connecté
	 * @throws NumberFormatException
	 * @throws DALException
	 */
	public ArrayList<ArticleVendu> filtreCo(String categorie , String name , Utilisateur utilisateur ,String checkbox , String ouvertes , String mesEnchere,String mesEnchereReporter,String mesVenteCours,String mesVenteDebutees,String mesVentetTerminees) throws NumberFormatException, DALException{
			
				
			return articleVenduDAO.selectByRecherche(Integer.parseInt(categorie),name ,utilisateur ,checkbox ,ouvertes ,mesEnchere,mesEnchereReporter,mesVenteCours,mesVenteDebutees,mesVentetTerminees);
			
		
	}

}
