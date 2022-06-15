<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page import="fr.eni.ProjetJee.bo.ArticleVendu"%>
<%@ page import="fr.eni.ProjetJee.bo.Enchere"%>
<%@ page import="fr.eni.ProjetJee.bo.Categorie"%>
<%@ page import="fr.eni.ProjetJee.bo.Utilisateur"%>

<%@ page import="java.util.List"%>
<jsp:include page="header/header.jsp" />

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container">
	<h3 class="text-center mt-3">Détail vente</h3>
	<div class="row">
		<div class="col-3">
			<img class="shadow-sm imgArticle" alt="" src="${pageContext.request.contextPath}/resources/imgTestNouvelleVente.jpg">
		</div>
		<div class="col-8 mt-5 offset-1 shadow p-3 mb-5 bg-body rounded col-article">
		
			
			
			${not empty propositionError ? "<div class='alert alert-danger mt-5 mb-3' role='alert'>Proposition incorrect !!</div>" : null}
			
			<p class="fw-bold">${ article.getNomArticle() }</p>
			
			<div class="row">
				<div class="col-3">
					<p>Déscription : </p>
				</div>
				<div class="col-9">
					<p>${ article.getDescription() }</p>
				</div>
			</div>	
			
			<div class="row">
				<div class="col-3">
					<p>Catégorie : </p>
				</div>
				<div class="col-9">
					<p>${ article.getCategorie().getLibelle() }</p>
				</div>
			</div>			
			
			<div class="row">
				<div class="col-3">
					<p>Meilleur offre : </p>
				</div>
				<div class="col-9">
					<p>${enchere.getMontantEnchere()} ${ not empty enchere ? " pts par " : null} ${enchere.getNoUtilisateur().getPseudo()} </p>
				</div>
			</div>
			
			<div class="row">
				<div class="col-3">
					<p>Mise à prix : </p>
				</div>
				<div class="col-9">
					<p>${ article.getMiseAPrix() } points</p>
				</div>
			</div>
			
			<div class="row">
				<div class="col-3">
					<p>Fin de l'enchère : </p>
				</div>
				<div class="col-9">
					<p>${ article.getDateFinEncheresFormat() }</p>
				</div>
			</div>
			
			<div class="row">
				<div class="col-3">
					<p>Retrait : </p>
				</div>
				<div class="col-9">
					<p class="mb-0">${ retrait.getRue() }</p>
					<p>${ retrait.getCodePostal() } ${ retrait.getVille() }</p>
				</div>
			</div>
			
			<div class="row">
				<div class="col-3">
					<p>Vendeur : </p>
				</div>
				<div class="col-9">
					<p>${ article.getUtilisateur().getPseudo() }</p>
				</div>
			</div>
			
			<form action="<%= request.getContextPath() %>/DetailVente?article=${ article.getNoArticle() }" method="post">
				<div class="row mt-5">
					<div class="col-2">
						<p>Ma proposition : </p>
					</div>
					<div class="col-5" style="max-width: 200px">
						<input class="form-control" name="proposition" type="number" min="${ not empty enchere ? enchere.getMontantEnchere() +1 : article.getMiseAPrix() +1 }" value="${ not empty enchere ? enchere.getMontantEnchere() +1 : article.getMiseAPrix() +1 }" />
					</div>
					<div class="col-5">
						<button class="btn btn-green">Enchérir</button>
					</div>
				</div>
			</form>
			
		</div>
	</div>
</div>
<jsp:include page="footer/footer.jsp" />