
<script>

var company_form = new IbsTable({
    el:"#company",
    template:"#company_form",                        
    onconfig:function() {                     

            $.get("/erp/town", (data)=>{
                      this.set("towns",data);  
                    });                        
            $.get("/erp/country", (data)=>{
                      this.set("countries",data);  
                    });               
                },          
    data: {  title:"Poduzeća",
             confirm_text:"Želite li obrisati poduzeće ?!",                
             towns:[],              
             data_rows:[],               
             default_row:{ id:null, name:"", address:"", town:"", country:"", cid:"" },
             table_width:"",
             headers:"Naziv poduzeća:50:center:,Sjedište:30:center:,Porezni broj:20:center:center", 
             columns:"name,town_name(town.id),cid",
             validations: { name: "isEmpty,isMinLen:3", cid:"isEmpty,isLen:11,isNum", },      
             data_url:"/erp/company",
             form_id:"#data_form",
             menu_id:"data_menu",
             town_name: function(id) {
              if(id!=null)  
                return this.get("towns").find((t)=> { return t.id === id;}).name;
             },             
             country_name: function(id) {
              if(id!=null)  
                return this.get("countries").find((t)=> { return t.id === id;}).name;
             },             
                },            
    components: { IbsCombo:IbsCombo },           
});

</script>

<script id="company_form" type="text/ractive">

<div style="padding-top:10px;align:center;">
{{>dynamic_partial}}</div>

        <div class="ui modal" id="data_form" style="left:30%;padding:15px;margin:auto;top:15em;width:500px;">
            <h3>Poduzeće</h3>     
            <div class="ui form">
                        <div class="ui forteen wide field">
                        <label for="name">Naziv</label><input name="name" type="text" value={{data_rows[selected].name}} twoway>
                        </div>                    
                        
                        <div class="two fields">

                            <div class="ui ten wide field">
                               <label for="country_combo">Država</label>                                                          
                               <IbsCombo id="country_combo" data_url="/erp/country" value={{data_rows[selected].country}} twoway></IbsCombo>
                            </div>

                           <div class="ui ten wide field">                           
                               <label for="town_combo">Sjedište</label>                           
                               <IbsCombo id="town_combo" data_url="/erp/town" value={{data_rows[selected].town}} twoway></IbsCombo>
                        </div>   
                            
                        </div>
                        <div class="ui ten wide field">
                            <label for="address">Adresa</label><input name="address" type="text" value={{data_rows[selected].address}} twoway>
                            </div>
                        <div class="ui seven wide field">
                        <label for="cid">OIB</label><input name="cid" type="text" value={{data_rows[selected].cid}} twoway>
                        </div>

            <div>
                <button class="ui primary button" on-click='@this.onClickSaveClose()'>Spremi</button>
                <button class="ui primary button" on-click='@this.onClickCancel()'>Odustani</button>
            </div>

            </div>
            
        </div>



</script>
<div id="company"></div>


