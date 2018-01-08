# --- !Ups

CREATE TABLE LibraryMember (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
    name_id INTEGER,
    joined_date VARCHAR(32) NOT NULL,
    FOREIGN KEY(name_id) REFERENCES PersonName(id)
);

# --- !Downs

DROP TABLE IF EXISTS LibraryMember;