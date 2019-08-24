<script>

var account_form = new IbsTable({
    el:"#posting",
    template:"#posting_form",
    data: {  title:"Knjiženja",
             confirm_text:"Želite li obrisati knjiženje ?!",
             country_id:null,
             countries:[],
             data_rows:[],
             default_row:{ id:null, posting_date:Date.now() , description:""},
             table_width:"",
             headers:"Datum:12:center:left,Opis knjiženja:65:center:left",
             validations: { posting_date: "isEmpty", description: "isEmpty,isMinLen:10" },
             columns:"localDate(posting_date), description",
             data_url:"/erp/posting",
             //name_search_url:"/erp/account/name/",
             //code_search_url:"/erp/account/code/",
             form_id:"#data_form",
             menu_id:"data_menu",
          },
    components: { IbsCombo:IbsCombo },
});

</script>

<script id="posting_form" type="text/ractive">
<div style="padding-top:10px;align:center;">
{{>dynamic_partial}}</div>
        <div class="ui modal" id="data_form" style="padding:15px;margin:auto;top:15em;width:400px;">
            <h3>Knjiženja</h3>
            <div class="ui form">
                    {{#data_rows[selected]}}                        
                            <div class="ten wide field">
                            <label for="posting_date">Datum</label><input name="posting_date" type="date" value={{posting_date}} twoway>
                            </div>
                            <div class="ui twenty wide field">
                            <label for="description">Opis knjiženja</label><input name="description" type="text" value={{description}} twoway>
                            </div>

                    {{/}}
            <div>
                <button class="ui primary button" on-click='@this.onClickSaveClose()'>Spremi</button>
                <button class="ui primary button" on-click='@this.onClickCancel()'>Odustani</button>
            </div>
            </div>
        </div>

</script>
<div id="posting"></div>
