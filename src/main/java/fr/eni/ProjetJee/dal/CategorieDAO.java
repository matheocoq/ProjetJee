package fr.eni.ProjetJee.dal;

import java.util.List;

import fr.eni.ProjetJee.bo.Categorie;


public interface CategorieDAO {

	void insert (Categorie cat) throws DALException;
	Categorie selectById(Integer no_categorie) throws DALException;
	List<Categorie> selectAll() throws DALException;
	void update(Categorie cat) throws DALException;
	void delete(Integer no_categorie) throws DALException;
}
