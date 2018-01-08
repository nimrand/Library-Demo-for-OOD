# --- !Ups

CREATE TABLE BookLoan (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
    book_id INTERGER NOT NULL,
    member_id INTEGER NOT NULL,
    loaned_date VARCHAR(32) NOT NULL,
    due_date VARCHAR(32) NOT NULL,
    returned_date VARCHAR(32) NULL,
    FOREIGN KEY(book_id) REFERENCES Book(id),
    FOREIGN KEY(member_id) REFERENCES LibraryMember(id)
);

# --- !Downs

DROP TABLE IF EXISTS BookLoan;