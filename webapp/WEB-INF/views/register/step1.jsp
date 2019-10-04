<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>회원가입 단계_1</title>
</head>
<body>
    <h2><spring:message code="term" /></h2>
    <p><spring:message code="term.content" /></p>
    <form action="step2">
        <label>
            <input type="checkbox" name="agree" value="true"> <spring:message code="term.agree" />
        </label>

        <input type="submit" value="<spring:message code="next.btn" />">
    </form>
</body>
</html>