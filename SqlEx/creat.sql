CREATE TABLE web_users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    login_id VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    nickname VARCHAR(255) NOT NULL,
    role ENUM('USER', 'ADMIN') DEFAULT 'USER' NOT NULL,
    UNIQUE KEY (login_id)
);
CREATE TABLE gpt_questions (
    question_Id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    emotion VARCHAR(255) NOT NULL,
    question TEXT,
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
