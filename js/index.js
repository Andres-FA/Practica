/** index.js **/

var server = 'http://localhost:8080/ProyectoPizzaAmericana/';

$(function () 
{
	$('#txtPassword').keypress(function (event) {
            if (event.which == 13) {
                autenticar();
                //return false; only if needed
            }
        });

});

function autenticar()
{
	var usuario =  $('#txtUsuario').val();
	var password =  $('#txtPassword').val();
	console.log("hola");
	$.ajax({ 
	    				url: server + 'GetIngresarAplicacion?txtUsuario=' + usuario + "&txtPassword=" + password, 
	    				dataType: 'text',
	    				type: 'post', 
	    				async: false, 
	    				success: function(data){ 
	    						if(data == 'OK')
	    						{
	    							location.href ="http://localhost:8080/ProyectoPizzaAmericana/Pedidos.html";
	    						}
	    						else
	    						{
	    							alert(data);
	    							$('#txtPassword').val('');
	    						}
							} 
						});
}

