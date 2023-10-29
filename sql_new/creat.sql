CREATE TABLE webuser (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    nickname VARCHAR(100) NOT NULL UNIQUE,
    role VARCHAR(10) DEFAULT 'USER'
);

CREATE TABLE qna (
    qnaid BIGINT AUTO_INCREMENT PRIMARY KEY,
    id INT,
    question TEXT NOT NULL,
    answer TEXT NOT NULL,
    qnadate DATE NOT NULL,
    FOREIGN KEY (id) REFERENCES webuser(id)
);

CREATE TABLE emotion (
    emotion VARCHAR(100) NOT NULL,
    id INT,
    emotionDate DATETIME NOT NULL,
    FOREIGN KEY (id) REFERENCES webuser(id)
);

CREATE INDEX qna_ix1 ON qna (id, qnadate);
CREATE INDEX emotion_ix1 ON emotion (id, emotionDate);
