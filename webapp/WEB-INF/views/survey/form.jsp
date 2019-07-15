<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문 조사</title>
</head>
<body>
	<h2>Spring Form을 이용한 설문조사</h2>
    <form:form method="post" modelAttribute="answerData">
        <c:forEach var="question" items="${questions}" varStatus="status">
            <p>
                ${status.index + 1}.${question.title}<br />
                <c:if test="${question.choice}">
                    <c:forEach var="text" items="${question.options}">
                        <form:radiobutton path="responses[${status.index}]" value="${text}" label="${text}"/>
                    </c:forEach>
                </c:if>
                <c:if test="${!question.choice}">
                    <form:input path="responses[${status.index}]" />
                </c:if>
            </p>
        </c:forEach>
        

        <p>
            <label>응답자 위치:<br />
            <form:input path="res.location" />
            </label>
        </p>

        <p>
            <label>응답자 나이:<br />
            <form:input path="res.age" />
            </label>
        </p>
        
        <input type="submit" value="전송">
    </form:form>
</body>
</html>