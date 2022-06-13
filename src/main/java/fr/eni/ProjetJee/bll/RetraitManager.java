package fr.eni.ProjetJee.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ProjetJee.bo.Retrait;
import fr.eni.ProjetJee.dal.DALException;
import fr.eni.ProjetJee.dal.DAOFactory;
import fr.eni.ProjetJee.dal.RetraitDAO;

public class RetraitManager {
	
	private RetraitDAO daoRetrait;
	
	
	private static RetraitManager instance;
	
	public static RetraitManager getInstance() {
		if(instance == null) {
			instance = new RetraitManager();
		}
		return instance;
	}
	
	private RetraitManager() {
		daoRetrait = DAOFactory.getDAORetrait();
	}
	
	public List<Retrait> getRetraits() throws BLLException{
		List<Retrait> retraits = new ArrayList<>();
		try {
			retraits = daoRetrait.selectAll();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retraits;
	}
	
	public void addRetraits(Retrait retrait) throws BLLException{
		try {
			daoRetrait.insert(retrait);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateRetrait(Retrait retrait) throws BLLException{
		try {
			daoRetrait.update(retrait);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void removeRetrait(int no_retrait) throws BLLException{
		try {
			daoRetrait.delete(no_retrait);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Retrait getArticleById(int noRetrait) throws BLLException{
		Retrait retrait = null;
		try {
			retrait =  daoRetrait.selectById(noRetrait);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retrait;
	}

}
