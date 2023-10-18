const apiKey = "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";
const apiUrl = 'https://api.openai.com/v1/chat/completions'; 

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

const generate = async () => {
    // Alert the user if no prompt value
    if (!userInput.value) {
      alert("글을 입력해주세요.");
      return;
    }
    
    const userMessage = userInput.value;
    chatLog.innerHTML += `<div class="user-message">${userMessage}</div>`;
    conversation.push({"role": "user", "content": userMessage});
    userInput.value = "";
    let botMessage = "";

    const response = await fetch(apiUrl, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${apiKey}`,
        },
        body: JSON.stringify({
            model: "gpt-3.5-turbo",
            messages: system_prompt.concat(conversation),
            max_tokens: 1024,
            stream: true, 
        }),

    });
  
    const reader = response.body.getReader();
    const decoder = new TextDecoder("utf-8");

    const chatbotMessageDiv = document.createElement('div');
    chatbotMessageDiv.className = 'chatbot-message';
    chatLog.appendChild(chatbotMessageDiv);

    while (true) {
        const { done, value } = await reader.read();
        if (done) {
            break;
        }

        const chunk = decoder.decode(value);
        const lines = chunk.split("\n");
        const parsedLines = lines
            .map((line) => line.replace(/^data: /, "").trim()) 
            .filter((line) => line !== "" && line !== "[DONE]") 
            .map((line) => JSON.parse(line)); 

        for (const parsedLine of parsedLines) {
            const { choices } = parsedLine;
            const { delta } = choices[0];
            const { content } = delta;
            botMessage += content;
            
            if (content) {
                chatbotMessageDiv.textContent += content;
            }
        }     

    }
    
    console.log(botMessage);
    conversation.push({"role":"assistant", "content": botMessage});
};

sendButton.addEventListener('click',() => {
    generate();
});

userInput.addEventListener("keyup", (event) => {
    if (event.key === "Enter") {
      generate();
    }
});
