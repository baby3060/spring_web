<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="register.complete" /></title>
</head>
<body>
	<spring:message code="register.complete" />
    <p><spring:message code="register.done" arguments="${registerMember.name}" /></p>
    
    <a href="<c:url value='/main'/>">[<spring:message code="go.main" />]</a>
</body>
</html>