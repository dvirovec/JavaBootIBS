<script>

var role_form = new IbsTable({
    el:"#user",
    template:"#user_form",
    onconfig:function() {
            
                },

    updateDetails: function() {
            console.log(this.get("data_rows")[this.get("selected")].userDetails);

        $.ajax ({       url: "/erp/user_detail",
                        type: "POST",
                        data: JSON.stringify(this.get("data_rows")[this.get("selected")].userDetails),
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        success: function(data){
                            /*object.upd(object.get("selected"), data)
                            $(object.get("form_id")).hide();*/
                        }
                    });

         },
    data: {  title:"User",
             confirm_text:"Želite li obrisati korisnika ?!",
             data_rows:[],
             default_row:{ id:null, name:"" },
             table_width:"",
             headers:"Naziv korsnika:33:center, Ime:33:center, Prezime:33:center",
             columns:"name, userDetails.firstName, userDetails.lastName",
             validations: { name: "isEmpty,isMinLen:5"},
             data_url:"/erp/user",
             form_id:"#data_form",
             menu_id:"user_menu",                        
                },

    components: { IbsCombo:IbsCombo },
});

</script>

<script id="user_form" type="text/ractive">

<div style="padding-top:10px;align:center;">
{{>dynamic_partial}}</div>
        <div class="ui modal" id="data_form" style="padding:15px;margin:auto;top:15em;width:400px;">
            <h3>Korisnik</h3>
            <div class="ui form">
                        <div class="ui forteen wide field">
                            <label for="name">Naziv korisnika</label><input name="name" type="text" value={{data_rows[selected].name}} twoway>
                        </div>

                        <div class="forteen wide field">
                            <label for="first_name">Ime</label>
                            <input name="first_name" type="text" value={{data_rows[selected].userDetails.firstName}} twoway>
                        </div>

                        <div class="forteen wide field">
                              <label for="last_name">Prezime</label>
                              <input name="last_name" type="text" value={{data_rows[selected].userDetails.lastName}} twoway>
                         </div>
            <div>
                <button class="ui primary button" on-click='@this.onClickSaveClose()'>Spremi</button>
                <button class="ui primary button" on-click='@this.onClickCancel()'>Odustani</button>
                <button class="ui primary button" on-click='@this.openDetail()'>Korisnički profil</button>
            </div>
            </div>
        </div>

<div class='ui vertical pointing menu' id='user_menu'
style='visibility:hidden;width:180px;position:fixed;z-index:100;top:0px;left:00px;background-color:#fcfcfc'>
<a class='item' on-click='@this.openEdit()'>Uređivanje</a>
<a class='item' on-click='@this.editUserDetails()'>Detalji korisnika</a>
<a class='item' on-click='@this.deleteSelected()'>Brisanje</a></div>
<div id='confirm_delete' class='ui small modal'>
<div class='header'>Brisanje</div>
<div class='content'><div class='left'>{{confirm_text}}</div>
</div>
    <div class='actions'><div class='ui cancel button'>Prekid</div><div class='ui approve button'>U redu</div></div></div>

        <div id='data_details' style='visibility:hidden;'>

               <div class='ui form secondary segment'>

                      <div class="two wide field">
                          <label for="first_name1">{{data_rows[selected].name}}</label>
                          <label for="name">Naziv korisnika</label><input name="name" type="text" value={{data_rows[selected].name}} twoway>
                      </div>

                      <div class="ui three fields">
                        <div class="three wide field">
                        <label for="first_name">Ime</label>
                        <input name="first_name" type="text" value={{data_rows[selected].userDetails.firstName}} twoway>
                      </div>

                        <div class="three wide field">
                          <label for="last_name">Prezime</label>
                          <input name="last_name" type="text" value={{data_rows[selected].userDetails.lastName}} twoway>
                         </div>
                        <div class="three wide field">
                          <label for="birth_date">Datum rođenja</label>
                          <input name="birthDate" type="date" value={{data_rows[selected].userDetails.birthDate}} twoway>
                          </div>
                      </div>

                      <div class="ui three fields">

                          <div class="two wide field">
                            <label for="town">Adresa</label>
                            <input name="town" type="text" value={{data_rows[selected].userDetails.city}} twoway>
                          </div>

                          <div class="three wide field">
                            <label for="town">Poštanski broj</label>
                            <input name="postal_code" type="text" value={{data_rows[selected].userDetails.postalCode}} twoway>
                          </div>

                          <div class="field">
                            <label for="town">Adresa</label>
                            <input name="street_address" type="text" value={{data_rows[selected].userDetails.streetAddress}} twoway>
                          </div>

                      </div>
          <div>
      </div>
     </div>
</div>

</script>
<div id="user"></div>
