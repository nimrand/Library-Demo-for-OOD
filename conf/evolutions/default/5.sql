# --- !Ups

CREATE TABLE User (
    username VARCHAR(64) NOT NULL,
    password VARCHAR(64) NOT NULL
);

INSERT INTO User (username, password) VALUES ('admin', 'password')

# --- !Downs

DROP TABLE IF EXISTS User;