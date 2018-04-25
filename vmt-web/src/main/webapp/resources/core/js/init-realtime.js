/*var  REALTIME_HOST = hostNodejs;
var listen = "";
$(document).ready(function (){	
	listen = io(REALTIME_HOST);
	 listen.on('checkData', function(data){
	   listenRealtime(data);
	   showNodeJsSuccess();
	 });
	 // Mat ket noi
	  listen.on('connect_error', function(err) {
	    console.log("Connect Error");	    
	    showNodeJsError();
	  });
	  // Thu ket noi lai
	  listen.on('reconnecting', function(err) {
	   console.log("Connecting");	   
	   showNodeJsError();
	  });
	  // Ket noi lai thanh cong
	  listen.on('reconnect', function(err) {
	   console.log("Connect Done");	   
	   showNodeJsSuccess();
	   refreshManual();   
	  });
});

function showNodeJsError(){
	$("#divErrorNodeJs").html('');
	$("#divErrorNodeJs").text(MS0008);
	$("#divErrorNodeJs").show();
}

function showNodeJsSuccess(){
	$("#divErrorNodeJs").html('');
	$("#divErrorNodeJs").text(MS0007);
	$("#divErrorNodeJs").show();
}*/