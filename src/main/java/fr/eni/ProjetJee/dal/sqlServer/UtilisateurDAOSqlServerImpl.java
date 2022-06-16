package fr.eni.ProjetJee.dal.sqlServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ProjetJee.bo.Utilisateur;
import fr.eni.ProjetJee.dal.ConnectionProvider;
import fr.eni.ProjetJee.dal.DALException;
import fr.eni.ProjetJee.dal.UtilisateursDAO;

public class UtilisateurDAOSqlServerImpl implements UtilisateursDAO {
	
	private static final String INSERT = "INSERT INTO Utilisateurs(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur, active) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String INSERTHISTAURIQUE = "INSERT INTO HISTORIQUE_UTILISATEUR(nom, prenom, email, telephone, rue, code_postal, ville, credit) values (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SELECT_BY_ID = "SELECT * FROM UTILISATEURS WHERE no_utilisateur = ?";
	private static final String SELECT_BY_LOGIN = "SELECT * FROM UTILISATEURS WHERE email = ? or pseudo = ?";
	private static final String SELECT_ALL = "SELECT * FROM UTILISATEURS";
	private static final String UPDATE = "UPDATE UTILISATEURS set pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ?, credit = ?, administrateur = ?, active = ? WHERE no_utilisateur = ?";
	private static final String DELETE = "DELETE FROM UTILISATEURS WHERE no_utilisateur = ?";
	private static final String CHECK_UNIQUE = "SELECT pseudo,email,telephone FROM UTILISATEURS WHERE pseudo = ? or email=?";

	/**
	 * Cette méthode permet d'inserer l'utilisateur passé en paramètre.
	 * @param  utilisateur est l'utilisateur à insérer.
	 */
	@Override
	public void insert(Utilisateur utilisateur) throws DALException {
		try (Connection conn = ConnectionProvider.getConnection();) {
			PreparedStatement stmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			
			// Faire les set(s)
			//Préparer la requete
			stmt.setString(1, utilisateur.getPseudo());
			stmt.setString(2, utilisateur.getNom());
			stmt.setString(3, utilisateur.getPrenom());
			stmt.setString(4, utilisateur.getEmail());
			stmt.setString(5, utilisateur.getTelephone());
			stmt.setString(6, utilisateur.getRue());
			stmt.setString(7, utilisateur.getCodePostal());
			stmt.setString(8, utilisateur.getVille());
			stmt.setString(9, utilisateur.getMotDePasse());
			stmt.setInt(10, utilisateur.getCredit());
			stmt.setBoolean(11, utilisateur.getAdministrateur());
			stmt.setBoolean(12, utilisateur.getActiver());
			
			
			//Executer la requete
			stmt.executeUpdate();
			
			//Recupérer l'identifiant créé
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				utilisateur.setNoUtilisateur(rs.getInt(1));
			}
			
		} catch (Exception e) {
			throw new DALException("Utilisateur insert Error ", e);
		}		
	}
	
	/**
	 * Cette méthode permet d'inserer l'historique de l'utilisateur passé en paramètre.
	 * @param  utilisateur est l'utilisateur dont on veut insérer l'historique.
	 */
	@Override
	public void insertHistoriques(Utilisateur utilisateur) throws DALException {
		try (Connection conn = ConnectionProvider.getConnection();) {
			PreparedStatement stmt = conn.prepareStatement(INSERTHISTAURIQUE);
			
			// Faire les set(s)
			//Préparer la requete
			stmt.setString(1, utilisateur.getNom());
			stmt.setString(2, utilisateur.getPrenom());
			stmt.setString(3, utilisateur.getEmail());
			stmt.setString(4, utilisateur.getTelephone());
			stmt.setString(5, utilisateur.getRue());
			stmt.setString(6, utilisateur.getCodePostal());
			stmt.setString(7, utilisateur.getVille());
			stmt.setInt(8, utilisateur.getCredit());
			
			//Executer la requete
			stmt.executeUpdate();
		
		} catch (Exception e) {
			throw new DALException("Utilisateur insert Error ", e);
		}		
		
	}
	
