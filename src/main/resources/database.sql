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
ALTER TABLE users
  OWNER TO postgres;

-- Table: roles

CREATE TABLE roles
(
  id INTEGER NOT NULL,
  name character varying(255) NOT NULL
)
WITH (
OIDS=FALSE
);
ALTER TABLE roles
  OWNER TO postgres;

-- Table: user_roles

CREATE TABLE user_roles
(
  user_id integer NOT NULL,
  role_id integer NOT NULL,
  CONSTRAINT role_id_fk FOREIGN KEY (role_id)
  REFERENCES roles (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uesr_id_fk FOREIGN KEY (user_id)
  REFERENCES users (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT unique_user_role UNIQUE (user_id, role_id)
)
WITH (
OIDS=FALSE
);
ALTER TABLE user_roles
  OWNER TO postgres;

-- Insert data

INSERT INTO roles VALUES (1, 'ROLE_USER');
INSERT INTO roles VALUES (2, 'ROLE_PREMIUM_USER');
