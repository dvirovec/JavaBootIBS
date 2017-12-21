<script>


IbsTable = Ractive.extend({    
    isolated: false,     
    
    oninit:function() {

    this.observe("search_sample", function(newSample, oldSample) {              

        if(newSample.length>0) {

              if(!this.get("isLetter")(newSample)) {
                this.load_data(this.get("name_search_url")+newSample);
              }

              if(!this.get("isNum")(newSample)) {
                this.load_data(this.get("code_search_url")+newSample);                
              }
          }
          else {
            this.load_data(this.get("data_url"));
          }
    });    

    this.observe("selected", function(newIndex, oldIndex) {
    
            if(Number.isNaN(newIndex)) return;

            if(oldIndex!=null && oldIndex>-1) {
                let row0 = document.getElementById("data_table").rows.item(oldIndex);
                if(row0!=null)
                    row0.style.backgroundColor='#ffffff';                            
            }            

            if(newIndex!=null && newIndex>-1) {
                let row1 = document.getElementById("data_table").rows.item(newIndex);
                if(row1!=null)
                    row1.style.backgroundColor='#dbe7f9';
            }
                
        });
    },     
    onrender:function() {                
        this.load_data(this.get("data_url"));
    },    
    data: {        
        delete_disabled:"disabled",                     
        append_mode:false,
        edit_mode:false,     
        selected:null,          
        data_url:"",
        name_search_url:null,
        code_search_url:null,  
        form_id:"",
        undo_cache:null,
        search_sample:"", 
        isEmpty: function(value) {                                        
            
            let message = "Podatak ne smije biti prazan!";    

            if(value===null) {                 
                return message;            
            }

            if(String(value).trim()==="") {            
                return message;            
            }                

            return null;
            },      
        isNum: function(value) {
                let message = "Podatak mora biti brojčani!";    

                if(Number.isNaN(Number(value))) {
                    return message;
                }
                
            return null;
            },
        isUpperLetter: function(value) {

            let message = "Podatak mora biti unesen samo velikim slovima!";                
            let rgx = /^[A-Z]+$/;
            
            if(!value.match(rgx)) {
                return message;
            }

            return null;
        },
        isLowerLetter: function(value) {

            let message = "Podatak mora biti unesen samo malim slovima!";                
            var rgx = /^[a-zčćđšž-]+$/;
            
            if(!value.match(rgx)) {
                return message;
            }
            return null;
        },
        isAlpha: function(value) {

            let message = "Podatak mora sadržavati samo slova i brojke!";                
            var rgx = /^[0-9][a-zčćđšž-][A-ZČĆĐŠŽ]+$/;
            
            if(!value.match(rgx)) {
                return message;
            }
            return null;
        },
        isLetter: function(value) {

            let message = "Podatak mora sadržavati samo slova !";                
            let rgx = /^[A-ZČĆĐŠŽ][a-zčćđšž-]+$/;
            
            if(!value.match(rgx)) {
                return message;
            }

            return null;
        },
        isLen: function(value, len) {
            
            let message = "Podatak mora biti dugačak "+String(len)+" znakova!";

            if(String(value).length != len)  { 
                return message;
            }
                    
            return null;
        },
        isMinLen: function(value, len) {
            
            let message = "Podataka mora biti dugačak minimalno "+String(len)+" znakova!";

            if(String(value).length < len)  { 
                return message;
            }
                    
            return null;
        },
         localDate:function(date) {
                    let dt = new Date(date);
                    return  dt.toLocaleDateString("hr-HR");
                           },
         statusValue:function(status) {

         if(this.get("status_values").length==2) {

                    if(status == true)
                        return this.get("status_values")[0];
                    else
                        return this.get("status_values")[1];
            }

                   return "Nedefinirano";

            },
    },//-----------------------------Data
        partials: {
        dynamic_partial:function() {

        let headers = this.get("headers").split(',');
        let columns = this.get("columns").split(',');
        let container_style = "";
        let table_style = "";

        if(this.get("table_width")!="") {
            container_style = "width:100%;overflow:scroll;";
            table_style     = "display: block;width:"+this.get("table_width")+";";
            }
        else  {
            container_style = "width:100%;";
            table_style="display: block;width:100%;";            
        }

        let table_template = "<table id='data_grid' class='ui dividing header' style='padding:2px;' width='100%'><tr><td width='90%'><h3>{{title}}</h3></td> \
        <td><div id='search_box' class='ui mini icon input'><input type='text' placeholder='Traženje...' value={{search_sample}} twoway> \
        <i class='search icon'></i></div><td> \
        <button id='show_table_button' class='ui primary button'                     on-click='@this.showTable()'      style='display:none;align:center;width:55px;'><i class='browser icon large'></i></button></td><td> \
        <button id='save_button'       class='ui primary button'                     on-click='@this.updateDetails()'  style='display:none;align:center;width:55px;'><i class='save icon large'></i></button></td><td> \
        <button id='add_button'        class='ui primary button'                     on-click='@this.onClickAdd()'     style='align:center;width:55px;'><i class='plus icon large'></i></button></td><td> \
        <button id='remove_button'     class='ui primary {{delete_disabled}} button' on-click='@this.onClickDelete()'  style='align:center;width:55px;'><i class='trash outline icon large'></i></button></td><td> \
        <button id='refresh_button'    class='ui primary button'                     on-click='@this.onClickRefresh()' style='align:center;width:55px;'><i class='refresh icon large'></i></button></td><td> \
        <button id='search_button'     class='ui primary button'                     on-click='@this.onClickAdd()'     style='align:center;width:55px;'><i class='search icon large'></i></button></td></tr> \
            </table><div style='"+container_style+"' id='data_container'><table class='ui selectable celled table' \
            id='data_table' style='"+table_style+"'><thead><th style='width:1%;text-align:center;'>\
            <input type='checkbox' class='select_all' id='select_all' on-change='@this.selectAll()'></th><input type='hidden' value={{selected}}>";
        
        let dstyles = [];    

        headers.forEach((header)=>{
                hattr = header.split(':')
                let hstyle = "", dstyle = "";                

                if(hattr[1]!="") {                    
                    hstyle += "style='width:"+hattr[1]+"%;"
                    dstyle += "style='width:"+hattr[1]+"%;"
                }                                

                if(hattr[2]!="") {                                                        
                      hstyle += "text-align:"+hattr[2]+";";                                           
                }

                if(hattr[3]!="") {                                                        
                      dstyle += "text-align:"+hattr[3]+";";                                           
                }

                dstyle += "'";
                hstyle += "'";
                
                dstyles.push(dstyle);

                table_template += "<th "+hstyle+">"+hattr[0]+"</th>"                
    });

        table_template +="</thead><tbody style='display: block;height:75vh;overflow-y:auto;'>{{#data_rows}}<tr on-dblclick='@this.onDblClickEdit(@index)' on-click='@this.selectDataRow(@index)'><td style='width:2%;text-align:center;'><input type='checkbox' class='select_row' id='select_row' on-change='@this.selectRow(@index)' on-click=''></td>";

        columns.forEach((column, i)=>{

            let column_data = column.split(':');

            if(column_data.length > 1) {

                  switch(column_data[1]) {
                         case 'date':
                         //table_template += "<td "+dstyles[i]+"><time datetime={{"+column_data[0]+"}} data-format='dd.MM.yyyy A'></time></td>"
                         table_template += "<td "+dstyles[i]+">{{"+column_data[1]+"}}</td>"
                      //table_template += "<td "+dstyles[i]+">{{"+column+"}}</td>"
                         break;
                                   }
                }
                  else {
                   table_template += "<td "+dstyles[i]+">{{"+column+"}}</td>"
                }

            });

        table_template +="</tr>{{/}}</tbody><tfooter></tfooter></table></div><div class='ui vertical pointing menu' style='visibility:hidden;width:180px;position:fixed;z-index:100;top:0px;left:00px;background-color:#fcfcfc' id='data_menu'><a class='item' on-click='@this.openEdit()'>Uređivanje</a><a class='item' on-click='@this.deleteSelected()'>Brisanje</a></div>"
       
        table_template+="<div id='confirm_delete' class='ui small modal'><div class='header'>Brisanje</div><div class='content'><div class='left'>{{confirm_text}}</div></div><div class='actions'><div class='ui cancel button'>Prekid</div><div class='ui approve button'>U redu</div></div></div>"

        return table_template;
         }
     },        
    load_data:function(url) {
        let object = this;
         $.get(url, function( data ) {
            if(data!=null)  object.set("data_rows", data);                     
        });         
    },
    upd : function(i, data) {
            let rows = this.get("data_rows");                
            rows[i] = data;                 
            this.set("data_rows", rows);
            this.update("data_rows");
        },
    add: function(openEdit) {

           let object = this;

           $.get(object.get("data_url")+"/-1", function( data ) {
                            //object.get("data_rows").push( JSON.parse( JSON.stringify( this.get("default_row"))));
                            object.get("data_rows").push(data);
                            object.update("data_rows");
                            object.set("selected",object.get("data_rows").length-1);
                            object.update("selected");
                            object.set("append_mode", true);

                            if(openEdit!=undefined) openEdit();

                       });
        },
    del: function(i) {                
        if((this.get("data_rows")[i]).id==null) {
            this.splice("data_rows",i,1);        
            this.update("data_rows");    
        }
    },
    onClickAdd: function() {
            this.add(this.openEdit);

    },
  onClickRefresh: function() {
            this.load_data(this.get("data_url"));
    },

    checkClick: function() {
            alert("Check click ...");
    },
  deleteSelected:function() {

    let object = this;

    $('#confirm_delete').modal('setting', {
        closable  : false,
    onDeny    : function(){      

    },
    onApprove : function() {
          
        let i = object.get("selected");
        let data = object.get("data_rows")[i];
        
        if(data.id!=null)
            $.ajax ({url: object.get("data_url")+"/"+data.id,
                        type: "DELETE",
                        data:  JSON.stringify(object.get("data_rows")[i]),     
                        contentType: "application/json; charset=utf-8",                                  
                        success: function(data){
                            let menu = document.getElementById("data_menu");
                            object.splice("data_rows",i,1);
                            menu.style.visibility = "hidden"; 
                        }
                    });
              }}).modal('show');    
 },
 onClickDelete: function() {
      let rows=[];
      let object = this; 

      $('#data_table tr').each((i, row)=>{               

            let data = this.get("data_rows")[i];
            
            if($(row).find("input:checked").length==0) { 
                rows.push(data);                                   
            }                       
            else {            
                if(data.id!=null)
                    $.ajax ({url: this.get("data_url")+"/"+data.id,
                            type: "DELETE",
                            data:  JSON.stringify(this.get("data_rows")[i]),     
                            contentType: "application/json; charset=utf-8",                                  
                            success: function(data){                        
                                object.set("data_rows",rows);
                            }
                        });            
              }
            });
        },
    selectDataRow: function(i) {            
            
            if(event.target.className=='select_row') return;
            
            if(!this.get("edit_mode"))  {

                let menu = document.getElementById(this.get("menu_id"));

                if(menu!=undefined) {       

                    menu.style.top = (event.clientY-12)+"px";
                    menu.style.left = (event.clientX-12)+"px";            
                    menu.style.visibility = 'visible';                
                 
                    menu.onmouseleave = ()=>{
                        menu.style.visibility = "hidden";
                    }
                }
            }
            this.set("undo_cache",  JSON.parse(JSON.stringify(this.get("data_rows")[i])));                        
                            
            this.set("selected", i);                           
    },
    selectRow: function(i) {
                        
        let selected_cnt = document.getElementById("data_table").querySelectorAll("tr input:checked").length; 

        this.set("delete_disabled", (selected_cnt===0) ? "disabled": "");      
        if(selected_cnt===0)
            document.getElementById("select_all").checked = false;

    },
    selectAll: function() {
      
        let checkbox = document.getElementById("select_all");

        let checked = checkbox.checked;

        document.querySelectorAll('.select_row').forEach((c)=>{
            c.checked = checked;
        });

        this.set("delete_disabled", document.getElementById("data_table").querySelectorAll("tr input:checked").length===0 ? "disabled": "");
    },   
    resetFormValidationStatus: function() {

        for(var field in this.get('validations')) {
                let object = $(this.get('form_id')+' input[name='+field+']');
                object.css('border-color','');                    
                object.attr('title','');
        }
    },
    validateForm: function() {
        
        this.resetFormValidationStatus();

        let valid = true;
        
        var validationMessage;

        var firstErrorObject = null;

        for(var field in this.get("validations")) {            
          
            let validations = this.get("validations")[field].split(',');

            validationMessage = "";            

            for(var v in validations) {

                    let value = (this.get("data_rows")[this.get("selected")])[field];               
                    
                    let message = null;
                    
                    let vFunction = null;

                    if(validations[v].includes(':')) {

                        let params = validations[v].split(":")
                        vFunction = this.get(params[0].trim());                        
                        let param = params[1];                        
                        
                        message = vFunction(value, param);         
                    }    
                    else {
                        vFunction = this.get(validations[v].trim());
                        message = vFunction(value);         
                    }
                    
                    if(message!=null) {
                        validationMessage = validationMessage + message +'\n';                                                                               
                    }

            }
                     if(validationMessage.length > 0) {

                                valid = false;
                                let object = $(this.get("form_id")+" input[name="+field+"]");
                                object.css("border-color","red");                    
                                object.attr('title', validationMessage);

                                if(firstErrorObject==null)
                                    firstErrorObject = object;
                            }   
        }

        if(!valid && firstErrorObject!=null) {
            firstErrorObject.focus();
            firstErrorObject.select();
        }

        return valid;
    },
   
    // Popup form events
    onClickSaveClose: function() {    

        if (!this.validateForm()) return;

        let object = this;    
        let data = this.get('data_rows')[this.get("selected")];        

        var sdata = JSON.stringify(data);

        if(data.id===null) { 

            $.ajax ({url: this.get("data_url"),
                        type: "POST",
                        data: JSON.stringify(data),
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        success: function(data){                        
                            object.upd(object.get("selected"), data)
                            $(object.get("form_id")).hide();
                        }
                    });
              }
        else {
            $.ajax ({url: this.get("data_url")+"/"+data.id,
                        type: "PUT",
                        data: JSON.stringify(data),
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        success: function(data){
                                object.upd(object.get("selected"), data);
                                $(object.get("form_id")).hide();                       
                        }
                    }); 
     }     
     this.set("edit_mode", false);
    },              
     onClickCancel:function(){
          
    $(document).unbind('keyup');

          this.set("edit_mode", false);            

          this.resetFormValidationStatus();

          $(this.get("form_id")).hide();
          
          let append_mode = this.get("append_mode");

          if(append_mode) {            
                this.del(this.get("selected"));
                this.set("append_mode", false); 
                this.update("append_mode");
          }
          else {            
                this.upd(this.get("selected"), this.get("undo_cache"));                                  
                this.set("undo_cache",null);
          }
    },    
    // Data row events
    onDblClickEdit: function(i) {
            
          this.set("selected",i);
          this.update("selected");
       
          this.openEdit();

    },

     editUserDetails: function() {

              document.getElementById("data_table").style.height = 0;
              document.getElementById("data_table").style.visibility = "hidden";

              document.getElementById("show_table_button").style.display = "block";
              document.getElementById("save_button").style.display = "block";

        if(document.getElementById("data_details")!='undefined') {

                document.getElementById("remove_button").style.display = "none";
                document.getElementById("refresh_button").style.display = "none";
                document.getElementById("search_button").style.display = "none";
                document.getElementById("search_box").style.display = "none";
              document.getElementById("data_details").style.visibility = "visible";
              }

        },
     showTable: function() {

                document.getElementById("data_table").style.visibility = "visible";

                document.getElementById("show_table_button").style.display = "none";
                document.getElementById("save_button").style.display = "none";

                document.getElementById("remove_button").style.display = "block";
                document.getElementById("refresh_button").style.display = "block";
                document.getElementById("search_button").style.display = "block";
                document.getElementById("search_box").style.display = "block";

          if(document.getElementById("data_details")!='undefined') {

                document.getElementById("data_details").style.visibility = "hidden";
                document.getElementById("data_details").style.height = 0;
                }
        },
    // Row menu events
    openEdit: function() {     

    let object = this;    
      
    this.set("edit_mode", true);

    let menu = document.getElementById("data_menu");        
    menu.style.visibility = "hidden"; 

    $(document).keyup(function(e) {            
            
            if (e.keyCode === 27) // Esc
                object.onClickCancel();
            else if (e.keyCode === 45) // Insert 
                object.onClickAdd();
            else if (e.keyCode === 113) // F2 
                object.onClickSaveClose();
          });    
                
    $(this.get("form_id")).show();         

    },
});

</script>