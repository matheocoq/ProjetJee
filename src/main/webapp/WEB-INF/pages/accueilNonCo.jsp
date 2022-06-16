<jsp:include page="header/header.jsp" />
<%@ page import="fr.eni.ProjetJee.bo.Categorie" %>
<%@ page import="fr.eni.ProjetJee.bo.ArticleVendu" %>
<%@page import="java.util.ArrayList"%>
<span class="titre-accueil">Liste des Enchères</span>
<div class="container" >
<span class="filtre-titre">Filtre :</span>
<form action="/ProjetJee/" method="get" class="form-filtre">
<div style="display: inline-block;margin-top: 35px;">
<input class="bandeau-recherche" name="nom" type="text"  placeholder="Recherche par mots-clé">
<label style="margin-left: 80px;"for="categorie-select">Categorie:</label>
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
</div>
<button style="float: right; " class="bouton" value=true name="rechercher">Rechercher</button>
</form>
<div class="fiche-produits">
	<%
		ArrayList<ArticleVendu> articles= (ArrayList<ArticleVendu>)request.getAttribute("articles");
	out.println();
	if(articles.size()>0){
		
		for (ArticleVendu article : articles) {
		
	%>
		<div class="article">
		<div>
		<img class="article-image" alt="" src="${pageContext.request.contextPath}/resources/logoENI.svg">
		</div>
		<div>
		<span class="article-nom"><%= article.getNomArticle() %></span>
		<span>Prix : <%= article.getPrixDeVente() %></span>
		<span style="margin-bottom: 15px;">Fin de l'enchère: <%= article.getDateFinEncheresFormat() %></span>
		<span>Vendeur : <%= article.getUtilisateur().getPseudo() %></span>
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