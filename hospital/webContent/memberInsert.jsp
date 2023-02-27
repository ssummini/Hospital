<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
<title>생성</title>
</head>
<body>
<h3>회원 가입</h3>

${error}																 <!--오류가 발생하여 현재 페이지로 다시 돌아올때 오류 메시지를 출력하는 코드  -->
<!--입력 뷰  -->
<form action="memberInsert.do"  method="post">							<!-- 폼에서 입력받은 값을 처리할 서버 프로그램으로 memberInsert.do를 지정 요청방식은 post  -->

	ID : <input type="text"  name="id" > <br>
	이름 : <input type="text"  name="name" ><br>
	생일 : <input type="text" name="birthday"> <br>
	전화번호 : <input type="text" name="phone" > <br>

	<input type="submit"  value="가입" >

</form>
</body>
</html>