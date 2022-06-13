package fr.eni.ProjetJee.bll;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import fr.eni.ProjetJee.bo.Utilisateur;
import fr.eni.ProjetJee.dal.DALException;
import fr.eni.ProjetJee.dal.DAOFactory;
import fr.eni.ProjetJee.dal.UtilisateursDAO;

public class UtilisateurMger {

	UtilisateursDAO utilisateurDAO;
	private static final String SALT = "mySecureKey";

	private static UtilisateurMger instance;

	public static UtilisateurMger getInstance() {
		if (instance == null) {
			instance = new UtilisateurMger();
		}
		return instance;
	}

	private UtilisateurMger() {
		utilisateurDAO = DAOFactory.getDAOUtilisateur();
	}

	public void ajouterUtilisateur(Utilisateur utilisateur) throws BLLException {
		try {
			utilisateurDAO.insert(utilisateur);
		} catch (DALException e) {
			throw new BLLException("ajouterUtilisateur Error ", e);
		}
	}
	
	public void ajouterUtilisateurHistorique(Utilisateur utilisateur) throws BLLException {
		try {
			utilisateurDAO.insertHistoriques(utilisateur);
		} catch (DALException e) {
			throw new BLLException("ajouterUtilisateur Error ", e);
		}
	}

	public Utilisateur utilisateurById(int noUtilisateur) throws BLLException {
		try {
			return utilisateurDAO.selectById(noUtilisateur);
		} catch (DALException e) {
			throw new BLLException("utilisateurById Error ", e);
		}
	}

	public List<Utilisateur> allUtilisateurs() throws BLLException {
		try {
			return utilisateurDAO.selectAll();
		} catch (DALException e) {
			throw new BLLException("allUtilisateurs Error ", e);
		}
	}

	public void majUtilisateur(Utilisateur user) throws BLLException {
		try {
			utilisateurDAO.update(user);
		} catch (DALException e) {
			throw new BLLException("majUtilisateur Error ", e);
		}
	}

	public void supprimerUtilisateur(int noUtilisateur) throws BLLException {
		try {
			utilisateurDAO.delete(noUtilisateur);
		} catch (DALException e) {
			throw new BLLException("supprimerUtilisateur Error ", e);
		}
	}

	public Utilisateur utilisateurByLogin(String login) throws BLLException {
		try {
			return utilisateurDAO.selectByLogin(login);
		} catch (DALException e) {
			throw new BLLException("utilisateurByLogin Error ", e);
		}
	}

	public Utilisateur verifConnexion(String login, String mdp) throws BLLException {
		
		Utilisateur user = this.utilisateurByLogin(login);
		if (user != null && this.compareHashPassword(mdp, user.getMotDePasse())) {
			return user;
		} else {
			throw new BLLException("email ou mdp incorrect !");
		}
	}

	public String generateHash(String passwordToHash) {
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(SALT.getBytes());
			byte[] bytes = md.digest(passwordToHash.getBytes());
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}
	
	private boolean compareHashPassword(String inputMdp, String bddMdp) {
		String generatedPassword = this.generateHash(inputMdp);	
		if(bddMdp.equals(generatedPassword)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkPseudoEmailTel(String pseudo,String email,String tel) throws DALException {
		
		return utilisateurDAO.checkPseudoEmailTel(pseudo, email, tel);
	}

}
