<jsp:include page="header/header.jsp" />
	<div class="container">
		
		
			<h3 class="text-center mt-5">S'authentifier</h3>
			<p>Afin de pouvoir faire des enchères sur ce site, vous devez vous authentifier. Si vous n'en possédez pas, vous devez créer un compte</p>
		
			
			
			<div class="row mt-4">
				<div class="col-7 shadow p-3 mb-5 bg-body rounded col-connexion">
				${not empty errorConnexion ? "<div class='alert alert-danger mb-4' role='alert'>Authentification incorrect !!</div>" : null}
					<form action="" method="post">
						<h4>Connectez-vous !</h4>
						<div class="form-group">
							<label>Votre pseudo ou votre email*:</label>
							<input class="form-control" name="login" type="text">
						</div>
						 
						<div class="form-group mt-2">
							<label>Votre mot de passe*:</label> 
							<input class="form-control" name="mdp" type="password"> 
						</div>
						
						<!-- <div class="form-group mt-2">
							<input class="form-check-input" name="souvenir" type="checkbox"> 
							<label>Se souvenir de moi</label>
						</div>
						
						<div class="mt-2">
							<a>Mots de passe oublié</a>
						</div> -->
							
						<button class="btn btn-green my-3">Connexion</button>
					</form>
				</div>
				<div class="col-4 ms-3 col-pasDeCompte">
					<h4>Pas de compte ?</h4>
					<a class="ms-3 text-decoration-none fw-bold green" href="<%= request.getContextPath() %>/inscription">Crée un compte</a>
				</div>
			</div>
	
	</div>
<jsp:include page="footer/footer.jsp" />