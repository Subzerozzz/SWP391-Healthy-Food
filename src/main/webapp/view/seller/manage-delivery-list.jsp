<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <!-- Theme Style -->
    <link
      rel="stylesheet"
      type="text/css"
      href="${pageContext.request.contextPath}/css/animate.min_1.css"
    />
    <link
      rel="stylesheet"
      type="text/css"
      href="${pageContext.request.contextPath}/css/animation.css"
    />
    <link
      rel="stylesheet"
      type="text/css"
      href="${pageContext.request.contextPath}/css/bootstrap.css"
    />
    <link
      rel="stylesheet"
      type="text/css"
      href="${pageContext.request.contextPath}/css/bootstrap-select.min.css"
    />
    <link
      rel="stylesheet"
      type="text/css"
      href="${pageContext.request.contextPath}/css/style_1.css"
    />

    <!--fontawsome-->
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"
    />

    <!-- Font -->
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/font/fonts.css"
    />

    <!-- Icon -->
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/icon/style.css"
    />

    <!-- Favicon and Touch Icons  -->
    <link
      rel="shortcut icon"
      href="${pageContext.request.contextPath}/images/favicon_1.png"
    />
    <link
      rel="apple-touch-icon-precomposed"
      href="${pageContext.request.contextPath}/images/favicon_1.png"
    />
    <style>
      .box-logo {
        height: 100px;
        overflow: hidden;
      }
      .logo {
        max-width: 100%;
        height: 100%;
        display: block;
      }

   .summary-item {
  display: flex;
  align-items: center;
  gap: 10px;
  margin-bottom: 8px;
}

.body-text {
  color: black;
  font-weight: 600;
  min-width: 120px; /* Giúp các label thẳng hàng */
}

.body-title-2 {
  color: black;
  flex: 1; /* Chiếm phần còn lại */
  word-break: break-word;
}
.feedback-card {
  display: flex;
  height: 75vh; /* Chiều cao phù hợp màn hình */
  max-height: 80vh;
  background: #ffffff;
  border-radius: 16px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
  overflow: hidden;
  padding: 20px;
  gap: 24px;
  box-sizing: border-box;
}

/* Bên trái: ảnh */
.feedback-left {
  flex: 0 0 35%;
  display: flex;
  align-items: center;
  justify-content: center;
}

.feedback-image {
  width: 100%;
  height: auto;
  max-height: 100%;
  border-radius: 12px;
  object-fit: cover;
  border: 1px solid #ccc;
}

/* Bên phải: nội dung */
.feedback-right {
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

/* Grid nội dung chia đều */
.feedback-grid {
   display: flex;
  flex-direction: column;
  gap: 20px; /* Khoảng cách giữa các dòng */
}

.feedback-item {
  display: flex;
  flex-direction: column;
}

.feedback-label {
  font-weight: 600;
  color: #444;
  margin-bottom: 4px;
  font-size:16px;
}

.feedback-value,
.feedback-text {
  font-size: 16px;
  color: #222;
  line-height: 1.5;
}

/* Nếu content dài, chiếm cả 2 cột */
.feedback-full {
  grid-column: span 2;
}
    </style>
    </head>
    <body>
        <h1>Hello World!</h1>
        <h1>This is my delevery list</h1>
        
    </body>
</html>
