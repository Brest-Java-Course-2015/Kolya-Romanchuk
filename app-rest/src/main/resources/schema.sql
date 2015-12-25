CREATE SCHEMA bank;

DROP TABLE user IF EXISTS;
DROP TABLE check IF EXISTS;
DROP TABLE transactions IF EXISTS;


CREATE TABLE user (
  id_user INT GENERATED BY DEFAULT AS IDENTITY (START WITH 1) PRIMARY KEY,
  login VARCHAR(50) NOT NULL,
  password VARCHAR(50) NOT NULL,
  role VARCHAR(50) DEFAULT 'ROLE_USER' NOT NULL,
  firstname VARCHAR(50),
  lastname VARCHAR(50),
  UNIQUE(login)
);

CREATE TABLE check
(
	id_check INT GENERATED BY DEFAULT AS IDENTITY (START WITH 1) PRIMARY KEY,
	checknumber INT NOT NULL,
	summa INT NOT NULL,
	id_user INT NOT NULL,
	UNIQUE(checknumber),
	FOREIGN KEY (id_user) REFERENCES user(id_user)
);

CREATE TABLE transaction
(
	id_transaction INT GENERATED BY DEFAULT AS IDENTITY (START WITH 1) PRIMARY KEY,
	checknumbersender INT NOT NULL,
	checknumberrecipient INT NOT NULL,
	summa INT,
	date DATE DEFAULT CURRENT_DATE NOT NULL,
	id_check INT NOT NULL,
	FOREIGN KEY (id_check) REFERENCES check(id_check)

);


