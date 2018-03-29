CREATE TABLE BF_ACCOUNT (
  ID IDENTITY,
  DESCRIPTION VARCHAR(300) NOT NULL,
  ON_BUDGET BOOLEAN NOT NULL,
  USER_ID BIGINT NOT NULL,

  CONSTRAINT PK_ACCOUNT PRIMARY KEY(ID),
  CONSTRAINT FK_ACCOUNT_USER FOREIGN KEY(USER_ID) REFERENCES BF_USER(ID)
)