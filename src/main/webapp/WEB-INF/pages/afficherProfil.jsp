<%@ page import="fr.eni.ProjetJee.bo.Utilisateur"%>
<jsp:include page="header/headerConnecter.jsp" />
<div class="container" style="max-width: 500px">
	<div class="my-4">
		<div class="row">
			<div class="col">
				<p class="fw-bold">Pseudo :</p>
			</div>
			<div class="col">
				<p>${ user.getPseudo() }</p>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<p class="fw-bold">Nom :</p>
			</div>
			<div class="col">
				<p>${ user.getNom() }</p>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<p class="fw-bold">Prénom :</p>
			</div>
			<div class="col">
				<p>${ user.getPrenom() }</p>
			</div>
		</div>
		<% if (request.getAttribute("owner") != null) { %>
		<div class="row">
			<div class="col">
				<p class="fw-bold">E-mail :</p>
			</div>
			<div class="col">
				<p>${ user.getEmail() }</p>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<p class="fw-bold">Téléphone :</p>
			</div>
			<div class="col">
				<p>${ user.getTelephone() }</p>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<p class="fw-bold">Rue :</p>
			</div>
			<div class="col">
				<p>${ user.getRue() }</p>
			</div>
		</div>
		<% } %>
		<div class="row">
			<div class="col">
				<p class="fw-bold">Code Postal :</p>
			</div>
			<div class="col">
				<p>${ user.getCodePostal() }</p>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<p class="fw-bold">Ville :</p>
			</div>
			<div class="col">
				<p>${ user.getVille() }</p>
			</div>
		</div>
		${not empty owner ? "<a href='/ProjetJee/editProfil' class='text-decoration-none btn btn-outline-secondary mt-3' style='display: block; margin: auto'>Modifier</a>" : null }
		
	</div>
</div>
<jsp:include page="footer/footer.jsp" />