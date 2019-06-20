<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix='c' %>

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
    <form action="joinproc" method="post">
        <p>
            <label for="">이메일:<br />
                <input type="text" name="email" id="email" value="${member.getEmail()}">
            </label>
        </p>

        <p>
            <label for="">이름:<br />
                <input type="text" name="name" id="name" value="${member.getName()}">
            </label>
        </p>

        <p>
            <label for="">비밀번호:<br />
                <input type="password" name="password" id="password">
            </label>
        </p>
        <p>
            <label for="">비밀번호 확인:<br />
                <input type="password" name="confirmPassword" id="confirmPassword">
            </label>
        </p>
        
        <input type="submit" value="가입 완료">
    </form>
</body>
</html>