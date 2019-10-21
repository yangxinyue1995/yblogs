

function getId(id){
  return id=typeof id=="string"?document.getElementById(id):id;
  } 

function setStyle(obj,style){
   var $cssText=getStyle(obj);
  
   if(typeof style=="object"){
   for(var i in style){
   $cssText += i+":"+style[i]+";";
       obj.style.cssText=$cssText;
      }
     }
    else{
      obj.style=style;
    } 
 } 

function getStyle(obj){
	return obj.style.cssText;
}

 var mapWindow={
       id: "win1",
       newDiv:null,
       newTitle:null,
       newIframe:null,
       newClose:null,
       
	   parentNode:document.body,
	   open:function(){
	 
		   this.newDiv=document.createElement("div");
		
		   this.newIframe=document.createElement("iframe");
		    this.newIframe.frameBorder = 0;
		   getId(this.parentNode).appendChild(this.newDiv);
		   this.newDiv.id=this.id;
		 
		   this.newDiv.appendChild(this.newIframe);
		   this.newIframe.id="iframe";
	       
	  },
	  setColse:function(){
		    this.newClose=document.createElement("span");
		    this.newDiv.appendChild(this.newClose);
		    this.newClose.id="close";
		    this.setCloseStyle();
		    getId("close").onclick=function(){
		    	
		    	mapWindow.CloseStyle.background="url(${root}/resources/images/icons/map_close.png) no-repeat";	
		        mapWindow.setCloseStyle();
		        mapWindow.close();
		    }
		    getId("close").onmouseover=function(){
		    	
		    	mapWindow.CloseStyle.background="url((${root}/resources/images/icons/map_close_hover.png) no-repeat";	
		       mapWindow.setCloseStyle();
		    }
		    getId("close").onmouseout=function(){
		    	mapWindow.CloseStyle.background="url((${root}/resources/images/icons/map_close.png) no-repeat";	
		        mapWindow.setCloseStyle();
		    }
	  },
	  CloseStyle:{
		  "width":"32px",
		  "height":"32px",
		  "background":"url((${root}/resources/images/icons/map_close.png) no-repeat",
		  "display":"block",
		  "cursor":"pointer",
		  "position":"absolute",
		  "left":"490px",
		  "top":"4px"
	  },
	  setCloseStyle:function(){
		  setStyle(getId("close"),this.CloseStyle);
	  },
	  close:function(){
		  var chN=getId(this.parentNode).childNodes;
		  if(chN!=null){
		    getId(this.parentNode).removeChild(chN[chN.length-1]);
		 }
	  },
	  style:{},
	  setWinStyle:function(){
	      setStyle(getId(this.id),this.style);
	  },
	  title:"win1",
	  setTitle:function(){
		   this.newTitle=document.createElement("div");
		   this.newDiv.appendChild(this.newTitle);
		   this.newTitle.id="title";
		   this.setTitleStyle();
		   
	  },
	  titleStyle:{
	          "width":"100%",
	          "height":"20px",
	          "padding":"0px",
		      "margin":"0px"
	              },
	  setTitleStyle:function(){
	     setStyle(getId("title"),this.titleStyle);
	  },
	  setTitle:function(){
	     getId("title").innerHTML=this.title;
	  },
	  deteTitle:function(){
		  var delet=getId(this.id).removeChild(this.newTitle)
	  },
	  content:"",
	  setContent:function(){
		  getId("iframe").setAttribute("frameborder", "0", 0)
		  getId("iframe").src=this.content;
	  },
	  contentStyle:{
	       "width":"98%",
		   "height":"98%",
		   "padding":"0px",
		   "overflow":"hidden",
		   "border":"none"
	  },
	  setContentStyle:function(){
	  
	     setStyle(getId("iframe"),this.contentStyle);
	  },
	  
	 init:function(){
		this.open();
		this.setWinStyle();
		this.setContent();
		this.setContentStyle();
	 } 
   }
 

