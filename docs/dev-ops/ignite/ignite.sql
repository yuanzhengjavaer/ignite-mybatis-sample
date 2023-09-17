-- PUBLIC.USER_ORDER definition

-- Drop table

-- DROP TABLE PUBLIC.USER_ORDER;

CREATE TABLE PUBLIC.USER_ORDER (
	ID BIGINT,
	USER_NAME VARCHAR(64) NOT NULL,
	USER_ID VARCHAR(24) NOT NULL,
	USER_MOBILE VARCHAR(20) NOT NULL,
	SKU VARCHAR(64) NOT NULL,
	SKU_NAME VARCHAR(128) NOT NULL,
	ORDER_ID VARCHAR(64) NOT NULL,
	QUANTITY INTEGER NOT NULL,
	UNIT_PRICE DECIMAL(10,2) NOT NULL,
	DISCOUNT_AMOUNT DECIMAL(10,2),
	TAX DECIMAL(4,2) NOT NULL,
	TOTAL_AMOUNT DECIMAL(10,2) NOT NULL,
	ORDER_DATE TIMESTAMP NOT NULL,
	ORDER_STATUS TINYINT NOT NULL,
	IS_DELETE TINYINT NOT NULL,
	UUID VARCHAR(128) NOT NULL,
	IPV4 VARCHAR(128) NOT NULL,
	IPV6 VARCHAR(128) NOT NULL,
	EXT_DATA VARCHAR(256),
	UPDATE_TIME TIMESTAMP NOT NULL,
	CREATE_TIME TIMESTAMP NOT NULL,
	CONSTRAINT ID PRIMARY KEY (ID)
);