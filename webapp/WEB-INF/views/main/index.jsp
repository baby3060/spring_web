<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <c:if test="${empty info}">
        <p>환영합니다</p>
        <h2>${greeting}</h2>
        <p><a href="<c:url value='/login' />">로그인 하러 가기</a></p>
        <p><a href="<c:url value='/register/step1' />">회원 가입 화면으로 이동</a></p>
    </c:if>

    <c:if test="${not empty info}">
        <h2>Hello~<c:out value="${info.name}" /></h2>
        <p><a href="<c:url value='/survey' />">설문조사 화면으로 이동</a></p>
        <p><a href="<c:url value='/logout' />">로그아웃</a></p>
        <p><a href="<c:url value='/member/changepwd' />">비밀번호 변경하러 가기</a></p>
    </c:if>
</body>
</html>