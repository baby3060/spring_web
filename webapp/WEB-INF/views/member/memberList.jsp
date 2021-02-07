<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>멤버 리스트</title>
<script src="${pageContext.request.contextPath}/resources/js/Calendar.js"></script>
</head>
<body>
    <p>
        <a href="<c:url value='/main.do' />">
            메인으로
        </a>
    </p>

    <form:form id="listForm" modelAttribute="searchCommand">
        <form:hidden path="search" value="N" />
        <p>
            가입일자 조회
        </p>
        <p>
            <label>
                시작일자 : <br />
                <form:input path="from" />
                <input type="button" onClick="_Calendar_On(from)" />
                ~
                <form:input path="to" />
                <input type="button" onClick="_Calendar_On(to)" />
            </label>
        </p>
        
        <input type="button" value="조회" onClick="submitClick()" />
    </form:form>
</body>


<script>
    function submitClick() {
        document.listForm.search.value = "Y";
        document.listForm.submit();
    }
</script>
</html>