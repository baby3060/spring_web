<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경 완료</title>
</head>
<body>
    <form:form id="updateForm" modelAttribute="changeCommand">
        <p>
            비밀번호 변경 완료
        </p>
        <p>
            <a href="<c:url value='/main.do' />">
                메인으로
            </a>
        </p>
    </form:form>
</body>
</html>