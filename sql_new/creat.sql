CREATE TABLE webUser(
    uid INT AUTO_INCREMENT PRIMARY KEY,
    loginId VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    nickname VARCHAR(100) NOT NULL UNIQUE,
    role VARCHAR(10) DEFAULT 'USER'
);
CREATE TABLE qna (
    qnaId INT AUTO_INCREMENT PRIMARY KEY,
    uid INT,
    question TEXT NOT NULL,
    answer TEXT NOT NULL,
    qnaDate DATETIME NOT NULL,
    FOREIGN KEY (uid) REFERENCES webUser(uid)
);
CREATE TABLE emotion (
    emotion VARCHAR(100) NOT NULL,
	uid INT,
	emotionDate DATETIME NOT NULL,
	FOREIGN KEY (uid) REFERENCES webUser(uid)
);
CREATE index qna_ix1 on qna (uid, qnaDate);
CREATE index emotion_ix1 on emotion (uid, emotionDate);
