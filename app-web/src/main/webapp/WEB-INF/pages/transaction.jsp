<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page session="true"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
	<title>Банк</title>

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
				<a class="navbar-brand" href="${pageContext.request.contextPath}/user">${pageContext.request.userPrincipal.name}</a>
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
                                <li><a href="${pageContext.request.contextPath}/user">Счета</a></li>
                                <li class="active"><a href="${pageContext.request.contextPath}/user/${pageContext.request.userPrincipal.name}/transaction">Транзакции<span class="sr-only">(current)</span></a></li>
                                <li><a href="${pageContext.request.contextPath}/user/${pageContext.request.userPrincipal.name}/extract">Выписка</a></li>
                </ul>
			</div>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
						<tr>
							<th>Номер счета</th>
							<th>Баланс</th>
						</tr>
						</thead>
						<tbody id="checkList">
							<c:forEach var="checks" items="${checks}">
								<tr>
									<td>${checks.checknumber}</td>
									<td>${checks.summa}</td>
                                    <td>
										<input type="radio" name="inlineRadioOptions" id="radioCheck" value=${checks.checknumber}>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<from id="addTransaction">
						<div class="form-user">
							<label>Номер получателя:</label>
							<input id="checkRecipient" name="checkRecipient" type="text">
							<p/>
							<label>Сумма:</label>
							<input id="summa" name="summa" type="text">
							<p/>
								<button class="btn btn-primary"  id="btnAddTransaction">Перевод</button>
						</div>
					</from>
				</div>
				<hr />
			</div>
		</div>
	</div>
</c:if>
	<script src="<c:url value="/resources/js/jquery.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
	<script src="<c:url value="/resources/js/addtransaction.js"/>"></script>
</body>
</html>