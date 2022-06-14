package fr.eni.ProjetJee.dal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ProjetJee.bo.ArticleVendu;
import fr.eni.ProjetJee.bo.Enchere;
import fr.eni.ProjetJee.bo.Utilisateur;

public interface EnchereDAO {

	void insert(Enchere enchere) throws DALException;
	Enchere selecteByIndex(LocalDateTime dateEnchere, Integer noUtilisateur, Integer noArticleVendu) throws DALException;
	Enchere selecteLast(Integer noArticleVendu) throws DALException;
	ArrayList<Enchere> selectEnchereByArticle(ArticleVendu article)throws DALException;
	ArrayList<Enchere> selectEnchereByUser(Utilisateur user)throws DALException;
	void delete(Integer noUtilisateur) throws DALException;
	void deleteById(ArticleVendu article,Utilisateur utilisateur ) throws DALException;
	void deleteByArticle(ArticleVendu article) throws DALException;
	
}
