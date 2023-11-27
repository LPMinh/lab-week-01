<%@ page import="java.util.List" %>
<%@ page import="com.example.labweek01.models.Account" %><%--
  Created by IntelliJ IDEA.
  User: lyphi
  Date: 11/27/2023
  Time: 7:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="styles.css">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <!-- Thư viện jQuery (được sử dụng bởi Bootstrap) -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <!-- Thư viện Bootstrap JavaScript -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

  <title>Quản Lý Tài Khoản</title>

</head>
<body>
<%
  List<Account> accountList = (List<Account>) request.getAttribute("accounts");
  Account info=(Account) request.getAttribute("info");
%>
<div class="container">
  <h1>Quản Lý Tài Khoản</h1>
  <button id="addButton" data-toggle="modal" data-target="#addModal">Thêm</button>
  <button type="button" id="buttonInfo" class="btn-info" data-toggle="modal" data-target="#userInfoModal">
    Thông tin người dùng hiện tại
  </button>
  <form class="" action="SearchServlet" method="get">
    <label for="filterCombo">Chọn vai trò:</label>
    <select id="filterCombo" name="filter"  class="form-control">
      <option value="all">Tất cả</option>
      <option value="admin">Admin</option>
      <option value="user">User</option>
    </select>
    <button id="filterButton" type="submit" class="btn btn-primary">Lọc</button>
  </form>
  <table id="accountTable" class="table table-sm">
    <thead>
    <tr>
      <th>ID</th>
      <th>Tên</th>
      <th>Email</th>
      <th>Điện Thoại</th>
      <th>Trạng Thái</th>
      <th>quyền</th>
      <th>Thao tác</th> <!-- Thêm cột mới cho nút xóa -->
    </tr>
    </thead>
    <tbody>
    <% for (Account account : accountList) { %>
    <tr>
      <td><%= account.getAccountID() %></td>
      <td><%= account.getName() %></td>
      <td><%= account.getEmail() %></td>
      <td><%= account.getPhone() %></td>
      <td><%= account.isStatus() %></td>
      <td><%= account.getRole() %></td>
      <td>
        <a class="editButton btn btn-primary" href="update-account.jsp?id=<%=account.getAccountID()%>" >Sửa</a>
        <button class="deleteButton btn btn-danger">Xóa</button>
      </td>
    <tr/>
    <% } %>
    </tbody>
  </table>
</div>
<!-- Modal thêm -->
<div class="modal fade"  id="addModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="addModalLabel">Thêm Tài Khoản</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form id="addAccountForm" action="AccountServlet" method="post">
          <div class="form-group">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id">
          </div>
          <div class="form-group">
            <label for="name">Tên</label>
            <input type="text" class="form-control" id="name" name="name">
          </div>
          <div class="form-group">
            <label for="email">Email</label>
            <input type="email" class="form-control" id="email" name="email">
          </div>
          <div class="form-group">
            <label for="password">Mật khẩu</label>
            <input type="password" class="form-control" id="password" name="password">
          </div>
          <div class="form-group">
            <label for="phone">Điện Thoại</label>
            <input type="tel" class="form-control" id="phone" name="phone">
          </div>
          <div class="form-group">
            <label for="status">Trạng Thái</label>
            <select class="form-control" id="status" name="status">
              <option value="Hoạt động">Hoạt động</option>
              <option value="Khóa">Khóa</option>
            </select>
          </div>
          <div class="form-check">
            <input class="form-check-input roleCheckbox" name="role" type="radio" value="user" id="userCheckbox">
            <label class="form-check-label" for="userCheckbox">User</label>
          </div>
          <div class="form-check">
            <input class="form-check-input roleCheckbox" name="role" type="radio" value="admin" id="adminCheckbox">
            <label class="form-check-label" for="adminCheckbox">Admin</label>
          </div>
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
          <input type="submit" class="btn btn-primary" id="saveButton" >Lưu</input>
          <input type="hidden" name="action" value="saveAccount">
        </form>
      </div>
      <div class="modal-footer">

      </div>
    </div>
  </div>
</div>
<!-- Modal hiển thị thông tin người dùng hiện tại -->
<<div class="modal fade" id="userInfoModal" tabindex="-1" role="dialog" aria-labelledby="userInfoModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="userInfoModalLabel">Thông Tin Người Dùng Hiện Tại</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p><strong>ID:</strong> <span id="userInfoId"><%=info.getAccountID()%></span></p>
        <p><strong>Tên:</strong> <span id="userInfoName"></span><%=info.getName()%></p>
        <p><strong>Email:</strong> <span id="userInfoEmail"><%=info.getEmail()%></span></p>
        <p><strong>Điện Thoại:</strong> <span id="userInfoPhone"><%=info.getPhone()%></span></p>
        <p><strong>Mật khẩu:</strong> <span id="userInfoPassword"><%=info.getPassword()%></span></p>
        <p><strong>Trạng Thái:</strong> <span id="userInfoStatus"><%=info.isStatus()%></span></p>
        <p><strong>Quyền:</strong> <span id="userInfoRole"></span><%=info.getRole()%></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="roleModal" tabindex="-1" role="dialog" aria-labelledby="roleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="roleModalLabel">Select Role(s)</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form>

        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button id="roleModalSaveButton" type="button" class="btn btn-primary">Save</button>
      </div>
    </div>
  </div>
</div>



</body>
<script src="app.js"></script>
</html>


</body>
</html>
