<%@ page import="com.example.labweek01.models.Account" %>
<%@ page import="com.example.labweek01.repository.AccountRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<%
   HttpSession session1 = request.getSession();
    Account account = (Account) session1.getAttribute("info");

%>
<div class="container mt-5">
    <form id="addAccountForm" action="UpdateServlet" method="post">
        <input type="hidden" name="id"  value="<%=account.getAccountID()%>">
        <div class="form-group">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" value="<%=account.getAccountID()%>">
        </div>
        <div class="form-group">
            <label for="name">Tên</label>
            <input type="text" class="form-control" id="name" name="name" value="<%=account.getName()%>">
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" class="form-control" id="email" name="email" value="<%=account.getEmail()%>">
        </div>
        <div class="form-group">
            <label for="password">Mật khẩu</label>
            <input type="password" class="form-control" id="password" name="password" value="<%=account.getPassword()%>">
        </div>
        <div class="form-group">
            <label for="phone">Điện Thoại</label>
            <input type="tel" class="form-control" id="phone" name="phone" value="<%=account.getName()%>">
        </div>
        <div class="form-group">
            <label for="status">Trạng Thái</label>
            <select class="form-control" id="status" name="status">
                <option  value="Hoạt động">Hoạt động</option>
                <option value="Khóa">Khóa</option>
            </select>
        </div>
        <div class="form-check">
            <input class="form-check-input" name="role" type="radio" value="user" id="userCheckbox">
            <label class="form-check-label" for="userCheckbox">User</label>
        </div>
        <div class="form-check">
            <input class="form-check-input" name="role" type="radio" value="admin" id="adminCheckbox">
            <label class="form-check-label" for="adminCheckbox">Admin</label>
        </div>

    </form>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
