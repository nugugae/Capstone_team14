
INSERT INTO users (login_id, password, nickname, provider, provider_id)
VALUES ('team14', 'password123', '백지수표', NULL, NULL);

INSERT INTO questions (id, question, question_date)
VALUES ((SELECT id FROM users WHERE login_id = 'team14'), 
        '오늘 하루 어땠나요?', NOW());

INSERT INTO answers (question_Id, answer_date, answer)
VALUES ((SELECT question_Id FROM questions ORDER BY question_date DESC LIMIT 1), 
        NOW(), 
        '오늘 하루는 정말 좋았어요.');

INSERT INTO diary (id, diary_date)
VALUES ((SELECT id FROM users WHERE login_id = 'team14'),
		CURDATE());