DROP SCHEMA zpr;
CREATE SCHEMA zpr;
USE zpr;
CREATE TABLE cryptocurrency
(
    id    BIGINT(20)    NOT NULL AUTO_INCREMENT,
    time  DATETIME      NOT NULL,
    pair  VARCHAR(25)   NOT NULL,
    price DECIMAL(8, 2) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE bitcoin
(
    id        BIGINT(20)    NOT NULL AUTO_INCREMENT,
    timestamp DATETIME      NOT NULL,
    price     DECIMAL(8, 2) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE ethernum
(
    id        BIGINT(20)    NOT NULL AUTO_INCREMENT,
    timestamp DATETIME      NOT NULL,
    price     DECIMAL(8, 2) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE litecoin
(
    id        BIGINT(20)    NOT NULL AUTO_INCREMENT,
    timestamp DATETIME      NOT NULL,
    price     DECIMAL(8, 2) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE zcash
(
    id        BIGINT(20)    NOT NULL AUTO_INCREMENT,
    timestamp DATETIME      NOT NULL,
    price     DECIMAL(8, 2) NOT NULL,
    PRIMARY KEY (id)
);