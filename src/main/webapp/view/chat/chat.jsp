<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${sessionScope.account == null}">
    <c:redirect url="${pageContext.request.contextPath}/login"/>
</c:if>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Chat với Admin - GreenBite</title>
    
    <!-- CSS Files -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/flaticon.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/boxicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon.png">
    
    <style>
        .chat-page {
            background-color: #f8f9fa;
            min-height: 100vh;
            padding: 50px 0;
        }
        
        .chat-container {
            max-width: 900px;
            margin: 0 auto;
            background: white;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.1);
            overflow: hidden;
        }
        
        .chat-header {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 25px;
            text-align: center;
            position: relative;
        }
        
        .chat-header h3 {
            margin: 0;
            font-size: 24px;
            font-weight: 600;
        }
        
        .chat-header .subtitle {
            margin-top: 5px;
            opacity: 0.9;
            font-size: 14px;
        }
        
        .back-btn {
            position: absolute;
            left: 25px;
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
        
        .user-info {
            background: #e3f2fd;
            padding: 15px 25px;
            border-bottom: 1px solid #e0e0e0;
        }
        
        .user-info p {
            margin: 0;
            color: #1976d2;
            font-weight: 500;
        }
        
        .chat-box {
            height: 500px;
            overflow-y: auto;
            padding: 25px;
            background: #fafafa;
        }
        
        .message {
            margin: 15px 0;
            display: flex;
            align-items: flex-start;
        }
        
        .message.my-message {
            justify-content: flex-end;
        }
        
        .message-bubble {
            max-width: 70%;
            padding: 15px 20px;
            border-radius: 20px;
            position: relative;
            word-wrap: break-word;
        }
        
        .my-message .message-bubble {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            border-bottom-right-radius: 5px;
        }
        
        .other-message .message-bubble {
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
            padding: 25px;
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
        
        .message-input button:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
        }
        
        .message-input button:active {
            transform: translateY(0);
        }
        
        .error-message {
            background: #ffebee;
            color: #c62828;
            padding: 15px;
            text-align: center;
            border-left: 4px solid #c62828;
            margin: 20px 25px;
        }
        
        /* Scrollbar styling */
        .chat-box::-webkit-scrollbar {
            width: 6px;
        }
        
        .chat-box::-webkit-scrollbar-track {
            background: #f1f1f1;
        }
        
        .chat-box::-webkit-scrollbar-thumb {
            background: #c1c1c1;
            border-radius: 3px;
        }
        
        .chat-box::-webkit-scrollbar-thumb:hover {
            background: #a8a8a8;
        }
        
        /* Responsive */
        @media (max-width: 768px) {
            .chat-container {
                margin: 20px;
                border-radius: 10px;
            }
            
            .chat-header {
                padding: 20px 15px;
            }
            
            .chat-header h3 {
                font-size: 20px;
            }
            
            .back-btn {
                left: 15px;
                padding: 6px 10px;
            }
            
            .chat-box {
                height: 400px;
                padding: 20px 15px;
            }
            
            .message-input-container {
                padding: 20px 15px;
            }
            
            .message-bubble {
                max-width: 85%;
                padding: 12px 16px;
            }
        }
    </style>
</head>
<body>
    <div class="chat-page">
        <div class="chat-container">
            <div class="chat-header">
                <a href="${pageContext.request.contextPath}/home" class="back-btn">
                    <i class='bx bx-arrow-back'></i>
                </a>
                <h3>Chat với Admin</h3>
                <div class="subtitle">Hỗ trợ trực tuyến 24/7</div>
            </div>
            
            <div class="user-info">
                <p><i class='bx bx-user'></i> Xin chào, <strong>${requestScope.currentUser}</strong>! Bạn đang chat với admin.</p>
            </div>
            
            <c:if test="${not empty requestScope.error}">
                <div class="error-message">
                    <i class='bx bx-error-circle'></i> ${requestScope.error}
                </div>
            </c:if>
            
            <div id="chatBox" class="chat-box">
                <c:if test="${not empty requestScope.messages}">
                    <c:forEach var="message" items="${requestScope.messages}">
                        <div class="message ${message.sender eq requestScope.currentUser ? 'my-message' : 'other-message'}">
                            <div class="message-bubble">
                                <div class="message-sender">
                                    ${message.sender eq requestScope.currentUser ? 'Bạn' : message.sender}
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
                    <input type="text" id="message" placeholder="Nhập tin nhắn của bạn..." />
                    <button id="sendBtn" type="button">
                        <i class='bx bx-send'></i> Gửi
                    </button>
                </div>
            </div>
        </div>
    </div>

    <script src="${pageContext.request.contextPath}/js/chat.js"></script>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            init({
                chatBoxId: 'chatBox',
                messageInputId: 'message',
                sendBtnId: 'sendBtn',
                currentUser: '${requestScope.currentUser}',
                receiver: 'admin',
                isUserChat: true,
                contextPath: '${pageContext.request.contextPath}'
            });
        });
    </script>
</body>
</html>