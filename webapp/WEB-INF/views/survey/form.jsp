<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문 조사</title>
</head>
<body>
	<h2>Spring Form을 이용한 설문조사</h2>
    <form:form method="post" modelAttribute="answerData">
        <p>
            1. 당신의 역할은?<br />
            <form:radiobutton path="responses[0]" value="서버" label="서버 개발자"/>
            <form:radiobutton path="responses[0]" value="프론트" label="프론트 개발자"/>
            <form:radiobutton path="responses[0]" value="풀스택" label="풀스택 개발자"/>
        </p>
        <p>
            2. 가장 많이 사용하는 개발도구는?<br />
            <form:radiobutton path="responses[1]" value="Eclipse" label="이클립스"/>
            <form:radiobutton path="responses[1]" value="Intelij" label="인텔리 J"/>
            <form:radiobutton path="responses[1]" value="Sublime" label="서브라임 텍스트"/>
        </p>

        <p>
            3. 하고 싶은 말<br />
            <form:input path="responses[2]" />
        </p>

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