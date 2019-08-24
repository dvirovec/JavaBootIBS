var CompanyTable =  React.createClass({
   
    getInitialState: function() { 
		return { rows: []};		
	},
	componentDidMount: function() {
			
			this.serverRequest = $.get(this.props.source, function(result) {											

				this.setState({ rows : result});				

			}.bind(this));		
	},	
	
	componentWillUnmount: function() {
		this.serverRequest.abort();
	},
        
	eachItem: function(row, i) {
		return (<tr onClick={this.openDialog} key={i} index={i}><td>{row.name}</td><td>{row.address}</td><td>{row.town}</td><td>{row.cid}</td></tr>)
	},
	render: function() {

		console.log(this.state.rows);

		return (<div><table className="ui selectable celled table">
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
		             </table></div>)
	}
});