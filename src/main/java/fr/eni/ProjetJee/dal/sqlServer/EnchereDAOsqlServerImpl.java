package fr.eni.ProjetJee.dal.sqlServer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.ZoneId;

import fr.eni.ProjetJee.bo.Enchere;
import fr.eni.ProjetJee.dal.ArticleVenduDAO;
import fr.eni.ProjetJee.dal.ConnectionProvider;
import fr.eni.ProjetJee.dal.DALException;
import fr.eni.ProjetJee.dal.DAOFactory;
import fr.eni.ProjetJee.dal.EnchereDAO;
import fr.eni.ProjetJee.dal.UtilisateursDAO;

public class EnchereDAOsqlServerImpl implements EnchereDAO {
	
	private static final String INSERT = "INSERT INTO ENCHERES(date_enchere, montant_enchere, no_article, no_utilisateur) values(?, ?, ?, ?)";
	private static final String SELECT_BY_INDEX = "SELECT * FROM ENCHERES WHERE date_enchere = ?, no_article = ?, no_utilisateur = ?";
	private static final String SELECT_LAST = "SELECT TOP 1 * FROM ENCHERES WHERE no_article = ? ORDER BY date_enchere";
	private static final String DELETE_BY_USER = "DELETE FROM ENCHERES WHERE no_utilisateur = ?";
	
	private static UtilisateursDAO utilisateurDAO = DAOFactory.getDAOUtilisateur();
	private static ArticleVenduDAO articleVenduDAO = DAOFactory.getDAOArticleVendu();

	@Override
	public void insert(Enchere enchere) throws DALException {
		try (Connection conn = ConnectionProvider.getConnection();) {
			PreparedStatement stmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			
			// Faire les set(s)
			//Préparer la requete
			Date date1 = (Date) Date.from(enchere.getDateEnchere().atZone(ZoneId.systemDefault()).toInstant());
			stmt.setDate(1, date1);
			
			//Executer la requete
			stmt.executeUpdate();
			
		} catch (Exception e) {
			throw new DALException("Enchere insert Error ", e);
		}	
	}

	@Override
	public Enchere selecteByIndex(LocalDateTime dateEnchere, Integer noUtilisateur, Integer noArticleVendu)
			throws DALException {
		
		try (Connection conn = ConnectionProvider.getConnection();) {
			PreparedStatement stmt = conn.prepareStatement(SELECT_BY_INDEX);
			
			//Préparer la requete
			Date date1 = (Date) Date.from(dateEnchere.atZone(ZoneId.systemDefault()).toInstant());
			stmt.setDate(1, date1);
			stmt.setInt(2, noArticleVendu);
			stmt.setInt(3, noUtilisateur);
			
			//Executer la requete
			ResultSet rs = stmt.executeQuery();
			
			rs.next();
			
			// Ajouter articleVenduDAO pour get l'article
			Enchere enchere = new Enchere(rs.getTimestamp("date_enchere").toLocalDateTime(), rs.getInt("montant_enchere"), utilisateurDAO.selectById(rs.getInt("no_utilisateur")), articleVenduDAO.select(rs.getInt("no_article")));
			return enchere;
			
		} catch (Exception e) {
			throw new DALException("Enchere selecteByIndex Error ", e);
		}
	}

	@Override
	public void delete(Integer noUtilisateur) throws DALException {
		try (Connection conn = ConnectionProvider.getConnection();) {
			
			//Préparer la requete
			PreparedStatement stmt = conn.prepareStatement(DELETE_BY_USER);
			stmt.setInt(1, noUtilisateur);
			
			//Executer la requete
			stmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new DALException("Enchere delete Error", e);
		}
	}

	@Override
	public Enchere selecteLast(Integer noArticleVendu) throws DALException {
		try (Connection conn = ConnectionProvider.getConnection();) {
			PreparedStatement stmt = conn.prepareStatement(SELECT_LAST);
			
			//Préparer la requete
			stmt.setInt(1, noArticleVendu);
			
			//Executer la requete
			ResultSet rs = stmt.executeQuery();
			
			Enchere enchere = null;
			
			if (rs.next()) {
				enchere = new Enchere(rs.getTimestamp("date_enchere").toLocalDateTime(), rs.getInt("montant_enchere"), utilisateurDAO.selectById(rs.getInt("no_utilisateur")), articleVenduDAO.select(rs.getInt("no_article")));
			}
			// Ajouter articleVenduDAO pour get l'article
			
			return enchere;
			
		} catch (Exception e) {
			throw new DALException("Enchere selecteLast Error ", e);
		}
	}

}
