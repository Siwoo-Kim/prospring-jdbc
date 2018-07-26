
CREATE USER 'siwoo'@'localhost' IDENTIFIED BY 'siwoo';

CREATE SCHEMA MUSICDB;
GRANT ALL PRIVILEGES ON MUSICDB.* TO 'siwoo'@'localhost';
FLUSH PRIVILEGES;

USE MUSICDB;
CREATE TABLE SINGER (
  ID INT NOT NULL AUTO_INCREMENT,
  FIRST_NAME VARCHAR(60) NOT NULL,
  LAST_NAME VARCHAR(60) NOT NULL,
  BIRTH_DATE DATE,
  UNIQUE UQ_SINGER_1 (FIRST_NAME, LAST_NAME),
  PRIMARY KEY (ID)
);

CREATE TABLE ALBUM (
  ID INT NOT NULL AUTO_INCREMENT,
  SINGER_ID INT NOT NULL,
  TITLE VARCHAR(100) NOT NULL,
  RELEASE_dATE DATE,
  UNIQUE UQ_SINGER_ALBUM_1 (SINGER_ID, TITLE),
  PRIMARY KEY (ID),
  CONSTRAINT FK_ALBUM
  FOREIGN KEY (SINGER_ID)
  REFERENCES SINGER (ID)
)