<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <h2>회원 정보 입력</h2>
    <form:form id="joinForm" action="joinproc" modelAttribute="registerMember">
        <p>
            <label for="email">이메일:<br />
                <form:input path="email" />
            </label>
        </p>

        <p>
            <label for="name">이름:<br />
                <form:input path="name" />
            </label>
        </p>

        <p>
            <label for="password">비밀번호:<br />
                <form:password path="password" />
            </label>
        </p>
        <p>
            <label for="confirmPassword">비밀번호 확인:<br />
                <form:password path="confirmPassword" />
            </label>
        </p>
        
        <input type="submit" value="가입 완료">
    </form:form>
</body>
</html>