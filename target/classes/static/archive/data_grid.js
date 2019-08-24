<script>



var IbsDataGrid = Ractive.extend({
    isolated: false,
    oninit: function() {
        this.form_id="#"+this.get("form_id");

        $('#data_table').DataTable();

    },
    onrender:function() {
        this.load_data();
    },
    data: {
        delete_disabled:"disabled",
        append_mode:false,
        data_url:"",
        form_id:"",
    },
    partials: {
        dynamicTable:function() {

        let headers = this.get("headers").split(',');
        let columns = this.get("columns").split(',');

        let table_template = "<table id='data_table' class='ui dividing header' width='100%'><tr><td width='90%'><h3 >{{title}}</h3></td><td align='right'><button id='add_button' class='ui primary button'  on-click='@this.onClickAdd()'>Novo</button></td><td align='left'><button id='remove_button' class='ui primary {{delete_disabled}} button' on-click='@this.onClickDelete()'>Brisati</button></td></tr> \
            </table><div><table class='ui selectable celled table' id='data_table' width='100%'><thead><th style='width:1%;text-align:center;'>\
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

        table_template +="</thead><tbody>{{#data_rows}}<tr on-dblclick='@this.onRowDblClick(@index)' on-click='@this.selectDataRow(@index)'><td style='width:2%;text-align:center;'><input type='checkbox' class='select_row' id='select_row' on-change='@this.selectRow(@index)'></td>";

        columns.forEach((column, i)=>{
            table_template += "<td "+dstyles[i]+">{{"+column+"}}</td>" });

        table_template +="</tr>{{/}}</tbody></table></div>";

        return table_template;
         }
     },
    load_data:function() {

        let object = this;

         $.get(this.get("data_url"), function( d ) {
            if(d!=null)  object.set("data_rows", d);
        });
    },
    upd : function(i, data) {

        let rows = this.get("data_rows");
        rows[i] = data;
        this.set("data_rows", rows);
        },

    del: function(index) {
        delete (this.get("data_rows")[index]);
    },
    add: function() {
          this.get("data_rows").push(this.get("default_row"));
          this.update("data_rows");
        },
    onClickAdd: function() {
            this.append_mode=true;
            this.add();
            this.set("selected",this.get("data_rows").length-1);
            $(this.form_id).show();
    },
    onClickSaveClose: function() {

        let object = this;
        let data = this.get('data_rows')[this.get("selected")];
        if(data.id===null) {

        $.ajax ({url: this.get("data_url"),
                        type: "POST",
                        data: JSON.stringify(data),
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        success: function(data){
                            object.upd(object.get("selected"), data);
                            $(object.form_id).hide();
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
                                $(object.form_id).hide();
                        }
                    });
     }
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

    onClickCancel:function(){

          if(this.get("append_mode")) {
            this.del(this.get("selected"));
          };

          this.set("append_mode", false);

          $(this.form_id).hide();
    },
    selectDataRow: function(i) {

        let i0 = this.get("selected");

        if(this.get("selected")>-1) {
            document.getElementById("data_table").rows.item(i0).style.backgroundColor='#ffffff';

            console.log(i0);

            }

            this.set("selected", i);
            document.getElementById("data_table").rows.item(i).style.backgroundColor='#dbe7f9';
    },
    selectRow: function(i) {

        let selected_cnt = document.getElementById("data_table").querySelectorAll("tr input:checked").length;

        this.set("delete_disabled", (selected_cnt===0) ? "disabled": "");

        if(selected_cnt===0)
            document.getElementById("select_all").checked = false;
    },
    selectAll: function() {

        let checked = document.getElementById("select_all").checked;

        document.querySelectorAll('.select_row').forEach((c)=>{
            c.checked = checked;
        });

        this.set("delete_disabled", document.getElementById("data_table").querySelectorAll("tr input:checked").length===0 ? "disabled": "");

        },

    onRowDblClick: function(i) {

          this.set("selected",i);
          this.update("selected");
          $(this.form_id).show();
          this.append_mode = false;
    },

});

</script>