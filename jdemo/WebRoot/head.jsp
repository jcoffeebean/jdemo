<%@ page contentType="text/html;charset=UTF-8" %>
	<div id="header">
		<div class="top-bar">
			<p class="user-bar">
				<span>您好，${param.menu}</span>
			</p>
		</div>
		<div class="h-box">
			<div id="frame_tips"></div>
			<div class="logo">
			<span class="logo-text">深圳市特行场所视频图像信息管理系统</span>
			</div>
	   		<div class="ui-menu">
	   			<ul>
	   				<li${param.menu=='device'?' class="selected"':''}><a href="">设备登记</a></li>
	   			</ul>
	   		</div>
		</div>
	</div>
