<script>

var company_form = new IbsTable({
    el:"#currency",
    template:"#currency_form",
    onconfig:function() {

    },
    oninit: function() {

        this._super();
        
    },
    data: {  title:"Valute",
             confirm_text:"Želite li obrisati valutu ?!",                          
             data_rows:[],
             default_row:{ id:null,  currencyNum:0, currencyCode:"", currencyName:"", country:null },
             table_width:"",
             headers:"Oznaka:5:center:center,Šifra:5:center:center,Naziv valute:50:center:",
             columns:"currencyCode, currencyNum, currencyName",
             validations: { currencyCode: "isEmpty,isMinLen:3", currencyNum:"isEmpty,isLen:3,isNum",currencyName:"isEmpty,isMinLen:3" },      
             data_url:"/erp/currency",
             form_id:"#data_form",
             menu_id:"data_menu",
                },
    components: { IbsCombo:IbsCombo },

});

</script>

<script id="currency_form" type="text/ractive">
<div style="padding-top:10px;align:center;">
{{>dynamic_partial}}</div>
        <div class="ui modal" id="data_form" style="padding:15px;margin:auto;top:15em;width:400px;">
            <h3>Valuta</h3>
            <div class="ui form">
                    {{#data_rows[selected]}}
                         <div class="two fields">
                            <div class="four wide field">
                            <label for="currencyCode">Šifra</label><input id="currencyCode" name="currencyCode" type="text" value={{currencyNum}} twoway>
                            </div>
                            <div class="ui four wide field">
                            <label for="currencyCode">Oznaka</label><input id="currencyCode"name="currencyCode" type="text" value={{currencyCode}} twoway>
                            </div>
                        </div>
                        <div class="two fields">
                            <div class="ui ten wide field">
                            <label for="currencyName">Naziv valute</label><input id="currencyName" name="currencyName" type="text" value={{currencyName}} twoway>
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
<div id="currency"></div>
