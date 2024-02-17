<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@include file="../Common/common_top.jsp"%>

<title>My Query</title>
</head>

<body>

	<div class="row">

		<%@include file="../Common/side_menu.jsp"%>
		
		<div style="height: 100px"></div>

		<div class="col-sm-9  offset-sm-3 vh-100 border-start border-1">

			<%@include file="../Common/common_header.jsp"%>


			<div class="row m-2 mt-5">

				<c:forEach items="${myList}" var="item">

					<div class="col-sm-6">
						<div class="card border-dark mb-3">
							<div class="card-header bg-primary text-light">${item.getTitle()}</div>
							<div class="card-body text-dark">
								<h6 class="card-title">Asked on : ${item.getAskedOn()}</h6>
								<div style="max-height: 12rem; overflow: scroll; overflow-x: hidden ">
									<p class="card-text">${item.getContent()}</p>
								</div>
							</div>
							<div class="row justify-content-end">
								<a class="col-sm-4 btn btn-outline text-right"
									href="${pageContext.request.contextPath}/read?query=${item.getQuery_id()}">Read
									More</a>
							</div>
						</div>

					</div>
				</c:forEach>

			</div>

		</div>
	</div>

	<%@include file="../Common/common_script.jsp"%>
</body>

</html>