package fr.eni.ProjetJee.dal.sqlServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ProjetJee.bo.Categorie;
import fr.eni.ProjetJee.dal.CategorieDAO;
import fr.eni.ProjetJee.dal.ConnectionProvider;
import fr.eni.ProjetJee.dal.DALException;

public class CategorieDAOImpl implements CategorieDAO{
	
	private static final String INSERT = "insert into CATEGORIES(libelle) values (?)";
	private final String selectID = "Select * from CATEGORIES where no_categorie=?";
	private static final String ALL = "SELECT * FROM CATEGORIES";
	private static final String UPDATE = "UPDATE CATEGORIES SET libelle=? where no_categorie=?";
	private final String DELETE = "DELETE FROM CATEGORIES WHERE no_categorie=?";
	

	@Override
	public void insert(Categorie cat) throws DALException {
		Connection conn = null;
		try {
			//R�cup�rer une connexion
			conn = ConnectionProvider.getConnection();

			//Pr�parer la requete
			PreparedStatement stmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, cat.getLibelle());
			//Executer la requete
			stmt.executeUpdate();
			
			//Recup�rer l'identifiant cr��
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				cat.setNoCategorie(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DALException("Erreur insert", e);
		
		}finally {
			//Fermer la connexion
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public Categorie selectById(Integer no_categorie) throws DALException {
		Categorie categorie = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			//Ouverture de la connexion � la base
			
			conn = ConnectionProvider.getConnection();
			
			//Execution de la requ�te
			stmt = conn.prepareStatement(selectID);
			stmt.setInt(1, no_categorie);
			
			ResultSet rs = stmt.executeQuery();
			
			//Traitement du r�sultat
			if (rs.next()) {
				categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
			}
			
		} catch (SQLException e) {
			throw new DALException("Selection par ID impossible");
		} finally {
			//Fermmeture de la connexion � la base
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(stmt!=null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return categorie;
	}

	@Override
	public List<Categorie> selectAll() throws DALException {
		List<Categorie> allCategories = new ArrayList<>();
		Connection conn = null;
		try {
			//R�cup�rer une connexion
			conn = ConnectionProvider.getConnection();

			//Pr�parer la requete
			PreparedStatement stmt = conn.prepareStatement(ALL);
			
			
			//Executer la requete
			ResultSet rs = stmt.executeQuery();
		    while ( rs.next() )
		    {
		      Categorie cat = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
		      
		      allCategories.add(cat);
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DALException("Erreur insert", e);
		
		}finally {
			//Fermer la connexion
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return allCategories;
	}

	@Override
	public void update(Categorie cat) throws DALException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			//R�cup�rer une connexion
			conn = ConnectionProvider.getConnection();

			//Pr�parer la requete
			//stmt = conn.prepareStatement(ALL);
			
			//Execution de la requ�te
			stmt = conn.prepareStatement(UPDATE);
			stmt.setInt(1,cat.getNoCategorie() );
			stmt.setString(2, cat.getLibelle());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new DALException("Mise � jour Impossible", e);
		} finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(stmt!=null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
	}

	@Override
	public void delete(Integer no_categorie) throws DALException {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			//Execution de la requ�te
			stmt = conn.prepareStatement(DELETE);
			stmt.setInt(1, no_categorie);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Suppression de la cat�gorie "+no_categorie+" est impossible");
		} finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(stmt!=null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

}
