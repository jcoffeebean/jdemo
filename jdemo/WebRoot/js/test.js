$(function(){
		$pop = $('<div class="pop-win"></div>').attr('id','win_110');	
		$pop._body = $('<div class="pop-body"><div class="loading">正在加载...</div></div>');
	 	$pop._foot = $('<div class="pop-foot"><div class="pop-foot-area"></div></div>');
	 	$pop._head = $('<div class="pop-head"><div class="pop-title">Title标题</div></div>');
		
		var $popIn = $('<div class="pop-side"></div>').appendTo($pop);
		
		$('#windows').append($pop);
		$pop.append('<span style="margin:2px;background:gray;">spn元素</span>');
		$popIn.append($pop._head);
		$pop._head.append('<a class="pop-close" href="javascript:void(0);">×</a>');
		
		$('.pop-close').on('click',function(){
			alert($pop.html());
					//$pop.remove(".pop-head");
					$pop.find(".pop-head").remove();
		});
		
		
		
		$form = $('<form>').appendTo('body');
	
		//动态创建一个 div 元素（以及其中的所有内容），并将它追加到 body 元素中
		$('<div>', {
			class: 'test',
			style: 'margin:10px;background: pink;',
			text: '动态创建的DIV',
			click: function(){
				$(this).toggleClass('test');
				$(this).append('<span>哈哈</span>');
			}
		}).appendTo('body');
		
		//创建一个 <input> 元素，同时设定 type 属性、属性值，以及一些事件
		$("<input>", {
		  type: "text",
		  val: "Test",
		  focusin: function() {
		    $(this).addClass("active");
		  },
		  focusout: function() {
		    $(this).removeClass("active");
		  }
		}).appendTo($form);	
		
});	
				
			