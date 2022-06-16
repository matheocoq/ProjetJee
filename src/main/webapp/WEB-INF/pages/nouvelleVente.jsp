<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page import="fr.eni.ProjetJee.bo.Utilisateur"%>
<%@ page import="fr.eni.ProjetJee.bo.Categorie"%>

<%@ page import="java.util.List"%>
<jsp:include page="header/header.jsp" />

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container">
<h3 class="text-center mb-4 titre-accueil">Nouvelle vente</h3>
	<div class="">
		<div style="display: inline-block;width: 33%;">
			<img class="shadow-sm imgArticle" alt="" src="${pageContext.request.contextPath}/resources/imgTestNouvelleVente.jpg">
		</div>
		<div style="transform: translateY(-172px);">
			
			
			
			
			<form action="<%= request.getContextPath() %>/NouvelleVente" method="post">
				<div class="">
					<div style="display: inline-block;width: 33%;">
					
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
						
						
					</div>
					<div  class="rounded col-article article-info">
					${not empty nouvelleVenteError ? "<div class='alert alert-danger mb-4' role='alert'>Un ou plusieur champ innatendu !!</div>" : null}
						<div class="form-group ">
							<label for="article">Article *</label>
							<input type="text" class="form-control" name="article" id="article" placeholder="Nom de mon article" >
						</div>
						
						<div class="form-floating ">
						  <textarea class="form-control" placeholder="Description" name="description" id="description"></textarea>
						  <label for="description">Description *</label>
						</div>
						
						<div class="">
							<label for="categorie">Cat�gorie *</label>
							<select class="form-select" name="categorie" id="categorie">
							  <option selected>Choix d'une cat�gorie</option>
							  
							  <c:forEach var="categorie" items="${ categorieList }">
							  	<option value="${ categorie.getNoCategorie() }">${ categorie.getLibelle() }</option>
							  </c:forEach>
							  
							</select>
						</div>
						
						<div class="input-group ">
						  <label class="input-group-text" for="photo">Uploader</label>
						  <input type="file" class="form-control" name="photo" id="photo" disabled="disabled">
						</div>
						
						<div class="form-group ">
							<label for="prix">Mise � prix *</label>
							<input type="number" min="0" max="" class="form-control" name="prix" id="prix" placeholder="100" >
						</div>
						
						<div class="form-group ">
							<label for="dateDebut">Date de d�but *</label>
							<input type="datetime-local" name="dateDebut" id="dateDebut" class="form-control dateDebut" value="${ dateNow }" min="${ dateNow }">
						</div>
						
						<div class="form-group ">
							<label for="dateFin">Date de Fin *</label>
							<input type="datetime-local" name="dateFin" id="dateFin" class="form-control dateFin" value="${ dateFin }" min="${ dateFin }">
						</div>
					</div>
					
					
					
				</div>
				
				<div style="text-align: center;margin-top: 20px;">
					<div class="">
						<button class="btn btn-green me-1" >Valider</button>
						<a href="<%= request.getContextPath() %>/accueil" class="text-decoration-none btn btn-blue" >Annuler</a>
					</div>
				</div>
		
			</form>
		</div>
	</div>
</div>
<jsp:include page="footer/footer.jsp" />