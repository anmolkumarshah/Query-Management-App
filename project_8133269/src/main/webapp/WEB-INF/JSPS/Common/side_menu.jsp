
<div class="col-sm-3 fixed-top offset-0" style="background-image: url(https://cdn.pixabay.com/photo/2021/03/26/14/37/gears-6126071_960_720.png); background-size: fit;">
	<div class="vh-100">

		<div class="list-group p-2">
			<c:choose>
				<c:when test="${page == 'home' }">
					<a href="${pageContext.request.contextPath}/"
						class="list-group-item list-group-item-action active">Home</a>
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath}/"
						class="list-group-item list-group-item-action ">Home</a>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${page == 'myQuery' }">
					<a href="${pageContext.request.contextPath}/user/queries"
						class="list-group-item list-group-item-action active">My Query</a>
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath}/user/queries"
						class="list-group-item list-group-item-action">My Query</a>
				</c:otherwise>
			</c:choose>


			<c:choose>
				<c:when test="${page == 'search' }">
					<a href="${pageContext.request.contextPath}/search"
						class="list-group-item list-group-item-action active">Search</a>
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath}/search"
						class="list-group-item list-group-item-action">Search</a>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${page == 'myAccount' }">
					<a href="${pageContext.request.contextPath}/user/account"
						class="list-group-item list-group-item-action active">My
						Account</a>
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath}/user/account"
						class="list-group-item list-group-item-action">My Account</a>
				</c:otherwise>
			</c:choose>




		</div>




		<c:if test="${ isLoggedIn == true }">
			<div class="card mt-5 m-2">
				<div class="card-body">
					<h5 class="card-title">Welcome, ${ currentUserName }</h5>
					<p class="card-text lead">your current rating is ${ currentUserRating }</p>
					<form action="${pageContext.request.contextPath}/logout">
						<input type="submit" value="Logout" class="btn btn-danger" />
					</form>

				</div>
			</div>
		</c:if>



	</div>
</div>
