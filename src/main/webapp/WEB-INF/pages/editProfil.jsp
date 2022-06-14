<%@ page import="fr.eni.ProjetJee.bo.Utilisateur" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="header/headerRegister.jsp" />

<form action="http://localhost:8080/ProjetJee/editProfil" method="post">
	
	${not empty errorModification ? "<div class='alert alert-danger mb-4' role='alert'>Modification incorrect !!</div>" : null }
	<br><br>
	
	<%
		Utilisateur user = (Utilisateur)request.getSession().getAttribute("utilisateur");
	%>
	<div class="modifInscription">
		<label for="pseudo">Pseudo :  </label>
  		<input type="text" id="pseudo" name="pseudo"  required="required" value="<%= user.getPseudo()%>"> 
  		<label for="nom">Nom :  </label>
  		<input type="text" id="nom" name="nom" required="required" value="<%= user.getNom()%>"> <br><br>

  		<label for="prenom">Prénom :  </label>
  		<input type="text" id="prenom" name="prenom"  required="required" value="<%= user.getPrenom()%>"> 
  		<label for="email">Email :  </label>
  		<input type="email" id="email" name="email"  required="required" value="<%= user.getEmail()%>"><br><br>
 
 		<label for="tel">Teléphone :  </label>
  		<input type="tel" id="tel" name="tel"  required="required" value="<%= user.getTelephone()%>"> 
  		<label for="rue">Rue :  </label>
  		<input type="text" id="rue" name="rue"  required="required" value="<%= user.getRue()%>"><br><br>
  	
  		<label for="codePostal">Code postal :  </label>
  		<input type="text" id="codePostal" name="codePostal"  required="required" value="<%= user.getCodePostal()%>"> 
  		<label for="ville">Ville :  </label>
  		<input type="text" id="ville" name="ville"  required="required" value="<%= user.getVille()%>"><br><br>
	
		<label for="mdp">Mot de passe actuel :  </label>
  		<input type="password" id="mdp" name="mdp"  required="required" value="<%= user.getMotDePasse()%>"> <br><br>
	
		<label for="newMdp">Nouveau mot de passe :  </label>
  		<input type="password" id="newMdp" name="newMdp"  pattern="(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{12,}$" title="Doit contenir au moins une lettre majuscule,une minscule et au moins un chiffre, un caractère spécial et au minimum 12 caractères"> 
  		<label for="confirmation">Confirmation :  </label>
  		<input type="password" id="confirmation" name="confirmation" pattern="(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{12,}$" title="Doit contenir au moins une lettre majuscule,une minsculeet au moins un chiffre, un caractère spécial et au minimum 12 caractères"><br><br>
		<label for="credit" > Crédit : <%= user.getCredit() %> </label>
		<input type="hidden" name="noUser" value="<%= user.getNoUtilisateur()%>">
		<br><br>
	</div>
	
	<input type="submit" value="Enregistrer">
	<button onclick="window.location.href = '<%=request.getContextPath()%>/supprimer';">Supprimer mon compte</button>
</form>

<jsp:include page="footer/footer.jsp" />