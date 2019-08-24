var CompanyForm = React.createClass({
    
	getInitialState: function() {		
		return {companys: [], company:{ id:null, name:'', town:'', address:'', cid:''}};
	},
	
    componentDidMount: function() {			
			this.serverRequest = $.get(this.props.source, function(result) {																
			this.setState({companys : result, company: result[0]});			
			}.bind(this));									
	},	
    
	componentWillUnmount: function() {
		this.serverRequest.abort();
	},	
    cancel: function() {
        $("#companyDialog").modal("hide");  
    },
	add: function() {
	    
	    document.getElementById("companyForm").reset();

		this.setState({company:{ id:null, name:'', town:'', address:'', cid:''}});   
		$("#companyDialog").modal("show");  
	},

	save: function() {
		var url = this.props.source;
		var method = "POST";

		if(this.state.company.id!=null) {
			url += this.state.id;
			method = "PUT";
		}		
		else {
			this.state.companys.push(this.state.company);
			this.setState({companys:this.state.companys});			
		}

		$.ajax ({
                    url: url,
                    type: method,
                    data: JSON.stringify(this.state.company),
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    success: function(){
                        console.log("OK");
  						$("#companyDialog").modal("hide");
                    }
                });
		},	
	valueChange: function() {
	            
		this.state.company[event.target.name] = event.target.value;				
		this.setState({ company: this.state.company });	

	},	
	openDialog: function(event) {		 
	
		 this.setState({company:this.state.companys[event.target.parentElement.id]});	

		$("#companyDialog").modal("show");
	},
	eachItem: function(company, i) {
		return (<tr id={i} onClick={this.openDialog} ><td>{company.name}</td><td>{company.address}</td><td>{company.town}</td><td>{company.cid}</td></tr>)
	},
	render: function() {

		return (  <div>
		            <div className="ui modal"  id="companyDialog">
		            										 		
								<i className="close icon"></i>	
					 			<div className="ui header inverted segment">Company</div>

					 <div className="ui content">				
		            
		            	<form className="ui form" id="companyForm">
		 					 			
						           	<div className="field six wide">
								 	  <label for="name">Name</label><input type="text" name="name" placeholder="Name" onChange={this.valueChange} value={this.state.company.name}/>        
								 	  </div>
								 <div className="two fields">	  	
									<div className="field six wide">
							           <label for="address">Address</label><input name="address" type="text" onChange={this.valueChange}  value={this.state.company.address}/>
							         </div>
						
									<div className="field six wide">
							           <label for="town">Town</label><input name="town" type="text" onChange={this.valueChange}  value={this.state.company.town}/>
							         </div>
								 </div>

						           <div className="field six wide">
						           		<label for="vat">VAT</label><input type="text" name="cid" onChange={this.valueChange}  value={this.state.company.cid}/>
						           </div>			         
			            
			            <div className="ui footer">
	 						 <div className="ui primary button" onClick={this.save}>Save</div>				            
	                         <div  className="ui primary button" onClick={this.cancel}>Cancel</div>		        
    </div>
			              </form>
			          
                      </div>
                      </div>

                
                    <div className="ui content">
                    
				<button className="ui primary button" onClick={this.openDialog}>Open</button>
                    
                    <div  className="ui primary button" onClick={this.add}>Add</div>
                    
                    
                        <table className="ui selectable celled table">

		 <thead>
    		<tr>
      			<th>Name</th>
      			<th>Address</th>
      			<th>Town</th>
      			<th>VAT</th>
    		</tr>
  		</thead>
  		<tbody>
			{this.state.companys.map(this.eachItem)}
		 </tbody>
    </table></div>	

				</div>

		         )}
	       });

    ReactDOM.render(<div><CompanyForm source="http://localhost:8080/erp/company/"/></div>, document.getElementById("container"));

