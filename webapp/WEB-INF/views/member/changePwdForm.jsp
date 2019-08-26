<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
</head>
<body>
    <form:form id="updateForm" modelAttribute="changeCommand">
        <p>
            <label>
                현재 비밀번호 : <br />
                <form:input path="currentPassword" />
                <form:errors path="currentPassword" />
            </label>
        </p>
        <p>
            <label>
                새로운 비밀번호 : <br />
                <form:input path="newPassword" />
                <form:errors path="newPassword" />
            </label>
        </p>
        <input type="submit" value="비밀번호 수정" />
    </form:form>
</body>
</html>