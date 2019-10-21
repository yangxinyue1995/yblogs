/************************
 * 检验是否为空
 ***********************/
function validate_required(field,alerttxt)
{
with (field)
  {
  if (value==null||value=="")
    {alert(alerttxt);return false}
  else {return true}
  }
return true;
}
function inputIsNull(field,alerttxt)
{
	//alert(123)
	if ($(field).val() == ""){
		if ($(field).css("display") == "none" )return true;
		if ($(field).parents().css("display") == "none" )return true;
		$(field).focus();
		
		alert(alerttxt);
		return false;
	}
return true; 
}
/************************
 * 检验是否为数值
 ***********************/
function isNumber(waitingStr,alerttxt){
    var reg = new RegExp("^[0-9]*$");
 if(!reg.test(waitingStr.value)){
     alert(alerttxt);
     return false;
 }
 return true;
}
/************************
 * 检验是否为邮箱
 ***********************/
function isEmail(waitingStr,alerttxt){
 if(/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test($(waitingStr).val()) == false){
     alert(alerttxt);
     return false;
 }
 return true;
}

/************************
 * 检验是否为整数
 ***********************/
function isInteger(waitingStr,alerttxt){
 if(/^\d+$/.test($(waitingStr).val()) == false){
     alert(alerttxt);
     return false;
 }
 return true;
}

/************************
 * 检验浮点数是否合法
 ***********************/
function isEmail(waitingStr,alerttxt){
 if(/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test($(waitingStr).val()) == false){
     alert(alerttxt);
     return false;
 }
 return true;
}

/************************
 * 只输入数字和小数点
 ***********************/
function onlyNumber(input_number){
	$(input_number).keypress(function(event) { 
        var keyCode = event.which; 
        if (keyCode == 8 ||keyCode == 46 || (keyCode >= 48 && keyCode <=57)) 
            return true; 
        else 
            return false; 
    }).focus(function() { 
        this.style.imeMode='disabled'; 
    });
}
/************************
 * 只输入数字
 ***********************/
function onlyInteger(input_number){
	$(input_number).keypress(function(event) { 
        var keyCode = event.which; 
        if (keyCode == 8 || (keyCode >= 48 && keyCode <=57)) 
            return true; 
        else 
            return false; 
    }).focus(function() { 
        this.style.imeMode='disabled'; 
    });
}

/************************
 * 检验是否为手机号码
 ***********************/
function isNumber(waitingStr,alerttxt){
    var reg = new RegExp("^\d{n}$");
 if(!reg.test(waitingStr.value)){
	 waitingStr.focus();
     alert(alerttxt);
     return false;
 }
 return true;
}

function checkNull(thisform){
	names = document.getElementsByName('name');
	areas = document.getElementsByName('area');
	cities = document.getElementsByName('"city"');
	for (i=0;i<names.length;i++)
	{
		if (names[i].value=="" || names[i].value == null){
			  alert("输入不能为空");
			  names[i].focus();
			  return false;
		}
	}
	for (i=0;i<areas.length;i++)
	{
		if (areas[i].value=="" || areas[i].value == null){
			  alert("输入不能为空");
			  areas[i].focus();
			  return false;
		}
	}
	for (i=0;i<cities.length;i++)
	{
		if (cities[i].value=="" || cities[i].value == null){
			  alert("输入不能为空");
			  cities[i].focus();
			  return false;
		}
	}
	return true;
};
function checkAll(waitingForm){
	input_txt = $(":text");
	for(i=0;i<input_txt.length;i++){
		
		if (input_txt[i].value == ""){
			if ($(input_txt[i]).css("display") == "none" )continue;
			input_txt[i].focus();
			str = $(input_txt[i]).parent().prev().text();
			alert("请输入"+str);
			return false;
		}
	}
	text_area = $(":textarea");
	for(i=0;i<text_area.length;i++){
		if (text_area[i].value == ""){
			if ($(text_area[i]).css("display") == "none" )continue;
			text_area[i].focus();
			str = $(text_area[i]).parent().prev().text();
			alert("textaera:请输入"+str);
			return false;
		}
	}
	unitCodes = $("input:radio[name='unitCodeJudge']:checked");
	
	if (unitCodes.length == 0&&$("input:radio[name='unitCodeJudge']:first").attr("disabled")==false ){
		$("input:radio[name='unitCodeJudge']").focus();
		alert("请选择一个值");
		return false;
	}
	email = document.getElementsByName('donatedEmail');
	if (isEmail(email[0],"请输入正确的邮箱") == false){
		
		email[0].focus();
		return false;
	}
	return true;
	}

