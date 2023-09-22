CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    login_id VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    nickname VARCHAR(255) NOT NULL,
    role ENUM('USER', 'ADMIN') DEFAULT 'USER' NOT NULL,
    provider VARCHAR(255),
    provider_id VARCHAR(255),
    UNIQUE KEY (login_id),
    UNIQUE KEY (provider, provider_id)
);

CREATE TABLE questions (
    question_Id INT AUTO_INCREMENT PRIMARY KEY,
    id INT,
    emotion VARCHAR(255) NOT NULL,
    question TEXT,
    question_date DATETIME NOT NULL,
    FOREIGN KEY (id) REFERENCES users(id)
);

CREATE TABLE answers (
    answer_Id INT AUTO_INCREMENT PRIMARY KEY,
    question_Id INT, 
    answer_date DATETIME NOT NULL,
    answer TEXT NOT NULL,
    FOREIGN KEY (question_Id) REFERENCES questions(question_Id)
);
