let username;
// WebSocket will be stored in window.ws for global access

// Debug: Confirm that sendMessage function is being defined
console.log('chat.js loaded, sendMessage should already be defined:', typeof sendMessage);

window.onload = function () {
    console.log('window.onload executed');
    
    // Get username from JSP variables (global scope)
    username = currentUser;
    
    // Check receiver variable
    if (typeof receiver === 'undefined' || !receiver) {
        receiver = "admin";
    }

    if (!username) {
        alert("Lỗi: Không thể lấy thông tin người dùng. Vui lòng đăng nhập lại.");
        window.location.href = "/login";
        return;
    }

    console.log("Initializing chat for user:", username, "to receiver:", receiver);

    // Initialize WebSocket connection and store in window.ws for global access
    window.ws = new WebSocket("ws://" + window.location.host + "/SWP391-Healthy-Food/chat?username=" + encodeURIComponent(username));

    window.ws.onopen = function() {
        console.log("WebSocket connection opened");
    };

    window.ws.onmessage = function (event) {
        const chatBox = document.getElementById("chatBox");
        const messageDiv = document.createElement("div");
        messageDiv.className = "message";
        
        // Check if message is from current user
        if (event.data.startsWith("You: ")) {
            messageDiv.className += " my-message";
            messageDiv.innerHTML = "<strong>Bạn:</strong> " + event.data.substring(5);
        } else {
            messageDiv.className += " other-message";
            messageDiv.innerHTML = "<strong>Admin:</strong> " + event.data.substring(event.data.indexOf(": ") + 2);
        }
        
        chatBox.appendChild(messageDiv);
        chatBox.scrollTop = chatBox.scrollHeight;
    };

    window.ws.onclose = function() {
        console.log("WebSocket connection closed");
    };

    window.ws.onerror = function(error) {
        console.log("WebSocket error:", error);
    };
    
    // Debug: Check WebSocket and sendMessage status
    console.log('WebSocket initialized:', typeof window.ws);
    console.log('sendMessage function available:', typeof sendMessage);
    console.log('window.sendMessage available:', typeof window.sendMessage);
};
