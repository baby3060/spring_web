<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 완료</title>
</head>
<body>
	회원가입 완료
    <p><strong>${registerMember.name}</strong> 가입 완료</p>
    
    <a href="<c:url value='/main'/>">첫 페이지로</a>
</body>
</html>