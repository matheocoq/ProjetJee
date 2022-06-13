package fr.eni.ProjetJee.bo;

public class Categorie {

	private int noCategorie;
	private String Libelle;
		
	public Categorie(int noCategorie, String libelle) {
		this.noCategorie = noCategorie;
		Libelle = libelle;
	}
	
	public Categorie(String libelle) {
		Libelle = libelle;
	}

	public int getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	public String getLibelle() {
		return Libelle;
	}

	public void setLibelle(String libelle) {
		Libelle = libelle;
	}
	
	
}
