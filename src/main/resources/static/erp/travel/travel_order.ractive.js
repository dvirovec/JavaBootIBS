

  var tdata = new Ractive({
       	el:'#app',
       	template: '#template',      

          data: {
            f2dec: function(value) {
                return (Number(value)).formatMoney(2,',','.');
              },
            town_name: function(id) {
              if(id!=null)  
              return this.get("towns").find((t)=> { return t.id === id;}).name;
        },
            order:{
                company_id:null,                
                town_id:null,
                country:null,
                car_km: 0.00,
                employee:null,
                routes: [],                
                from_date: getCurrentDate(),
                to_date: getCurrentDate(),
                hours:0.00,
                travel_days:0.00,
                trawel_desc:'',
                mailage:{ start_km:0, end_km:0, total_km:0, total_km_routes:0, total_km_amount:0.00, },
                costs:[],                
                total_costs:0.00 },

          route_delete_disabled:"disabled",
          cost_delete_disabled:"disabled",

          selected_cost:-1,
          selected_route:-1,

          cost_append_mode:false,
          route_append_mode:false,

          countries: [],
          towns:[],
          employees:[],
          country_towns:[],
          
          ex_rates: [{currency:'HRK', rate:1}, {currency:'EUR',rate:7.47} ],
          means_of_trans: [{id:0,name:'Osobno vozilo'}, {id:1,name:'Javni prijevoz'}]          
        },       	

       	get_ex_rate: function(country_code) {

       		var curr_code = this.get("country").find((c)=> {
       				return c.code === country_code;
       			}).currency;

       		var ex_rate = this.get("ex_rates").find((r)=> {
       				return r.currency === curr_code;
       		}).rate;

       		return ex_rate;
       	},

       	select_all_costs(select) {

          let cnt = 0;

       		tdata.get("order.costs").forEach((e) => {
       			cnt++;
            e.checked = select;
       		});

            if(cnt>0)
              this.update("order.costs");

            return cnt;
       	},

        select_all_routes(select) {

          let cnt = 0;

          tdata.get("order.routes").forEach((e) => {
            cnt++;
            e.checked = select;
          });

          if(cnt>0)
            this.update("order.routes");

            return cnt;
        },

       	calc_costs() {

    			   tdata.get("order.costs").forEach((e) => {
    				    e.amount =  (Number(e.qty) * Number(e.price)).toFixed(2);
    				    e.qty = Number(e.qty).toFixed(2);
    					e.price = Number(e.price).toFixed(2);
    				});

    	    		this.update("order.costs");

    				var total_costs = 0.00;
    				tdata.get("order.costs").forEach((e) => {
        				total_costs += Number(e.amount);
    			   });
    			 
             tdata.set("order.total_costs", total_costs);
       	},

       	add_cost: function() {
       		this.get("order.costs").push({checked:false,desc:"", qty: 1.00, price:0.00, amount:0.00, currency_code:'HRK' });
       		this.update("order.costs");
       	},

       	remove_cost: function(index) {
       				this.get("order.costs").splice(index,1);
              this.update("order.costs");
       	},

       	remove_selected_costs: function() {
       		let costs = [];
       		this.get("order.costs").forEach((e) => {
       			if(!e.checked) costs.push(e);
       		});
       		this.set("order.costs",costs);
       	},       	

        remove_selected_routes: function() {
       		let routes = [];
       		this.get("order.routes").forEach((e) => {
       			if(!e.checked) routes.push(e);
       		});
       		this.set("order.routes",routes);
       	},

       	add_route: function() {
       		this.get("order.routes").push({checked:false, town_id0:null, town_name0:"",  town_id1:null, town_name1:"", km:0, trans_type_id:0, amount:0.00});
          this.update("order.routes");
       	},

        remove_route: function(index) {
              this.get("order.routes").splice(index,1);
              this.update("order.routes");
        },

        calc_routes: function() {          

          let total_km = 0, total_amount = 0;

          tdata.get("order.routes").forEach((e) => {              

              e.amount = e.km * tdata.get("order.car_km");

              total_km += e.km;
              total_amount += e.amount;

          });

            this.set("order.mailage.total_km_routes", total_km);
            this.set("order.mailage.total_km_amount", total_amount);

            this.update("order.mailage.total_km_routes");
            this.update("order.mailage.total_km_amount");

            this.update("order.routes");
        },

        set_company: function(id) {
          if(id!=null) {
            order.company_id = id;
            this.update("order.company_id");  
          }
        },
        set_town: function(id) {

          if(id!=null) {
            order.town_id = id;
            this.update("town_id");  
          }
        },

        
       	set_country: function(id) {

       		if(id!=null) {
	       		var country_code = this.get("towns").find((t)=> {
	       				return t.id === id;
	       			}).country_code;
	    		
            order.country = country_code;

	            this.update("order.country");
	           }
            },       

       });

$.get(host+"/erp/company", function( data ) {  
  
  if(data!=null) {
  
    tdata.set_company(data[0].id);    

    let company_id = data[0].id;

        $.get(host+"/erp/travel_order_config/"+company_id, function( data ) {
            if(data!=null)
              tdata.set("order.car_km", data.car_km);
              tdata.update("order.car_km");
              tdata.calc_routes();
         });
  }
 });

 $.get(host+"/erp/country", function( data ) {
 	  tdata.set("countries", data);
 });

$.get(host+"/erp/town", function( data ) {
    tdata.set("towns", data);
    tdata.set("country_towns", data);
 });

