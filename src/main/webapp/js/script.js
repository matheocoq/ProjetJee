$(".boutonRadio").click(function() {
	console.log(this)
	var value = $(this).attr('value');
	if(value=="vente"){
		
		$("#ouvertes").attr('checked', false)
		$("#mesEnchere").attr('checked', false)
		$("#mesEnchereReporter").attr('checked', false)
		$("#ouvertes").attr('disabled', true)
		$("#mesEnchere").attr('disabled', true)
		$("#mesEnchereReporter").attr('disabled', true)
		$("#mesVenteCours").attr('disabled', false)
		$("#mesVenteDebutees").attr('disabled', false)
		$("#mesVentetTerminees").attr('disabled', false)
	}else{
		$("#mesVenteCours").attr('checked', false)
		$("#mesVenteDebutees").attr('checked', false)
		$("#mesVentetTerminees").attr('checked', false)
		$("#mesVenteCours").attr('disabled', true)
		$("#mesVenteDebutees").attr('disabled', true)
		$("#mesVentetTerminees").attr('disabled', true)
		$("#ouvertes").attr('disabled', false)
		$("#mesEnchere").attr('disabled', false)
		$("#mesEnchereReporter").attr('disabled', false)
	}
  	
});