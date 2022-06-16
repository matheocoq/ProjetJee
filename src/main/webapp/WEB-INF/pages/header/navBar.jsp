<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="https://code.iconify.design/2/2.2.1/iconify.min.js"></script>
<style>
body {
min-height: 100vh;
position: relative;
margin: 0;
}
footer {
position: absolute;
bottom: 0;
}
</style>

<nav class="navbar navbar-expand-lg bg-white mb-2">
	<div class="container-fluid">
		<a class="navbar-brand p-0" href="${pageContext.request.contextPath}/accueil"><img alt="" class="imgLogo" src="${pageContext.request.contextPath}/resources/logoENIEncheres.png"></a>
	<div style="flex-grow: 1;">
		<div  style="max-width: 100px;display: inline-block;" >
			<a class="text-decoration-none text-dark fw-bold" id="btnRetour" href="javascript:history.back()" ><span class="iconify" data-icon="ic:outline-keyboard-return"></span> Retour</a>
		</div>
		<div style="display: inline-block;">
			<a class="text-decoration-none text-dark fw-bold" id="btnRefresh" href="javascript:location.reload()" ><span class="iconify" data-icon="ci:refresh"></span> Rafraîchir</a>
		</div>
	</div>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarNav" >
			<c:choose>
				<c:when test="${!empty sessionScope.utilisateur}">
					<ul class="navbar-nav">
						<li class="nav-item bouton-bandeau"><a class="nav-link nav-bouton-texte" href="<%=request.getContextPath()%>/accueil">Accueil</a>
						</li>
						<li class="nav-item bouton-bandeau"><a class="nav-link nav-bouton-texte" href="${pageContext.request.contextPath}/NouvelleVente">Vendre
								un article</a></li>
						<li class="nav-item bouton-bandeau"><a class="nav-link nav-bouton-texte" href="${pageContext.request.contextPath}/AfficherProfil?user=${utilisateur.getNoUtilisateur()} ">Mon
								profil</a></li>
						<li class="nav-item bouton-bandeau"><a class="nav-link nav-bouton-texte"
							href="<%=request.getContextPath()%>/Deconnexion">Déconexion</a></li>
					</ul>
				</c:when>
				<c:otherwise>
					<ul class="navbar-nav">
						<li class="nav-item bouton-bandeau"><a class="nav-link nav-bouton-texte" href="<%=request.getContextPath()%>/accueil">Accueil</a>
						</li>
						<li class="nav-item bouton-bandeau"><a class="nav-link nav-bouton-texte"
							href="${pageContext.request.contextPath}/Connexion">Se Connecter</a>
						</li>
						<li class="nav-item bouton-bandeau"><a class="nav-link nav-bouton-texte"
							href="${pageContext.request.contextPath}/inscription">S'insrire</a>
						</li>
					</ul>
				</c:otherwise>
			</c:choose>
			<div class="time blue mt-2">
			<p class="txtTime"></p>
			</div>
		</div>
	</div>
</nav>

