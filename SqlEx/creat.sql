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
                     question TEXT,
                     answer TEXT,
                     qnadate DATE NOT NULL,
                     FOREIGN KEY (uid) REFERENCES webuser(uid)
);

CREATE TABLE emotion (
                         mid BIGINT AUTO_INCREMENT PRIMARY KEY,
                         mood VARCHAR(100) NOT NULL,
                         uid INT,
                         mdate DATETIME NOT NULL,
                         FOREIGN KEY (uid) REFERENCES webuser(uid)
);
