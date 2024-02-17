<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="../Common/common_top.jsp"%>

<title>My Query</title>
</head>

<body>

	<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Write Your Answer</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">

					<form:form
						action="${pageContext.request.contextPath}/comment/create?query=${query.getQuery_id()}"
						method="POST" modelAttribute="new_comment">

						<div class="mb-3">

							<div class="mb-3">

								<form:textarea path="content" class="form-control is-invalid"
									id="validationTextarea" placeholder="Type Answer Here ..."
									required="true"></form:textarea>
								<div class="invalid-feedback">Please enter a message in
									the textarea.</div>
							</div>
							<form:errors cssClass="text-danger h6" path="content" />
						</div>

						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-primary">Submit
								Answer</button>
						</div>

						<div class="mb-3"></div>

					</form:form>


				</div>

			</div>
		</div>
	</div>

	<div class="row">

		<%@include file="../Common/side_menu.jsp"%>
		<div class="vr"></div>
		<div class="col-sm-9 offset-sm-3">

			<%@include file="../Common/common_header.jsp"%>

			<div style="height: 100px"></div>

			<div class="row m-2 mt-5">
				<div class="container">
					<div class="row">
						<div class="col-sm-9">
							<div class="display-5 fs-2">
								Question : <strong> ${query.getTitle()} </strong>
							</div>
							<div>
								<span class="fw-light">Asked By :</span> <span class="fw-italic">${user.getName()}</span>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="btn-group btn-group-sm" role="group"
								aria-label="Basic mixed styles example">
								<a
									href="${pageContext.request.contextPath}/query/upvote?query=${query.getQuery_id()}"
									type="button" class="btn btn-primary">Useful</a>

								<button type="button" class="btn btn-warning">${query.getVotes()}</button>

								<a
									href="${pageContext.request.contextPath}/query/downvote?query=${query.getQuery_id()}"
									type="button" class="btn btn-danger">Not Useful</a>
							</div>

						</div>
					</div>
					<hr>

					<div class="border p-3 rounded-3">
						<p>${query.getContent()}</p>
					</div>

					<div class="row my-2">
						<c:forEach items="${attachments}" var="item">
							<div class="col-sm-6">
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

					<div class="">
						<c:forEach items="${tags}" var="t">
							<div class="d-inline mx-1 ">
								<span class="badge bg-info text-dark">${t.getName().toUpperCase()}</span>
							</div>
						</c:forEach>
					</div>

					<hr>

					<div class="row">
						<div class="col">
							<div>
								<button type="button" class="btn m-1 btn-primary"
									data-bs-toggle="modal" data-bs-target="#staticBackdrop">
									Write Answer!</button>
								<c:if test="${currentUserId == user.getUserId()}">
									<a
										href="${pageContext.request.contextPath}/query/update?query=${query.getQuery_id()}"
										type="button" class="btn btn m-1 btn-warning">Edit</a>
									<a
										href="${pageContext.request.contextPath}/query/delete?query=${query.getQuery_id()}"
										type="button" class="btn btn m-1 btn-danger">Delete</a>
								</c:if>

							</div>
						</div>
					</div>


					<hr>
					<h5>
						Answers <span class="badge bg-secondary">${comments.size()}</span>
					</h5>

					<c:forEach items="${comments}" var="item">

						<!-- COMMENTS  -->

						<div class="border border-1 border-dark rounded-2 p-3 mt-2"
							style="background-color: rgb(204, 255, 217);">
							<div class="px-2">
								<div class="row ">
									<div class="col-sm-10">

										<p class="text-justify">${item.getContent()}</p>
									</div>
									<div class="col-sm-2 align-self-center  border-start">
										<div class="btn-group-vertical" role="group"
											aria-label="Third group">
											<a
												href="${pageContext.request.contextPath}/comment/upvote?comment=${item.getCommentId()}&query=${query.getQuery_id()}"
												type="button" class="btn btn-primary">upvote</a>
											<button type="button" class="btn btn-light" disabled>${ item.getVotes()}</button>
											<a
												href="${pageContext.request.contextPath}/comment/downvote?comment=${item.getCommentId()}&query=${query.getQuery_id()}"
												type="button" class="btn btn-danger">downvote</a>

										</div>

									</div>
								</div>
								<hr>
								<div class="row">
									<div class="col-10">
										<div>
											<span class="fw-light">Posted By :</span> <span
												class="fw-italic">${item.getUser().getName()}</span>
										</div>

										<div>
											<span class="fw-light">Posted On :</span> <span
												class="fw-italic">${item.getTimestamp()}</span>
										</div>
									</div>
									<c:if test="${item.getIsMarkedRight() == true }">
										<div class="col-1 mx-auto">
											<img alt="correct" width="40rem" height="40rem"
												src="https://cdn.pixabay.com/photo/2013/07/13/12/49/tick-160426_960_720.png">
										</div>
									</c:if>
									
									<c:if test="${item.getIsMarkedRight() != true &&  currentUserId == user.getUserId()}">
										<div class="col-1">
											<a href="${pageContext.request.contextPath}/comment/correct?comment=${item.getCommentId()}&query=${query.getQuery_id()}"  style="width: 100px" class="btn btn-warning" >Correct</a>
										</div>
									</c:if>

								</div>
							</div>
						</div>

					</c:forEach>

				</div>



			</div>

		</div>
	</div>





	<%@include file="../Common/common_script.jsp"%>
</body>

</html>