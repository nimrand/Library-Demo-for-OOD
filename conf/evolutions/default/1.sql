# Publishers schema

# --- !Ups

CREATE TABLE Publisher (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
    name varchar(255) NOT NULL
);

# --- !Downs

DROP TABLE Publisher;