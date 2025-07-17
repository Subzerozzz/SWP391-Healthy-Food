<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${sessionScope.account == null || sessionScope.account.role != 'admin'}">
    <c:redirect url="${pageContext.request.contextPath}/login"/>
</c:if>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Admin Chat System - GreenBite</title>
    
    <!-- CSS Files -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/flaticon.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/boxicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon.png">
    
    <style>
        .admin-chat-page {
            background-color: #f8f9fa;
            min-height: 100vh;
            padding: 20px 0;
        }
        
        .admin-chat-container {
            max-width: 1200px;
            margin: 0 auto;
            background: white;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.1);
            overflow: hidden;
            display: flex;
            height: 80vh;
        }
        
        .admin-header {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 20px;
            text-align: center;
            position: relative;
        }
        
        .admin-header h3 {
            margin: 0;
            font-size: 24px;
            font-weight: 600;
        }
        
        .back-btn {
            position: absolute;
            left: 20px;
            top: 50%;
            transform: translateY(-50%);
            background: rgba(255,255,255,0.2);
            border: none;
            color: white;
            padding: 8px 12px;
            border-radius: 8px;
            text-decoration: none;
            transition: all 0.3s ease;
        }
        
        .back-btn:hover {
            background: rgba(255,255,255,0.3);
            color: white;
        }
        
        .user-list {
            width: 350px;
            background-color: #f8f9fa;
            border-right: 1px solid #e0e0e0;
            display: flex;
            flex-direction: column;
        }
        
        .user-list-header {
            padding: 20px;
            background: #fff;
            border-bottom: 1px solid #e0e0e0;
        }
        
        .user-list-header h4 {
            margin: 0;
            color: #333;
            font-size: 18px;
            font-weight: 600;
        }
        
        .user-list-content {
            flex: 1;
            overflow-y: auto;
            padding: 10px;
        }
        
        .user-item {
            padding: 15px;
            margin: 5px 0;
            background-color: white;
            border: 1px solid #e0e0e0;
            border-radius: 10px;
            cursor: pointer;
            transition: all 0.3s ease;
            display: flex;
            align-items: center;
        }
        
        .user-item:hover {
            background-color: #e3f2fd;
            border-color: #2196f3;
            transform: translateY(-2px);
        }
        
        .user-item.selected {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            border-color: #667eea;
        }
        
        .user-item i {
            margin-right: 10px;
            font-size: 16px;
        }
        
        .chat-area {
            flex: 1;
            display: flex;
            flex-direction: column;
        }
        
        .chat-header {
            background: #fff;
            padding: 20px;
            border-bottom: 1px solid #e0e0e0;
            text-align: center;
        }
        
        .chat-header h4 {
            margin: 0;
            color: #333;
            font-size: 18px;
        }
        
        .chat-messages {
            flex: 1;
            padding: 20px;
            overflow-y: auto;
            background: #fafafa;
        }
        
        .message {
            margin: 15px 0;
            display: flex;
            align-items: flex-start;
        }
        
        .message.admin-message {
            justify-content: flex-end;
        }
        
        .message-bubble {
            max-width: 70%;
            padding: 15px 20px;
            border-radius: 20px;
            position: relative;
            word-wrap: break-word;
        }
        
        .admin-message .message-bubble {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            border-bottom-right-radius: 5px;
        }
        
        .user-message .message-bubble {
            background: white;
            color: #333;
            border: 1px solid #e0e0e0;
            border-bottom-left-radius: 5px;
        }
        
        .message-sender {
            font-weight: 600;
            margin-bottom: 5px;
            font-size: 12px;
            opacity: 0.8;
        }
        
        .message-time {
            font-size: 11px;
            opacity: 0.7;
            margin-top: 5px;
        }
        
        .message-input-container {
            background: white;
            padding: 20px;
            border-top: 1px solid #e0e0e0;
        }
        
        .message-input {
            display: flex;
            gap: 15px;
            align-items: center;
        }
        
        .message-input input {
            flex: 1;
            padding: 15px 20px;
            border: 2px solid #e0e0e0;
            border-radius: 25px;
            font-size: 14px;
            outline: none;
            transition: border-color 0.3s ease;
        }
        
        .message-input input:focus {
            border-color: #667eea;
        }
        
        .message-input input:disabled {
            background-color: #f5f5f5;
            cursor: not-allowed;
        }
        
        .message-input button {
            padding: 15px 25px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            border: none;
            border-radius: 25px;
            cursor: pointer;
            font-weight: 600;
            transition: all 0.3s ease;
            min-width: 80px;
        }
        
        .message-input button:hover:not(:disabled) {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
        }
        
        .message-input button:disabled {
            background: #ccc;
            cursor: not-allowed;
            transform: none;
        }
        
        .no-user-selected {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100%;
            color: #666;
            font-size: 18px;
            flex-direction: column;
        }
        
        .no-user-selected i {
            font-size: 48px;
            margin-bottom: 20px;
            opacity: 0.5;
        }
        
        .error-message {
            background: #ffebee;
            color: #c62828;
            padding: 15px;
            text-align: center;
            border-left: 4px solid #c62828;
            margin: 20px;
        }
        
        /* Scrollbar styling */
        .chat-messages::-webkit-scrollbar,
        .user-list-content::-webkit-scrollbar {
            width: 6px;
        }
        
        .chat-messages::-webkit-scrollbar-track,
        .user-list-content::-webkit-scrollbar-track {
            background: #f1f1f1;
        }
        
        .chat-messages::-webkit-scrollbar-thumb,
        .user-list-content::-webkit-scrollbar-thumb {
            background: #c1c1c1;
            border-radius: 3px;
        }
        
        .chat-messages::-webkit-scrollbar-thumb:hover,
        .user-list-content::-webkit-scrollbar-thumb:hover {
            background: #a8a8a8;
        }
        
        /* Responsive */
        @media (max-width: 768px) {
            .admin-chat-container {
                margin: 10px;
                height: 90vh;
                flex-direction: column;
            }
            
            .user-list {
                width: 100%;
                height: 200px;
                border-right: none;
                border-bottom: 1px solid #e0e0e0;
            }
            
            .user-list-content {
                display: flex;
                overflow-x: auto;
                overflow-y: hidden;
                padding: 10px;
            }
            
            .user-item {
                min-width: 150px;
                margin-right: 10px;
            }
        }
    </style>
