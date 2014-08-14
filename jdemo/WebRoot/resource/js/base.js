var Utils = {
	isNum:function(obj){return typeof obj==="number";},
	isObj:function(obj){return typeof obj==="object";},
	isStr:function(obj){return typeof obj==="string";},
	setCookie: function(name, value, options){
		options = options || {};
		if (value === null) {
			value = '';
			options.expires = -1;
		}
		var date = new Date();
		if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
			if (typeof options.expires == 'number') {
				date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
			} else {
				date = options.expires;
			}
		}else{
			date.setTime(date.getTime() + (365 * 24 * 60 * 60 * 1000));
		}
		var expires = '; expires=' + date.toUTCString();
		var path = options.path ? '; path=' + options.path : '';
		var domain = options.domain ? '; domain=' + options.domain : '';
		var secure = options.secure ? '; secure' : '';
		document.cookie = [ name, '=', encodeURIComponent(value), expires, path, domain, secure ].join('');			
	},
	getCookie: function(name){
		var cookieValue = null;
		if (document.cookie && document.cookie != '') {
			var cookies = document.cookie.split(';');
			for ( var i = 0; i < cookies.length; i++) {
				var cookie = jQuery.trim(cookies[i]);
				if (cookie.substring(0, name.length + 1) == (name + '=')) {
					cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
					break;
				}
			}
		}
		return cookieValue;		
	},
	getFormData: function(formId){
		var data = {};
		$('#'+formId+' :input').each(function(i,self){
			var name = self.name;
			var type = self.type;
			if(name){
				var value = self.value;
				if(type=='checkbox'){
					if(self.checked){
    					if(data[name]){
    						data[name] = data[name]+','+value;
    					}else{
    						data[name] = value;
    					}
					}
				}else if(type=='radio'){
					if(self.checked){
						data[name] = value;
					}
				}else{
					if(value != ""){
    					data[name] = value;
					}
				}
			}
		});
		return data;
	},

	/*
	 * 自动调整元素高度的方法
	 */
	resizeHeight: function(id,h){
	 	function _resize(){
	 		//document.body.clientHeight
	 		var bodyH = document.documentElement.clientHeight;
	 		$('#'+id).height(bodyH-h);
	 	}
	    $(document).ready(_resize);
	    $(window).resize(_resize); 
	 },
	/*-- 设置最小高度 --*/
	setMinHeight: function(elementId, height){
	    var container = document.getElementById(elementId);
	    container.style.height = (container.scrollHeight < height) ? height + "px" : "auto";
	},
	/*-- 设置最大高度 --*/
	setMaxHeight: function(elementId, height){
	    var container = document.getElementById(elementId);
	    container.style.height = (container.scrollHeight > (height - 1)) ? height + "px" : "auto";
	},
	/* 
	 * 解析 url 参数，返回对象
	 */
	parseURL: function(url){
		var url = url || document.location.search.replace(/^\?/,'');
		var param = {};
		if(url.length > 1){
			var items = url.split('&');
			for(var i=0;i<items.length;i++){
				var kv = items[i].split('=');
				param[kv[0]] = kv[1];
			}
		}
		return param;
	}	
};
/*--移除数据中指定的元素--*/
Array.prototype.remove = function(e) {
	for(var i = 0; i < this.length; i++){
		var element = this[i];
		if(element == e){
			for(var j = i; j < this.length - 1; j++){
				this[j] = this[j + 1];
			}
			this.pop();
			break;
		}
	}
};
Array.prototype.isContain = function(e){
	for(var i = 0; i < this.length; i++){
	if(this[i] == e){return true;}
	}
	return false;
};
String.prototype.cut = function(maxLen) {
	return this.length > maxLen ? this.substr(0, maxLen - 1) + "…" : this;
};
String.prototype.decodeSpecialChars = function() {
	return this.replace(/&amp;/g, "&").replace(/&lt;/g, "<")
		.replace(/&gt;/g, ">").replace(/&apos;/g, "'").replace(/&quot;/g, '"');
};
String.prototype.encodeSpecialChars = function() {
	return this.replace(/&/g, "&amp;").replace(/</g, "&lt;")
		.replace(/>/g, "&gt;").replace(/'/g, "&apos;").replace(/"/g, "&quot;");
};
String.prototype.evalJSON = function() {
	return eval("(" + this + ")");
};
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};

/*-- 封装表单验证方法 --*/
function ValidateForm(formId,rules,messages,handler,highlightMap,isStar,errorWarpId,invalidHandler){
	if(isStar){//添加必填项星号标识
		for(r in rules){
			$('#'+formId + ' [name="' + r + '"]:visible').after('<span style="color:red;">*</span>');
		}
	}
	var ErrorContainerId = 'ErrorContainer_'+formId;
	if(errorWarpId){
	   $('#'+errorWarpId).prepend('<div id="ErrorContainer"><ol></ol></div>');
	}else{
	   $('#'+formId).prepend('<div id="'+ErrorContainerId+'" class="ErrorContainer"><ol></ol></div>');
	}
    return $('#'+formId).validate({
	    errorContainer: $('#'+ErrorContainerId+''),
		errorLabelContainer: $('#'+ErrorContainerId+' ol'),
		wrapper:"li",
	    rules:rules,
       	messages:messages,
		highlight: function( element, errorClass, validClass ) {
			if(highlightMap && highlightMap[element.name]){
				$('#' + highlightMap[element.name]).addClass(errorClass).removeClass(validClass);
			}else{
				$(element).addClass(errorClass).removeClass(validClass);
			}
		},
		unhighlight: function( element, errorClass, validClass ) {
			if(highlightMap && highlightMap[element.name]){
				$('#' + highlightMap[element.name]).removeClass(errorClass).addClass(validClass);
			}else{
				$(element).removeClass(errorClass).addClass(validClass);
			}
		},     	
        submitHandler: function(form){
        	if(handler){
				handler();
			}else{
				form.submit();
			}
        },
        invalidHandler: function(form,validator){
        	invalidHandler && invalidHandler();
        }        
    });	
}
