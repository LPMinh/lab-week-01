<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="ControlServlet" method="get">
    <div>
        <label for="id">ID:</label>
        <input type="text" id="id" name="id" required>
    </div>

    <div>
        <label for="password">Mật khẩu:</label>
        <input type="password" id="password" name="password" required>
    </div>

    <div>
        <input type="submit" value="Đăng nhập">
    </div>
</form>
</body>
</html>