$.get(host+"/erp/employee", function( data ) {
 	  tdata.set("employees", data);
 });

var format_decimal = (value)=> {
          return (Number(value)).formatMoney(2,'.',',');
        }

var loadExternal=()=>{

	$('#order_form').submit();
}
//-------------------------------------------------
var setCheckedCost = (object)=>{

	if(object.checked) {

	        tdata.set("cost_delete_disabled","");

			//$("#remove_cost_button").show();
	}

	if(!object.checked) {

		let chk = 0;

		 tdata.get("order.costs").forEach((e) => {
		 	    if(e.checked) chk += 1;
			});

		 if(chk<=1)
		    tdata.set("cost_delete_disabled","disabled");
	}
}

var selectAllCosts = (object)=> {

	let select = object.checked;  
    
  tdata.set("cost_delete_disabled",
    $('#cost_table').find("input:checked").length === 0 ? "disabled": "");

}

var selectAllRoutes = (object)=> {

  let select = object.checked;    
  
  tdata.set("route_delete_disabled",
    $('#route_table').find("input:checked").length === 0 ? "disabled":"");
  
  }

var calcCosts = ()=> {
	
  tdata.calc_costs();

	$("#cost_form").hide();

	$('#cost_table').find('tr').dblclick(function() {
			tdata.set("selected_cost", this.rowIndex);
      $('#cost_form').position().top = $("#cost_table").position().top;
			tdata.set("cost_append_mode",false);
			$('#cost_form').show();
		});
}

var onCostEditCancel=()=>{

  if(tdata.get("cost_append_mode")) {
  	tdata.remove_cost(tdata.get("selected_cost"));
  }

  tdata.set("cost_append_mode", false);

  $("#cost_form").hide();
}

var onDeleteCosts = ()=> {
	tdata.remove_selected_costs();
	$("#select_all_costs").prop("checked", false);
  tdata.set("cost_delete_disabled","disabled");
}

var onClickAddCost = ()=> {

		tdata.set("cost_append_mode",true);

		tdata.add_cost();
    
    tdata.update("order.costs");
		
    tdata.set("selected_cost",tdata.get("order.costs").length-1);

		$('#cost_table').find('tr').click(function() {      
			tdata.set("selected_cost", this.rowIndex);
		});

		$('#cost_form').show();
      };
//---------------------------------------------
var setCheckedRoute = (object)=>{

  if(object.checked) {
          tdata.set("route_delete_disabled","");      
  }

  if(!object.checked) {

    let chk = 0;

     tdata.get("order.routes").forEach((e) => {
          if(e.checked) chk += 1;
      });

     if(chk<=1)
        tdata.set("route_delete_disabled","disabled");
  }
}

var onClickAddRoute = ()=> {

    tdata.set('route_append_mode',true);

    tdata.add_route();

    tdata.update("order.routes");

    tdata.set("selected_route", tdata.get("order.routes").length-1);

    $('#route_table').find('tr').click(function() {
        tdata.set("selected_route", this.rowIndex);
    });

    $('#route_form').show();

      };

var calcRoutes=()=>{

  tdata.calc_routes();


  $('#route_table').find('tr').dblclick(function() {

      tdata.set("selected_route", this.rowIndex);
      tdata.set("route_append_mode",false);

      $('#route_form').position().top = $("#route_table").position().top;
      $('#route_form').show();
    
    });


  $("#route_form").hide();  
}

var onDeleteRoute = ()=> {
  tdata.remove_selected_routes();
  $("#select_all_routes").prop("checked", false);
  tdata.set("route_delete_disabled","disabled");
}

var onRouteEditCancel=()=>{

  if(tdata.get("route_append_mode")) {
    tdata.remove_route(tdata.get("selected_route"));
  }

  tdata.set("route_append_mode", false);

  $("#route_form").hide();
}

//---------------------------------------------

var onClickCalc = ()=>{

			var hours = dateDiffHours(new Date(tdata.get("order.from_date")),new Date(tdata.get("order.to_date")));

			hours = Math.round((hours * 100))/100;

			tdata.set("order.hours", hours);

			days = Math.floor(hours / 24);

			hours = hours%24;

			if(hours >= 12)  days = days + 1;

			if(hours>8 && hours < 12) days = days + .5;

      tdata.set("order.travel_days",days);

			calculateKm();

      };

var calculateKm =()=> {

  let start_km = tdata.get("order.milage.start_km");
  let end_km = tdata.get("order.milage.end_km");

  if( ( !isNaN(start_km) && !isNaN(start_km) ) && end_km >  start_km) {
      tdata.set("order.milage.total_km", tdata.get("order.milage.end_km")-tdata.get("order.milage.start_km"));
    }
};

tdata.observe('order.milage.end_km', function() {
  calculateKm();
});

tdata.observe('order.town_id', function(newId, oldId, keypath) {
	
});

tdata.observe('order.country', function(newCountryCode, oldCountryCode, keypath) {

	 $.get(host+"/erp/town/country/"+newCountryCode, function( data ) { 		     

         if(data.length >0) {            
            tdata.set("country_towns", data);               
            tdata.set_town(data[0].id);                        
       }

 		});
});

tdata.observe('order.travel_desc', function(newValue, oldValue, keypath) {

    //console.log(newValue);

	if(!newValue) {
		//alert("Prazno");
	}

});

$(document).ready(function() {

    $('.ui.dropdown').dropdown();

})