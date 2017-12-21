<script>

var role_form = new IbsTable({
    el:"#role",
    template:"#role_form",
    onconfig:function() {
            
                },
    data: {  title:"Role",
             confirm_text:"Želite li obrisati rolu ?!",
             data_rows:[],
             default_row:{ id:null, name:"" },
             table_width:"",
             headers:"Naziv role:50:center",
             columns:"name",
             validations: { name: "isEmpty,isMinLen:3"},
             data_url:"/erp/role",
             form_id:"#data_form",
             menu_id:"data_menu",                        
                },
    components: { IbsCombo:IbsCombo },
});

</script>

<script id="role_form" type="text/ractive">
<div style="padding-top:10px;align:center;">
{{>dynamic_partial}}</div>

        <div class="ui modal" id="data_form" style="padding:15px;margin:auto;top:15em;width:400px;">
            <h3>Grad</h3>
            <div class="ui form">
                    {{#data_rows[selected]}}
                        <div class="ui forteen wide field">
                        <label for="name">Naziv role</label><input name="name" type="text" value={{name}} twoway>
                        </div>                        
                    {{/}}
            <div>
                <button class="ui primary button" on-click='@this.onClickSaveClose()'>Spremi</button>
                <button class="ui primary button" on-click='@this.onClickCancel()'>Odustani</button>
            </div>

            </div>

        </div>

</script>
<div id="role"></div>
