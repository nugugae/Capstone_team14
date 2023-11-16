


    // API 설정을 읽어옵니다.
    const apiConfig = document.getElementById('api-config');
    API_URL = apiConfig.dataset.url || API_URL;
    API_MODEL = apiConfig.dataset.model || API_MODEL;
    API_KEY = apiConfig.dataset.key || API_KEY;

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
        {"role": "system", "content": "You must say it in 50 words."},
        {"role": "system", "content": "You must say it in Korean."},
    ]

    const conversation = [
        {"role": "assistant", "content": "오늘 하루는 어땠나요?"}
    ]
    const saveConversationToCapsule = async (answer, question) => {
        // Replace '/capsule/chat' with your actual endpoint for saving the conversation
        try {
            const response = await fetch('/capsule/chat', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    question: question,
                    answer: answer
                }),
            });
        // Check for a 201 Created status which indicates successful creation
        if (response.status === 201) {
            console.log('Conversation saved successfully.');
            const data = await response.json();  // If you need the created capsule's data
            console.log(data);  // Log or process the data as needed
        } else {
            console.error('Error saving conversation:', response.status, response.statusText);
            // Optionally, you can handle different types of errors based on the status code
        }
    } catch (error) {
        console.error('Error during fetch:', error);
        // Handle network errors or other fetch issues
    }
    };
    const generate = async () => {
        const answer = userInput.value;
        userInput.value = "";
        sendButton.disabled = true;
        userInput.disabled = true;

        chatLog.innerHTML += `<div class="user-message">${answer}</div>`;
        conversation.push({"role": "user", "content": answer});
        userInput.value = "";
        let question = "";
        const response = await fetch(API_URL, {
            method: 'POST',
            headers: {
                'Authorization':`Bearer ${API_KEY}`,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                "model": API_MODEL,
                "messages": system_prompt.concat(conversation),
                max_tokens: 1024,
                stream: true,
            }),
        });

        /*.then(response => response.json())
        .then(data => {
        const question = data.choices[0].message.content;

        const chatquestions = chatLog.querySelectorAll('.chatbot-message');
        chatquestions[chatquestions.length - 1].remove();

        chatLog.innerHTML += `<div class="chatbot-message">${question}</div>`; */


        const reader = response.body.getReader();
        const decoder = new TextDecoder("utf-8");

        const chatquestionDiv = document.createElement('div');
        chatquestionDiv.className = 'chatbot-message';
        chatLog.appendChild(chatquestionDiv);

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
                    question += content;
                    chatquestionDiv.textContent += content;
                }

            }

        }

        console.log(question);
        conversation.push({"role":"assistant", "content": question});

        // Save the conversation after the bot message is obtained
        await saveConversationToCapsule(answer, question);


        // Scroll
        chatLog.scrollTop = chatLog.scrollHeight;
        userInput.disabled = false;
        sendButton.disabled = false;
    };


    // const saveanswer = async (message) => {
    //     const response = await fetch('/chat', { //url 백에서 바꿔서 연동 & 엔드포인트 설정
    //         method: 'POST',
    //         headers: {
    //             'Content-Type': 'application/json',
    //         },
    //         body: JSON.stringify({ content: message }),
    //     });
    //
    //     if (response.status === 200) {
    //         console.log('User message saved successfully.');
    //     } else {
    //         console.error('Error saving user message.');
    //     }
    // };



    sendButton.addEventListener('click', (event) => {
        event.preventDefault(); // Prevent the form from submitting the traditional way
        if (userInput.value.trim() !== '') {
            generate();
        }
        // sendButton.addEventListener('click',() => {
        //     if (userInput.value.trim() !== '') {
        //         sendButton.disabled = false;
        //         generate();
        //
        //     } else {
        //         sendButton.disabled = true;
        //     }

    });

