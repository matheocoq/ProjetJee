package fr.eni.ProjetJee.dal;

import java.time.LocalDateTime;
import java.util.List;

import fr.eni.ProjetJee.bo.ArticleVendu;
import fr.eni.ProjetJee.bo.Enchere;
import fr.eni.ProjetJee.bo.Utilisateur;

public interface EnchereDAO {

	void insert(Enchere enchere) throws DALException;
	Enchere selecteByIndex(LocalDateTime dateEnchere, Integer noUtilisateur, Integer noArticleVendu) throws DALException;
	Enchere selecteLast(Integer noArticleVendu) throws DALException;
	void delete(Integer noUtilisateur) throws DALException;
	void deleteById(ArticleVendu article,Utilisateur utilisateur ) throws DALException;
	
}
