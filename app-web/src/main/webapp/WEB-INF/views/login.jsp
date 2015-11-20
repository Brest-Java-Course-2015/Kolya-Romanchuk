<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
    <head lang="ru">
        <meta charset="utf-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <meta name="description" content="">
            <meta name="author" content="">

        <title>Login</title>

        <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
        <link rel="stylesheet" href="<c:url value="/resources/css/signin.css"/>">

    </head>
    <body>
        <div class="container">

        <form class="form-signin" style="width: 300px;">
            <c:url value="/j_spring_security_check" var="loginUrl" />
            <h2 class="form-signin-heading">Авторизация</h2>
                <label for="inputLogin" class="sr-only">Логин</label>
                <input type="text" class="form-control" name="j_login" placeholder="Логин" required autofocus>
                <label for="inputPassword" class="sr-only">Пароль</label>
                <input type="password" class="form-control" name="j_password" placeholder="Пароль" required>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Вход</button>
        </form>

        </div>
    </body>
</html