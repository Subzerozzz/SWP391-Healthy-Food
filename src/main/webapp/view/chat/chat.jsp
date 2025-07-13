<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${sessionScope.account == null}">
    <c:redirect url="${pageContext.request.contextPath}/login"/>
</c:if>

<html>
<head>
    <title>Chat với Admin</title>
    <style>
        .chat-container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
        }
        
        .chat-header {
            background-color: #007bff;
            color: white;
            padding: 15px;
            text-align: center;
            margin-bottom: 20px;
        }
        
        .chat-box {
            height: 400px;
            border: 1px solid #ddd;
            overflow-y: auto;
            padding: 10px;
            background-color: #f9f9f9;
            margin-bottom: 20px;
        }
        
        .message {
            margin: 5px 0;
            padding: 8px;
            border-radius: 5px;
        }
        
        .my-message {
            background-color: #007bff;
            color: white;
            text-align: right;
            margin-left: 20%;
        }
        
        .other-message {
            background-color: #e9ecef;
            color: #333;
            text-align: left;
            margin-right: 20%;
        }
        
        .message-input {
            display: flex;
            gap: 10px;
        }
        
        .message-input input {
            flex: 1;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        
        .message-input button {
            padding: 10px 20px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        
        .message-input button:hover {
            background-color: #218838;
        }
        
        .user-info {
            text-align: center;
            margin-bottom: 20px;
            color: #666;
        }
    </style>
    
    <!-- Load JavaScript variables and functions first -->
    <script>
        // Pass data to JavaScript
        var currentUser = '${requestScope.currentUser}';
        var receiver = '${requestScope.receiver}';
        
        // Debug: Check if variables are properly set
        console.log('JSP Variables loaded:');
        console.log('currentUser:', currentUser);
        console.log('receiver:', receiver);
        
        // Ensure variables are not empty strings
        if (!currentUser || currentUser === '') {
            console.error('currentUser is empty or undefined');
        }
        if (!receiver || receiver === '') {
            console.log('receiver is empty, will use default "admin"');
        }
        
        // Define sendMessage function in global scope immediately
        function sendMessage() {
            console.log('Global sendMessage function called');
            
            const messageInput = document.getElementById("message");
            const msg = messageInput.value.trim();
            
            console.log("Attempting to send message:", msg);
            
            if (msg) {
                if (window.ws && window.ws.readyState === WebSocket.OPEN) {
                    const rec = receiver || "admin";
                    window.ws.send(rec + "|" + msg);
                    messageInput.value = "";
                    console.log("Message sent successfully");
                } else {
                    console.error("WebSocket not ready. State:", window.ws ? window.ws.readyState : "undefined");
                    alert("Kết nối chat chưa sẵn sàng. Vui lòng đợi một chút và thử lại.");
                }
            }
        }
        
        // Ensure function is available globally
        window.sendMessage = sendMessage;
        console.log('sendMessage function defined in head:', typeof sendMessage);
    </script>
</head>
<body>
    <div class="chat-container">
        <div class="chat-header">
            <h3>Chat với Admin</h3>
        </div>
        
        <div class="user-info">
            <p>Xin chào, <strong>${requestScope.currentUser}</strong>! Bạn đang chat với admin.</p>
        </div>
        
        <c:if test="${not empty requestScope.error}">
            <div style="color: red; text-align: center; margin-bottom: 20px;">
                ${requestScope.error}
            </div>
        </c:if>
        
        <div id="chatBox" class="chat-box">
            <c:if test="${not empty requestScope.messages}">
                <c:forEach var="message" items="${requestScope.messages}">
                    <div class="message ${message.sender eq requestScope.currentUser ? 'my-message' : 'other-message'}">
                        <strong>${message.sender eq requestScope.currentUser ? 'Bạn' : message.sender}:</strong>
                        <c:out value="${message.message}" escapeXml="true"/>
                        <br><small>${message.timestamp}</small>
                    </div>
                </c:forEach>
            </c:if>
        </div>
        
        <div class="message-input">
            <input type="text" id="message" placeholder="Nhập tin nhắn..." />
            <button id="sendBtn" type="button">Gửi</button>
        </div>
    </div>

    <!-- Load chat.js for WebSocket handling -->
    <script src="${pageContext.request.contextPath}/view/chat/js/chat.js"></script>
    
    <!-- Setup button click handler after all scripts are loaded -->
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const sendBtn = document.getElementById('sendBtn');
            const messageInput = document.getElementById('message');
            
            // Add click handler
            sendBtn.addEventListener('click', function() {
                console.log('Button clicked, calling sendMessage');
                sendMessage();
            });
            
            // Add Enter key handler
            messageInput.addEventListener('keypress', function(event) {
                if (event.key === 'Enter') {
                    console.log('Enter pressed, calling sendMessage');
                    sendMessage();
                }
            });
            
            console.log('Event handlers attached successfully');
        });
    </script>
</body>
</html>