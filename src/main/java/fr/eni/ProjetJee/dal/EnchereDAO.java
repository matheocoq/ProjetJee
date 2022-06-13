package fr.eni.ProjetJee.dal;

import java.time.LocalDateTime;
import java.util.List;

import fr.eni.ProjetJee.bo.Enchere;

public interface EnchereDAO {

	void insert(Enchere enchere) throws DALException;
	Enchere selecteByIndex(LocalDateTime dateEnchere, Integer noUtilisateur, Integer noArticleVendu) throws DALException;
	Enchere selecteLast(Integer noArticleVendu) throws DALException;
	void delete(Integer noUtilisateur) throws DALException;
	
}
