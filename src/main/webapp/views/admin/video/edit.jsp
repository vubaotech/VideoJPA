<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cập nhật Video</title>
</head>
<body>
	<h3>CẬP NHẬT VIDEO</h3>
	<c:url value="/admin/video/edit" var="editUrl"></c:url>
	<form action="${editUrl}" method="post" enctype="multipart/form-data">

		<div class="form-group">
			<label>Video ID:</label> <input type="text" class="form-control"
				name="videoId" value="${video.videoId}" readonly />
		</div>

		<div class="form-group">
			<label>Tiêu đề:</label> <input type="text" class="form-control"
				name="title" value="${video.title}" required />
		</div>
		<div class="form-group">
			<label>Mô tả:</label>
			<textarea class="form-control" name="description" rows="3">${video.description}</textarea>
		</div>

		<div class="form-group">
			<label>Ảnh Poster hiện tại:</label>
			<div>
				<c:url value="/image?fname=videos/${video.poster}" var="imgUrl"></c:url>
				<img height="150px" src="${imgUrl}" alt="Poster">
			</div>
			<label>Upload ảnh mới (nếu muốn thay đổi):</label> <input type="file"
				name="poster" />
		</div>

		<div class="form-group">
			<label>Danh mục:</label> <select name="categoryId"
				class="form-control">
				<c:forEach items="${categories}" var="cat">
					<option value="${cat.id}"
						${video.category.id == cat.id ? 'selected' : ''}>
						${cat.name}</option>
				</c:forEach>
			</select>
		</div>

		<div class="form-group">
			<label>Trạng thái (Active):</label> <label><input
				type="radio" name="active" value="true"
				${video.active ? 'checked' : ''}> Hiển thị</label> <label><input
				type="radio" name="active" value="false"
				${!video.active ? 'checked' : ''}> Ẩn</label>
		</div>

		<hr>
		<button type="submit" class="btn btn-default">Cập nhật</button>
		<a href="<c:url value='/admin/video/list'/>">Quay lại danh sách</a>
	</form>
</body>
</html>