function checkInput(){
	input_txt = $(":text");
	for(i=0;i<input_txt.length;i++){
		
		if (input_txt[i].value == ""){
			if ($(input_txt[i]).css("display") == "none" )continue;
			if ($(input_txt[i]).parents().css("display") == "none" )continue;
			input_txt[i].focus();
			str = $(input_txt[i]).parent().prev().text();
			if (str == ""){
				str = $(input_txt[i]).attr("name");
			}
			alert("请输入"+str);
			return false;
		}
	}
	
	return true;
	}
function checkPassword(){
	input_txt = $(":password");
	for(i=0;i<input_txt.length;i++){
		
		if (input_txt[i].value == ""){
			if ($(input_txt[i]).css("display") == "none" )continue;
			if ($(input_txt[i]).parents().css("display") == "none" )continue;
			input_txt[i].focus();
			str = $(input_txt[i]).parent().prev().text();
			if (str == ""){
				str = $(input_txt[i]).attr("name");
			}
			alert("请输入"+str);
			return false;
		}
	}
	if (input_txt.length==2){
		if (input_txt[0].value!=input_txt[1].value){
			alert("两次密码不一致")
			return false;
		}
	}
	
	return true;
	}
function checkInputInBlock(block){
	input_txt = $(block).find("input[type='text']");
	//alert($(block).find("input[type='text']").size());
	for(i=0;i<input_txt.length;i++){
		
		if (input_txt[i].value == ""){
			if ($(input_txt[i]).css("display") == "none" )continue;
			if ($(input_txt[i]).parents().css("display") == "none" )continue;
			input_txt[i].focus();
				str = $(input_txt[i]).attr("name");
			
			alert("请输入"+str);
			return false;
		}
	}
	
	return true;
	}
function checkArea(){
	text_area = $(":textarea");
	for(i=0;i<text_area.length;i++){
		if (text_area[i].value == ""){
			if ($(text_area[i]).css("display") == "none" )continue;
			text_area[i].focus();
			str = $(text_area[i]).attr("name");
			alert("textaera:请输入"+str);
			return false;
		}
	}
	return true;
}

function checkText(){
	if (checkInput()&&checkArea())
		return true;
	else return false;
}
function checkNews(editor){
	if (checkInput()==false){
		return false;
	};
	if (editor.getContentLength() == 0){
		alert("编辑器不能为空！")
		editor.focus();
		return false;
	}
	return true;
	}

function checkFile(){
 if($("[name='title']").val() == ""){
	 alert("标题不能为空");
 $("[name='title']").focus();
	 return false;
 }
 if($("[name='content']").val() == ""){
	 alert("内容不能为空");
 $("[name='content']").focus();
	 return false;
 }
 if($("[name='file']").val() == ""){
	 alert("上传文件不能为空");
 $("[name='file']").focus();
	 return false;
 }
 return true;
}

function checkRadio(name,txt){
	if ($("[name="+name+"]:checked").size()<1)
	{
		alert("请选择"+txt)
		$("[name='radio']").focus();
		return false;
	}
	return true;
}
function checkSelect(name,str,nullNumber){
	if ($("[name="+name+"]:eq(0)").val()==nullNumber){
		alert("请选择"+str);
		$("[name="+name+"]:eq(0)").focus();
		return false;
	}
	return true;
}