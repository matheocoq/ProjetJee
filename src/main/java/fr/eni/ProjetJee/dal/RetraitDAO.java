package fr.eni.ProjetJee.dal;

import java.util.List;

import fr.eni.ProjetJee.bo.Retrait;

public interface RetraitDAO {

	void insert (Retrait retrait) throws DALException;
	Retrait selectById(Integer noRetrait) throws DALException;
	Retrait selectByArticle(Integer idArticle) throws DALException;
	List<Retrait> selectAll() throws DALException;
	void update(Retrait retrait) throws DALException;
	void delete(Integer noRetrait) throws DALException;
}
