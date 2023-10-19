const API_URL = 'https://api.openai.com/v1/chat/completions';
const API_KEY = "API_KEY";

const userInput = document.getElementById("user-input");
const sendButton = document.querySelector(".submit-button");
const chatLog = document.querySelector(".chat-log");
const resultContainer = document.getElementById('resultContainer');

// 사용자가 메시지를 입력할 때마다 버튼을 활성화
userInput.addEventListener('input', () => {
    if (userInput.value.trim() !== '') {
        sendButton.disabled = false;
    } else {
        sendButton.disabled = true;
    }
});

sendButton.addEventListener('click', () => {

    if (!userInput.value) {
        alert("Please enter a message.");
        return;
    }

    const userMessage = userInput.value;
    userInput.value = "";

    chatLog.innerHTML += `<div class="user-message">${userMessage}</div>`;
    
    sendButton.disabled = true;

    userInput.disabled = true;

    // "Generating..." message 
    chatLog.innerHTML += '<div class="chatbot-message">Generating...</div>';

    // GPT-3 API 호출
    fetch(API_URL, {
        method: 'POST',
        headers: {
            'Authorization': `Bearer ${API_KEY}`,
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            "model": "gpt-3.5-turbo",
            "messages": [
                {"role": "system", "content": "You have to provide mental care to user.answer in 50 words"},
                {"role": "user", "content": userMessage}
            ]
        }),
    })
    .then(response => response.json())
    .then(data => {
        const botMessage = data.choices[0].message.content;

        // "Generating..." message 삭제
        const chatbotMessages = chatLog.querySelectorAll('.chatbot-message');
        chatbotMessages[chatbotMessages.length - 1].remove();

        chatLog.innerHTML += `<div class="chatbot-message">${botMessage}</div>`;

        // Scroll
        chatLog.scrollTop = chatLog.scrollHeight;

        userInput.disabled = false;
    });

});
