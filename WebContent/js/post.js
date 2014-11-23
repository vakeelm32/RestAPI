/**
 * js file for post.html
 * Please use modern web browser as this file will not attempt to be
 * compatible with older browsers. Use Chrome and open javascript console
 * or Firefox with developer console.
 * 
 * jquery is required
 */
$(document).ready(function() {
	//console.log("ready");
	
	var $post_example = $('#post_example');
	
	/**
	 * This is for the Submit button
	 * It will trigger a ajax POST call to: api/v2/inventory
	 * This will submit a item entry to our inventory database
	 */
	$('#submit_it').click(function(e) {
		//console.log("submit button has been clicked");
		e.preventDefault(); //cancel form submit
		
		var jsObj = $post_example.serializeObject()
			, ajaxObj = {};
		
		//console.log(jsObj);
		
		ajaxObj = {  
			type: "POST",
			url: "http://localhost:8080/RESTAPI/api/v2/records/postRequest", 
			data: JSON.stringify(jsObj), 
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log("Error " + jqXHR.getAllResponseHeaders() + " " + errorThrown);
			},
			success: function(data) { 
				//console.log(data);
				if(data[0].HTTP_CODE == 200) {
					$('#div_ajaxResponse').text( data[0].MSG);
				}
			},
			complete: function(XMLHttpRequest) {
				//console.log( XMLHttpRequest.getAllResponseHeaders() );
				getFreindsData();
			}, 
			dataType: "json" //request JSON
		};
		
		$.ajax(ajaxObj);
	});
	
	function getFreindsData() {
		
		var d = new Date()
			, n = d.getTime();
		
		ajaxObj = {  
				type: "GET",
				url: "http://localhost:8080/RESTAPI/api/v2/records/allRecord", 
				data: "ts="+n, 
				contentType:"application/json",
				error: function(jqXHR, textStatus, errorThrown) {
					console.log(jqXHR.responseText);
				},
				success: function(data) { 
					console.log(data);
					var html_string = "";
					
					$.each(data, function(index1, val1) {
						console.log(val1);
						html_string = html_string + templateGetInventory(val1);
					});
					
					$('#get_friendsData').html("<table border='1'>" + html_string + "</table>");
					//$('.UPDATE_BTN , .DELETE_BTN').trigger('click');
				},
				complete: function(XMLHttpRequest) {
					//console.log( XMLHttpRequest.getAllResponseHeaders() );
				}, 
				dataType: "json" //request JSON
			};
			
		return $.ajax(ajaxObj);
	}
	function templateGetInventory(param) {
		return '<tr>' +
					'<td class="name">' + param.NAME + '</td>' +
					'<td class="interest">' + param.INTEREST + '</td>' +
					'<td class="developer">' + param.DEVELOPER + '</td>' +
					'<td class="ctc">' + param.CTC + '</td>' +
					'<td class="hometown">' + param.HOMETOWN + '</td>' +
					'<td class="company">' + param.COMPANY + '</td>' +
					'<td class="postion">' + param.POSITION + '</td>' +
					'<td class="technology">' + param.TECHNOLOGY + '</td>' +
					'<td class="phonenumber">' + param.PHONENUMBER + '</td>' +
					
					'<td class="UPDATE_BTN"> <button class="UPDATE_BTN" value=" ' + param.FRND_DATA + ' " type="button">Update</button> </td>' +
					'<td class="DELETE_BTN"> <button class="DELETE_BTN" value=" ' + param.FRND_DATA + ' " type="button">Delete</button> </td>' +
					/*'<td class="DELETE_BTN"> <button class="DELETE_BTN" onclick="delete_btn_function(event,this)" value=" ' + param.FRND_DATA + ' " type="button">Delete</button> </td>'+*/
					
				'</tr>';
		
	}
	
	/**
	 * This is for the 2nd Submit button "Submit v2"
	 * It will do the same thing as Submit above but the api
	 * will process it in a different way.
	 */
	$('#submit_it2').click(function(e) {
		//console.log("submit button has been clicked");
		e.preventDefault(); //cancel form submit
		
		var jsObj = $post_example.serializeObject()
			, ajaxObj = {};
		
		//console.log(jsObj);
		
		ajaxObj = {  
			type: "POST",
			url: "http://localhost:8080/RESTAPI/api/v3/records/postRequest", 
			data: JSON.stringify(jsObj), 
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log("Error " + jqXHR.getAllResponseHeaders() + " " + errorThrown);
			},
			success: function(data) { 
				console.log(data);
				if(data[0].HTTP_CODE == 200) {
					$('#div_ajaxResponse').text( data[0].MSG);
				}
			},
			complete: function(XMLHttpRequest) {
				console.log( XMLHttpRequest.getAllResponseHeaders() );
				getFreindsData();
			}, 
			dataType: "json" //request JSON
		};
		
		$.ajax(ajaxObj);
	});
});
