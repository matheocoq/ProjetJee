<jsp:include page="header/headerConnecter.jsp" />
<%@ page import="fr.eni.ProjetJee.bo.Categorie" %>
<%@ page import="fr.eni.ProjetJee.bo.ArticleVendu" %>
<%@page import="java.util.ArrayList"%>
<span>Liste des Enchères</span>
<span>Filtre :</span>
<form action="/ProjetJee/" method="get">
<input name="nom" type="text">
<label for="categorie-select">Categorie:</label>
<select name="categorie" id="categorie-select">
    <option value="0">Toute</option>
    <%
		ArrayList<Categorie> categories= (ArrayList<Categorie>)request.getAttribute("categories");
		for (Categorie catgorie : categories) {
	%>
    	<option value="<%=catgorie.getNoCategorie() %>"><%=catgorie.getLibelle() %></option>
    <%
		}
    %>
</select>
<div>
      <input type="radio" id="radioAchat" name="radioAchat" value="achat" class="boutonRadio" checked>
      <label for="radioAchat">Achat</label>
      <div>
      	<div>
	      	<input type="checkbox" id="ouvertes" name="ouvertes" checked>
	      	<label for="ouvertes">Enchère ouvertes</label>
      	</div>
      	<div>
	      	<input type="checkbox" id="mesEnchere" name="mesEnchere">
	      	<label for="mesEnchere">Mes enchères</label>
      	</div>
      	<div>
	      	<input type="checkbox" id="mesEnchereReporter" name="mesEnchereReporter">
	      	<label for="mesEnchereReporter">Mes enchères remportées</label>
  	  	</div>
  	  </div>

      <input type="radio" id="radioVente" name="radioAchat" value="vente" class="boutonRadio">
      <label for="radioVente">Mes Ventes</label>
      
      <div>
      	<div>
	      	<input type="checkbox" id="mesVenteCours" name="mesVenteCours" disabled="disabled">
	      	<label for="mesVenteCours">Mes ventes en cours</label>
      	</div>
      	<div>
	      	<input type="checkbox" id="mesVenteDebutees" name="mesVenteDebutees" disabled="disabled">
	      	<label for="mesVenteDebutees">Mes ventes non débutées</label>
      	</div>
      	<div>
	      	<input type="checkbox" id="mesVentetTerminees" name="mesVentetTerminees" disabled="disabled">
	      	<label for="mesVentetTerminees">Mes ventes terminées</label>
  	  	</div>
  	  </div>
</div>
<button value=true name="rechercher" >Rechercher</button>
</form>
<div class="fiche-produits">
	<%
		ArrayList<ArticleVendu> articles= (ArrayList<ArticleVendu>)request.getAttribute("articles");
		for (ArticleVendu article : articles) {
	%>
		<div class="article">
		<img alt="" src="<%= article.getPhoto() %>">
		<span><%= article.getNomArticle() %></span>
		<span>Prix : <%= article.getPrixDeVente() %></span>
		<span>Fin de l'enchère: <%= article.getDateFinEncheres() %></span>
		<span>Vendeur : <%= article.getUtilisateur().getPseudo() %></span>
		</div>
    <%
		}
    %>
</div>
<jsp:include page="footer/footer.jsp" />