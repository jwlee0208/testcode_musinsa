DROP TABLE IF EXISTS PRODUCT_OPTION;

CREATE TABLE IF NOT EXISTS PRODUCT_OPTION (
	PRODUCT_NAME CHARACTER VARYING(30) NOT NULL,
	OPTION_NAME CHARACTER VARYING(30) NOT NULL,
	PRODUCT_OPTION_CNT INTEGER DEFAULT 0,
	CONSTRAINT PRODUCT_OPTION_PK PRIMARY KEY (PRODUCT_NAME,OPTION_NAME)
);

-- CREATE UNIQUE INDEX PRIMARY_KEY_1 ON PRODUCT_OPTION (PRODUCT_NAME,OPTION_NAME);

INSERT INTO PRODUCT_OPTION (PRODUCT_NAME, OPTION_NAME, PRODUCT_OPTION_CNT) VALUES('prd-a','opt-aa',0);
INSERT INTO PRODUCT_OPTION (PRODUCT_NAME, OPTION_NAME, PRODUCT_OPTION_CNT) VALUES('prd-a','opt-ab',0);
INSERT INTO PRODUCT_OPTION (PRODUCT_NAME, OPTION_NAME, PRODUCT_OPTION_CNT) VALUES('prd-b','opt-ba',0);
INSERT INTO PRODUCT_OPTION (PRODUCT_NAME, OPTION_NAME, PRODUCT_OPTION_CNT) VALUES('prd-b','opt-bb',0);
INSERT INTO PRODUCT_OPTION (PRODUCT_NAME, OPTION_NAME, PRODUCT_OPTION_CNT) VALUES('prd-b','opt-bc',0);
INSERT INTO PRODUCT_OPTION (PRODUCT_NAME, OPTION_NAME, PRODUCT_OPTION_CNT) VALUES('prd-c','opt-ca',0);