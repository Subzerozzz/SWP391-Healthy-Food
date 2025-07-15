<%-- 
    Document   : headerDashboard
    Created on : May 26, 2025, 6:03:59 PM
    Author     : Dell
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>JSP Page</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" />
</head>

<body>
  <div class="header-dashboard">
    <div class="wrap">
      <div class="header-left" style="visibility: hidden;">
        <a href="index.html">
          <img class="" id="logo_header_mobile" alt="" src="${pageContext.request.contextPath}/images/logo/logo.png"
            data-light="${pageContext.request.contextPath}/images/logo/logo.png"
            data-dark="${pageContext.request.contextPath}/images/logo/logo-dark.png" data-width="154px"
            data-height="52px" data-retina="${pageContext.request.contextPath}/images/logo/logo@2x.png">
        </a>
        <div class="button-show-hide">
          <i class="icon-menu-left"></i>
        </div>
        <form class="form-search flex-grow">
          <fieldset class="name">
            <input type="text" placeholder="Search here..." class="show-search" name="name" tabindex="2" value=""
              aria-required="true" required="">
          </fieldset>
          <div class="button-submit">
            <button class="" type="submit"><i class="icon-search"></i></button>
          </div>
          <div class="box-content-search" id="box-content-search">
            <ul class="mb-24">
              <li class="mb-14">
                <div class="body-title">Top selling product</div>
              </li>
              <li class="mb-14">
                <div class="divider"></div>
              </li>
              <li>
                <ul>
                  <li class="product-item gap14 mb-10">
                    <div class="image no-bg">
                      <img src="${pageContext.request.contextPath}/images/products/17.png" alt="">
                    </div>
                    <div class="flex items-center justify-between gap20 flex-grow">
                      <div class="name">
                        <a href="product-list.html" class="body-text">Dog Food Rachael Ray Nutrish®</a>
                      </div>
                    </div>
                  </li>
                  <li class="mb-10">
                    <div class="divider"></div>
                  </li>
                  <li class="product-item gap14 mb-10">
                    <div class="image no-bg">
                      <img src="${pageContext.request.contextPath}/images/products/18.png" alt="">
                    </div>
                    <div class="flex items-center justify-between gap20 flex-grow">
                      <div class="name">
                        <a href="product-list.html" class="body-text">Natural Dog Food Healthy Dog Food</a>
                      </div>
                    </div>
                  </li>
                  <li class="mb-10">
                    <div class="divider"></div>
                  </li>
                  <li class="product-item gap14">
                    <div class="image no-bg">
                      <img src="${pageContext.request.contextPath}/images/products/19.png" alt="">
                    </div>
                    <div class="flex items-center justify-between gap20 flex-grow">
                      <div class="name">
                        <a href="product-list.html" class="body-text">Freshpet Healthy Dog Food and Cat</a>
                      </div>
                    </div>
                  </li>
                </ul>
              </li>
            </ul>
            <ul class="">
              <li class="mb-14">
                <div class="body-title">Order product</div>
              </li>
              <li class="mb-14">
                <div class="divider"></div>
              </li>
              <li>
                <ul>
                  <li class="product-item gap14 mb-10">
                    <div class="image no-bg">
                      <img src="${pageContext.request.contextPath}/images/products/20.png" alt="">
                    </div>
                    <div class="flex items-center justify-between gap20 flex-grow">
                      <div class="name">
                        <a href="product-list.html" class="body-text">Sojos Crunchy Natural Grain Free...</a>
                      </div>
                    </div>
                  </li>
                  <li class="mb-10">
                    <div class="divider"></div>
                  </li>
                  <li class="product-item gap14 mb-10">
                    <div class="image no-bg">
                      <img src="${pageContext.request.contextPath}/images/products/21.png" alt="">
                    </div>
                    <div class="flex items-center justify-between gap20 flex-grow">
                      <div class="name">
                        <a href="product-list.html" class="body-text">Kristin Watson</a>
                      </div>
                    </div>
                  </li>
                  <li class="mb-10">
                    <div class="divider"></div>
                  </li>
                  <li class="product-item gap14 mb-10">
                    <div class="image no-bg">
                      <img src="${pageContext.request.contextPath}/images/products/22.png" alt="">
                    </div>
                    <div class="flex items-center justify-between gap20 flex-grow">
                      <div class="name">
                        <a href="product-list.html" class="body-text">Mega Pumpkin Bone</a>
                      </div>
                    </div>
                  </li>
                  <li class="mb-10">
                    <div class="divider"></div>
                  </li>
                  <li class="product-item gap14">
                    <div class="image no-bg">
                      <img src="${pageContext.request.contextPath}/images/products/23.png" alt="">
                    </div>
                    <div class="flex items-center justify-between gap20 flex-grow">
                      <div class="name">
                        <a href="product-list.html" class="body-text">Mega Pumpkin Bone</a>
                      </div>
                    </div>
                  </li>
                </ul>
              </li>
            </ul>
          </div>
        </form>
      </div>
      <div class="header-grid d-flex justify-content-end align-items-center">
        <c:if test="${sessionScope.account.role eq 'admin'}">
          <div class="chat-notification-container">
            <div class="chat-notification-icon" onclick="toggleChatPopup()">
              <i class="icon-message-square"></i>
              <span class="chat-badge" id="chatBadge" style="display: none;">0</span>
            </div>

            <!-- Chat Popup -->
            <div class="chat-popup" id="chatPopup">
              <div class="chat-popup-header">
                <h5>Chat Messages</h5>
                <button class="close-chat" onclick="toggleChatPopup()">×</button>
              </div>

              <div class="chat-users-list" id="chatUsersList">
                <div class="loading-text">Đang tải...</div>
              </div>

              <div class="chat-conversation" id="chatConversation" style="display: none;">
                <div class="chat-conversation-header">
                  <button class="back-to-users" onclick="backToUsersList()">←</button>
                  <span class="current-chat-user" id="currentChatUser"></span>
                </div>

                <div class="chat-messages-container" id="chatMessagesContainer">
                  <!-- Messages will be loaded here -->
                </div>

                <div class="chat-input-popup">
                  <input type="text" id="adminChatInput" placeholder="Nhập tin nhắn...">
                  <button onclick="sendAdminMessage()">Gửi</button>
                </div>
              </div>
            </div>
          </div>
        </c:if>

        <c:if test="${sessionScope.account.role ne 'admin'}">
          <div class="chat-icon-container">
            <a href="${pageContext.request.contextPath}/chatpage" class="chat-icon-link">
              <i class="icon-message-square"></i>
            </a>
          </div>
        </c:if>

        <div class="popup-wrap user type-header">
          <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton3"
              data-bs-toggle="dropdown" aria-expanded="false">
              <span class="header-user wg-user">
                <span class="image">
                  <img src="${pageContext.request.contextPath}/images/user_icon2.png" alt="">
                </span>
                <span class="flex flex-column">
                  <span class="body-title mb-2">${sessionScope.account.getUser_name()}</span>
                  <c:if test="${sessionScope.account.role eq 'nutri' }">
                    <span class="text-tiny">Nutritionist</span>
                  </c:if>

                  <c:if test="${sessionScope.account.role eq 'admin' }">
                    <span class="text-tiny">Admin</span>
                  </c:if>

                  <c:if test="${sessionScope.account.role eq 'customer' }">
                    <span class="text-tiny">Customer</span>
                  </c:if>

                  <c:if test="${sessionScope.account.role eq 'manager' }">
                    <span class="text-tiny">Manager</span>
                  </c:if>
                  <c:if test="${sessionScope.account.role eq 'seller'}">
                    <span class="text-tiny">Seller</span>
                  </c:if>
                  <c:if test="${sessionScope.account.role eq 'shipper'}">
                    <span class="text-tiny">Shipper</span>
                  </c:if>
                </span>
              </span>
            </button>
            <ul class="dropdown-menu dropdown-menu-end has-content" aria-labelledby="dropdownMenuButton3">

              <li>
                <a href="${pageContext.request.contextPath}/logout" class="user-item">
                  <div class="icon">
                    <i class="icon-log-out"></i>
                  </div>
                  <div class="body-title-2">Log out</div>
                </a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>


  <style>
    .header-dashboard {
      background-color: #F0FDF4;
    }

    .chat-notification-container {
      position: relative;
      margin-right: 20px;
    }

    .chat-notification-icon {
      font-size: 24px;
      color: #333;
      cursor: pointer;
      position: relative;
      padding: 8px;
      border-radius: 50%;
      transition: all 0.3s ease;
    }

    .chat-notification-icon:hover {
      background-color: #e3f2fd;
      color: #2196f3;
    }

    .chat-badge {
      position: absolute;
      top: -5px;
      right: -5px;
      background: #ff4444;
      color: white;
      border-radius: 50%;
      padding: 2px 6px;
      font-size: 12px;
      min-width: 18px;
      text-align: center;
    }

    .chat-popup {
      position: absolute;
      top: 100%;
      right: 0;
      width: 350px;
      height: 450px;
      background: white;
      border: 1px solid #e0e0e0;
      border-radius: 10px;
      box-shadow: 0 10px 30px rgba(0, 0, 0, 0.15);
      display: none;
      z-index: 1000;
      overflow: hidden;
    }

    .chat-popup-header {
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      color: white;
      padding: 15px;
      display: flex;
      justify-content: space-between;
      align-items: center;
    }

    .chat-popup-header h5 {
      margin: 0;
      font-size: 16px;
    }

    .close-chat {
      background: none;
      border: none;
      color: white;
      font-size: 20px;
      cursor: pointer;
      padding: 0;
      width: 25px;
      height: 25px;
      display: flex;
      align-items: center;
      justify-content: center;
    }

    .chat-users-list {
      height: 350px;
      overflow-y: auto;
      padding: 10px;
    }

    .chat-user-item {
      padding: 12px;
      border-bottom: 1px solid #f0f0f0;
      cursor: pointer;
      transition: background-color 0.2s;
      display: flex;
      align-items: center;
    }

    .chat-user-item:hover {
      background-color: #f5f5f5;
    }

    .chat-user-item i {
      margin-right: 10px;
      color: #666;
    }

    .chat-conversation {
      height: 100%;
      display: flex;
      flex-direction: column;
    }

    .chat-conversation-header {
      background: #f8f9fa;
      padding: 15px;
      border-bottom: 1px solid #e0e0e0;
      display: flex;
      align-items: center;
    }

    .back-to-users {
      background: none;
      border: none;
      font-size: 18px;
      cursor: pointer;
      margin-right: 10px;
      padding: 5px;
    }

    .chat-messages-container {
      flex: 1;
      overflow-y: auto;
      padding: 15px;
      background: #fafafa;
    }

    .popup-message {
      margin: 10px 0;
      display: flex;
      align-items: flex-start;
    }

    .popup-message.admin-message {
      justify-content: flex-end;
    }

    .popup-message-bubble {
      max-width: 70%;
      padding: 8px 12px;
      border-radius: 15px;
      font-size: 13px;
    }

    .admin-message .popup-message-bubble {
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      color: white;
    }

    .user-message .popup-message-bubble {
      background: white;
      color: #333;
      border: 1px solid #e0e0e0;
    }

    .chat-input-popup {
      padding: 15px;
      border-top: 1px solid #e0e0e0;
      display: flex;
      gap: 10px;
    }

    .chat-input-popup input {
      flex: 1;
      padding: 8px 12px;
      border: 1px solid #e0e0e0;
      border-radius: 20px;
      outline: none;
      font-size: 13px;
    }

    .chat-input-popup button {
      padding: 8px 15px;
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      color: white;
      border: none;
      border-radius: 20px;
      cursor: pointer;
      font-size: 13px;
    }

    .loading-text {
      text-align: center;
      padding: 20px;
      color: #666;
    }

    .chat-icon-container {
      margin-right: 20px;
    }

    .chat-icon-link {
      font-size: 24px;
      color: #333;
      text-decoration: none;
    }

    .chat-icon-link:hover {
      color: #007bff;
    }
  </style>

  <script>
    let adminChatWs;
    let currentChatUser = '';
    let isPopupOpen = false;

    function toggleChatPopup() {
      const popup = document.getElementById('chatPopup');
      isPopupOpen = !isPopupOpen;

      if (isPopupOpen) {
        popup.style.display = 'block';
        loadChatUsers();
        initAdminWebSocket();
      } else {
        popup.style.display = 'none';
        if (adminChatWs) {
          adminChatWs.close();
        }
      }
    }

    function loadChatUsers() {
      fetch('${pageContext.request.contextPath}/admin/chat')
        .then(response => response.text())
        .then(html => {
          const parser = new DOMParser();
          const doc = parser.parseFromString(html, 'text/html');
          const usersList = document.getElementById('chatUsersList');

          // Extract users from the admin chat page
          const userElements = doc.querySelectorAll('.user-item');
          if (userElements.length > 0) {
            usersList.innerHTML = '';
            userElements.forEach(userEl => {
              const username = userEl.textContent.trim();
              const userItem = document.createElement('div');
              userItem.className = 'chat-user-item';
              userItem.innerHTML = '<i class="icon-user"></i>' + username;
              userItem.onclick = () => openChatWithUser(username);
              usersList.appendChild(userItem);
            });
          } else {
            usersList.innerHTML = '<div class="loading-text">Chưa có tin nhắn nào</div>';
          }
        })
        .catch(error => {
          console.error('Error loading chat users:', error);
          document.getElementById('chatUsersList').innerHTML = '<div class="loading-text">Lỗi tải dữ liệu</div>';
        });
    }

    function openChatWithUser(username) {
      currentChatUser = username;
      document.getElementById('currentChatUser').textContent = username;
      document.getElementById('chatUsersList').style.display = 'none';
      document.getElementById('chatConversation').style.display = 'flex';

      // Load chat history
      loadChatHistory(username);

      // Re-attach enter key handler to new input (in case of re-render)
      setTimeout(() => {
        const input = document.getElementById('adminChatInput');
        if (input) {
          input.focus();
          input.addEventListener('keypress', function (e) {
            if (e.key === 'Enter') {
              sendAdminMessage();
            }
          });
        }
      }, 0);
    }

    function backToUsersList() {
      document.getElementById('chatUsersList').style.display = 'block';
      document.getElementById('chatConversation').style.display = 'none';
      currentChatUser = '';
    }

    function loadChatHistory(username) {
      fetch('${pageContext.request.contextPath}/admin/chat?user=' + encodeURIComponent(username))
        .then(response => response.text())
        .then(html => {
          const parser = new DOMParser();
          const doc = parser.parseFromString(html, 'text/html');
          const messagesContainer = document.getElementById('chatMessagesContainer');

          // Extract messages from the admin chat page
          const messageElements = doc.querySelectorAll('.message');
          messagesContainer.innerHTML = '';

          messageElements.forEach(msgEl => {
            const isAdmin = msgEl.classList.contains('admin-message');
            const messageDiv = document.createElement('div');
            messageDiv.className = 'popup-message ' + (isAdmin ? 'admin-message' : 'user-message');

            const bubble = document.createElement('div');
            bubble.className = 'popup-message-bubble';
            bubble.textContent = msgEl.querySelector('.message-content').textContent;

            messageDiv.appendChild(bubble);
            messagesContainer.appendChild(messageDiv);
          });

          messagesContainer.scrollTop = messagesContainer.scrollHeight;
        })
        .catch(error => {
          console.error('Error loading chat history:', error);
        });
    }

    function initAdminWebSocket() {
      if (adminChatWs) {
        adminChatWs.close();
      }

      const wsProtocol = window.location.protocol === 'https:' ? 'wss:' : 'ws:';
      const wsUrl = wsProtocol + '//' + window.location.host + '${pageContext.request.contextPath}/chat?username=admin';

      adminChatWs = new WebSocket(wsUrl);

      adminChatWs.onopen = function () {
        console.log('Admin WebSocket connected');
      };

      adminChatWs.onmessage = function (event) {
        const message = event.data;
        console.log('Admin received:', message);

        // Add message to current conversation if it's from the current user
        if (currentChatUser && message.startsWith(currentChatUser + ': ')) {
          displayPopupMessage(message, false);
        }

        // Update notification badge
        updateChatBadge();
      };

      adminChatWs.onclose = function () {
        console.log('Admin WebSocket disconnected');
      };
    }

    function sendAdminMessage() {
      const input = document.getElementById('adminChatInput');
      if (!input) return;
      const message = input.value.trim();

      if (message && currentChatUser) {
        const sendAction = () => {
          adminChatWs.send(currentChatUser + '|' + message);
          input.value = '';
          displayPopupMessage('You: ' + message, true);
        };

        if (adminChatWs && adminChatWs.readyState === WebSocket.OPEN) {
          sendAction();
        } else {
          // If socket is not ready, wait until it's open then send
          const waitInterval = setInterval(() => {
            if (adminChatWs && adminChatWs.readyState === WebSocket.OPEN) {
              clearInterval(waitInterval);
              sendAction();
            }
          }, 100);
        }
      }
    }

    function displayPopupMessage(message, isAdmin) {
      const container = document.getElementById('chatMessagesContainer');
      const messageDiv = document.createElement('div');
      messageDiv.className = 'popup-message ' + (isAdmin ? 'admin-message' : 'user-message');

      const bubble = document.createElement('div');
      bubble.className = 'popup-message-bubble';

      if (message.startsWith('You: ')) {
        bubble.textContent = message.substring(5);
      } else {
        const colonIndex = message.indexOf(': ');
        if (colonIndex > 0) {
          bubble.textContent = message.substring(colonIndex + 2);
        } else {
          bubble.textContent = message;
        }
      }

      messageDiv.appendChild(bubble);
      container.appendChild(messageDiv);
      container.scrollTop = container.scrollHeight;
    }

    function updateChatBadge() {
      // This would typically query for unread messages
      // For now, we'll just show a simple notification
      const badge = document.getElementById('chatBadge');
      badge.style.display = 'block';
      badge.textContent = '!';
    }

    // Handle Enter key in chat input
    document.addEventListener('DOMContentLoaded', function () {
      const adminChatInput = document.getElementById('adminChatInput');
      if (adminChatInput) {
        adminChatInput.addEventListener('keypress', function (e) {
          if (e.key === 'Enter') {
            sendAdminMessage();
          }
        });
      }
    });
  </script>

</body>

</html>