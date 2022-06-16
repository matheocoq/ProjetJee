

<jsp:include page="header/header.jsp" />
<form action="http://localhost:8080/ProjetJee/inscription" method="post">
	
	${not empty errorInscription ? "<div class='alert alert-danger mb-4' role='alert'>Inscription incorrect !!</div>" : null }
	<br><br>
	
	
	<div class="inscription">
	
		<label for="pseudo">Pseudo :  </label>
  		<input type="text" id="pseudo" name="pseudo"  required="required"> 
  		<label for="nom">Nom :  </label>
  		<input type="text" id="nom" name="nom"  required="required"> <br><br>

  		<label for="prenom">Prénom :  </label>
  		<input type="text" id="prenom" name="prenom"  required="required"> 
  		<label for="email">Email :  </label>
  		<input type="email" id="email" name="email"  required="required"><br><br>
 
 		<label for="tel">Teléphone :  </label>
  		<input type="tel" id="tel" name="tel"  required="required"> 
  		<label for="rue">Rue :  </label>
  		<input type="text" id="rue" name="rue"  required="required"><br><br>
  	
  		<label for="codePostal">Code postal :  </label>
  		<input type="text" id="codePostal" name="codePostal"  required="required"> 
  		<label for="ville">Ville :  </label>
  		<input type="text" id="ville" name="ville"  required="required"><br><br>
	
		<label for="mdp">Mot de passe :  </label>
  		<input type="password" id="mdp" name="mdp"  pattern="(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{12,}$" title="Doit contenir au moins une lettre majuscule,une minscule et au moins un chiffre, un caractère spécial et au minimum 12 caractères" required="required"> 
  		<label for="confirmation">Confirmation :  </label>
  		<input type="password" id="confirmation" name="confirmation" pattern="(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{12,}$" title="Doit contenir au moins une lettre majuscule,une minsculeet au moins un chiffre, un caractère spécial et au minimum 12 caractères" required="required"><br><br>
	
		<br><br>
	
	</div>
  	<div class="saveNewInscription">
  		<input type="submit" value="Créer  ">
		<button onclick="window.location.href = 'http://localhost:8080/ProjetJee/accueil';" style="transform: translateX(70px);">Annuler</button>
  	</div>
	
	
</form>

<jsp:include page="footer/footer.jsp" />