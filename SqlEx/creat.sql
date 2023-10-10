CREATE TABLE web_users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    login_id VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(512) NOT NULL,
    nickname VARCHAR(100) NOT NULL UNIQUE,
    role VARCHAR(255) DEFAULT 'USER'
);
CREATE TABLE gpt_questions (
    question_Id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    emotion VARCHAR(100) NOT NULL,
    question TEXT NOT NULL,
    question_date DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES web_users(id)
);

CREATE TABLE user_answers (
    answer_Id INT AUTO_INCREMENT PRIMARY KEY,
    question_Id INT, 
    answer_date DATETIME NOT NULL,
    answer TEXT NOT NULL,
    FOREIGN KEY (question_Id) REFERENCES gpt_questions(question_Id)
);
