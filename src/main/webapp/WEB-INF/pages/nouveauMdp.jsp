<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="header/headerRegister.jsp" />

<form action="http://localhost:8080/ProjetJee/nouveauMdp" method="post">

	
	<label for="newMdp">Nouveau mot de passe :  </label>
  	<input type="password" id="newMdp" name="newMdp"  pattern="(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{12,}$" title="Doit contenir au moins une lettre majuscule,une minscule et au moins un chiffre, un caractère spécial et au minimum 12 caractères"> 
  	<label for="confirmation">Confirmation :  </label>
  	<input type="password" id="confirmation" name="confirmation" pattern="(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{12,}$" title="Doit contenir au moins une lettre majuscule,une minsculeet au moins un chiffre, un caractère spécial et au minimum 12 caractères"><br><br>
	<input type="submit" value="Enregistrer">
</form>

<jsp:include page="footer/footer.jsp" />