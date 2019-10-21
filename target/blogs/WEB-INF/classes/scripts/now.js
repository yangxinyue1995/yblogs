$(document).ready(function(){
			//新
		
			$("#nowId").click(function(){
				var unitName = $("#donatedUnitsId").val();
					//1
						$.ajax({
							url:"../ajax/isHavaOld",
							data:({"unitName":unitName}),
							type:"POST",
							dataType: 'json',
							success:function(unit){
								var dd = unit.data;
								if(dd != null ){
									if(window.confirm("存在相似的旧的单位名，是否产生行的单位代码？")){
										//2
										$.ajax({
											url:"../ajax/createUnitCode",
											type:"POST",
											success:function(msg){
												var obj=JSON.parse(msg); 
												$("#ucId").val(obj.data);
												$("#ucId").css({'display' : 'block'});
												$("#ucsubmitId").css({'display' : 'block'});
											}
										});
										//2
									}
								}else{
									//2
									$.ajax({
										url:"../ajax/createUnitCode",
										type:"POST",
										success:function(msg){
											var obj=JSON.parse(msg); 
											$("#ucId").val(obj.data);
											$("#ucId").css({'display' : 'block'});
											$("#ucsubmitId").css({'display' : 'block'});
										}
									});
									//2
								}
							}
						});
					//1
			  });
				
		});
				 
		$(document).ready(function(){
			$("#ucsubmitId").click(function(){
				var unitCode = $("#ucId").val();
				var donatedUnits = $("#donatedUnitsId").val();
				$.ajax({
				      url: "../ajax/addUnitCode",
				      type: "POST",
				      data: ({"unitCode":unitCode,"donatedUnits":donatedUnits}),
				      success: function(msg){
				    	  var obj=JSON.parse(msg); 
				    	 $("#ucIdId").val(obj.data);
				    	 $("#ucsubmitId").css({'display' : 'none'});
						 if(msg != "0"){
							 alert("成功");
						 }else{
							 alert("失败");
						 }
				      }
				 }); 
				});
		});
		