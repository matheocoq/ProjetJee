<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page import="fr.eni.ProjetJee.bo.ArticleVendu"%>
<%@ page import="fr.eni.ProjetJee.bo.Enchere"%>

<%@ page import="java.util.List"%>
<jsp:include page="header/headerConnecter.jsp" />

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container">
	<div class="row">
		<div class="col-3">
			<img alt="" src="resources/imgTestNouvelleVente.jpg">
			<p>IMAGE</p>
		</div>
		<div class="col-9">
		
			<h5 style="text-align: center">Détail vente</h5>
			
			<p>${ article.getNomArticle() }</p>
			
			<form action="<%= request.getContextPath() %>/DetailVente" method="post">
				
			</form>
		</div>
	</div>
</div>
<jsp:include page="footer/footer.jsp" />