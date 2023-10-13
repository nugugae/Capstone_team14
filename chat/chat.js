const apiKey = "apiKey";

const userInput = document.getElementById("user-input");
const sendButton = document.getElementById("submit-button");
const chatLog = document.getElementById("chat-log");

sendButton.addEventListener('click', () => {
    const userMessage = userInput.value;
    chatLog.innerHTML += `<div class="user-message">${userMessage}</div>`;
    userInput.value = "";

    // GPT-3 API 호출
    fetch('https://api.openai.com/v1/engines/text-davinci-002/completions', {
        method: 'POST',
        headers: {
            'Authorization': `Bearer ${apiKey}`,
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            prompt: userMessage,
            max_tokens: 2048,
        }),
    })
    .then(response => response.json())
    .then(data => {
        const botMessage = data.choices[0].message.content;
        chatLog.innerHTML += `<div class="chatbot-message">${botMessage}</div>`;
    });

});
