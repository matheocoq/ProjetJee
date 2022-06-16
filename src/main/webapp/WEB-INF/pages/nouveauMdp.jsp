<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="header/header.jsp" />

<form action="http://localhost:8080/ProjetJee/nouveauMdp" method="post">

	${not empty newMdpError ? "<div class='alert alert-danger mb-4' role='alert'> Erreur dans la saisie du nouveau mot de passe. </div>" : null }
	<br><br>

	<div class="newMdp">
		
		<label for="mdpActuel">Nouveau actuel :  </label>
  		<input type="password" id="mdpActuel" name="mdpActuel" required="required"> <br><br>
  		
	
		<label for="newMdp">Nouveau mot de passe :  </label>
  		<input type="password" id="newMdp" name="newMdp"  pattern="(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{12,}$" title="Doit contenir au moins une lettre majuscule,une minscule et au moins un chiffre, un caractère spécial et au minimum 12 caractères" required="required"> <br><br>
  		
  		<label for="confirmation">Confirmation :  </label>
  		<input type="password" id="confirmation" name="confirmation" pattern="(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{12,}$" title="Doit contenir au moins une lettre majuscule,une minsculeet au moins un chiffre, un caractère spécial et au minimum 12 caractères" required="required"><br><br>
	</div>
	<div class="saveNewMdp">
		<input type="submit" value="Enregistrer">
	</div>
	
</form>

<jsp:include page="footer/footer.jsp" />