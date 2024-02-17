<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="../Common/common_top.jsp"%>

<title>Search Query</title>
</head>

<body>

	<div class="row">

		<%@include file="../Common/side_menu.jsp"%>

		<div class="col-sm-9  offset-sm-3 vh-100 border-start border-1">

			<%@include file="../Common/common_header.jsp"%>

			<div style="height: 100px"></div>
			<div class="px-3 my-2 ">
				<form action="textSearch" class="row">
					<div class="col-10">
						<input class="form-control me-2" type="search" name="text"
							placeholder="Search Your Query" aria-label="Search">
					</div>
					<div class="col-2">
						<button style="width: 100%" class="btn btn-outline-success"
							type="submit">Search</button>
					</div>
				</form>
			</div>

			<div class="px-3 my-2">
				<form:form action="tagSearch" class="row" modelAttribute="search">
					<div class="col-10">
						<form:select  cssClass="form-select form-select" path="selectedTag">
							<form:option selected="true" value="0">Search With Tag</form:option>
							<c:forEach items="${tags}" var="tag">
								<form:option value="${tag.getTag_id()}">${tag.getName()}</form:option>
							</c:forEach>
						</form:select>
					</div>
					<div class="col-2">
						<button style="width: 100%" class="btn btn-outline-success"
							type="submit">Search</button>
					</div>
				</form:form>
			</div>



			<div class="row m-2 mt-5">

				<c:forEach items="${list}" var="item">



					<div class="col-sm-6">
						<div class="card border-dark mb-3"">
							<div class="card-header display-5" style="font-size: 25px;">${item.getTitle()}</div>
							<div class="card-body text-dark">

								<h6 class="card-title">Asked on : ${item.getAskedOn()}</h6>
								<div
									style="max-height: 12rem; overflow: scroll; overflow-x: hidden">
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