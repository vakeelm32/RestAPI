/**
 * js file for post.html
 * Please use modern web browser as this file will not attempt to be
 * compatible with older browsers. Use Chrome and open javascript console
 * or Firefox with developer console.
 * 
 * jquery is required
 */
$(document).ready(function() {
	
	var $put_example = $('#put_example'), $SET_PhoneNumber = $('#SET_PhoneNumber'), $SET_Company = $('#SET_Company');
	
	getFreindsData();
	
	$(document.body).on('click', ' .UPDATE_BTN', function(e) {//:button,
		//console.log(this);
		var $this = $(this)
			, FRND_DATA = $this.val()
			, $tr = $this.closest('tr')
			, Name = $tr.find('.name').text()
			, Interest = $tr.find('.interest').text()
			, Developer =$tr.find('.developer').text()
			, CTC = $tr.find('.ctc').text()
			, Home_Town = $tr.find('.hometown').text()
			, Company = $tr.find('.company').text()
			, Position = $tr.find('.postion').text()
			, Technology = $tr.find('.technology').text()
			, PhoneNumber = $tr.find('.phonenumber').text();
		
		
		$('#SET_FRND_DATA').val(FRND_DATA);
		$('#SET_Name').text(Name);
		$SET_PhoneNumber.text(PhoneNumber);
		$('#SET_Interest').text(Interest);
		$('#SET_Developer').text(Developer);
		$('#SET_CTC').text(CTC);
		$('#SET_Home_Town').text(Home_Town);
		$SET_Company.val(Company);
		$('#SET_Position').text(Position);
		$('#SET_Technology').text(Technology);
		
		
		$('#update_response').text("");
	});
	
	
	$(document.body).on('click', ' .DELETE_BTN', function(e) {
		alert('hello');
		//console.log(this);
		var $this = $(this)
			, FRND_DATA = $this.val()
			, obj = {FRND_DATA : FRND_DATA}
			, $tr = $this.closest('tr')
			, phonenumber = $tr.find('.phonenumber').text()
			, name = $tr.find('.name').text();
		
		deleteFriendsData(obj, phonenumber, name);
	});
	
	/*delete_btn_function=function(e,box){
		alert('ab');
	console.log(box);
	var $this = $(box)
		, FRND_DATA = $this.val()
		, obj = {FRND_DATA : FRND_DATA}
		, $tr = $this.closest('tr')
		, phonenumber = $tr.find('.phonenumber').text()
		, name = $tr.find('.name').text();
	
	deleteFriendsData(obj, phonenumber, name);
		
	};*/
	
	$put_example.submit(function(e) {
		e.preventDefault(); //cancel form submit
		
		var obj = $put_example.serializeObject()
			, companyName = $SET_Company.val()
			, PhoneNumber = $SET_PhoneNumber.text();
		updateInventory(obj, PhoneNumber, companyName);
	});
});

function updateInventory(obj, mobileNumber, companyName) {
	
	ajaxObj = {  
			type: "PUT",
			url: "http://localhost:8080/RESTAPI/api/v3/records/update/" + mobileNumber +"/"+ companyName,
			data: JSON.stringify(obj), 
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.responseText);
			},
			success: function(data) {
				//console.log(data);
				$('#update_response').text( data[0].MSG );
			},
			complete: function(XMLHttpRequest) {
				//console.log( XMLHttpRequest.getAllResponseHeaders() );
				getFreindsData();
			}, 
			dataType: "json" //request JSON
		};
		
	return $.ajax(ajaxObj);
}

function deleteFriendsData(obj, mobileNumber, companyName) {
	
	ajaxObj = {  
			type: "DELETE",
			url: "http://localhost:8080/RESTAPI/api/v3/records/delete/" + mobileNumber +"/"+ companyName,
			data: JSON.stringify(obj), 
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.responseText);
			},
			success: function(data) {
				console.log(data);
				$('#delete_response').text( data[0].MSG);
			},
			complete: function(XMLHttpRequest) {
				console.log( XMLHttpRequest.getAllResponseHeaders() );
				getFreindsData();
			}, 
			dataType: "json" //request JSON
		};
		
	return $.ajax(ajaxObj);
}

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

