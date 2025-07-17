
let ws;
let chatBox;
let messageInput;
let sendBtn;
let currentUser;
let receiver;
let isUserChat = false;

function init(options) {
    chatBox = document.getElementById(options.chatBoxId);
    messageInput = document.getElementById(options.messageInputId);
    sendBtn = document.getElementById(options.sendBtnId);
    currentUser = options.currentUser;
    receiver = options.receiver;
    isUserChat = options.isUserChat;

    const wsProtocol = window.location.protocol === 'https:' ? 'wss:' : 'ws:';
    const wsUrl = `${wsProtocol}//${window.location.host}${options.contextPath}/chat?username=${encodeURIComponent(currentUser)}`;

    ws = new WebSocket(wsUrl);

    ws.onopen = function() {
        console.log("WebSocket connection opened.");
    };

    ws.onmessage = function(event) {
        displayMessage(event.data);
    };

    ws.onclose = function() {
        console.log("WebSocket connection closed.");
    };

    ws.onerror = function(error) {
        console.log("WebSocket error:", error);
    };

    sendBtn.addEventListener('click', sendMessage);
    messageInput.addEventListener('keypress', function(event) {
        if (event.key === 'Enter') {
            sendMessage();
        }
    });
}

function sendMessage() {
    const msg = messageInput.value.trim();
    if (msg) {
        const destination = isUserChat ? 'admin' : receiver;
        if (!destination) {
            console.error("No receiver selected.");
            return;
        }
        ws.send(`${destination}|${msg}`);
        messageInput.value = '';
    }
}

function displayMessage(message) {
    const messageDiv = document.createElement('div');
    messageDiv.className = 'message';

    let sender = 'Unknown';
    let content = message;
    let messageClass = 'other-message'; // Default for incoming messages

    if (message.startsWith("You: ")) {
        sender = 'You';
        content = message.substring(5);
        messageClass = 'my-message';
    } else {
        const parts = message.split(': ');
        if (parts.length > 1) {
            sender = parts[0];
            content = parts.slice(1).join(': ');
        }
    }

    if (sender === currentUser) {
        messageClass = 'my-message';
        sender = 'Bạn';
    } else if (currentUser === 'admin' && sender !== 'admin') {
        messageClass = 'user-message';
    } else if (currentUser !== 'admin' && sender === 'admin') {
        messageClass = 'other-message';
        sender = 'Admin';
    }

    messageDiv.classList.add(messageClass);

    // Create message bubble structure
    const messageBubble = document.createElement('div');
    messageBubble.className = 'message-bubble';

    const senderDiv = document.createElement('div');
    senderDiv.className = 'message-sender';
    senderDiv.textContent = sender;

    const contentDiv = document.createElement('div');
    contentDiv.className = 'message-content';
    contentDiv.textContent = content;

    const timeDiv = document.createElement('div');
    timeDiv.className = 'message-time';
    timeDiv.textContent = 'Vừa gửi';

    messageBubble.appendChild(senderDiv);
    messageBubble.appendChild(contentDiv);
    messageBubble.appendChild(timeDiv);
    messageDiv.appendChild(messageBubble);

    chatBox.appendChild(messageDiv);
    chatBox.scrollTop = chatBox.scrollHeight;
}
