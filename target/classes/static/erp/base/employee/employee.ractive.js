<script>

var employee_form = new IbsTable({
    el:"#employee",
    template:"#employee_form",
    onconfig:function() {
            $.get("/erp/employee", (data)=>{ this.set("employee",data); });
                },
    data: {  title:"Zaposlenici",
             confirm_text:"Želite li obrisati zaposlenika ?!",
             town_id:null,
             towns:[],
             data_rows:[],
             default_row:{ id:null, name:"", address:"", town:"", cid:"" },
             table_width:"",
             validations: { name: "isEmpty,isLetter, isMinLen:4", surname:"isEmpty,isLetter,isMinLen:4", pid:"isEmpty,isLen:11,isNum", },      
             headers:"Ime:30:center:,Prezime:30:center:,OIB:20:center:center",
             columns:"name,surname,pid",
             data_url:"/erp/employee",
             form_id:"#data_form",
             menu_id:"data_menu",
             town_name: function(id) {
              if(id!=null)
                return this.get("towns").find((t)=> { return t.id === id;}).name;
             },
                },
    components: { IbsCombo:IbsCombo },
});

</script>

<script id="employee_form" type="text/ractive">

<div style="padding-top:10px;align:center;">
{{>dynamic_partial}}</div>

        <div class="ui modal" id="data_form" style="padding:15px;margin:auto;top:15em;width:400px;">
            <h3>Zaposlenik</h3>
            <div class="ui form">
                    {{#data_rows[selected]}}
                        <div class="ui forteen wide field">
                        <label for="name">Ime</label><input name="name" type="text" value={{name}} twoway>
                        </div>
                        <div class="ui forteen wide field">
                        <label for="surname">Prezime</label><input name="surname" type="text" value={{surname}} twoway>
                        </div>                                                
                        <div class="ui seven wide field">
                        <label for="pid">OIB</label><input name="pid" type="text" value={{pid}} twoway>
                        </div>
                        <div class="ui seven wide field">
                        <label for="birth_date">Datum rođenja</label>
                        <input name="birth_date" type="date" value={{birth_date}} twoway>
                        </div>

                    {{/}}
            <div>
                <button class="ui primary button" on-click='@this.onClickSaveClose()'>Spremi</button>
                <button class="ui primary button" on-click='@this.onClickCancel()'>Odustani</button>
            </div>

            </div>

        </div>

</script>
<div id="employee"></div>
