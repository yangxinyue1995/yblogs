$(document).ready(function(){
			
			$("#isInputId").click(function(){
				
				  if($("#isInputId").attr("checked")){
					    var id = $("#appId").val();
					    $.ajax({
						      url: "../ajax/inputData",
						      type: "POST",
						      data: ({"applicationId":id}),
						      dataType:'json',
						      success: function(msg){
						    	  var dd = msg.data;
						    	 
				                        var name = dd.address;  
				                        var linkman = dd.linkman; 
				                        var phone = dd.phone;  
				                        $("#receiverAddressId").val(name);
				                        $("#receiverId").val(linkman);
				                        $("#receiverPhoneId").val(phone);
						    	 
						      }
						 }); 
				   }else{
					   $("#receiverAddressId").val("");
					   $("#receiverId").val("");
					   $("#receiverPhoneId").val("");
				   }
			});
			
		});