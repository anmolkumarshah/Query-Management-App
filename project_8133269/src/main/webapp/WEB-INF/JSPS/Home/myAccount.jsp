<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="../Common/common_top.jsp"%>

<title>My Account</title>
</head>

<body>

	<div class="row">

		<%@include file="../Common/side_menu.jsp"%>

		<div class="col-sm-9  offset-sm-3 vh-100 border-start border-1">

			<%@include file="../Common/common_header.jsp"%>
			<div style="height: 100px"></div>

			<div class="container">
				<c:if test="${msg != null }">
					<div class="alert alert-primary mt-4" role="alert">${msg}</div>
				</c:if>
				
				<form:form action="update" method="POST" modelAttribute="current">

					<div class="mb-3">
						<label class="form-label">Name</label>
						<form:input path="userId" type="hidden" cssClass="form-control" />
						<form:input path="name" cssClass="form-control" />
						<form:errors cssClass="text-danger h6" path="name" />
					</div>

					<div class="mb-3">
						<label class="form-label">Email</label>
						<form:input path="email" cssClass="form-control" />
						<form:errors cssClass="text-danger h6" path="email" />
					</div>


					<div class="mb-3">
						<button type="submit" class="btn btn-primary">Update
							Account</button>
					</div>

				</form:form>
			</div>

		</div>
	</div>





	<%@include file="../Common/common_script.jsp"%>
</body>

</html>