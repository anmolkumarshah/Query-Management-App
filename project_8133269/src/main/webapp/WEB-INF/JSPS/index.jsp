<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@include file="Common/common_top.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<title>Login</title>
</head>

<body>


	<div class="vh-100 "
		style="background-image: url(https://cdn.pixabay.com/photo/2021/03/26/14/37/gears-6126071_960_720.png); background-size: cover;">

		<div class="h-100 row justify-content-center align-items-center">
			<div class="col-md-6 border border-3  rounded-3 bg-light mt-5 p-5 ">
				<div class="text-center display-5">Login</div>

				<c:if test="${msg != null }">
					<div class="alert alert-primary mt-4" role="alert">${msg}</div>
				</c:if>

				<form action="login" method="POST">

					<c:if test="${param.error != null}">
						<div class="alert alert-danger mt-2">
							<strong>ERROR!</strong> Invalid Email or Password
						</div>
					</c:if>

					<div class="mb-3">
						<label class="form-label">Email address</label> <input
							name="username" type="email" class="form-control">
					</div>
					<div class="mb-3">
						<label class="form-label">Password</label> <input name="password"
							type="password" class="form-control">
					</div>

					<div class="btn-group" role="group" aria-label="Basic example">
						<button type="submit" class="btn btn-primary">Login</button>

						<a href="${pageContext.request.contextPath}/signup"
							class="btn btn-warning">Create Account</a> 
						<a
							href="${pageContext.request.contextPath}/"
							class="btn btn-info">Home</a>
					</div>


				</form>
			</div>

		</div>



		<%@include file="Common/common_script.jsp"%>
</body>

</html>