</head>
<body>
    <div class="admin-chat-page">
        <div class="admin-header">
            <a href="${pageContext.request.contextPath}/admin/dashboard" class="back-btn">
                <i class='bx bx-arrow-back'></i>
            </a>
            <h3>Admin Chat System</h3>
        </div>
        
        <div class="admin-chat-container">
            <!-- User List -->
            <div class="user-list">
                <div class="user-list-header">
                    <h4><i class='bx bx-users'></i> Danh sách người dùng</h4>
                </div>
                <div class="user-list-content">
                    <c:choose>
                        <c:when test="${not empty requestScope.chatUsers}">
                            <c:forEach var="user" items="${requestScope.chatUsers}">
                                <div class="user-item ${requestScope.selectedUser eq user ? 'selected' : ''}" 
                                     onclick="selectUser('${user}')">
                                    <i class='bx bx-user'></i>
                                    <c:out value="${user}" escapeXml="true"/>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <div style="text-align: center; padding: 20px; color: #666;">
                                <i class='bx bx-chat' style="font-size: 32px; margin-bottom: 10px;"></i>
                                <p>Chưa có cuộc trò chuyện nào</p>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            
            <!-- Chat Area -->
            <div class="chat-area">
                <c:choose>
                    <c:when test="${not empty requestScope.selectedUser}">
                        <div class="chat-header">
                            <h4><i class='bx bx-message-dots'></i> Chat với ${requestScope.selectedUser}</h4>
                        </div>
                        
                        <c:if test="${not empty requestScope.error}">
                            <div class="error-message">
                                <i class='bx bx-error-circle'></i> ${requestScope.error}
                            </div>
                        </c:if>
                        
                        <div id="chatMessages" class="chat-messages">
                            <c:if test="${not empty requestScope.messages}">
                                <c:forEach var="message" items="${requestScope.messages}">
                                    <div class="message ${message.sender eq 'admin' ? 'admin-message' : 'user-message'}">
                                        <div class="message-bubble">
                                            <div class="message-sender">
                                                ${message.sender eq 'admin' ? 'Admin (Bạn)' : message.sender}
                                            </div>
                                            <div class="message-content">
                                                <c:out value="${message.message}" escapeXml="true"/>
                                            </div>
                                            <div class="message-time">${message.timestamp}</div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:if>
                        </div>
                        
                        <div class="message-input-container">
                            <div class="message-input">
                                <input type="text" id="messageInput" placeholder="Nhập tin nhắn..." 
                                       <c:if test="${empty requestScope.selectedUser}">disabled</c:if> />
                                <button id="sendBtn" type="button" <c:if test="${empty requestScope.selectedUser}">disabled</c:if>>
                                    <i class='bx bx-send'></i> Gửi
                                </button>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="no-user-selected">
                            <i class='bx bx-chat'></i>
                            <p>Chọn một người dùng để bắt đầu chat</p>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>

    <script src="${pageContext.request.contextPath}/js/chat.js"></script>
    <script>
        function selectUser(username) {
            window.location.href = window.location.pathname + "?user=" + encodeURIComponent(username);
        }

        document.addEventListener('DOMContentLoaded', function() {
            const selectedUser = '${requestScope.selectedUser}';
            if (selectedUser) {
                init({
                    chatBoxId: 'chatMessages',
                    messageInputId: 'messageInput',
                    sendBtnId: 'sendBtn',
                    currentUser: 'admin',
                    receiver: selectedUser,
                    isUserChat: false,
                    contextPath: '${pageContext.request.contextPath}'
                });
            }
        });
    </script>
</body>
</html> 