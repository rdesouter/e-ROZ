--CREATE DATABASE EROZ;

-- for reset while send mission isn't integrate
--TRUNCATE missions CASCADE;
--TRUNCATE missions_heroes;
--
--UPDATE heroes
--SET state = 'ACTIV';

CREATE TABLE missions(
	id SERIAL PRIMARY KEY,
	title VARCHAR(150) NOT NULL,
	difficulty NUMERIC ,
	safe_people INTEGER,
	town VARCHAR(100),
	award NUMERIC NOT NULL,
	date TIMESTAMP,
	accomplished TIMESTAMP,
	experience NUMERIC,
	is_launched BOOLEAN,
	is_done BOOLEAN,
	is_accomplished BOOLEAN
);

CREATE TABLE heroes(
	id SERIAL PRIMARY KEY,
	name VARCHAR(15) NOT NULL,
	ability TEXT,
	description TEXT,
	strength NUMERIC NOT NULL,
	balance NUMERIC,
	death_insurance NUMERIC,
	state VARCHAR(20) NOT NULL
);

CREATE TABLE company(
	id SERIAL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	assets NUMERIC NOT NULL,
	town VARCHAR(100) NOT NULL
);

CREATE TABLE missions_heroes(
	id_mission INTEGER NOT NULL,
	id_hero INTEGER NOT NULL,
	FOREIGN KEY (id_mission) REFERENCES missions(id),
	FOREIGN KEY (id_hero) REFERENCES heroes(id),
	PRIMARY KEY (id_mission,id_hero)
);