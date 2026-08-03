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





CREATE TABLE student_answers (

    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    student_id BIGINT NOT NULL,

    question_id BIGINT NOT NULL,

    selected_option_id BIGINT NOT NULL,

    answered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_answer_student
        FOREIGN KEY(student_id)
        REFERENCES users(id),

    CONSTRAINT fk_answer_question
        FOREIGN KEY(question_id)
        REFERENCES questions(id),

    CONSTRAINT fk_answer_option
        FOREIGN KEY(selected_option_id)
        REFERENCES options(id)

);





CREATE TABLE results (

    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    student_id BIGINT NOT NULL,

    assessment_id BIGINT NOT NULL,

    score INT NOT NULL,

    percentage DECIMAL(5,2),

    total_questions INT,

    correct_answers INT,

    wrong_answers INT,

    submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_result_student
        FOREIGN KEY(student_id)
        REFERENCES users(id),

    CONSTRAINT fk_result_assessment
        FOREIGN KEY(assessment_id)
        REFERENCES assessments(id)

);


CREATE TABLE careers (

    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    career_name VARCHAR(100) NOT NULL UNIQUE,

    description TEXT,

    required_skills TEXT

);




CREATE TABLE recommendations (

    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    student_id BIGINT NOT NULL,

    career_id BIGINT NOT NULL,

    confidence DECIMAL(5,2),

    recommended_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_recommendation_student
        FOREIGN KEY(student_id)
        REFERENCES users(id),

    CONSTRAINT fk_recommendation_career
        FOREIGN KEY(career_id)
        REFERENCES careers(id)

);




CREATE TABLE learning_resources (

    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    career_id BIGINT NOT NULL,

    title VARCHAR(150) NOT NULL,

    resource_url VARCHAR(500),

    resource_type ENUM('YOUTUBE','COURSE','DOCUMENTATION','BOOK'),

    CONSTRAINT fk_learning_resource_career
        FOREIGN KEY(career_id)
        REFERENCES careers(id)

);