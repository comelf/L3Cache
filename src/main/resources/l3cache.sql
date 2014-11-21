DROP TABLE IF EXISTS L3_USERS;

CREATE TABLE L3_USERS (
	userId 				int					auto_increment,
	email				varchar(50)			NOT NULL,
	password			varchar(50)			NOT NULL,
	PRIMARY KEY         (userId)
);

INSERT INTO L3_USERS VALUES(1,'test@test.com','pwpw');