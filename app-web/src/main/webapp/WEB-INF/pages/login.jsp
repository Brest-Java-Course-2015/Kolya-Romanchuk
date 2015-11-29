<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head lang="ru">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">

	<title>Login</title>

	<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet"/>
	<link href="<c:url value="/resources/css/signin.css"/>" rel="stylesheet"/>

	<style>
		.error {
			padding: 15px;
			margin-bottom: 20px;
			border: 1px solid transparent;
			border-radius: 4px;
			color: #a94442;
			background-color: #f2dede;
			border-color: #ebccd1;
		}

		.msg {
			padding: 15px;
			margin-bottom: 20px;
			border: 1px solid transparent;
			border-radius: 4px;
			color: #31708f;
			background-color: #d9edf7;
			border-color: #bce8f1;
		}

		#login-box {
			width: 300px;
			padding: 20px;
			margin: 100px auto;
			background: #fff;
			-webkit-border-radius: 2px;
			-moz-border-radius: 2px;
			border: 1px solid #000;
		}
	</style>
</head>
<body onload='document.loginForm.username.focus();'>
<div class="container">

	<form class="form-signin" action="<c:url value="/j_spring_security_check"/>" method="post" style="width: 300px;">

		<h2 class="form-signin-heading" align="center">Авторизация:</h2>
		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
		<label class="sr-only">Логин</label>
		<input type="text" class="form-control" name="j_login" placeholder="Логин" required autofocus>
		<label class="sr-only">Пароль</label>
		<input type="password" class="form-control" name="j_password" placeholder="Пароль" required autofocus>
		<button class="btn btn-lg btn-primary btn-block" type="submit">Вход</button>
		<input type="hidden" name="${_csrf.parameterName}"
			   value="${_csrf.token}" />
	</form>

</div>
</body>
</html>