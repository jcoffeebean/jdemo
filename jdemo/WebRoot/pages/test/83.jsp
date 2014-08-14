<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
(function(){
	var a=0;
	alert(a);
})();
(function(){
	var a=0;
	alert("-"+a);
})();
</script>

<!-- 尽量不要用子选择器，CSS选择符需保证权重尽可能的低，提高可维护性 -->
<style type="text/css">
#test{font-size:14px;}
#test span{color:red}    /*选择符权重为：100+1=101*/
#test .font{color:green} /*选择符权重为：100+10=110*/
</style>

<!-- 方案二：新增class作为容器的选择符 -->
<style type="text/css">
#test{font-size:14px;}
.font{color:red}
.font2{color:blue}
</style>

<style type="text/css">
.nav li{float:left;display:inline;margin-right:10px;font-family:黑体;}
.nav a{float:left;width:139px;height:31px;line-height:31px;font-size:24px;color:green;text-decoration:none;text-align:center;backgroup:url(../../resource/image/avatar.jpg);}
a:visited{color:red;}
.nav a:hover{backgroup:url(../../resource/image/logo_lg.png)}
</style>

</head>
<body>
<p id="test">CSS选择符权重<span>很重要</span>，<span>我们要小心处理</span></p><br>
<p id="test">CSS选择符权重<span class="font">很重要</span>，<span class="font2">我们要小心处理</span></p><br>
<ul class="nav">
	<li><a href="#">联系我们</a></li>
	<li><a href="#">产品答疑</a></li>
	<li><a href="#">广告服务</a></li>
</ul>
</body>
</html>