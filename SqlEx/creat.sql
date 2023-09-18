CREATE TABLE users (
    user_auto_Id INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL,
    user_id VARCHAR(255) UNIQUE NOT NULL,
    user_password VARCHAR(255) NOT NULL
);

CREATE TABLE questions (
    question_Id INT AUTO_INCREMENT PRIMARY KEY,
    user_auto_Id INT,
    question TEXT NOT NULL,
    question_date DATETIME NOT NULL,
    FOREIGN KEY (user_auto_Id) REFERENCES users(user_auto_Id)
);

CREATE TABLE answers (
    answer_Id INT AUTO_INCREMENT PRIMARY KEY,
    question_Id INT, 
    answer_date DATETIME NOT NULL,
    answer TEXT NOT NULL,
    FOREIGN KEY (question_Id) REFERENCES questions(question_Id)
);

CREATE TABLE diary (
   diary_Id INT AUTO_INCREMENT PRIMARY KEY,
   user_auto_Id INT, 
   diary_date DATE NOT NULL,
   reconstructed_text LONGTEXT,
   FOREIGN KEY (user_auto_Id) REFERENCES users(user_auto_Id)
);