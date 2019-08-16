<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title><spring:message code="member.register" /></title>
</head>
<body>
    <h2>회원 정보 입력</h2>
    <form:form id="joinForm" action="joinproc" modelAttribute="registerMember"
        cssStyle="background-color : gray; color : red;"
    >
        <p>
            <label for="email"><spring:message code="email" />:<br />
                <form:input path="email" />
                <form:errors path="email" />
            </label>
            <form:checkbox path="allowMail" label="이메일 수신을 허용합니다." />
        </p>

        <p>
            <label for="name"><spring:message code="name" />:<br />
                <form:input path="name" />
                <form:errors path="name" />
            </label>
        </p>

        <p>
            <label for="password"><spring:message code="password" />:<br />
                <form:password path="password" />
                <form:errors path="password" />
            </label>
        </p>
        <p>
            <label for="confirmPassword"><spring:message code="password.confirm" />:<br />
                <form:password path="confirmPassword" />
                <form:errors path="confirmPassword" />
            </label>
        </p>
        
        <p>
            <label for="loginType"><spring:message code="member.type" />:<br />
                <form:select path="loginType">
                    <option value="">---선택하세요.---</option>
                    <form:options items="${loginTypes}" />
                </form:select>
            </label>
        </p>

        <p>
            <label for=""><spring:message code="member.favoritefood" /> : <br />
                <form:checkboxes items="${favoriteFoodArr}" path="favoriteFoods" />
            </label>
        </p>

        <input type="submit" value='<spring:message code="register.btn" />'>
    </form:form>
</body>
</html>