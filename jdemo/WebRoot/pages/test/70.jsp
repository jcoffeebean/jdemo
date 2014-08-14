<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>70</title>
</head>
<!-- 多用组合，少用继承 -->
<style text="text/css">
.f12{font-size:12px}
.f16{font-size:16px}
.red{color:red}
.numberList{border:1px solid #ccc;padding:10px;}
.numberList li{height:20px;line-height:20px;}
</style>

<!--当不同的选择符样式有冲突时会采用权重高的选择符设置的样式：HTML标签的权重是1，class的权重是10，id的权重是100  -->
<!--如果CSS选择符权重相同则采用就近原则，哪个选择符最后定义就采用哪个选择符的样式 -->
<style type="text/css">
span{font-size:40px;color:green}
.test{color:red;font-size:40px;font-family:黑体;}
.test2{color:blue}
</style>
<body>
	<ul class="numberList f12">
		<li>1111111111</li>
		<li>2222222222</li>
	</ul>
	<ul class="numberList f16">
		<li>3333333333</li>
		<li>4444444444</li>
	</ul>
	<ul class="numberList f12 red">
		<li>5555555555</li>
		<li>6666666666</li>
	</ul>
	<ul class="numberList f16 red">
		<li>7777777777</li>
		<li>8888888888</li>
	</ul>
	<span class="test">123456789</span><br>
	<span class="test2 test">123456789</span>
</body>
</html>