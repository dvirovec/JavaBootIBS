<div style="margin:auto;padding:2em;width:450px;align:center;margin-top:12em;background-color:#efd392;">

	<div class="ui form">
	<h1 class="ui dividing header">Prijava korisnika</h1>
	    <div class="ui twelwe wide field">
	        <label for="username" >Korisničko ime</label>
	        <input type="text" id="username">
	    </div>

	    <div class="ui twelwe wide field">
	    	<label for="password" >Lozinka</label>
	        <input type="password" id="password">	        
	    </div>

		<div class="ui twelwe wide field">
			<label for="client">Radna baza</label>
			<input type="text" id="client" value="INFORBIS">
		</div>

		<div class="ui checkbox twelwe wide field">
	  		<input type="checkbox" name="save_login">
	  		<label>Spremiti prijavu</label>  		  		
		</div>    

		<div class="ui sixteen wide field" align="right">
			<div class="ui right pointing red basic label" style="visibility: hidden" id="error">
				Prijava nije uspijela !
		     </div>
		</div>
		<div class="ui sixteen wide field" align="right">
			<button class="ui primary button" id="reg_button">Registracija</button>
			<button class="ui primary button">Prekid</button>
			<button class="ui primary button" id="login_button">Prijava</button>
		</div>

	</div>
</div>
</div>

<script>

$(document).ready(()=>{

$("#reg_button").click(()=>{


data = {"name": document.getElementById("username").value, "password":document.getElementById("password").value, "client":document.getElementById("client").value };

console.log(data);

	  $.ajax ({url: "/erp/register/",
                        type: "POST",
                        data: JSON.stringify(data),
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        success: function(data){                                                   
                        }
                    });
             }
	);

$("#login_button" ).click(()=> {
								
			$.get( "/erp/login/"+$("#username").val()+"/"+$("#password").val()+"/"+$("#client").val(), (user)=>{

					$.get( "/erp/user_detail/"+user.id, (user_detail)=>{
							home_page.set("user_detail", user_detail);								
						});					
										
			 	 	  $("#error").css("visibility","hidden");		

			 	 	  home_page.set("user", user);								

			 	 	  setCookie("orbis.erp.login", user.id, 1); //  min			 	 	  
			 	 	  setCookie("orbis.erp.session_id", user.sessionId, 1); //  min			 	 	  
			 	 	  console.log(user.sessionId);

			 	 	  displayPage(page);

			}).fail(function() {
			 	 	$("#error").css("visibility","visible");
		});
			
  	});
	

});



</script>