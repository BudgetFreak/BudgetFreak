CREATE TABLE BF_PAYEE (
  ID IDENTITY,
  NAME VARCHAR(300) NOT NULL,
  DESCRIPTION VARCHAR(300),
  USER_ID BIGINT NOT NULL,

  CONSTRAINT PK_PAYEE PRIMARY KEY(ID),
  CONSTRAINT FK_PAYEE_USER FOREIGN KEY(USER_ID) REFERENCES BF_USER(ID)
)