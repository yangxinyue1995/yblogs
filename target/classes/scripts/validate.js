// JavaScript Document
 function Field(params){
	   this.field_id = params.fid;
	   this.validators = params.val;
	   this.on_suc = params.suc;
	   this.on_error = params.err;
	   this.checked = false;	
	  }
	Field.prototype.validate = function(index){
		 for(items in this.validators){
			   //console.log(index);
			   this.set_callback(this.validators[items],index);
			   if(!this.validators[items].validate(this.data(index))){
				   break;
				   }
			 }
		}  
	Field.prototype.data = function(index){
		  return $(this.field_id[index]).val();
		}	
	Field.prototype.set_callback = function(val,index){
		  var self = this;
		  //console.log(this);
		  val.on_suc = function(){
			    self.checked = true;
				self.on_suc(self,index,val.tips)
			  }
		  val.on_error = function(){
			  self.checked=false;
			  self.on_error(self,index,val.tips);
			  }	  
			  
		}	
	function Len_val(min_l,max_l,tip){
		  this.min_v = min_l;
		  this.max_v = max_l;
		  this.tips = tip;
		  this.on_suc = null;
		  this.on_err = null;
		}	
	Len_val.prototype.validate=function(fd){
		  if(fd.length<this.min_v||fd.length>this.max_V){
			   this.on_error();
			   return false;
			  }
		this.on_suc();
		return true;	  
		}	
    function Exp_val(expression,tip){
		 this.exps = expression;
		 this.tips = tip;
		 this.on_suc = null;
		 this.on_error = null;
		}		
	Exp_val.prototype.validate = function(fd){
		if(!fd){
			this.on_suc();
			return true;
		}
		if(this.exps.test(fd)){
			  this.on_suc();
			  return true;
			}
		else{
			this.on_error();
			return false;
			}	
			
		}	
	function Man_val(tip,func){
		  this.tips = tip;
		  this.val_func = func;
		  this.on_suc = null;
		  this.on_error = null;
		}	
	Man_val.prototype.validate = function(fd){
		  if(this.val_func(fd)){
			  this.on_suc();
			  return true;
			  }
		   else{
			  this.on_error();
			  return false; 
			   }	  
		}		
	function UserForm(items){
		this.f_item = items;
		for(idx=0;idx<this.f_item.length;idx++){
	        // console.log(this.f_item[idx].field_id)
			 for(var index=0;index<this.f_item[idx].field_id.length;index++){
		      var fc = this.get_check(this.f_item[idx],index);
			   $(this.f_item[idx].field_id[index]).blur(fc);
			 }
			}
		}	
	UserForm.prototype.get_check = function(v,index){
		  return function(){
			 
			  v.validate(index);
			  }
		}	
	UserForm.prototype.set_submit = function(bid,bind){
		var self=this;
		
		$(bid).submit(function(){
			
			if(self.validate()){
				return bind();
				}
		     else{
		    	 return false;
		    	 
		     }	
			})
		}	
	UserForm.prototype.validate=function(){
		for(idx in this.f_item){
			 for(var index=0;index<this.f_item[idx].field_id.length;index++){
			this.f_item[idx].validate(index);
			}
			if(!this.f_item[idx].checked){
				 return false;
				}
			}
		return true;	
		}	
	function valiRadio(fd){
		console.log(fd);
		if(fd==1){
			  return true;
			}
		else{
			return false;
			}	
		}	