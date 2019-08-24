<script>

var company_form = new IbsTable({
    el:"#period",
    template:"#period_form",
    onconfig:function() {

    },
    oninit: function() {

        this._super();
        
    },
    data: {  title:"Računovodstveni periodi",
             confirm_text:"Želite li obrisati periode ?!",
             data_rows:[],
             status_values:["Otvoreno","Zatvoreno"],
             default_row:{ id:null,  periodDescription:"", startDate:"", finishDate:"", periodActive:false },
             period_level:'G',
             table_width:"",
             headers:"Naziv perioda:50:center:left,Početak:10:center:center,Završetak:10:center:center,Aktivan:30:center:",
             columns:"periodDescription, localDate(startDate), localDate(finishDate), statusValue(periodActive),",
             validations: { },
             data_url:"/erp/account_period",
             form_id:"#data_form",
             menu_id:"data_menu",
                },
    components: { IbsCombo:IbsCombo },

});

</script>

<script id="period_form" type="text/ractive">
<div style="padding-top:10px;align:center;">
{{>dynamic_partial}}</div>

        <div class="ui modal" id="data_form" style="padding:15px;margin:auto;top:15em;width:400px;">
            <h3>Valuta</h3>
            <div class="ui form">
                    {{#data_rows[selected]}}

                    <div class="ui twenty wide field">
                    <label for="description">Opis perioda</label><input name="periodDescription" type="text" value={{periodDescription}} twoway>
                    </div>


                    <div class="two fields">

                    <div class="ten wide field">
                    <label for="start_date">Datum početka</label><input name="start_date" type="date" value={{startDate}} twoway>
                    </div>

                    <div class="ten wide field">
                    <label for="finish_date">Datum početka</label><input name="finish_date" type="date" value={{finishDate}} twoway>
                    </div>

                    </div>

                    <div class="ten wide field">
                    <label for="active">Period aktivan</label>
                    <input type="checkbox" id="active" name="active" checked={{periodActive}} twoway>
                    </div>


                    {{/}}
            <div>
                <button class="ui primary button" on-click='@this.onClickSaveClose()'>Spremi</button>
                <button class="ui primary button" on-click='@this.onClickCancel()'>Odustani</button>
            </div>
            </div>
        </div>

</script>
<div id="period"></div>
