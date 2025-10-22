<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm Video Mới</title>
</head>
<body>
	<h3>THÊM VIDEO MỚI</h3>
	<c:url value="/admin/video/add" var="addUrl"></c:url>
	<form action="${addUrl}" method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label>Video ID:</label> <input type="text" class="form-control"
				placeholder="Nhập ID (vd: v001)" name="videoId" required />
		</div>
		<div class="form-group">
			<label>Tiêu đề:</label> <input type="text" class="form-control"
				placeholder="Nhập tiêu đề video" name="title" required />
		</div>
		<div class="form-group">
			<label>Mô tả:</label>
			<textarea class="form-control" name="description" rows="3"></textarea>
		</div>
		<div class="form-group">
			<label>Ảnh Poster:</label> <input type="file" name="poster" required />
		</div>
		<div class="form-group">
			<label>Danh mục:</label> <select name="categoryId"
				class="form-control">
				<c:forEach items="${categories}" var="cat">
					<option value="${cat.id}">${cat.name}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<label>Trạng thái (Active):</label> <label><input
				type="radio" name="active" value="true" checked> Hiển thị</label> <label><input
				type="radio" name="active" value="false"> Ẩn</label>
		</div>

		<hr>
		<button type="submit" class="btn btn-default">Thêm</button>
		<button type="reset" class="btn btn-primary">Hủy</button>
	</form>
</body>
</html>