function saveAccount() {


    var form = document.getElementById("addAccountForm");

// Lấy giá trị từ mỗi trường
    var id = form.querySelector("#id").value;
    var name = form.querySelector("#name").value;
    var email = form.querySelector("#email").value;
    var password = form.querySelector("#password").value;
    var phone = form.querySelector("#phone").value;
    var status = form.querySelector("#status").value;

    var color = $("#input_color");
    var selectedColor = color.val();
    var size = $("#input_size");
    var selectedSize = size.val();
    var masp = $("#product_info").val();
    var xhr = new XMLHttpRequest();


    var id = document.getElementById("id").value;
    var name = document.getElementById("name").value;
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    var phone = document.getElementById("phone").value;
    var status = document.getElementById("status").value;

// Tạo một đối tượng JSON từ các giá trị thu thập
    var formData = {
        id: id,
        name: name,
        email: email,
        password: password,
        phone: phone,
        status: status
    };

// Sử dụng fetch để gửi đối tượng JSON đến máy chủ hoặc điểm cuối API
    fetch('your_api_endpoint', {
        method: 'POST', // Hoặc 'PUT' nếu cần
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Gửi dữ liệu không thành công.');
            }
        })
        .then(data => {

            console.log(data);
        })
        .catch(error => {

            console.error('Lỗi:', error);
        });
}