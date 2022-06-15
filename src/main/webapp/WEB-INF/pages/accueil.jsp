<jsp:include page="header/header.jsp" />
<%@ page import="fr.eni.ProjetJee.bo.Categorie" %>
<%@ page import="fr.eni.ProjetJee.bo.ArticleVendu" %>
<%@page import="java.util.ArrayList"%>
<span class="titre-accueil" >Liste des Enchères</span>
<div class="container" >
<span class="filtre-titre">Filtre :</span>
<form action="/ProjetJee/" method="get" class="form-filtre">
<div style="display: inline-block;margin-top: 35px;">
<input style="display:inline-block;" class="bandeau-recherche" name="nom" type="text"  placeholder="Recherche par mots-clé">
<label style="margin-left: 94px;"for="categorie-select">Categorie:</label>
<select style="border:0px;" name="categorie" id="categorie-select">
    <option value="Toute">Toute</option>
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
	<div style="display: inline-block;">
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
	</div>
	<div style="display: inline-block;margin-left: 80px;">
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
</div>
</div>

<button style="float: right; margin-top: 48px;" class="bouton" value=true name="rechercher">Rechercher</button>
</form>
<div class="fiche-produits">
	<%
		ArrayList<ArticleVendu> articles= (ArrayList<ArticleVendu>)request.getAttribute("articles");
	    if(articles.size()>0){
		for (ArticleVendu article : articles) {
		
	%>
		<div class="article">
		<div>
		<img class="article-image" alt="" src="${pageContext.request.contextPath}/resources/logoENI.svg">
		</div>
		<div>
		<a class="article-nom" href="${pageContext.request.contextPath}/DetailVente?article=<%= article.getNoArticle() %>"><%= article.getNomArticle() %></a>
		<span style="margin-top: 15px;">Prix : <%= article.getPrixDeVente() %></span>
		<span style="margin-bottom: 15px;">Fin de l'enchère: <%= article.getDateFinEncheresFormat() %></span>
		<span style="display: inline-block;">Vendeur : </span><a href="${pageContext.request.contextPath}/AfficherProfil?user=<%= article.getUtilisateur().getNoUtilisateur() %>"><%= article.getUtilisateur().getPseudo() %></a>
		
		</div>
		</div>
    <%
		}
	    }
	    else{
	    	
	    
    %>
    <span>Aucun article trouvée</span>
    <%
		}
    %>
</div>
</div>
<jsp:include page="footer/footer.jsp" />