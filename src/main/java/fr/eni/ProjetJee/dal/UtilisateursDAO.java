package fr.eni.ProjetJee.dal;

import java.util.List;

import fr.eni.ProjetJee.bo.Utilisateur;

public interface UtilisateursDAO {
	
	void insert (Utilisateur utilisateur) throws DALException;
	void insertHistoriques (Utilisateur utilisateur) throws DALException;
	Utilisateur selectById(Integer noUtilisateur) throws DALException;
	Utilisateur selectByLogin(String login) throws DALException;
	List<Utilisateur> selectAll() throws DALException;
	void update(Utilisateur utilisateur) throws DALException;
	void delete(Integer noUtilisateur) throws DALException;
	boolean checkPseudoEmailTel(String speudo,String email,String tel) throws DALException;
	
}
