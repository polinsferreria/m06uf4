-- https://www.h2database.com/html/grammar.html

CREATE TABLE SMARTPHONES (

	id				BIGINT			NOT NULL,
	nombre			VARCHAR(150)	,
	marca			VARCHAR(50)		,
	precio			DOUBLE			,
	familia			VARCHAR(10)		,
	pulgadas		DOUBLE			,
	descatalogado	BOOLEAN			,
	
	PRIMARY KEY (id)

);