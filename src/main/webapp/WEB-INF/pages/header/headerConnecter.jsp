<%@ page import="fr.eni.ProjetJee.bo.Categorie" %>
<%@ page import="fr.eni.ProjetJee.bo.ArticleVendu" %>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>A Web Page</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/pages/css/style.css" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
</head>
<body>
<div>
<span>ENI-Enchère</span>
<a>Enchère</a>
<a>Vendre un article</a>
<a>Mon profil</a>
<a href="/Projet_ENI-Encheres/Deconnexion">Déconexion</a>
</div>