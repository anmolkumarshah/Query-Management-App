<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="../Common/common_top.jsp"%>

<title>Edit Query</title>
</head>

<body>

	<div class="row">

		<%@include file="../Common/side_menu.jsp"%>
		
		<div style="height: 100px"></div>

		<div class="col-sm-9  offset-sm-3 vh-100 border-start border-1">

			<%@include file="../Common/common_header.jsp"%>


			<div class="row p-3">

				<form:form action="/query/update" modelAttribute="oldQuery"
					method="POST">



					<div class="form-floating mb-3">

						<form:input path="query_id" type="hidden" />


						<form:input path="title" cssClass="form-control"
							id="floatingInput" />
						<label for="floatingInput">Title</label>
						<form:errors path="title" cssClass="text-danger h6" />
					</div>

					<div class="form-floating mb-3">
						<form:textarea path="content" class="form-control" id="content"
							style="height: 100%" rows="20" placeholder="Enter Content" />

						<label for="floatingTextarea2">Content</label>
						<form:errors path="content" cssClass="text-danger h6" />
					</div>

					<div class="form-floating mb-3 row">

						<div class="lead">Selected Tags</div>

						<div class="row p-3">

							<div class="">
								<c:forEach items="${tags}" var="t">
									<div class="d-inline mx-1 ">
										<span class="badge bg-info text-dark">${t.getName().toUpperCase()}</span>
									</div>
								</c:forEach>
							</div>

						</div>
						<form:errors path="tags" cssClass="text-danger h6" />
					</div>

					<div class="lead mb-2">Selected Images</div>
					<div class="row my-2">
						<c:forEach items="${attachments}" var="item">
							<div class="col-sm-3">
								<div class="card my-1">
									<div class="card-body">
										<img alt="img" style="width: 100%"
											src="${pageContext.request.contextPath}/image/${item.getAttachmentId()}" />
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
					<hr>

					<input class="btn btn-primary" type="submit" value="Update Query" />


				</form:form>

			</div>




		</div>
	</div>

	<%@include file="../Common/common_script.jsp"%>
</body>

</html>