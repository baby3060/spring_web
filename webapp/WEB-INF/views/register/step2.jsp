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
    <form:form id="joinForm" action="joinproc" modelAttribute="registerMember"
        cssStyle="background-color : gray; color : red;"
    >
        <p>
            <label for="email">이메일:<br />
                <form:input path="email" />
            </label>
            <form:checkbox path="allowMail" label="이메일 수신을 허용합니다." />
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
        
        <p>
            <label for="loginType">회원유형:<br />
                <form:select path="loginType">
                    <option value="">---선택하세요.---</option>
                    <form:options items="${loginTypes}" />
                </form:select>
            </label>
        </p>

        <p>
            <label for="">좋아하는 과일 : <br />
                <form:checkboxes items="${favoriteFoodArr}" path="favoriteFoods" />
            </label>
        </p>

        <input type="submit" value="가입 완료">
    </form:form>
</body>
</html>