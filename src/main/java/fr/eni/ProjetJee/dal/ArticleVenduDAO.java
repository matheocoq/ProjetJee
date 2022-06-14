package fr.eni.ProjetJee.dal;

import java.util.ArrayList;

import fr.eni.ProjetJee.bo.ArticleVendu;
import fr.eni.ProjetJee.bo.Categorie;
import fr.eni.ProjetJee.bo.Utilisateur;

public interface ArticleVenduDAO {
	void insert(ArticleVendu article) throws DALException; 
	ArticleVendu select(int id) throws DALException;
	ArrayList<ArticleVendu> select() throws DALException;
	ArrayList<ArticleVendu> selectByUtilisateur(Utilisateur utilisateur) throws DALException;
	ArrayList<ArticleVendu> selectByCategorie(int categorie) throws DALException;
	ArrayList<ArticleVendu> selectByCategorieName(int categorie , String name) throws DALException;
	ArrayList<ArticleVendu> selectByRecherche(int categorie , String name , Utilisateur utilisateur ,String checkbox , String ouvertes , String mesEnchere,String mesEnchereReporter,String mesVenteCours,String mesVenteDebutees,String mesVentetTerminees) throws DALException;
	ArrayList<ArticleVendu> selectByName(String name) throws DALException;
	ArrayList<ArticleVendu> selectEnCourById(Utilisateur utilisateur) throws DALException;
	ArrayList<ArticleVendu> selectUserEnchere(Utilisateur utilisateur) throws DALException;
	void delete(int id) throws DALException;
	void deleteCreeByUtilisateur(Utilisateur utilisateur) throws DALException; 
	void update(ArticleVendu article) throws DALException; 
}
