$(document).ready(function() {
	getUserGroup();
	$("#username").change(function(){
		getUserGroup();
	});
	$("#btnRefresh").bind('click',function(){
		getUserGroup();
	});
	$("#btnLogoutDup").bind('click',function(){
		logoutDup();
	});
	$("#btnFouceLogOut").click(function(e){
		 e.preventDefault();
		 $("#hdhActionLogin").val(2);
		 $( "#userForm" ).submit();
	});
	
	/*$('#username').keypress(function(e) {
	    var tval = $('#username').val(),
	        tlength = tval.length,
	        set = 6,
	        remain = parseInt(set - tlength);
	    if (remain <= 0 && e.which !== 0 && e.charCode !== 0) {
	        //$('#username').val((tval).substring(0, tlength - 1))
	    	
	    }
	});
	
	$('#password').keypress(function(e) {
		if(e.charCode==13){
			return;
		}
		var  set = 6;
		if($("#username").val()=="CLT"){
			set = 9;
		}
	    var tval = $('#password').val(),
	        tlength = tval.length,
	       
	        remain = parseInt(set - tlength);
	    if (remain <= 0 && e.which !== 0 && e.charCode !== 0) {
	        $('#password').val((tval).substring(0, tlength - 1))
	    }
	});
	$('#ttNo').keypress(function(e) {
	    var tval = $('#ttNo').val(),
	        tlength = tval.length,
	        set = 10,
	        remain = parseInt(set - tlength);
	    if (remain <= 0 && e.which !== 0 && e.charCode !== 0) {
	        $('#ttNo').val((tval).substring(0, tlength - 1))
	    }
	});*/
	
});

function logoutDup(){
	var userId=$("#hdhUserName").val();
	$.post(contextPathRoot+"/logutuserDuplicator",{username:userId},function(data){
		 location.reload();
	});
}

function getUserGroup(){
	var userId=$("#username").val();
	if(userId!=''){
		var user={};
		user["userId"] =userId;
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : contextPathRoot+"/getUserGroup",
				data : JSON.stringify(user),
				dataType : 'json',
				timeout : 100000,
				success : function(data) {
					//console.log(data);
					displayDataSuc(data);
				},
				error : function(e) {
					//display(e);
				},
				done : function(e) {
					//enableSearchButton(true);
				}
			});
	}
	else{
		$('#team').html('');
	}
	
}



function displayDataSuc(resultData){
	$('#team').html('');
	$("#divError").html('');
	if(resultData.error.status){
		$.each(resultData.data, function (i, item) {
		    $('#team').append($('<option>', { 
		        value: item,
		        text : item 
		    }));
		});
	}
	else{
		$("#divError").text(resultData.error.errorMessage);
	}
}