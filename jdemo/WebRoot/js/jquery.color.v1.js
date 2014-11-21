;(function($){
	$.fn.extend({
		alterBgColor: function(options){
				options = $.extend({
						even:"even", //偶数行样式
						odd:"odd", //奇数行样式
						selected:"selected"  //选中行样式
				},options);
				//插件里面的this代表的是jQuery对象
				alert("v1: " + 'length == ' + this.length + ' ids: ' + this.attr('id'));
				$('tbody > tr:odd',this).addClass(options.odd);//jQuery([selector,[context]]):selector:用来查找的字符串,context:作为待查找的 DOM 元素集、文档或 jQuery 对象
				$('tbody > tr:even',this).addClass(options.even);
				$('tbody > tr',this).click(function(){
						var hasSelected = $(this).hasClass(options.selected);
						$(this)[hasSelected?"removeClass":"addClass"](options.selected)
						//查找内部的checkbox，设置相应的属性
						.find(':checked').attr('checked',!hasSelected);
				});
				//若单选框默认情况是选中的，则高亮
				$('tbody > tr:has(:checked)',this).addClass(options.selected);
				//返回this，使方法可链
				return this;
		}	
	});
})(jQuery);