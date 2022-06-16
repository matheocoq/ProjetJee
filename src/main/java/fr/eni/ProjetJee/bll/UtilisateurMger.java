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

	/**
	 * Singleton qui permet ainsi la création d'un seul objet (UtilisateurMger)
	 * @return une instance de UtilisateurMger
	 */
	public static UtilisateurMger getInstance() {
		if (instance == null) {
			instance = new UtilisateurMger();
		}
		return instance;
	}

	/**
	 * Récupere l'interface commune à tout les implémentations de UtilisateurDAO
	 */
	private UtilisateurMger() {
		utilisateurDAO = DAOFactory.getDAOUtilisateur();
	}

	/**
	 * ajouterUtilisateur permet l'ajout l'ajout d'un utilisateur en BDD
	 * @param utilisateur
	 * @throws BLLException
	 */
	public void ajouterUtilisateur(Utilisateur utilisateur) throws BLLException {
		try {
			utilisateurDAO.insert(utilisateur);
		} catch (DALException e) {
			throw new BLLException("ajouterUtilisateur Error ", e);
		}
	}
	
	/**
	 * ajouterUtilisateurHistorique permet l'ajout l'ajout d'un utilisateur dans la table historique des utilisateurs en BDD
	 * @param utilisateur
	 * @throws BLLException
	 */
	public void ajouterUtilisateurHistorique(Utilisateur utilisateur) throws BLLException {
		try {
			utilisateurDAO.insertHistoriques(utilisateur);
		} catch (DALException e) {
			throw new BLLException("ajouterUtilisateur Error ", e);
		}
	}

	/**
	 * utilisateurById 
	 * @param noUtilisateur
	 * @return un utilisateur en fonction de son ID
	 * @throws BLLException
	 */
	public Utilisateur utilisateurById(int noUtilisateur) throws BLLException {
		try {
			return utilisateurDAO.selectById(noUtilisateur);
		} catch (DALException e) {
			throw new BLLException("utilisateurById Error ", e);
		}
	}

	/**
	 * 
	 * @return une liste d'utilisateur
	 * @throws BLLException
	 */
	public List<Utilisateur> allUtilisateurs() throws BLLException {
		try {
			return utilisateurDAO.selectAll();
		} catch (DALException e) {
			throw new BLLException("allUtilisateurs Error ", e);
		}
	}

	/**
	 * majUtilisateur permet de modifier les infomartions d'un utilisateur stocker dans la BDD 
	 * @param user
	 * @throws BLLException
	 */
	public void majUtilisateur(Utilisateur user) throws BLLException {
		try {
			utilisateurDAO.update(user);
		} catch (DALException e) {
			throw new BLLException("majUtilisateur Error ", e);
		}
	}

	/**
	 * supprimerUtilisateur permet la suppression d'un utilisateur en fonction de son ID
	 * @param noUtilisateur
	 * @throws BLLException
	 */
	public void supprimerUtilisateur(int noUtilisateur) throws BLLException {
		try {
			utilisateurDAO.delete(noUtilisateur);
		} catch (DALException e) {
			throw new BLLException("supprimerUtilisateur Error ", e);
		}
	}

	/**
	 * utilisateurByLogin
	 * @param login
	 * @return un utilisateur en fonction de son login
	 * @throws BLLException
	 */
	public Utilisateur utilisateurByLogin(String login) throws BLLException {
		try {
			return utilisateurDAO.selectByLogin(login);
		} catch (DALException e) {
			throw new BLLException("utilisateurByLogin Error ", e);
		}
	}

	/**
	 * verifConnexion permet de verifier les informations (login et mot de passe) saisie par l'utilisateur pour se connecter
	 * @param login
	 * @param mdp
	 * @return l'instance de l'utilisateur si les information son correct sinon renvoie une erreur BLLException
	 * @throws BLLException
	 */
	public Utilisateur verifConnexion(String login, String mdp) throws BLLException {
		
		Utilisateur user = this.utilisateurByLogin(login);
		if (user != null && this.compareHashPassword(mdp, user.getMotDePasse())) {
			return user;
		} else {
			throw new BLLException("email ou mdp incorrect !");
		}
	}

	/**
	 * generateHash
	 * @param passwordToHash
	 * @return le mot de passe saisie par l'utilisateur, hasher (SHA-256)
	 */
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
	
	/**
	 * compareHashPassword permet de comparer le mot de passe saisie par l'utilisateur et le mot de passe hasher stocker dans la BDD
	 * @param inputMdp
	 * @param bddMdp
	 * @return true or false
	 */
	public boolean compareHashPassword(String inputMdp, String bddMdp) {
		String generatedPassword = this.generateHash(inputMdp);	
		if(bddMdp.equals(generatedPassword)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkPseudoEmail(String pseudo,String email) throws DALException {
		
		return utilisateurDAO.checkPseudoEmail(pseudo, email);
	}

	public boolean checkPseudoEmailModif(String pseudo,String email) throws DALException {
		
		return utilisateurDAO.checkPseudoEmailModif(pseudo, email);
	}
}
