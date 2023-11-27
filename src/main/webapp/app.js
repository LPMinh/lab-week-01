document.addEventListener("DOMContentLoaded", function () {
    // Gọi hàm để lấy danh sách tài khoản và đổ chúng vào bảng
    loadAccounts("all").then(function () {

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

function loadAccounts(selectRole) {
    return new Promise(function (resolve, reject) {
        fetch("ControlServlet?action="+selectRole+"")
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
                    var roleCell=row.insertCell(5);
                    var deleteCell = row.insertCell(6);
                    var deleteButton = document.createElement("button");
                    deleteButton.value = account.accountID;
                    deleteButton.textContent = "Xóa";
                    deleteButton.classList.add("deleteButton");
                    deleteButton.classList.add("btn-danger");
                    deleteButton.type = "button";

                    var roleSelectionButton = document.createElement("button");
                    roleSelectionButton.textContent = "Cấp quyền";
                    roleSelectionButton.classList.add("btn-primary");
                    roleSelectionButton.setAttribute("data-toggle", "modal");
                    roleSelectionButton.setAttribute("data-target", "#roleModal");
                    roleSelectionButton.value=account.accountID;
                    var updateButton = document.createElement("button");
                    updateButton.value = account.accountID;
                    updateButton.textContent = "Cập nhật";
                    updateButton.classList.add("updateButton");
                    updateButton.classList.add("btn-warning");
                    updateButton.type = "button"
                    deleteCell.appendChild(deleteButton);
                    deleteCell.appendChild(roleSelectionButton);
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
            loadAccounts("all");
        })
        .catch(function (error) {
            console.error("Lỗi Fetch:", error);
        });
}
function updateRoles(userId, roles) {
    // Send a POST request to your server to update user roles
    fetch('RoleUpdateServlet', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            userId: userId,
            roles: roles,
        }),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            // Reload the accounts after updating roles
            return loadAccounts("all");
        })
        .then(() => {
            // Close the role modal after updating roles
            $('#roleModal').modal('hide');
        })
        .catch(error => {
            console.error('Error updating roles:', error);
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
$(document).ready(function () {
    // ...

    $('#roleModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Button that triggered the modal
        var userId = button.data('userid'); // Extract user ID from data attribute
        console.log("User ID:", userId);

        // Store the user ID in a hidden input field in the modal
        $('#roleUserId').val(userId);
    });

    // ...

    // Handle the click event on the Save button in the role modal
    $('#roleModalSaveButton').click(function () {
        // Get the selected roles
        var selectedRoles = [];
        $('.roleCheckbox:checked').each(function () {
            selectedRoles.push($(this).val());
        });

        // Get the user ID from the hidden input
        var userId = $('#roleUserId').val();

        // Call the function to update user roles
        updateRoles(userId, selectedRoles);
    });


});

$(document).ready(function() {
    $('#filterCombo').on('change', function() {
        var selectedValue = $(this).val();
        loadAccounts(selectedValue);
    });
});


