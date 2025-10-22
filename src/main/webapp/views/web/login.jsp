<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>ĐĂNG NHẬP</h3>
    
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>

    <c:url value="/login" var="loginUrl"></c:url>
    <form action="${loginUrl}" method="post">
        <div>
            <label>Email:</label>
            <input type="email" name="email" required>
        </div>
        <div>
            <label>Mật khẩu:</label>
            <input type="password" name="password" required>
        </div>
        <hr>
        <button type="submit">Đăng nhập</button>
        <a href="<c:url value='/register'/>">Chưa có tài khoản? Đăng ký</a>
    </form>
</body>
</html>