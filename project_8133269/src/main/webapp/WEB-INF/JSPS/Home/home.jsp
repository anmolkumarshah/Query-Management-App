<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@include file="../Common/common_top.jsp"%>

<style>
#circle {
	width: 110%;
	height: 70%;
	-webkit-border-radius: 35px;
	-moz-border-radius: 35px;
	border-radius: 50%;
	background: rgb(160, 160, 248);
}
</style>
<title>Virtusa My Query</title>
</head>

<body>

	<div class="row">

		<%@include file="../Common/side_menu.jsp"%>

		<div class="col-sm-9  offset-sm-3 vh-100 border-start border-1">

			<%@include file="../Common/common_header.jsp"%>
			<div style="height: 100px"></div>

			<div class="row container">

				<div class="col-7 align-self-center">
					<div class="row">
						<div class="display-1">Simple Ask Queries at your
							fingertips</div>
					</div>
					<div class="mt-3">
						<form action="textSearch" class="row">
							<div class="col-9">
								<input class="form-control me-2" type="search" name="text"
									placeholder="Search Your Query" aria-label="Search">
							</div>
							<div class="col-3	">
								<button style="width: 100%" class="btn btn-outline-success"
									type="submit">Search</button>
							</div>
						</form>
					</div>
				</div>
				<div class="col-5 align-self-center">
					<div class="mx-auto" id="circle">
						<img alt="banner" src="/banner.png" height="100%">
					</div>

				</div>

			</div>

			<div class="container mt-5">
				<div class="row">
					<div class="col-6 align-self-center">
						<div class="border rounded-1 p-1">
							<img width="100%"
								src="https://cdn.pixabay.com/photo/2017/01/23/19/40/woman-2003647__340.jpg"
								alt="" />
						</div>
					</div>
					<div class="col-6 align-self-center">
						<div class="lead text-left">Lorem, ipsum dolor sit amet
							consectetur adipisicing elit. Aspernatur, id! Deleniti eius,
							pariatur ex fugiat, ipsam nisi quos velit vitae enim quisquam
							impedit, numquam illum quod. Eius facilis, enim laboriosam
							perspiciatis? Illo architecto nulla fugiat sunt earum nemo
							ducimus iusto similique, blanditiis quod aspernatur dolorem iure
							harum impedit sequi cum consequuntur modi quam dicta delectus
							distinctio quisquam dolorum dicta beatae unde ad.</div>
					</div>
				</div>


				<div class="row">

					<div class="col-6 align-self-center">
						<div class="lead text-left">Lorem, ipsum dolor sit amet
							consectetur adipisicing elit. Aspernatur, id! Deleniti eius,
							pariatur ex fugiat, ipsam nisi quos velit vitae enim quisquam
							impedit, numquam illum quod. Eius facilis, enim laboriosam
							aliquid eaque vero doloribus ratione repellendus voluptatum
							labore dignissimos autem sapiente quas fugiat maiores
							doloribus nulla voluptatem culpa illum quasi qui, molestias ipsam
							distinctio quisquam dolorum dicta beatae unde ad.</div>
					</div>

					<div class="col-6 align-self-center">
						<div class="border rounded-1 p-1">
							<img width="100%"
								src="https://cdn.pixabay.com/photo/2015/05/31/13/45/working-791849__340.jpg"
								alt="" />
						</div>
					</div>
				</div>

			</div>

		</div>
	</div>





	<%@include file="../Common/common_script.jsp"%>
</body>

</html>