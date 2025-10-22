<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>ĐĂNG KÝ TÀI KHOẢN</h3>

	<c:if test="${not empty error}">
		<p style="color: red;">${error}</p>
	</c:if>

	<c:url value="/register" var="registerUrl"></c:url>
	<form action="${registerUrl}" method="post">
		<div>
			<label>Họ và tên:</label> <input type="text" name="fullName" required>
		</div>
		<div>
			<label>Email:</label> <input type="email" name="email" required>
		</div>
		<div>
			<label>Mật khẩu:</label> <input type="password" name="password"
				required>
		</div>
		<hr>
		<button type="submit">Đăng ký</button>
		<a href="<c:url value='/login'/>">Đã có tài khoản? Đăng nhập</a>
	</form>
</body>
</html>