<%@ page contentType="text/html;charset=gbk" language="java"%>
<html>
	<head>
		<title>����request��ʹ��</title>
	</head>
	<body>
		<form id="form1" method="post" action="request1.jsp">
		�û�����<br>
		<input type="text" name="username"/><hr>
		�Ա�<br>
		�У�<input type="radio" name="gender" value="��"/>
		Ů��<input type="radio" name="gender" value="Ů"/><hr>
		ϲ������ɫ��<br>
		�죺<input type="checkbox" name="color" value="��"/>
		�̣�<input type="checkbox" name="color" value="��"/>
		����<input type="checkbox" name="color" value="��"/><hr>
		���ԵĹ��ң�<br>
		<select name="country">
			<option value="�й�">�й�</option>
			<option value="����">����</option>
			<option value="����˹">����˹</option>
		</select><hr>
		<input type="submit" value="�ύ"/>
		<input type="reset" value="����"/><hr>
		</form>
	</body>
</html>