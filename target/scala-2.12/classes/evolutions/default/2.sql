# --- !Ups

CREATE TABLE PersonName (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
    first_name varchar(256) NOT NULL,
    middle_name varchar(256) NOT NULL,
    last_name varchar(256) NOT NULL,
    suffix_name varchar(32) NULL,
    titles varchar(256) NOT NULL
);

CREATE TABLE Author (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
    name_id INTEGER,
    FOREIGN KEY(name_id) REFERENCES PersonName(id)
);

CREATE TABLE Book (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
    title varchar(256) NOT NULL,
    isbn varchar(13) NOT NULL,
    price REAL NOT NULL,
    keywords TEXT NOT NULL,
    description TEXT NOT NULL,
    call_number VARCHAR(32) NOT NULL,
    publication_date TEXT NOT NULL,
    publisher_id INTEGER NOT NULL,
    FOREIGN KEY(publisher_id) REFERENCES Publisher(id)
);

CREATE TABLE BookAuthor (
	author_id INTEGER NOT NULL,
	book_id INTEGER NOT NULL,
	FOREIGN KEY(author_id) REFERENCES Author(id),
	FOREIGN KEY(publisher_id) REFERENCES Publisher(id)
);

# --- !Downs

DROP TABLE BookAuthor;
DROP TABLE Book;
DROP TABLE Author;
DROP TABLE PersonName;