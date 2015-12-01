<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page session="true"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
	<title>Главная</title>

	<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/dashboard.css"/>" rel="stylesheet">


</head>
<body>
<c:if test="${pageContext.request.userPrincipal.name != null}">
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
	<nav class="navbar navbar-inverse navbar-fixed-top">
    		<div class="container-fluid">
    			<div class="navbar-header">
    				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
    					<span class="sr-only"></span>
    					<span class="icon-bar"></span>
    					<span class="icon-bar"></span>
    					<span class="icon-bar"></span>
    				</button>
    				<a class="navbar-brand" href="${pageContext.request.contextPath}/admin">${pageContext.request.userPrincipal.name}</a>
    			</div>
    			<div id="navbar" class="navbar-collapse collapse">
    				<ul class="nav navbar-nav navbar-right">
    					<li><a href="javascript:formSubmit()">Выход</a></li>
    				</ul>
    			</div>
    		</div>
    </nav>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-3 col-md-2 sidebar">
                <ul class="nav nav-sidebar">
                    <li><a href="${pageContext.request.contextPath}/admin">Все пользователи</a></li>
                </ul>
            </div>
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

                <h2>Новый пользователь:</h2>

                <from id="addUser">
                    <div class="form-user">
                        <label>Логин:</label>
                        <input id="userLogin" name="userLogin" type="text">
                        <p/>
                        <label>Пароль:</label>
                        <input id="userPassword" name="userPassword" type="text">
                        <p/>
                        <label>Имя:</label>
                        <input id="userFirstname" name="userFirstname" type="text">
                        <p/>
                        <label>Фамилия:</label>
                        <input id="userLastname" name="userLastname" type="text">
                        <p/>
                        <a class="btn btn-primary" href="${pageContext.request.contextPath}/admin">Отменить</a>
                        <button class="btn btn-primary"  id="btnAddUser">Зарегистрировать</button>
                    </div>
                </from>

            </div>
        </div>
    </div>

</c:if>
	<script src="<c:url value="/resources/js/jquery.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
	<script src="<c:url value="/resources/js/adduser.js"/>"></script>
</body>
</html>
