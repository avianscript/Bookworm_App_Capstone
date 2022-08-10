BEGIN TRANSACTION;

DROP TABLE IF EXISTS prize;

CREATE TABLE prize (
	prize_id SERIAL,
	prize_name varchar(50) NOT NULL UNIQUE,
	description varchar(50) NOT NULL,
	milestone int NULL,
	maxprizes int NULL,
	startdate date NULL,
	enddate date NULL,

	CONSTRAINT PK_prizeid PRIMARY KEY (prize_id)
);


DROP TABLE IF EXISTS book;

CREATE TABLE book (
	book_id SERIAL,
	book_name varchar(50) NOT NULL,
	description varchar(50) NOT NULL,
	author varchar(50) NOT NULL,
	numberofpages int DEFAULT 0,
	rating varchar(2) NOT NULL,
	genre varchar(50) NOT NULL,

	CONSTRAINT PK_bookid PRIMARY KEY (book_id)
);

DROP TABLE IF EXISTS public.users;

CREATE TABLE IF NOT EXISTS public.users
(
    user_id  SERIAL,
    username character varying(50) NOT NULL,
    password_hash character varying(200) NOT NULL,
    role character varying(50),
    numofprizes integer DEFAULT 0,
    minutesread integer DEFAULT 0,
    booksread integer DEFAULT 0,
    familyaccount character varying(50),
    status smallint DEFAULT 1,
    CONSTRAINT pk_user PRIMARY KEY (user_id),
    CONSTRAINT users_username_key UNIQUE (username)
);

DROP TABLE IF EXISTS public.family_account;

CREATE TABLE IF NOT EXISTS public.family_account
(
    user_id  SERIAL,
    username character varying(50) NOT NULL,
    password_hash character varying(200) NOT NULL,
    role character varying(50),
    numofprizes integer DEFAULT 0,
    minutesread integer DEFAULT 0,
    booksread integer DEFAULT 0,
    familyaccount character varying(50),
    status smallint DEFAULT 1,
    CONSTRAINT pk_user PRIMARY KEY (user_id),
    CONSTRAINT users_username_key UNIQUE (username)
);



COMMIT TRANSACTION;

