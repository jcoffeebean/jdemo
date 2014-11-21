;(function($){
	$.fn.extend({
		alterBgColor: function(options){
				options = $.extend({
						even:"even", //偶数行样式
						odd:"odd", //奇数行样式
						selected:"selected"  //选中行样式
				},options);
				alert("v2: " + this.length);
				return this.each(function(){
						alert($(this).attr('id'));//①注意：这里this是dom元素
						$('tbody > tr:odd',this).addClass(options.odd);
						$('tbody > tr:even',$(this)).addClass(options.even);
						$('tbody > tr',$(this)).click(function(){
								alert($(this).attr('id'));//②注意：两处的$(this)引用的对象是不一样的，①代表当前的table，②代表当前点击的tr
								var hasSelected = $(this).hasClass(options.selected);
								$(this)[hasSelected?"removeClass":"addClass"](options.selected)
								//查找内部的checkbox，设置相应的属性
								.find(':checked').attr('checked',!hasSelected);
						});
						//若单选框默认情况是选中的，则高亮
						$('tbody > tr:has(:checked)',$(this)).addClass(options.selected);					
				});
		}	
	});
})(jQuery);