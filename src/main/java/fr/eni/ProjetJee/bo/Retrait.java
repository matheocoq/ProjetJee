package fr.eni.ProjetJee.bo;

public class Retrait {
	
	private int noRetrait;
	private String rue;
	private String codePostal;
	private String ville;
	
	
	
	public Retrait(String rue, String codePostal, String ville) {
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	public Retrait(int noRetrait, String rue, String codePostal, String ville) {
		this.noRetrait=noRetrait;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}



	public int getNoRetrait() {
		return noRetrait;
	}
	

	public void setNoRetrait(int noRetrait) {
		this.noRetrait = noRetrait;
	}



	public String getRue() {
		return rue;
	}



	public void setRue(String rue) {
		this.rue = rue;
	}



	public String getCodePostal() {
		return codePostal;
	}



	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}



	public String getVille() {
		return ville;
	}



	public void setVille(String ville) {
		this.ville = ville;
	}


	@Override
	public String toString() {
		return "Retrait [rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville + "]";
	}
	
	
	
}
