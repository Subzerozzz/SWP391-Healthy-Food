<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
         <!--IzizToast-->
       <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/css/iziToast.min.css">
       <script src="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/js/iziToast.min.js"></script>
       
        <title>Xác thực OTP</title>
        <style>
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            }

            body {
                background-color: #f5f8fa;
                display: flex;
                justify-content: center;
                align-items: center;
                min-height: 100vh;
                background-image: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
            }

            .container {
                background-color: white;
                border-radius: 10px;
                box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
                width: 400px;
                padding: 30px;
                text-align: center;
            }

            .logo {
                margin-bottom: 20px;
            }

            .logo img {
                max-width: 120px;
            }

            h1 {
                color: #333;
                font-size: 24px;
                margin-bottom: 10px;
            }

            p {
                color: #666;
                margin-bottom: 25px;
                font-size: 14px;
                line-height: 1.6;
            }

            .otp-container {
                display: flex;
                justify-content: center;
                gap: 10px;
                margin-bottom: 25px;
            }

            .otp-input {
                width: 50px;
                height: 50px;
                border: 1px solid #ddd;
                border-radius: 5px;
                text-align: center;
                font-size: 20px;
                font-weight: bold;
                color: #333;
            }

            .otp-input:focus {
                border-color: #4CAF50;
                outline: none;
                box-shadow: 0 0 5px rgba(76, 175, 80, 0.3);
            }

            .btn {
                background-color: #4CAF50;
                color: white;
                border: none;
                padding: 12px 20px;
                border-radius: 5px;
                cursor: pointer;
                font-size: 16px;
                font-weight: 500;
                width: 100%;
                transition: background-color 0.3s;
            }

            .btn:hover {
                background-color: #45a049;
            }

            .resend {
                margin-top: 20px;
                font-size: 14px;
                color: #666;
            }

            .resend a {
                color: #4CAF50;
                text-decoration: none;
                font-weight: 500;
            }

            .resend a:hover {
                text-decoration: underline;
            }

            .error-message {
                color: #f44336;
                margin-bottom: 15px;
                font-size: 14px;
            }

            @media (max-width: 480px) {
                .container {
                    width: 90%;
                    padding: 20px;
                }

                .otp-input {
                    width: 40px;
                    height: 40px;
                }
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="logo">
                <img src="${pageContext.request.contextPath}/images/logos/logo-2.png" alt="Logo">
            </div>

            <h1>Xác thực OTP</h1>
            <p>Chúng tôi đã gửi mã xác thực đến email của bạn. Vui lòng nhập mã 6 chữ số để tiếp tục.</p>

            <c:if test="${not empty errorMessage}">
                <div class="error-message">${errorMessage}</div>
            </c:if>

            <form action="otp" method="post">
                <div class="otp-container">
                    <input type="text" class="otp-input" name="otp1" maxlength="1" autofocus>
                    <input type="text" class="otp-input" name="otp2" maxlength="1">
                    <input type="text" class="otp-input" name="otp3" maxlength="1">
                    <input type="text" class="otp-input" name="otp4" maxlength="1">
                    <input type="text" class="otp-input" name="otp5" maxlength="1">
                    <input type="text" class="otp-input" name="otp6" maxlength="1">
                </div>

<!--                <input type="hidden" name="email" value="${email}">
                <input type="hidden" name="action" value="${action}">-->

                <button type="submit" class="btn">Xác nhận</button>
            </form>

<!--            <div class="resend">
                Không nhận được mã? <a href="${pageContext.request.contextPath}/authen/resend-otp?email=${email}&action=${POST}">Gửi lại</a>
            </div>-->
        </div>

        <script>
            const otpInputs = document.querySelectorAll('.otp-input');

            otpInputs.forEach((input, index) => {
                input.addEventListener('keyup', (e) => {
                    if (e.key >= '0' && e.key <= '9') {
                        if (index < otpInputs.length - 1) {
                            otpInputs[index + 1].focus();
                        }
                    } else if (e.key === 'Backspace') {
                        if (index > 0 && input.value === '') {
                            otpInputs[index - 1].focus();
                        }
                    }
                });

                input.addEventListener('paste', (e) => {
                    e.preventDefault();
                    const pasteData = e.clipboardData.getData('text');
                    const otpDigits = pasteData.match(/\d/g);

                    if (otpDigits && otpDigits.length > 0) {
                        otpInputs.forEach((input, i) => {
                            if (i < otpDigits.length) {
                                input.value = otpDigits[i];
                            }
                        });

                        if (otpDigits.length < otpInputs.length) {
                            otpInputs[otpDigits.length].focus();
                        } else {
                            otpInputs[otpInputs.length - 1].focus();
                        }
                    }
                });
            });
        </script>
        
        <!--Thông báo lỗi đăng nhập--> 
        
        <c:if test="${toastType == 'error'}">
           <script>
              document.addEventListener("DOMContentLoaded", function () {
                iziToast.error({
                    title: "Thông báo",
                    message: '${sessionScope.toastMessage}',
                    position: 'topRight',
                    timeout: 5000,
                    backgroundColor:"#E53E31"
                    });
              });
            </script>
            <!--Xóa đi biến isDelete sau khi đã thông báo--> 
            <%
                session.removeAttribute("toastType");
                session.removeAttribute("toastMessage");
            %>
        </c:if>
    </body>
</html>