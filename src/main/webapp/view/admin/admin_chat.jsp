<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${sessionScope.account == null || sessionScope.account.role != 'admin'}">
    <c:redirect url="${pageContext.request.contextPath}/login"/>
</c:if>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Chat System</title>
    <style>
        .admin-chat-container {
            display: flex;
            height: 100vh;
            font-family: Arial, sans-serif;
        }
        
        .user-list {
            width: 300px;
            border-right: 1px solid #ddd;
            background-color: #f8f9fa;
            padding: 20px;
        }
        
        .user-list h3 {
            margin-top: 0;
            color: #333;
        }
        
        .user-item {
            padding: 10px;
            margin: 5px 0;
            background-color: white;
            border: 1px solid #ddd;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.2s;
        }
        
        .user-item:hover {
            background-color: #e9ecef;
        }
        
        .user-item.selected {
            background-color: #007bff;
            color: white;
        }
        
        .chat-area {
            flex: 1;
            display: flex;
            flex-direction: column;
        }
        
        .chat-header {
            background-color: #007bff;
            color: white;
            padding: 15px;
            text-align: center;
        }
        
        .chat-messages {
            flex: 1;
            padding: 20px;
            overflow-y: auto;
            background-color: #f9f9f9;
        }
        
        .message {
            margin: 10px 0;
            padding: 10px;
            border-radius: 5px;
            max-width: 70%;
        }
        
        .admin-message {
            background-color: #007bff;
            color: white;
            text-align: right;
            margin-left: auto;
        }
        
        .user-message {
            background-color: #e9ecef;
            color: #333;
            text-align: left;
        }
        
        .message-sender {
            font-weight: bold;
            margin-bottom: 5px;
        }
        
        .message-time {
            font-size: 0.8em;
            opacity: 0.7;
            margin-top: 5px;
        }
        
        .message-input {
            display: flex;
            padding: 20px;
            border-top: 1px solid #ddd;
            background-color: white;
        }
        
        .message-input input {
            flex: 1;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            margin-right: 10px;
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
        
        .no-user-selected {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100%;
            color: #666;
            font-size: 18px;
        }
        
        .error-message {
            color: red;
            text-align: center;
            padding: 20px;
        }
    </style>
    
    <!-- JavaScript variables and functions -->
    <script>
        // Global variables
        var currentUser = '${requestScope.currentUser}';
        var selectedUser = '${not empty requestScope.selectedUser ? requestScope.selectedUser : ""}';
        var ws;
        
        console.log('Admin chat initialized with user:', currentUser, 'chatting with:', selectedUser);
        
        // Define sendAdminMessage function globally
        function sendAdminMessage() {
            console.log('sendAdminMessage called');
            
            const messageInput = document.getElementById("messageInput");
            const message = messageInput.value.trim();
            
            console.log('Attempting to send message:', message, 'to:', selectedUser);
            
            if (message && selectedUser && ws && ws.readyState === WebSocket.OPEN) {
                ws.send(selectedUser + "|" + message);
                messageInput.value = "";
                console.log('Message sent via WebSocket');
            } else {
                console.error('Cannot send message. WebSocket state:', ws ? ws.readyState : 'undefined');
                alert('Kết nối chat chưa sẵn sàng. Vui lòng đợi một chút và thử lại.');
            }
        }
        
        // Initialize WebSocket connection
        function initWebSocket() {
            if (selectedUser && selectedUser !== "" && currentUser === "admin") {
                console.log('Initializing admin WebSocket for user:', selectedUser);
                
                ws = new WebSocket("ws://" + window.location.host + "/SWP391-Healthy-Food/chat?username=" + encodeURIComponent(currentUser));
                
                ws.onopen = function() {
                    console.log("Admin WebSocket connection opened for chatting with:", selectedUser);
                };
                
                ws.onmessage = function(event) {
                    console.log("Admin received WebSocket message:", event.data);
                    
                    const chatMessages = document.getElementById("chatMessages");
                    const messageDiv = document.createElement("div");
                    messageDiv.className = "message";
                    
                    if (event.data.startsWith("You: ")) {
                        // Message sent by admin (confirmation)
                        messageDiv.className += " admin-message";
                        messageDiv.innerHTML = 
                            "<div class='message-sender'>Admin (Bạn)</div>" +
                            "<div>" + event.data.substring(5) + "</div>" +
                            "<div class='message-time'>Vừa gửi</div>";
                    } else {
                        // Message from user
                        const senderEnd = event.data.indexOf(": ");
                        if (senderEnd > 0) {
                            const sender = event.data.substring(0, senderEnd);
                            const messageContent = event.data.substring(senderEnd + 2);
                            messageDiv.className += " user-message";
                            messageDiv.innerHTML = 
                                "<div class='message-sender'>" + sender + "</div>" +
                                "<div>" + messageContent + "</div>" +
                                "<div class='message-time'>Vừa nhận</div>";
                        }
                    }
                    
                    chatMessages.appendChild(messageDiv);
                    chatMessages.scrollTop = chatMessages.scrollHeight;
                };
                
                ws.onclose = function() {
                    console.log("Admin WebSocket connection closed");
                };
                
                ws.onerror = function(error) {
                    console.log("Admin WebSocket error:", error);
                };
            }
        }
        
        function selectUser(username) {
            console.log('Selecting user:', username);
            window.location.href = window.location.pathname + "?user=" + encodeURIComponent(username);
        }
        
        // Make functions globally available
        window.sendAdminMessage = sendAdminMessage;
        window.initWebSocket = initWebSocket;
        window.selectUser = selectUser;
    </script>
</head>
<body>
    <div class="admin-chat-container">
        <!-- User List -->
        <div class="user-list">
            <h3>Danh sách người dùng</h3>
            <c:choose>
                <c:when test="${not empty requestScope.chatUsers}">
                    <c:forEach var="user" items="${requestScope.chatUsers}">
                        <div class="user-item ${requestScope.selectedUser eq user ? 'selected' : ''}" 
                             onclick="selectUser('${user}')">
                            <c:out value="${user}" escapeXml="true"/>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div style="text-align: center; color: #666; margin-top: 20px;">
                        Chưa có cuộc trò chuyện nào
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
        
        <!-- Chat Area -->
        <div class="chat-area">
            <c:choose>
                <c:when test="${not empty requestScope.selectedUser}">
                    <div class="chat-header">
                        <h3>Chat với <c:out value="${requestScope.selectedUser}" escapeXml="true"/></h3>
                    </div>
                    
                    <c:if test="${not empty requestScope.error}">
                        <div class="error-message">
                            <c:out value="${requestScope.error}" escapeXml="true"/>
                        </div>
                    </c:if>
                    
                    <div id="chatMessages" class="chat-messages">
                        <c:if test="${not empty requestScope.messages}">
                            <c:forEach var="message" items="${requestScope.messages}">
                                <div class="message ${message.sender eq 'admin' ? 'admin-message' : 'user-message'}">
                                    <div class="message-sender">
                                        <c:out value="${message.sender eq 'admin' ? 'Admin (Bạn)' : message.sender}" escapeXml="true"/>
                                    </div>
                                    <div><c:out value="${message.message}" escapeXml="true"/></div>
                                    <div class="message-time">${message.timestamp}</div>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                    
                    <div class="message-input">
                        <input type="text" id="messageInput" placeholder="Nhập tin nhắn...">
                        <button type="button" id="sendBtn">Gửi</button>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="no-user-selected">
                        Chọn một người dùng để bắt đầu chat
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <!-- Setup event handlers after DOM is loaded -->
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            console.log('DOM loaded, setting up event handlers');
            
            const messageInput = document.getElementById("messageInput");
            const sendBtn = document.getElementById("sendBtn");
            
            if (sendBtn) {
                sendBtn.addEventListener('click', function() {
                    console.log('Send button clicked');
                    sendAdminMessage();
                });
            }
            
            if (messageInput) {
                messageInput.addEventListener("keypress", function(event) {
                    if (event.key === "Enter") {
                        console.log('Enter key pressed');
                        sendAdminMessage();
                    }
                });
            }
            
            // Initialize WebSocket if user is selected
            if (selectedUser && selectedUser !== "") {
                console.log('Initializing WebSocket for selected user:', selectedUser);
                initWebSocket();
            }
            
            console.log('Event handlers setup complete');
        });
    </script>
</body>
</html> 