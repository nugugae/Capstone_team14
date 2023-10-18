const apiKey = "apiKey";

const userInput = document.getElementById("user-input");
const sendButton = document.querySelector(".submit-button");
const chatLog = document.querySelector(".chat-log");

const system_prompt = [              
    {"role": "system", "content": "You empathize with users and help the user take care of their minds."},
    {"role": "system", "content": "If you don't understand what the user says, ask again carefully."},
    
    {"role": "system", "content": "If you can guess what users feel, share the guess with the user."},

    {"role": "system", "content": "Before suggesting advice, you should ask if the user wants."},
    {"role": "system", "content": "You should give advice with numbering."},

    {"role": "system", "content": "If the user experiences an emotion, ask the user why they are feeling that emotion."},

    {"role": "system", "content": "You should say it in 50 words."},
    {"role": "system", "content": "You must say it in Korean."},
]

const conversation = [
    {"role": "assistant", "content": "오늘 하루는 어땠나요?"},
]

sendButton.addEventListener('click', () => {
    const userMessage = userInput.value;
    chatLog.innerHTML += `<div class="user-message">${userMessage}</div>`;
    conversation.push({"role": "user", "content": userMessage});
    userInput.value = "";
    
    // GPT-3 API 호출
    fetch('https://api.openai.com/v1/chat/completions', {
        method: 'POST',
        headers: {
            'Authorization': `Bearer ${apiKey}`,
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            model: "gpt-3.5-turbo",
            messages: system_prompt.concat(conversation),
        }),
    })
    .then(response => response.json())
    .then(data => {
        const botMessage = data.choices[0].message.content;
        chatLog.innerHTML += `<div class="chatbot-message">${botMessage}</div>`;
        conversation.push({"role":"assistant", "content": botMessage});
    });
});
