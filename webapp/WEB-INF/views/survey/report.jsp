<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문 조사 결과</title>
</head>
<body>
	<p>응답 내용</p>
    <ul>
        <c:forEach var="response" items="${ansData.responses}" varStatus="status">
            <li>${status.index+1}번 문항 : ${response}</li>
        </c:forEach>
    </ul>
    <p>응답자 위치 : <c:out value="${ansData.res.location}" escapeXml="false"/></p>
    <p>응답자 나이 : <c:out value="${ansData.res.age}" escapeXml="false"/></p>

    <a href="<c:url value='/main'/>">첫 페이지로</a>
</body>
</html>