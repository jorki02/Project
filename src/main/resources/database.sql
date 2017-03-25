-- Table: users

CREATE TABLE users
(
  password character varying(255) NOT NULL,
  username character varying(255),
  id serial NOT NULL
);

-- Table: roles

CREATE TABLE roles
(
  id INTEGER NOT NULL,
  name character varying(255) NOT NULL
);

-- Table: user_roles

CREATE TABLE user_roles
(
  user_id integer NOT NULL,
  role_id integer NOT NULL
);

CREATE TABLE words
(
  id serial NOT NULL,
  english character varying(255) NOT NULL,
  russian character varying(255) NOT NULL,
  meaning text,
  example text
);

CREATE TABLE progress
(
  id serial NOT NULL,
  word_id integer NOT NULL,
  user_id integer NOT NULL,
  last_repeat timestamp with time zone,
  count_repeat integer,
  add_date timestamp with time zone,
  next_repeat timestamp with time zone
);

CREATE TABLE progress_translation_word
(
  id serial NOT NULL,
  word_id integer NOT NULL,
  user_id integer NOT NULL,
  last_repeat timestamp with time zone,
  count_repeat integer,
  add_date timestamp with time zone,
  next_repeat timestamp with time zone
);

CREATE TABLE progress_word_translation
(
  id serial NOT NULL,
  word_id integer NOT NULL,
  user_id integer NOT NULL,
  last_repeat timestamp with time zone,
  count_repeat integer,
  add_date timestamp with time zone,
  next_repeat timestamp with time zone
);

-- Insert data

INSERT INTO roles VALUES (1, 'ROLE_USER');
INSERT INTO roles VALUES (2, 'ROLE_PREMIUM_USER');

CREATE TABLE heap_words
(
  english character varying(255),
  russian character varying(255)
);

INSERT INTO heap_words VALUES ('I', 'Я');
INSERT INTO heap_words VALUES ('bed', 'кровать');
INSERT INTO heap_words VALUES ('book', 'книга');
INSERT INTO heap_words VALUES ('come', 'приходить');
INSERT INTO heap_words VALUES ('do', 'делать');
INSERT INTO heap_words VALUES ('drink', 'пить');
INSERT INTO heap_words VALUES ('drive', 'водить машину');
INSERT INTO heap_words VALUES ('eat', 'есть');
INSERT INTO heap_words VALUES ('family', 'семья');
INSERT INTO heap_words VALUES ('father', 'отец');
INSERT INTO heap_words VALUES ('food', 'еда');
INSERT INTO heap_words VALUES ('friend', 'друг');
INSERT INTO heap_words VALUES ('go', 'идти');
INSERT INTO heap_words VALUES ('happy', 'счастливый');
INSERT INTO heap_words VALUES ('have', 'иметь');
INSERT INTO heap_words VALUES ('he', 'он');
INSERT INTO heap_words VALUES ('help', 'помощь');
INSERT INTO heap_words VALUES ('his', 'его');
INSERT INTO heap_words VALUES ('it', 'оно');
INSERT INTO heap_words VALUES ('like', 'нравиться');
INSERT INTO heap_words VALUES ('meet', 'встречаться');
INSERT INTO heap_words VALUES ('mother', 'мать');
INSERT INTO heap_words VALUES ('name', 'имя');
INSERT INTO heap_words VALUES ('read', 'читать');
INSERT INTO heap_words VALUES ('see', 'видеть');
INSERT INTO heap_words VALUES ('she', 'она');
INSERT INTO heap_words VALUES ('sit', 'сидеть');
INSERT INTO heap_words VALUES ('sleep', 'спать');
INSERT INTO heap_words VALUES ('think', 'дамать');
INSERT INTO heap_words VALUES ('want', 'хотеть');


