CREATE TABLE interview_items (
        id BIGSERIAL NOT NULL,
        name_interview VARCHAR(255) NOT NULL,
        start_date TIMESTAMP NOT NULL,
        finish_date TIMESTAMP NOT NULL,
        activity VARCHAR(255) NOT NULL,
        PRIMARY KEY (id)
    )
GO