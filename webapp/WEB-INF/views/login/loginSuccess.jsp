<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="login.success.title" /></title>
</head>
<body>
    <p>
        <spring:message code="login.done" arguments="${info.name}" />
    </p>

    <p>
        <a href="<c:url value='/main.do'/>">[<spring:message code="go.main" />]</a>
    </p>
</body>
</html>