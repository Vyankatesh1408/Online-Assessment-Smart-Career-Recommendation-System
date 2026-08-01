CREATE DATABASE career_recommendation_db;

USE career_recommendation_db;

//roles table

CREATE TABLE roles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(30) NOT NULL UNIQUE
);




// user table 

CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,

    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,

    phone VARCHAR(15),

    gender ENUM('MALE','FEMALE','OTHER'),

    dob DATE,

    role_id BIGINT NOT NULL,

    status BOOLEAN DEFAULT TRUE,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_user_role
        FOREIGN KEY(role_id)
        REFERENCES roles(id)
);



CREATE TABLE categories (

    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    name VARCHAR(100) NOT NULL UNIQUE

);



CREATE TABLE assessments (

    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    title VARCHAR(100) NOT NULL,

    description TEXT,

    duration INT NOT NULL,

    total_marks INT NOT NULL,

    category_id BIGINT NOT NULL,

    created_by BIGINT NOT NULL,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_assessment_category
        FOREIGN KEY(category_id)
        REFERENCES categories(id),

    CONSTRAINT fk_assessment_user
        FOREIGN KEY(created_by)
        REFERENCES users(id)

);



CREATE TABLE questions (

    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    assessment_id BIGINT NOT NULL,

    question_text TEXT NOT NULL,

    difficulty ENUM('EASY','MEDIUM','HARD'),

    marks INT DEFAULT 1,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_question_assessment
        FOREIGN KEY (assessment_id)
        REFERENCES assessments(id)

);





CREATE TABLE options (

    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    question_id BIGINT NOT NULL,

    option_text VARCHAR(255) NOT NULL,

    is_correct BOOLEAN DEFAULT FALSE,

    CONSTRAINT fk_option_question
        FOREIGN KEY(question_id)
        REFERENCES questions(id)

);








