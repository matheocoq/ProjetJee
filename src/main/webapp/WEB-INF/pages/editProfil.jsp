<%@ page import="fr.eni.ProjetJee.bo.Utilisateur" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="header/header.jsp" />

<form action="http://localhost:8080/ProjetJee/editProfil" method="post">
	
	${not empty errorModification ? "<div class='alert alert-danger mb-4' role='alert'>Modification incorrect !!</div>" : null }
	<br><br>
	${not empty infosNewMdp ? "<div class='alert alert-danger mb-4' role='alert'> Nouveau mot de passe ok !!</div>" : null }
	<br>
	
	<%
		Utilisateur user = (Utilisateur)request.getSession().getAttribute("utilisateur");
	%>
	<div class="modifInscription">
		
		<div>
			<div style="display: inline-block;" class="editCol">
			<div style="float: right;">
			<label for="pseudo">Pseudo * :  </label>
	  		<input type="text" id="pseudo" name="pseudo"  required="required" value="<%= user.getPseudo()%>"> 
	  		</div>
	  		<div>
	  		<label for="prenom">Prénom * :  </label>
	  		<input type="text" id="prenom" name="prenom"  required="required" value="<%= user.getPrenom()%>"> 
	  		</div>
	  		<div>
	  		<label for="tel">Teléphone :  </label>
	  		<input type="tel" id="tel" name="tel" value="<%= user.getTelephone()%>">
	  		</div> 
	  		<div>
	  		<label for="codePostal">Code postal * :  </label>
	  		<input type="text" id="codePostal" name="codePostal"  required="required" value="<%= user.getCodePostal()%>">
	  		</div> 
  			</div>
  			
  			
  			<div style="display: inline-block; transform: translateY(-6px);"class="editCol">
			<div>
			<label for="nom">Nom * :  </label>
	  		<input type="text" id="nom" name="nom" required="required" value="<%= user.getNom()%>"> 
	  		</div>
	  		<div>
	  		<label for="email">Email * :  </label>
	  		<input type="email" id="email" name="email"  required="required" value="<%= user.getEmail()%>">
	  		</div>
	  		<div>
	  		<label for="rue">Rue * :  </label>
	  		<input type="text" id="rue" name="rue"  required="required" value="<%= user.getRue()%>">
	  		</div> 
	  		<div>
	  		<label for="ville">Ville * :  </label>
	  		<input type="text" id="ville" name="ville"  required="required" value="<%= user.getVille()%>">
	  		</div> 
  			</div>
  			
		</div>
		<label for="mdp">Mot de passe actuel * :  </label>
  		<input type="password" id="mdp" name="mdp"  required="required" pattern="(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{12,}$">
  		<a href="http://localhost:8080/ProjetJee/nouveauMdp">nouveau mot de passe?</a> <br>
  		<br>
		<input type="hidden" id="hashMdp" name="hashMdp" value="<%= user.getMotDePasse()%>">
		
		<label for="credit" > Crédit : <%= user.getCredit() %> </label>
		<input type="hidden" name="noUser" value="<%= user.getNoUtilisateur()%>">
		<br><br>
		
	</div>
	<div class="saveEditProfil" >
		<input type="submit" value="Enregistrer">
	</div>
	
</form>
<form action="<%=request.getContextPath()%>/supprimer" method="post" style="text-align: center;margin-top: 10px;">
	<input class="bouton-sup-compte" type="submit" value="Supprimer mon compte">
</form>
<jsp:include page="footer/footer.jsp" />