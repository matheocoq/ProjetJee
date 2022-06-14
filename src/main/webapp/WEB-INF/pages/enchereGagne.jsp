<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page import="fr.eni.ProjetJee.bo.ArticleVendu"%>
<%@ page import="fr.eni.ProjetJee.bo.Enchere"%>
<%@ page import="fr.eni.ProjetJee.bo.Categorie"%>
<%@ page import="fr.eni.ProjetJee.bo.Utilisateur"%>

<%@ page import="java.util.List"%>
<jsp:include page="header/headerConnecter.jsp" />

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container">
	<div class="row">
		<div class="col-3">
			<img alt="" src="${pageContext.request.contextPath}/resources/imgTestNouvelleVente.jpg">
		</div>
		<div class="col-8 offset-1">
		<c:choose>
			<c:when test="${empty gagnant}">
				<h5 style="text-align: center">Vous avez remport� la vente</h5>
			</c:when>
			<c:otherwise>
				<h5 style="text-align: center">${ gagnant } a remport� la vente</h5>
			</c:otherwise>
		</c:choose>
				
			
			
			${not empty propositionError ? "<div class='alert alert-danger mt-5 mb-3' role='alert'>Proposition incorrect !!</div>" : null}
			
			<p class="fw-bold mt-5">${ article.getNomArticle() }</p>
			
			<div class="row">
				<div class="col-2">
					<p>D�scription : </p>
				</div>
				<div class="col-6">
					<p>${ article.getDescription() }</p>
				</div>
			</div>	
			
			<div class="row">
				<div class="col-2">
					<p>Cat�gorie : </p>
				</div>
				<div class="col-6">
					<p>${ article.getCategorie().getLibelle() }</p>
				</div>
			</div>			
			
			<div class="row">
				<div class="col-2">
					<p>Meilleur offre : </p>
				</div>
				<div class="col-6">
					<p>${enchere.getMontantEnchere()} pts </p>
				</div>
			</div>
			
			<div class="row">
				<div class="col-2">
					<p>Mise � prix : </p>
				</div>
				<div class="col-6">
					<p>${ article.getMiseAPrix() } points</p>
				</div>
			</div>
			
			<div class="row">
				<div class="col-2">
					<p>Retrait : </p>
				</div>
				<div class="col-6">
					<p class="mb-0">${ retrait.getRue() }</p>
					<p>${ retrait.getCodePostal() } ${ retrait.getVille() }</p>
				</div>
			</div>
			
			<c:if test="${empty gagnant}">
				<div class="row">
					<div class="col-2">
						<p>Vendeur : </p>
					</div>
					<div class="col-6">
						<p>${ article.getUtilisateur().getPseudo() }</p>
					</div>
				</div>
			</c:if>
			
			<div class="row">
				<div class="col-2">
					<p>Tel : </p>
				</div>
				<div class="col-6">
					<p>${ article.getUtilisateur().getTelephone() }</p>
				</div>
			</div>
			
			
			
			<c:if test="${not empty retraitE && not empty gagnant}">
				<div class="row mt-5">
					<div class="col-2">
						<a class="text-decoration-none btn btn-secondary" href="<%=request.getContextPath()%>/accueil">Retrait �fectu�</a>
					</div>
				</div>
			</c:if>
			
		</div>
	</div>
</div>
<jsp:include page="footer/footer.jsp" />