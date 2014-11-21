var page = {
		util: {
			getClientHeight : function(eDoc) {
				!eDoc && (eDoc = document);
				var v1 = eDoc.documentElement.clientHeight;//, v2 = eDoc.body.clientHeight;
				if(v1 == 0){
					v1 = eDoc.body.clientHeight;
				}
				return v1;//Math.max(v1, v2);
			},
			getScrollHeight : function(eDoc) {
				!eDoc && (eDoc = document);
				var v1 = eDoc.documentElement.scrollHeight, v2 = eDoc.body.scrollHeight;
				return Math.max(v1, v2);
			},
			getScrollWidth : function(eDoc) {
				!eDoc && (eDoc = document);
				var v1 = eDoc.documentElement.scrollWidth, v2 = eDoc.body.scrollWidth;
				return Math.max(v1, v2);
			},
			getScrollLeft : function(eDoc) {
				!eDoc && (eDoc = document);
				var doc = eDoc.documentElement, body = eDoc.body;
				return Math.max(doc.scrollLeft, body.scrollLeft);
			},
			getScrollTop : function(eDoc) {
				!eDoc && (eDoc = document);
				var doc = eDoc.documentElement, body = eDoc.body;
				return Math.max(doc.scrollTop, body.scrollTop);
			}		
		},		
		win: {
	        showTips: function(tips, okOrErr, callback){
	        	okOrErr = okOrErr || 'ok';
	        	$('#windows > div.js-tips').remove();
	        	var $tips = $('<div class="window js-tips">' +
				 '   <div class="frame-tips">' +
				 '       <div class="textwithicon">' +
				 '           <span class="icon_tips icon32_' + okOrErr + '"></span>' +
				 '           <p class="text">' + tips + '</p>' +
				 '       </div>' +
				 '   </div>' +
				 '</div>').appendTo($('#windows'));
	        	var left = ($(window).width()-$tips.outerWidth())/2 + $(document).scrollLeft();
	        	var top = ($(window).height()-$tips.outerHeight())/2 + $(document).scrollTop();
	        	$tips.css({"left": left + 'px', "top": top + 'px'});
	        	window.setTimeout(function(){
	        		$tips.remove();
	        		callback && callback();
	        	}, 1500);
	        },			
	        /*-- 显示遮罩层方法 --*/
	        showMask: function() {
	        	var h = page.util.getScrollHeight();
	        	var w = page.util.getScrollWidth();
	        	$('<div class="mask"></div>').css({"width": w+'px', "height": h+'px'}).appendTo($('#windows'));
	        },
	        /*-- 隐藏遮罩层方法 --*/
	        hideMask: function() {
	        	$('#windows').find('> div.mask').remove();
	        },
	        /*
	         * 公共的弹出窗体
	         * id, title, url, isMask, isIframe, callback, isClose,isCache
	         */
	        popWin: function(opt){
	        	opt.id = opt.id || 'win_'+new Date().getTime();
	        	var $pop = window['win_' + opt.id];
	        	 if($pop){
	        		 opt.isMask !== false && page.win.showMask();
	        		 $pop.show();
	        		 $pop._resize();
	        		 return $pop;
	        	 }
				 $pop = $('<div class="pop-win"></div>').attr("id", opt.id);
				 $pop._body = $('<div class="pop-body"><div class="loading">正在加载...</div></div>');
				 $pop._foot = $('<div class="pop-foot"><div class="pop-foot-area"></div></div>');
				 $pop._head = $('<div class="pop-head"><div class="pop-title">' + (opt.title||'') + '</div></div>');
				 
				 var $popIn = $('<div class="pop-side"></div>').appendTo($pop);
				 if(opt.isHead !== false){
					 $popIn.append($pop._head);
					 if(opt.isClose !== false){
						 $pop._head.append('<a class="pop-close" href="javascript:void(0);">×</a>');
					 }
				 }
				 $popIn.append($pop._body);
				 if(opt.isBgIframe === true){
					 $pop.append('<iframe frameborder="0" class="iframe"></iframe>');
				 }
				 if(opt.arrBtns && opt.arrBtns.length > 0){
					 $popIn.append($pop._foot);
					 for(var i=0; i<opt.arrBtns.length; i++){
						 var btn = opt.arrBtns[i];
						 var btnId = btn.id ? 'id="'+btn.id+'"' : '';
						 $('<input '+btnId+' type="button" class="btn '+(btn.css||'')+'" value="' + btn.text + '"/>').data('data-btn', btn).click(function(){
							 $(this).data('data-btn').clkHandler($pop);
							 return false;
						 }).appendTo($pop._foot.find('> div.pop-foot-area'));
					 }
				 }			 

				 if(opt.isCache === true){
					 window['win_' + opt.id] = $pop;
				 }
				 
	        	 $pop._show = function(){
	        		 opt.isMask !== false && page.win.showMask();
	        		 
	        		 $('#' + (opt.wrapId || 'windows')).append($pop);
	        		 
	        		 $pop.show(function(){
	        	 		if(opt.isIframe === true){//还未加载内容        	
	        	 			opt.width = opt.width || 800;
	        	 			opt.height = opt.height || parseInt(page.util.getClientHeight() * 0.8);
	        	 			$pop._body.empty();
	        	 			var $iframe = $('<iframe width="100%" height="100%" frameborder="0" scrolling="auto"></iframe>');
	        	 			$iframe.appendTo($pop._body);
	        	        	window.setTimeout(function(){
	        	        		$iframe.attr('src',opt.url).load($pop._resize());
	        	        	}, 1);
	        			}else{
	        				if(opt.url.indexOf('<') > -1){
	        					$pop._body.html(opt.url);
	        					opt.callback && opt.callback($pop);
	        				}else if(opt.url.length > 1){
	        					$pop._body.load(opt.url, opt.data, function(){
	        						$pop._resize();
	        						opt.callback && opt.callback($pop);
	        					});
	        				}
	        			}
	        		 });
	        		 $pop._resize();
	        		 return $pop;
	        	 };
	        	 $pop._close = function(completeFun){
	        		 if(opt.isCache === true){
	        			 $pop.hide();
	        			 $('#'+opt.id).hide();
	        		 }else{
	        			 $pop.remove();
	        			 $('#'+opt.id).remove();
	        		 }
	        		 opt.isMask !==false && page.win.hideMask();
	        		 if(typeof(completeFun) == 'function'){
	        			 completeFun();
	        		 }
	        		 opt.onClose && opt.onClose($pop);
	        	 };
	        	 $pop._resize = function(){
	        		 if(opt.width){
	        			 $pop.css({width: opt.width+'px'});
	        		 }
	        		 if(opt.height){
	        			 $pop.css({height: opt.height+'px'});
	        			 var h = opt.height - ($pop._head.outerHeight()+$pop._foot.outerHeight()) - 10;
	    				 $pop._body.css({height: h+'px'});
	    				 $pop.find('> iframe.iframe').height(opt.height + 2);
	        		 }
	        		 if(opt.position){
	        			 $pop.css(opt.position);
	        		 }else{
	        			 var l = ($(window).width() - $pop.outerWidth()) / 2 + page.util.getScrollLeft();
	        			 var t = ($(window).height() - $pop.outerHeight()) / 2 + page.util.getScrollTop();
	        			 if(l < 0) l=5;
	        			 if(t < 0) t=5;
	        			 $pop.css({"left": l+"px", "top": t+"px"});
	        		 }
	        		 if(opt.isIframe){
	        			 $pop._body.find('> iframe').attr('height', $pop._body.outerHeight()-20);
	        		 }
	        		 opt.onResize && opt.onResize($pop);
	        	 };
	        	 
	        	 $pop._head.find('a.pop-close').click(function(){$pop._close();return false;});
	        	 //添加拖拽功能
	        	 //$pop.draggable({handle: $pop._head});
	        	 return $pop._show();
	         }
		},
		btn: {
			logout: function(act){
				if(confirm('确定退出系统?')){
					var url = ctx + '/yh/logout';
					if(act){
						url += '?act=' + act;
					}
					window.location.href = url;
				}
			},
			popSelect: function(btn,title,url,pid,form_input,isMulte,isNoCache,isCheckAll){
				var objs = form_input.split('-');
				var formId = objs[0];
				var hiddenInput = objs[1];
				var val = document.getElementById(formId)[hiddenInput].value;
		        showTypePop(btn,title,ctx+url+pid,450,
		                function(bd){
		        				document.getElementById(formId)[hiddenInput].value = bd.ids;
		        				var ipt = document.getElementById(formId)[hiddenInput+'_name'];
		        				ipt.value = bd.names;
		        				valiForm_cs.element($(ipt));
		               	},val,true,isMulte,isNoCache,isCheckAll); 					
			},
			//选择派出所
			//showTypePop(trigger,title,dataUrl,height,backFun,val,isShowBG,isMulte,isNoCache,isCheckAll){
			selectPCS: function(btn,pid,form_input,isMulte,isCheckAll){
				page.btn.popSelect(btn, '选择派出所', '/jg/getTree?id=', pid, form_input, isMulte, true,isCheckAll);
			},
			//选择行业类别
			selectHYLB: function(btn,pid,form_input,isMulte,isCheckAll){
				page.btn.popSelect(btn, '行业类别', '/sysdic/getTreeData?code=', pid, form_input, isMulte, false, isCheckAll);
			},
			//高级查询选择行政区划
			advSelectXZCH: function(btn,pid){
				var val = '';
		        showTypePop(btn,"区域选择",ctx+'/jg/getTreeData?id='+pid,450,
		                function(bd){
		        		if(bd.ids && bd.ids.length > 0){
		        			var allJgIds = page.getQueryData(false).allJgIds;
		        			var $ul = $('#form_query').find('ul.js-pcs');
		        			page.even_handler.poptype_select_callback(allJgIds, $ul, bd);
		        		}
		               	},val,true,true,false,true); 				
			},
			//高级查询选择 行业类别
			advSelectHYLB: function(btn,pid){
				var val = '';
				showTypePop(btn,"行业类别",ctx+'/sysdic/getTreeData?code='+pid,450,
						function(bd){
					var hylbs = bd.ids.join(',');
					var names = bd.names.join(',');
					if(names == ''){
						names = '所有行业';
					}
					$('#form_query').find(':hidden[name="hylbs"]').val(hylbs);
	        		$('#btn_query_hy').html(names);
				},val,true,false,false,false); 				
			},
			//选择企业
			selectQY: function(){
		      page.win.popWin({
			      	title: '选择所属企业',
			      	url: ctx+'/qy/list',
			      	width: 800,
			      	isIframe: true,
			      	arrBtns: [
			      	  {id: 'btn_qy_select', text: '选择企业', css: 'btn-primary', clkHandler: function($pop){
			      		var selectVal = $pop._body.find('> iframe')[0].contentWindow.doSelect();
			      		if(selectVal == undefined){
			      			alert("请选择企业!");
			      			return false;
			      		}
			      		var val = $.parseJSON(selectVal);
						var form = document.getElementById('form_cs');
						form['dwbh'].value = val.dwbh;
						form['dwbh_name'].value = val.qysjjymc;
						$pop._close();
			      	  }}
			      	]
			      });				
			},
			//选择录相机
			selectLXJ: function(formId){
				page.win.popWin({
					title: '选择录相机品牌型号',
					url: ctx+'/lxj/list',
					width: 800,
					isIframe: true,
					arrBtns: [
					          {id: 'btn_lxj_select', text: '选择', css: 'btn-primary', clkHandler: function($pop){
					        	  var selectVal = $pop._body.find('> iframe')[0].contentWindow.doSelect();
					        	  if(selectVal == undefined){
					        		  alert("请选择设备的品牌型号!");
					        		  return false;
					        	  }
					        	  var val = $.parseJSON(selectVal);
					        	  var form = document.getElementById(formId);
					        	  form['lxjid'].value = val.cpid;
					        	  form['lxjid_name'].value = val.cppp + val.cpxh;
					        	  form['lxj_lx'].value = val.cplx;
					        	  form['lxj_pp'].value = val.cppp;
					        	  form['lxj_xh'].value = val.cpxh;
					        	  form['lxj_tdsl'].value = val.tds;
					        	  window['vali_'+formId].element($(form['lxjid_name']));
					        	  $pop._close();
					          }}
					          ]
				});				
			},
			//地图打点
			doMapPoint: function(){
				page.win.popWin({
					title: '标识场所在地图上的位置',
					url: ctx+'/pub/mappoint',
					width: page.util.getScrollWidth() * 0.8,
					height: page.util.getClientHeight() * 0.9,
					isIframe: true,
					arrBtns: [
					          {text: '确定', css: 'btn-primary', clkHandler: function($pop){
					        	  var point = $pop._body.find('> iframe')[0].contentWindow.doOk();
					        	  if(point){
					        		  $('#ipt_jd').val(point.lng);
					        		  $('#ipt_wd').val(point.lat);
					        		  $pop._close();
					        	  }else{
					        		  page.showMsg('请在地图上标识场所的位置.', 'warning');
					        	  }
					          }},
					          {text: '取消', clkHandler: function($pop){
					        	  $pop._close();
					          }}
					         ]
				});				
			},
			//预览通道
			previewTd: function(data, tdmc, sdkind){
				if(data.sbid == undefined){
					var index = data.index;
					var indNum = data.indNum;
					var formData = Utils.getFormData('form_sb_'+index);
					data = formData;
					data.indNum = indNum;
				}
				page.win.popWin({
					title: '视频预览 - ' + (tdmc || ''),
					url: ctx+'/play/preview?sdkind='+(sdkind||'')+'&r='+Math.random(),
					width: 500,
					height: 360,
					data: data
				});				
			},
			//清空场所列表的查询条件
			clearfilterCsList: function(){
				Utils.clearFormData('form_cslist_condition');
				//获取过滤参数
				var param = page.even_handler.getFilterParam_treeJg();
				reLoadTreeJg(param);
			},
			//过滤机构树
			filterCsList: function(hasJrztControls){
				page.win.popWin({
					id: 'pop_filter_cs',
					url: ctx + '/cs/condition?act='+(hasJrztControls || ''),
					wrapId: 'set_wrap',
					width: 380,
					height: 230,
					isHead: false,
					isMask: false,
					isCache: true,
					position: {"left": "0px", "top": "22px"},
			      	arrBtns: [
					      	  {text: '重置', clkHandler: function($pop){
								Utils.clearFormData('form_cslist_condition');
					      	  }},
					      	  {text: '取消', clkHandler: function($pop){
					      		  $pop._close();
					      	  }},
					      	  {text: '确定', css: 'btn-primary', clkHandler: function($pop){
					      		  $pop._close();
								  //获取过滤参数
								  var param = page.even_handler.getFilterParam_treeJg();
								  reLoadTreeJg(param);
					      	  }}
					      	],
					onResize: function($pop){
						$(document).on("click.filterCsList", function(){
							$pop._close();
						});
					},
					onClose: function(){
						$(document).off("click.filterCsList");
					},
					callback: function($pop){
						$pop.click(function(event){
							event.stopPropagation();
						});
					}
				});					
			},
			//通道加入分组
			popAddGroup: function(id,sbid,indNum,tdid,csid,sdkind,tdmc){
			      page.win.popWin({
				      	title: id?'编辑 - ' + tdmc : '把 ' + tdmc + ' 加入分组',
				      	url: ctx + '/ttfzgl/popAdd?id=' + (id||'') + '&sbid='+sbid+'&indnum='+indNum+'&tdid='+tdid+'&csid='+csid+'&sdkind='+sdkind+'&t='+new Date().getTime(),
				      	width: 420,
				      	height: 300,
				      	isBgIframe: true,
				      	callback: function($pop){
				      	},
				      	arrBtns: [
		      	          {text: id?'保存修改':'确定加入分组', css: 'btn-primary', clkHandler: function($pop){
		      	        	  if(valiForm_ttfzgl.form()){
		      	        		  var formData = Utils.getFormData('form_ttfzgl');
		      	        		  $.post(ctx+'/ttfzgl/save', formData, function(jsonNotify){
		      	        			  if(jsonNotify.code == '1'){
		      	        				  var obj = jsonNotify.obj;
	      	        					  var sbid = obj.sbid;
	      	        					  var indnum = obj.indnum;
	      	        					  var fzid = obj.fzid;
	      	        					  if(id){//修改
	      	        						var $li = $('#'+fzid);
	      	        						//更新修改
	      	        						page.even_handler.buildFzTdList($li.find('> ul.td-list'), fzid, $li.find('> div.note-title > span.icon_exp'));
	      	        					  }else{//新增
	      	        						  //在通道上做标记
	      	        						  $('#'+sbid+'_'+indnum).find('span.name').prepend('<strong style="color:green;" title="已加入分组">[+]</strong>');		      	        				  
	      	        					  }
	      	        					  $pop._close();
		      	        			  }else{
	      	        					  alert(jsonNotify.message || '操作失败!');
	      	        				  }
		      	        		  }, 'json');
		      	        	  }
		      	          }},
				      	  {text: '取消', clkHandler: function($pop){
							$pop._close();
				      	  }}
				      	]
				      });				
			},
			//查看场所详情
			showCs: function(csid, csmc){
			      page.win.popWin({
				      	title: csmc,
				      	url: ctx + '/cs/detail?csid='+csid,
				      	width: page.util.getScrollWidth() - 456,
				      	height: page.util.getScrollHeight() - 30,
				      	position: {right: "1px", top: "2px"},
				      	isBgIframe: true,
				      	callback: function($pop){
				      	},
				      	arrBtns: [
				      	  {text: '关闭窗口', css: 'btn-primary', clkHandler: function($pop){
							$pop._close();
				      	  }}
				      	]
				      });				
			}
		},
		valid: {
			cs: {
				//添加联系人 处理函数
				addLXR: function(){
					var $html = $('<div class="item-contacts-box">' +
					'	<a class="remove" href="javascript:void(0);"></a>' +
						'<table class="tb-cs-contacts" width="100%">' +
						'	<tr>' +
						'		<td class="photo" rowspan="4">' +
						'			<img alt="" src="'+ctx+'/resource/img/original.png">' +
						'		</td> ' +
						'		<td class="lab">姓名：</td>' +
						'		<td><input class="text" name="ryxm" type="text"></td>' +
						'	</tr>' +
						'	<tr>' +
						'		<td class="lab">身份证号：</td>' +
						'		<td><input class="text" name="sfzh" type="text"></td>' +
						'	</tr>' +
						'	<tr>' +
						'		<td class="lab">公司职务：</td>' +
						'		<td><input class="text" name="gszw" type="text"></td>' +
						'	</tr>' +
						'	<tr>' +
						'		<td class="lab">联系电话：</td>' +
						'		<td><input class="text" name="lxdh" type="text"></td>' +
						'	</tr>' +
						'</table>' +
					'</div>');
					$html.find('> a.remove').click(function(){
						if(confirm('确定要移除联系人吗？')){
							$(this).parent().remove();
						}
					});
					$('#items_contacts').append($html);
				},				
				init: function(){
					/*--自定义表单验证规则--*/
					$.validator.addMethod('vali_sfzh', function(value, element, param){
						var isOK = true;
						var reg = /^\d{15}(\d{2}[0-9x])?$/i;  
						$('#form_cs :text[name="sfzh"]').each(function(i,n){
						   var val = $.trim(n.value);
						   if(val == ''){
							   $.validator.messages['vali_sfzh'] = '身份证号不能为空!';
							   isOK = false;
							   $(this).addClass('error');
						   }else if(!reg.test(val)){  
							   $.validator.messages['vali_sfzh'] = '身份证号输入错误!';
							   isOK = false;
							   $(this).addClass('error');
						   }else{
							   $(this).removeClass('error');
						   }
						});
						return isOK;
					});
					$.validator.addMethod('vali_lxdh', function(value, element, param){
						var isOK = true;
						var reg = /^0?1\d{10}$/;  
						$('#form_cs :text[name="lxdh"]').each(function(i,n){
							var val = $.trim(n.value);
							if(val == ''){
								$.validator.messages['vali_lxdh'] = '联系电话不能为空!';
								isOK = false;
								$(this).addClass('error');
							}else if(!reg.test(val)){  
								$.validator.messages['vali_lxdh'] = '联系电话输入错误!';
								isOK = false;
								$(this).addClass('error');
							}else{
								$(this).removeClass('error');
							}
						});
						return isOK;
					});
					//初始化表单验证
			       	valiForm_cs = ValidateForm('form_cs',
			           		{
			           			"csmc": "required","hylbs": "required","dwszdpcs_name": "required",
			           			"sfzh": "vali_sfzh","lxdh": "vali_lxdh"
			           		},{
			           			"csmc":"请填写场所名称!","hylbs": "请选择场所类型!","dwszdpcs_name": "请选择管辖单位!"
			           		});
					var form = document.getElementById('form_cs');
					form['csmc'].focus();
			       	//点击添加联系人按钮事件
			       	$('#btn_cs_add_contacts').click(page.valid.cs.addLXR);
			       	//联系人增加删除事件
			       	$('#items_contacts').find('> div.item-contacts-box').find('> a.remove').click(function(){
			       		if(confirm('确定要移除联系人吗？')){
							$(this).parent().remove();
						}
					});
			       	//接入状态按钮切换
			       	$('#btn_switch_jrzt').click(function(){
			       		if($('#cs_jrzt').val() == '1'){//已接入
			       			$(this).removeClass('on').addClass('off');
			       			$('#cs_jrzt').val('0');
			       		}else{
			       			$(this).removeClass('off').addClass('on');
			       			$('#cs_jrzt').val('1');
			       		}
			       	});
				},
				remove: function(csid){
					if(confirm('确定要删除场所吗？')){
	        			$.getJSON(ctx+'/cs/remove/'+csid, function(result){
	        				if(result){
	        					loadCsList(true);//刷新场所列表
	        				}else{
	        					alert('删除失败!');
	        				}
	        			});						
					}
				}
			},
			xqgl: {
				doEditXqglWrap: function(csid){
					$('#js_module_xqgl').css('display', 'block');
					page.load('js_module_xqgl_con', ctx+'/csxqgl/edit?csid='+csid);
				},
				doShowXqglWrap: function(csid){
					$('#js_module_xqgl').css('display', 'block');
					page.load('js_module_xqgl_con', ctx+'/csxqgl/show?csid='+csid);
				},
				doRemove: function(csid){
					if(!confirm('确定要删除吗?')){
						return false;
					}
					$.getJSON(ctx+'/csxqgl/remove',{csid: csid},function(result){
						if(result){
							page.valid.xqgl.doShowXqglWrap(csid);
						}else{
							alert('删除失败！');
						}
					});
				}
			},
			azd: {
				addAzd: function(){
					var $html = $('<li>' +
							'	<input type="hidden" name="sbid"><input type="hidden" name="tdid">' +
							'	<span class="text"><input type="text" name="name"></span>' +
							'	<a class="td">(未绑定通道)</a>' +
							'	<a href="javascript:void(0);" class="remove"></a>' +
							'</li>');
					$html.find('> a').click(function(){
						$(this).parent().remove();
						return false;
					});
					$('#js_azd_items').append($html);
					$html.find('input').focus();
				},
				doEditAzdWrap: function(csid){
					$('#js_module_azd').css('display', 'block');
					page.load('js_module_azd_con', ctx+'/azd/edit?csid='+csid);
				},
				doShowAzdWrap: function(csid){
					$('#js_module_azd').css('display', 'block');
					page.load('js_module_azd_con', ctx+'/azd/show?csid='+csid);
				}
			},
			sb: {
				init: function(index){
					var fromId = 'form_sb_'+index;
					var form = document.getElementById(fromId);
					//设定表单焦点
					form['ip'].focus();
					//注册类型change事件
					var handleZclxChange = function(objZclx){
						var flag = false;
						var zclx = objZclx.value;
						if(zclx == 'hkDDNS'){
							form['ip'].value = 'www.hik-online.com';
						}else if(zclx == 'hbDDNS'){
							form['ip'].value = 'hanbang.org.cn';
						}else if(zclx == 'dhDDNS'){
							form['ip'].value = 'dahuaddns.com';
						}else{
							flag = true;
						}
						if(flag){
							$(form['ip']).removeAttr('readOnly').removeClass('disabled');
							$('#control_group_sbbs > label.control-label').removeClass('required');
							$(form['sbbs']).val('').attr('readOnly','readonly').addClass('disabled');
						}else{
							$(form['ip']).attr('readOnly','readonly').addClass('disabled');
							$('#control_group_sbbs > label.control-label').addClass('required');
							$(form['sbbs']).removeAttr('readOnly').removeClass('disabled');
						}
						window['vali_form_sb_'+index].element($(form['ip']));
						window['vali_form_sb_'+index].element($(form['sbbs']));
					};
					$(form['zclx']).change(function(){handleZclxChange(this);});
					/*--自定义表单验证规则--*/
					//1、验证通道绑定重复的安装点位置
					//2、验证设备地址
					$.validator.addMethod('vali_ip', function(value, element, param){
						if(value == ''){
							$.validator.messages['vali_ip'] = '请填写设备地址!';
							return false;
						}
						var zclx = form['zclx'].value;
						if(zclx == 'hkDDNS' || zclx == 'DDNS' || zclx == 'hbDDNS'|| zclx == 'dhDDNS'){
							var regEx = /^(?=^.{3,255}$)[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+(\:[1-9]\d{0,4})?$/;
							if(!regEx.test(value)){
								$.validator.messages['vali_ip'] = '设备地址填写不正确,示例:[www.hk.com]';
								return false;
							}
						}else if(zclx == 'IPServer' || zclx == 'ip'){
							var regEx = /^((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d)(\.((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d)){3}(\:[1-9]\d{0,4})?$/;
							if(!regEx.test(value)){
								$.validator.messages['vali_ip'] = '设备地址填写不正确,示例:[115.239.210.27:8080]';
								return false;							
							}
						}
						return true;
					});
					//3、验证设备端口
					$.validator.addMethod('vali_port', function(value, element, param){
						//var zclx = form['zclx'].value;
						//if(zclx == 'IPServer' || zclx == 'ip'){
							if(value == ''){
								$.validator.messages['vali_port'] = '服务端口不能为空!';
								return false;							
							}else{
								if(!/^[1-9]\d*$/.test(value)){
									$.validator.messages['vali_port'] = '服务端口输入错误!';
									return false;									
								}
								if(parseInt(value) > 65535){
									$.validator.messages['vali_port'] = '服务端口是 [1-65535] 的数字!';
									return false;									
								}
							}
						//}
						return true;
					});
					//4、验证设备标识
					$.validator.addMethod('vali_sbbs', function(value, element, param){
						var zclx = form['zclx'].value;
						if(zclx == 'hkDDNS' || zclx == 'hbDDNS'  || zclx == 'dhDDNS'){
							if(value == ''){
								$.validator.messages['vali_sbbs'] = '设备标识不能为空!';
								return false;							
							}
						}else if(value != ''){
							$.validator.messages['vali_sbbs'] = '不需要填写设备标识!';
							return false;							
						}
						return true;
					});
					window['vali_form_sb_'+index] = ValidateForm(fromId,
			           		{
			           			"lxjid_name": "required","zclx": "required",
			           			"ip": "vali_ip","port":"vali_port","sbbs":"vali_sbbs",
			           			"glzh": "required","glmm": "required"
			           		},{
			           			"lxjid_name":"请选择设备型号!","zclx": "请选择接入类型!",
			           			"glzh": "管理账号不能为空!","glmm": "管理密码不能为空!"
			           		});
					handleZclxChange(form['zclx']);
				},
				//编辑设备表单
				doEditSbWrap: function(sbid, index){
					sbid = sbid || '';
					$('#js_module_sb').css('display', 'block');
					//页面上已存在详情
					if(index){
						var postData = {"csid":_csid,"sbid":sbid,"index": index,"r": Math.random()};
						$('#form_sb_'+index+'_wrap').parent().load(ctx+'/sb/edit',postData,function(){
							postData.pstate = 'edit';
							page.load('form_sb_'+index+'_channel',ctx + '/sb/channel_sbid',postData);							
						});
					}else{
						index = _sbWrapNum++;
						var postData = {"csid":_csid,"sbid":sbid,"index": index,"r": Math.random()};
						$('#js_module_sb_con').append(
							$('<div>').load(ctx+'/sb/edit',postData,function(){
								postData.pstate = 'edit';
								page.load('form_sb_'+index+'_channel',ctx + '/sb/channel_sbid',postData);							
							})
						);				
					}
				},
				//显示设备详情
				doShowSbWrap: function(sbid, index){
					$('#js_module_sb').css('display', 'block');
					//页面上已存在编辑表单
					if(index){
						var postData = {"csid":_csid,"sbid":sbid,"index": index,"r": Math.random()};
						$('#form_sb_'+index+'_wrap').parent().load(ctx+'/sb/show',postData,function(){
							postData.pstate = 'show';
							page.load('form_sb_'+index+'_channel',ctx + '/sb/channel_sbid',postData);							
						});
					}else{
						index = _sbWrapNum++;
						var postData = {"csid":_csid,"sbid":sbid,"index": index,"r": Math.random()};
						$('#js_module_sb_con').append(
								$('<div>').load(ctx+'/sb/show',postData,function(){
									postData.pstate = 'show';
									page.load('form_sb_'+index+'_channel',ctx + '/sb/channel_sbid',postData);							
								})
						);					
					}
				},
				doRemove: function(sbid, index){
					if(sbid != ''){
						if(confirm('确定要删除设备吗？')){
		        			$.getJSON(ctx+'/sb/remove/'+sbid, function(result){
		        				if(result){
		        					$('#form_sb_'+index+'_wrap').parent().remove();
		        					//刷新安装点列表
		        					page.valid.azd.doShowAzdWrap(_csid);
		        				}else{
		        					alert('删除失败!');
		        				}
		        			});						
						}						
					}else{
						$('#form_sb_'+index+'_wrap').parent().remove();
					}
				},
				//隐藏无信号通道
				doHideNoSignal: function(index, btn){
					if($(btn).attr('up') != '1'){
						$(btn).find('span.icon').removeClass('icon-chevron-up').addClass('icon-chevron-down').end().find('span.txt').html('显示全部通道');
						$(btn).attr('up','1');
						$('#form_sb_'+index+'_channel').find('table.table-condensed').find('tr[s="0"]').hide();
					}else{
						$(btn).find('span.icon').removeClass('icon-chevron-down').addClass('icon-chevron-up').end().find('span.txt').html('隐藏无信号通道');
						$(btn).attr('up','0');
						$('#form_sb_'+index+'_channel').find('table.table-condensed').find('tr[s="0"]').show();
					}
				}
			}
		},
		even_handler: {
			//设备登记-选择场所事件
			selectCs_edit: function($liCsitem){
				if($liCsitem[0]){
					$liCsitem.addClass('selected').siblings().removeClass('selected');
					var id = $liCsitem.attr('id');
					page.load('edit_wrap', ctx+'/cs/frame?id='+ id);				
				}
			},
			/**
			 * 场所列表中 展开通道列表
			 * tdClickHandle 点击通道的执行事件， false = 不执行任何通道事件
			 */
			expandCs: function($exp, tdClickHandle){
        		var isLoadTd = $exp.attr('isLoadTd');
        		var $itemCs = $exp.closest('li.csitem');
        		var csid = $itemCs.attr('id');
        		var $ul = $itemCs.find('> ul.td-list');
        		if(isLoadTd != '1'){
        			$exp.removeClass('collapse').removeClass('expand').addClass('loading').attr('isLoadTd','1');
        			page.even_handler.buildTdList($ul, csid, tdClickHandle, $exp);
        		}else{
        			if($ul.css('display') == 'none'){
        				$ul.css('display','block');
        				$exp.removeClass('collapse').addClass('expand');
        			}else{
        				$ul.css('display','none');
        				$exp.removeClass('expand').addClass('collapse');
        			}
        		}
			},
			buildTdList: function($ul, csid, tdClickHandle, $exp){
				tdClickHandle = tdClickHandle || {};
    			$ul.css('display','block');
    			$.getJSON(ctx+'/td/getTdList', {"csid": csid, "r": Math.random()}, function(jsonData){
    				if($exp){
    					$exp.removeClass('loading').addClass('expand');
    				}
    				if(jsonData && jsonData.length > 0){
    					for(var i=0;i<jsonData.length;i++){
    						var itemData = jsonData[i];
    						var name = itemData.AZWZ?itemData.AZWZ.encodeSpecialChars():itemData.TDMC;
    						var sbid = itemData.SBID;
    						var indNum = itemData.INDNUM;
    						var stateInfo = '';
    						//0无视频信号 1正常 2未获取
    						switch(itemData.signalStatus){
    						case '-1': stateInfo = '<span class="label" title="安装点未指定视频通道">未安装</span>';
    						break;
    						case '0': stateInfo = '<span class="label label-warning" title="视频通道无视频信号">无信号</span>';
    						break;
    						case '1': stateInfo = '<span class="label label-success" title="视频通道状态正常">正常</span>';
    						break;
    						case '2': stateInfo = '<span class="label" title="视频通道状态侦测失败">未知</span>';
    						break;
    						}
    						var $li = $('<li>' + 
    								'<span class="name" title="'+(name)+'">'+(name)+'</span>' + 
    								'<span class="state">'+(stateInfo)+'</span>' + 
    						'</li>').data('itemData',itemData);
    						if(sbid && indNum){
    							$li.attr('id', sbid+'_'+indNum);
    						}
    						$li.click(function(event){
    							var $self = $(this);
    							var $old = window['$selected_td'];
    							if($old != $self){
    								if($old){
    									$old.removeClass('on');
    								}
    								window['$selected_td'] = $self;
    								$self.addClass('on');
    								if(typeof(tdClickHandle.liClickFun) == 'function'){
    									tdClickHandle.liClickFun($(this));
    								}else if(tdClickHandle.liClickFun == 'default'){
    									$self.find('> span.icon-camera').trigger('click');
    								}
    							}
    							return false;
    						}).on('contextmenu',function(e){
    							e.preventDefault();
    							var liData = $(this).data('itemData');
        						var _sbid = liData.SBID;
        						var _tdid = liData.TDID;
        						var _indNum = liData.INDNUM; 							
        						var _csid = liData.CSID;
        						var _name = itemData.AZWZ?itemData.AZWZ.encodeSpecialChars():itemData.TDMC;
        						var _sdkind = liData.SDKIND;
    							if(_sbid && _indNum){
    								page.btn.popAddGroup(undefined,_sbid,_indNum,_tdid,_csid,_sdkind,_name);
    							}else{
    								page.showMsg('未找到 设备ID和通道序号!', 'error');
    							}
    							return false;
    						});
    						//列表录像图标-事件处理
    						if(tdClickHandle.isIconRecord){
    							var $iconRecord = $('<span class="icon icon-record"></span>');
    							if(typeof(tdClickHandle.recordClickFun) == 'function'){
    								$iconRecord.click(tdClickHandle.recordClickFun);
    							}else if(tdClickHandle.recordClickFun == 'default'){
    								$iconRecord.attr('title','点击录像').click(function(event){
    	    							if(!P.StartRecord){
    	    								return false;
    	    							}
    	    							var itemData = $(this).parent().data('itemData');
    	    							var wndNum = Player.getWndnumByTd(itemData.SBID, itemData.INDNUM);
    	    							if($(this).hasClass('on')){//正在录像
    	    								P.StopRecord(wndNum, true);
    	    							}else{
    	    								P.StartRecord(wndNum);
    	    							}
    	    							return false;    							
    	    						});  					   								
    							}
    							$li.prepend($iconRecord);
    						}
    						if(tdClickHandle.isIconCamera){
    							var $iconCamera = $('<span class="icon icon-camera"></span>');
        						if(typeof(tdClickHandle.cameraClickFun) == 'function'){
        							$iconCamera.click(tdClickHandle.cameraClickFun);
    							}else if(tdClickHandle.cameraClickFun == 'default'){
    								$iconCamera.attr('title','点击播放').click(function(event){
    	    							if(!P.StartRealPlay){
    	    								return false;
    	    							}
    	    							var itemData = $(this).parent().data('itemData');
    	    							if($(this).hasClass('on')){//正在播放
    	    								var wndNum = Player.getWndnumByTd(itemData.SBID, itemData.INDNUM);
    	    								P.StopRealPlay(wndNum);
    	    							}else{
    	    								P.StartRealPlay(itemData.SBID, itemData.INDNUM, undefined, itemData.SDKIND);
    	    							}
    	    							return false;
    	    						});   								
    							}
    							$li.prepend($iconCamera);
    						}
    						$ul.append($li);
    					}
    				}else{
						var $li = $('<li class="on">没有数据!</li>');
						$ul.append($li);
    				}
    			});			
			},
			/**
			 * 分组列表中 展开通道列表
			 * tdClickHandle 点击通道的执行事件， false = 不执行任何通道事件
			 */
			expandFz: function($exp){
        		var isLoadTd = $exp.attr('isLoadTd');
        		var $itemFz = $exp.closest('li.csitem');
        		var fzid = $itemFz.attr('id');
        		var $ul = $itemFz.find('> ul.td-list');
        		if(isLoadTd != '1'){
        			page.even_handler.buildFzTdList($ul, fzid, $exp);
        		}else{
        			if($ul.css('display') == 'none'){
        				$ul.css('display','block');
        				$exp.removeClass('collapse').addClass('expand');
        			}else{
        				$ul.css('display','none');
        				$exp.removeClass('expand').addClass('collapse');
        			}
        		}
			},
			//构建分组通道
			buildFzTdList: function($ul, fzid, $exp){
				$exp.removeClass('collapse').removeClass('expand').addClass('loading').attr('isLoadTd','1');
    			$ul.empty().css('display','block');
    			$.getJSON(ctx+'/ttfzgl/list', {"fzid": fzid, "r": Math.random()}, function(jsonData){
    				if($exp){
    					$exp.removeClass('loading').addClass('expand');
    				}
    				if(jsonData && jsonData.length > 0){
    					for(var i=0;i<jsonData.length;i++){
    						var itemData = jsonData[i];
    						var _name = itemData.NAME?itemData.NAME.encodeSpecialChars():'';
    						var _td_state = itemData.TD_STATE;//1通道正常 0通道不存在
    						var stateInfo = '';
    						if(_td_state == 0){
    							itemData.signalStatus = '-2';
    						}
    						//0无视频信号 1正常 2未获取
    						switch(itemData.signalStatus){
    						case '-2': stateInfo = '<i class="i-warn" title="关联的探头不存在"></i>';
    						break;
    						case '-1': stateInfo = '<span class="label" title="安装点未指定视频通道">未安装</span>';
    						break;
    						case '0': stateInfo = '<span class="label label-warning" title="视频通道无视频信号">无信号</span>';
    						break;
    						case '1': stateInfo = '<span class="label label-success" title="视频通道状态正常">正常</span>';
    						break;
    						case '2': stateInfo = '<span class="label" title="视频通道状态侦测失败">未知</span>';
    						break;
    						}
    						var $li = $('<li>' + 
    								'<span class="name" title="'+(_name)+'">'+(_name)+'</span>' + 
    								'<span class="state">'+(stateInfo)+'</span>' + 
    						'</li>').data('itemData',itemData).attr('id', itemData.ID);
    						$li.click(function(event){
    							var $self = $(this);
    							var $old = window['$selected_td'];
    							if($old != $self){
    								if($old){
    									$old.removeClass('on');
    								}
    								window['$selected_td'] = $self;
    								$self.addClass('on');
    								$self.find('> span.icon-camera').trigger('click');
    							}
    							return false;
    						}).on('contextmenu',function(e){
    							e.preventDefault();
    							var liData = $(this).data('itemData');
        						$('#menus_ttfzgl').data('itemData', liData).menu('show', {
        							left: e.pageX,
        							top: e.pageY
        						});
        						return false;
    						});
    						//列表录像图标-事件处理
							var $iconRecord = $('<span class="icon icon-record"></span>').attr('title','点击录像').click(function(event){
	    							if(!P.StartRecord){
	    								return false;
	    							}
	    							var itemData = $(this).parent().data('itemData');
	    							var wndNum = Player.getWndnumByPid(itemData.ID);
	    							if($(this).hasClass('on')){//正在录像
	    								P.StopRecord(wndNum, true);
	    							}else{
	    								P.StartRecord(wndNum);
	    							}
	    							return false;    							
	    						});  					   								
							$li.prepend($iconRecord);
    						var $iconCamera = $('<span class="icon icon-camera"></span>').attr('title','点击播放').click(function(event){
    	    							if(!P.StartRealPlay){
    	    								return false;
    	    							}
    	    							var itemData = $(this).parent().data('itemData');
    	    							if($(this).hasClass('on')){//正在播放
    	    								var wndNum = Player.getWndnumByPid(itemData.ID);
    	    								P.StopRealPlay(wndNum);
    	    							}else{
    	    								//播放前先检查选择窗口是否正在轮播
    	    								//播放窗口数据
    	    								var wndData = P.m_wnds_data[P.m_iCurWnd];
    	    								//如果是轮播窗口，则先停止轮播
    	    								if(wndData && wndData.fzid){
    	    									fz.player.stopFzPlay(wndData.fzid);
    	    									return;
    	    								}    	    								
    	    								P.StartRealPlay(itemData.SBID, itemData.INDNUM, undefined, itemData.SDKIND, itemData.ID);
    	    							}
    	    							return false;
    	    						});   								
    						$li.prepend($iconCamera);
    						$ul.append($li);
    					}
    				}else{
						var $li = $('<li class="on">没有数据!</li>');
						$ul.append($li);
    				}
    			});			
			},			
			//获取场所列表的 过滤参数
			getFilterParam_cslist: function(){
				//获取机构树的过滤参数
				var param = page.even_handler.getFilterParam_treeJg();
				$('#jgmc').html(userinfo.jgmc);
				param.jgIds = userinfo.jgid;
				var name = $('#name_filter_cs').val();
				if(name != ''){
					param.name = name;
				}
				return param;
			},
			//获取机构树的 过滤参数
			getFilterParam_treeJg: function(){
				var param = {};
				var $conditionForm = $('#form_cslist_condition');
				var hylbCodes = [];
				var hylbNames = [];
				var jyztCodes = [];
				var jyztNames = [];
				var jrztCodes = [];//接入状态
				var jrztNames = [];
				$conditionForm.find(':checkbox[name="hylbs"]').each(function(i,n){
					if(n.checked){
						hylbCodes.push(n.value);
						hylbNames.push($(this).next().html());
					}
				});
				$conditionForm.find(':checkbox[name="jyzt"]').each(function(i,n){
					if(n.checked){
						jyztCodes.push(n.value);
						jyztNames.push($(this).next().html());
					}
				});
				$conditionForm.find(':checkbox[name="jrzt"]').each(function(i,n){
					if(n.checked){
						jrztCodes.push(n.value);
						jrztNames.push($(this).next().html());
					}
				});
				var filter_set_info = [];
				if(hylbCodes.length > 0){
					param.hylbs = hylbCodes.join(',');
					filter_set_info.push("行业类型:" + hylbNames.join(','));
				}
				if(jyztCodes.length > 0){
					param.jyzts = jyztCodes.join(',');
					filter_set_info.push("经营状态:" + jyztNames.join(','));
				}
				if(jrztCodes.length > 0){
					param.jrzts = jrztCodes.join(',');
					filter_set_info.push("接入状态:" + jrztNames.join(','));
				}
				if(filter_set_info.length > 0){
					var set_info = filter_set_info.join('；');
					$('#filter_set_info').attr('title',set_info).html(set_info);
				}else{
					$('#filter_set_info').attr('title','').html('全部场所');
				}
				//如果接入状态已经有了，则覆盖表单参数
				var jrzts = $('#tree_jg').attr('jrzts');
				if(jrzts != undefined){
					param.jrzts = jrzts;
				}
				return param;
			}
		},
		sdk: {
			//获取视频通道
			loadChannel: function(index, btn){
				if(!window['vali_form_sb_'+index].form()){
					return false;
				}
				var boxId = 'form_sb_'+index+'_channel';
				var $box = $('#'+boxId);
				var loaded = $box.attr('loaded');
				if(loaded=='1'){
					if(!confirm('已经获取过了视频通道信息,您是否需要重新获取？')){
						return false;
					}
				}
				var formData = Utils.getFormData('form_sb_'+index);
				page.load(boxId,ctx + '/sb/channel_data',formData,function(){
					$box.attr('loaded','1');
				});
			},
			//设备测试连接
			checkConnect: function(index, btn){
				if(!window['vali_form_sb_'+index].form()){
					return false;
				}
				var formData = Utils.getFormData('form_sb_'+index);
				var $btn = $(btn);
				$btn.find('.smaill-loading').removeClass('none');
				$btn.attr('title','').find('.js-state').addClass('none');
				$.getJSON(ctx + '/sb/testConn?r='+Math.random(), formData, function(jsonData){
					$btn.find('.smaill-loading').addClass('none');
					var $state = $btn.find('.js-state').removeClass('none');
					if(jsonData.conn == '1'){
						$state.removeClass('error').addClass('ok').html('-连接成功!');
					}else{
						$state.removeClass('ok').addClass('error').html('-连接失败!');
						$btn.attr('title', jsonData.message);
					}
				});
			}
		},
		//统一的加载页面调用方法
		load: function(wrapId,url,data,callback){
			var data = data || {};
			data.r = Math.random();
	        var $wrap = $('#'+wrapId);
	        $wrap.html('<div class="paper-loading">正在加载数据...</div>');
	        $wrap.load(url, data, callback);			
		},
	  initPlayPlugin: function(opt){
		  //检测插件
		  checkPlugin();
		  m_PreviewOCX = document.getElementById('PreviewActiveX');
		  //视频多窗口切换
		  $('#lnk_ArrangeWnd,#pop_menu_box_wnd').hover(function(){
			  window.clearTimeout(window['pop_menu_box_wnd_tid']);
			  $('#pop_menu_box_wnd').css('display','block');
		  }, function(){
			  window['pop_menu_box_wnd_tid'] = setTimeout(function(){
				  $('#pop_menu_box_wnd').css('display','none');
			  }, 1000);
		  });
		  var $arrWndA = $('#pop_menu_box_wnd > ul.sub-menus a');
		  $arrWndA.click(function(){
			  var $self = $(this);
			  var iWindowType = +$self.attr('w');
			  if(P.ChangeWnd(iWindowType)){
				  $arrWndA.each(function(i,n){
					  var w = +$(n).attr('w');
					  if($self[0] == n){
						  $(n).attr('class', 'bgicon-Wnd'+(w*w)+'-on');
					  }else{
						  $(n).attr('class', 'bgicon-Wnd'+(w*w));
					  }
				  });
			  }
		  });
		  $('#btn_group_stype').find('button.btn').click(function(){
			  var stype = $(this).attr('stype');
			  P.switchStreamType(stype);
		  });
		  //初始化播放控件的其它执行代码
		  if(typeof(opt.callFun) == 'function'){
			  opt.callFun();
		  }
		  function resize(){
			  //设备播放器的高度
			  var $wrap = $('#play_plugin_wrap');
			var cHeight = page.util.getClientHeight()-$wrap.offset().top - 40;
			if(opt.offsetHeight){
				cHeight += opt.offsetHeight;
			}
			$wrap.find('> div.activeX_box').css({height: cHeight+'px'});
			if(typeof(opt.onResize) == 'function'){
				opt.onResize();
			}
		  }
		  if(opt == undefined || opt.isResize != '0'){
			  resize();
			  $(window).on('resize', resize);		  
		  }
		},
	    initFitSize: function($selecter, offsetHeight){
	    	function _resize(){
	    		var cHeight = page.util.getClientHeight();
	    		$selecter.each(function(){
	    			var $self = $(this);
	    			var top = $self.offset().top;
	    			var h = 0;
	    			$self.next().each(function(){
	    				h += $(this).outerHeight();
	    			});
	    			if(typeof(offsetHeight) == 'number'){
	    				h += offsetHeight;
	    			}
	    			$self.css('height', (cHeight - top - h) + 'px');
	    		});
	    	}
		    $(document).ready(_resize);
		    $(window).resize(_resize); 	    	
	    },
	    showMsg: function(msg, act){
	    	var bgColor = '#05d605';
	    	switch(act){
		    	case 'warning': bgColor = '#f5a24d'; break;//橙
		    	case 'error': bgColor = '#d64f39'; break;//红
		    	case 'ok': bgColor = '#05d605'; break;//绿
	    	}
	    	//清除定时器
	    	window.clearTimeout(window['_frameMsgTimeoutId_']);
	    	//显示消息
	    	$('#frame_tips').css('background-color', bgColor).html(msg).show();
	    	//定时器6秒后关闭
	    	window['_frameMsgTimeoutId_'] = setTimeout(function(){
	    		$('#frame_tips').hide();
	    	}, 6000);
	    },
	    d: function(){
			      page.win.popWin({
				      	title: '-',
				      	url: '<p>URL</p>',
				      	width: 360,
				      	height: 160,
				      	isBgIframe: true		      	
				      });
			}
};