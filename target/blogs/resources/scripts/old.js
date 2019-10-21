$(function(){
$("#oldId").click(function(){
  	$("#faqbg").css({display:"block",height:$(document).height()});
				var yscroll =document.documentElement.scrollTop;
				$("#faqdiv").css("top","100px");
				$("#faqdiv").css("display","block");
				document.documentElement.scrollTop=0;
 });
 $(".close").click(function(){
  $("#faqbg").css("display","none");
  $("#faqdiv").css("display","none");
 });
});

$(document).ready(function() {
			$("#oldId").click(function(){
				var donatedUnits = $("#donatedUnitsId").val();//获得填写的单位
				 $.ajax({
				      url: "../ajax/searchUnitCode",
				      type: "POST",
				      data: ({"unitName":donatedUnits}),
				      dataType: 'json',
				      success: function(list){
				    	 $.each(list,function(i,item){
				    		 var list = item.data;
				    		 for(var i=0;i<list.length;i++){
				    		 var str = "<li title='"+list[i].unitCode+"'>" +
				    		 		   "<input type='hidden' name='JS_unit_id' value='"+list[i].id+"'/>" +
				    		 		   "<input type='hidden' name='JS_unit_unitCode' value='"+list[i].unitCode+"'/>"+
				    		 		   "<input type='radio' name='Js_unit_donatedUnits' value='"+list[i].donatedUnits+"'>"+list[i].donatedUnits+"</li>"
				    		 $("#Id_select_list").append(str);
				    		 }
				    	 });
				      }
				   });
			});
				 
		});
$(document).ready(function(){
	$("#Id_choose").click(function(){
		var unitId = $("input=[name='Js_unit_donatedUnits']:checked").parent().find("input").eq(0).val();
		var unitCode = $("input=[name='Js_unit_donatedUnits']:checked").parent().find("input").eq(1).val();
		var unitName = $("input=[name='Js_unit_donatedUnits']:checked").val();
		$("#Id_selected_list").html("");
		var str = "<li title='"+unitCode+"'>" +
		   "<input type='hidden' id='JS_unit_id_ID' value='"+unitId+"'/>" +
 		   "<input type='hidden' id='JS_unit_unitCode_ID' value='"+unitCode+"'/>"+
 		   "<input type='hidden'  id='Js_unit_donatedUnits_ID' value='"+unitName+"'>"+unitName+"</li>"
		$("#Id_selected_list").append(str);
	});
	
});

$(document).ready(function(){
	$("#Id_ensure").click(function(){
		var child = $("#Id_selected_list").find("li")
		if(child){
			$("#donatedUnitsId").val($("#Js_unit_donatedUnits_ID").val());
			$("#ucId").val($("#JS_unit_unitCode_ID").val());
			$("#ucIdId").val($("#JS_unit_id_ID").val());
			$("#Id_select_list").html("");
			$("#Id_selected_list").html("");
			$("#faqbg").css("display","none");
			$("#faqdiv").css("display","none");
		}else{
			alert("请选择");
		}
	});
});
$(document).ready(function(){
	$("#Id_select").click(function(){
		var unitName = $("#Id_unitName").val();
		var unitCode = $("#Id_unitCode").val();
		 $.ajax({
		      url: "../ajax/searchUnitCode",
		      type: "POST",
		      data: ({"unitName":unitName,"unitCode":unitCode}),
		      dataType: 'json',
		      success: function(list){
		    	  $("#Id_select_list").html("");
		    	 $.each(list,function(i,item){
		    		 var list = item.data;
		    		 for(var i=0;i<list.length;i++){
		    		 var str = "<li title='"+list[i].unitCode+"'>" +
		    		 		   "<input type='hidden' name='JS_unit_id' value='"+list[i].id+"'/>" +
		    		 		   "<input type='hidden' name='JS_unit_unitCode' value='"+list[i].unitCode+"'/>"+
		    		 		   "<input type='radio' name='Js_unit_donatedUnits' value='"+list[i].donatedUnits+"'>"+list[i].donatedUnits+"</li>"
		    		 $("#Id_select_list").append(str);
		    		 }
		    	 });
		      }
		   });
	});
});
