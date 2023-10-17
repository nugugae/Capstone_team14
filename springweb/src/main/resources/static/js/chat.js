

/*
const apiKey = "api-key";

const userInput = document.getElementById("user-input");
const sendButton = document.querySelector(".submit-button");
const chatLog = document.querySelector(".chat-log");

sendButton.addEventListener('click', () => {
    const userMessage = userInput.value;
    chatLog.innerHTML += `<div class="user-message">${userMessage}</div>`;
    userInput.value = "";

    // GPT-3 API 호출
    fetch('https://api.openai.com/v1/chat/completions', {
        method: 'POST',
        headers: {
            'Authorization': `Bearer ${apiKey}`,
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
        chatLog.innerHTML += `<div class="chatbot-message">${botMessage}</div>`;
    });

});

*/

const API_URL = 'https://api.openai.com/v1/chat/completions';
const API_KEY = "api-key";

const userInput = document.getElementById("user-input");
const sendButton = document.querySelector(".submit-button");
const chatLog = document.querySelector(".chat-log");
const resultContainer = document.getElementById('resultContainer');

sendButton.addEventListener('click', () => {

    if (!userInput.value) {
        alert("Please enter a message.");
        return;
    }

    const userMessage = userInput.value;
    userInput.value = "";

    chatLog.innerHTML += `<div class="user-message">${userMessage}</div>`;
    
    sendButton.disabled = true;

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

        // Remove the "Generating..." message from chatLog
        const chatbotMessages = chatLog.querySelectorAll('.chatbot-message');
        chatbotMessages[chatbotMessages.length - 1].remove();

        chatLog.innerHTML += `<div class="chatbot-message">${botMessage}</div>`;
    });

});


        