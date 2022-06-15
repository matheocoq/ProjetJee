<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page import="fr.eni.ProjetJee.bo.Utilisateur"%>
<%@ page import="fr.eni.ProjetJee.bo.Categorie"%>

<%@ page import="java.util.List"%>
<jsp:include page="header/header.jsp" />

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container">
	<div class="row">
		<div class="col-3">
			<img alt="" src="${pageContext.request.contextPath}/resources/imgTestNouvelleVente.jpg">
		</div>
		<div class="col-8 offset-1">
		
			<h5 class="titre-accueil"">Nouvelle vente</h5>
			<form action="<%= request.getContextPath() %>/NouvelleVente" method="post">
				<div class="form-group mt-3">
					<label for="article">Article *</label>
					<input type="text" class="form-control" name="article" id="article" >
				</div>
				
				<div class="form-floating mt-3">
				  <textarea class="form-control" placeholder="Description" name="description" id="description"></textarea>
				  <label for="description">Description *</label>
				</div>
				
				<div class="mt-3">
					<label for="categorie">Catégorie *</label>
					<select class="form-select" name="categorie" id="categorie">
					  <option selected></option>
					  
					  <c:forEach var="categorie" items="${ categorieList }">
					  	<option value="${ categorie.getNoCategorie() }">${ categorie.getLibelle() }</option>
					  </c:forEach>
					  
					</select>
				</div>
				
				<div class="input-group mt-3">
				  <label class="input-group-text" for="photo">Uploader</label>
				  <input type="file" class="form-control" name="photo" id="photo" disabled="disabled">
				</div>
				
				<div class="form-group mt-3">
					<label for="prix">Mise à prix *</label>
					<input type="number" min="0" max="" class="form-control" name="prix" id="prix" >
				</div>
				
				<div class="form-group mt-3">
					<label for="dateDebut">Date de début *</label>
					<input type="date" name="dateDebut" id="dateDebut" class="form-control dateDebut" value="${ dateNow }" min="${ dateNow }">
				</div>
				
				<div class="form-group mt-3">
					<label for="dateFin">Date de Fin *</label>
					<input type="date" name="dateFin" id="dateFin" class="form-control dateFin" value="${ dateNow }" min="${ dateNow }">
				</div>
				
				<hr>
				
				<p class="text-center fw-bold m-0">Retrait</p>
				
				<div class="form-group">
					<label for="rue">Rue</label>
					<input type="text" class="form-control" name="rue" id="rue" value="${ not empty utilisateur ? utilisateur.getRue() : null }" >
				</div>
				
				<div class="form-group mt-3">
					<label for="codePostal">Code postal</label>
					<input type="text" class="form-control" name="codePostal" id="codePostal" value="${ not empty utilisateur ? utilisateur.getCodePostal() : null }" >
				</div>
				
				<div class="form-group mt-3">
					<label for="ville">Ville</label>
					<input type="text" class="form-control" name="ville" id="ville" value="${ not empty utilisateur ? utilisateur.getVille() : null }" >
				</div>
				
				<div class="row mt-3">
					<div class="col offset-5">
						<button class="btn btn-primary me-1" >Valider</button>
						<a href="<%= request.getContextPath() %>/accueil" class="text-decoration-none btn btn-secondary" >Annuler</a>
					</div>
				</div>
				
			</form>
		</div>
	</div>
</div>
<jsp:include page="footer/footer.jsp" />