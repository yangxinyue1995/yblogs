function getdate(){
  var date = new Date();
  var time=date.getTime();
  
 return time;
}
function setnews(obj,days){

var cdate=parseInt((getdate()-days)/(1000 * 60 * 60 * 24));

	if(cdate<10&&cdate>0){
	 obj.show();
	 setnewsStyle(obj);
	}
	else{
		obj.hide();
	}
}
function setnewsStyle(obj){
  if(obj.hasClass("red")){
     obj.attr("class","yellow");
  }
  else{
  obj.attr("class","red");
  }
  setTimeout(function(){
    setnewsStyle(obj);
  },500);
}
$(function(){

   $("#newssub").children("li").each(function(){
        var date = $(this).find("i").eq(0).text();
        var obj = $(this).find("span").eq(0);
        date=new Date(date.replace(/-/g, "/"));
       
        date=date.getTime();
         setnews(obj,date);
        
   });
});