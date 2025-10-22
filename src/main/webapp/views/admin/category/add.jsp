<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="page-content-wrapper">

	<div class="page-content">

		<!-- /. ROW -->


		<div class="row">

			<div class="col-md-12">

				<!-- Form Elements -->

				<div class="panel panel-default">

					<div class="panel-heading">Thêm danh mục</div>

					<div class="panel-body">

						<div class="row">

							<div class="col-md-6">

								<h3>Danh mục:</h3>

								<c:url value="/admin-category/create" var="edit"></c:url>

								<form role="form" action="${edit}" method="post"
									enctype="multipart/form-data">

									<div class="form-group">

										<label>Tên danh mục:</label> <input type="text"
											class="form-control" name="name" />

									</div>

									<div class="form-group">

										<label>Mã danh mục:</label> <input type="text"
											class="form-control" name="code" />

									</div>

									<div class="form-group">

										<label>Ảnh đại diện</label> <input type="file" name="images"
											value="" />

									</div>


									<button type="submit" class="btn btn-default">Thêm</button>

									<button type="reset" class="btn btn-primary">Thoát</button>

								</form>





							</div>

						</div>

					</div>

				</div>

				<!-- End Form Elements -->

			</div>

		</div>

		<!-- /. ROW -->

	</div>

</div>