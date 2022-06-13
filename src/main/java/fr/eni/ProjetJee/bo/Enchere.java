package fr.eni.ProjetJee.bo;

import java.time.LocalDateTime;

public class Enchere {
	private int noEnchere;
	private LocalDateTime dateEnchere;
	private int montantEnchere;
	private Utilisateur utilisateur;
	private ArticleVendu articleVendu;
	
	public Enchere(int noEnchere, LocalDateTime dateEnchere, int montantEnchere, Utilisateur utilisateur, ArticleVendu articleVendu) {
		this.noEnchere = noEnchere;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.utilisateur = utilisateur;
		this.articleVendu = articleVendu;
	}
	
	public Enchere(LocalDateTime dateEnchere, int montantEnchere, Utilisateur utilisateur, ArticleVendu articleVendu) {
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.utilisateur = utilisateur;
		this.articleVendu = articleVendu;
	}

	public int getNoEnchere() {
		return noEnchere;
	}

	public void setNoEnchere(int noEnchere) {
		this.noEnchere = noEnchere;
	}

	public LocalDateTime getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalDateTime dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public int getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	public Utilisateur getNoUtilisateur() {
		return utilisateur;
	}

	public void setNoUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public ArticleVendu getNoArticle() {
		return articleVendu;
	}

	public void setNoArticle(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}
	
	

}
