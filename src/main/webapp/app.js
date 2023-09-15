document.addEventListener("DOMContentLoaded", function () {
    // Gọi hàm để lấy danh sách tài khoản và đổ chúng vào bảng
    loadAccounts().then(function () {

        // Gắn sự kiện click cho các nút "Xóa"
        document.addEventListener("click", function (event) {
            if (event.target.classList.contains("deleteButton")) {
                console.log("đã ckick")
                // Lấy accountId từ thuộc tính data-accountid của nút Xóa
                var accountId = event.target.value;
                console.log(accountId)

                // Gọi hàm deleteAccount với accountId đã lấy
                deleteAccount(accountId);
            }
        });


    })
        .catch(function (error) {
            console.error("Lỗi loadAccounts:", error);
        });

});

function loadAccounts() {
    return new Promise(function (resolve, reject) {
        fetch("ControlServlet?action=all")
            .then(function (response) {
                if (!response.ok) {
                    throw new Error("Lỗi kết nối mạng");
                }
                return response.json();
            })
            .then(function (data) {
                var tableBody = document.querySelector("#accountTable tbody");
                tableBody.innerHTML = "";

                data.forEach(function (account) {
                    var row = tableBody.insertRow(-1);

                    var idCell = row.insertCell(0);
                    idCell.textContent = account.accountID;

                    var nameCell = row.insertCell(1);
                    nameCell.textContent = account.name;

                    var emailCell = row.insertCell(2);
                    emailCell.textContent = account.email;

                    var phoneCell = row.insertCell(3);
                    phoneCell.textContent = account.phone;

                    var statusCell = row.insertCell(4);
                    if (account.status == true) {
                        statusCell.textContent = "Hoạt động";
                        statusCell.classList.add("status");
                    } else {
                        statusCell.textContent = "Đã khóa";
                        statusCell.classList.add("status");
                        statusCell.classList.add("locked");
                    }

                    var deleteCell = row.insertCell(5);
                    var deleteButton = document.createElement("button");
                    deleteButton.value = account.accountID;
                    deleteButton.textContent = "Xóa";
                    deleteButton.classList.add("deleteButton");
                    deleteButton.classList.add("btn-danger");
                    deleteButton.type = "button";
                    var grantButton = document.createElement("button");
                    grantButton.value = account.accountID;
                    grantButton.textContent = "Cấp quyền";
                    grantButton.classList.add("grantButton");
                    grantButton.classList.add("btn-success");
                    grantButton.type = "button";
                    var updateButton = document.createElement("button");
                    updateButton.value = account.accountID;
                    updateButton.textContent = "Cập nhật";
                    updateButton.classList.add("updateButton");
                    updateButton.classList.add("btn-warning");
                    updateButton.type = "button"
                    deleteCell.appendChild(deleteButton);
                    deleteCell.appendChild(grantButton);
                    deleteCell.appendChild(updateButton);
                });
            })
            .catch(function (error) {
                console.error("Lỗi Fetch:", error);
            });
        resolve();
    });
}

function deleteAccount(accountId) {
    fetch("ControlServlet?id=" + accountId + "&action=delete", {
        method: "DELETE"
    })

        .then(function (response) {
            if (!response.ok) {
                throw new Error("Lỗi kết nối mạng");
            }
            // Gọi hàm để cập nhật lại danh sách tài khoản sau khi xóa
            loadAccounts();
        })
        .catch(function (error) {
            console.error("Lỗi Fetch:", error);
        });
}

$(document).ready(function () {
    $('#buttonInfo').click(function () {
        fetch("ControlServlet?action=info", {
            method: "get"
        })
            .then(function (response) {
                if (!response.ok) {
                    throw new Error("Lỗi kết nối mạng");
                }
                // Gọi hàm để cập nhật lại danh sách tài khoản sau khi xóa
                return response.json();
            }).then(data => {
            // Xử lý dữ liệu nhận được
            $('#userInfoId').text(data.accountID);
            $('#userInfoName').text(data.name);
            $('#userInfoEmail').text(data.email);
            $('#userInfoPhone').text(data.phone);
            $('#userInfoPassword').text(data.password);
            $('#userInfoStatus').text(data.status == true ? "hoạt động" : "Đã khóa");
            // $('#userInfoRole').text(userInfo.role);
        })
            .catch(error => {
                // Xử lý lỗi
                console.error('There has been a problem with your fetch operation:', error);
            });
        fetch("ControlServlet?action=getRole", {
            method: "get",

        }).then(function (response) {
            if (!response.ok) {
                throw new Error("Lỗi kết nối mạng");
            }
            // Gọi hàm để cập nhật lại danh sách tài khoản sau khi xóa
            return response.json();
        }).then(data => {
            // Xử lý dữ liệu nhận được
                var role=[];
                data.forEach(n=>{
                    role.push(n.roleName);
                })
             $('#userInfoRole').text(role);
        })
            .catch(error => {
                // Xử lý lỗi
                console.error('There has been a problem with your fetch operation:', error);
            });
    });
});


