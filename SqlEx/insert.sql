INSERT INTO users (user_name, user_id, user_password)
VALUES ('백지수표', 'team14', 'password');

INSERT INTO questions (user_auto_Id, question, question_date)
VALUES ((SELECT user_auto_Id FROM users WHERE user_id = 'team14'), 
        '오늘 하루 어땠나요?', NOW());

INSERT INTO answers (question_id, answer_date, answer)
VALUES ((SELECT question_id FROM questions ORDER BY question_date DESC LIMIT 1), 
        NOW(), 
        '오늘 하루는 정말 좋았어요.');

INSERT INTO diary (user_auto_id, diary_date)
VALUES ((SELECT user_auto_id FROM users WHERE user_id = 'team14'),
		CURDATE());