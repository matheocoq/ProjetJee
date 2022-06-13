package fr.eni.ProjetJee.bll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Date;

import fr.eni.ProjetJee.bo.Retrait;
import fr.eni.ProjetJee.dal.ConnectionProvider;

public class Test {

	public static void main(String[] args) throws BLLException {
		
		RetraitManager mger = RetraitManager.getInstance();
		
		/*try {
			
			mger.addRetraits(new Retrait(1,"Jamet", "44100", "NANTES"));
			System.out.println("Ajout");
			

		} catch (BLLException e) {
			e.printStackTrace();
		}*/
		
		
		try(Connection conn = ConnectionProvider.getConnection();) {
			mger.addRetraits(new Retrait(1,"Jamet", "44100", "NANTES"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
