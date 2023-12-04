--CREATE DATABASE IF NOT EXISTS bookvault;

CREATE TABLE IF NOT EXISTS librarians(
    librarian_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(30) NOT NULL,
    password_hash CHAR(128) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE,
    phone_number VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS patrons(
    patron_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE,
    phone_number VARCHAR(20) UNIQUE,
    registration_date DATE DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS series(
    series_id INT PRIMARY KEY AUTO_INCREMENT,
    series_name VARCHAR(100) NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS internal_locations(
    location_id INT PRIMARY KEY AUTO_INCREMENT,
    location_name VARCHAR(50) NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS books(
    book_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    subject VARCHAR(100),
    genre VARCHAR(30),
    series INT,
    volume_number INT,
    edition_number INT,
    imprint VARCHAR(100),
    publisher VARCHAR(100),
    place_of_publication VARCHAR(100),
    language VARCHAR(20),
    year_of_publication INT NOT NULL,
    isbn VARCHAR(13) NOT NULL,
    barcode VARCHAR(20) UNIQUE,
    accession_number VARCHAR(20) UNIQUE,
    location_id INT NOT NULL,
    entry_date DATETIME DEFAULT NOW(),
    status ENUM('Available', 'Checked Out', 'On Hold', 'Lost', 'Damaged') DEFAULT 'Available',
    current_holder INT DEFAULT NULL,
    notes TEXT,
    FOREIGN KEY(series) REFERENCES series(series_id),
    FOREIGN KEY(location_id) REFERENCES internal_locations(location_id)
);

CREATE TABLE IF NOT EXISTS authors(
    author_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS book_authors(
    book_id INT NOT NULL,
    author_id INT NOT NULL,
    PRIMARY KEY(book_id, author_id),
    FOREIGN KEY(book_id) REFERENCES books(book_id),
    FOREIGN KEY(author_id) REFERENCES authors(author_id)
);

CREATE TABLE IF NOT EXISTS borrows(
    borrow_id INT PRIMARY KEY AUTO_INCREMENT,
    patron_id INT NOT NULL,
    book_id INT NOT NULL,
    borrow_date TIMESTAMP DEFAULT NOW(),
    due_date TIMESTAMP DEFAULT DATE_ADD(NOW(), INTERVAL 2 WEEK),
    return_date TIMESTAMP,
    status ENUM('Borrowed', 'Returned') DEFAULT 'Borrowed',
    checked_out_by_librarian_id INT NOT NULL,
    checked_in_by_librarian_id INT NOT NULL,
    FOREIGN KEY(patron_id) REFERENCES patrons(patron_id),
    FOREIGN KEY(book_id) REFERENCES books(book_id),
    FOREIGN KEY(checked_out_by_librarian_id) REFERENCES librarians(librarian_id),
    FOREIGN KEY(checked_in_by_librarian_id) REFERENCES librarians(librarian_id)
);




















