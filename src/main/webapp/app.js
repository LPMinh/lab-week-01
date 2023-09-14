document.addEventListener("DOMContentLoaded", function () {
    // Gọi hàm để lấy danh sách tài khoản và đổ chúng vào bảng
    loadAccounts();
    var deleteButtons = document.querySelectorAll(".deleteButton");
    deleteButtons.forEach(function (button) {
        button.addEventListener("click", function () {
            // Trích xuất thông tin tài khoản từ hàng tương ứng
            var row = this.closest("tr");
            var id = row.cells[0].textContent; // Giả sử ID ở cột đầu tiên
            console.log("hahahah")
            // Gửi yêu cầu DELETE đến servlet
            deleteAccount(id);
        });
    });
});
function deleteAccount(accountId) {
    fetch("ControlServlet?id=" +accountId+"&action=delete", {
        method: "DELETE"
    })
        .then(function (response) {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            // Gọi hàm để cập nhật lại danh sách tài khoản sau khi xóa
            loadAccounts();
        })
        .catch(function (error) {
            console.error("Fetch error:", error);
        });
}
function loadAccounts() {
    fetch("ControlServlet?action=all")
        .then(function (response) {
            if (!response.ok) {
                throw new Error("Network response was not ok");
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
                if(account.status==true){
                    statusCell.textContent ="Hoạt động";
                    statusCell.classList.add("status")
                }else{
                    statusCell.textContent="Đã khóa"
                    statusCell.classList.add("status")
                    statusCell.classList.add("locked")
                }



                var deleteCell = row.insertCell(5);
                var deleteButton = document.createElement("button");
                deleteButton.textContent = "Xóa";
                deleteButton.classList.add("deleteButton");
                deleteButton.type="button"
                deleteCell.appendChild(deleteButton);
            });
        })
        .catch(function (error) {
            console.error("Fetch error:", error);
        });
}