<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="../Common/common_top.jsp"%>

<title>Ask Query</title>
</head>

<body>

	<div class="row">

		<%@include file="../Common/side_menu.jsp"%>

		<div style="height: 100px"></div>
		<div class="col-sm-9  offset-sm-3 vh-100 border-start border-1">

			<%@include file="../Common/common_header.jsp"%>


			<div class="row p-3">

				<form:form action="/query/save" modelAttribute="newQuery"
					method="POST" enctype="multipart/form-data">


					<div class="form-floating mb-3">
						<form:input path="title" cssClass="form-control"
							id="floatingInput" />
						<label for="floatingInput">Title</label>
						<form:errors path="title" cssClass="text-danger h6" />
					</div>

					<div class="form-floating mb-3">
						<form:textarea path="content" class="form-control" id="content"
							style="height: 200px" rows="100" placeholder="Enter Content" />

						<label for="floatingTextarea2">Content</label>
						<form:errors path="content" cssClass="text-danger h6" />
					</div>

					<div class="form-floating mb-3 row">

						<div class="lead">Select Tags</div>

						<div class="row p-3">

							<c:forEach items="${tags}" var="t">
								<div class="col-3">
									<form:checkbox path="tagsId" value="${t.getTag_id()}"
										label="${t.getName().toUpperCase()}" cssClass="form-check-input mx-2 h5" />
								</div>
							</c:forEach>

						</div>
						<form:errors path="tagsId" cssClass="text-danger h6" />
					</div>

					<div class="lead mb-2">Select Images</div>
					<div class="mb-3">
						<input class="form-control" type="file" name="images"
							accept="image/x-png,image/gif,image/jpeg" multiple>
					</div>

					<input class="btn btn-primary" type="submit" value="Create Query" />


				</form:form>

			</div>




		</div>
	</div>

	<%@include file="../Common/common_script.jsp"%>
</body>

</html>