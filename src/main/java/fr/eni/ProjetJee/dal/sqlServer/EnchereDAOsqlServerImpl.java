package fr.eni.ProjetJee.dal.sqlServer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import fr.eni.ProjetJee.bo.ArticleVendu;
import fr.eni.ProjetJee.bo.Categorie;
import fr.eni.ProjetJee.bo.Enchere;
import fr.eni.ProjetJee.bo.Retrait;
import fr.eni.ProjetJee.bo.Utilisateur;
import fr.eni.ProjetJee.dal.ArticleVenduDAO;
import fr.eni.ProjetJee.dal.CategorieDAO;
import fr.eni.ProjetJee.dal.ConnectionProvider;
import fr.eni.ProjetJee.dal.DALException;
import fr.eni.ProjetJee.dal.DAOFactory;
import fr.eni.ProjetJee.dal.EnchereDAO;
import fr.eni.ProjetJee.dal.RetraitDAO;
import fr.eni.ProjetJee.dal.UtilisateursDAO;

public class EnchereDAOsqlServerImpl implements EnchereDAO {
	
	private static final String INSERT = "INSERT INTO ENCHERES(date_enchere, montant_enchere, no_article, no_utilisateur) values(?, ?, ?, ?)";
	private static final String SELECT_BY_INDEX = "SELECT * FROM ENCHERES WHERE date_enchere = ?, no_article = ?, no_utilisateur = ?";
	private static final String SELECT_BY_ARTICLE = "SELECT * FROM ENCHERES WHERE  no_article = ?";
	private static final String SELECT_BY_USER = "SELECT * FROM ENCHERES WHERE no_utilisateur = ?";
	private static final String SELECT_LAST = "SELECT TOP 1 * FROM ENCHERES WHERE no_article = ? ORDER BY date_enchere DESC";
	private static final String DELETE_BY_USER = "DELETE FROM ENCHERES WHERE no_utilisateur = ?";
	private static final String DELETE_BY_ID = "DELETE FROM ENCHERES WHERE no_article = ? AND no_utilisateur= ?";
	private static final String DELETE_BY_ARTTICLE = "DELETE FROM ENCHERES WHERE no_article = ? ";
	
	private static UtilisateursDAO utilisateurDAO = DAOFactory.getDAOUtilisateur();
	private static ArticleVenduDAO articleVenduDAO = DAOFactory.getDAOArticleVendu();

	/**
	 * Cette méthode permet d'inserer une enchère passé en paramètre.
	 * @param  enchere est l'enchère à insérer.
	 */
	@Override
	public void insert(Enchere enchere) throws DALException {
		try (Connection conn = ConnectionProvider.getConnection();) {
			PreparedStatement stmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			
			// Faire les set(s)
			//Préparer la requete
			Timestamp date1 = Timestamp.valueOf(enchere.getDateEnchere());
			stmt.setTimestamp(1, date1);
			stmt.setInt(2, enchere.getMontantEnchere());
			stmt.setInt(3, enchere.getNoArticle().getNoArticle());
			stmt.setInt(4, enchere.getNoUtilisateur().getNoUtilisateur());
			//Executer la requete
			stmt.executeUpdate();
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			throw new DALException("Enchere insert Error ", e);
		}	
	}

	/**
	 * Cette méthode permet de selectionner une enchère dont la date de l'enchère,le numéro de l'utilisateur et le numéro de l'article
	 * correspondent à ceux passé en paramètre.
	 * @param dateEnchere est la date de l'enchère.
	 * @param noUtilisateur est le numéro de l'utilisateur.
	 * @param noArticleVendu est le numéro de l'article.
	 */
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

	/**
	 * Cette méthode permet de supprimer les enchères dont le numéro d'utilisateur correspont à celui passé en paramètre.
	 * @param  noUtilisateur est le numéro de l'utilisateur.
	 */
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

	/**
	 * Cette méthode permet de récuperer la dernièr enchère de l'utilisateur dont le numéro de l'article est passé en paramètre.
	 * @param  noArticleVendu est le numéro de l'article.
	 */
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

	/**
	 * Cette méthode permet de supprimer l'enchère dont l'article et l'utilisateur sont passé en paramètre.
	 * @param  article est l'article.
	 * @param  utilisateur est l'utilisateur.
	 */
	@Override
	public void deleteById(ArticleVendu article, Utilisateur utilisateur) throws DALException {
		try (Connection conn = ConnectionProvider.getConnection();) {
			
			//Préparer la requete
			PreparedStatement stmt = conn.prepareStatement(DELETE_BY_ID);
			stmt.setInt(1, article.getNoArticle());
			stmt.setInt(2, utilisateur.getNoUtilisateur());
			
			//Executer la requete
			stmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new DALException("Enchere delete Error", e);
		}
		
	}

	/**
	 * Cette méthode permet de récuperer l'enchère dont l'article utilisé lors de l'enchère est passé en paramètre.
	 * @param  article est l'article.
	 */
	@Override
	public ArrayList<Enchere> selectEnchereByArticle(ArticleVendu article) throws DALException {
		try (Connection conn = ConnectionProvider.getConnection();) {
			PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ARTICLE);
			ArrayList<Enchere> encheres= new ArrayList<Enchere>();
			//Préparer la requete
			stmt.setInt(1, article.getNoArticle());
		
			
			//Executer la requete
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Enchere enchere = new Enchere(rs.getTimestamp("date_enchere").toLocalDateTime(), rs.getInt("montant_enchere"), utilisateurDAO.selectById(rs.getInt("no_utilisateur")), articleVenduDAO.select(rs.getInt("no_article")));
				encheres.add(enchere);
			}
			// Ajouter articleVenduDAO pour get l'article
		
			return encheres;
			
		} catch (Exception e) {
			throw new DALException("Enchere selecteByIndex Error ", e);
		}
	}

	/**
	 * Cette méthode permet de supprimer l'enchère dont l'article utilisé lors de l'enchère est passé en paramètre.
	 * @param  article est l'article.
	 */
	@Override
	public void deleteByArticle(ArticleVendu article) throws DALException {
		try (Connection conn = ConnectionProvider.getConnection();) {
			
			//Préparer la requete
			PreparedStatement stmt = conn.prepareStatement(DELETE_BY_ARTTICLE);
			stmt.setInt(1, article.getNoArticle());

			
			//Executer la requete
			stmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new DALException("Enchere delete Error", e);
		}
	}

	/**
	 * Cette méthode permet de récuperer l'enchère dont l'utilisateur qui a fait l'enchère est passé en paramètre.
	 * @param  user est l'utilisateur.
	 */
	@Override
	public ArrayList<Enchere> selectEnchereByUser(Utilisateur user) throws DALException {
		try (Connection conn = ConnectionProvider.getConnection();) {
			PreparedStatement stmt = conn.prepareStatement(SELECT_BY_USER);
			ArrayList<Enchere> encheres= new ArrayList<Enchere>();
			//Préparer la requete
			stmt.setInt(1, user.getNoUtilisateur());
		
			
			//Executer la requete
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Enchere enchere = new Enchere(rs.getTimestamp("date_enchere").toLocalDateTime(), rs.getInt("montant_enchere"), utilisateurDAO.selectById(rs.getInt("no_utilisateur")), articleVenduDAO.select(rs.getInt("no_article")));
				encheres.add(enchere);
			}
			// Ajouter articleVenduDAO pour get l'article
		
			return encheres;
			
		} catch (Exception e) {
			throw new DALException("Enchere selecteByIndex Error ", e);
		}
	}

}
