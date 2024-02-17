<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@include file="Common/common_top.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<title>Signup</title>
</head>

<body>


	<div class="vh-100 "
		style="background-image: url(https://cdn.pixabay.com/photo/2021/03/26/14/37/gears-6126071_960_720.png); background-size: cover;">

		<div class="h-100 row justify-content-center align-items-center">
			<div class="col-md-6 border border-3  rounded-3 bg-light mt-5 p-5 ">
				<div class="text-center display-5">Signup</div>

				<c:if test="${msg != null}">
					<div class="alert alert-primary mt-4" role="alert">${msg}</div>
				</c:if>

				<form:form action="registerUser" method="POST"
					modelAttribute="signup_user">

					<div class="mb-3">
						<label class="form-label">Name</label>
						<form:input path="name" cssClass="form-control" />
						<form:errors cssClass="text-danger h6" path="name" />
					</div>

					<div class="mb-3">
						<label class="form-label">Email</label>
						<form:input path="email" cssClass="form-control" />
						<form:errors cssClass="text-danger h6" path="email" />
					</div>

					<div class="mb-3">
						<label class="form-label">Password</label>
						<form:input path="password" cssClass="form-control" />
						<form:errors cssClass="text-danger h6" path="password" />
					</div>

					<div class="mb-3">
						<button type="submit" class="btn btn-primary">Create
							Account</button>
						<a href="${pageContext.request.contextPath}/login"
							class="btn btn-warning">Login</a> <a
							href="${pageContext.request.contextPath}/" class="btn btn-info">Home</a>
					</div>

				</form:form>
			</div>

		</div>



		<%@include file="Common/common_script.jsp"%>
</body>

</html>