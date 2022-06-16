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

function getDateTime() {
	let pTime = document.createElement('p');
	pTime.classList.add('txtTime');
	let divTime = document.getElementsByClassName('time')
	let oldPTime = document.getElementsByClassName('txtTime')
	let txtTime = document.createTextNode(moment().format('DD-MM-YYYY HH:mm:ss'))
	pTime.appendChild(txtTime)
	divTime[0].removeChild(oldPTime[0])
	divTime[0].appendChild(pTime)
}

getDateTime()
	
window.setInterval(function () {
	getDateTime()
}, 500);


let Debut = document.querySelector("#dateDebut")
let Fin = document.querySelector("#dateFin")

if (Debut != null) {
	Debut.addEventListener('change', (e) => {
	  	let dateDebut = moment(Debut.value)
		Fin.value = moment(dateDebut).add(1, 'days').format('YYYY-MM-DDTHH:mm')
		Fin.min = moment(dateDebut).add(1, 'days').format('YYYY-MM-DDTHH:mm')
	});
}



