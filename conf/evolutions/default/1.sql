# books schema

# --- !Ups
CREATE TABLE books (
    id INT NOT NULL PRIMARY KEY,
    title VARCHAR(11),
    isbn VARCHAR(11)
);


# --- !Downs

DROP TABLE User;