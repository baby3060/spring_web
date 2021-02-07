<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
    <form name="stepfrm" method="post">
    
    	<input type="hidden" name="name" value="${name}" />
    	<input type="hidden" name="email" value="${email}" />
    	<input type="hidden" name="agree" value="${agree}" />
    	<input type="hidden" name="mobile" value="${mobile}" />
    	
        <label>
            <input type="checkbox" name="agree2" value="true"> <spring:message code="term.agree" />
        </label>

        <input type="button" onclick="submitFrm()" value="<spring:message code="next.btn" />" />
    </form>
    
	<script>
	function submitFrm() {
		
		var checked = document.stepfrm.agree2.checked ;
		
		if(  checked ) {
			document.stepfrm.agree.value = "true";
			document.stepfrm.action = "step2";
	 		document.stepfrm.submit();
		} else {
			alert("약관 체크");
		}
	} 		
   </script>  	
    
    <c:if test="${ null ne mobile && '' ne mobile}">
		 <script>
		 	(function() {
		 		document.stepfrm.action = "step2";
		 		document.stepfrm.submit();
		 	})();
    	</script>  	
    </c:if>
    
</body>
</html>