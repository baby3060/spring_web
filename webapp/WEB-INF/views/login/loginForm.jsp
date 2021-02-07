<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/style.css">

<meta charset="UTF-8">
<title><spring:message code="login.title" /></title>
</head>
<body>
	<form:form id="loginForm" modelAttribute="command">
        <form:errors  />
        <p>
            <label>
                <spring:message code="email" />:<br />
                <form:input path="email" />
                <form:errors path="email" />
            </label>
        </p>

        <p>
            <label>
                <spring:message code="password" />:<br />
                <form:password path="password" />
                <form:errors path="password" />
            </label>
        </p>

        <p>
            <label>
                <spring:message code="login.rememberEmail" />:<br />
                <form:checkbox path="rememberEmail" />
            </label>
        </p>
		
        <input type="submit" value='<spring:message code="login.btn" />'>
        <div id="naver_id_login" style="">
        	<a href="#" onclick="javascript:naver_login_req('${url}')">
				<img width="223" src="https://developers.naver.com/doc/review_201802/CK_bEFnWMeEBjXpQ5o8N_20180202_7aot50.png"/>
			</a>
		</div>
        
    </form:form>
    
    <script>
    	function naver_login_req(url) {
    		// Popup
    		
    		window.open(url, "pop1", "width=600,height=600");
    	}
    </script>
    
</body>
</html>