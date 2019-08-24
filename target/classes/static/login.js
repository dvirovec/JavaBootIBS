

var login=(username, password, callback) => {

   $.get( "/erp/login/"+username+"/"+password, (user)=>{

   			 	 	  setCookie("orbis.erp.login", user.id, 1); 
   			 	 	  setCookie("orbis.erp.session_id", user.sessionId, 1); 
   			 	 	  console.log(user.sessionId);

                     active_user = user;

   						console.log("Login ok ...");
                     console.log(user);
                    
                     $.get( "/erp/user_detail/"+user.id,(user_detail)=>{
                        active_user.userDetails = user_detail;
                        callback(user);
                     });

   			}).fail(function() {
   			 	 	console.log("Login error ...");
   		});

}

var logout = () =>{

    $.get( "/erp/logout");

    deleteCookie("orbis.erp.login");
    deleteCookie("orbis.erp.session_id");
    console.log("Logged out...");

    active_user = null;

}
