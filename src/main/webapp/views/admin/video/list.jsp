<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách Video</title>
</head>
<body>
	<h3>DANH SÁCH VIDEO</h3>
	<a href="<c:url value='/admin/video/add' />">Thêm Video Mới</a>
	<hr>
	<table border="1" style="width: 100%; border-collapse: collapse;">
		<thead>
			<tr>
				<th>Video ID</th>
				<th>Poster</th>
				<th>Tiêu đề</th>
				<th>Lượt xem</th>
				<th>Danh mục</th>
				<th>Trạng thái (Active)</th>
				<th>Hành động</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${videos}" var="video">
				<tr>
					<td>${video.videoId}</td>
					<td>
						<c:url value="/image?fname=videos/${video.poster}" var="imgUrl"></c:url> 
						<img height="100px" src="${imgUrl}" alt="Poster">
					</td>
					<td>${video.title}</td>
					<td>${video.views}</td>
					<td>${video.category.name}</td> <td>${video.active ? 'Hiển thị' : 'Ẩn'}</td>
					<td>
						<a href="<c:url value='/admin/video/edit?videoId=${video.videoId}'/>">Sửa</a> | 
						<a href="<c:url value='/admin/video/delete?videoId=${video.videoId}'/>" onclick="return confirm('Bạn có chắc muốn xóa video này?')">Xóa</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>