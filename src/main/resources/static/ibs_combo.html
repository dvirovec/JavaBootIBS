
<script>
 
IbsCombo = Ractive.extend({
	template:"<div>{{>dynamic_component}}</div>",
	isolated: false,			      
	oninit: function() {
		  
		this.observe("value", function(newValue, oldValue) { 
		  	
			if(newValue==null) return;

		  	this.set("id_value", newValue.id);
		  	this.update("id_value");			 

		  	$('#'+this._guid).val(newValue.id).change();
		  });

		 this.observe("id_value", function(newValue, oldValue) { 

		 	if(newValue==null) return;
		  	
		    if(this.get("data_rows")!=null)		
		  		this.set("value", this.get("data_rows").find((t)=> { return t.id === newValue;}));		  	
		  });
	   }, 
 	onconfig:function() {

		          let data_url = this.get("data_url");
				  let object = this;
                  		                                    		
		         $.get(data_url, function( data ) {  
		         
			         object.set("data_rows", data);		 		         	 

			         $('#'+object._guid)
	                        .dropdown({
	                         fullTextSearch: true
	                        });
		        	});
	},	
	data: { 
		 value_field:"",
	     description_field:"",
	     id_field:"",	
		 data_url:"",
		 value:null,
		 id_value:null,
		 data_rows:null,
    },	
 partials: {
 	dynamic_component:function() {
						        	
        	let description_field = (this.get("description_field") === "") ? "name": this.get("description_field");
        	let id_field = (this.get("id_field") === "") ? "id":this.get("id_field");
        
        	component = "<select class='ui search dropdown' id="+this._guid+" value={{id_value}} twoway> \
                 {{#data_rows}} \
                    <option value='{{"+id_field+"}}'>{{"+description_field+"}}</option> \
                    {{/}} \
                </select>";

          return component;
        },
 }
});

</script>
 
