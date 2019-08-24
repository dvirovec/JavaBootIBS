//-----------------------------------------------------------
function set_rowid(data) {
	
	data.forEach(function(e) {
		   e.recid = e.id;
		   delete e.id;    
		});

	return data;
}
//-----------------------------------------------------------
function set_id(data) {

	if (data.isArray) {	
	data.forEach(function(e) {
		   e.id = e.recid;
		   delete e.recid;    
		});
	}
	else  {
		data.id = data.recid;
		delete data.recid;
	}
	
	return data;
}
//-----------------------------------------------------------
function open_popup(title, form_name, width, height) {

w2popup.open({
    title     : title,
    body    : '<div id="form" style="width: 100%; height: 100%;"></div>',        
    width     : width,
    height    : height,
    overflow  : 'hidden',
    color     : '#AAA',
    speed     : '0.3',
    opacity   : '0.8',
    modal     : true,
    showClose : true,
    showMax   : true,                
    onOpen: function (event) {
        event.onComplete = function () {                
            $('#w2ui-popup #form').w2render(form_name);
        }
    }    
});
}
//-----------------------------------------------------------
function getREST(url, json, callback) {

	sJson = JSON.stringify(json);

	$.ajax({
        type: "GET",
        url: url,
        contentType: "application/json; charset=utf-8",
        data: sJson,
        async: true,
        cache: false,
        processData:false,
        dataType: "json",
        success: callback(this.result),
        error : function(responseData) {
        	alert(responseData.responseText);
        }
    });
}
//-----------------------------------------------------------
function postREST(url, json, callback) {

	sJson = JSON.stringify(json);
	var result;
	$.ajax({
        type: "POST",
        url: url,
        contentType: "application/json; charset=utf-8",
        data: sJson,
        async: true,
        cache: false,
        processData:true,
        dataType: "json",
        done: callback,
        error : function(responseData) {
        	alert(responseData.responseText);
        }
    });
	return result;
}
//-----------------------------------------------------------
function putREST(url, json, callback) {

	sJson = JSON.stringify(json);
	var result;
	$.ajax({
        type: "PUT",
        url: url,
        contentType: "application/json; charset=utf-8",
        data: sJson,
        async: true,
        cache: false,
        processData:true,
        dataType: "json",
        success: callback,
        error : function(responseData) {
        	alert(responseData.responseText);
        }
    });
	return result;
}
//-----------------------------------------------------------
function deleteREST(url, json, callback) {

	sJson = JSON.stringify(json);
	$.ajax({
        type: "DELETE",
        url: url,
        contentType: "application/json; charset=utf-8",
        data: sJson,
        async: true,
        cache: false,
        processData:true,
        dataType: "json",
        success: callback(json.recid),
        error : function(responseData) {
        	alert(responseData.responseText);
        }
    });
}