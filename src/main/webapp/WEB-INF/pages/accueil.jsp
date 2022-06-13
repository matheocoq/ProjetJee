<jsp:include page="header/headerConnecter.jsp" />
<%@ page import="fr.eni.ProjetJee.bo.Categorie" %>
<%@ page import="fr.eni.ProjetJee.bo.ArticleVendu" %>
<%@page import="java.util.ArrayList"%>
<span>Liste des Ench�res</span>
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
      <input type="radio" id="radioAchat" name="radioAchat" value="achat" checked>
      <label for="radioAchat">Achat</label>
      <div>
      	<div>
	      	<input type="checkbox" id="ouvertes" name="ouvertes" checked>
	      	<label for="ouvertes">Ench�re ouvertes</label>
      	</div>
      	<div>
	      	<input type="checkbox" id="mesEnchere" name="mesEnchere">
	      	<label for="mesEnchere">Mes ench�res</label>
      	</div>
      	<div>
	      	<input type="checkbox" id="mesEnchereReporter" name="mesEnchereReporter">
	      	<label for="mesEnchereReporter">Mes ench�res remport�es</label>
  	  	</div>
  	  </div>

      <input type="radio" id="radioVente" name="radioAchat" value="vente">
      <label for="radioVente">Mes Ventes</label>
      
      <div>
      	<div>
	      	<input type="checkbox" id="mesVenteCours" name="mesVenteCours">
	      	<label for="ouvertes">Mes ventes en cours</label>
      	</div>
      	<div>
	      	<input type="checkbox" id="mesVenteDebutees" name="mesVenteDebutees">
	      	<label for="mesVenteDebutees">Mes ventes non d�but�es</label>
      	</div>
      	<div>
	      	<input type="checkbox" id="mesVentetTerminees" name="mesVentetTerminees">
	      	<label for="mesVentetTerminees">Mes ventes termin�es</label>
  	  	</div>
  	  </div>
</div>
<button>Rechercher</button>
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
		<span>Fin de l'ench�re: <%= article.getDateFinEncheres() %></span>
		<span>Vendeur : <%= article.getUtilisateur().getPseudo() %></span>
		</div>
    <%
		}
    %>
</div>
<jsp:include page="footer/footer.jsp" />