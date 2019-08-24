<script>

var account_form = new IbsTable({
    el:"#account",
    template:"#account_form",       
    data: {  title:"Konta",
             confirm_text:"Želite li obrisati konto ?!",
             country_id:null,
             countries:[],
             data_rows:[],
             default_row:{ id:null,  code:"", name:"", active:false },
             table_width:"",
             headers:"Konto:12:center:left,Opis konta:45:center:left",
             validations: { code: "isEmpty", name: "isEmpty,isMinLen:5" },    
             columns:"code, name",
             data_url:"/erp/account",
             name_search_url:"/erp/account/name/",
             code_search_url:"/erp/account/code/",  
             form_id:"#data_form",
             menu_id:"data_menu",
          },      
    components: { IbsCombo:IbsCombo },
});

</script>

<script id="account_form" type="text/ractive">
<div style="padding-top:10px;align:center;">
{{>dynamic_partial}}</div>
        <div class="ui modal" id="data_form" style="left:30%;padding:15px;margin:auto;top:15em;width:500px;">
            <h3>Konto</h3>
            <div class="ui form">
                    {{#data_rows[selected]}}                        
                            <div class="ten wide field">
                            <label for="code">Konto</label><input name="code" type="text" value={{code}} twoway>
                            </div>

                            <div class="ui twenty wide field">
                            <label for="name">Opis</label><input name="name" type="text" value={{name}} twoway>
                            </div>

                            <div class="ten wide field">
                            <label for="active">Aktivno</label>
                            <input name="active" type="checkbox" checked={{active}} twoway>                            
                            </div>
                        
                    {{/}}
            <div>
                <button class="ui primary button" on-click='@this.onClickSaveClose()'>Spremi</button>
                <button class="ui primary button" on-click='@this.onClickCancel()'>Odustani</button>
            </div>
            </div>
        </div>

</script>
<div id="account"></div>
