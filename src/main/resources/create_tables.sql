CREATE TABLE users (
    id INT NOT NULL,
    username VARCHAR(40) NOT NULL,
    fullname VARCHAR(40) NOT NULL,
    password VARCHAR(40) NOT NULL,
    attempt INT,
    is_locked BOOLEAN DEFAULT FALSE,
    question1 VARCHAR(255),
    answer1 VARCHAR(255),
    question2 VARCHAR(255),
    answer2 VARCHAR(255),
    question3 VARCHAR(255),
    answer3 VARCHAR(255),
    is_first_time_login BOOLEAN DEFAULT TRUE,
    PRIMARY KEY (id)
);
