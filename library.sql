CREATE DATABASE IF NOT EXISTS library_db;
USE library_db;

DROP TABLE IF EXISTS books;

CREATE TABLE books (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100),
    author VARCHAR(100),
    isAvailable BOOLEAN DEFAULT TRUE
);

INSERT INTO books (title, author, isAvailable) VALUES
('Java Basics', 'James Gosling', TRUE),
('Database Systems', 'C. J. Date', TRUE),
('Effective Java', 'Joshua Bloch', TRUE);
