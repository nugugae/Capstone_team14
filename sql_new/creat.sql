CREATE TABLE webuser (
    uid INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    nickname VARCHAR(100) NOT NULL UNIQUE,
    role VARCHAR(10) 
);

CREATE TABLE qna (
    qnaid BIGINT AUTO_INCREMENT PRIMARY KEY,
    uid INT NOT NULL,
    question TEXT NOT NULL,
    answer TEXT NOT NULL,
    qnadate DATE NOT NULL,
    FOREIGN KEY (uid) REFERENCES webuser(uid)
);

CREATE TABLE emotion (
    eid BIGINT NOT NULL AUTO_INCREMENT,
    emotion VARCHAR(100) NOT NULL,
    uid INT,
    emotionDate DATETIME NOT NULL,
    FOREIGN KEY (uid) REFERENCES webuser(uid)
);

CREATE INDEX qna_ix1 ON qna (uid, qnadate);
CREATE INDEX emotion_ix1 ON emotion (uid, emotionDate);
