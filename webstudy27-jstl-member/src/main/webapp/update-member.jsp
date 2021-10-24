<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정폼</title>
</head>
<body>
<a href="index.jsp">Home</a>
<form action="UpdateMemberController.do" method="post">
	아이디 <input type="text" name="id"  value="${sessionScope.mvo.id}" readonly="readonly"><br>
	패스워드 <input type="text" name="password"   value="${sessionScope.mvo.password}" required="required"><br>
	이름 <input type="text" name="name"   value="${sessionScope.mvo.name}" required="required"><br>
	주소 <input type="text" name="address"   value="${sessionScope.mvo.address}" required="required"><br>
	생년월일 <input type="date" name="birthday"   value="${sessionScope.mvo.birthday}" required="required"><br>
	회원정보 <button> 수정</button>
</form>
</body>
</html>