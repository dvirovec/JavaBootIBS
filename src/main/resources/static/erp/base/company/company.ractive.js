
var table_name = "companies"

var cdata = new Ractive({
	el:'#app',
	template:'#template',	
	data: {
			companies: [],
			delete_disabled:"disabled",
			append_mode:false,
			selected:-1,
	},  
			
   add: function() {
       	  this.get(table_name).push({ id:null, name:"", address:"", town:"", cid:""} );
          this.update(table_name);
       	},

	upd : function(i, data) {
		let rows = this.get(table_name);
		rows[i] = data; 
       	this.set(table_name, rows);
       	},


   del: function(index) {
    	delete ((this.get(table_name))[index]);    	
    },   	

});


$.get("/erp/company", function(data) {
	 		cdata.set(table_name, data);
	 	});

var onRowDblClick = (object) => {

		console.log(cdata.get(table_name)[object.rowIndex]);

 	  cdata.set("selected", object.rowIndex);
      cdata.set("append_mode",false);
      
      $('#data_form').show();
};


var onClickAdd = ()=> {

    cdata.set('append_mode',true);

    cdata.add();

    cdata.update(table_name);

    cdata.set("selected", cdata.get(table_name).length-1);

    $('#data_table').find('tr').click(function() {
        cdata.set("selected", this.rowIndex);
    });

    $('#data_form').show();

    };

 var onClickSaveClose = ()=> {

 	let data = cdata.get('companies')[cdata.get("selected")];

 	if(data.id===null) { 

 	$.ajax ({url: "/erp/company",
                    type: "POST",
                    data: JSON.stringify(data),
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    success: function(data){
                    		cdata.upd(cdata.get("selected"), data);
                    			$('#data_form').hide();	                        
                    }
                });
 }
 else {
 	$.ajax ({url: "/erp/company/"+data.id,
                    type: "PUT",
                    data: JSON.stringify(data),
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    success: function(data){
                    		cdata.upd(cdata.get("selected"), data);
                    			$('#data_form').hide();	                        
                    }
                });	
 }
 };      

 var onClickCancel=()=>{

	  if(cdata.get("append_mode")) {
	  	cdata.del(cdata.get("selected"));
	  };

	  cdata.set("append_mode", false);

	  $("#data_form").hide();
};

var selectRow = (object) => {	

	cdata.set("delete_disabled", 
		$("#data_table").find("input:checked").length===0 ? "disabled": "");			
};

var selectAll = (object)=> {
	
  	$(".select_row").prop("checked", object.checked);  
  	  	
  	cdata.set("delete_disabled", $("#data_table").find("input:checked").length===0 ? "disabled": "");
};

var selectDataRow = (object)=> {
	cdata.set("selected", object.rowIndex);
};

var onClickDelete = ()=> {
    	
  var rows=[]; 

  $('#data_table tr').each((i, row)=>{  		
  		if($(row).find("input:checked").length==0) {  		
  			rows.push(cdata.get("companies")[i]);  						  			
  		}				
  		else {

  			let id = (cdata.get("companies")[i]).id;

  			$.ajax ({url: "/erp/company/"+id,
                    type: "DELETE",
                    data:  JSON.stringify(cdata.get("companies")[i]),     
                    contentType: "application/json; charset=utf-8",                                  
                    success: function(data){
                    }
                });
  		}
  });
 
  cdata.set(table_name, rows);
  cdata.update(table_name);

  $("#select_all").prop("checked", false);
  $(".select_row").prop("checked", false);  

  cdata.set("delete_disabled", "disabled");
};
