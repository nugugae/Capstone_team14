        const API_URL = 'https://api.openai.com/v1/chat/completions';
        const API_KEY = "API_KEY";

        const userInput = document.getElementById("user-input");
        const sendButton = document.querySelector(".submit-button");
        const chatLog = document.querySelector(".chat-log");
        const resultContainer = document.getElementById('resultContainer');

        const system_prompt = [
            {"role": "system", "content": "You empathize with users and help the user take care of their minds. Answer in 50 words"},
            {"role": "system", "content": "If you don't understand what the user says, ask again carefully.Answer in 50 words"},
            {"role": "system", "content": "If you can guess what users feel, share the guess with the user.Answer in 50 words"},
            {"role": "system", "content": "Before suggesting advice, you should ask if the user wants.Answer in 50 words"},
            {"role": "system", "content": "You should give advice with numbering.Answer in 50 words"},
            {"role": "system", "content": "If the user experiences an emotion, ask the user why they are feeling that emotion.Answer in 50 words"},
            {"role": "system", "content": "You must say it in 50 words."},
            {"role": "system", "content": "You must say it in Korean.Answer in 50 words"},
        ]

        const conversation = [
            {"role": "assistant", "content": "오늘 하루는 어땠나요?"},
        ]

        const generate = async () => {
            const userMessage = userInput.value;
            userInput.value = "";
            sendButton.disabled = true;
            userInput.disabled = true;

            chatLog.innerHTML += `<div class="user-message">${userMessage}</div>`;
            conversation.push({"role": "user", "content": userMessage});
            userInput.value = "";
            let botMessage = "";

            const response = await fetch(API_URL, {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${API_KEY}`,
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    "model": "gpt-3.5-turbo",
                    "messages": system_prompt.concat(conversation),
                    max_tokens: 1024,
                    stream: true,
                }),
            });

            /*.then(response => response.json())
            .then(data => {
            const botMessage = data.choices[0].message.content;

            const chatbotMessages = chatLog.querySelectorAll('.chatbot-message');
            chatbotMessages[chatbotMessages.length - 1].remove();

            chatLog.innerHTML += `<div class="chatbot-message">${botMessage}</div>`; */


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
                    if (content) {
                        botMessage += content;
                        chatbotMessageDiv.textContent += content;
                    }

                }

            }

            console.log(botMessage);
            conversation.push({"role":"assistant", "content": botMessage});


                // Scroll
                chatLog.scrollTop = chatLog.scrollHeight;
                userInput.disabled = false;
                sendButton.disabled = false;
        };




    sendButton.addEventListener('click',() => {
        if (userInput.value.trim() !== '') {
                sendButton.disabled = false;
                generate();

            } else {
                sendButton.disabled = true;
            }

    });