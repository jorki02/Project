-- Table: users

CREATE TABLE users
(
  id INTEGER NOT NULL,
  username character varying(255) NOT NULL,
  password character varying(255) NOT NULL
)
WITH (
OIDS=FALSE
);

-- Table: roles

CREATE TABLE roles
(
  id INTEGER NOT NULL,
  name character varying(255) NOT NULL
)
WITH (
OIDS=FALSE
);

-- Table: user_roles

CREATE TABLE user_roles
(
  user_id integer NOT NULL,
  role_id integer NOT NULL
)
WITH (
OIDS=FALSE
);

CREATE TABLE words
(
  english character varying(255) NOT NULL,
  russian character varying(255) NOT NULL,
  example text
)
WITH (
OIDS=FALSE
);

-- Insert data

INSERT INTO roles VALUES (1, 'ROLE_USER');
INSERT INTO roles VALUES (2, 'ROLE_PREMIUM_USER');
