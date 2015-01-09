$(function(){
	$("#add_group_name").on("click",addGroupeName);
});

function addGroupeName(){
	var name = $("#NewNomGroupContact").val();
	if(name==""){
		alert("Attention champ invalide !");
		return;
	}
	$('#NameGroupContact').append($('<option>', {
	    value: name,
	    text: name
	}));
}