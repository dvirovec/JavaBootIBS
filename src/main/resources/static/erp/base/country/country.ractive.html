<script>

var country_form = new IbsTable({
    el:"#country",
    template:"#country_form",
    onconfig:function() {
            $.get("/erp/currency", (data)=>{
                      this.set("currencies",data);
                    });
                },
    data: {  title:"Države",
             confirm_text:"Želite li obrisati državu ?!",             
             currencies:[],
             data_rows:[],
             default_row:{ id:null, name:"", code:"", currency:"" },
             table_width:"",
             headers:"Oznaka:5:center:center,Naziv:50:center,Valuta:5:center:center",
             columns:"code,name,currencyCode(currency.id)",
             validations: { name: "isEmpty,isMinLen:3", code:"isEmpty,isLen:2",currency:"isEmpty",  },
             data_url:"/erp/country",
             form_id:"#data_form",
             menu_id:"data_menu",      
             currencyCode: function(id) {
              if(id!=null)  
                return this.get("currencies").find((t)=> { return t.id === id;}).currencyCode;
             },                   
                },
    components: { IbsCombo:IbsCombo },
});

</script>

<script id="country_form" type="text/ractive">
<div style="padding-top:10px;align:center;">
{{>dynamic_partial}}</div>

        <div class="ui modal" id="data_form" style="padding:15px;margin:auto;top:15em;width:400px;">
            <h3>Poduzeće</h3>
            <div class="ui form">
                    {{#data_rows[selected]}}
                        <div class="ui forteen wide field">
                        <label for="name">Naziv</label><input name="name" type="text" value={{name}} twoway>
                        </div>
                        <div class="ui four wide field">
                        <label for="code">Oznaka</label><input name="code" type="text" value={{code}} twoway>
                        </div>                        

                        <div class="two fields">                        

                            <div class="six wide field">
                            <label for="code">Iznos dnevnice</label><input name="day_amount" type="number" class="number" value={{day_amount}} twoway>
                            </div>    
                        
                            <div class="four wide field">
                               <label for="currency">Valuta</label>
                               <IbsCombo id="currency" description_field="currencyCode" data_url="/erp/currency" value={{currency}} twoway></IbsCombo>
                            </div>        

                        </div>                    
                    {{/}}
            <div>
                <button class="ui primary button" on-click='@this.onClickSaveClose()'>Spremi</button>
                <button class="ui primary button" on-click='@this.onClickCancel()'>Odustani</button>
            </div>

            </div>

        </div>

</script>
<div id="country"></div>
