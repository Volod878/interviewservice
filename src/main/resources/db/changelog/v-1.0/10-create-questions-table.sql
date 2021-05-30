CREATE TABLE questions (
        id BIGSERIAL NOT NULL,
        name_question VARCHAR(255) NOT NULL,
        order_question INT8 NOT NULL,
        interview_id INT8,
        PRIMARY KEY (id)
    )
GO

ALTER TABLE IF EXISTS questions
       ADD CONSTRAINT FK97s5hq7gec8yp7ae5yjbcc4fd
       FOREIGN KEY (interview_id)
       REFERENCES interview_items
GO