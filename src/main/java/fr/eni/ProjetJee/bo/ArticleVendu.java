package fr.eni.ProjetJee.bo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ArticleVendu {
	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDateTime dateDebutEncheres;
	private LocalDateTime dateFinEncheres;
	private int miseAPrix;
	private int prixDeVente;
	private String etatVente;
	private String photo;
	private Utilisateur Utilisateur;
	private Retrait lieuRetrait;
	private Categorie categorie;
	private Utilisateur gagnant;
	
	private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, String etatVente,int miseAPrix,int prixVente, String photo, Utilisateur utilisateur,
			Retrait lieuRetrait, Categorie categorie , Utilisateur gagnant) {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixDeVente = prixVente;
		this.etatVente = etatVente;
		this.photo = photo;
		this.Utilisateur = utilisateur;
		this.lieuRetrait = lieuRetrait;
		this.categorie = categorie;
		this.gagnant = gagnant;
	}
	
	public ArticleVendu(String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, String etatVente,int miseAPrix,int prixVente, String photo, Utilisateur utilisateur,
			Retrait lieuRetrait, Categorie categorie) {
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixDeVente = prixVente;
		this.etatVente = etatVente;
		this.photo = photo;
		this.Utilisateur = utilisateur;
		this.lieuRetrait = lieuRetrait;
		this.categorie = categorie;
	}
	

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(LocalDateTime dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	public LocalDateTime getDateFinEncheres() {
		return dateFinEncheres;
	}
	
	public String getDateFinEncheresFormat() {
		return dateFinEncheres.format(formatter);
	}

	public void setDateFinEncheres(LocalDateTime dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	public int getMiseAPrix() {
		return miseAPrix;
	}

	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	public int getPrixDeVente() {
		return prixDeVente;
	}

	public void setPrixDeVente(int prixDeVente) {
		this.prixDeVente = prixDeVente;
	}

	public String getEtatVente() {
		return etatVente;
	}

	public void setEtatVente(String etatVente) {
		this.etatVente = etatVente;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Utilisateur getUtilisateur() {
		return Utilisateur;
	}

	public void setUtilisateur(Utilisateur Utilisateur) {
		this.Utilisateur = Utilisateur;
	}

	public Retrait getLieuRetrait() {
		return lieuRetrait;
	}

	public void setLieuRetrait(Retrait lieuRetrait) {
		this.lieuRetrait = lieuRetrait;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	

	public Utilisateur getGagnant() {
		return gagnant;
	}


	public void setGagnant(Utilisateur gagnant) {
		this.gagnant = gagnant;
	}


	@Override
	public String toString() {
		return "ArticleVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix="
				+ miseAPrix + ", prixDeVente=" + prixDeVente + ", etatVente=" + etatVente + ", photo=" + photo
				+ ", noUtilisateur=" + Utilisateur + ", lieuRetrait=" + lieuRetrait + ", categorie=" + categorie
				+ "]";
	}
	
	
	
	
}
