package fr.eni.ProjetJee.dal.sqlServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ProjetJee.bo.Retrait;
import fr.eni.ProjetJee.dal.ConnectionProvider;
import fr.eni.ProjetJee.dal.DALException;
import fr.eni.ProjetJee.dal.RetraitDAO;

public class RetraitDAOImpl implements RetraitDAO{
	
	private static final String INSERT = "insert into RETRAITS(rue, code_postal, ville) values (?, ?, ?)";
	private static final String ALL = "select * from RETRAITS";
	private static final String UPDATE = "UPDATE RETRAITS SET rue=?,code_postal=?,ville=? where no_retrait=?";
	private final String DELETE = "DELETE FROM RETRAITS WHERE no_retrait=?";
	private final String selectID = "Select * from RETRAITS where no_retrait=?";
	
	
	@Override
	public void insert(Retrait retrait) throws DALException {
		
		Connection conn = null;
		try {
			//R�cup�rer une connexion
			conn = ConnectionProvider.getConnection();

			//Pr�parer la requete
			PreparedStatement stmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, retrait.getRue());
			stmt.setString(2, retrait.getCodePostal());
			stmt.setString(3,retrait.getVille());
			
			//Executer la requete
			stmt.executeUpdate();
			
			//Recup�rer l'identifiant cr��
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				retrait.setNoRetrait(rs.getInt(1));
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
	public Retrait selectById(Integer no_retrait) throws DALException {
		Retrait retrait = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			//Ouverture de la connexion � la base
			
			conn = ConnectionProvider.getConnection();
			
			//Execution de la requ�te
			stmt = conn.prepareStatement(selectID);
			stmt.setInt(1, no_retrait);
			
			ResultSet rs = stmt.executeQuery();
			
			//Traitement du r�sultat
			if (rs.next()) {
				retrait = new Retrait(rs.getInt("no_retrait"),rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"));
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
		return retrait;

	}

	@Override
	public List<Retrait> selectAll() throws DALException {
		List<Retrait> allRetraits = new ArrayList<>();
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
		      Retrait retrait = new Retrait(rs.getInt("no_retrait"),rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"));
		      
		      allRetraits.add(retrait);
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
		return allRetraits;
	}

	@Override
	public void update(Retrait retrait) throws DALException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			//R�cup�rer une connexion
			conn = ConnectionProvider.getConnection();

			//Pr�parer la requete
			stmt = conn.prepareStatement(ALL);
			
			//Execution de la requ�te
			stmt = conn.prepareStatement(UPDATE);
			stmt.setString(1, retrait.getRue());
			stmt.setString(2, retrait.getCodePostal());
			stmt.setString(3, retrait.getVille());
			stmt.setInt(4, retrait.getNoRetrait());
			
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
	public void delete(Integer no_retrait) throws DALException {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			//Execution de la requ�te
			stmt = conn.prepareStatement(DELETE);
			stmt.setInt(1, no_retrait);
			
			stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new DALException("Suppression Impossible");
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