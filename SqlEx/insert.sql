
-- 사용자 정보를 추가하는 INSERT 문
INSERT INTO users (login_id, password, nickname, provider, provider_id)
VALUES ('team14', 'password123', '백지수표', NULL, NULL);


-- 질문을 추가하는 INSERT 문. 초기 생성 시 question 필드는 비어있음. 질문 생성 후 update를 통해 필드 채워넣기
INSERT INTO questions (id, emotion, question_date)
VALUES ((SELECT id FROM users WHERE login_id = 'team14'), 
        '행복', NOW());

-- 답변을 추가하는 INSERT 문
INSERT INTO answers (question_Id, answer_date, answer)
VALUES ((SELECT question_Id FROM questions ORDER BY question_date DESC LIMIT 1), 
        NOW(), 
        '오늘 하루는 정말 좋았어요.');

