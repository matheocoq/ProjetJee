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

<nav class="navbar navbar-expand-lg bg-light mb-2">
	<div class="container-fluid">
		<a class="navbar-brand" href="${pageContext.request.contextPath}/accueil"><img alt="" class="imgLogo" src="${pageContext.request.contextPath}/resources/logoENIEncheres.png"></a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav" >
			<c:choose>
				<c:when test="${!empty sessionScope.utilisateur}">
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/accueil">Accueil</a>
						</li>
						<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/NouvelleVente">Vendre
								un article</a></li>
						<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/AfficherProfil?user=${utilisateur.getNoUtilisateur()} ">Mon
								profil</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/Deconnexion">Déconexion</a></li>
					</ul>
				</c:when>
				<c:otherwise>
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/Connexion">S'insrire - Se Connecter</a></li>
					</ul>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</nav>
<div class="row mx-3 mb-3">
	<div class="col-1" style="max-width: 100px" >
		<a class="text-decoration-none text-dark fw-bold" id="btnRetour" href="javascript:history.back()" ><span class="iconify" data-icon="ic:outline-keyboard-return"></span> Retour</a>
	</div>
	<div class="col-1">
		<a class="text-decoration-none text-dark fw-bold" id="btnRefresh" href="javascript:location.reload()" ><span class="iconify" data-icon="ci:refresh"></span> Rafraîchir</a>
	</div>
</div>