	/**
	 * Cette méthode permet de récuperer l'utilisateur dont le numero est passé en paramètre.
	 * @param  noUtilisateur est le numéro de l'utilisateur à récuperer.
	 */
	@Override
	public Utilisateur selectById(Integer noUtilisateur) throws DALException {
		try (Connection conn = ConnectionProvider.getConnection();) {
			PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID);
			
			//Préparer la requete
			stmt.setInt(1, noUtilisateur);
			
			//Executer la requete
			ResultSet rs = stmt.executeQuery();
			
			rs.next();
			
			Utilisateur user = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getBoolean("administrateur"));
			return user;
			
		} catch (Exception e) {
			throw new DALException("Utilisateur selectById Error ", e);
		}	
	}

	/**
	 * Cette méthode permet de recuperer tous les utilisateurs dans la base de données.
	 */
	@Override
	public List<Utilisateur> selectAll() throws DALException {
		try (Connection conn = ConnectionProvider.getConnection();) {
			PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
			
			List<Utilisateur> utilisateursList = new ArrayList<Utilisateur>();
			
			//Executer la requete
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				utilisateursList.add(new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getBoolean("administrateur")));
			}
			
			return utilisateursList;
			
		} catch (Exception e) {
			throw new DALException("Utilisateur selectAll Error ", e);
		}
	}

	/**
	 * Cette méthode permet de mettre à jour l'utilisateur passé en paramètre.
	 * @param  utilisateur est l'utilisateur à mettre à jour.
	 */
	@Override
	public void update(Utilisateur utilisateur) throws DALException {
		try (Connection conn = ConnectionProvider.getConnection();) {
			
			//Préparer la requete
			PreparedStatement stmt = conn.prepareStatement(UPDATE);
			stmt.setString(1, utilisateur.getPseudo());
			stmt.setString(2, utilisateur.getNom());
			stmt.setString(3, utilisateur.getPrenom());
			stmt.setString(4, utilisateur.getEmail());
			stmt.setString(5, utilisateur.getTelephone());
			stmt.setString(6, utilisateur.getRue());
			stmt.setString(7, utilisateur.getCodePostal());
			stmt.setString(8, utilisateur.getVille());
			stmt.setString(9, utilisateur.getMotDePasse());
			stmt.setInt(10, utilisateur.getCredit());
			stmt.setBoolean(11, utilisateur.getAdministrateur());
			stmt.setBoolean(12, utilisateur.getActiver());
			stmt.setInt(13, utilisateur.getNoUtilisateur());
			
			//Executer la requete
			stmt.executeUpdate();
			//System.out.println("MAJ Ok");
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			throw new DALException("Utilisateur update Error", e);
		}
	}

	/**
	 * Cette méthode permet de supprimer l'utilisateur dont le numero est passé en paramètre.
	 * @param  noUtilisateur est le numero l'utilisateur à supprimer.
	 */
	@Override
	public void delete(Integer noUtilisateur) throws DALException {
		// Faire une transaction pour supprimer de la table Utilisateur et ajouter à la table archive Utilisateurs
		
		try (Connection conn = ConnectionProvider.getConnection();) {
			
			//Préparer la requete
			PreparedStatement stmt = conn.prepareStatement(DELETE);
			stmt.setInt(1, noUtilisateur);
			
			//Executer la requete
			stmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new DALException("Utilisateur delete Error", e);
		}
	}

	/**
	 * Cette méthode permet de récuperer l'utilisateur dont le pseudo ou l'émail est passé en paramètre.
	 * @param  login soit le speudo ou l'émail de l'utilisateur à récuperer.
	 */
	@Override
	public Utilisateur selectByLogin(String login) throws DALException {
		try (Connection conn = ConnectionProvider.getConnection();) {
			PreparedStatement stmt = conn.prepareStatement(SELECT_BY_LOGIN);
			
			//Préparer la requete
			stmt.setString(1, login);
			stmt.setString(2, login);
			
			//Executer la requete
			ResultSet rs = stmt.executeQuery();
			
			rs.next();
			
			Utilisateur user = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getBoolean("administrateur"));
			return user;
			
		} catch (Exception e) {
			throw new DALException("Utilisateur selectByEmail Error ", e);
		}
	}

	/**
	 * Cette méthode permet de verifier l'unicité du speudo et l'émail dans la base de données.
	 * @param  speudo est le speudo à vérifier.
	 * @param  email est l'émail à vérifier.
	 */
	@Override
	public boolean checkPseudoEmail(String speudo, String email) throws DALException {
		boolean isDuplicated = false; 
		try (Connection conn = ConnectionProvider.getConnection();) {
			PreparedStatement stmt = conn.prepareStatement(CHECK_UNIQUE);
			
		
			//Préparer la requete
			stmt.setString(1, speudo);
			stmt.setString(2, email);
			
			//Executer la requete
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				isDuplicated = true;
			}
			
		} catch (Exception e) {
			throw new DALException("check unique Error ", e);
		}		
		return isDuplicated;
	}

	/**
	 * Cette méthode permet de verifier l'unicité du speudo et l'émail dans la 
	 * base de données lors d'une modification du profil d'un utilisateur.
	 * @param  speudo est le speudo à vérifier.
	 * @param  email est l'émail à vérifier.
	 */
	@Override
	public boolean checkPseudoEmailModif(String speudo, String email) throws DALException {
		
		int nbLignes =0;
		try (Connection conn = ConnectionProvider.getConnection();) {
			PreparedStatement stmt = conn.prepareStatement(CHECK_UNIQUE);
			
		
			//Préparer la requete
			stmt.setString(1, speudo);
			stmt.setString(2, email);

			
			//Executer la requete
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				nbLignes++;
			}
			
		} catch (Exception e) {
			throw new DALException("check unique Error ", e);
		}		
		return nbLignes==1;
	}

}
